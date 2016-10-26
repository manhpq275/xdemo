package vn.mvv.xconnect.services;

import vn.mvv.xconnect.apis.EventApi;
import vn.mvv.xconnect.models.EventView;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class EventService extends BaseService<EventView> {

    public EventService(){
        super(EventApi.getInstance(), EventView.class, EventView[].class);
    }

    private static EventService sInstance;

    public static EventService getInstance() {
        if (sInstance == null) sInstance = new EventService();
        return sInstance;
    }
}
