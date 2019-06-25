# Base Commerce Sandbox Test
This suite of programs is for testing integration with the Base Commerce Sandbox.
## Updates
#### 6/25/19
Documentation has been added for the entire suite of programs. This should make it even easier to get started. 

#### 6/21/19
The entire suite has been refactored. Functionally, everything operates the same as before. However, all the code has been commented and rewritten to be more extensible, usable, and understandable.

#### 6/18/19
All the sender programs are now obsolete. They have been replaced by the writer and reader programs.
The writer program stores transactions and all their information as JSON data. The reader program reads the JSON
data, processes the transactions, and stores the IDs. The receiver program functions the same as before. The entire suite of programs is now able to read/write/process ACH as well. All the programs are fully implemented and interoperable.

## Getting Started
* For each program, be sure to put in your credentials and the paths to where you'd like the program to write its files.  
* Make sure you have all the proper [packages](https://github.com/jkirchhefer/sandbox-test/blob/master/README.md#packages).  
* More detailed instructions are available in each language's respective folder.
* Consult the [Base Commerce technical documentation](https://confluence.basecommerce.net/bctd), if you're having any trouble.  

#### Packages
  * Make sure you have the Base Commere SDK for your respective language in the source folder, which can be downloaded from the developer page of the [sandbox portal](https://my.basecommercesandbox.com/).  
  * The Java suite of programs also requires that you have the [org.json package](https://github.com/stleary/JSON-java).

## Descriptions
#### Writer Programs
These programs help you design the specific test cases you would like to process. Simply set the parameters for each transaction, and add it to the list. When you run the program, it will convert the transaction to JSON and write it to the transactions.json file. This makes it easier for the user to design test cases and the reader program to later process.     
Format: JSON

#### Reader Programs
These programs read transactions from the transactions.json file, process them, and writ each transaction type and ID to the transactions.txt file. The receiver programs will later use this information to retrieve each transaction's status.     
Format: Type,ID

#### Receiver Programs
These programs read the transaction types and IDs from the transactions.txt file, retrieve the status of each transaction, and write the Name, ID, amount, and status to the statuses.csv file. This format makes it easy for the user read and check the status of each test case.     
Format: Name,ID,Amount,Status
