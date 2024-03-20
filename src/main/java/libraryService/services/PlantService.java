package libraryService.services;

import java.net.ConnectException;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;



import libraryService.exceptions.LibraryServiceException;
import libraryService.models.Plant;

@Service
public class PlantService
{
	final static String resourceUrl = "http://plant_service:5000/plants";
	
	public PlantService()
	{
		
	}
	
	public Plant[] getAllPlants() throws LibraryServiceException
	{
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Plant[]> response = null;
		
		try
		{
			response = restTemplate.getForEntity(resourceUrl, Plant[].class);
		}
		catch(HttpClientErrorException.NotFound e)
		{
		    throw new LibraryServiceException("No plants exist", HttpStatus.NOT_FOUND);
		}
		catch(RestClientException e)
		{
			if(e.getCause() instanceof ConnectException || e.getCause() instanceof UnknownHostException)
			{
				throw new LibraryServiceException("Plant service is down", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		if(response == null)
		{
			throw new LibraryServiceException("Plant service is down", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response.getBody();
	}
	
	public Plant checkIfPlantExists(long id) throws LibraryServiceException
	{
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Plant> response = null;
		
		try
		{
			response = restTemplate.getForEntity(resourceUrl + "/" + id, Plant.class);
		}
		catch(HttpClientErrorException.NotFound e)
		{
		    return null;
		}
		catch(RestClientException e)
		{
			if(e.getCause() instanceof ConnectException || e.getCause() instanceof UnknownHostException)
			{
				throw new LibraryServiceException("Plant service is down", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		if(response == null)
		{
			throw new LibraryServiceException("Plant service is down", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response.getBody();
	}
	
	public Plant addPlant(Plant plant) throws LibraryServiceException
	{
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
			
			JSONObject requestJson = new JSONObject(plant);
			requestJson.put("sellers", new JSONArray());
			
		    HttpEntity<String> request = new HttpEntity<String>(requestJson.toString(), headers);
			
			ResponseEntity<String> response = restTemplate.postForEntity(resourceUrl, request, String.class);
			
			JSONObject responseJson = new JSONObject(response.getBody());
			
			plant.setId(responseJson.getLong("id"));
		}
		catch(RestClientException e)
		{
			if(e.getCause() instanceof ConnectException || e.getCause() instanceof UnknownHostException)
			{
				throw new LibraryServiceException("Plant service is down", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return plant;
	}

}
