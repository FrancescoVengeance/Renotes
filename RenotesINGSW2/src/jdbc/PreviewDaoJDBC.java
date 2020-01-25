package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.DataSource;
import dao.PreviewDao;
import model.Preview;


public class PreviewDaoJDBC implements PreviewDao {

	private DataSource dataSource;
	
	public PreviewDaoJDBC(DataSource ds)
	{
		dataSource = ds;
	}
	
	public void save(Preview preview) {
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			String insert = "insert into anteprima(immagine) values(?)";
			
			PreparedStatement stm = connection.prepareStatement(insert);
			//stm.setInt(1, nextval("inserzioneId")); //id seriale
			/*
			FileInputStream fis = null;
			try {
				if(preview.getImage() != null)
					fis = new FileInputStream( preview.getImage().getDescription());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			stm.setBinaryStream(1, fis);
			*/
			stm.setString(1, preview.getImage());
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

	public Preview findByPrimaryKey(Integer id) {
		Preview preview = null;
		Connection connection=null;
		
		try {
			connection = dataSource.getConnection();
			
			PreparedStatement stm;
			String query = "select * from anteprima where anteprima_id=?";
			stm = connection.prepareStatement(query);
			stm.setInt(1, id);
			
			ResultSet result = stm.executeQuery();
			
			if(result.next())
			{
				preview = new Preview();
				preview.setId(result.getInt("anteprima_id"));
				
				/*
				InputStream is =  result.getBinaryStream("file");
				ObjectInputStream in = null;
				try {
					in = new ObjectInputStream(is);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon img = null;
				try {
					img = (ImageIcon) in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				preview.setImage(img);	*/
				preview.setImage(result.getString("immagine"));
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
		
		return preview;
	}

	public void update(Preview preview) {
		Connection connection = null;
		
		try 
		{
			connection = dataSource.getConnection();
			String update = "update anteprima SET immagine=? WHERE anteprima_id=?";
			
			PreparedStatement stm = connection.prepareStatement(update);
			stm.setString(1, preview.getImage());
			stm.setInt(2, preview.getId());
			
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
	
	//NON SEMBRA AVERE UTILITÀ 
	public void delete(Preview preview) {
		// TODO Auto-generated method stub
		
	}
}
