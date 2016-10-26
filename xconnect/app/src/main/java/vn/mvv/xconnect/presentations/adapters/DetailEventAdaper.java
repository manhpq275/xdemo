package vn.mvv.xconnect.presentations.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.presentations.fragments.detail.CommentFragment;
import vn.mvv.xconnect.presentations.fragments.detail.EventInfoFragment;
import vn.mvv.xconnect.presentations.fragments.detail.LocationFragment;
import vn.mvv.xconnect.utils.XLog;


/**
 * Created by admin on 6/10/16.
 */
public class DetailEventAdaper extends FragmentStatePagerAdapter {
    int numOfTabs;
    EventView event;

    public DetailEventAdaper(FragmentManager fm, int numOfTabs,EventView event) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.event = event;
    }

    @Override
    public Fragment getItem(int position) {

        XLog.d(DetailEventAdaper.class, "======>DetailEventAdaper: " + XConnectApplication.getInstance().getGson().toJson(event));

        switch (position) {
            case 0:
                return  new EventInfoFragment(this.event);
            case 1:
                return new CommentFragment(this.event);
            case 2:
                return  new LocationFragment(this.event.getLocations());
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
