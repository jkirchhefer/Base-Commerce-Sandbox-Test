# Getting Started
## javawriter.java
* The first thing you must do is make sure you have the [org.json package](https://github.com/stleary/JSON-java) in your source folder.
* All that is left for you to run this program is that you define `transactions_file` by replacing `$path_to_transactions.json` with the path to where you'd like the program to store the file where it will write the transactions. The file path variables must be defined as String.   
E.G.
```
File transactions_file = new File("/home/user/sandbox-test/transactions.json");
```
* From there, you can design the transactions to your desire. Just be sure to modify `transactions[]` accordingly, if you add or remove any transactions.   
E.G. if you add bct13,
```
JSONObject bct13 = new JSONObject();
        bct13.put("form", "BCT");
        bct13.put("type", "SALE");
        bct13.put("name", "BCT 13");
        bct13.put("number", "4111111111111111");
        bct13.put("month", "10");
        bct13.put("year", "2020");
        bct13.put("amount", 5.25);
```
do not forget
```
transactions.add(bct13);
```
and vice versa. If you remove any transactions, remove any references to it elsewhere.

## javareader.java
* The [org.json package](https://github.com/stleary/JSON-java) should be added to the source folder for this program, if it is not in the same location as the writer program. 
* This program has 2 file paths to be specified, just as before: `transactions_json` and `transactions_text`. `transactions_json` should point to where the writer program stored the JSON file with the test transactions, and `transactions_text` should point to where you want the reader program to store the file to which it will write transaction IDs.     
E.G.
```
File transactions_json = new File( "/home/user/sandbox-test/transactions.json");
File transactions_text = new File("/home/user/sandbox-test/transactions.txt");

```
* It also requires your credentials to initialize the Base Commerce client. Simply replace `$username`, `$password`, and `$key` with their respecive credentials, which can be found on the Developer page of the Sandbox Portal. Your credentials should be passed as String.      
E.G. 
```
BaseCommerceClient client = new BaseCommerceClient($"your username here", "your password here", "your transaction key here");
```
* From there, you should be able to run the program. 

## JavaReceiver.java
* This last program requires that you specify 2 file paths, `transactions_file` and `statuses`. These should point to the file from which it will read transaction IDs and where the program will store the file to which it will write transaction statuses, respectively.    
E.G.
```
File transactions_file = new File("/home/user/sandbox-test/transactions.txt");
File statuses = new File("/home/user/sandbox-test/statuses.csv");
```
* Second, you must provide your credentials to initialize the Base Commerce client. This is the same as before. Replace `$username`, `$password`, and `$key` with their respective credentials.   
E.G.
```
BaseCommerceClient client = new BaseCommerceClient($"your username here", "your password here", "your transaction key here");
```
* The program should now be able to run.
* The statuses file will be in csv format for easy human digestion. You will be able to find it at the location previously specified. 
