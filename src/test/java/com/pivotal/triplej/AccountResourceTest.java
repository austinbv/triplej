package com.pivotal.triplej;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AccountResourceTest {
  private static App app;

  @BeforeClass
  public static void setUp() throws Exception {
    app = new App();
    app.start();
  }

  @AfterClass
  public static void tearDown() throws Exception {
    app.stop();
  }

  @Test
  public void listAccounts() throws Exception {
    HttpClient httpclient = new DefaultHttpClient();
    try {
      HttpGet httpget = new HttpGet("http://localhost:8080/accounts");
      ResponseHandler<String> responseHandler = new BasicResponseHandler();
      String responseBody = httpclient.execute(httpget, responseHandler);
      assertEquals("[{\"id\":2,\"name\":\"coke\"},{\"id\":1,\"name\":\"pepsi\"}]", responseBody);
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
  }
}
