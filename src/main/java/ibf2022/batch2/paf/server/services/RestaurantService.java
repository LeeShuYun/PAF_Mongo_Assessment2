package ibf2022.batch2.paf.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;
import ibf2022.batch2.paf.server.repositories.RestaurantRepository;

import static ibf2022.batch2.paf.server.Utils.*;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository rRepo;
	
	// TODO: Task 2
	// Do not change the method's signature
	public List<String> getCuisines() {
		List<String> crusineList =  rRepo.getCuisines();
		List<String> list = crusineList.stream()
					.map(v -> v.replace("/", "_"))
					.collect(Collectors.toList());
		// for (String i : list) {
		// 	System.out.printf("cruisine @ service>>> %s %n", i);
		// }
		return list;
	}

	// TODO: Task 3 
	// Do not change the method's signature
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		return rRepo.getRestaurantsByCuisine(cuisine);
	}

	// TODO: Task 4 
	// Do not change the method's signature
	public Optional<Restaurant> getRestaurantById(String id) {
		// validate(id);
		return rRepo.getRestaurantById(id);
	}

	// TODO: Task 5 
	// Do not change the method's signature
	public void postRestaurantComment(Comment comment) {
		rRepo.insertRestaurantComment(comment);
	}
}
