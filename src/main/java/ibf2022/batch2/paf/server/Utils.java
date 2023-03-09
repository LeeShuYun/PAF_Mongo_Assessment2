package ibf2022.batch2.paf.server;

import org.bson.Document;

import static ibf2022.batch2.paf.server.Constants.*;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;

public class Utils {

    public static Restaurant fromDocToRestaurant(Document doc) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(doc.getObjectId(FIELD_RESTAURANT_ID).toString());
        restaurant.setName(doc.getString(FIELD_NAME));
        restaurant.setAddress(doc.getString(FIELD_ADDRESS));
        restaurant.setCuisine(doc.getString(FIELD_CUISINE));

        Comment comment = new Comment();
        comment.setRestaurantId(doc.getObjectId(FIELD_RESTAURANT_ID).toString());
        comment.setName(doc.getString(FIELD_ADDRESS));
        comment.setDate(doc.getLong(FIELD_COMMENT_DATE));
        comment.setComment(doc.getString(FIELD_COMMENTS));
        comment.setRating(doc.getInteger(FIELD_NAME));

        restaurant.addComment(comment);

        return restaurant;
    }
}
