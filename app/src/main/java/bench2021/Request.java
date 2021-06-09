package bench2021;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

import com.google.gson.JsonSyntaxException;

import bench2021.dto.Page;
import bench2021.exceptions.ClientException;
import bench2021.helpers.BodyParser;
import bench2021.helpers.ClientWrapper;

/**
 * Request is used to send the HTTP request to the transactions endpoint.
 */
public class Request {

  //Shouldn't be hardcoded in a real system.
  public static final String URL_PATTERN = "https://resttest.bench.co/transactions/%s.json";
  public static final String REQUEST_METHOD_GET = "GET";

  //Timeout should be adjusted based on SLA requirements.
  public static final int READ_TIMEOUT_MS = 5000;

  private ClientWrapper client;
  private BodyParser bodyParser;

  public Request(ClientWrapper client, BodyParser bodyParser) {
    this.client = client;
    this.bodyParser = bodyParser;
  }

  /**
   * Retrieves a page of transactions from the transactions endpoint.
   * 
   * @param pageNumber
   * @return Page of transactions.
   * @throws ClientException Thrown when receiving an unexpected response from the server.
   * @throws IOException Thrown by HttpClient when encountering unexepected connection issues.
   * @throws InterruptedException HttpClient forced to exit. 
   * @throws JsonSyntaxException Thrown when malformed JSON received from server.
   */
  public Page getTransactionPage(int pageNumber)
      throws ClientException, IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(String.format(URL_PATTERN, pageNumber)))
        .timeout(Duration.ofMillis(READ_TIMEOUT_MS)).build();

    client.send(request);

    return processResponse(client.getStatusCode(), client.getBody());
  }

  /**
   * Parses the status code and response body.
   * 
   * @param statusCode The HTTP status code from the request.
   * @param body The body of the get response.
   * @return Page of transactions.
   * @throws ClientException Thrown when receiving an unexpected response from the server.
   * @throws JsonSyntaxException Thrown when malformed JSON received from server.
   */
  public Page processResponse(int statusCode, String body) throws ClientException, JsonSyntaxException {
    //Should do more granular status code handling.
    switch (statusCode / 100) {
      case (1):
        throw new ClientException("Not expecting information response here.");
      case (2):
        return bodyParser.parseJson(body);
      case (3):
        throw new ClientException("Not expecting redirects here.");
      case (4):
        throw new ClientException("Client error.");
      case (5):
        throw new ClientException("Server error.");
      default:
        throw new ClientException("Unexpected response from server.");
    }
  }

}
