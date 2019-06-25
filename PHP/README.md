# Getting Started
## phpwriter.php
* The only thing necessary for you to run this program is that you define `transactions_json_path` by replacing `$path_to_transactions.json` with the path to where you'd like the program to store the file where it will write the transactions. The file path variables must be defined as String.   
E.G.
```
$transactions_json_path = "/home/user/sandbox-test/transactions.json"
```
* From there, you can design the transactions to your desire. Just be sure to modify `transactions[]` accordingly, if you add or remove any transactions.   
E.G. if you add bct13,
```
$bct13 = new stdClass();
$bct13->form = "BCT";
$bct13->type = "SALE";
$bct13->name = "BCT 13";
$bct13->number = "4111111111111111";
$bct13->month = "09";
$bct13->year = "2019";
$bct13->amount = 1.33;
```
do not forget
```
array_push($transactions, json_encode($bct13));
```
and vice versa. If you remove any transactions, remove any references to it elsewhere.

## phpreader.php
* This program has 2 file paths to be specified, just as before: `transactions_text_path` and `transactions_json_path`. `transactions_text_path` should point to where you want the reader program to store the file to which it will write transaction IDs, and `transactions_json_path` should point to where the writer program stored the JSON file with the test transactions.     
E.G.
```
$transactions_text_path = "/home/user/sandbox-test/transactions.txt";
$transactions_json_path = "/home/user/sandbox-test/transactions.json";
```
* It also requires your credentials to initialize the Base Commerce client. Simply replace `$username`, `$password`, and `$key` with their respecive credentials, which can be found on the Developer page of the Sandbox Portal. Your credentials should be passed as String.      
E.G. 
```
$client = new BaseCommerceClient("your username here", "your password here", "your transaction key here");
```
* From there, you should be able to run the program. 

## PHPReceiver.php
* This last program requires that you specify 2 file paths, `transactions_path` and `statuses_path`. These should point to the file from which it will read transaction IDs and where the program will store the file to which it will write transaction statuses, respectively.  
E.G.
```
$transactions_path = "/home/user/sandbox-test/transactions.txt"
$statuses_path = "/home/user/sandbox-test/statuses.csv"
```
* Second, you must provide your credentials to initialize the Base Commerce client. This is the same as before. Replace `$username`, `$password`, and `$key` with their respective credentials.   
E.G.
```
$client = new BaseCommerceClient("your username here", "your password here", "your transaction key here");
```
* The program should now be able to run.
* The statuses file will be in csv format for easy human digestion. You will be able to find it at the location you previously specified. 
