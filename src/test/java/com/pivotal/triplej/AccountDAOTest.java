package com.pivotal.triplej;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AccountDAOTest {
  @Test
  public void getAccounts() throws IOException {
    Injector injector = Guice.createInjector(new Environment("test"), new DataSourceModule());

    AccountDAO dao = injector.getInstance(AccountDAO.class);
    List<Account> accounts = dao.getAccounts();
    assertEquals(2, accounts.size());
    assertEquals("coke", accounts.get(0).getName());
    assertEquals("pepsi", accounts.get(1).getName());
  }
}
