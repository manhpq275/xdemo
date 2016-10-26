package vn.mvv.xconnect.utils;

/**
 * Created by phuc.nguyen on 5/17/2016.
 */
public class AppConstants {
    public static final String ROOT_DATA_API_URL = "https://xconnect.digimed.vn/data/";

    public static final String IMAGEX1200KEY = "x1200";
    public static final String IMAGEX800KEY = "x800";
    public static final String IMAGEX600KEY = "x600";
    public static final String IMAGEX400KEY = "x400";
    public static final String IMAGEX200KEY = "x200";
    public static final String IMAGEX100KEY = "x100";
    public static final String IMAGEX50KEY = "x50";
    //Common APIs
    public static final String API_GET_ITEM_PATTERN = ROOT_DATA_API_URL + "%s/getitem/%s" ;
    public static final String API_GET_ITEMS_PATTERN = ROOT_DATA_API_URL + "%s/getitems/?page=%s&quantity=%s&searchValue=%s&sortBy=%s&sortDirection=%s";
    //Consumer APIs
    public static final String API_CONSUMER_LOGIN_PATTERN = ROOT_DATA_API_URL + "%s/login%s%s%s";
    public static final String API_CONSUMER_EXTERNAL_LOGIN_PATTERN = ROOT_DATA_API_URL + "%s/mobileexlogin/";
    public static final String API_CONSUMER_REGISTER_PATTERN = ROOT_DATA_API_URL + "%s/register/";
    //ConsumerData APIs
    public static final String API_CONSUMER_DATA_UPDATE_FAVOURITE_VENDOR_PATTERN = ROOT_DATA_API_URL + "%s/updatefavouritevendor/%s/%s";
    public static final String API_CONSUMER_DATA_UPDATE_FAVOURITE_EVENT_PATTERN = ROOT_DATA_API_URL + "%s/updatefavouriteevent/%s/%s";
    public static final String API_CONSUMER_DATA_UPDATE_LIKE_EVENT_PATTERN = ROOT_DATA_API_URL + "%s/updatelikeevent/%s/%s";
    public static final String API_CONSUMER_DATA_GET_FAVOURITE_VENDORS_PATTERN = ROOT_DATA_API_URL + "%s/getfavouritevendors/%s/%s";
    public static final String API_CONSUMER_DATA_GET_UNFAVOURITE_VENDORS_PATTERN = ROOT_DATA_API_URL + "%s/getfavouritevendors/%s/%s/%s";
    public static final String API_CONSUMER_DATA_GET_SUGGESTED_VENDORS_PATTERN = ROOT_DATA_API_URL + "%s/getsuggestedvendors/%s/%s";
    public static final String API_CONSUMER_DATA_GET_FAVOURITE_EVENTS_PATTERN = ROOT_DATA_API_URL + "%s/getfavouriteevents/%s/%s";
    public static final String API_CONSUMER_DATA_GET_EVENTS_FROM_FAVOURITE_VENDORS_PATTERN = ROOT_DATA_API_URL + "%s/geteventsfromfavouritevendors/%s/%s/%s";
    //Event APIs
    public static final String API_EVENT_GET_RECENT_EVENTS_PATTERN = ROOT_DATA_API_URL + "%s/getrecentevents/%s/%s/%s/%s";
    //Comment APIs
    public static final String API_COMMENT_GET_COMMENTS_FOR_EVENTS_PATTERN = ROOT_DATA_API_URL + "%s/getcommentsforevent/%s/%s/%s";
    //Promotion APIS
    public static final String API_PROMOTION_GENERATE_CODE_PATTERN = ROOT_DATA_API_URL + "%s/getpromotioncode/%s/%s";

    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String ERROR_PREFIX = "error_";

    public static final int VOLLEY_TIMEOUT = 30000;
    public static final int VOLLEY_MAX_NUM_RETRIES = 0;
}
