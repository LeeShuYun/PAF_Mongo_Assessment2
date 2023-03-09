package ibf2022.batch2.paf.server;

import org.bson.Document;

import static ibf2022.batch2.paf.server.Constants.*;

import java.util.Date;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;

public class Utils {

    public static Restaurant fromDocToRestaurant(Document doc) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(doc.getString(FIELD_RESTAURANT_ID).toString());
        restaurant.setName(doc.getString(FIELD_NAME));
        restaurant.setAddress(doc.getString(FIELD_ADDRESS));
        restaurant.setCuisine(doc.getString(FIELD_CUISINE));

        Comment comment = new Comment();
        comment.setRestaurantId(doc.getString(FIELD_RESTAURANT_ID).toString());
        comment.setName(doc.getString(FIELD_ADDRESS));
        comment.setDate(doc.getLong(FIELD_COMMENT_DATE));
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
