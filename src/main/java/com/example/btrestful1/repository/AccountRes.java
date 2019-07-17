package com.example.btrestful1.repository;

import com.example.btrestful1.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRes extends JpaRepository<Account, Integer> {

    @Modifying
    @Transactional
    @Query("update Account as a set a.password = ?1 where a.id = ?2")
    void updatePassword(String password, int id);

    Account findAccountByUsernameAndPassword(String username, String passowrd);

}
