package com.example.mpesa_springboot.Services;

import com.example.mpesa_springboot.dtos.StkpushResponseSync;
import com.example.mpesa_springboot.dtos.TokenResponse;

public interface DarajaApi {

    TokenResponse getToken();
    StkpushResponseSync performStkPush();
}
