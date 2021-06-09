package bench2021;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;

import bench2021.dto.Page;
import bench2021.dto.Transaction;
import bench2021.exceptions.ClientException;

public class PageSequenceTest {

  private Request request;
  private PageSequence pageSequence;
  private Page mockedPage;
  private Transaction mockedTransaction;

  @Before
  public void setUp() {
    request = mock(Request.class);
    mockedPage = mock(Page.class);
    mockedTransaction = mock(Transaction.class);
    
    pageSequence = new PageSequence(request);

    when(mockedPage.getTransactions()).thenReturn(Lists.newArrayList(mockedTransaction));
    when(mockedPage.getTotalCount()).thenReturn(2);
  }

  @Test
  public void testNext() throws ClientException, IOException, InterruptedException {
    when(request.getTransactionPage(anyInt())).thenReturn(mockedPage);
    var result = pageSequence.next();
    assertEquals(result, mockedPage);
    assertTrue(pageSequence.hasPagesRemaining());
    assertEquals(2, pageSequence.getNextPage());
  }

}
