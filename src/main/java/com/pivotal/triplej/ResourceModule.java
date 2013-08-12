package com.pivotal.triplej;

import com.google.inject.Scopes;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class ResourceModule extends JerseyServletModule {
  @Override
  protected void configureServlets() {
    bind(HealthCheckResource.class);
    bind(AccountResource.class);
    bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
    serve("/*").with(GuiceContainer.class);
  }
}
