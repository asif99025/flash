import java.io.FileReader;

//added first line here
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;    
import javax.mail.internet.*;


/*class Mailer{  
    public static void send(String from,String password,String to,String sub,String msg) throws IOException{  
          //Get properties object  
    	FileReader reader=new FileReader("mail.properties");

    	sub="Subject line";
    	msg ="Since the default session is potentially available to all code executing in the same Java virtual machine, and the session can contain security sensitive information such as user names and passwords, access to the default session is restricted. The Authenticator object, which must be created by the caller, is used indirectly to check access permission. The Authenticator object passed in when the session is created is compared with the Authenticator object passed in to subsequent requests to get the default session. If both objects are the same, or are from the same ClassLoader, the request is allowed. Otherwise, it is denied.";
    	Properties p=new Properties();
    	p.load(reader);
          Properties props = new Properties();  
          //props.load(reader);
          System.out.println(p.getProperty("name"));
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session 

          System.out.println(props.getProperty("mail.smtp.host"));
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);    

           //            https://www.google.com/settings/security/lesssecureapps
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    

    }  
}  */
public class MailSender{    
	public static void main(String[] args) throws IOException {    
		/*    //from,password,to,subject,message  
     Mailer.send("asif99025@gmail.com","9902538773","asif99025@gmail.com","hello javatpoint","How r u?");  
     //change from, password and to  
 }  */
		String from;String password;String to;String sub;String msg ;
		StringBuilder finalmessage=new StringBuilder();
		//Get properties object  
		FileReader reader=new FileReader("mail.properties");
		sub="Subject line";
		Properties p=new Properties();
		p.load(reader);
		Properties props = new Properties();  
		//props.load(reader);
		msg=p.getProperty("msg");
		from=p.getProperty("from");
		to=p.getProperty("to");
		password=p.getProperty("password");
		props.put("mail.smtp.host", "smtp.gmail.com");    
		props.put("mail.smtp.socketFactory.port", "465");    
		props.put("mail.smtp.socketFactory.class",    
				"javax.net.ssl.SSLSocketFactory");    
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", "465");    
		//get Session 

		System.out.println(props.getProperty("mail.smtp.host"));
		Session session = Session.getDefaultInstance(props,    
				new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(from,password);  
			}    
		});    

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String timenow=dtf.format(now);
		finalmessage=finalmessage.append(msg).append(timenow);
		System.out.println(timenow);
		//compose message    
		try { 
			MimeMessage message = new MimeMessage(session);    
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
			message.setSubject(sub);    
			// message.setText(finalmessage.toString()); 
			BodyPart messageBodyPart=new MimeBodyPart();
			messageBodyPart.setText(finalmessage.toString());
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			String filename = "example.txt";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			//send message  
			message.setContent(multipart);
			Transport.send(message);    
			//            https://www.google.com/settings/security/lesssecureapps
			System.out.println("message sent successfully");    
		} catch (MessagingException e) {throw new RuntimeException(e);}    

	} 
}    