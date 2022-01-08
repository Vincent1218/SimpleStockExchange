package com.company;

import java.util.*;
import java.text.DateFormat;


public class Order {
    private int orderId;
    private String action;
    private String symbol;
    private String type;
    private double limitPrice;
    private double triggerPrice;
    private int amount;
    private int notFilled;
    private String status;

    public Order(String action, String symbol, String type, double limitPrice, int amount)
    {
        this.action = action;
        this.symbol = symbol;
        this.type = type;
        this.limitPrice = limitPrice;
        this.amount = amount;
        this.notFilled = amount;
        this.status = "PENDING";
        this.orderId = 0;
        this.triggerPrice = 0;

    }

    public Order(String action, String symbol, String type, int amount)
    {
        this.action = action;
        this.symbol = symbol;
        this.type = type;
        this.amount = amount;
        this.notFilled = amount;
        this.status = "PENDING";
        this.orderId = 0;
        this.limitPrice = -1;
        this.triggerPrice = 0;
    }

    public int getOrderId() { return orderId; }

    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getAction() { return action; }

    public void setAction(String action) { this.action = action; }

    public String getSymbol() { return symbol; }

    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type;}

    public double getLimitPrice() { return limitPrice; }

    public void setLimitPrice(double limitPrice) { this.limitPrice = limitPrice; }

    public double getTriggerPrice() { return triggerPrice; }

    public void setTriggerPrice(double triggerPrice) { this.triggerPrice = triggerPrice; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public int getNotFilled() { return notFilled; }

    public void setNotFilled(int filled) { this.notFilled = filled; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status;}

    public void printOrder(){
        if(this.triggerPrice == 0){
            System.out.println(this.orderId + ". " + this.symbol + " " + this.type + " " + this.action + " " +
                      (this.amount - this.notFilled) + "/" + this.amount + " " + this.status );
        }
        else{
            System.out.println(this.orderId + ". " + this.symbol + " " + this.type + " " + this.action + " $" +
                    this.triggerPrice + " " + (this.amount - this.notFilled) + "/" + this.amount + " " + this.status );
        }
    }



}