package libraryService.models;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

public class Plant
{
	@Schema(accessMode = AccessMode.READ_ONLY)
	long id;
	
	String name;
	String type;
	
	Plant()
	{
		
	}

	public Plant(long id, String name, String type)
	{
		this.id = id;
		this.name = name;
		this.type = type;
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
