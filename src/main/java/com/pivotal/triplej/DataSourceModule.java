package com.pivotal.triplej;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import static com.google.inject.name.Names.named;

public class DataSourceModule extends MyBatisModule {
  @Override
  protected void initialize() {
    bindConstant().annotatedWith(named("JDBC.driver")).to("com.mysql.jdbc.Driver");
    bindDataSourceProviderType(PooledDataSourceProvider.class);
    bindTransactionFactoryType(JdbcTransactionFactory.class);
    addMapperClass(AccountMapper.class);
    bind(AccountDAO.class);
  }
}
