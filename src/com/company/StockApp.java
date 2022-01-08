package com.company;

import java.util.*;


public class StockApp {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean proceed = true;

        System.out.println("Welcome to a simple stock exchange platform.");
        System.out.println("Note: This is only a simulation program and no real-world transaction happens during the process.");
        System.out.println("Initializing......");
        OrderList orderList = new OrderList();
        do
        {
//            Action: SELL FB LMT $20.00 20
            System.out.println("Please enter your action");
            System.out.println("Please add \"Action\" in front of the input (Eg: \"Action: SELL FB LMT $20.00 20\")");
            System.out.println("Here are the actions you can make");
            System.out.println("1. {BUY/SELL} {SYMBOL} {LMT} {$PRICE} {AMOUNT}");
            System.out.println("2. {BUY/SELL} {SYMBOL} {MKT} {AMOUNT}");
            System.out.println("3. VIEW ORDERS");
            System.out.println("4. QUOTE {SYMBOL}");
            System.out.println("5. QUIT");
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");

            // View Orders
            if ((inputArray.length == 3) && inputArray[0].equals("Action:") && inputArray[2].equals("ORDERS") && inputArray[1].equals("VIEW")){
                orderList.viewOrders();
            }
            // QUOTE
            else if ((inputArray.length == 3) && inputArray[0].equals("Action:") && inputArray[1].equals("QUOTE") ){
                String symbol = inputArray[2];
                orderList.quote(symbol);
            }
            // LIMIT BUY ORDER
            else if ((inputArray.length == 6) && inputArray[0].equals("Action:") && inputArray[3].equals("LMT") && inputArray[1].equals("BUY") ){
                double price = Double.parseDouble(inputArray[4].substring(1));
                int amount = Integer.parseInt(inputArray[5]);
                Order order = new Order(inputArray[1], inputArray[2],"LMT", price, amount );
                orderList.processLmtBuyOrders(order);
            }
            // LIMIT SELL ORDER
            else if ((inputArray.length == 6) && inputArray[0].equals("Action:") && inputArray[3].equals("LMT") && inputArray[1].equals("SELL") ){
                double price = Double.parseDouble(inputArray[4].substring(1));
                int amount = Integer.parseInt(inputArray[5]);
                Order order = new Order(inputArray[1], inputArray[2],"LMT", price, amount );
                orderList.processLmtSellOrders(order);
            }
            // MARKET BUY ORDER
            else if ((inputArray.length == 5) && inputArray[0].equals("Action:") && inputArray[3].equals("MKT") && inputArray[1].equals("BUY") ){
                int amount = Integer.parseInt(inputArray[4]);
                Order order = new Order(inputArray[1], inputArray[2],"MKT", amount );
                orderList.processMktBuyOrders(order);
            }
            // MARKET SELL ORDER
            else if ((inputArray.length == 5) && inputArray[0].equals("Action:") && inputArray[3].equals("MKT") && inputArray[1].equals("SELL") ){
                int amount = Integer.parseInt(inputArray[4]);
                Order order = new Order(inputArray[1], inputArray[2],"MKT", amount );
                orderList.processMktSellOrders(order);
            }
            // QUIT
            else if ( (inputArray.length == 2) && inputArray[1].equals("QUIT") && inputArray[0].equals("Action:") ){
                proceed = false;
                System.out.println("Quiting...");
            }
            // INPUT INVALID
            else{
                System.out.println("Input invalid");
            }


        }while (proceed);
    }
}
