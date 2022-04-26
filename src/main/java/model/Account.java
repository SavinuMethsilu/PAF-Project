package model; 
import java.sql.*; 
public class Account 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/account", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertAccount(String accountNumber, String accountName, String premisesID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into electricity (`accountID`,`accountNumber`,`accountName`,`premisesID`)"
 + " values (?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, accountNumber); 
 preparedStmt.setString(3, accountName); 
 preparedStmt.setString(4, premisesID); 
  
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String readItems() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Account Number</th>" +
 "<th>Account Name</th>" + 
 "<th>Premises ID</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from electricity"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String accountId = Integer.toString(rs.getInt("accountID")); 
 String accountNumber = rs.getString("accountNumber"); 
 String accountName = rs.getString("accountName"); 
 String premisesId = rs.getString("premisesID"); 
 // Add into the html table
 output += "<tr><td>" + accountNumber + "</td>"; 
 output += "<td>" + accountName + "</td>"; 
 output += "<td>" + premisesId + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='accountId' type='hidden' value='" + accountId
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the items."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updateAccount(String accountID, String accountNumber, String accountName, String premisesID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE electricity SET accountNumber=?,accountName=?,premisesID=? WHERE accountID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, accountNumber); 
 preparedStmt.setString(2, accountName);  
 preparedStmt.setString(3, premisesID); 
 preparedStmt.setInt(4, Integer.parseInt(accountID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while updating the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String deleteAccount(String accountID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from electricity where accountID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(accountID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while deleting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
} 

