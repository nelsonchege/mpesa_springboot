package com.example.mpesa_springboot.Controllers;

import com.example.mpesa_springboot.Repositories.TransactionRepository;
import com.example.mpesa_springboot.Services.DarajaApi;
import com.example.mpesa_springboot.dtos.AcknowledgeResponse;
import com.example.mpesa_springboot.dtos.StkpushResponseAsync;
import com.example.mpesa_springboot.dtos.StkpushResponseSync;
import com.example.mpesa_springboot.dtos.TokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import com.example.mpesa_springboot.Models.Transactions;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("action")
public class MpesaController {
    private final DarajaApi darajaApi;
    private final ObjectMapper objectMapper;
    private final AcknowledgeResponse acknowledgeResponse;
    private Log log;


    public MpesaController(DarajaApi darajaApi, ObjectMapper objectMapper, AcknowledgeResponse acknowledgeResponse) {
        this.darajaApi = darajaApi;
        this.objectMapper = objectMapper;
        this.acknowledgeResponse = acknowledgeResponse;
    }

    @GetMapping(path = "/token", produces = "application/json")
    public ResponseEntity<TokenResponse> getToken() {
        return ResponseEntity.ok(darajaApi.getToken());
    }

    @PostMapping(path = "/stk-request", produces = "application/json")
    public ResponseEntity<StkpushResponseSync> performStkPush() {
        return ResponseEntity.ok(darajaApi.performStkPush());
    }

    @SneakyThrows
    @PostMapping(path = "/stk-result", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> acknowledgeStkPushResponse(@RequestBody StkpushResponseAsync StkpushResponseAsync) {
        log.info("======= STK Push Async Response =====");
        log.info(objectMapper.writeValueAsString(StkpushResponseAsync));
        StkpushResponseAsync stkpushResponseAsync = new StkpushResponseAsync();
        Transactions transactions = new Transactions();

        transactions.setResultDesc(stkpushResponseAsync.getResultDesc());
        transactions.setResultCode(stkpushResponseAsync.getResultCode());
        transactions.setMerchantRequestID(stkpushResponseAsync.getMerchantRequestID());
        transactions.setName(stkpushResponseAsync.getName());
        transactions.setValue(stkpushResponseAsync.getValue());

        TransactionRepository.save(transactions);

        return ResponseEntity.ok(acknowledgeResponse);
    }


    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping(path = "/transaction")
    public Transactions CreateTransactions(@RequestBody Transactions transactions){
          return this.transactionRepository.save(transactions);
    }

}
