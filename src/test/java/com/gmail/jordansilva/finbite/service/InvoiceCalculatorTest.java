package com.gmail.jordansilva.finbite.service;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import com.gmail.jordansilva.finbite.model.Invoice;
import com.gmail.jordansilva.finbite.model.Package;
import com.gmail.jordansilva.finbite.model.PriceList;
import com.gmail.jordansilva.finbite.model.SmallPackage;
import com.gmail.jordansilva.finbite.model.Usage;
import org.junit.jupiter.api.Test;

public class InvoiceCalculatorTest {

  private final InvoiceCalculator calculator = new InvoiceCalculator();

  private final Package small = new SmallPackage();

  private final PriceList priceList = buildPriceList();

  private Usage usage;

  @Test
  void totalPriceShouldBeEqualToPackagePriceWhenCallAndSmsUsageInsidePackageLimit() {
    usage = callAndSmsInsidePackageLimit();
    assertThat(calculator.calculateInvoice(usage, small, priceList)).isEqualTo(noExtraUsageInvoice());
  }

  @Test
  void totalPriceShouldBeEqualToPackagePricePlusExtraConsumptionWhenCallIsAbovePackageLimit() {
    usage = callAbovePackageLimit();
    assertThat(calculator.calculateInvoice(usage, small, priceList)).isEqualTo(callAboveLimitInvoice());
  }

  @Test
  void totalPriceShouldBeEqualToPlusExtraSmsWhenSmsIsAbovePackageLimit() {
    usage = smsAbovePackageLimit();
    assertThat(calculator.calculateInvoice(usage, small, priceList)).isEqualTo(smsAboveLimitInvoice());
  }

  private Usage smsAbovePackageLimit() {
    return Usage.builder()
        .callInMinutes(1)
        .sms(53)
        .build();
  }

  private Invoice smsAboveLimitInvoice() {
    return buildInvoice(0, 0.9);
  }

  private Usage callAbovePackageLimit() {
    return Usage.builder()
        .callInMinutes(21)
        .sms(49)
        .build();
  }

  private Invoice callAboveLimitInvoice() {
    return buildInvoice(2.2, 0);
  }

  private Usage callAndSmsInsidePackageLimit() {
    return Usage.builder()
        .callInMinutes(10)
        .sms(49)
        .build();
  }

  private Invoice noExtraUsageInvoice() {
    return buildInvoice(0, 0);
  }

  private Invoice buildInvoice(double callPrice, double smsPrice) {
    return Invoice.builder()
        .phonePackage(small)
        .usage(usage)
        .callPrice(valueOf(callPrice))
        .smsPrice(valueOf(smsPrice))
        .build();
  }

  private static PriceList buildPriceList() {
    return PriceList.builder()
        .callMinute(valueOf(0.2))
        .sms(valueOf(0.3))
        .build();
  }
}