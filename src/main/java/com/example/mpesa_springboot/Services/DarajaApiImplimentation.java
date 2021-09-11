package com.example.mpesa_springboot.Services;

import com.example.mpesa_springboot.Config.MpesaConfig;
import com.example.mpesa_springboot.Constants.Constants;
import com.example.mpesa_springboot.dtos.StkpushRequest;
import com.example.mpesa_springboot.dtos.StkpushResponseSync;
import com.example.mpesa_springboot.dtos.TokenResponse;
import com.example.mpesa_springboot.utils.HelperUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import static com.example.mpesa_springboot.Constants.Constants.*;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class DarajaApiImplimentation implements DarajaApi{
    private final ObjectMapper objectMapper;

    public DarajaApiImplimentation(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public TokenResponse getToken(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .method("GET", null)
                .addHeader("Authorization", "Basic cFJZcjZ6anEwaThMMXp6d1FETUxwWkIzeVBDa2hNc2M6UmYyMkJmWm9nMHFRR2xWOQ==")
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return objectMapper.readValue(response.body().string(), TokenResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public StkpushResponseSync performStkPush() {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        String transactionTimestamp = HelperUtilities.getTransactionTimestamp();

        StkpushRequest stkpushRequest = new StkpushRequest();


        stkpushRequest.setPassword(Constants.STK_PASSWORD);
        stkpushRequest.setTimestamp(TIMESTAMP);
        stkpushRequest.setTransactionType(Constants.TRANSACTION_TYPE);
        stkpushRequest.setAmount(Constants.AMOUNT);
        stkpushRequest.setPartyA(Constants.CUSTOMER_PHONENUMBER);
        stkpushRequest.setPartyB(Constants.BUSINESS_SHORT_CODE);
        stkpushRequest.setPhoneNumber(Constants.CUSTOMER_PHONENUMBER);
        stkpushRequest.setCallBackURL(Constants.CALL_BACK_URL);
        stkpushRequest.setAccountReference(Constants.ACCOUNT_REFERENCE);
        stkpushRequest.setTransactionDesc(Constants.TRANSACTION_DEC);
        stkpushRequest.setBusinessShortCode(Constants.BUSINESS_SHORT_CODE);


        TokenResponse tokenResponse = getToken();
        RequestBody body = RequestBody.create(mediaType,
                Objects.requireNonNull(HelperUtilities.toJson(stkpushRequest)));

        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, tokenResponse.getAccessToken()))
                .build();

        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            // use Jackson to Decode the ResponseBody ...

            return objectMapper.readValue(response.body().string(), StkpushResponseSync.class);
        } catch (IOException e) {
            log.error(String.format("Could not send request -> %s", e.getLocalizedMessage()));
            return null;
        }

    }
}
