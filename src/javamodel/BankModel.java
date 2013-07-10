package javamodel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class BankModel {
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet rs;
	int userid;
	public int validatebankdetails(String cardnumber,String nameoncard,String expirydate,String cvvcode, String type, String Username, int totalamount) throws ClassNotFoundException, SQLException
	{	int x=-1;
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "schinta2","qwe123");
		preparedStatement = con.prepareStatement("SELECT * from ACCOUNTDETAILS where USERS = (?) and CARDNUMBER=(?) and CVVCODE=(?) and NAMEASONCARD=(?) and CARDTYPE=(?)");
		preparedStatement.setString(1,Username);
		preparedStatement.setString(2,cardnumber);
		preparedStatement.setString(3,cvvcode);
		preparedStatement.setString(4,nameoncard);
		preparedStatement.setString(5,type);
		rs=preparedStatement.executeQuery();
		while(rs.next())
		{
			//variable x maintained to  Display failure reason (Incorrect details/insufficient funds etc.)
			x=0;
		if(rs.getInt("BALANCE")>=totalamount)
		{
			x=1;
			deductbalance(Username,totalamount);
			return x;
		}
		}
		return x;
	}
	
	
	
	
	public void deductbalance(String Username,int totalamout) throws ClassNotFoundException, SQLException
	{
	//Below code to deduct the amount spent in from the account details(update the new balance in the card)
	int balance=0;
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "schinta2","qwe123");
	preparedStatement = con.prepareStatement("select BALANCE from ACCOUNTDETAILS where USERS=(?)");
	preparedStatement.setString(1, Username);
	rs=preparedStatement.executeQuery();
	while(rs.next())
	{
	balance=rs.getInt("BALANCE");
	}
	preparedStatement = con.prepareStatement("update ACCOUNTDETAILS set BALANCE = (?) where USERS=(?)");
	preparedStatement.setInt(1, balance-totalamout);
	preparedStatement.setString(2, Username);
	preparedStatement.executeQuery();
	}


}
