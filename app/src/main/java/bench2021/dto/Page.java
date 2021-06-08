package bench2021.dto;

import java.util.List;

import lombok.Data;

@Data
public class Page<T> {
  private int totalCount;
  private int page;
  private List<T> transactions;
}
