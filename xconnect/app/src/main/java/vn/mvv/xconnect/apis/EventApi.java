package vn.mvv.xconnect.apis;

import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.models.enums.Region;
import vn.mvv.xconnect.utils.AppConstants;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class EventApi extends BaseApi<EventView> {
    private static EventApi sInstance;

    public static EventApi getInstance() {
        if (sInstance == null) sInstance = new EventApi();
        return sInstance;
    }

    public EventApi(){
        super("api/event");
    }
    public String getRecentEventsApiUrl(int page, int quantity, Region region, String categoryId) {
        return String.format(AppConstants.API_EVENT_GET_RECENT_EVENTS_PATTERN, this.getBaseUrl(), page, quantity, region, categoryId);
    }
}
