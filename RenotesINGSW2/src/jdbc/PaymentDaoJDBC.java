package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DataSource;
import dao.PaymentMethodDao;
import model.PaymentMethod;


public class PaymentDaoJDBC implements PaymentMethodDao{
	private DataSource dataSource;
	
	public PaymentDaoJDBC (DataSource ds) {
		dataSource=ds;
	}
	
	public void save(PaymentMethod paymentMethod) {
		Connection connection=null;
		try {
			connection=dataSource.getConnection();
			String insert="insert into metodo_di_pagamento(numero_carta, intestatario, saldo, data_scadenza, cvc, predefinito) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement stm=connection.prepareStatement(insert);
			stm.setString(1, paymentMethod.getCardNumber());
			stm.setString(2, paymentMethod.getOwner());
			stm.setDouble(3, paymentMethod.getBalance());
			stm.setDate(4, new Date(paymentMethod.getExpiryDate().getTime()));
			stm.setInt(5, paymentMethod.getCvc());
			stm.setBoolean(6, paymentMethod.isDefault());
			stm.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public PaymentMethod findByPrimaryKey(String cardNumber) {
		Connection connection=null;
		PaymentMethod payment=null;
		try {
			PreparedStatement stm=null;
			connection=dataSource.getConnection();
			String query="select * from metodo_di_pagamento where numero_carta=?";
			stm = connection.prepareStatement(query);
			stm.setString(1, cardNumber);
			ResultSet result=stm.executeQuery();
			if(result.next()) {
				payment=new PaymentMethod();
				payment.setCardNumber(result.getString("numero_carta"));
				payment.setOwner(result.getString("intestatario"));
				payment.setBalance(result.getDouble("saldo"));
				payment.setExpiryDate(result.getDate("data_scadenza"));
				payment.setCvc(result.getInt("cvc"));
				payment.setDefault(result.getBoolean("predefinito"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return payment;
	}
	
	public void update(PaymentMethod paymentMethod) {
		Connection connection=null;
		try {
			connection=dataSource.getConnection();
			String update="update metodo_di_pagamento SET numero_carta=?, intestatario=?, saldo=?, cvc=?, data_scadenza=?, predefinito=?";
			PreparedStatement stm= connection.prepareStatement(update);
			stm.setString(1, paymentMethod.getCardNumber());
			stm.setString(2, paymentMethod.getOwner());
			stm.setDouble(3, paymentMethod.getBalance());
			stm.setInt(4, paymentMethod.getCvc());
			stm.setDate(5, new Date(paymentMethod.getExpiryDate().getTime()));
			stm.setBoolean(6, paymentMethod.isDefault());
			
			stm.executeUpdate();
		} catch (SQLException e) {
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

	public void delete(PaymentMethod paymentMethod) {
		Connection connection = null;
		try {
			connection=dataSource.getConnection();
			String delete="delete FROM metodo_di_pagamento WHERE numero_carta=?";
			PreparedStatement stm=connection.prepareStatement(delete);
			stm.setString(1, paymentMethod.getCardNumber());
			stm.executeUpdate();
		} catch (SQLException e) {
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
	
}
