package model;

import java.util.List;

public class Ad {

	private int id;
	private String title;
	private String subject;
	private String university;
	private String degreeCourse;
	private String description;
	private double price;
	private String file;
	private Preview preview; 
	
	public Preview getPreview() {
		return preview;
	}
	public void setPreview(Preview preview) {
		this.preview = preview;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getDegreeCourse() {
		return degreeCourse;
	}
	public void setDegreeCourse(String degreeCourse) {
		this.degreeCourse = degreeCourse;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	public int[] getStat(List<Review> reviews)
	{
		int statistics[] = {0,0,0};
		
		for(Review r: reviews)
		{
			statistics[0] += r.getQuality(); 
			statistics[1] += r.getReliability(); 
			statistics[2] += r.getCompleteness(); 
		}
		
		for(int i=0; i<3; i++)
			statistics[i] /= reviews.size();
		
		return statistics;
	}
}
