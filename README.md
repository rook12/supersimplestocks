# Super Simple Stocks

# Assumptions

- Order/Trade are used interchangeably
- Could have done dividend yield and P/E ratio on stock broker, kept it all in exchange
- Made Preferred stock a subclass of Common stock as it shares all the same properties (excluding one) and the same functionality (excluding dividend yield calculation). Perhaps would have used decorator or strategy if they diverged more.
- **Couldn't get a PE ratio for TEA (divide by zero dividend). Default to 0? throws exception at the moment**
- Haven't introduced check preventing same order ID going in more than once
- Check if I can run more than 100 order without breaking vwsp
- improve all share index calculation
- Put in invalid stock exception
- naive implementation, blindly assuming stocks are available
- maybe could have put dividend service methods into general service class, and remove all share index/vwsp caluclation to other service. initial thought was they are closely related to trade activity. ran of out time to refactor
- maybe could have made broker oder id not an int
- change dependencies to explicit versions
- All Share index. calculating product dies at 100
- log of product, then dies at around 500
- log10 of product, around 800
stockexchange.geometricmeanmethod=SIMPLE / NATUALLOG
- Was going to implement a store on the broker side to hold client position, hence the empty CUstomerAccount and repository classes. 
#Example commands

- execute-trade TEA 100 BUY 2000

#Notes

- 04-07-18 Backend looks complete. Finish up tests on shell commands, and wire up client service calls to back end
- 03-07-18 PM - Implemented all functionality on backend, including simulation. Expose execute Order functionality through front end, and complete client code.
- 03-07-18 AM - Done trading repo and tests, next do trading service and tests. Move trade response DTO to client?
- 02-07-18 Started on trading activity service and repo. Test repo interface (for save, get all and get delta). Then concrete impl and test
- 01-07-18 Implemented dividend yield calculation and some plumbing. Do dividend yield end to end, cover with unit tests, then move onto other reqs
- 30-06-18 - Got to point where I can call (non-existent) web service. Need to map response from web service back to DTO. Failing at the moment. Perhaps try implementing service and seeing what it returns, then attempt debug