package com.pivotal.triplej;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class Listener extends GuiceServletContextListener {
  private Injector injector;

  public Listener(Injector injector) {
    this.injector = injector;
  }

  @Override
  protected Injector getInjector() {
    return injector;
  }
}