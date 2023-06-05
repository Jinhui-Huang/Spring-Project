package com.itstudy.service;

import com.itstudy.domain.Account;

import java.util.List;

public interface AccountService {
    void insert(Account account);
    void delete(Integer id);
    Account findById(Integer id);
    List<Account> findAll();
}
