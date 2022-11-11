package com.gmail.jordansilva.finbite.service;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import com.gmail.jordansilva.finbite.model.LargePackage;
import com.gmail.jordansilva.finbite.model.Package;
import com.gmail.jordansilva.finbite.model.PriceList;
import com.gmail.jordansilva.finbite.model.Usage;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class InvoiceServiceTest {

  private final ByteArrayOutputStream invoiceOut = new ByteArrayOutputStream();

  private final PrintStream originalOut = System.out;

  private final Package large = new LargePackage();

  private final PriceList priceList = buildPriceList();

  private final Usage usage = buildUsage();

  private final InvoiceService service = new InvoiceService(new InvoiceCalculator(), new Printer());

  @Test
  void shouldReturnInvoiceTotal() {
    assertThat(service.calculateInvoiceTotal(usage, large, priceList)).isEqualByComparingTo(valueOf(100));
  }

  @Test
  void shouldPrintInvoice() {
    setupOutput();

    service.calculateInvoiceTotal(usage, large, priceList);
    assertThat(invoiceOut.toString()).isEqualTo("""
        Package\tL\t\t20.00 EUR
        Calls\t16:40\t5.00 EUR
        SMS\t\t2000\t75.00 EUR
        Total\t\t\t100.00 EUR""");

    restoreOutput();
  }

  private void setupOutput() {
    System.setOut(new PrintStream(invoiceOut));
  }

  private void restoreOutput() {
    System.setOut(originalOut);
  }

  private static PriceList buildPriceList() {
    return PriceList.builder()
        .callMinute(valueOf(0.01))
        .sms(valueOf(0.05))
        .build();
  }

  private static Usage buildUsage() {
    return Usage.builder()
        .callInMinutes(1000)
        .sms(2000)
        .build();
  }
}