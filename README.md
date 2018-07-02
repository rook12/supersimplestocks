# Super Simple Stocks

# Assumptions

- Could have done dividend yield and P/E ratio on stock broker, kept it all in exchange
- Made Preferred stock a subclass of Common stock as it shares all the same properties (excluding one) and the same functionality (excluding dividend yield calculation). Perhaps would have used decorator or strategy if they diverged more.
- **Couldn't get a PE ratio for TEA (divide by zero dividend). Default to 0? throws exception at the moment**

#Notes

- 01-07-18 Implemented dividend yield calculation and some plumbing. Do dividend yield end to end, cover with unit tests, then move onto other reqs
- 30-06-18 - Got to point where I can call (non-existent) web service. Need to map response from web service back to DTO. Failing at the moment. Perhaps try implementing service and seeing what it returns, then attempt debug