package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;


@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorService authorService;
	
	Book saveBook(Book book) throws Exception {
		if(!this.contains(book.getIsbn())) {
			return bookRepository.save(book);
		}else{
			throw new IllegalArgumentException("Book already exsits");
		}

	}
	
	Book updateBook(Book providedBook) throws NotFoundException {
		if(!this.contains(providedBook.getIsbn())) {
			Book book = findBookByIsbn(providedBook.getIsbn()).get();
			book.setTitle(providedBook.getTitle());
			book.setDescription(providedBook.getDescription());
			book.setAuthor(providedBook.getAuthor());
			return bookRepository.save(book);
		}else {
			throw new NotFoundException("Book not found");
		}
		
	}

	Optional<Book> findBookByIsbn(Long isbn) {
		return bookRepository.findById(isbn);
	}

	List<Book> findAllBooks() {
		return bookRepository.findAll();
	}
	
	boolean contains(long isbn) {
		Optional <Book> bookData = findBookByIsbn(isbn);
		return bookData.isPresent();
		
	}


}
