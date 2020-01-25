package model;

public class Review {
	
	private int id;
	private int quality;
	private int reliability;
	private int completeness;
	private String comment;
	private Ad ad;
	private User user;
	
	
	public Ad getAd() {
		return ad;
	}
	
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuality() {
		return quality;
	}
	
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getReliability() {
		return reliability;
	}
	public void setReliability(int reliability) {
		this.reliability = reliability;
	}
	public int getCompleteness() {
		return completeness;
	}
	public void setCompleteness(int completeness) {
		this.completeness = completeness;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

}