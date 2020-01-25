package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import model.User;
import utility.PasswordManager;

@WebServlet("/LoginSevlet")
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 555521455256668L;
    
    public LoginSevlet() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//impossibile contattare il metodo get su un login
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String mail = (String) request.getParameter("mail-accesso");
		String password = (String) request.getParameter("password-accesso");
		User utente = DBManager.getInstance().getUserDao().findByPrimaryKey(mail);
		if(utente != null && utente.getMail().equals(mail))
		{
			if(PasswordManager.getMD5(password).equals(utente.getPassword()))
			{
				HttpSession session = request.getSession();
				session.setAttribute("Nome", utente.getUsername());
				session.setAttribute("Mail", utente.getMail());
				
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("form.html");
				rd.forward(request, response);
			}
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("form.html");
			rd.forward(request, response);
		}
	}

}
