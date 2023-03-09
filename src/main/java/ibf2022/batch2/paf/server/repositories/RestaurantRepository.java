package ibf2022.batch2.paf.server.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
			MatchOperation matchRated = Aggregation.match(Criteria.where(FIELD_CUISINE).is(cuisine));
			ProjectionOperation projectFields = Aggregation
										.project(FIELD_RESTAURANT_ID, FIELD_NAME)
										.andExclude("_id");
			Aggregation pipeline = Aggregation.newAggregation(matchRated, projectFields);
			AggregationResults<Document> results= template.aggregate(
			pipeline, COLLECTION_RESTAURANT, Document.class);
			List<Document> list = results.getMappedResults();
			

			List<Restaurant> restList = list.stream()
											.map (v -> fromDocToRestaurant(v))
											.toList();
			

			return restList;
	}
	
	// TODO: Task 4 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// db.restaurant.aggregate([
	// 	{
	// 		$match: { restaurant_id: "40392724" }
	// 	},
	// 	{
	// 		$lookup: {
	// 			from: "comments",
	// 			localField: "restaurant_id",
	// 			foreignField: "restaurant_id",
	// 			as: "comments"
	// 		}
	// 	}
	// ])
	public Optional<Restaurant> getRestaurantById(String id) {
		try {
			MatchOperation matchRated= Aggregation.match(Criteria.where(FIELD_RESTAURANT_ID).is(id));
			LookupOperation lookupTo = Aggregation.lookup(
                COLLECTION_COMMENT,
            	FIELD_RESTAURANT_ID,
                FIELD_RESTAURANT_ID,
                "comments");

				Aggregation pipeline = Aggregation.newAggregation(
					matchRated, lookupTo);
			AggregationResults<Document> results = template.aggregate(
					pipeline, COLLECTION_RESTAURANT, Document.class);
			List<Document> doc = results.getMappedResults();

			Restaurant restt = fromDocToRestaurant(doc.get(0));

			return Optional.ofNullable(restt);

			} catch (IllegalArgumentException e) {
					return Optional.empty();
			}
		
	}

	// TODO: Task 5 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// db.comments.insert({
	// 	restaurant_id: "40392724",
	// 	name: "commenter",
	// 	date: ISODate("2012-10-01T00:00:00.000+0000"),
	// 	comment:"alksjdlfja",
	// 	rating: 8.0
	// })
	public void insertRestaurantComment(Comment comment) {
		//convert to document
		
		Document doc = fromCommentToDoc(comment);
		Document commentDoc = template.insert(doc, COLLECTION_COMMENT);

		// for some reason this doesn't want to work :(
		// ObjectId id = commentDoc.getObjectId();
		
		// return id != null;

		// UpdateResult updateResult = template.update(query, updateOps, Document.class, COLLECTION_COMMENT);
		// System.out.printf("Doc updated: %d", updateResult.getModifiedCount());
	}
}
