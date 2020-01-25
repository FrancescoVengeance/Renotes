package utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Cart;
import model.User;

public class EmailManager 
{
	public static void main(String[] args) 
	{
		User user = new User();
		user.setUsername("Francuzzu1");
		user.setMail("spsfnc98s12d005a@studenti.unical.it");
		Cart cart = new Cart();
		cart.setId(0);
		user.setCart(cart);
		
		
	}
	/*private static final String from = "noreplyrenotes12@gmail.com";
	private static final String password = "33851242Re";
	
	public static void main(String[] args) 
	{
		User user = new User();
		user.setUsername("Francuzzu1");
		user.setMail("spsfnc98s12d005a@studenti.unical.it");
		Cart cart = new Cart();
		cart.setId(0);
		user.setCart(cart);
		
		EmailManager.registerValidation(user);
	}
	
	public static void registerValidation(User user)
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(from, password);
			}
		});
        
		String text = "Ciao " + user.getUsername() + ",\n" +
				"benvenuto su Renotes!!\n"+
				"La tua registrazione è stata completata, buon divertimento!";
		
		Message msg = prepareMessage(session,user.getMail(), text);
		try 
		{
			Transport.send(msg);
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected static Message prepareMessage(Session session, String mailTo, String text)
	{
		
		Message message = new MimeMessage(session);
		try 
		{
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
			message.setSubject("Benvenuto su Renotes");
			message.setText(text);
		} 
		catch (AddressException e) 
		{
			e.printStackTrace();
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		
		
		
		return null;
	}*/
}
