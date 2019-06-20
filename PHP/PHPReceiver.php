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
$transactions = fopen($transactionsPath, "r");
$statuses = fopen($statusesPath, "a");

//iteratespast the header information
fgets($transactions);        

//creates array for transaction types and ids
$ids = array();

//populates array of transaction types and ids, filtering empty entries
while(!feof($transactions)) {
    $transaction = explode(",", fgets($transactions));
    $transaction = array_filter($transaction);
    array_push($ids, $transaction);
    $ids = array_filter($ids);
}

//closes transactions file
fclose($transactions);

//stores the length of the ids array
$lenids = count($ids);

//authenticates client
//credentials should be string
$o_bcpc = new BaseCommerceClient($username, $password, $key);
$o_bcpc->setSandbox( true );

//gets transaction statuses and stores it in the statuses file
for($i=0; $i<$lenids; $i++) {
    if($ids[$i][0] === "BCT") {
        $id = intval($ids[$i][1]);
        $transaction = $o_bcpc->getBankCardTransaction($id);
        $name = $transaction->getCardName();
        $amount = $transaction->getAmount();
        $status = $transaction->getStatus();
        fwrite($statuses, "$name,$id,$amount,$status\n");
    } elseif($ids[$i][0] === "BAT") {
        $id = intval($ids[$i][1]);
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
echo "Session ID: " + $o_bcpc->getLastSessionId();
