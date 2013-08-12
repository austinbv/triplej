package com.pivotal.triplej;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class EnvironmentTest {
  @Test
  public void readsPropertiesFromEnv() throws IOException {
    Injector injector = Guice.createInjector(new Environment("test"));
    A a = injector.getInstance(A.class);
    assertEquals("42", a.getValue());
  }
}

class A {
  private String value;

  @Inject
  A(@Named("a.value") String value) {
    this.value = value;
  }

  String getValue() {
    return value;
  }
}
