<?php
include "BaseCommerceClient/basecommercephpsdk/index.php";

//paths for necessary files
//paths should be String
$transactions_text_path = "/home/justin/Documents/sandbox-test/transactions.txt";
$transactions_json_path = "/home/justin/Documents/sandbox-test/transactions.json";

//checks if transactions file exists, creates if DNE
if (! file_exists($transactions_text_path)) {
    $transactions_text = fopen($transactions_text_path, "w");
    fwrite($transactions_text, "Format: Type,TransactionID\n");
    fclose($transactions_text);
}

//opens transactions file
$transactions_json = fopen($transactions_json_path, "r");       

//creates array for storing transactions
$transactions = array();

//populates transactions array
while(!feof($transactions_json)) {
    array_push($transactions, json_decode(fgets($transactions_json)));
    $transactions = array_filter($transactions);
}

//closes transactions file
fclose($transactions_json);

//opens transactions file
$transactions_text = fopen($transactions_text_path, "a");

//authenticates client
//credentials should be String
$o_bcpc = new BaseCommerceClient("0014480001", "YjSbhVjTp4zv3Jvw8F6g", "C88A85467391577A4A49A832DAF2D3E6D32F6D2092267540");
$o_bcpc->setSandbox(true);

//processes transactions and stores its type and ID
foreach ($transactions as $transaction) {
    if($transaction->form === "BCT") {
        $bct = new BankCardTransaction();
        $bct->setType($transaction->type);
        $bct->setCardName($transaction->name);
        $bct->setCardNumber($transaction->number);
        $bct->setCardExpirationMonth($transaction->month);
        $bct->setCardExpirationYear($transaction->year);
        $bct->setAmount($transaction->amount);
        $bct = $o_bcpc->processBankCardTransaction($bct);
        fwrite($transactions_text, "BCT,");
        fwrite($transactions_text, $bct->getTransactionID());
        fwrite($transactions_text, "\n");
    } elseif($transaction->form === "BAT") {
        $bat = new BankAccountTransaction();
        $bat->setType($transaction->type);
        $bat->setMethod($transaction->method);
        $bat->setRoutingNumber($transaction->rt_number);
        $bat->setAccountType($transaction->acct_type);
        $bat->setAccountName($transaction->name);
        $bat->setAccountNumber($transaction->acct_number);
        $bat->setAmount($transaction->amount);
        $bat->setEffectiveDate(date('m-d-Y H:i:s'));
        $bat = $o_bcpc->processBankAccountTransaction($bat);
        fwrite($transactions_text, "BAT,");
        fwrite($transactions_text, $bat->getBankAccountTransactionId());
        fwrite($transactions_text, "\n");
    } 
}

//closes transactions file
fclose($transactions_text);

//echos session ID for later reference
$sessionID = $o_bcpc->getLastSessionId();
echo "Session ID: $sessionID";
