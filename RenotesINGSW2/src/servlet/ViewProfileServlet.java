package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DBManager;
import model.Ad;
import model.Review;
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
		
		//InputStream image = DBManager.getInstance().getUserDao().getUserImage(utente);
		
		/*if(image != null)
		{
			System.out.println(image);
			
		}*/
		
		calculateUserRate(utente);

		ArrayList<String> dati = new ArrayList<String>();
		dati.add((String)session.getAttribute("Nome"));
		dati.add((String)session.getAttribute("Mail"));
		dati.add(String.valueOf(utente.getRate()));
		
		Gson gson = new Gson();
		String json = gson.toJson(dati);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	private void calculateUserRate(User user) {
		//PER SFRUTTARE TALE CALCOLO HO AGGIUNTO LA COLONNA RATE(INTEGER-NOT NULL-DEFAULT: 0) AL DB
		//E AGGIORNATO LE QUERY NELL'USERDAOJDBC TENENDO CONTOO DELLA NUOVA COLONNA
		//TALE ALGORITMO È DA USARE NELLA SERVLET CHE PARTE QUANDO SI ACCEDE A "IL MIO PROFILO"
		
		//RECUPERO LE INSERZIONI CHE HA MESSO IN VENDITA
		List<Ad> adsManaged = DBManager.getInstance().getUserDao().findManagedAd(user);
		List<Double> valueReviews = new ArrayList<Double>();
		
		//PER OGNI INSERZIONE SOMMO I PARAMETRI DI UNA SINGOLA VALUTAZIONE E NE FACCIO LA MEDIA.
		//AGGIUNGO QUESTA MEDIA IN UNA LISTA DI VALUTAZIONI
		for(Ad ad: adsManaged)
		{	
			List<Review> reviewTemp =  DBManager.getInstance().getAdDao().findReview(ad.getId());
			
			for(Review rev: reviewTemp)
			{
				double media = (rev.getQuality() + rev.getReliability() + rev.getCompleteness())/3;
				valueReviews.add(media);
			}
		}
		
		//SOMMO TUTTE LE MEDIE
		double userRateTemp = 0;
		for(Double value: valueReviews)
			userRateTemp += value;
		
		//FACCIO LA MEDIA DELLE MEDIE (AHAH)
		if(valueReviews.size()>0)
			userRateTemp /=  valueReviews.size();
		
		//CASTO AD INT PER UNA QUESTIONE GRAFICA(LE STELLE INTERE) E DI DB
		int userRate = (int) userRateTemp;
		
		//IL RANGE DI STELLE VA DA 0 A 10.
		//SE LA VALUTAZIONE DELL'UTENTE ECCEDE 10 ALLORA LO RIPORTO AL VALORE MASSIMO
		if(userRate > 10)
			userRate = 10;
		
		//FACCIO L'UPDATE DELL'USER COSÌ DA SALVARE IL NUOVO RATE NEL DB
		DBManager.getInstance().getUserDao().update(user);
		
		//IMPOSTO IL RATE NELL'USER SESSION (POTREBBE ANCHE ESSER SUPERFLUO PER ORA)
		user.setRate(userRate);
	}

}
