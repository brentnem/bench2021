package bench2021.helpers;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import lombok.Data;

/**
 * Wrapper around HttpClient logic, largely for mock injections.
 */
@Data
public class ClientWrapper {

  //Timeout should be adjust based on SLA requirements.
  public static final int CONNECT_TIMEOUT_MS = 5000;

  HttpClient client;
  HttpResponse<String> response;

  public ClientWrapper() {
    client = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(CONNECT_TIMEOUT_MS)).build();
  }

  public void send(HttpRequest request) throws IOException, InterruptedException {
    response = client.send(request, BodyHandlers.ofString());
  }

  /**
   * Returns the body from the response.
   * @return
   */
  public String getBody() {
    //Should add some null checking here, response might not be set.
    return response.body();
  }

  public int getStatusCode() {
    //Should add some null checking here, response might not be set.
    return response.statusCode();
  }

}
