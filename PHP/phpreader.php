<?php
include "BaseCommerceClient/basecommercephpsdk/index.php";

//checks if transactions file exists, creates if DNE
if (! file_exists($transactions_text)) {
    $file = fopen($transactions_text, "w");
    fwrite($file, "Format: Type,TransactionID\n");
    fclose($file);
}

//opens transactions file
//paths should be String
$transactions_json = fopen($path_to_transactions.json, "r");       

//creates array for storing transactions
$transactions = array();

//populates transactions array
while(!feof($transactions_json)) {
    array_push($transactions, json_decode(fgets($transactions_json)));
    $transactions = array_filter($transactions);
}

//closes transactions file
fclose($transactions_son);

//stores length of transactions array
$lentransactions = count($transactions);

//opens transactions file
$transactions_text = fopen($path_to_transactions.txt, "a");

//authenticates client
//credentials should be String
$o_bcpc = new BaseCommerceClient($username, $password, $key);
$o_bcpc->setSandbox( true );

//processes transactions and stores its type and ID
for($i=0; $i<$lentransactions; $i++) {
    if($transactions[$i]->form === "BCT") {
        $bct = new BankCardTransaction();
        $bct->setType($transactions[$i]->type);
        $bct->setCardName($transactions[$i]->name);
        $bct->setCardNumber($transactions[$i]->number);
        $bct->setCardExpirationMonth($transactions[$i]->month);
        $bct->setCardExpirationYear($transactions[$i]->year);
        $bct->setAmount($transactions[$i]->amount);
        $bct = $o_bcpc->processBankCardTransaction( $bct );
        fwrite($file, "BCT,");
        fwrite($file, $bct->getTransactionID());
        fwrite($file, "\n");
    } elseif($transactions[$i]->form === "BAT") {
        $bat = new BankAccountTransaction();
        $bat->setType($transactions[$i]->type);
        $bat->setMethod($transactions[$i]->method);
        $bat->setRoutingNumber($transactions[$i]->rt_number);
        $bat->setAccountType($transactions[$i]->acct_type);
        $bat->setAccountName($transactions[$i]->name);
        $bat->setAccountNumber($transactions[$i]->acct_number);
        $bat->setAmount($transactions[$i]->amount);
        $bat->setEffectiveDate(date('m-d-Y H:i:s'));
        $bat = $o_bcpc->processBankAccountTransaction( $bat );
        fwrite($file, "BAT,");
        fwrite($file, $bat->getBankAccountTransactionId());
        fwrite($file, "\n");
    } 
}

//closes transactions file
fclose($file);

//echos session ID for later reference
echo "Session ID: " + $o_bcpc->getLastSessionId();
