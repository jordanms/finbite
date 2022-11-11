package com.gmail.jordansilva.finbite.model;

import static java.math.BigDecimal.valueOf;

public class SmallPackage extends Package {

  public SmallPackage() {
    super(10, 50, valueOf(5));
  }

  @Override
  public String getName() {
    return "S";
  }
}