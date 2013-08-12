package com.pivotal.triplej;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {
  private static final Logger logger = LoggerFactory.getLogger(App.class);
  private final Server server;

  public static void main(String[] args) throws Exception {
    App app = new App();
    app.start();
  }

  public App() {
    server = new Server(8080);

    ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
    context.addEventListener(new Listener());
    context.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
    context.addServlet(DefaultServlet.class, "/");

    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        try {
          if (server.isRunning()) {
            server.stop();
          }
          logger.info("App shutdown.");
        } catch (Exception e) {
          logger.info("Error shutting down app.", e);
        }
      }
    });
  }

  public void start() throws Exception {
    logger.info("App started.");
    server.start();
  }

  public void stop() throws Exception {
    logger.info("App stopped.");
    server.stop();
  }
}
