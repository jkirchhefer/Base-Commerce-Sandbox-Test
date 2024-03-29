<?php

//imports base commerce SDK
include "BaseCommerceClient/basecommercephpsdk/index.php";

//path to transactions file
//path should be String
$transactions_path = $path_to_transactions.txt;

//creates/initializes transactions file if DNE
if (! file_exists($transactions_path)) {
    $transactions_file = fopen($transactions_path, "w");
    fwrite($transactions_file, "Transaction ID\n");
    fclose($transactions_file);
}

//creates/initializes transation
//should pass
$transaction1 = new BankCardTransaction();
$transaction1->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction1->setCardName("Transaction 1");
$transaction1->setCardNumber("4111111111111111");
$transaction1->setCardExpirationMonth("09");
$transaction1->setCardExpirationYear("2019");
$transaction1->setAmount(1.33);

//should fail
$transaction2 = new BankCardTransaction();
$transaction2->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction2->setCardName("Transaction 2");
$transaction2->setCardNumber("123456789023456");
$transaction2->setCardExpirationMonth("11");
$transaction2->setCardExpirationYear("20122");
$transaction2->setAmount(1.33);

//random
$transaction3 = new BankCardTransaction();
$transaction3->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction3->setCardName("Transaction 3");
$transaction3->setCardNumber("4111111111111111");
$transaction3->setCardExpirationMonth("09");
$transaction3->setCardExpirationYear("2020");
$transaction3->setAmount(1.30);

//random
$transaction4 = new BankCardTransaction();
$transaction4->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction4->setCardName("Transaction 4");
$transaction4->setCardNumber("6011111111111117");
$transaction4->setCardExpirationMonth("07");
$transaction4->setCardExpirationYear("2021");
$transaction4->setAmount(13.37);

//random
$transaction5 = new BankCardTransaction();
$transaction5->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction5->setCardName("Transaction 5");
$transaction5->setCardNumber("5555555555554444");
$transaction5->setCardExpirationMonth("04");
$transaction5->setCardExpirationYear("2020");
$transaction5->setAmount(1.88);

//random
$transaction6 = new BankCardTransaction();
$transaction6->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction6->setCardName("Transaction 6");
$transaction6->setCardNumber("69420313376668888");
$transaction6->setCardExpirationMonth("09");
$transaction6->setCardExpirationYear("1967");
$transaction6->setAmount(2.60);

//random
$transaction7 = new BankCardTransaction();
$transaction7->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction7->setCardName("Transaction 7");
$transaction7->setCardNumber("30569309025904");
$transaction7->setCardExpirationMonth("02");
$transaction7->setCardExpirationYear("2019");
$transaction7->setAmount(1.30);

//random
$transaction8 = new BankCardTransaction();
$transaction8->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction8->setCardName("Transaction 8");
$transaction8->setCardNumber("371449635398431");
$transaction8->setCardExpirationMonth("11");
$transaction8->setCardExpirationYear("2019");
$transaction8->setAmount(2.49);

//random
$transaction9 = new BankCardTransaction();
$transaction9->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction9->setCardName("Transaction 9");
$transaction9->setCardNumber("1111222233334444");
$transaction9->setCardExpirationMonth("10");
$transaction9->setCardExpirationYear("2030");
$transaction9->setAmount(0.89);

//random
$transaction10 = new BankCardTransaction();
$transaction10->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction10->setCardName("Transaction 10");
$transaction10->setCardNumber("8888555544442222");
$transaction10->setCardExpirationMonth("04");
$transaction10->setCardExpirationYear("2019");
$transaction10->setAmount(6.30);

//random
$transaction11 = new BankCardTransaction();
$transaction11->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction11->setCardName("Transaction 11");
$transaction11->setCardNumber("5200858585858510");
$transaction11->setCardExpirationMonth("12");
$transaction11->setCardExpirationYear("2021");
$transaction11->setAmount(3.36);

//random
$transaction12 = new BankCardTransaction();
$transaction12->setType(BankCardTransaction::$XS_BCT_TYPE_SALE);
$transaction12->setCardName("Transaction 12");
$transaction12->setCardNumber("41111111111111111");
$transaction12->setCardExpirationMonth("01");
$transaction12->setCardExpirationYear("2009");
$transaction12->setAmount(4.89);


//authenticates client
//credentials should be String
$client = new BaseCommerceClient($username, $key, $password);
$client->setSandbox(true);

//processes transaction
$transaction1 = $client->processBankCardTransaction($transaction1);
$transaction2 = $client->processBankCardTransaction($transaction2);
$transaction3 = $client->processBankCardTransaction($transaction3);
$transaction4 = $client->processBankCardTransaction($transaction4);
$transaction5 = $client->processBankCardTransaction($transaction5);
$transaction6 = $client->processBankCardTransaction($transaction6);
$transaction7 = $client->processBankCardTransaction($transaction7);
$transaction8 = $client->processBankCardTransaction($transaction8);
$transaction9 = $client->processBankCardTransaction($transaction9);
$transaction10 = $client->processBankCardTransaction($transaction10);
$transaction11 = $client->processBankCardTransaction($transaction11);
$transaction12 = $client->processBankCardTransaction($transaction12);


//creates array of transaction IDs
$transactions = array();
array_push($transactions, $transaction1->getTransactionID());
array_push($transactions, $transaction2->getTransactionID());
array_push($transactions, $transaction3->getTransactionID());
array_push($transactions, $transaction4->getTransactionID());
array_push($transactions, $transaction5->getTransactionID());
array_push($transactions, $transaction6->getTransactionID());
array_push($transactions, $transaction7->getTransactionID());
array_push($transactions, $transaction8->getTransactionID());
array_push($transactions, $transaction9->getTransactionID());
array_push($transactions, $transaction10->getTransactionID());
array_push($transactions, $transaction11->getTransactionID());
array_push($transactions, $transaction12->getTransactionID());

//opens file for writing transaction IDs
$transactions_file = fopen($transactions_path, "a");

//writes transaction IDs
foreach ($transactions as $transaction) {
    fwrite($transactions_file, "$transaction\n");
}

//closes file stream
fclose($transactions_file);

//echos session ID for later reference
$sessionID = $client->getLastSessionId();
echo "Session ID: $sessionID";
