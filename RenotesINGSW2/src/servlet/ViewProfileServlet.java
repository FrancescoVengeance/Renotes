package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DBManager;
import model.User;

@WebServlet("/ViewProfileServlet")
public class ViewProfileServlet extends HttpServlet 
{
	private static final long serialVersionUID = 156532333221445796L;
       
    public ViewProfileServlet() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		User utente = DBManager.getInstance().getUserDao().findByPrimaryKey((String) session.getAttribute("Mail"));
		ArrayList<String> dati = new ArrayList<String>();
		dati.add(utente.getUsername());
		dati.add(utente.getMail());
		
		Gson gson = new Gson();
		String json = gson.toJson(dati);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
