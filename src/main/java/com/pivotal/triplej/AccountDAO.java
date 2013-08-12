package com.pivotal.triplej;

import com.google.inject.Inject;

import java.util.List;

public class AccountDAO {
  private AccountMapper mapper;

  @Inject
  public AccountDAO(AccountMapper mapper) {
    this.mapper = mapper;
  }

  public List<Account> getAccounts() {
    return mapper.getAccounts();
  }
}
