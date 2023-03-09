package ibf2022.batch2.paf.server.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;
import static ibf2022.batch2.paf.server.Constants.*;
import static ibf2022.batch2.paf.server.Utils.*;

@Repository
public class RestaurantRepository {

	@Autowired 
	MongoTemplate template;

	// TODO: Task 2 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// db.restaurant.distinct("cuisine");

	public List<String> getCuisines() {
		return template.findDistinct(new Query(), FIELD_CUISINE, COLLECTION_RESTAURANT, String.class);
	}


	// TODO: Task 3 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// db.restaurant.aggregate([
	// 	{
	// 		$match: { cuisine : "Thai" }
	// 	}
	// 	,
	// 	{
	// 		$project: { _id: -1 , restaurant_id: 1, name: 1 }
	// 	}
	// ]);
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
			MatchOperation matchRated = Aggregation.match(
			Criteria.where(FIELD_CUISINE).is(cuisine));
			ProjectionOperation projectFields = Aggregation
										.project(FIELD_RESTAURANT_ID, FIELD_NAME)
										.andExclude("_id");
			Aggregation pipeline= Aggregation.newAggregation(matchRated, projectFields);
			AggregationResults<Document> results= template.aggregate(
			pipeline, COLLECTION_RESTAURANT, Document.class);
			List<Document> list = results.getMappedResults();
			List<Restaurant> returnedList = list.stream()
											.map (v -> fromDocToRestaurant(v))
											.toList();
			return returnedList;
	}
	
	// TODO: Task 4 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	//
	public Optional<Restaurant> getRestaurantById(String id) {
		return null;
	}

	// TODO: Task 5 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	//
	public void insertRestaurantComment(Comment comment) {
	}
}
