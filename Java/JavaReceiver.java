package sdk.test;
import java.io.File;
import java.io.FileWriter;
import com.basecommercepay.client.*;
import java.util.Scanner;

public class JavaReceiver {
    public static void main(String[] args) throws Exception{
        //creates file object for reading transaction types and IDs
        File transactions = new File("/home/justin/Documents/sandbox-test/transactions.txt");
        Scanner s = new Scanner(transactions);
        
        //creates file object for storing transaction information
        File statuses = new File("/home/justin/Documents/sandbox-test/statuses.csv");
        
        //checks existence of a file for storing transaction information, creates/initializes if DNE 
        if (statuses.createNewFile()) { 
            FileWriter statusWriter = new FileWriter(statuses);
            statusWriter.write("Name,Id,Amount,Status\n");
            statusWriter.close();
        }
        
        //authenticates client
        BaseCommerceClient o_client = new BaseCommerceClient("0014480001", "YjSbhVjTp4zv3Jvw8F6g", "C88A85467391577A4A49A832DAF2D3E6D32F6D2092267540");
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
