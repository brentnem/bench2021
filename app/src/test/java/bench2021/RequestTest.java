package bench2021;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import bench2021.dto.Page;
import bench2021.exceptions.ClientException;
import bench2021.helpers.BodyParser;
import bench2021.helpers.ClientWrapper;



public class RequestTest {
  
  private Request request;
  private Page page;
  private ClientWrapper mockedClientWrapper;
  private BodyParser mockedBodyParser;

  @Before
  public void setUp() {
    mockedClientWrapper = mock(ClientWrapper.class);
    mockedBodyParser = mock(BodyParser.class);
    page = new Page();

    request = new Request(mockedClientWrapper, mockedBodyParser);
  }

  @Test
  public void testProcessResponseNegative() throws ClientException {
    assertThrows(ClientException.class, () -> request.processResponse(100, "{}"));
    assertThrows(ClientException.class, () -> request.processResponse(308, "{}"));
    assertThrows(ClientException.class, () -> request.processResponse(400, "{}"));
    assertThrows(ClientException.class, () -> request.processResponse(500, "{}"));
  }

  @Test
  public void testProcessResponsePositive() {
    try {
      when(mockedBodyParser.parseJson(anyString())).thenReturn(page);
      assertEquals(page, request.processResponse(200, "{}"));
    } catch (ClientException e) {
      fail("Unexpected client exception: " + e.getMessage());
    }
  }
}
