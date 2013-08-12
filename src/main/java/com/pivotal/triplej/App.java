package com.pivotal.triplej;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

public class App {
  private static final Logger logger = LoggerFactory.getLogger(App.class);
  private final Server server;

  public App(Environment environment) throws Exception {
    Injector injector = Guice.createInjector(environment, new DataSourceModule(), new ResourceModule());

    server = new Server(8080);
    ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
    context.addEventListener(new Listener(injector));
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

  public static void main(String[] args) throws Exception {
    App app = new App(findEnvironment(args));
    app.start();
  }

  private static Environment findEnvironment(String[] args) throws IOException {
    if (args == null || args.length == 0) {
      throw new RuntimeException("Missing environment arg.");
    }

    List<String> environments = Lists.newArrayList("development", "test", "production");
    if (!environments.contains(args[0])) {
      throw new RuntimeException("Unknown environmentArg.");
    }
    return new Environment(args[0]);
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