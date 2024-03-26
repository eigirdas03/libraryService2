package libraryService.requests;

import java.util.List;

public class PutBooksInLibraryRequest
{
	List<Long> books;
	
	public PutBooksInLibraryRequest()
	{
		
	}

	public PutBooksInLibraryRequest(List<Long> books)
	{
		this.books = books;
	}

	public List<Long> getBooks()
	{
		return books;
	}

	public void setBooks(List<Long> books)
	{
		this.books = books;
	}
}
