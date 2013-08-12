package com.pivotal.triplej;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class AppRunner {
  protected static App app;

  @BeforeClass
  public static void setUp() throws Exception {
    app = new App(new Environment("test"));
    app.start();
  }

  @AfterClass
  public static void tearDown() throws Exception {
    app.stop();
  }
}
