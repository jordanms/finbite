package com.gmail.jordansilva.finbite.model;

import lombok.Builder;
import lombok.EqualsAndHashCode.Exclude;
import lombok.EqualsAndHashCode.Include;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class Invoice {

  @NonNull
  Package phonePackage;

  @NonNull
  Usage usage;

  @Exclude
  @NonNull
  BigDecimal callPrice;

  @Exclude
  @NonNull
  BigDecimal smsPrice;

  public BigDecimal getTotal() {
    return phonePackage.getPrice().add(callPrice).add(smsPrice);
  }

  @Include
  private BigDecimal getCallPriceForEquals() {
    return normalize(callPrice);
  }

  @Include
  private BigDecimal getSmsPriceForEquals() {
    return normalize(callPrice);
  }

  private BigDecimal normalize(BigDecimal callPrice) {
    return callPrice.stripTrailingZeros();
  }
}