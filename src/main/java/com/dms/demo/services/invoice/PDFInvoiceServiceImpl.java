package com.dms.demo.services.invoice;

import com.dms.demo.util.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class PDFInvoiceServiceImpl implements PDFInvoiceService {

    @Value("${pdfmonkey.api.key}")
    private String pdfMonkeyAPI;

    @Override
    public String generateListenPlusInvoicePDF(String address, String firstName, String secondName, String invoiceNumber, double priceWithVAT) {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + pdfMonkeyAPI);

        String body = "{" +
                "\"document\": {" +
                "\"document_template_id\": \"53c6cad1-f607-4ae8-b355-d277a8387c00\"," +
                "\"payload\": {" +
                "\"invoice_number\": \"" + invoiceNumber + "\"," +
                "\"invoice_date\": \"" + LocalDate.now().toString() + "\"," +
                "\"client_code\": \"ClientCode123\"," +
                "\"due_date\": \"" + LocalDate.now().plusDays(30).toString() + "\"," +
                "\"payment_mode\": \"Credit Card\"," +
                "\"client_name\": \"" + firstName + " " + secondName + "\"," +
                "\"billing_address\": \"" + address + "\"," +
                "\"lineItems\": [" +
                "{" +
                "\"code\": \"Item1\"," +
                "\"description\": \"SoundSync Listen Plus Subscription\"," +
                "\"quantity\": \"1\"," +
                "\"price\": \"100\"" +
                "}," +
                "{" +
                "\"code\": \"Item2\"," +
                "\"description\": \"Description for item 2\"," +
                "\"quantity\": \"1\"," +
                "\"price\": \"200\"" +
                "}" +
                "]," +
                "\"total_without_vat\": \"800\"," +
                "\"deposit\": \"0\"," +
                "\"total_with_vat\": \"" + priceWithVAT + "\"" +
                "}" +
                "}" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(Constants.PDF_MONKEY_DOCUMENT_URL, request, String.class);
        return response.getBody();
    }

    @Override
    public void generateListenMaxInvoicePDF() {

    }
}