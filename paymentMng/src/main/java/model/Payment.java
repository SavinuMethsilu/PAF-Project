package model; 
import java.sql.*; 

public class Payment 
{ //A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
 
		return con; 
	} 

	public String insertPayment(String payID, String customerName, String amount, String cardNumber) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			String query = " insert into payment (`payID`,`customerName`,`amount`,`cardNumber`)"
					+ " values (?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, customerName);  
			preparedStmt.setString(3, amount); 
			preparedStmt.setString(4, cardNumber); 

			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the payment."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 

	public String readPayment() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			output = "<table border='1'"+ ">"
					+ 
			       "<th>Customer Name</th>" +
					"<th>Amount</th>" + 
					"<th>Card Number</th>" +
					"<th>Update</th>"
					+ "<th>Remove</th></tr>"; 
 
			String query = "select * from payment"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 

			while (rs.next()) 
			{ 
				 String payID = Integer.toString(rs.getInt("payID"));
				 String customerName = rs.getString("customerName"); 
				 String amount = rs.getString("amount"); 
				 String cardNumber = rs.getString("cardNumber"); 

				
				 output += "<td>" + customerName + "</td>"; 
				 output += "<td>" + amount + "</td>"; 
				 output += "<td>" + cardNumber + "</td>"; 
 // buttons
				 output += "<td><input name='btnUpdate' " + " type='button' value='Update'></td>" + "<td><form method='post' action='payment.jsp'>" + "<input name='btnRemove' " + " type='submit' value='Remove'>" + "<input name='payID' type='hidden' " + " value='" + payID + "'>" + "</form></td></tr>"; } con.close();
		          
		 
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the inquiry."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 

	public String updatePayment( String payID, String customerName, String amount, String cardNumber) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE inquiry SET customerName=?,amount=?,cardNumber=? WHERE payID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
		 
			preparedStmt.setString(2, customerName); 
			preparedStmt.setString(3, amount); 
			preparedStmt.setString(4, cardNumber); 
			preparedStmt.setInt(5, Integer.parseInt(payID)); 
 // execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the inquiry."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	public String deletePayment(String payID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from inquiry where payID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the inquiry."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
}