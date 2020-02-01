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
import utility.EmailManager;
import utility.PasswordManager;

@WebServlet("/LoginSevlet")
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 555521455256668L;
    
    public LoginSevlet() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//request.getRequestDispatcher("twofactor.jsp").forward(request, response);
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
				
				request.setAttribute("mail", utente.getMail());
				DBManager.getInstance().getUserDao().setVerificationCode(utente, Integer.toString(EmailManager.sendTwoFactorAutenticationCode(utente)));
				//EmailManager.sendTwoFactorAutenticationCode(utente);
				RequestDispatcher rd = request.getRequestDispatcher("twofactor.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("scrittaErrore", "Mail o password errate");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		else
		{
			request.setAttribute("scrittaErrore", "Mail o password errate");
			request.getRequestDispatcher("index.jsp").forward(request, response);;
		}
	}

}
