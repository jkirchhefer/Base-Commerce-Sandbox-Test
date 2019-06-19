<?php
include "BaseCommerceClient/basecommercephpsdk/index.php";

//checks if transactions file exists, creates if DNE
if (! file_exists("/home/justin/Documents/sandbox-test/transactions.txt")) {
    $file = fopen("/home/justin/Documents/sandbox-test/transactions.txt", "w");
    fwrite($file, "Format: Type,TransactionID\n");
    fclose($file);
}

//opens transactions file
$transactionsjson = fopen("/home/justin/Documents/sandbox-test/transactions.json", "r");       

//creates array for storing transactions
$transactions = array();

//populates transactions array
while(!feof($transactionsjson)) {
    array_push($transactions, json_decode(fgets($transactionsjson)));
    $transactions = array_filter($transactions);
}

//closes transactions file
fclose($transactionsjson);

//stores length of transactions array
$lentransactions = count($transactions);

//opens transactions file
$file = fopen("/home/justin/Documents/sandbox-test/transactions.txt", "a");

//authenticates client
$o_bcpc = new BaseCommerceClient("0014480001", "YjSbhVjTp4zv3Jvw8F6g", "C88A85467391577A4A49A832DAF2D3E6D32F6D2092267540");
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
