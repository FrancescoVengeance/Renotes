package model;

import java.util.Date;
import java.util.List;

public class Cart {
	
	private int id;
	private double total;
	private Date date;
	private List<Ad> ads;
	
	public List<Ad> getAds() {
		return ads;
	}
	
	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
