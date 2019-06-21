package sdk.test;
import java.io.File;
import java.io.FileWriter;
import com.basecommercepay.client.*;
import java.util.Scanner;

public class JavaReceiver {
    public static void main(String[] args) throws Exception{
        //creates file object for reading transaction types and IDs
        //path must be a String
        File transactions_file = new File($path_to_transactions.txt);
        Scanner transactions_file_scanner = new Scanner(transactions_file);
        
        //creates file object for storing transaction information
        File statuses = new File($path_to_statuses.csv);
        
        //checks existence of a file for storing transaction information, creates/initializes if DNE 
        if (statuses.createNewFile()) { 
            FileWriter status_writer = new FileWriter(statuses);
            status_writer.write("Name,Id,Amount,Status\n");
            status_writer.close();
        }
        
        //authenticates client
        //credentials should be passed as String
        BaseCommerceClient client = new BaseCommerceClient($username, $password, $key);
        client.setSandbox(true);
        
        //iterates over header info
        transactions_file_scanner.nextLine();
        
        //reads transaction IDs from transactions.txt, stores transaction information in statuses.csv
        while (transactions_file_scanner.hasNextLine()) {
            String line = transactions_file_scanner.nextLine();
            String[] transaction_info = line.split(",");
            String name = "";
            String amount = "";
            String status = "";
            Integer id = Integer.parseInt(transaction_info[1]);
            
            if(transaction_info[0].equals("BCT")) {
                BankCardTransaction transaction;
                transaction = client.getBankCardTransaction(id);
                name = transaction.getCardName();
                amount = Double.toString(transaction.getAmount());
                status = transaction.getStatus();
            } else if(transaction_info[0].equals("BAT")) {
                BankAccountTransaction transaction;
                transaction = client.getBankAccountTransaction(id);
                name = transaction.getAccountName();
                amount = Double.toString(transaction.getAmount());
                status = transaction.getStatus();
            }
            FileWriter statusWriter = new FileWriter(statuses, true);
            statusWriter.write(name + "," + id + "," + amount + "," + status + "\n");
            statusWriter.close();
        }
        //closes file stream
        transactions_file_scanner.close();
        
        //prints session ID for later reference
        System.out.println("Session ID: " + client.getLastSessionID());
    }
}
