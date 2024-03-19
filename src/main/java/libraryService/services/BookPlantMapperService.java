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
		
		Plant plant;
		
		for(int i = 0; i < plantIds.size(); ++i)
		{
			plant = plantService.checkIfPlantExists(plantIds.get(i));
			
			if(plant == null)
			{
				bookPlantMapperRepository.deleteById(new BookPlantMapperId(book, plantIds.get(i)));
			}
			else
			{
				plants.add(plant);
			}
		}
		
		if(plants.size() == 0)
		{
			throw new LibraryServiceException("This book does not have any plants linked to it", HttpStatus.NOT_FOUND);
		}
		
		return plants;
	}
	
	public void linkPlantToBook(long book, long plant) throws LibraryServiceException
	{
		bookService.checkIfBookExists(book);
		
		Plant plantObject = plantService.checkIfPlantExists(plant);
		
		Optional<BookPlantMapper> bookLinkedToPlant = bookPlantMapperRepository.findById(new BookPlantMapperId(book, plant));
		
		if(bookLinkedToPlant.isPresent())
		{
			if(plantObject != null)
			{
				throw new LibraryServiceException("Plant is already linked to this book", HttpStatus.BAD_REQUEST);
			}
			else
			{
				bookPlantMapperRepository.deleteById(new BookPlantMapperId(book, plant));
				
				throw new LibraryServiceException("Plant does not exist", HttpStatus.NOT_FOUND);
			}
		}
		else if(plantObject == null)
		{
			throw new LibraryServiceException("Plant does not exist", HttpStatus.NOT_FOUND);
		}
		
		bookPlantMapperRepository.save(new BookPlantMapper(book, plant));
	}
	
	public void unlinkPlantFromBook(long book, long plant) throws LibraryServiceException
	{
		bookService.checkIfBookExists(book);
		
		Plant plantObject = plantService.checkIfPlantExists(plant);
		
		Optional<BookPlantMapper> bookLinkedToPlant = bookPlantMapperRepository.findById(new BookPlantMapperId(book, plant));
		
		if(bookLinkedToPlant.isPresent() == false)
		{
			if(plantObject != null)
			{
				throw new LibraryServiceException("Plant is not linked to this book", HttpStatus.NOT_FOUND);
			}
			else
			{
				throw new LibraryServiceException("Plant does not exist", HttpStatus.NOT_FOUND);
			}
		}
		
		bookPlantMapperRepository.deleteById(new BookPlantMapperId(book, plant));
	}
}
