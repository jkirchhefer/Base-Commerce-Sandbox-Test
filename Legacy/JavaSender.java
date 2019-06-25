package sdk.test;
import java.io.File;
import java.io.FileWriter;
import com.basecommercepay.client.*;
import java.util.ArrayList;
import java.util.List;

public class JavaSender {
    public static void main(String[] args) throws Exception {
        //creates file object for storing transaction IDs
        //paths should be String
        File transactions_file = new File($path_to_transactions.txt);
        
        //tests existence of transactions file, creates/initializes file if DNE
        if (transactions_file.createNewFile()) { 
            FileWriter transactions_file_writer = new FileWriter(transactions_file);
            transactions_file_writer.write("TransactionID\n");
            transactions_file_writer.close();
        }
        
        //creates/initializes transactions
        //should pass
        BankCardTransaction transaction1 = new BankCardTransaction();
        transaction1.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction1.setAmount(5.25);
        transaction1.setCardName("Transaction 1");
        transaction1.setCardNumber("4111111111111111");
        transaction1.setCardExpirationMonth("03");
        transaction1.setCardExpirationYear("2020");
        
        //should fail
        BankCardTransaction transaction2 = new BankCardTransaction();
        transaction2.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction2.setAmount(5.25);
        transaction2.setCardName("Transaction 2");
        transaction2.setCardNumber("4111111111111111");
        transaction2.setCardExpirationMonth("03");
        transaction2.setCardExpirationYear("2019");
        
        //random
        BankCardTransaction transaction3 = new BankCardTransaction();
        transaction3.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction3.setAmount(6.25);
        transaction3.setCardName("Transaction 3");
        transaction3.setCardNumber("371449635398431");
        transaction3.setCardExpirationMonth("04");
        transaction3.setCardExpirationYear("2020");
        
        //random
        BankCardTransaction transaction4 = new BankCardTransaction();
        transaction4.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction4.setAmount(4.20);
        transaction4.setCardName("Transaction 4");
        transaction4.setCardNumber("601111111111117");
        transaction4.setCardExpirationMonth("03");
        transaction4.setCardExpirationYear("2018");
        
        //random
        BankCardTransaction transaction5 = new BankCardTransaction();
        transaction5.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction5.setAmount(3.33);
        transaction5.setCardName("Transaction 5");
        transaction5.setCardNumber("5200828282828210");
        transaction5.setCardExpirationMonth("06");
        transaction5.setCardExpirationYear("2022");
        
        //random
        BankCardTransaction transaction6 = new BankCardTransaction();
        transaction6.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction6.setAmount(13.37);
        transaction6.setCardName("Transaction 6");
        transaction6.setCardNumber("4012888888881881");
        transaction6.setCardExpirationMonth("10");
        transaction6.setCardExpirationYear("2019");
       
        //random
        BankCardTransaction transaction7 = new BankCardTransaction();
        transaction7.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction7.setAmount(2.20);
        transaction7.setCardName("Transaction 7");
        transaction7.setCardNumber("378734493671000");
        transaction7.setCardExpirationMonth("8");
        transaction7.setCardExpirationYear("2020");
        
        //random
        BankCardTransaction transaction8 = new BankCardTransaction();
        transaction8.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction8.setAmount(14.42);
        transaction8.setCardName("Transaction 8");
        transaction8.setCardNumber("1234313371237965");
        transaction8.setCardExpirationMonth("19");
        transaction8.setCardExpirationYear("3608");
       
        //random
        BankCardTransaction transaction9 = new BankCardTransaction();
        transaction9.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction9.setAmount(4.48);
        transaction9.setCardName("Transaction 9");
        transaction9.setCardNumber("4222222222222222");
        transaction9.setCardExpirationMonth("11");
        transaction9.setCardExpirationYear("2021");
        
        //random
        BankCardTransaction transaction10 = new BankCardTransaction();
        transaction10.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction10.setAmount(0.28);
        transaction10.setCardName("Transaction 10");
        transaction10.setCardNumber("5105105105105100");
        transaction10.setCardExpirationMonth("3");
        transaction10.setCardExpirationYear("2022");
        
        //random
        BankCardTransaction transaction11 = new BankCardTransaction();
        transaction11.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction11.setAmount(8.29);
        transaction11.setCardName("Transaction 11");
        transaction11.setCardNumber("37144963598431");
        transaction11.setCardExpirationMonth("twelve");
        transaction11.setCardExpirationYear("2024");
        
        //random
        BankCardTransaction transaction12 = new BankCardTransaction();
        transaction12.setType(BankCardTransaction.XS_BCT_TYPE_SALE);
        transaction12.setAmount(12.56);
        transaction12.setCardName("Transaction 12");
        transaction12.setCardNumber("6011000990139424");
        transaction12.setCardExpirationMonth("12");
        transaction12.setCardExpirationYear("2022");
        
        //creates/initializes list of transactions
        List<BankCardTransaction> transactions = new ArrayList();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        transactions.add(transaction6);
        transactions.add(transaction7);
        transactions.add(transaction8);
        transactions.add(transaction9);
        transactions.add(transaction10);
        transactions.add(transaction11);
        transactions.add(transaction12);
        
        //authenticates client
        //credentials should be String
        BaseCommerceClient client = new BaseCommerceClient($username, $password, $key);
        client.setSandbox(true);
        
        //opens transactions file stream
        FileWriter transactions_file_writer = new FileWriter(transactions_file, true);

        //processes transaction and records ID
        for(BankCardTransaction transaction: transactions) {
            transaction = client.processBankCardTransaction(transaction);
            transactions_file_writer.write(transaction.getTransactionId() + "\n");
        }
        //closes transactions file stream
        transactions_file_writer.close();
        
        //prints session ID for later reference
        System.out.println("Session ID: " + client.getLastSessionID());
    }
}
