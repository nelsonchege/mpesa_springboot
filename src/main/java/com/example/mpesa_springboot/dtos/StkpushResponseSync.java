package com.example.mpesa_springboot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StkpushResponseSync {
    @JsonProperty("MerchantRequestID")
    private String merchantRequestID;

    @JsonProperty("ResponseCode")
    private String responseCode;

    @JsonProperty("CustomerMessage")
    private String customerMessage;

    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestID;

    @JsonProperty("ResponseDescription")
    private String responseDescription;
}
