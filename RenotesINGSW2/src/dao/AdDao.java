package dao;

import java.util.List;

import model.Ad;
import model.Review;
import model.User;

public interface AdDao {
	
	public void save(Ad ad); //Insert -Create
	public Ad findByPrimaryKey(Integer id);
	public void update(Ad ad);
	public void delete(Ad ad);
	
	public List<Review> findReview(Integer adId);
	public User findUserCreator(Integer adId);

}
