package ibf2022.batch2.paf.server;

import org.bson.Document;

import static ibf2022.batch2.paf.server.Constants.*;

import java.util.Date;
import java.util.List;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

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

    //task 4
    public static Restaurant fromDocToRestaurant4(Document doc) {
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
        return restaurant;

    }
    //task 4
    // {
    //     "_id" : ObjectId("640934c5547c4bb6e057cc2a"),
    //     "address" : {
    //         "building" : "396",
    //         "coord" : [
    //             -73.9809909,
    //             40.7422324
    //         ],
    //         "street" : "Third Avenue",
    //         "zipcode" : "10016"
    //     },
    //     "borough" : "Manhattan",
    //     "cuisine" : "Thai",
    //     "grades" : [
    //         {
    //             "date" : ISODate("2014-10-30T00:00:00.000+0000"),
    //             "grade" : "A",
    //             "score" : NumberInt(13)
    //         },
    //         {
    //             "date" : ISODate("2014-06-04T00:00:00.000+0000"),
    //             "grade" : "A",
    //             "score" : NumberInt(7)
    //         },
    //         {
    //             "date" : ISODate("2013-03-08T00:00:00.000+0000"),
    //             "grade" : "A",
    //             "score" : NumberInt(13)
    //         },
    //         {
    //             "date" : ISODate("2012-10-01T00:00:00.000+0000"),
    //             "grade" : "A",
    //             "score" : NumberInt(9)
    //         }
    //     ],
    //     "name" : "Jaiya Thai Oriental Restaurant",
    //     "restaurant_id" : "40392724",
    //     "comments" : [
    //         {
    //             "_id" : ObjectId("64098830b39ef2459fb01fca"),
    //             "restaurant_id" : "40392724",
    //             "name" : "commenter",
    //             "date" : ISODate("2012-10-01T00:00:00.000+0000"),
    //             "comment" : "alksjdlfja",
    //             "rating" : NumberInt(8)
    //         }
    //     ]
    // }
    // public static JsonObject fromDocToRestaurantJsonWithComment(Restaurant r) {
        // JsonArrayBuilder commentsArray = Json.createArrayBuilder();
        // List<Comment> commentList = r.getComments();
        
        // if (doc.getString(FIELD_COMMENTNEST_NAME) != null){
        //     for (Comment comment : commentList) {
        //         commentsArray.add(
        //             Json.createObjectBuilder
        //             .add(FIELD_RESTAURANT_ID, r.getRestaurantId())
        //             );
        //     }
        //             commentsArray
        //             .add(FIELD_COMMENT_RESTAURANTID, r.getComments))
        //             .add(FIELD_COMMENTNEST_NAME, )
        //             .add(FIELD_COMMENTNEST_DATE, doc.getDate(FIELD_COMMENTNEST_DATE))
        //             .add(FIELD_COMMENTNEST_TEXT, doc.getString(FIELD_COMMENTNEST_TEXT))
        //             .add(FIELD_COMMENTNEST_RATING, doc.getInteger(FIELD_COMMENTNEST_RATING));
        //         }
        // return Json.createObjectBuilder()
        //         .add(FIELD_RESTAURANT_ID, r.getRestaurantId())
        //         .add(FIELD_NAME, r.getName())
        //         .add(FIELD_ADDRESS, r.getAddress())
        //         .add(FIELD_CUISINE, r.getCuisine())
        //         .add(FIELD_COMMENTS, commentsArray)
        //         .build();

        // if (doc.getString(FIELD_COMMENTNEST_NAME) != null){
        //     commentsArray
        //     .add(FIELD_COMMENT_RESTAURANTID, doc.getString(FIELD_COMMENT_RESTAURANTID))
        //     .add(FIELD_COMMENTNEST_NAME, doc.getString(FIELD_COMMENTNEST_NAME))
        //     .add(FIELD_COMMENTNEST_DATE, doc.getDate(FIELD_COMMENTNEST_DATE))
        //     .add(FIELD_COMMENTNEST_TEXT, doc.getString(FIELD_COMMENTNEST_TEXT))
        //     .add(FIELD_COMMENTNEST_RATING, doc.getInteger(FIELD_COMMENTNEST_RATING));
        // }
        // return Json.createObjectBuilder()
        //         .add(FIELD_RESTAURANT_ID, doc.getString(FIELD_RESTAURANT_ID).toString())
        //         .add(FIELD_NAME, doc.getString(FIELD_NAME))
        //         .add(FIELD_ADDRESS, doc.getString(FIELD_ADDRESS))
        //         .add(FIELD_CUISINE, doc.getString(FIELD_CUISINE))
        //         .add(FIELD_COMMENTS, commentsArray)
        //         .build();
    // }

    //task 5
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
