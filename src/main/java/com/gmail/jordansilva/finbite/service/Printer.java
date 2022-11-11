package com.gmail.jordansilva.finbite.service;

import com.gmail.jordansilva.finbite.model.Invoice;
import com.gmail.jordansilva.finbite.model.Package;

/**
 * Prints invoice to Stdout.
 */
class Printer {

  public void print(Invoice invoice) {
    System.out.print(format(invoice));
  }

  private String format(Invoice invoice) {
    return new StringBuilder()
        .append(formatPackage(invoice))
        .append("\n")
        .append(formatCalls(invoice))
        .append("\n")
        .append(formatSms(invoice))
        .append("\n")
        .append(formatTotal(invoice))
        .toString();
  }

  private String formatPackage(Invoice invoice) {
    Package phonePackage = invoice.getPhonePackage();
    return String.format("Package\t%s\t\t%.2f EUR", phonePackage.getName(), phonePackage.getPrice());
  }

  private String formatCalls(Invoice invoice) {
    int min = invoice.getUsage().getCallInMinutes();
    int hours = min / 60;
    int minutes = min % 60;
    return String.format("Calls\t%02d:%02d\t%.2f EUR", hours, minutes, invoice.getCallPrice());
  }

  private String formatSms(Invoice invoice) {
    return String.format("SMS\t\t%d\t%.2f EUR", invoice.getUsage().getSms(), invoice.getSmsPrice());
  }

  private String formatTotal(Invoice invoice) {
    return String.format("Total\t\t\t%.2f EUR", invoice.getTotal());
  }
}