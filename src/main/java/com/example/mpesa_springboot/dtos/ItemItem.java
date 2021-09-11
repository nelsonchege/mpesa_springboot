package com.example.mpesa_springboot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemItem{

    @JsonProperty("Value")
    private String value;

    @JsonProperty("Name")
    private String name;
}
