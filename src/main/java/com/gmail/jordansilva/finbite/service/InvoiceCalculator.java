package com.gmail.jordansilva.finbite.service;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

import com.gmail.jordansilva.finbite.model.Invoice;
import com.gmail.jordansilva.finbite.model.Package;
import com.gmail.jordansilva.finbite.model.PriceList;
import com.gmail.jordansilva.finbite.model.Usage;

import java.math.BigDecimal;

class InvoiceCalculator {

  public Invoice calculateInvoice(Usage usage, Package aPackage, PriceList priceList) {
    return Invoice.builder()
        .phonePackage(aPackage)
        .usage(usage)
        .callPrice(calculateExtraCallPrice(usage, aPackage, priceList))
        .smsPrice(calculateExtraSmsPrice(usage, aPackage, priceList))
        .build();
  }

  private static BigDecimal calculateExtraCallPrice(Usage usage, Package aPackage, PriceList priceList) {
    return hasExtraCalls(usage, aPackage) ? doCalculateExtraCallPrice(usage, aPackage, priceList) : ZERO;
  }

  private static boolean hasExtraCalls(Usage usage, Package aPackage) {
    return usage.getCallInMinutes() > aPackage.getIncludedCallMinutes();
  }

  private static BigDecimal doCalculateExtraCallPrice(Usage usage, Package aPackage, PriceList priceList) {
    BigDecimal extraMinutes = valueOf(usage.getCallInMinutes() - aPackage.getIncludedCallMinutes());
    return priceList.getCallMinute().multiply(extraMinutes);
  }

  private static BigDecimal calculateExtraSmsPrice(Usage usage, Package aPackage, PriceList priceList) {
    return hasExtraSms(usage, aPackage) ? doCalculateExtraSmsPrice(usage, aPackage, priceList) : ZERO;
  }

  private static boolean hasExtraSms(Usage usage, Package aPackage) {
    return usage.getSms() > aPackage.getIncludedSms();
  }

  private static BigDecimal doCalculateExtraSmsPrice(Usage usage, Package aPackage, PriceList priceList) {
    BigDecimal extraMinutes = valueOf(usage.getSms() - aPackage.getIncludedSms());
    return priceList.getSms().multiply(extraMinutes);
  }
}