package com.gmail.jordansilva.finbite.service;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

import com.gmail.jordansilva.finbite.model.Invoice;
import com.gmail.jordansilva.finbite.model.MediumPackage;
import com.gmail.jordansilva.finbite.model.Usage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterTest {

  private final ByteArrayOutputStream invoiceOut = new ByteArrayOutputStream();

  private final PrintStream originalOut = System.out;

  private final Printer printer = new Printer();

  private final Invoice invoice = buildInvoice();

  @BeforeEach
  void setUpStreams() {
    System.setOut(new PrintStream(invoiceOut));
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  void shouldPrintInvoice() {
    printer.print(invoice);
    assertThat(invoiceOut.toString()).isEqualTo("""
        Package\tM\t\t10.00 EUR
        Calls\t00:39\t0.00 EUR
        SMS\t\t102\t0.60 EUR
        Total\t\t\t10.60 EUR""");
  }

  private Invoice buildInvoice() {
    return Invoice.builder()
        .phonePackage(new MediumPackage())
        .usage(Usage.builder()
            .callInMinutes(39)
            .sms(102)
            .build())
        .callPrice(ZERO)
        .smsPrice(valueOf(0.6))
        .build();
  }
}