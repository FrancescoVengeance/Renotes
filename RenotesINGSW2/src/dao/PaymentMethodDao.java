package dao;

import model.PaymentMethod;

public interface PaymentMethodDao {
	
	public void save(PaymentMethod paymentMethod); //Insert -Create
	public PaymentMethod findByPrimaryKey(String cardNumber);
	public void update(PaymentMethod paymentMethod);
	public void delete(PaymentMethod paymentMethod);
}
