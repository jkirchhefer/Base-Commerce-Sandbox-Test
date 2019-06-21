<?php
//import base commerce sdk
include "BaseCommerceClient/basecommercephpsdk/index.php";

//path variables for the transactions and statuses files
//paths should be String
$transactions_path = $path_to_transactions.txt;
$statuses_path = $path_to_statuses.csv;

//creates/initializes statuses file if DNE
if (! file_exists($statuses_path)) {
    $statuses = fopen($statuses_path, "w");
    fwrite($statuses, "Name,Id,Amount,Status\n");
    fclose($statuses);
}

//opens the transactions and statuses files for reading and writing, respectively
$transactions_file = fopen($transactions_path, "r");
$statuses = fopen($statuses_path, "a");

//iteratespast the header information
fgets($transactions_file);        

//creates array for transaction types and ids
$transactions = array();

//populates array of transaction types and ids, filtering empty entries
while(!feof($transactions_file)) {
    $transaction_info = explode(",", fgets($transactions_file));
    $transaction_info = array_filter($transaction_info);
    array_push($transactions, $transaction_info);
    $transactions = array_filter($transactions);
}

//closes transactions file
fclose($transactions_file);

//authenticates client
//credentials should be string
$client = new BaseCommerceClient($username, $password, $key);
$client->setSandbox(true);

//gets transaction statuses and stores it in the statuses file
foreach ($transactions as $transaction_info) {
    $type = $transaction_info[0];
    $id = intval($transaction_info[1]);
            
    if($type === "BCT") {
        $transaction = $client->getBankCardTransaction($id);
        $name = $transaction->getCardName();
        $amount = $transaction->getAmount();
        $status = $transaction->getStatus();
        fwrite($statuses, "$name,$id,$amount,$status\n");
    } elseif($type === "BAT") {
        $transaction = $client->getBankAccountTransaction($id);
        $name = $transaction->getAccountName();
        $amount = $transaction->getAmount();
        $status = $transaction->getStatus();
        fwrite($statuses, "$name,$id,$amount,$status\n");
    }
}

//closes statuses file
fclose($statuses);

//echos session ID for later reference
$sessionID = $client->getLastSessionId();
echo "Session ID: $sessionID";
