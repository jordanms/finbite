package com.gmail.jordansilva.finbite.service;

import com.gmail.jordansilva.finbite.model.Package;
import com.gmail.jordansilva.finbite.model.PriceList;
import com.gmail.jordansilva.finbite.model.Usage;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class InvoiceService {

  private final InvoiceCalculator invoiceCalculator;
  private final Printer printer;

  public BigDecimal calculateInvoiceTotal(Usage usage, Package aPackage, PriceList priceList) {
    var invoice = invoiceCalculator.calculateInvoice(usage, aPackage, priceList);
    printer.print(invoice);
    return invoice.getTotal();
  }
}