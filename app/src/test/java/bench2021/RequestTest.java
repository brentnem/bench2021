package bench2021;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {
  
  private Request request;

  @Before
  public void setUp() {
    request = new Request();
  }

  @Test
  public void testGetPage() throws IOException, InterruptedException {
    request.getTransactionPage(1);
  }
}
