package libraryService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import libraryService.exceptions.LibraryServiceException;
import libraryService.models.Plant;
import libraryService.services.PlantService;

@RestController
public class PlantController
{
	PlantService plantService;

	public PlantController(PlantService plantService)
	{
		this.plantService = plantService;
	}
	
	@GetMapping("/plants")
	public ResponseEntity<Plant[]> getAllPlants() throws LibraryServiceException
	{
		return new ResponseEntity<>(plantService.getAllPlants(), HttpStatus.OK);
	}
	
	@PostMapping("/plants")
	public ResponseEntity<Plant> addPlant(@RequestBody Plant plant) throws LibraryServiceException
	{
		return new ResponseEntity<>(plantService.addPlant(plant), HttpStatus.CREATED);
	}
}
