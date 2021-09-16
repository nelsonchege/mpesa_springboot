package com.example.mpesa_springboot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StkpushResponseAsync {



    @JsonProperty("Body")
    private Body body;


}
