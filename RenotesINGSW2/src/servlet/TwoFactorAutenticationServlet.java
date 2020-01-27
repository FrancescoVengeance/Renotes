package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import model.User;

@WebServlet("/TwoFactorAutenticationServlet")
public class TwoFactorAutenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 18859485264972366L;
	
    public TwoFactorAutenticationServlet() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//request.getRequestDispatcher("twofactor.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String code = (String) request.getParameter("verification-code");
		User utente = DBManager.getInstance().getUserDao().findByPrimaryKey((String)session.getAttribute("Mail"));
		
		if(DBManager.getInstance().getUserDao().getVerificationCode(utente).equals(code))
		{
			request.getRequestDispatcher("qualcosa.html").forward(request, response);
		}
	}
}
