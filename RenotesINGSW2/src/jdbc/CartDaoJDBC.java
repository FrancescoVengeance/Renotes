package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CartDao;
import dao.DataSource;
import model.Ad;
import model.Cart;

public class CartDaoJDBC implements CartDao {

	private DataSource dataSource;
	
	public CartDaoJDBC(DataSource ds) 
	{
		dataSource = ds;
	}
	
	public void save(Cart cart) {
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			String insert = "insert into carrello(totale,data) values(?,?)";
			
			PreparedStatement stm = connection.prepareStatement(insert);
			
			stm.setDouble(1, cart.getTotal());
			stm.setDate(2, new Date(cart.getDate().getTime()));
			
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
	
	//NON VIENE SETTATA LA LISTA DI INSERZIONI
	public Cart findByPrimaryKey(Integer id) {
		Cart cart = null;
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			
			String query = "select * from carrello where carrello_id=?";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			
			ResultSet result = stm.executeQuery();
			
			if(result.next())
			{
				cart = new Cart();
				cart.setId(result.getInt("carrello_id"));
				cart.setTotal(result.getDouble("totale"));
				cart.setDate(result.getDate("data"));
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
		
		//cart.setAds(listOfAds(cart));
		
		return cart;
	}

	public void update(Cart cart) {
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			
			String update = "update carrello SET totale=?, data=? where carrello_id=?";
			PreparedStatement stm = connection.prepareStatement(update);
			
			stm.setDouble(1, cart.getTotal());
			stm.setDate(2, new Date(cart.getDate().getTime()));
			stm.setInt(3, cart.getId());
			
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
	
	//ESSENDO UNA RELAZIONE 1 AD 1 CON L'UTENTE LA DELETE HA SENSO QUANDO L'UTENTE NON ESISTE PIÙ
	public void delete(Cart cart) {
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			String delete = "delete from carrello where carrello_id = ? ";
			PreparedStatement stm = connection.prepareStatement(delete);
			
			stm.setInt(1, cart.getId());
			
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

	public void insertAd(Integer cartId, Integer adId) {
		Connection  connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			String insert = "insert into inserzioni_nel_carrello(inserzione,carrello) values(?,?)";
			PreparedStatement stm = connection.prepareStatement(insert);
			
			stm.setInt(1, adId);
			stm.setInt(2, cartId);
			
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

	public void deleteAd(Integer cartId, Integer adId) {
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			String delete = "delete from inserzioni_nel_carrello where inserzione=? and carrello = ?";
			PreparedStatement stm = connection.prepareStatement(delete);
			
			stm.setInt(1, cartId);
			stm.setInt(2, adId);
			
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
	
	//LE ad ALL'INTERNO DELLA LISTA HANNO SOLO L'id ASSEGNATO
	public List<Ad> listOfAds(Cart cart) {
		
		List<Ad> ads = new ArrayList<Ad>();
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			String query = "select inserzione from inserzioni_nel_carrello where carrello=?";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setInt(1, cart.getId());
			
			ResultSet result = stm.executeQuery();
			
			while(result.next())
			{
				Ad ad = new Ad();
				
				ad.setId(result.getInt("inserzione"));
				
				ads.add(ad);
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
		return ads;
	}

}
