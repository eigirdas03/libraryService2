package libraryService.requests;

import java.util.List;


public class PostAndPutLibraryRequest
{
	String name;
	
	String address;
	
	int opened;
	
	List<Long> books;
	
	public PostAndPutLibraryRequest()
	{
		
	}

	public PostAndPutLibraryRequest(String name, String address, int opened, List<Long> books)
	{
		this.name = name;
		this.address = address;
		this.opened = opened;
		this.books = books;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public int getOpened()
	{
		return opened;
	}

	public void setOpened(int opened)
	{
		this.opened = opened;
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
