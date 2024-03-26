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
import libraryService.models.Library;
import libraryService.requests.PostAndPutLibraryRequest;
import libraryService.requests.PutBooksInLibraryRequest;
import libraryService.services.LibraryService;

@RestController
public class LibraryController
{
	LibraryService libraryService;
	
	public LibraryController(LibraryService libraryService)
	{
		this.libraryService = libraryService;
	}

	@GetMapping("/libraries")
	public ResponseEntity<List<Library>> getAllLibraries() throws LibraryServiceException
	{
		return new ResponseEntity<>(libraryService.getAllLibraries(), HttpStatus.OK);
	}
	
	@GetMapping("/libraries/{id}")
	public ResponseEntity<Library> getLibraryById(@PathVariable long id) throws LibraryServiceException
	{
		return new ResponseEntity<>(libraryService.getLibraryById(id), HttpStatus.OK);
	}
	
	@PostMapping("/libraries")
	public ResponseEntity<Library> addLibrary(@RequestBody PostAndPutLibraryRequest request) throws LibraryServiceException
	{
		return new ResponseEntity<>(libraryService.addLibrary(request), HttpStatus.CREATED);
	}
	
	@PutMapping("/libraries/{id}")
	public ResponseEntity<Library> updateLibrary(@PathVariable long id, @RequestBody PostAndPutLibraryRequest request) throws LibraryServiceException
	{
		return new ResponseEntity<>(libraryService.updateLibrary(id, request), HttpStatus.OK);
	}
	
	@PutMapping("/libraries/{id}/books")
	public ResponseEntity<Library> updateLibraryBooks(@PathVariable long id, @RequestBody PutBooksInLibraryRequest request) throws LibraryServiceException
	{
		return new ResponseEntity<>(libraryService.updateLibraryBooks(id, request), HttpStatus.OK);
	}
	
	@DeleteMapping("/libraries/{id}")
	public ResponseEntity<HttpStatus> deleteLibrary(@PathVariable long id) throws LibraryServiceException
	{
		libraryService.deleteLibrary(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/libraries/{id}/books/{bookId}")
	public ResponseEntity<HttpStatus> deleteLibraryBooks(@PathVariable("id") long library, @PathVariable("bookId") long  book) throws LibraryServiceException
	{
		libraryService.deleteLibraryBook(library, book);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}