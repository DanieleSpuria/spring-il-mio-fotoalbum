package org.java.app.db.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;




@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@NotNull
	@Length(min = 3, max = 255)
	private String name;
	
	@ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
	private List<Photo> photos;

	
	
	
	public Category() {}
	
	public Category(String name) {
		setName(name);
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public void addPhoto(Photo photo) {
		getPhotos().add(photo);
	}
	
	public void delPhoto(Photo photo) {
		getPhotos().remove(photo);
	}
	
	
	
	
	@Override
	public String toString() {
		return "[ " + getId() + " ]\n"
				+ getName();
	}
}
