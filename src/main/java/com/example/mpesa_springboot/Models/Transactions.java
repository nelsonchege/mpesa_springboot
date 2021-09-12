package com.example.mpesa_springboot.Models;

import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name ;

    @Column(name="value")
    private int value ;

    public Transactions(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Transactions(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }


}
