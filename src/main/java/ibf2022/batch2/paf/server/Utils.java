package ibf2022.batch2.paf.server;

import org.bson.Document;

import static ibf2022.batch2.paf.server.Constants.*;

import java.util.Date;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Utils {

    //for task 3
    public static Restaurant fromDocToRestaurant(Document doc) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(doc.getString(FIELD_RESTAURANT_ID).toString());
        restaurant.setName(doc.getString(FIELD_NAME));
        return restaurant;

    }

    //also task 3
    public static JsonObject returnTask3(Restaurant rest){
        return Json.createObjectBuilder()
        .add(FIELD_RESTAURANT_ID, rest.getRestaurantId())
        .add(FIELD_NAME, rest.getName())
        .build();
    }

    public static Restaurant fromDocToRestaurantWEmptyComment(Document doc) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(doc.getString(FIELD_RESTAURANT_ID).toString());
        restaurant.setName(doc.getString(FIELD_NAME));
        restaurant.setAddress(doc.getString(FIELD_ADDRESS));
        restaurant.setCuisine(doc.getString(FIELD_CUISINE));

        Comment comment = new Comment();

        restaurant.addComment(comment);

        return restaurant;
    }
    public static Restaurant fromDocToRestaurantWithComment(Document doc) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(doc.getString(FIELD_RESTAURANT_ID).toString());
        restaurant.setName(doc.getString(FIELD_NAME));
        restaurant.setAddress(doc.getString(FIELD_ADDRESS));
        restaurant.setCuisine(doc.getString(FIELD_CUISINE));

        Comment comment = new Comment();
        comment.setRestaurantId(doc.getString(FIELD_RESTAURANT_ID).toString());
        comment.setName(doc.getString(FIELD_ADDRESS));

        Date date = doc.getDate(FIELD_COMMENT_DATE);
        long epoch = date.getTime();
        comment.setDate(epoch);
        comment.setComment(doc.getString(FIELD_COMMENTS));
        comment.setRating(doc.getInteger(FIELD_NAME));

        restaurant.addComment(comment);

        return restaurant;
    }

    public static Document fromCommentToDoc(Comment comment){
        Date d = new java.util.Date();
		long epoch = d.getTime();
        Document doc =  new Document()
                            .append(FIELD_COMMENT_RESTAURANTID, comment.getRestaurantId())
                            .append(FIELD_COMMENT_NAME, comment.getName())
                            .append(FIELD_COMMENT_RATING, comment.getRating())
                            .append(FIELD_COMMENT_DATE, epoch);
        return doc;
    }
}
