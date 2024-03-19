package libraryService.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import libraryService.exceptions.LibraryServiceException;
import libraryService.models.Book;
import libraryService.services.BookService;

@RestController
public class BookController
{
	BookService bookService;
	
	public BookController(BookService bookService)
	{
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() throws LibraryServiceException
	{
		return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable long id) throws LibraryServiceException
	{
		return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) throws LibraryServiceException
	{
		return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable long id,@RequestBody Book newBookData) throws LibraryServiceException
	{
		return new ResponseEntity<>(bookService.updateBook(id, newBookData), HttpStatus.OK);
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable long id) throws LibraryServiceException
	{
		bookService.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
