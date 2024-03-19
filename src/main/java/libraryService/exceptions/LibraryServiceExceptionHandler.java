package libraryService.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class LibraryServiceExceptionHandler
{
	@ResponseBody
	@ExceptionHandler(LibraryServiceException.class)
	ResponseEntity<Error> handleException(LibraryServiceException ex)
	{
		return new  ResponseEntity<>(new Error(ex.getMessage()), ex.getHttpCode());
	}
}
