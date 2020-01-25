package dao;

import model.Preview;

public interface PreviewDao {

	public void save(Preview preview); //Insert -Create
	public Preview findByPrimaryKey(Integer id);
	public void update(Preview preview);
	public void delete(Preview preview);
	
}
