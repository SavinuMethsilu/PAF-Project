package com; 
import model.Payment; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Payment") 

public class PaymentService 
	{ 
		Payment PaymentObj = new Payment(); 

	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPayment() 
	{ 
		return PaymentObj.readPayment(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPayment(@FormParam("payID") String payID, 
			@FormParam("customerName") String customerName, 
			@FormParam("amount") String amount, 
			@FormParam("cardNumber") String cardNumber) 
	{ 
	
		String output = PaymentObj.insertPayment(payID, customerName, amount, cardNumber); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePayment(String PaymentData) 
	{ 
	//Convert the input string to a JSON object 
		JsonObject PaymentObject = new JsonParser().parse(PaymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 	String PayID = PaymentObject.get("PayID").getAsString();
	 	String customerName = PaymentObject.get("customerName").getAsString(); 
	 	String amount = PaymentObject.get("amount").getAsString(); 
	 	String cardNumber = PaymentObject.get("cardNumber").getAsString(); 
	 	
	 	String output = PaymentObj.updatePayment(PayID, customerName, amount, cardNumber); 
	
	 	return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePayment(String PaymentData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String PayID = doc.select("PayID").text(); 
	 String output = PaymentObj.deletePayment(PayID); 
	return output; 
	}

}