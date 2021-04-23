package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Book {
	@Id
	Long isbn;
	
	@Column
	String title;
	
	@Column
	String description;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "id")
		Author author;
	
	public Book() {
		super();
	}

	public Book(Long isbn, String title, String description) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.description = description;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book(Long isbn, String title, String description, Author author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.author = author;
	}

	
}
