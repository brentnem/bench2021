package bench2021.dto;

import java.math.BigDecimal;
import java.util.HashMap;

import com.google.common.base.Optional;

public class RunningBalance {
    
  private HashMap<String, BigDecimal> balances = new HashMap<>();

  public void incrementDailyBalance(Transaction transaction) {
    balances.computeIfAbsent(transaction.getDate(), k -> BigDecimal.ZERO);
    balances.computeIfPresent(transaction.getDate(), (k, v) -> v.add(transaction.getAmount()));
  }

  public Optional<BigDecimal> getBalanceForDate(String date) {
    return Optional.fromNullable(balances.get(date));
  }

  public void printBalances() {
    balances.forEach((date, amount) -> System.out.println(String.format("%s %.2f", date, amount)));
  }
}
