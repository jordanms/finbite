package com.gmail.jordansilva.finbite.model;

import static lombok.AccessLevel.PROTECTED;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor(access = PROTECTED)
public abstract class Package {

  @NonNull
  private final int includedCallMinutes;

  @NonNull
  private final int includedSms;

  @NonNull
  private final BigDecimal price;

  abstract public String getName();
}