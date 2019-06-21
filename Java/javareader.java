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
        File transactions_json = new File($path_to_transactions.json);
        Scanner transactions_json_scanner = new Scanner(transactions_json);
        
        //creates file object for the file that will store the transaction types and IDs
        File transactions_text = new File($path_to_transactions.txt);
         
        //creates/initializes file for storing transaction type and IDs, if DNE
        if (transactions_text.createNewFile()) { 
            FileWriter transactions_text_writer = new FileWriter(transactions_text);
            transactions_text_writer.write("Format: Type,TransactionID\n");
            transactions_text_writer.close();
        }
        
        //authenticates client
        //make sure credentials are Strings
        BaseCommerceClient client = new BaseCommerceClient($username, $password, $key);
        client.setSandbox(true);
        
        //creates a file writer for storing transaction types and IDs
        FileWriter transactions_text_writer = new FileWriter(transactions_text, true);
        
        //iterates through each line of the JSON file
        //processes each transaction
        //stores the transaction type and ID to the new file
        while (transactions_json_scanner.hasNextLine()) {
            String transaction_json = transactions_json_scanner.nextLine();
            JSONObject transaction_object = new JSONObject(transaction_json);
            //System.out.println(transactionObj.getString("form"));
            if(transaction_object.getString("form").equals("BCT")) {
                BankCardTransaction transaction = new BankCardTransaction();
                transaction.setType("SALE");
                transaction.setAmount(transaction_object.getDouble("amount"));
                transaction.setCardName(transaction_object.getString("name"));
                transaction.setCardNumber(transaction_object.getString("number"));
                transaction.setCardExpirationMonth(transaction_object.getString("month"));
                transaction.setCardExpirationYear(transaction_object.getString("year"));
                transaction = client.processBankCardTransaction(transaction);
                transactions_text_writer.write("BCT," + transaction.getTransactionId() + "\n");           
            }
            else if(transaction_object.getString("form").equals("BAT")) {
                BankAccountTransaction transaction = new BankAccountTransaction();
                transaction.setType(transaction_object.getString("type"));
                transaction.setMethod(transaction_object.getString("method"));
                transaction.setRoutingNumber(transaction_object.getString("rt_number"));
                transaction.setAmount(transaction_object.getDouble("amount"));
                transaction.setAccountType(transaction_object.getString("acct_type"));
                transaction.setAccountName(transaction_object.getString("name"));
                transaction.setAccountNumber(transaction_object.getString("acct_number"));
                Calendar o_calendar = new GregorianCalendar(Locale.US);
                transaction.setEffectiveDate(o_calendar.getTime());
                transaction = client.processBankAccountTransaction(transaction);
                transactions_text_writer.write("BAT," + transaction.getBankAccountTransactionId() + "\n");        
            }
        }
        //closes open file streams
        transactions_text_writer.close();
        transactions_json_scanner.close();
        
        //prints session ID for later reference
        System.out.println("Session ID: " + client.getLastSessionID());
    }
}
