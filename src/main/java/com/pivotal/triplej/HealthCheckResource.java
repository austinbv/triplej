package com.pivotal.triplej;

import com.google.common.collect.Lists;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthCheckResource {
  @GET
  public List<HealthCheck> getHealth() {
    List<HealthCheck> checks = Lists.newArrayList();
    checks.add(new BasicHealthCheck());
    return checks;
  }
}

