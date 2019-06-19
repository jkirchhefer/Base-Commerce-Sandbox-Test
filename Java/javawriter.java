package sdk.test;
import java.io.File;
import java.io.FileWriter;
import org.json.*;
import java.util.List;
import java.util.ArrayList;
//this program requires org.json

public class javawriter {
    public static void main(String[] args) throws Exception{
        //creates/initializes transaction
        //should pass
        JSONObject bct1 = new JSONObject();
        bct1.put("form", "BCT");
        bct1.put("type", "SALE");
        bct1.put("name", "BCT 1");
        bct1.put("number", "4111111111111111");
        bct1.put("month", "10");
        bct1.put("year", "2020");
        bct1.put("amount", 5.25);
        
        //should pass
        JSONObject bat1 = new JSONObject();
        bat1.put("form", "BAT");
        bat1.put("type", "DEBIT");
        bat1.put("method", "CCD");
        bat1.put("rt_number", "021000021");
        bat1.put("acct_type", "CHECKING");
        bat1.put("name", "BAT 1");
        bat1.put("acct_number", "12345");
        bat1.put("amount", 10.18);
        bat1.put("date", "now");
        
        //should fail
        JSONObject bct2 = new JSONObject();
        bct2.put("form", "BCT");
        bct2.put("type", "SALE");
        bct2.put("name", "BCT 2");
        bct2.put("number", "4111111111111111");
        bct2.put("month","03");
        bct2.put("year", "2019");
        bct2.put("amount", 5.25);
        
        //should fail
        JSONObject bat2 = new JSONObject();
        bat2.put("form", "BAT");
        bat2.put("type", "DEBIT");
        bat2.put("method", "CCD");
        bat2.put("rt_number", "111155555");
        bat2.put("acct_type", "CHECKING");
        bat2.put("name", "BAT 2");
        bat2.put("acct_number", "66228");
        bat2.put("amount", 4.46);
        bat2.put("date", "now");
        
        //random
        JSONObject bat3 = new JSONObject();
        bat3.put("form", "BAT");
        bat3.put("type", "DEBIT");
        bat3.put("method", "CCD");
        bat3.put("rt_number", "744268425");
        bat3.put("acct_type", "CHECKING");
        bat3.put("name", "BAT 3");
        bat3.put("acct_number", "22453");
        bat3.put("amount", 5.21);
        bat3.put("date", "now");
        
        //random
        JSONObject bct3 = new JSONObject();
        bct3.put("form", "BCT");
        bct3.put("type", "SALE");
        bct3.put("name", "BCT 3");
        bct3.put("number", "371449635398431");
        bct3.put("month","03");
        bct3.put("year", "2019");
        bct3.put("amount", 5.25);
        
        //random
        JSONObject bct4 = new JSONObject();
        bct4.put("form", "BCT");
        bct4.put("type", "SALE");
        bct4.put("name", "BCT 4");
        bct4.put("number", "6011111111111117");
        bct4.put("month","03");
        bct4.put("year", "2018");
        bct4.put("amount", 4.20);
        
        //random
        JSONObject bct5 = new JSONObject();
        bct5.put("form", "BCT");
        bct5.put("type", "SALE");
        bct5.put("name", "BCT 5");
        bct5.put("number", "5200828282828210");
        bct5.put("month","06");
        bct5.put("year", "2022");
        bct5.put("amount", 3.33);
        
        //random
        JSONObject bct6 = new JSONObject();
        bct6.put("form", "BCT");
        bct6.put("type", "SALE");
        bct6.put("name", "BCT 6");
        bct6.put("number", "4012888888881881");
        bct6.put("month","10");
        bct6.put("year", "2019");
        bct6.put("amount", 13.37);
        
        //random
        JSONObject bat4 = new JSONObject();
        bat4.put("form", "BAT");
        bat4.put("type", "DEBIT");
        bat4.put("method", "CCD");
        bat4.put("rt_number", "220346581");
        bat4.put("acct_type", "CHECKING");
        bat4.put("name", "BAT 4");
        bat4.put("acct_number", "54096");
        bat4.put("amount", 2.23);
        bat4.put("date", "now");
        
        //random
        JSONObject bat5 = new JSONObject();
        bat5.put("form", "BAT");
        bat5.put("type", "DEBIT");
        bat5.put("method", "CCD");
        bat5.put("rt_number", "842566742");
        bat5.put("acct_type", "CHECKING");
        bat5.put("name", "BAT 5");
        bat5.put("acct_number", "61389");
        bat5.put("amount", 4.30);
        bat5.put("date", "now");
        
        //random
        JSONObject bct7 = new JSONObject();
        bct7.put("form", "BCT");
        bct7.put("type", "SALE");
        bct7.put("name", "BCT 7");
        bct7.put("number", "378734493671000");
        bct7.put("month","8");
        bct7.put("year", "2020");
        bct7.put("amount", 2.20);
        
        //random
        JSONObject bct8 = new JSONObject();
        bct8.put("form", "BCT");
        bct8.put("type", "SALE");
        bct8.put("name", "BCT 8");
        bct8.put("number", "1234313371237965");
        bct8.put("month","19");
        bct8.put("year", "3608");
        bct8.put("amount", 14.42);
        
        //random
        JSONObject bct9 = new JSONObject();
        bct9.put("form", "BCT");
        bct9.put("type", "SALE");
        bct9.put("name", "BCT 9");
        bct9.put("number", "4222222222222222");
        bct9.put("month","11");
        bct9.put("year", "2021");
        bct9.put("amount", 4.48);
        
        //random
        JSONObject bct10 = new JSONObject();
        bct10.put("form", "BCT");
        bct10.put("type", "SALE");
        bct10.put("name", "BCT 10");
        bct10.put("number", "5105105105105100");
        bct10.put("month","3");
        bct10.put("year", "2022");
        bct10.put("amount", 0.28);
        
        //random
        JSONObject bct11 = new JSONObject();
        bct11.put("form", "BCT");
        bct11.put("type", "SALE");
        bct11.put("name", "BCT 11");
        bct11.put("number", "4012888888881881");
        bct11.put("month","twelve");
        bct11.put("year", "2024");
        bct11.put("amount", 8.291);
        
        //random
        JSONObject bct12 = new JSONObject();
        bct12.put("form", "BCT");
        bct12.put("type", "SALE");
        bct12.put("name", "BCT 12");
        bct12.put("number", "6011000990139424");
        bct12.put("month","12");
        bct12.put("year", "2022");
        bct12.put("amount", 12.56);
        
        //creates/initializes list of transactions
        List<JSONObject> transactions = new ArrayList();
        transactions.add(bct1);
        transactions.add(bat1);
        transactions.add(bct2);
        transactions.add(bat2);
        transactions.add(bat3);
        transactions.add(bct3);
        transactions.add(bct4);
        transactions.add(bct5);
        transactions.add(bct6);
        transactions.add(bat4);
        transactions.add(bat5);
        transactions.add(bct7);
        transactions.add(bct8);
        transactions.add(bct9);
        transactions.add(bct10);
        transactions.add(bct11);
        transactions.add(bct12);
        
        //creates file object for the storage file
        //path must be a String
        File f = new File($path_to_transactions.json);
        
        //iteratest through the list of transactions, writing each one to the file
        for(JSONObject transaction: transactions) {
            FileWriter fWriter = new FileWriter(f, true);
            fWriter.write(transaction + "\n");
            fWriter.close();
        }   
    }
}
