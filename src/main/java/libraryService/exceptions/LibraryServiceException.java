package libraryService.exceptions;

import org.springframework.http.HttpStatus;

public class LibraryServiceException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	HttpStatus httpCode;
	
	public LibraryServiceException(String message, HttpStatus httpCode)
	{
		super(message);
		this.httpCode = httpCode;
	}

	public HttpStatus getHttpCode()
	{
		return httpCode;
	}

}
