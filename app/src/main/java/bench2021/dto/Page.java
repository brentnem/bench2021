package bench2021.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Page DTO for representing a subselection of transactions.
 */
@Data
public class Page {
  private int totalCount;
  private int page;
  // This could be generalized to take any set of paginated data, not just Transactions.
  private List<Transaction> transactions = new ArrayList<>();
}
