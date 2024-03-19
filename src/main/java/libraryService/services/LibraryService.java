package libraryService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import libraryService.exceptions.LibraryServiceException;
import libraryService.models.BookLibraryMapper;
import libraryService.models.Library;
import libraryService.repositories.BookLibraryMapperRepository;
import libraryService.repositories.LibraryRepository;

@Service
public class LibraryService
{
	LibraryRepository libraryRepository;
	
	BookLibraryMapperRepository bookLibraryMapperRepository;

	public LibraryService(LibraryRepository libraryRepository, BookLibraryMapperRepository bookLibraryMapperRepository) throws LibraryServiceException
	{
		this.libraryRepository = libraryRepository;
		this.bookLibraryMapperRepository = bookLibraryMapperRepository;
		
        libraryRepository.save(new Library("library1", "address 1-1", 1990));
        libraryRepository.save(new Library("library2", "address 1-2", 1991));
        libraryRepository.save(new Library("library3", "address 1-3", 1992));
	}
	
	public void checkIfLibrarykExists(Optional<Library> library) throws LibraryServiceException
	{
		if(library.isPresent() == false)
		{
			throw new LibraryServiceException("Library does not exist", HttpStatus.NOT_FOUND);
		}
	}
	
	public void checkIfLibrarykExists(long id) throws LibraryServiceException
	{
		checkIfLibrarykExists(libraryRepository.findById(id));
	}
	
	public void checkIfLibraryDoesNotExist(long id) throws LibraryServiceException
	{
		if(libraryRepository.findById(id).isPresent())
		{
			throw new LibraryServiceException("Library already exists", HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<Library> getAllLibraries() throws LibraryServiceException
	{
		List<Library> libraries = libraryRepository.findAll();
		
		if(libraries.size() == 0)
		{
			throw new LibraryServiceException("No libraries exist", HttpStatus.NOT_FOUND);
		}
		
		return libraries;
	}
	
	
	public Library getLibraryById(long id) throws LibraryServiceException
	{
		Optional<Library> library = libraryRepository.findById(id);
		checkIfLibrarykExists(library);
		
		return library.get();
	}
	
	public Library addLibrary(Library library) throws LibraryServiceException
	{
		Library newLibrary = libraryRepository.save(new Library(library));
		return newLibrary;
	}
	

	public Library updateLibrary(long id, Library newLibraryData) throws LibraryServiceException
	{
		Optional<Library> library = libraryRepository.findById(id);
		checkIfLibrarykExists(library);
		
		Library libraryData = library.get();
		
		libraryData.setName(newLibraryData.getName());
		libraryData.setAddress(newLibraryData.getAddress());
		libraryData.setOpened(newLibraryData.getOpened());
		
		Library updatedLibrary = libraryRepository.save(libraryData);
		
		return updatedLibrary;
	}
	
	public void deleteLibrary(long id) throws LibraryServiceException
	{
		Optional<Library> library = libraryRepository.findById(id);
		
		checkIfLibrarykExists(library);
		
		libraryRepository.deleteById(id);
			
		List<BookLibraryMapper> allMapperData = bookLibraryMapperRepository.findAll();
		
		for(int i = 0; i < allMapperData.size(); ++i)
		{
			BookLibraryMapper mapperData = allMapperData.get(i);
			
			if(mapperData.getLibrary() == id)
			{
				bookLibraryMapperRepository.delete(mapperData);
			}
		}
	}

}
