package libraryService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import libraryService.exceptions.LibraryServiceException;
import libraryService.models.BookPlantMapper;
import libraryService.models.BookPlantMapperId;
import libraryService.models.Plant;
import libraryService.repositories.BookPlantMapperRepository;

@Service
public class BookPlantMapperService
{
	BookPlantMapperRepository bookPlantMapperRepository;
	
	BookService bookService;
	PlantService plantService;

	public BookPlantMapperService(BookPlantMapperRepository bookPlantMapperRepository, BookService bookService, PlantService plantService)
	{
		this.bookPlantMapperRepository = bookPlantMapperRepository;
		this.bookService = bookService;
		this.plantService = plantService;
		
		bookPlantMapperRepository.save(new BookPlantMapper(1, 1));
		bookPlantMapperRepository.save(new BookPlantMapper(1, 2));
	}
	
	public List<Plant> getPlantsLinkedToBook(long book) throws LibraryServiceException
	{
		bookService.checkIfBookExists(book);
		
		List<BookPlantMapper> mapperData = bookPlantMapperRepository.findAll();
		
		List<Long> plantIds = new ArrayList<>();
	
		for(int i = 0; i < mapperData.size(); ++i)
		{
			if(mapperData.get(i).getBook() == book)
			{
				plantIds.add(mapperData.get(i).getPlant());
			}
		}
		
		if(plantIds.size() == 0)
		{
			throw new LibraryServiceException("This book does not have any plants linked to it", HttpStatus.NOT_FOUND);
		}
		
		List<Plant> plants = new ArrayList<>();
		
		for(int i = 0; i < plantIds.size(); ++i)
		{
			plants.add(plantService.getPlant(plantIds.get(i)));
		}
		
		return plants;
	}
	
	public void linkPlantToBook(long book, long plant) throws LibraryServiceException
	{
		bookService.checkIfBookExists(book);
		plantService.getPlant(plant);
		
		Optional<BookPlantMapper> bookLinkedToPlant = bookPlantMapperRepository.findById(new BookPlantMapperId(book, plant));
		
		if(bookLinkedToPlant.isPresent())
		{
			throw new LibraryServiceException("Plant is already linked to this book", HttpStatus.BAD_REQUEST);
		}
		
		bookPlantMapperRepository.save(new BookPlantMapper(book, plant));
	}
	
	public void unlinkPlantFromBook(long book, long plant) throws LibraryServiceException
	{
		bookService.checkIfBookExists(book);
		plantService.getPlant(plant);
		
		Optional<BookPlantMapper> bookLinkedToPlant = bookPlantMapperRepository.findById(new BookPlantMapperId(book, plant));
		
		if(bookLinkedToPlant.isPresent() == false)
		{
			throw new LibraryServiceException("Plant is not linked to this book", HttpStatus.NOT_FOUND);
		}
		
		bookPlantMapperRepository.deleteById(new BookPlantMapperId(book, plant));
	}
	
	public void unlinkPlantsFromBook(long book) throws LibraryServiceException
	{
		bookService.checkIfBookExists(book);
		List<Plant> plants = getPlantsLinkedToBook(book);
		
		for(int i = 0; i < plants.size(); ++i)
		{
			bookPlantMapperRepository.deleteById(new BookPlantMapperId(book, plants.get(i).getId()));
		}
	}
	
	

}
