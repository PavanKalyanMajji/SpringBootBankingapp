API Url's:-
******************************************************************************
getPing Url: - 		GET: localhost:8080/tcsbank/v1/api/getping 

*******************************************************************************

create-account Url: - 	POST: localhost:8080/tcsbank/v1/api/create-account

RequestBody: -	
			{
    				"accountHolder":"Pavan Kalyan",
    				"accountNumber": 112233,
    				"balance" : 500.00,
    				"id":420
			}
*****************************************************************************
getAccountHolder:- GET: localhost:8080/tcsbank/v1/api/getAccountHolder/{accountNo}

*****************************************************************************
beleteByAccountNumber:- DELETE: localhost:8080/tcsbank/v1/api/{accountNo}

******************************************************************************	

creditAmount: - PUT: localhost:8080/tcsbank/v1/api/credit/{accountNo}/{amount}

*******************************************************************************

debitAmount: - PUT: localhost:8080/tcsbank/v1/api/debit/{accountNo}/{amount}

*******************************************************************************