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
import libraryService.models.Plant;
import libraryService.services.BookPlantMapperService;

@RestController
public class BookPlantMapperController
{
	BookPlantMapperService bookPlantMapperService;

	public BookPlantMapperController(BookPlantMapperService bookPlantMapperService)
	{
		this.bookPlantMapperService = bookPlantMapperService;
	}
	
	@GetMapping("/books/{id}/plants")
	public ResponseEntity<List<Plant>> getPlantsLinkedToBook(@PathVariable("id") long bookID) throws LibraryServiceException
	{
		return new ResponseEntity<>(bookPlantMapperService.getPlantsLinkedToBook(bookID), HttpStatus.OK);
	}
	
	@PostMapping("/books/{id}/plants/{plantId}")
	public ResponseEntity<HttpStatus> linkPlantToBook(@PathVariable("id") long book, @PathVariable("plantId") long plant) throws LibraryServiceException
	{
		bookPlantMapperService.linkPlantToBook(book, plant);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/books/{id}/plants/{plantId}")
	public ResponseEntity<HttpStatus>unlinkPlantFromBook(@PathVariable("id") long book, @PathVariable("plantId") long plant) throws LibraryServiceException
	{
		bookPlantMapperService.unlinkPlantFromBook(book, plant);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
