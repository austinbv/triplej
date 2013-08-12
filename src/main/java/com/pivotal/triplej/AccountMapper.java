package com.pivotal.triplej;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountMapper {

  @Select("select id, name from accounts order by name")
  List<Account> getAccounts();
}
