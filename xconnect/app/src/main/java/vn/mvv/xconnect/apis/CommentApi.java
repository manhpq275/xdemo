package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.CommentView;
import vn.mvv.xconnect.utils.AppConstants;

/**
 * Created by admin on 6/15/16.
 */
public class CommentApi extends BaseApi<CommentView> {
    private static CommentApi sInstance;
    public static CommentApi getInstance() {
        if (sInstance == null) sInstance = new CommentApi();
        return sInstance;
    }

    public CommentApi() {
        super("api/comment");
    }

    public String getCommentsForEventApiUrl(int page, int quantity, String eventId) {
        return String.format(AppConstants.API_COMMENT_GET_COMMENTS_FOR_EVENTS_PATTERN, this.getBaseUrl(), page, quantity, eventId);
    }
}