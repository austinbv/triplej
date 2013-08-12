package com.pivotal.triplej;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment extends AbstractModule {
  private static final Logger logger = LoggerFactory.getLogger(Environment.class);
  private Properties properties;

  public Environment() throws IOException {
    this(System.getProperty("ENV", "development"));
  }

  public Environment(String environment) throws IOException {
    properties = getProperties("/" + environment + ".properties");
  }

  @Override
  protected void configure() {
    Names.bindProperties(binder(), properties);
  }

  private Properties getProperties(String file) throws IOException {
    logger.info("Loading properties for {}", file);
    Properties properties = new Properties();
    InputStream resourceAsStream = getClass().getResourceAsStream(file);
    if (resourceAsStream != null) {
      properties.load(resourceAsStream);
    }
    return properties;
  }
}
