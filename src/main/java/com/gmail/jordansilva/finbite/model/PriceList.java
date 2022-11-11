package com.gmail.jordansilva.finbite.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PriceList {

  @NonNull
  BigDecimal callMinute;

  @NonNull
  BigDecimal sms;
}