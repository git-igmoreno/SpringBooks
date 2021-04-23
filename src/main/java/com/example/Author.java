package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Author {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	long id;
	
	@Column
	String name;
	
	@Column
	String picture;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public Author() {
		super();
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Author(String name, String picture) {
		super();
		this.name = name;
		this.picture = picture;
		this.books = new ArrayList<Book>();
	}

	public Author(long id, String name, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.books = new ArrayList<Book>();
	}
	
	public Author(long id, String name, String picture, List<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.books = books;
	}
}
