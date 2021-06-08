package bench2021.dto;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;

public class RunningBalanceTest {

  private RunningBalance runningBalance;
  private Transaction transaction1;
  private Transaction transaction2;
  private Transaction transaction3;

  private String day1 = "2021-06-07";
  private String day2 = "2021-06-08";

  ArrayList<Transaction> transactions;

  @Before
  public void setUp() {
    runningBalance = new RunningBalance();

    transaction1 = mock(Transaction.class);
    when(transaction1.getDate()).thenReturn(day1);
    when(transaction1.getAmount()).thenReturn(new BigDecimal(1.0));

    transaction2 = mock(Transaction.class);
    when(transaction2.getDate()).thenReturn(day1);
    when(transaction2.getAmount()).thenReturn(new BigDecimal(-5.5));

    transaction3 = mock(Transaction.class);
    when(transaction3.getDate()).thenReturn(day2);
    when(transaction3.getAmount()).thenReturn(new BigDecimal(11.11));

    transactions = Lists.newArrayList(
      transaction1,
      transaction2,
      transaction3
    );

  }

  @Test
  public void testIncrementDailyBalance() {
    transactions.forEach(
      (transaction) -> runningBalance.incrementDailyBalance(transaction)
    );

    assertEquals(
      Lists.newArrayList(transaction1, transaction2).stream()
        .map(transaction -> transaction.getAmount())
        .reduce(BigDecimal.ZERO, BigDecimal::add), 
      runningBalance.getBalanceForDate(day1).get()
    );

    runningBalance.printBalances();
  }
}
