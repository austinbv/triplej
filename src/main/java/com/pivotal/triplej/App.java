package com.pivotal.triplej;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    App app = new App();
  }

  public App() {
    logger.info("Running.");
  }
}
