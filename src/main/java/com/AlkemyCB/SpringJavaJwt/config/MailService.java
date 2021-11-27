package com.AlkemyCB.SpringJavaJwt.config;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;





@Service
public class MailService {
	

	 
		@Autowired
	    SendGrid sendGrid;

	    
//IMPLEMENTACION DE SENGRID
	   
	    	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	    	
	    	public void sendTextEmail(String email) throws IOException {
	    		// the sender email should be the same as we used to Create a Single Sender Verification
	    		    Email from = new Email("carolinagomez.tp@live.com.ar");
	    		    String subject = "REGISTRACION OK";
	    		    Email to = new Email(email);
	    		    Content content = new Content("text/plain", "BIENVENIDO");
	    		    Mail mail = new Mail(from, subject, to, content);
	    		    mail.setReplyTo(new Email(email));
	    		
	    		 //   SendGrid sg = new SendGrid("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");
	    		    Response response=null;
	    		    Request request = new Request();
	    		    try {
	    		      request.setMethod(Method.POST);
	    		      request.setEndpoint("mail/send");
	    		      request.setBody(mail.build());
	    		      response=this.sendGrid.api(request);
	    		      logger.info(response.getBody());
	    		     response.getBody();	     
	    		    } catch (IOException ex) {
	    		      throw ex;
	    		    }	   
	    	}
	    }


