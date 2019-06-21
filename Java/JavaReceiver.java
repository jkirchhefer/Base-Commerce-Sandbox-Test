package sdk.test;
import java.io.File;
import java.io.FileWriter;
import com.basecommercepay.client.*;
import java.util.Scanner;

public class JavaReceiver {
    public static void main(String[] args) throws Exception{
        //creates file object for reading transaction types and IDs
        //path must be a String
        File transactionsFile = new File($path_to_transactions.txt);
        Scanner s = new Scanner(transactionsFile);
        
        //creates file object for storing transaction information
        File statuses = new File($path_to_statuses.csv);
        
        //checks existence of a file for storing transaction information, creates/initializes if DNE 
        if (statuses.createNewFile()) { 
            FileWriter statusWriter = new FileWriter(statuses);
            statusWriter.write("Name,Id,Amount,Status\n");
            statusWriter.close();
        }
        
        //authenticates client
        //credentials should be passed as String
        BaseCommerceClient o_client = new BaseCommerceClient($username, $password, $key);
        o_client.setSandbox(true);
        
        //iterates over header info
        s.nextLine();
        
        //reads transaction IDs from transactions.txt, stores transaction information in statuses.csv
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] transactionInfo = line.split(",");
            String name = "";
            String amount = "";
            String status = "";
            Integer id = Integer.parseInt(transactionInfo[1]);
            
            if(transactionInfo[0].equals("BCT")) {
                BankCardTransaction transaction;
                transaction = o_client.getBankCardTransaction(id);
                name = transaction.getCardName();
                amount = Double.toString(transaction.getAmount());
                status = transaction.getStatus();
            } else if(transactionInfo[0].equals("BAT")) {
                BankAccountTransaction transaction;
                transaction = o_client.getBankAccountTransaction(id);
                name = transaction.getAccountName();
                amount = Double.toString(transaction.getAmount());
                status = transaction.getStatus();
            }
            FileWriter statusWriter = new FileWriter(statuses, true);
            statusWriter.write(name + "," + id + "," + amount + "," + status + "\n");
            statusWriter.close();
        }
        //closes file stream
        s.close();
        
        //prints session ID for later reference
        System.out.println("Session ID: " + o_client.getLastSessionID());
    }
}
