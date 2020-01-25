package dao;

import java.util.List;

import model.Ad;
import model.Cart;


public interface CartDao {
	public void save(Cart cart); //Insert -Create
	public Cart findByPrimaryKey(Integer id);
	public void update(Cart cart);
	public void delete(Cart cart);
	
	public void insertAd(Integer cartId, Integer adId);
	public void deleteAd(Integer cartId, Integer adId);
	
	public List<Ad> listOfAds(Cart cart); 
	

}
