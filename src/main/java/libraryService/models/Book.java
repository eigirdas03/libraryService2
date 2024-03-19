package libraryService.models;



import java.time.Year;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import libraryService.exceptions.LibraryServiceException;

@Entity
@Table(name = "Books")
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(accessMode = AccessMode.READ_ONLY)
	long id;
	
	@Column(name = "author")
	String author;
	
	@Column(name = "title")
	String title;
	
	@Column(name = "published")
	int published;
	
	public Book()
	{
		
	}

	public Book(long id, String author, String title, int published) throws LibraryServiceException
	{
		this(author, title, published);
		this.id = id;
	}
	
	public Book(String author, String title, int published) throws LibraryServiceException
	{
		this.author = author;
		this.title = title;
		setPublished(published);
	}
	
	public Book(Book other) throws LibraryServiceException
	{
		this.author = other.getAuthor();
		this.title = other.getTitle();
		setPublished(other.getPublished());
	}
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getPublished()
	{
		return published;
	}

	public void setPublished(int published) throws LibraryServiceException
	{
		if(published < 0 || published > Year.now().getValue())
		{
			throw new LibraryServiceException("invalid year value", HttpStatus.BAD_REQUEST);
		}
		this.published = published;
	}
}
