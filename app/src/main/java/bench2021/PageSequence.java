package bench2021;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import bench2021.dto.Page;
import bench2021.exceptions.ClientException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

/**
 * Manages traversal of paginated data.
 */
@Data
public class PageSequence {

  private Request request;

  private int transactionsReceived = 0;
  private int nextPage = 1;

  @Getter(AccessLevel.NONE)
  private boolean hasPagesRemaining = false;

  public PageSequence(Request request) {
    this.request = request;
  }

  /**
   * Attempts to fetch the next page in the sequence.
   * Updates record of received pages and whether any more pages remain.
   * 
   * @return Page of transaction records.
   * @throws ClientException Thrown when receiving an unexpected response from the server.
   * @throws IOException Thrown by HttpClient when encountering unexepected connection issues.
   * @throws InterruptedException HttpClient forced to exit. 
   * @throws JsonSyntaxException Thrown when malformed JSON received from server.
   */
  public Page next() throws ClientException, IOException, InterruptedException, JsonSyntaxException {
    Page page = request.getTransactionPage(this.getNextPage());
    receivePage(page);
    return page;
  }

  /**
   * Updates the current state of the sequence using the given Page.
   * @param page
   */
  private void receivePage(Page page) {
    this.transactionsReceived += page.getTransactions().size();
    this.hasPagesRemaining = (page.getTotalCount() > this.transactionsReceived);
    this.nextPage++;
  }

  public boolean hasPagesRemaining() {
    return this.hasPagesRemaining;
  }
}
