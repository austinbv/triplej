package com.pivotal.triplej;

import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
  private AccountDAO dao;

  @Inject
  public AccountResource(AccountDAO dao) {
    this.dao = dao;
  }

  @GET
  public List<Account> getAccounts() {
    return dao.getAccounts();
  }
}

