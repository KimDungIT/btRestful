package com.example.btrestful1.controller;

import com.example.btrestful1.model.Account;
import com.example.btrestful1.model.Account1;
import com.example.btrestful1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    Logger logger = Logger.getLogger(this.getClass().getName());

    //insert account
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public Account insertAccount(@RequestBody Account account )
    {
        System.out.println("Account: "+ account.getPassword());
        accountService.saveAccount(account);
        return account;
    }

    @GetMapping("/api/private")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Object index(OAuth2Authentication auth){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("test", 123);
        map.put("auth", auth);
        return map;
    }


    @GetMapping("/api/public")
//    @PreAuthorize("hasScope('read')")
    public Object index2(Principal principal){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("test", 123);
        map.put("principal", principal);
        return map;
    }


    @GetMapping("/api/index")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object index3(OAuth2Authentication auth){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("test", 123);
        map.put("auth", auth);
        return map;
    }

    //update password

//    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PATCH)
//    public Account updatePassword(@PathVariable("id") int id, @RequestBody Map<String, Object> updates) throws Exception
//    {
//        return accountService.updateAccount(updates.get(updates), id);
//
//    }

    //update password if role is "ADMIN" or its username
   /* @PreAuthorize("#oauth2.hasScope('read')")
    public Account updatePassword(@PathVariable("id") int id, @RequestBody Account1 account1, OAuth2Authentication auth) throws Exception{

        boolean ch = false;

        Account account = accountService.getById(id);

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities)
        {
            if(grantedAuthority.getAuthority().equals("ADMIN"))
            {
                ch = true;
            }

        }
        System.out.println("ch: "+ch);

        if(account.getUsername().equals(auth.getName()) || ch==true)
        {
            logger.info("change password if role is ADMIN or its username");
            return accountService.updatePassword(account1.getPassword(), id);
        }

        return null;

    }*/

   //update account
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Account updateAccount(@PathVariable("id") int id, @RequestBody Account account, OAuth2Authentication auth) throws Exception{

        boolean ch = false;

        Account account1 = accountService.getById(id);

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities)
        {
            if(grantedAuthority.getAuthority().equals("ADMIN"))
            {
                ch = true;
            }

        }
        System.out.println("ch: "+ch);

        if(account1.getUsername().equals(auth.getName()) || ch==true)
        {
            logger.info("change password if role is ADMIN or its username");

            account1.setUsername(account.getUsername());
            account1.setPassword(account.getPassword());
            account1.setRole(account.getRole());
            accountService.saveAccount(account1);

            return account1;
        }

        return null;

    }

    //delete account
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Account deleteAccount(@PathVariable("id") int id)
    {
          return accountService.deleteAccountById(id);
    }


    //login

    @RequestMapping(value = "/logins", method = RequestMethod.GET)
    public Account login(String username, String password) throws Exception
    {
        return accountService.getAccountByUsernameAndPassword(username, password);

    }





}
