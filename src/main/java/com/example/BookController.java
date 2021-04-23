package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(value="v1/books", method=RequestMethod.GET)
	ResponseEntity<List<Book>> findAllBooks(){
		return new ResponseEntity <> (bookService.findAllBooks(), HttpStatus.OK);
	}
	
	@RequestMapping(value="v1/books", method=RequestMethod.PUT)
	ResponseEntity <Book> updateBook(Book providedBook){
		try {
			return new ResponseEntity<Book> (bookService.updateBook(providedBook), HttpStatus.OK);
		}catch(NotFoundException e) {
			return new ResponseEntity <> (HttpStatus.NOT_FOUND); 
		}catch(Exception e) {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST); 
		}
		
		
	}
	
}
