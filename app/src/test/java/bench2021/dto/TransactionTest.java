package bench2021.dto;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

  Transaction transaction;

  @Before
  public void setUp() {
    transaction = new Transaction();
  }

  @Test
  public void testSetAmount() {
    transaction.setAmount("blah");
    assertEquals(BigDecimal.ZERO, transaction.getAmount());

    transaction.setAmount("1.2");
    assertEquals(new BigDecimal("1.2"), transaction.getAmount());

    transaction.setAmount("-2.1");
    assertEquals(new BigDecimal("-2.1"), transaction.getAmount());
  }
}
