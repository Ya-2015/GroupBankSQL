package groupBank;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



public class GroupBankDatabaseApp {
	public static void main(String args[]) throws SQLException {
		
		
        //URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);
        
        //*********************//
        String sql ="select a.customername, b.transactionid,b.accountnum,tt.description,b.transamount,b.transactiondate \n"+
        				"from account a, transaction b,transactiontype tt\n"+
        				"where b.ACCOUNTNUM = a.ACCOUNTNUM AND\n"+
        				"tt.transactiontype = b.TRANSACTIONTYPE AND\n"+
        				"a.customername = 'Walter White'\n"+
        				"order by b.transactiondate";
        
        String sql_2 ="call transfer_money(6660,6661,300.50)";
        String sql_3 = "select accountnum,check_balance(6661) from account where accountnum = 6661";
        String sql_4 = "call overdraft_chargeszz(6661,7643.90,5)";

        //System.out.println(sql);
        //creating PreparedStatement object to execute query
        PreparedStatement preStatement = conn.prepareStatement(sql_4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    
        ResultSet result = preStatement.executeQuery();
        
       // result.first();
        
        while (result.next()){
        	
            System.out.println(result.getString(1)+" "+result.getString(2));//+" "+result.getString(3)+" "+result.getString(4)+" "+result.getDouble(5)+" "+result.getString(6));
           
        } 
                System.out.println("done");
                conn.close();
    }
}
