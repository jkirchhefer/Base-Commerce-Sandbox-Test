# Getting Started
## pythonwriter.py
* The only thing necessary for you to run this program is that you define `transactions_json_path` by replacing `$path_to_transactions.json` with the path to where you'd like the program to write these files. The file path variables must be defined as String.   
E.G.
```
transactions_json_path = "/home/user/sandbox-test/transactions.json"
```
* From there, you can design the transactions to your desire. Just be sure to modify `transactions[]` accordingly, if you add or remove any transactions.   
E.G. if you add bct13,
```
bct13 = {
"form": "BCT",
        "type": "SALE",
        "name": "BCT 13",
        "number": "4111111111111111",
        "month": "10",
        "year": "2020",
        "amount": 5.25,
}
```
do not forget
```
transactions.append(json.dumps(bct13))
```
and vice versa. If you remove any transactions, remove any references to it elsewhere.

## pythonreader.py
* This program has 2 file paths to be specified, just as before: `transactions_json_path` and `transactions_text_path`. `transactions_json_path` should point to where the writer program stored the test transactions as JSON data and `transactions_text_path` should point to where you want the program to store the file to which it will write transaction IDs.     
E.G.
```
transactions_json_path = "/home/user/sandbox-test/transactions.json"
transactions_text_path = "/home/user/sandbox-test/transactions.txt"
```
* It also requires your credentials to initialize the client. Simply replace each variable with its respecive credential. The `True` is necessary for testing in the sandbox environment. Your credentials should be passed as String.      
E.G. 
```
client = BaseCommerceClient("your username here", "your password here", "your transaction key herr", True)
```
* From there, you should be able to run the program. 

##PythonReceiver.py
* This last program requires that you specify 2 file paths, `statuses_file_path` and `transactions_file_path`. These should point to where the program will create the file to where it will write transaction statuses and from where it will read transaction IDs, respectively.    
E.G.
```
statuses_file_path = "/home/user/sandbox-test/statuses.csv"
transactions_file_path = "/home/user/sandbox-test/transactions.txt"
```
* Second, you must provide your credentials to initialize the client. This is the same as before. Replace the variables with their respective credentials.   
E.G.
```
client = BaseCommerceClient($username, $password, $key, True)
```
* The program should now be able to run.
* The statuses file will be in csv format for easy human digestion. 

