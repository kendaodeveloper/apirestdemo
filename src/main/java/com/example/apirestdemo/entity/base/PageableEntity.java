package com.example.apirestdemo.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableEntity<T> {
  private Meta meta;
  private List<T> records;

  public PageableEntity(@NotNull List<T> records, Integer pageNumber, Integer pageSize) {
    this.records = records;
    this.meta = new Meta(pageNumber, pageSize, records.size(), records.size());
  }

  public PageableEntity<T> doFilter() {
    ArrayList<T> filteredRecords = new ArrayList<>();

    for (int i = 0; i < (this.records == null ? 0 : this.records.size()); i++) {
      if (
          i >= ((this.meta.pageNumber - 1) * this.meta.pageSize) &&
              i < (((this.meta.pageNumber - 1) * this.meta.pageSize) + this.meta.pageSize)
      ) {
        filteredRecords.add(this.records.get(i));
      }
    }

    this.records = filteredRecords;
    this.meta.recordSize = filteredRecords.size();

    return this;
  }

  @JsonIgnore
  public boolean isShowingAll() {
    return (this.meta.recordSize.equals(this.meta.total));
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Meta {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer recordSize;
    private Integer total;
  }
}
