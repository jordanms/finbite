package com.gmail.jordansilva.finbite.model;

import static java.math.BigDecimal.valueOf;

public class LargePackage extends Package {

  public LargePackage() {
    super(500, 500, valueOf(20));
  }

  @Override
  public String getName() {
    return "L";
  }
}