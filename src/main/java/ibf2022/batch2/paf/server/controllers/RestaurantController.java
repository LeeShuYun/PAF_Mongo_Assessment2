package ibf2022.batch2.paf.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import ibf2022.batch2.paf.server.services.RestaurantService;
import ibf2022.batch2.paf.server.models.Restaurant;

@RestController
@RequestMapping(path = "/api")
public class RestaurantController {
	@Autowired
	RestaurantService rSvc;

	// TODO: Task 2 - request handler
	@GetMapping(path= "/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getListOfCuisines(){
		List<String> cList = rSvc.getCuisines();
		String result = "";
		for (String string : cList) {
			result = result + " , " + string;
		}


        if (cList.isEmpty()){
			return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(null); 
        }else{
		return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(cList); 
        }
	}

	// TODO: Task 3 - request handler
	@GetMapping("/restaurants/{cruisine}")
	public ResponseEntity<List<String>> getListofRestaurantByCrusine(@PathVariable("cruisine") String cruisine){
		List<Restaurant> resList = rSvc.getRestaurantsByCuisine(cruisine);

		List<String> stringRestList = resList.stream()
											.map(v -> v.toString())
											.toList();

		for (String i : stringRestList) {
			System.out.printf("restaurant @ service>>> %s %n", i);
		}
        if (resList.isEmpty()){
			return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(null); 
        }else{
			return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(stringRestList); 

        }
	
	}

	// TODO: Task 4 - request handler

	// TODO: Task 5 - request handler

}
