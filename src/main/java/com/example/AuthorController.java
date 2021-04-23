package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	BookRepository bookRepository;
	
	@RequestMapping(value="v1/authors", method=RequestMethod.GET)
	ResponseEntity<List<Author>> findAllAuthors(){
		return new ResponseEntity <> (authorService.findAllAuthors(), HttpStatus.OK);
	}
	
	@RequestMapping(value="v1/authors/{id}", method=RequestMethod.GET)
	ResponseEntity<Author> findAuthorById(@PathVariable(value="id") Long id) {
		Optional <Author> authorData = authorService.findAuthorById(id);
		if(authorData.isPresent()) {
			return new ResponseEntity<> (authorData.get(), HttpStatus.OK);
		}
		return new ResponseEntity <> (HttpStatus.NOT_FOUND); 		
	}
	
	@RequestMapping(value="v1/authors", method=RequestMethod.POST)
	ResponseEntity<Author> saveAuthor(@RequestBody Author author){
		try {
			return new ResponseEntity<> (authorService.saveAuthor(author), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST); 	
		}
	}
	
	@RequestMapping(value="v1/authors/{id}/books", method=RequestMethod.POST)
	ResponseEntity<Author> registerBookForAuthor(@PathVariable(value="id") Long authorId, @RequestBody Book book){
		try {
			return new ResponseEntity<> (authorService.registerBookForAuthor(authorId, book), HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="v1/authors/{id}", method=RequestMethod.PUT)
	ResponseEntity<Author> updateAuthor(@PathVariable(value="id") Long id, @RequestBody Author providedAuthor){
		try {
			return new ResponseEntity<> (authorService.updateAuthor(id, providedAuthor), HttpStatus.OK);
		}catch(NotFoundException e) {
			return new ResponseEntity <> (HttpStatus.NOT_FOUND); 	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST); 	
		}
	}
	
	@RequestMapping(value="v1/authors/{id}", method=RequestMethod.DELETE)
	ResponseEntity<Author> deleteAuthor(@PathVariable(value="id") Long id){
		try {
			return new ResponseEntity<Author> (authorService.deleteAuthor(id), HttpStatus.OK);
		}catch(NotFoundException e) {
			return new ResponseEntity <> (HttpStatus.NOT_FOUND); 	
		} catch (Exception e) {
			return new ResponseEntity <> (HttpStatus.BAD_REQUEST); 	
		}
	}
	
}
