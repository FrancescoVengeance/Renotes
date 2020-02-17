	package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import dao.DBManager;
import model.Cart;
import model.User;
import utility.EmailManager;
import utility.PasswordManager;

@WebServlet("/GoogleLogin")
public class GoogleLogin extends HttpServlet {
	private static final long serialVersionUID = 134245463765L;
	
    public GoogleLogin() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		System.out.println("Google");
		JsonElement json = new Gson().fromJson(request.getReader(), JsonElement.class);
		String mail = json.toString();
		mail = mail.substring(1, mail.length() - 1);
		//System.out.println(mail);
		User user = DBManager.getInstance().getUserDao().findByPrimaryKey(mail);
		if(user == null)
		{
			user = new User();
			user.setMail(mail);
			user.setUsername(mail);
			user.setPassword(PasswordManager.getMD5("ciccoFazio"));
			Cart cart = new Cart();
			cart.setId(2);
			user.setCart(cart);
			
			DBManager.getInstance().getUserDao().save(user);
			EmailManager.registerValidation(user);
			HttpSession session = request.getSession();
			session.setAttribute("Mail", mail);
			session.setAttribute("Nome", user.getUsername());
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("Mail", mail);
			session.setAttribute("Nome", user.getUsername());
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}
		
	}
}
