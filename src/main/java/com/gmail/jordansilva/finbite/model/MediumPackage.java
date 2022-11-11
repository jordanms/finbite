package com.gmail.jordansilva.finbite.model;

import static java.math.BigDecimal.valueOf;

public class MediumPackage extends Package {

  public MediumPackage() {
    super(50, 100, valueOf(10));
  }

  @Override
  public String getName() {
    return "M";
  }
}