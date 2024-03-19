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
@Table(name = "Libraries")
public class Library
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(accessMode = AccessMode.READ_ONLY)
	long id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "address")
	String address;
	
	@Column(name = "opened")
	int opened;
	
	public Library()
	{

	}

	public Library(long id, String name, String address, int opened) throws LibraryServiceException
	{
		this(name ,address, opened);
		this.id = id;
	}
	
	public Library(String name, String address, int opened) throws LibraryServiceException
	{
		this.name = name;
		this.address = address;
		setOpened(opened);
	}
	
	public Library(Library other) throws LibraryServiceException
	{
		this.name = other.getName();
		this.address = other.getAddress();
		setOpened(other.getOpened());
	}
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
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

	public void setOpened(int opened) throws LibraryServiceException
	{
		if(opened < 0 || opened > Year.now().getValue())
		{
			throw new LibraryServiceException("invalid year value", HttpStatus.BAD_REQUEST);
		}
		this.opened = opened;
	}
}
