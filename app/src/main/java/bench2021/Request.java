package bench2021;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import bench2021.dto.Page;
import bench2021.dto.Transaction;

public class Request {

  public static final String URL_PATTERN = "https://resttest.bench.co/transactions/%s.json";
  public static final String REQUEST_METHOD_GET = "GET";

  public static final int CONNECT_TIMEOUT_MS = 5000;
  public static final int READ_TIMEOUT_MS = 5000;

  private GsonBuilder gson = new GsonBuilder();
  private Type pageType;

  public Request() {
    pageType = new TypeToken<Page<Transaction>>(){}.getType();
  }

  public Page<Transaction> getTransactionPage(int pageNumber) throws IOException, InterruptedException {
  
    HttpClient client = HttpClient.newBuilder()
      .connectTimeout(Duration.ofMillis(CONNECT_TIMEOUT_MS))
      .build();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(String.format(URL_PATTERN, pageNumber)))
      .timeout(Duration.ofMillis(READ_TIMEOUT_MS))
      .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    Page<Transaction> result = gson.create().fromJson(response.body(), pageType);
    return result;
  }

}
