package sdk.test;
import com.basecommercepay.client.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import org.json.*;
//this program requires org.json

public class javareader {
    public static void main(String[] args) throws Exception{
        //creates file object for the json file that stores the transactions and a scanner for reading it
        //paths must be String
        File transactions = new File($path_to_transactions.json);
        Scanner s = new Scanner(transactions);
        
        //creates file object for the file that will store the transaction types and IDs
        File transactionIDs = new File($path_to_transactions.txt);
         
        //creates/initializes file for storing transaction type and IDs, if DNE
        if (transactionIDs.createNewFile()) { 
            FileWriter f = new FileWriter(transactionIDs);
            f.write("Format: Type,TransactionID\n");
            f.close();
        }
        
        //authenticates client
        //make sure credentials are Strings
        BaseCommerceClient o_client = new BaseCommerceClient($username, $password, $key);
        o_client.setSandbox(true);
        
        //creates a file writer for storing transaction types and IDs
        FileWriter f = new FileWriter(transactionIDs, true);
        
        //iterates through each line of the JSON file
        //processes each transaction
        //stores the transaction type and ID to the new file
        while (s.hasNextLine()) {
            String transactionJSON = s.nextLine();
            JSONObject transactionObj = new JSONObject(transactionJSON);
            //System.out.println(transactionObj.getString("form"));
            if(transactionObj.getString("form").equals("BCT")) {
                BankCardTransaction transaction = new BankCardTransaction();
                transaction.setType("SALE");
                transaction.setAmount(transactionObj.getDouble("amount"));
                transaction.setCardName(transactionObj.getString("name"));
                transaction.setCardNumber(transactionObj.getString("number"));
                transaction.setCardExpirationMonth(transactionObj.getString("month"));
                transaction.setCardExpirationYear(transactionObj.getString("year"));
                transaction = o_client.processBankCardTransaction(transaction);
                f.write("BCT," + transaction.getTransactionId() + "\n");           
            }
            else if(transactionObj.getString("form").equals("BAT")) {
                BankAccountTransaction transaction = new BankAccountTransaction();
                transaction.setType(transactionObj.getString("type"));
                transaction.setMethod(transactionObj.getString("method"));
                transaction.setRoutingNumber(transactionObj.getString("rt_number"));
                transaction.setAmount(transactionObj.getDouble("amount"));
                transaction.setAccountType(transactionObj.getString("acct_type"));
                transaction.setAccountName(transactionObj.getString("name"));
                transaction.setAccountNumber(transactionObj.getString("acct_number"));
                Calendar o_calendar = new GregorianCalendar(Locale.US);
                transaction.setEffectiveDate(o_calendar.getTime());
                transaction = o_client.processBankAccountTransaction(transaction);
                f.write("BAT," + transaction.getBankAccountTransactionId() + "\n");        
            }
        }
        //closes open file streams
        f.close();
        s.close();
        
        //prints session ID for later reference
        System.out.println("Session ID: " + o_client.getLastSessionID());
    }
}
