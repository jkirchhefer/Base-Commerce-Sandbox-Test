package sdk.test;
import java.io.File;
import java.io.FileWriter;
import com.basecommercepay.client.*;
import java.util.Scanner;

public class JavaReceiver {
    public static void main(String[] args) throws Exception{
        //creates file object for reading transaction types and IDs
        //path must be a String
        File transactions = new File($path_to_transactions.txt);
        Scanner s = new Scanner(transactions);
        
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
            String[] array = line.split(",");
            String name = "";
            String amount = "";
            String status = "";
            
            if(array[0].equals("BCT")) {
                BankCardTransaction transaction;
                transaction = o_client.getBankCardTransaction(Integer.parseInt(array[1]));
                name = transaction.getCardName();
                amount = Double.toString(transaction.getAmount());
                status = transaction.getStatus();
            } else if(array[0].equals("BAT")) {
                BankAccountTransaction transaction;
                transaction = o_client.getBankAccountTransaction(Integer.parseInt(array[1]));
                name = transaction.getAccountName();
                amount = Double.toString(transaction.getAmount());
                status = transaction.getStatus();
            }
            FileWriter statusWriter = new FileWriter(statuses, true);
            statusWriter.write(name + "," + array[1] + "," + amount + "," + status + "\n");
            statusWriter.close();
        }
        //prevents duplicates
        s.close();
        //transactions.delete();
    }
}
