# SimpleStockExchange
## A Simple Stock Exchange written in java


## How to run
1. Open any java IDE (IntelliJ)
2. Direct into src folder
3. Run StockApp

## Test case
####Test: Placing Buy Limit order

>Action: BUY SNAP LMT $30 100 

You have placed a limit buy order for 100 SNAP shares at $30.00 each.

####Test: View Orders

>Action: VIEW ORDERS
1. SNAP LMT BUY $30.00 0/100 PENDING

####Test: Placing Buy Market order

>Action: BUY FB MKT 20

You have placed a market order for 20 FB shares.

>Action: VIEW ORDERS
1. SNAP LMT BUY $30.00 0/100 PENDING 
2. FB MKT BUY 0/20 PENDING

####Test: Placing Sell Limit order

>Action: SELL FB LMT $20.00 20

You have placed a limit sell order for 20 FB shares at $20.00 each

>Action: VIEW ORDERS
1. SNAP LMT BUY $30.00 0/100 PENDING
2. FB MKT BUY $20.00 20/20 FILLED
3. FB LMT SELL $20.00 20/20 FILLED

####Test: Placing Sell Market order

>Action: SELL SNAP MKT 20

You have placed a market sell order for 20 SNAP shares at $30.00 each

>Action: VIEW ORDERS
1. SNAP LMT BUY $30.00 20/100 PARTIAL 
2. FB MKT BUY $20.00 20/20 FILLED
3. FB LMT SELL $20.00 20/20 FILLED
4. SNAP MKT SELL $30.0 20/20 FILLED

####Test: Higher limit SELL price does not trigger lower BUY price

>Action: SELL SNAP LMT $31.00 10

You have placed a limit sell order for 10 SNAP shares at $31.00 each

>Action: VIEW ORDERS
1. SNAP LMT BUY $30.00 20/100 PARTIAL 
2. FB MKT BUY $20.00 20/20 FILLED 
3. FB LMT SELL $20.00 20/20 FILLED 
4. SNAP MKT SELL $30.0 20/20 FILLED
5. SNAP LMT SELL $31.0 0/10 PENDING

####Test: Quote Symbol

>Action: QUOTE SNAP

SNAP BID: $30.00 ASK: $31.00 LAST: $30.00

####Test: Sell stock position amount over available buy position

>Action: SELL SNAP LMT $30.00 100

You have placed a limit sell order for 100 SNAP shares at $30.0 each.

>Action: VIEW ORDERS
1. SNAP LMT BUY $30.0 100/100 FILLED
2. FB MKT BUY $20.0 20/20 FILLED
3. FB LMT SELL $20.0 20/20 FILLED
4. SNAP MKT SELL $30.0 20/20 FILLED
5. SNAP LMT SELL $31.0 0/10 PENDING
6. SNAP LMT SELL $30.0 80/100 PARTIAL

####Test: Higher limit BUY price trigger lower SELL price
#### (Note: It will trigger the first SELL price encounter from oldest to most recent order)
>Action: BUY SNAP LMT $32.00 10

You have placed a limit buy order for 10 SNAP shares at $32.0 each.

>Action: VIEW ORDERS

1. SNAP LMT BUY $30.0 100/100 FILLED
2. FB MKT BUY $20.0 20/20 FILLED
3. FB LMT SELL $20.0 20/20 FILLED
4. SNAP MKT SELL $30.0 20/20 FILLED
5. SNAP LMT SELL $31.0 10/10 FILLED
6. SNAP LMT SELL $30.0 80/100 PARTIAL
7. SNAP LMT BUY $31.0 10/10 FILLED

>Action: QUIT

Quiting...

## Assumption Made
1. When placing order, limit order will be resolved.
2. Limit order has limitPrice(The price user enter when placing order) & triggerPrice(The price order is placed).
3. Market order has only triggerPrice.
4. TriggerPrice will be recalculated everytime position is filled.
5. For Limit Order, if no position is filled, when view order, limitPrice will be shown.
6. For Market Order, if no position is filled, when view order, no price will be shown. 



## Class Attributes

1. Order 
   1. orderId
   2. action 
   3. symbol 
   4. type 
   5. limitPrice 
   6. triggerPrice 
   7. amount 
   8. notFilled 
   9. status
   
2. OrderList
   1. orderArray

## Class Method
1. Order
   1. Order(String action, String symbol, String type, double limitPrice, int amount)
   2. Order(String action, String symbol, String type, int amount)
   3. getOrderId()
   4. setOrderId(int orderId)
   5. getAction()
   6. setAction(String action)
   7. getSymbol()
   8. setSymbol(String symbol)
   9. getType()
   10. setType(String type)
   11. getLimitPrice()
   12. setLimitPrice(double limitPrice)
   13. getTriggerPrice()
   14. setTriggerPrice(double triggerPrice)
   15. getAmount()
   16. setAmount(int amount)
   17. getNotFilled()
   18. setNotFilled(int filled)
   19. getStatus()
   20. setStatus(String status)
   21. printOrder()
2. OrderList
   1. OrderList() 
   2. processLmtBuyOrders(Order order)
   3. processLmtSellOrders(Order order)
   4. processMktBuyOrders(Order order)
   5. processMktSellOrders(Order order)
   6. viewOrders()
   7. quote(String symbol)

