package com;

import model.Account; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Accounts") 

public class AccountService {
	Account itemObj = new Account(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return itemObj.readItems();  
	 }
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertAccount(@FormParam("accountNumber") String accountNumber, 
	 @FormParam("accountName") String accountName, 
	 @FormParam("premisesID") String premisesID)
	{ 
	 String output = itemObj.insertAccount(accountNumber, accountName, premisesID); 
	return output; 
	}
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateAccount(String accountData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(accountData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String accountID = itemObject.get("accountID").getAsString(); 
	 String accountNumber = itemObject.get("accountNumber").getAsString(); 
	 String accountName = itemObject.get("accountName").getAsString(); 
	 String premisesID = itemObject.get("premisesID").getAsString(); 
	 String output = itemObj.updateAccount(accountID, accountNumber, accountName, premisesID); 
	return output; 
	}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAccount(String accountData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(accountData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <accountID>
	 String accountID = doc.select("accountID").text(); 
	 String output = itemObj.deleteAccount(accountID); 
	return output; 
	}

}
