# Super Simple Stocks

# Build

Navigate to root directory and run:

mvn clean install

Two fat Spring boot JARs will be generated

# Usage

Open two separate command prompts and navigate to the root directory. Run each command in the separate windows

```
java -jar stockbroker\target\stockbroker-0.0.1-SNAPSHOT.jar

java -jar stockexchange\target\stockexchange-0.0.1-SNAPSHOT.jar
```

Stock Exchange is the back end.

Stock Broker is the client program, it provides a shell for you to send commands to the back end. It is based on Spring Shell. Type in help and it will show you all the available commands. Tab completion is available.

# Example commands
```
* help
* simulate-trades CONSISTENT
  * -- Executes the same 10 trades every time
* simulate-trades RANDOM
  * -- Executes 100 pseudo-random trades
* calculate-all-share-index TEA
* calculate-dividend-yield POP 230
* calculate-peratio POP 230
* calculate-vwsp TEA
* execute-trade TEA 100 BUY 2000
```

# Notes

* Order/Trade are used interchangeably
* The broker sends an order ID over to the exchange (as a reference from the broker), the exchange sends it's own ID back after the trade is recorded. The exchange keeps a copy of the broker reference as well.
  * Currently a bit brittle (eg if two brokers were open at the same time they'd send the same order ID). If i was doing this again I'd change it to a UUID most likely
* Technically could have one dividend yield and P/E ratio on stock broker but decided to keep all the calculations on the back end
* Made Preferred stock a subclass of Common stock as it shares all the same properties (excluding one) and the same functionality (excluding dividend yield calculation). Perhaps would have used decorator or strategy if they diverged more.
* The BUYing and SELLing of trades doesn't verify whether stocks are available for trading, the system simply records the order
* Calcultion of the geometric mean in the all share index proved to be problematic: 
  * Calculation of the product results in some very large numbers. I initially tried to get around this by using BigDecimal. This would hold the product, but there don't appear to be any functions for getting the power of the product if that power value is less than 1 (required for a root calcuation)
  * I changed the product to be a double instead, as that supports power calculations to the power on numbers < 1. This returns an accurate geometric mean up for approximately 100 trades (based on random simulation). After this the number becomes too large 
  * Next I tried getting the product by getting the natural logarithm of each number, then reversing this using exponent after the geometric was calculated. On an example 10 trades, this results in a loss of precision of around 1.3%. However, it works on up to approximately 500 simulated trades.
  * You can switch between the double / log functions by going to application.properties in the stockexchange project. Change:
  ```
    * stockexchange.geometricmeanmethod=SIMPLE to stockexchange.geometricmeanmethod=NATURALLOG
    ```
- I was going to implement a store on the broker side to hold client position but never got around to it, hence the empty CustomerAccount and CustomerAccountRepository classes. 

