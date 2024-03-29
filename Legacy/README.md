# Legacy
## Overview
These programs are now obsolete and will no longer be updated.

PythonSender.py is able to work with ACH but it has not been fully implemented. JavaSender.java and PHPSender.php only process credit cards and no further functionality will be implemented.

## Getting Started
#### JavaSender.java
* The first thing that is required for you to run this program is that you define `f` by replacing `$path_to_transactions.txt` with the path to where you'd like the program to store the file where it will write transaction IDs. The file path variables must be defined as String.   
E.G.
```
File f = new File("/home/user/sandbox-test/transactions.txt");
```
* From there, you can design the transactions to your desire. Just be sure to modify `transactions[]` accordingly, if you add or remove any transactions.   
E.G. if you add transaction13,
```
BankCardTransaction transaction13 = new BankCardTransaction();
transaction13.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
transaction13.setAmount(5.25);
transaction13.setCardName("Transaction 13");
transaction13.setCardNumber("4111111111111111");
transaction13.setCardExpirationMonth("03");
transaction13.setCardExpirationYear("2020");
```
do not forget
```
transactions.add(transaction13);
```
and vice versa. If you remove any transactions, remove any references to it elsewhere.
* It also requires your credentials to initialize the Base Commerce client. Simply replace `$username`, `$password`, and `$key` with their respecive credentials, which can be found on the Developer page of the Sandbox Portal. Your credentials should be passed as String.      
E.G. 
```
BaseCommerceClient client = new BaseCommerceClient($"your username here", "your password here", "your transaction key here");
```
* From there, you should be able to run the program. 

#### PHPSender.php
* The first thing necessary for you to run this program is that you define `path` by replacing `$path_to_transactions.txt` with the path to where you'd like the program to store the file where it will write the transaction IDs. The file path variables must be defined as String.   
E.G.
```
$path = "/home/user/sandbox-test/transactions.txt"
```
* From there, you can design the transactions to your desire. Just be sure to modify `transactions[]` accordingly, if you add or remove any transactions.   
E.G. if you add transaction13,
```
$transaction13 = new BankCardTransaction();
$transaction13->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction13->setCardName("Transaction 13");
$transaction13->setCardNumber("4111111111111111");
$transaction13->setCardExpirationMonth("09");
$transaction13->setCardExpirationYear("2019");
$transaction13->setAmount(1.33);
```
do not forget
```
array_push($transactions, $transaction13->getTransactionID());
```
and vice versa. If you remove any transactions, remove any references to it elsewhere.
* It also requires your credentials to initialize the Base Commerce client. Simply replace `$username`, `$password`, and `$key` with their respecive credentials, which can be found on the Developer page of the Sandbox Portal. Your credentials should be passed as String.      
E.G. 
```
$client = new BaseCommerceClient("your username here", "your password here", "your transaction key here");
```
* From there, you should be able to run the program. 

#### PythonSender.py
* The only thing necessary for you to run this program is that you define `file` by replacing `$path_to_transactions.txt` with the path to where you'd like the program to store the file where it will write the transactions. The file path variables must be defined as String.   
E.G.
```
file = "/home/user/sandbox-test/transactions.txt"
```
* From there, you can design the transactions to your desire. Just be sure to modify `transactions[]` accordingly, if you add or remove any transactions.   
E.G. if you add transaction13,
```
transaction13 = BankCardTransaction()
transaction13.type = BankCardTransaction.XS_BCT_TYPE_SALE
transaction13.name = "BCT 13"
transaction13.card_number = "4111111111111111"
transaction13.expiration_month = "10"
transaction13.expiration_year = "2019"
transaction13.amount = 5.25
```
do not forget
```
transactions.append(transaction13)
```
and vice versa. If you remove any transactions, remove any references to it elsewhere.
* It also requires your credentials to initialize the Base Commerce client. Simply replace `$username`, `$password`, and `$key` with their respecive credentials, which can be found on the Developer page of the Sandbox Portal. The `True` is necessary for testing in the sandbox environment. Your credentials should be passed as String.      
E.G. 
```
client = BaseCommerceClient("your username here", "your password here", "your transaction key here", True)
```
* From there, you should be able to run the program. 
