package bench2021;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;

import bench2021.dto.Transaction;

public class RunningBalanceTest {

  private RunningBalance runningBalance;

  private String day1 = "2021-06-07";
  private String day2 = "2021-06-08";

  private BigDecimal amount1 = new BigDecimal(1.0);
  private BigDecimal amount2 = new BigDecimal(-3.4);
  private BigDecimal amount3 = new BigDecimal(101.8);
  private BigDecimal amount4 = new BigDecimal(-500);

  ArrayList<Transaction> transactions;

  @Before
  public void setUp() {
    runningBalance = new RunningBalance();

    transactions = Lists.newArrayList(
      createMockTransaction(day1, amount1),
      createMockTransaction(day1, amount2),
      createMockTransaction(day2, amount3),
      createMockTransaction(day2, amount4)
    );
  }

  public Transaction createMockTransaction(String date, BigDecimal amount) {
    var transaction = mock(Transaction.class);
    when(transaction.getDate()).thenReturn(date);
    when(transaction.getAmount()).thenReturn(amount);
    return transaction;
  }

  @Test
  public void testIncrementDailyBalance() {
    transactions.forEach(
      (transaction) -> runningBalance.incrementDailyBalance(transaction)
    );

    assertEquals(
      Lists.newArrayList(transactions.get(0), transactions.get(1)).stream()
        .map(transaction -> transaction.getAmount())
        .reduce(BigDecimal.ZERO, BigDecimal::add), 
      runningBalance.getBalanceForDate(day1).get()
    );

    assertEquals(
      Lists.newArrayList(transactions.get(2), transactions.get(3)).stream()
        .map(transaction -> transaction.getAmount())
        .reduce(BigDecimal.ZERO, BigDecimal::add), 
      runningBalance.getBalanceForDate(day2).get()
    );
  }

  
}
