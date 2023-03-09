package ibf2022.batch2.paf.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import ibf2022.batch2.paf.server.services.RestaurantService;
import jakarta.json.Json;
import ibf2022.batch2.paf.server.models.Restaurant;
import ibf2022.batch2.paf.server.models.Comment;

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
	public ResponseEntity<String> getRestaurantById(@PathVariable("restaurant_id") String id){

		Optional<Restaurant> retrievedData = rSvc.getRestaurantById(id);
		
		if (retrievedData.isEmpty()){
			return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(Json.createObjectBuilder()
						.add("status", 404)
						.add("error", "Missing  %s ".formatted(id))
						.build().toString()
						);
		}else{
			return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(retrievedData.toString()); 
			
		}
	}

	// TODO: Task 5 - request handler
	@PostMapping(path="/restaurant/comment")
	public ResponseEntity<String> postRestaurantComment(@RequestBody Comment comment){
		rSvc.postRestaurantComment(comment);
		Integer isSuccess = rSvc.getCommentById();

		if (isSuccess != 1){
			return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(Json.createObjectBuilder()
						.add("status", 404)
						.add("error", "Comment insert not successful.")
						.build().toString()
						);
			}else{
				return ResponseEntity
				.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(Json.createObjectBuilder().build().toString()
							); 
				
			}
	}
}
