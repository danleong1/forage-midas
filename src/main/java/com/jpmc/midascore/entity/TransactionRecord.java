package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue()
    private long id;

    @ManyToOne(optional = false)
    private UserRecord sender;

    @ManyToOne(optional = false)
    private UserRecord receiver;

    private float amount;

    public UserRecord getSender() {
        return sender;
    }

    public void setSender(UserRecord sender) {
        this.sender = sender;
    }

    public UserRecord getReceiver() {
        return receiver;
    }

    public void setReceiver(UserRecord receiver) {
        this.receiver = receiver;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

}
