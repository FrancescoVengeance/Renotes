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
import utility.EmailManager;
import utility.PasswordManager;

@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 12201325554L;
	
    public ModifyUserServlet() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		User user = DBManager.getInstance().getUserDao().findByPrimaryKey((String) session.getAttribute("Mail"));
		
		DBManager.getInstance().getUserDao().modifyUser(user, PasswordManager.getMD5(request.getParameter("password")));
		EmailManager.passwordModified(user);
		
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}
}
