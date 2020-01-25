package dao;

import model.Review;

public interface ReviewDao {

	public void save(Review review); //Insert -Create
	public Review findByPrimaryKey(Integer id);
	public void update(Review review);
	public void delete(Review review);
}
