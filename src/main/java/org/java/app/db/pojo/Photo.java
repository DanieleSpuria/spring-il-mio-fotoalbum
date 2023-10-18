package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;




@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@NotNull
	@Length(min = 3, max = 255)
	private String title;
	
	@Length(min = 3)
	private String description;
	
	@Column(unique = true, columnDefinition = "TEXT")
	private String url;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean visible;
	
	@ManyToMany
	private List<Category> categories;
	
	
	
	


	public Photo() {}
	
	public Photo(String title, String description, String url, boolean visible, Category...categories) {
	  setTitle(title);
	  setDescription(description);
	  setUrl(url);
	  setVisible(visible);
	  setCategories(Arrays.asList(categories));
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	
	
	
	@Override
	  public String toString() {
	    return "[ " + getId() + " ]\n"
	      + getTitle() + "\n"
	      + getDescription() + "\n"
	      + getUrl() + "\n";
	  }
}
