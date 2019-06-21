<?php
//import base commerce sdk
include "BaseCommerceClient/basecommercephpsdk/index.php";

//path variables for the transactions and statuses files
//paths should be String
$transactionsPath = $path_to_transactions.txt;
$statusesPath = $path_to_statuses.csv;

//creates/initializes statuses file if DNE
if (! file_exists($statusesPath)) {
    $statuses = fopen($statusesPath, "w");
    fwrite($statuses, "Name,Id,Amount,Status\n");
    fclose($statuses);
}

//opens the transactions and statuses files for reading and writing, respectively
$transactionsFile = fopen($transactionsPath, "r");
$statuses = fopen($statusesPath, "a");

//iteratespast the header information
fgets($transactionsFile);        

//creates array for transaction types and ids
$transactions = array();

//populates array of transaction types and ids, filtering empty entries
while(!feof($transactionsFile)) {
    $transactionInfo = explode(",", fgets($transactionsFile));
    $transactionInfo = array_filter($transactionInfo);
    array_push($transactions, $transactionInfo);
    $transactions = array_filter($transactions);
}

//closes transactions file
fclose($transactionsFile);

//authenticates client
//credentials should be string
$o_bcpc = new BaseCommerceClient($username, $password, $key);
$o_bcpc->setSandbox( true );

//gets transaction statuses and stores it in the statuses file
foreach ($transactions as $transactionInfo) {
    $type = $transactionInfo[0];
    $id = intval($transactionInfo[1]);
            
    if($type === "BCT") {
        $transaction = $o_bcpc->getBankCardTransaction($id);
        $name = $transaction->getCardName();
        $amount = $transaction->getAmount();
        $status = $transaction->getStatus();
        fwrite($statuses, "$name,$id,$amount,$status\n");
    } elseif($type === "BAT") {
        $transaction = $o_bcpc->getBankAccountTransaction($id);
        $name = $transaction->getAccountName();
        $amount = $transaction->getAmount();
        $status = $transaction->getStatus();
        fwrite($statuses, "$name,$id,$amount,$status\n");
    }
}

//closes statuses file
fclose($statuses);

//echos session ID for later reference
$sessionID = $o_bcpc->getLastSessionId();
echo "Session ID: $sessionID";
