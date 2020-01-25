package utility;

import dao.DBManager;
import dao.UserDao;
import jdbc.UserDaoJDBC;
import model.Cart;
import model.User;

public class ProvaLogin 
{
	public static void main(String[] args) 
	{
		User utente = new User();
		utente.setPassword(PasswordManager.getMD5("ciaocomestai"));
		utente.setUsername("Ciccio");
		utente.setMail("ciccio.fazio@gmail.com");
		Cart c = new Cart();
		c.setId(2);
		utente.setCart(c);
		//DBManager.getInstance().getUserDao().save(utente);
		
		User utente2 = DBManager.getInstance().getUserDao().findByPrimaryKey(utente.getMail());
		
		if(utente2.getPassword().equals(utente.getPassword()))
		{
			System.out.println("Login!!!!");
		}
		else
		{
			System.out.println("Login failed");
		}
	}
}
