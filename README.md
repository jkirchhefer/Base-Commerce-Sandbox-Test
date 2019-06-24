# Base Commerce Sandbox Test
This suite of programs is for testing integration with the Base Commerce Sandbox.
## Updates
#### 6/24/19
Added more documentation for the Python set of programs. This will soon be completed for the entire suite.  

#### 6/21/19
The entire suite has been refactored. Functionally, everything operates the same as before. However, all the code has been commented and rewritten to be more extensible, usable, and understandable.

#### 6/18/19
All the sender programs are now obsolete. They have been replaced by the writer and reader programs.
The writer program stores transactions and all their information as JSON data. The reader program reads the JSON
data and processes the transactions and stores the IDs. The receiver program functions the same as before. The entire suite of programs is now able to read/write/process ACH as well. All the programs are fully implemented and interoperable.

## Getting Started
* For each program, be sure to put in your credentials and the paths to where you'd like the program to write its files.  
* Make sure you have all the proper [packages](https://github.com/jkirchhefer/sandbox-test/blob/master/README.md#packages).  
* Consult the [Base Commerce technical documentation](https://confluence.basecommerce.net/bctd), if you're having any trouble.  

#### Packages
  * Make sure you have the Base Commere SDK for your respective language in the source folder, which can be downloaded from the developer page of the [sandbox portal](https://my.basecommercesandbox.com/).  
  * The Java suite of programs also requires that you have the [org.json package](https://github.com/stleary/JSON-java).

## Descriptions
#### Writer Programs
This program helps you design the specific test cases you would like to process. Simply set the parameters for each transaction and add it to the list. When you run the program, it will convert the transaction to JSON and write it to the transactions.json file. 
Format: JSON

#### Reader Programs
This program reads transactions from the transactions.json file, processes them, and writes each transaction type and ID to the transactions.txt file.
Format: Type,ID

#### Receiver Programs
This program reads the transaction types and IDs from the transactions.txt file, retrives the status of each transaction, and writes the
Name, ID, amount, and status to the statuses.csv file. 
Format: Name,ID,Amount,Status
