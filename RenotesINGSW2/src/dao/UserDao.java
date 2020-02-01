package dao;

import java.io.InputStream;
import java.util.List;
import model.Ad;
import model.PaymentMethod;
import model.User;

public interface UserDao {
	
	public void save(User user); //Insert -Create
	public User findByPrimaryKey(String username, String mail);
	public User findByPrimaryKey(String username);
	public void update(User user);
	public void delete(User user);
	public String getVerificationCode(User user);
	public boolean getTwoFactorAutenticationActivated(User user);
	public void setVerificationCode(User user, String code);
	public void modifyUser(User user, String password);
	public void modifyImage(User user, InputStream image);
	public InputStream getUserImage(User user);
	
	public List<Ad> findBoughtAd(User user);
	public List<Ad> findManagedAd(User user);
	public List<PaymentMethod> findPaymentMethods(User user);
	
	public void insertBoughtAd(User user, Integer id);
	public void deleteBoughtAd(User user, Integer id);
	
	public void insertManagedAd(User user, Integer id);
	public void deleteManagedAd(User user, Integer id);
	
	public void insertPaymentMethods(User user, String cardNumber);
	public void deletePaymentMethods(User user, String cardNumber);
}
