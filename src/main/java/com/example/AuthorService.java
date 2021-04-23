package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class AuthorService {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookService bookService;
	
	List<Author> findAllAuthors(){
		return authorRepository.findAll();
	}
	
	Optional<Author> findAuthorById(long id){
		return authorRepository.findById(id);
	}

	Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}

	Author updateAuthor(Long id, Author providedAuthor) throws Exception {
		if(this.contains(id)) {
			Author author  = findAuthorById(id).get();
			author.setName(providedAuthor.getName());
			author.setPicture(providedAuthor.getPicture());
			return authorRepository.save(author);
		}else {
			throw new NotFoundException("Author not found");
		}
	}
	
	Author registerBookForAuthor(Long authorId, Book book) throws Exception{	
		if(this.contains(authorId)) {
			Author author = this.findAuthorById(authorId).get();
			List<Book> authorBooks = author.getBooks();
			book.setAuthor(author);
			bookService.saveBook(book);
			authorBooks.add(book);
			author.setBooks(authorBooks);
			return this.saveAuthor(author);

		}else {
			throw new NotFoundException("Author not found");
		}
	}
	
	Author deleteAuthor(Long id) throws Exception {
		Optional <Author> authorData = findAuthorById(id);
		if(!authorData.isEmpty()) {
			Author author  = authorData.get();
			 authorRepository.delete(author);
			return author;
		}else {
			throw new NotFoundException("Author not found");
		}
	}

	 
	 boolean contains(long id) {
		Optional <Author> authorData = findAuthorById(id);
		return authorData.isPresent(); 
	 }
}
