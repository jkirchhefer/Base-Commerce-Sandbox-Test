<?php
//path for transactions.json file
//path should be String
$transactions_json_path = $path_to_transactions.json;

//creates/initializes transactions
//should pass
$bct1 = new stdClass();
$bct1->form = "BCT";
$bct1->type = "SALE";
$bct1->name = "BCT 1";
$bct1->number = "4111111111111111";
$bct1->month = "09";
$bct1->year = "2019";
$bct1->amount = 1.33;

//should fail
$bct2 = new stdClass();
$bct2->form = "BCT";
$bct2->type = "SALE";
$bct2->name = "BCT 2";
$bct2->number = "1234567890123456";
$bct2->month = "11";
$bct2->year = "2012";
$bct2->amount = 1.33;

//should pass
$bat1 = new stdClass();
$bat1->form = "BAT";
$bat1->type = "DEBIT";
$bat1->method = "CCD";
$bat1->rt_number = "021000021";
$bat1->acct_type = "CHECKING";
$bat1->name = "BAT 1";
$bat1->acct_number = "12345";
$bat1->amount = 10.18;
$bat1->date = "now";

//should fail
$bat2 = new stdClass();
$bat2->form = "BAT";
$bat2->type = "DEBIT";
$bat2->method = "CCD";
$bat2->rt_number = "686868689";
$bat2->acct_type = "CHECKING";
$bat2->name = "Bat 2";
$bat2->acct_number = "56789";
$bat2->amount = 10.18;
$bat2->date = "now";

//random
$bct3 = new stdClass();
$bct3->form = "BCT";
$bct3->type = "SALE";
$bct3->name = "BCT 3";
$bct3->number = "4111111111111111";
$bct3->month = "09";
$bct3->year = "2020";
$bct3->amount = 1.30;

//random
$bct4 = new stdClass();
$bct4->form = "BCT";
$bct4->type = "SALE";
$bct4->name = "BCT 4";
$bct4->number = "6011111111111117";
$bct4->month = "07";
$bct4->year = "2021";
$bct4->amount = 13.37;

//random
$bct5 = new stdClass();
$bct5->form = "BCT";
$bct5->type = "SALE";
$bct5->name = "BCT 5";
$bct5->number = "5555555555554444";
$bct5->month = "04";
$bct5->year = "2020";
$bct5->amount = 1.88;

//random
$bat3 = new stdClass();
$bat3->form = "BAT";
$bat3->type = "DEBIT";
$bat3->method = "CCD";
$bat3->rt_number = "555554444";
$bat3->acct_type = "CHECKING";
$bat3->name = "Bat 3";
$bat3->acct_number = "55544";
$bat3->amount = 3.32;
$bat3->date = "now";

//random
$bat4 = new stdClass();
$bat4->form = "BAT";
$bat4->type = "DEBIT";
$bat4->method = "CCD";
$bat4->rt_number = "313374449";
$bat4->acct_type = "CHECKING";
$bat4->name = "Bat 4";
$bat4->acct_number = "22511";
$bat4->amount = 10.20;
$bat4->date = "now";

//random
$bct6 = new stdClass();
$bct6->form = "BCT";
$bct6->type = "SALE";
$bct6->name = "BCT 6";
$bct6->number = "69420313376669999";
$bct6->month = "09";
$bct6->year = "1967";
$bct6->amount = 2.60;

//random
$bct7 = new stdClass();
$bct7->form = "BCT";
$bct7->type = "SALE";
$bct7->name = "BCT 7";
$bct7->number = "30569309025904";
$bct7->month = "02";
$bct7->year = "2019";
$bct7->amount = 1.30;

//random
$bat5 = new stdClass();
$bat5->form = "BAT";
$bat5->type = "DEBIT";
$bat5->method = "CCD";
$bat5->rt_number = "089572644";
$bat5->acct_type = "CHECKING";
$bat5->name = "Bat 5";
$bat5->acct_number = "25048";
$bat5->amount = 5.24;
$bat5->date = "now";

//random
$bct8 = new stdClass();
$bct8->form = "BCT";
$bct8->type = "SALE";
$bct8->name = "BCT 8";
$bct8->number = "371449635398431";
$bct8->month = "11";
$bct8->year = "2019";
$bct8->amount = 2.49;

//random
$bct9 = new stdClass();
$bct9->form = "BCT";
$bct9->type = "SALE";
$bct9->name = "BCT 9";
$bct9->number = "1111222233334444";
$bct9->month = "10";
$bct9->year = "2030";
$bct9->amount = 0.89;

//random
$bct10 = new stdClass();
$bct10->form = "BCT";
$bct10->type = "SALE";
$bct10->name = "BCT 10";
$bct10->number = "8888555544442222";
$bct10->month = "04";
$bct10->year = "2019";
$bct10->amount = 6.30;

//random
$bct11 = new stdClass();
$bct11->form = "BCT";
$bct11->type = "SALE";
$bct11->name = "BCT 11";
$bct11->number = "5200828282828210";
$bct11->month = "10";
$bct11->year = "2021";
$bct11->amount = 3.36;

//random
$bct12 = new stdClass();
$bct12->form = "BCT";
$bct12->type = "SALE";
$bct12->name = "BCT 12";
$bct12->number = "4111111111111111";
$bct12->month = "01";
$bct12->year = "2009";
$bct12->amount = 4.89;

//creates array of transactions
$transactions = array();
array_push($transactions, json_encode($bct1));
array_push($transactions, json_encode($bct2));
array_push($transactions, json_encode($bat1));
array_push($transactions, json_encode($bat2));
array_push($transactions, json_encode($bct3));
array_push($transactions, json_encode($bct4));
array_push($transactions, json_encode($bct5));
array_push($transactions, json_encode($bat3));
array_push($transactions, json_encode($bat4));
array_push($transactions, json_encode($bct6));
array_push($transactions, json_encode($bct7));
array_push($transactions, json_encode($bat5));
array_push($transactions, json_encode($bct8));
array_push($transactions, json_encode($bct9));
array_push($transactions, json_encode($bct10));
array_push($transactions, json_encode($bct11));
array_push($transactions, json_encode($bct12));

//opens transactions.json file
$transactions_file = fopen($transactions_json_path, "a");

//writes transactions to the file
foreach ($transactions as $transaction) {
    fwrite($transactions_file, "$transaction\n");
}

//closes transactions file
fclose($transactions_file);
