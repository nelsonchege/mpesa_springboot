package com.example.mpesa_springboot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StkCallback{

    @JsonProperty("MerchantRequestID")
    private String merchantRequestID;

    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestID;

    @JsonProperty("ResultDesc")
    private String resultDesc;
    public String getResultDesc() {
        return resultDesc;
    };
    @JsonProperty("ResultCode")
    private int resultCode;

    @JsonProperty("CallbackMetadata")
    private CallbackMetadata callbackMetadata;
}
