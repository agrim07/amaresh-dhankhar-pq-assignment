# Payconiq Stock Service

This is a service that is responsible for creating, updating, Deleting and Fetching stocks. Its using SQLLite in-memory database used to store Stock 
details in the DB tables.

We can view this api documentation at http://localhost:8080/swagger-ui/index.html location.

###This application having two end-points:

1) GET: /api/stocks/page/{pageNo} - This end-point takes page number as parameter and return stocks for this page.
   Example: /api/stocks/page/1
   Response Format: 

   {
   "hasPreviousPage": true,
   "hasNextPage": false,
   "stocks": [
   {
   "id": 11,
   "name": "BPCL",
   "currentPrice": 324,
   "timestamp": "1651898936069"
   },
   {
   "id": 12,
   "name": "LT",
   "currentPrice": 1700,
   "timestamp": "1651898947920"
   },
   {
   "id": 13,
   "name": "PEPSI",
   "currentPrice": 100,
   "timestamp": "1651925314427"
   }
   ]
   } 
2) POST: /api/stocks - This end-point used to create/insert stock in the database.
   Request Format:
   {
   "name": "PEPSI",
   "currentPrice": "100"
   }
3) GET: /api/stocks/{stockId} - This end-point return stock whose id passed in url as parameter if found else will throw StockNotFoundException.
   Response Format:
   {
   "id": 2,
   "name": "BOA",
   "currentPrice": 46,
   "timestamp": "1651898692798"
   }
4) PATCH: /api/stocks/{stockId} - This end-point is used to just update the current price of the stock.
   Request Format:
   {
   "id": "1",
   "currentPrice": "15"
   }
5) DELETE: /api/stocks/{stockId} - This end point is used to delete the stock whose id passed as parameter if found else it will throw StockNotFoundException.