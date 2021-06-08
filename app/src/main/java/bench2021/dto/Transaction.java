package bench2021.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Transaction {
  private String Date;
  private String Ledger;
  private BigDecimal Amount;
  private String Company;

  public void setAmount(String amount) {
    try {
      this.Amount = new BigDecimal(amount);
    } catch (Exception e) {
      System.out.println(String.format("Failed to typecast value: %s", amount));
      this.Amount = new BigDecimal(0);
    }
  }
}
