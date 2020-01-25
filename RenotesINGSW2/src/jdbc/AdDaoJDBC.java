package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AdDao;
import dao.DataSource;
import model.Ad;
import model.Preview;
import model.Review;
import model.User;



public class AdDaoJDBC implements AdDao {
	
	private DataSource dataSource;
	
	public AdDaoJDBC(DataSource ds) {
		dataSource = ds;
	}


	public void save(Ad ad) {
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			String insert = "insert into inserzione(titolo,materia,universita,corso_di_laurea,descrizione,prezzo,file,anteprima) values(?,?,?,?,?,?,?,?)";
			
			PreparedStatement stm = connection.prepareStatement(insert);
			//LE COLONNE CON IL serial NON NECESSITANO DI AGGIUNTA DEI VALORI TRAMITE QUERY.
			stm.setString(1, ad.getTitle());
			stm.setString(2, ad.getSubject());
			stm.setString(3, ad.getUniversity());
			stm.setString(4, ad.getDegreeCourse());
			stm.setString(5, ad.getDescription());
			stm.setDouble(6, ad.getPrice());
			stm.setString(7, ad.getFile());
			stm.setInt(8, ad.getPreview().getId());
			/*
			FileInputStream fis = null;
			try {
				if(ad.getFile() != null)
					fis = new FileInputStream( ad.getFile().getPath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			stm.setBinaryStream(7, fis);
			
			*/
			
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

	public Ad findByPrimaryKey(Integer id) {
		Ad ad = null;
		int previewId = 0;
		Connection connection=null;
		
		try {
			connection = dataSource.getConnection();
			
			PreparedStatement stm;
			String query = "select * from inserzione where inserzione_id=?";
			stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			
			ResultSet result = stm.executeQuery();
			
			if(result.next())
			{
				ad = new Ad();
				ad.setId(result.getInt("inserzione_id"));
				ad.setTitle(result.getString("titolo"));
				ad.setSubject(result.getString("materia"));
				ad.setUniversity(result.getString("universita"));
				ad.setDegreeCourse(result.getString("corso_di_laurea"));
				/*
				InputStream is =  result.getBinaryStream("file");
				ObjectInputStream in = null;
				try {
					in = new ObjectInputStream(is);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File file = null;
				try {
					file = (File) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				ad.setFile(result.getString("file"));
				ad.setPrice(result.getDouble("prezzo"));
				ad.setDescription(result.getString("descrizione"));
				previewId = result.getInt("anteprima");	
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
		//commento
		finally
		{
			try {
				connection.close();
			} 
			catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		PreviewDaoJDBC previewDaoJDBC = new PreviewDaoJDBC(dataSource);
		
		Preview preview = previewDaoJDBC.findByPrimaryKey(previewId);
		
		ad.setPreview(preview);
		
		return ad;
	}

	public void update(Ad ad) {
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			String update = "update inserzione SET titolo=?, materia=?, universita=?, corso_di_laurea=?, descrizione=?, prezzo=?, file=?, anteprima=? WHERE inserzione_id=?";
			
			PreparedStatement stm = connection.prepareStatement(update);
			
			stm.setString(1, ad.getTitle());
			stm.setString(2, ad.getSubject());
			stm.setString(3, ad.getUniversity());
			stm.setString(4, ad.getDegreeCourse());
			stm.setString(5, ad.getDescription());
			stm.setDouble(6, ad.getPrice());
			stm.setString(7, ad.getFile());
			stm.setInt(8, ad.getPreview().getId());
			stm.setInt(9, ad.getId());
			
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

	public void delete(Ad ad) {
		//È NECESSARIO AGGIUNGERE UNA booleana DISPONIBILE/NONDISPONIBILE.
		//UTILE QUANDO UN VENDITORE ELIMINA UNA SUA INSERZIONE, ALCUNE PERSONE L'HANNO ACQUISTATAT QUINDI NON SI PUÒ WLIMINARE DAL DB
		//MA ALLO STESSO TEMPO NON PUÒ PIÙ COMPARIRE SUL SITO QUINDI DIVENTA NONDISPONIBILE
		
	}

	public List<Review> findReview(Integer adId) {
		List<Review> reviews = new ArrayList<Review>();
		
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			
			PreparedStatement stm;
			String query = "select * from valutazione where inserzione=?";
			stm = connection.prepareStatement(query);
			stm.setInt(1, adId);
			
			ResultSet result = stm.executeQuery();
			
			while(result.next())
			{
				Review review = new Review();
	
				review.setId(result.getInt("valutazione_id"));
				review.setQuality(result.getInt("qualita"));
				review.setReliability(result.getInt("attendibilita"));
				review.setCompleteness(result.getInt("completezza"));
				review.setComment(result.getString("commento"));
				
				Ad ad = new Ad();
				ad.setId(adId);
				
				review.setAd(ad);
				
				User user = new User();
				user.setUsername(result.getString("utente_username"));
				user.setMail(result.getString("utente_mail"));
				
				review.setUser(user);
				
				reviews.add(review);
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
		
		return reviews;
	}


	public User findUserCreator(Integer adId) {
		User user = null;
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			
			String query = "select utente_username, utente_mail from relazione_utente_inserzione where inserzione=? and e_acquistata=false";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setInt(1, adId);
			
			ResultSet result = stm.executeQuery();
			
			if(result.next())
			{
				user = new User();
				user.setUsername(result.getString("utente_username"));
				user.setMail(result.getString("utente_mail"));
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
		
		return user;
	}
}
