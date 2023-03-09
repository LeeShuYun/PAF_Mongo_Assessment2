package ibf2022.batch2.paf.server;

public class Constants {
    public static final String DB_RESTDB = "restdb";
    public static final String COLLECTION_RESTAURANT= "restaurant";
    public static final String COLLECTION_COMMENT= "comments";
    
    public static final String FIELD_CUISINE = "cuisine";
    public static final String FIELD_RESTAURANT_ID = "restaurant_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_ADDRESS = "address";
    public static final String FIELD_COMMENTS = "comments";

    public static final String FIELD_COMMENT_TEXT = "comment";
    public static final String FIELD_COMMENT_DATE = "date";
    public static final String FIELD_COMMENT_NAME = "name";
    public static final String FIELD_COMMENT_RATING = "rating";
    public static final String FIELD_COMMENT_RESTAURANTID = "restaurant_id";
    
    public static final String FIELD_COMMENTNEST_TEXT = "comments.comment";
    public static final String FIELD_COMMENTNEST_DATE = "comments.date";
    public static final String FIELD_COMMENTNEST_NAME = "comments.name";
    public static final String FIELD_COMMENTNEST_RATING = "comments.rating";
    public static final String FIELD_COMMENTNEST_RESTAURANTID = "comments.restaurant_id";
}
