package com.pivotal.triplej;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.google.inject.name.Names.named;

public class Environment extends AbstractModule {
  private static final Logger logger = LoggerFactory.getLogger(Environment.class);
  protected Properties properties;

  public Environment(String environment) throws IOException {
    properties = getProperties("/" + environment + ".properties");
  }

  @Override
  protected void configure() {
    bind(Key.get(String.class, named("a.value"))).toInstance(properties.getProperty("a.value"));
    bind(Key.get(String.class, named("mybatis.environment.id"))).toInstance(properties.getProperty("mybatis.environment.id"));

    if (System.getenv("DATABASE_URL") != null && System.getenv("DATABASE_USERNAME") != null && System.getenv("DATABASE_PASSWORD") != null) {
      bind(Key.get(String.class, named("JDBC.url"))).toInstance(System.getenv("DATABASE_URL"));
      bind(Key.get(String.class, named("JDBC.username"))).toInstance(System.getenv("DATABASE_USERNAME"));
      bind(Key.get(String.class, named("JDBC.password"))).toInstance(System.getenv("DATABASE_PASSWORD"));
    } else {
      bind(Key.get(String.class, named("JDBC.url"))).toInstance(properties.getProperty("jdbc.url"));
      bind(Key.get(String.class, named("JDBC.username"))).toInstance(properties.getProperty("jdbc.username"));
      bind(Key.get(String.class, named("JDBC.password"))).toInstance(properties.getProperty("jdbc.password"));
    }
  }

  private Properties getProperties(String file) throws IOException {
    logger.info("Loading properties for {}", file);
    Properties properties = new Properties();
    InputStream resourceAsStream = getClass().getResourceAsStream(file);
    if (resourceAsStream != null) {
      properties.load(resourceAsStream);
    }
    logger.info(properties.toString());
    return properties;
  }
}
