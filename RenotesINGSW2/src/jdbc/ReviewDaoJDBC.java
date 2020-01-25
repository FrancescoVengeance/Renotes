package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DataSource;
import dao.ReviewDao;
import model.Ad;
import model.Review;
import model.User;


public class ReviewDaoJDBC implements ReviewDao {

	private DataSource dataSource;
	
	public ReviewDaoJDBC(DataSource ds)
	{
		dataSource = ds;
	}

	public void save(Review review) {
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			String insert = "insert into valutazione(qualita,attendibilita,completezza,commento,inserzione,utente_username,utente_mail) values(?,?,?,?,?,?,?)";
			PreparedStatement stm = connection.prepareStatement(insert);
			
			stm.setInt(1, review.getQuality());
			stm.setInt(2, review.getReliability());
			stm.setInt(3, review.getCompleteness());
			stm.setString(4, review.getComment());
			stm.setInt(5, review.getAd().getId());
			stm.setString(6, review.getUser().getUsername());
			stm.setString(7, review.getUser().getMail());
			
			stm.executeUpdate();
			
		} 
		catch (SQLException e) {
			if(connection != null)
			{
				try {
					connection.rollback();
				} catch (SQLException e1) {
					
					throw new RuntimeException(e.getMessage());
				}
			}	
			
		}
		finally
		{
			try {
				connection.close();
			} 
			catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}

	public Review findByPrimaryKey(Integer id) {
		Review review = null;
		Connection connection=null;
		
		try {
			connection = dataSource.getConnection();
			
			PreparedStatement stm;
			String query = "select * from valutazione where valutazione_id=?";
			stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			
			ResultSet result = stm.executeQuery();
			
			if(result.next())
			{
				review = new Review();
				review.setId(result.getInt("valutazione_id"));
				review.setQuality(result.getInt("qualita"));
				review.setReliability(result.getInt("attendibilita"));
				review.setCompleteness(result.getInt("completezza"));
				review.setComment(result.getString("commento"));
				
				Ad ad = new Ad();
				ad.setId(result.getInt("inserzione"));
				
				review.setAd(ad);
				
				User user = new User();
				user.setUsername(result.getString("utente_username"));
				user.setMail(result.getString("utente_mail"));
				
				review.setUser(user);
			}
			
		} 
		catch (SQLException e) {
			if(connection != null)
			{
				try {
					connection.rollback();
				} catch (SQLException e1) {
					
					throw new RuntimeException(e.getMessage());
				}
			}	
			
		}
		finally
		{
			try {
				connection.close();
			} 
			catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return review;
	}

	public void update(Review review) {
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			String update = "update valutazione SET qualita=?, attendibilita=?, completezza=?, commento=?, inserzione=?, utente_username=?, utente_mail=? WHERE valutazione_id=?";
			PreparedStatement stm = connection.prepareStatement(update);
			
			stm.setInt(1, review.getQuality());
			stm.setInt(2, review.getReliability());
			stm.setInt(3, review.getCompleteness());
			stm.setString(4, review.getComment());
			stm.setInt(5, review.getAd().getId());
			stm.setString(6, review.getUser().getUsername());
			stm.setString(7, review.getUser().getMail());
			stm.setInt(8, review.getId());
			
			stm.executeUpdate();
			
		} 
		catch (SQLException e) {
			if(connection != null)
			{
				try {
					connection.rollback();
				} catch (SQLException e1) {
					
					throw new RuntimeException(e.getMessage());
				}
			}	
		}
		finally
		{
			try {
				connection.close();
			} 
			catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}

	public void delete(Review review) {
		// TODO Auto-generated method stub
		
	}
}
