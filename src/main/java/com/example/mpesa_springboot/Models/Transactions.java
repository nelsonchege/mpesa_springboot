package com.example.mpesa_springboot.Models;



import javax.persistence.*;


@Entity
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String internalId;


    @Column(name="resultDesc" ,unique = true)
    private String resultDesc;

    @Column(name="resultCode" ,unique = true)
    private int resultCode;

    @Column(name="name" ,unique = true)
    private String name;

    @Column(name="value" ,unique = true)
    private String value;

    public Transactions(String internalId,
                        String resultDesc,
                        int resultCode,
                        String name,
                        String value,
                        String merchantRequestID) {
        this.internalId = internalId;
        this.resultDesc = resultDesc;
        this.resultCode = resultCode;
        this.name = name;
        this.value = value;
        this.merchantRequestID = merchantRequestID;
    }

    public String getInternalId() {
        return internalId;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMerchantRequestID() {
        return merchantRequestID;
    }

    public void setMerchantRequestID(String merchantRequestID) {
        this.merchantRequestID = merchantRequestID;
    }

    @Column(name="merchantRequestID" ,unique = true)
    private String merchantRequestID;

    public Transactions() {
    }
}
