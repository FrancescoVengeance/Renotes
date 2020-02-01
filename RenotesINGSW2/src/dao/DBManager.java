package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.AdDaoJDBC;
import jdbc.CartDaoJDBC;
import jdbc.PaymentDaoJDBC;
import jdbc.PreviewDaoJDBC;
import jdbc.ReviewDaoJDBC;
import jdbc.UserDaoJDBC;
import model.Ad;
import model.Preview;


public class DBManager 
{
	
	private static  DataSource dataSource;

	static {
		try 
		{
			Class.forName("org.postgresql.Driver");
			dataSource = new DataSource("jdbc:postgresql://renotes.cd53zwl9jl0o.us-east-2.rds.amazonaws.com:5432/postgres","xxxxx","xxxxxx");

		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}
	
	public static DBManager instance = null;
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager()	
	{}
	
	public AdDao getAdDao()
	{
		return new AdDaoJDBC(dataSource);
	}
	
	public UserDao getUserDao()
	{
		return new UserDaoJDBC(dataSource);
	}
	
	public void insertAd(Ad ad)
	{
		this.getAdDao().save(ad);
	}
	
	public PreviewDao getPreviewDao()
	{
		return new PreviewDaoJDBC(dataSource);
	}
	
	public void insertPreview(Preview preview)
	{
		this.getPreviewDao().save(preview);
	}
	
	public ReviewDaoJDBC getReviewDao()
	{
		return new ReviewDaoJDBC(dataSource);
	}
	
	public CartDao getCartDao()
	{
		return new CartDaoJDBC(dataSource);
	}
	
	
	public PaymentMethodDao getPaymentMethodDao()
	{
		return new PaymentDaoJDBC(dataSource);
	}
	
	public void findCarrelloProva() {
		Connection connection = null;
		
		try {
			connection = DBManager.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select * from carrello";
			statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
				Integer carrelloId = (Integer) result.getObject(1);
				Double totale = (Double) result.getObject(2);
				Date data = (Date) result.getObject(3);
				
				System.out.println("CarrelloId: " + String.valueOf(carrelloId) + " Totale: " + String.valueOf(totale) + " Data: " + String.valueOf(data));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	//PARTE DA 2 COSÌ LASCIO SEMPRE UN CARRELLO CUI REFERENZIA L'USER
	public void resetSerialPreview() {
		Connection connection = null;
		
		try {
			connection = DBManager.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select setval('\"anteprima_anteprimaId_seq\"',2,false)";
			statement = connection.prepareStatement(query);

			statement.executeQuery();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
	}
	
	public void resetSerialAd() {
		Connection connection = null;
		
		try {
			connection = DBManager.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select setval('\"Inserzione_inserioneId_seq\"',1,false)";
			statement = connection.prepareStatement(query);

			statement.executeQuery();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
	}
	
	public void resetSerialCart() {
		Connection connection = null;
		
		try {
			connection = DBManager.dataSource.getConnection();
			PreparedStatement statement;
			String query = "select setval('\"carrello_carrelloId_seq\"',1,false)";
			statement = connection.prepareStatement(query);

			statement.executeQuery();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
	}
}
