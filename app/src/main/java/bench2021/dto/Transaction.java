package bench2021.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * DTO reprenting Transaction payload.
 */
@Data
public class Transaction {

  //Real system should perform validation of all of the fields.
  private String Date;
  private String Ledger;
  private BigDecimal Amount;
  private String Company;

  /**
   * Attempts to convert the string amount to BigDecimal.
   * Sets amount to BigDecimal.ZERO on failure and logs a message.
   * 
   * @param amount
   */
  public void setAmount(String amount) {
    try {
      this.Amount = new BigDecimal(amount);
    } catch (NumberFormatException e) {
      //Log this somewhere appropriate like splunk/datadog.
      //Could be considered a serious enough failure to throw an exception.
      System.out.println(String.format("Failed to typecast value: %s", amount));
      this.Amount = BigDecimal.ZERO;
    }
  }
}
