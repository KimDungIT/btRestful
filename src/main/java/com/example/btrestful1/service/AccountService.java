package com.example.btrestful1.service;

import com.example.btrestful1.exception.NotFoundException;
import com.example.btrestful1.model.Account;
import com.example.btrestful1.repository.AccountRes;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    AccountRes accountRes;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //save
    public Account saveAccount(Account account)
    {
        logger.info("save account ");
        account.setPassword(passwordEncoder().encode(account.getPassword()));
        return accountRes.save(account);

    }

    public Account getById(int id)
    {
        logger.info("get account by id "+ id);
       return accountRes.findById(id).orElseThrow(() -> new NotFoundException("ko co"));
    }

    //update password

    public Account updatePassword(String password, int id)
    {
        Account account = new Account();
        if (getById(id)!=null)
        {
            logger.info("update account " + id);
            accountRes.updatePassword(passwordEncoder().encode(password), id);
        }

        return null;
    }

    //getAccountByUsernameAndPassword

    public Account getAccountByUsernameAndPassword(String username, String password)
    {
        Account account = accountRes.findAccountByUsernameAndPassword(username, passwordEncoder().encode(password));
        if(account!=null)
        {
            logger.info("get account by username and password");
            return account;
        }
        else {
            logger.error("not found account by username and password");
            throw  new NotFoundException("ko tim thay");
        }

    }

    //delete account

    public Account deleteAccountById(int id)
    {
        Account account = getById(id);
        if(account!=null)
        {
            logger.info("delet account by id");
            accountRes.delete(getById(id));

            return account;
        }
        throw new NotFoundException("ko tim thay");
    }



}
