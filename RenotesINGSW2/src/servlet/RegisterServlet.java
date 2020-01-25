package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DBManager;
import dao.UserDao;
import model.Cart;
import model.User;
import utility.PasswordManager;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1558663200014545L;
    
    public RegisterServlet() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UserDao utenteDao = DBManager.getInstance().getUserDao();
		if(utenteDao.findByPrimaryKey(request.getParameter("mail-registrazione")) == null)
		{
			User utente = new User();
			utente.setMail(request.getParameter("mail-registrazione"));
			utente.setUsername(request.getParameter("username-registrazione"));
			utente.setPassword(PasswordManager.getMD5(request.getParameter("password-registrazione")));
			Cart cart = new Cart();
			cart.setId(2);
			utente.setCart(cart);
			utenteDao.save(utente);
			//EmailManager.registerValidation(utente);
			//TODO aggiustare l'invio di email
			
			RequestDispatcher rd = request.getRequestDispatcher("buttons.html");
			rd.forward(request, response);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("form.html");
			rd.forward(request, response);
		}
	}

}
