package bench2021;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.google.common.base.Optional;

import bench2021.dto.Transaction;

/**
 * Maintains a sorted date indexed record of the running balances.
 */
public class RunningBalance {

  private TreeMap<String, BigDecimal> balances = new TreeMap<>();

  /**
   * Increments the balance for the given transaction.
   * @param transaction Transaction to add to the running balance.
   */
  public void incrementDailyBalance(Transaction transaction) {
    balances.computeIfAbsent(transaction.getDate(), date -> BigDecimal.ZERO);
    balances.compute(transaction.getDate(), (date, amount) -> amount.add(transaction.getAmount()));
  }

  /**
   * Get the balance for a given day.
   * Used for testing.
   * @param date
   * @return Optional BigDecimal containing the balance for the given day.
   */
  public Optional<BigDecimal> getBalanceForDate(String date) {
    return Optional.fromNullable(balances.get(date));
  }

  /**
   * Prints out the daily balances in the format
   * yyyy-MM-dd %.2f
   */
  public void printBalances() {
    balances.forEach((date, amount) -> System.out.println(String.format("%s %.2f", date, amount)));
  }
}
