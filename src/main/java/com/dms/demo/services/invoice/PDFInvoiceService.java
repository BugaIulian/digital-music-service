package com.dms.demo.services.invoice;

public interface PDFInvoiceService {

    String generateListenPlusInvoicePDF(String address, String firstName, String secondName, String invoiceNumber, double priceWithVAT);

    void generateListenMaxInvoicePDF();
}