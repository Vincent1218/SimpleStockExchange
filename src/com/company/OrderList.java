package com.company;

import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orderArray;

    public OrderList() {
        this.orderArray = new ArrayList<Order>();
    }

    // LIMIT BUY ORDER
    public void processLmtBuyOrders(Order order){
        for(int i=0 ; i < orderArray.size(); i++){
            Order tOrder = orderArray.get(i);
            //Check current order status
            if(order.getStatus().equals("FILLED")){
               break;
            }
            //Check LMT, Check Symbol, Check Action, Check Price & resolve order, Check Status
            if(tOrder.getType().equals("MKT") && tOrder.getSymbol().equals(order.getSymbol()) && tOrder.getAction().equals("SELL")  && (!tOrder.getStatus().equals("FILLED"))) {
                // Check Amount
                if(tOrder.getNotFilled() == order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * order.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * order.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() < order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * order.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + ((order.getNotFilled() - tOrder.getNotFilled()) * order.getLimitPrice()))/(order.getAmount() - tOrder.getNotFilled()));
                    order.setNotFilled(order.getNotFilled() - tOrder.getNotFilled());
                    order.setStatus("PARTIAL");
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() > order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + ((tOrder.getNotFilled() - order.getNotFilled()) * order.getLimitPrice()))/(tOrder.getAmount() - order.getNotFilled()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * order.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(tOrder.getNotFilled() - order.getNotFilled());
                    tOrder.setStatus("PARTIAL");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
            }
            else if (tOrder.getType().equals("LMT") && tOrder.getSymbol().equals(order.getSymbol()) && tOrder.getAction().equals("SELL") && (tOrder.getLimitPrice()<=order.getLimitPrice()) && (!tOrder.getStatus().equals("FILLED"))){
                // Check Amount
                if(tOrder.getNotFilled() == order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * tOrder.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * tOrder.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() < order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * tOrder.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + ((order.getNotFilled() - tOrder.getNotFilled()) * tOrder.getLimitPrice()))/(order.getAmount() - tOrder.getNotFilled()));
                    order.setNotFilled(order.getNotFilled() - tOrder.getNotFilled());
                    order.setStatus("PARTIAL");
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() > order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + ((tOrder.getNotFilled() - order.getNotFilled()) * tOrder.getLimitPrice()))/(tOrder.getAmount() - order.getNotFilled()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * tOrder.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(tOrder.getNotFilled() - order.getNotFilled());
                    tOrder.setStatus("PARTIAL");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
            }
        }
        System.out.println("You have placed a limit buy order for " + order.getAmount() + " " + order.getSymbol() + " shares at $" + order.getLimitPrice()+ " each.");
        orderArray.add(order);
        orderArray.get(orderArray.size()-1).setOrderId(orderArray.size());
    }
    // LIMIT SELL ORDER
    public void processLmtSellOrders(Order order){
        for(int i=0 ; i < orderArray.size(); i++){
            Order tOrder = orderArray.get(i);
            //Check current order status
            if(order.getStatus().equals("FILLED")){
                break;
            }
            //Check LMT, Check Symbol, Check Action, Check Price & resolve order, Check Status
            if(tOrder.getType().equals("MKT") && tOrder.getSymbol().equals(order.getSymbol()) && tOrder.getAction().equals("BUY")  && (!tOrder.getStatus().equals("FILLED"))) {
                // Check Amount
                if(tOrder.getNotFilled() == order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * order.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * order.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() < order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * order.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + ((order.getNotFilled() - tOrder.getNotFilled()) * order.getLimitPrice()))/(order.getAmount() - tOrder.getNotFilled()));
                    order.setNotFilled(order.getNotFilled() - tOrder.getNotFilled());
                    order.setStatus("PARTIAL");
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() > order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + ((tOrder.getNotFilled() - order.getNotFilled())*order.getLimitPrice()))/(tOrder.getAmount() - order.getNotFilled()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled()*order.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(tOrder.getNotFilled() - order.getNotFilled());
                    tOrder.setStatus("PARTIAL");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
            }
            else if(tOrder.getType().equals("LMT") && tOrder.getSymbol().equals(order.getSymbol()) && tOrder.getAction().equals("BUY") && (tOrder.getLimitPrice()>=order.getLimitPrice()) && (!tOrder.getStatus().equals("FILLED"))){
                if(tOrder.getNotFilled() == order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * tOrder.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * tOrder.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() < order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * tOrder.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + ((order.getNotFilled() - tOrder.getNotFilled()) * tOrder.getLimitPrice()))/(order.getAmount() - tOrder.getNotFilled()));
                    order.setNotFilled(order.getNotFilled() - tOrder.getNotFilled());
                    order.setStatus("PARTIAL");
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() > order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + ((tOrder.getNotFilled() - order.getNotFilled()) * tOrder.getLimitPrice()))/(tOrder.getAmount() - order.getNotFilled()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * tOrder.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(tOrder.getNotFilled() - order.getNotFilled());
                    tOrder.setStatus("PARTIAL");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
            }
        }
        System.out.println("You have placed a limit sell order for " + order.getAmount() + " " + order.getSymbol() + " shares at $" + order.getLimitPrice()+ " each.");
        orderArray.add(order);
        orderArray.get(orderArray.size()-1).setOrderId(orderArray.size());
    }
    // MARKET BUY ORDER
    public void processMktBuyOrders(Order order){
        for(int i=0 ; i < orderArray.size(); i++){
            Order tOrder = orderArray.get(i);
            //Check current order status
            if(order.getStatus().equals("FILLED")){
                break;
            }
            //Check LMT, Check Symbol, Check Action, Check Price & resolve order, Check Status
            if(tOrder.getType().equals("LMT") && tOrder.getSymbol().equals(order.getSymbol()) && tOrder.getAction().equals("SELL")  && (!tOrder.getStatus().equals("FILLED"))) {
                // Check Amount
                if(tOrder.getNotFilled() == order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * tOrder.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * tOrder.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() < order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * tOrder.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + ((order.getNotFilled() - tOrder.getNotFilled()) * tOrder.getLimitPrice()))/(order.getAmount() - tOrder.getNotFilled()));
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                    order.setNotFilled(order.getNotFilled() - tOrder.getNotFilled());
                    order.setStatus("PARTIAL");
                }
                else if (tOrder.getNotFilled() > order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + ((tOrder.getNotFilled() - order.getNotFilled()) * tOrder.getLimitPrice()))/(tOrder.getAmount() - order.getNotFilled()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * tOrder.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(tOrder.getNotFilled() - order.getNotFilled());
                    tOrder.setStatus("PARTIAL");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
            }
        }
        System.out.println("You have placed a market buy order for " + order.getAmount() + " " + order.getSymbol() + " shares.");
        orderArray.add(order);
        orderArray.get(orderArray.size()-1).setOrderId(orderArray.size());
    }
    // MARKET SELL ORDER
    public void processMktSellOrders(Order order){
        for(int i=0 ; i < orderArray.size(); i++){
            Order tOrder = orderArray.get(i);
            //Check current order status
            if(order.getStatus().equals("FILLED")){
                break;
            }
            //Check LMT, Check Symbol, Check Action, Check Price & resolve order, Check Status
            if(tOrder.getType().equals("LMT") && tOrder.getSymbol().equals(order.getSymbol()) && tOrder.getAction().equals("BUY")  && (!tOrder.getStatus().equals("FILLED"))) {
                // Check Amount
                if(tOrder.getNotFilled() == order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * tOrder.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * tOrder.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
                else if (tOrder.getNotFilled() < order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + (tOrder.getNotFilled() * tOrder.getLimitPrice()))/(tOrder.getAmount()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + ((order.getNotFilled() - tOrder.getNotFilled()) * tOrder.getLimitPrice()))/(order.getAmount() - tOrder.getNotFilled()));
                    tOrder.setNotFilled(0);
                    tOrder.setStatus("FILLED");
                    order.setNotFilled(order.getNotFilled() - tOrder.getNotFilled());
                    order.setStatus("PARTIAL");
                }
                else if (tOrder.getNotFilled() > order.getNotFilled()){
                    tOrder.setTriggerPrice((((tOrder.getAmount() - tOrder.getNotFilled()) * tOrder.getTriggerPrice()) + ((tOrder.getNotFilled() - order.getNotFilled()) * tOrder.getLimitPrice()))/(tOrder.getAmount() - order.getNotFilled()));
                    order.setTriggerPrice((((order.getAmount() - order.getNotFilled()) * order.getTriggerPrice()) + (order.getNotFilled() * tOrder.getLimitPrice()))/(order.getAmount()));
                    tOrder.setNotFilled(tOrder.getNotFilled() - order.getNotFilled());
                    tOrder.setStatus("PARTIAL");
                    order.setNotFilled(0);
                    order.setStatus("FILLED");
                }
            }
        }
        System.out.println("You have placed a market sell order for " + order.getAmount() + " " + order.getSymbol() + " shares.");
        orderArray.add(order);
        orderArray.get(orderArray.size()-1).setOrderId(orderArray.size());
    }
    // VIEW ORDERS
    public void viewOrders(){
        System.out.println("Order List");
        for(int i=0 ; i < orderArray.size(); i++){
            orderArray.get(i).printOrder();
        }
    }
    //QUOTE
    public void quote(String symbol){
        double ask = Integer.MAX_VALUE;
        double bid = -1;
        double last = 0;
        for(int i=0 ; i < orderArray.size(); i++){
            Order tOrder = orderArray.get(i);
            if(tOrder.getType().equals("LMT") && tOrder.getSymbol().equals(symbol) && tOrder.getAction().equals("BUY")  && (!tOrder.getStatus().equals("FILLED"))) {
                bid = Math.max(tOrder.getLimitPrice(), bid);
            }
            else if(tOrder.getType().equals("LMT") && tOrder.getSymbol().equals(symbol) && tOrder.getAction().equals("SELL")  && (!tOrder.getStatus().equals("FILLED"))) {
                ask = Math.min(tOrder.getLimitPrice(), ask);
            }
            else if (tOrder.getSymbol().equals(symbol) && (tOrder.getStatus().equals("FILLED"))) {
                last = tOrder.getTriggerPrice();
            }
        }
        if(bid == -1){
            System.out.println("No available " + symbol + " position to sell");
        }
        if(ask == Integer.MAX_VALUE){
            System.out.println("No available " + symbol + " position to buy");
        }
        if(last == 0){
            System.out.println("No transaction has been made for " + symbol);
        }
        System.out.println(symbol + " " + "BID: $" + bid + " ASK: $" + ask + " LAST: $" + last );
    }
}

