package com.pivotal.triplej;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HealthCheckResourceTest extends AppRunner {
  @Test
  public void basicHealthCheck() throws Exception {
    HttpClient httpclient = new DefaultHttpClient();
    try {
      HttpGet httpget = new HttpGet("http://localhost:8080/health");
      ResponseHandler<String> responseHandler = new BasicResponseHandler();
      String responseBody = httpclient.execute(httpget, responseHandler);
      assertTrue(responseBody.indexOf("Service is operating normally") > 0);
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
  }
}
