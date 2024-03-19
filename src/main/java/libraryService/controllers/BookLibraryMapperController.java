package libraryService.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import libraryService.exceptions.LibraryServiceException;
import libraryService.models.Book;
import libraryService.services.BookLibraryMapperService;

@RestController
public class BookLibraryMapperController
{
	BookLibraryMapperService bookLibraryMapperService;
	
	public BookLibraryMapperController(BookLibraryMapperService bookLibraryMapperService)
	{
		this.bookLibraryMapperService = bookLibraryMapperService;
	}

	@GetMapping("/libraries/{id}/books")
	public ResponseEntity<List<Book>> getAllLibraryBooks(@PathVariable("id") long libraryId) throws LibraryServiceException
	{
		return new ResponseEntity<>(bookLibraryMapperService.getAllLibraryBooks(libraryId), HttpStatus.OK);
	}
	
	@PostMapping("/libraries/{id}/books/{bookId}")
	public ResponseEntity<HttpStatus> addBookToLibrary(@PathVariable("id") long library, @PathVariable("bookId") long  book) throws LibraryServiceException
	{
		bookLibraryMapperService.addBookToLibrary(library, book);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/libraries/{id}/books/{bookId}")
	public ResponseEntity<HttpStatus> removeBookFromLibrary(@PathVariable("id") long library, @PathVariable("bookId") long  book) throws LibraryServiceException
	{
		bookLibraryMapperService.removeBookFromLibrary(library, book);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
}
