package com.pivotal.triplej;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import java.io.IOException;

public class Listener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    try {
      return Guice.createInjector(new Environment(), new DataSourceModule(), new ResourceModule());
    } catch (IOException e) {
      return null;
    }
  }
}