package libraryService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import libraryService.exceptions.LibraryServiceException;
import libraryService.models.Book;
import libraryService.models.BookLibraryMapper;
import libraryService.models.BookLibraryMapperId;
import libraryService.repositories.BookLibraryMapperRepository;

@Service
public class BookLibraryMapperService
{
	BookLibraryMapperRepository bookLibraryMapperRepository;
	
	LibraryService libraryService;
	BookService bookService;
	
	public BookLibraryMapperService(LibraryService libraryService, BookService bookService,
			BookLibraryMapperRepository bookLibraryMapperRepository)
	{
		this.bookLibraryMapperRepository = bookLibraryMapperRepository;
		this.libraryService = libraryService;
		this.bookService = bookService;
		
        bookLibraryMapperRepository.save(new BookLibraryMapper(1, 1));
        bookLibraryMapperRepository.save(new BookLibraryMapper(1, 2));
	}
	
	public List<Book> getAllLibraryBooks(long libraryId) throws LibraryServiceException
	{
		libraryService.checkIfLibrarykExists(libraryId);
		
		List<BookLibraryMapper> mapperData = bookLibraryMapperRepository.findAll();
		
		List <Book> libraryBooks = new ArrayList<>();
		
		for(int i = 0; i < mapperData.size(); ++i)
		{
			if(mapperData.get(i).getLibrary() == libraryId)
			{
				long bookId = mapperData.get(i).getBook();
				libraryBooks.add(bookService.getBookById(bookId));
			}
		}
		
		if(libraryBooks.size() == 0)
		{
			throw new LibraryServiceException("Library does not have any books", HttpStatus.NOT_FOUND);
		}
		
		return libraryBooks;
	}
	
	
	public void addBookToLibrary(long library, long  book) throws LibraryServiceException
	{
		libraryService.checkIfLibrarykExists(library);
		bookService.checkIfBookExists(book);
		
		if(bookLibraryMapperRepository.findById(new BookLibraryMapperId(library, book)).isPresent())
		{
			throw new LibraryServiceException("Book is already in this library", HttpStatus.BAD_REQUEST);
		}
		
		List<BookLibraryMapper> allMapperData = bookLibraryMapperRepository.findAll();
		
		for(int i = 0; i < allMapperData.size(); ++i)
		{
			BookLibraryMapper mapperData = allMapperData.get(i);
			
			if(mapperData.getBook() == book)
			{
				throw new LibraryServiceException("Book is in another library", HttpStatus.BAD_REQUEST);
			}
		}
		
		bookLibraryMapperRepository.save(new BookLibraryMapper(library, book));
	}
	
	public void removeBookFromLibrary(long library, long  book) throws LibraryServiceException
	{
		libraryService.checkIfLibrarykExists(library);
		bookService.checkIfBookExists(book);
		
		Optional<BookLibraryMapper> bookInLibrary = bookLibraryMapperRepository.findById(new BookLibraryMapperId(library, book));
		
		if(bookInLibrary.isPresent() == false)
		{
			throw new LibraryServiceException("Book is not in this library", HttpStatus.NOT_FOUND);
		}
		
		bookLibraryMapperRepository.deleteById(new BookLibraryMapperId(library, book));
	}
}
