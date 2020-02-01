package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import model.User;

@MultipartConfig
@WebServlet("/ModifyUserImage")
public class ModifyUserImage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public ModifyUserImage() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		User user = DBManager.getInstance().getUserDao().findByPrimaryKey((String)session.getAttribute("Mail"));
		//System.out.println(request.getPart("file").getSubmittedFileName());
		//user.setImage(request.getPart("file").getInputStream());
		DBManager.getInstance().getUserDao().modifyImage(user, request.getPart("file").getInputStream());
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

}
