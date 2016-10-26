package vn.mvv.xconnect.presentations.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.mvv.xconnect.presentations.fragments.tabs.categories.MainCategoryFragment;
import vn.mvv.xconnect.presentations.fragments.tabs.favorites.MainFavoriteFragment;
import vn.mvv.xconnect.presentations.fragments.tabs.home.MainHomeFragment;
import vn.mvv.xconnect.presentations.fragments.tabs.stores.MainStoreFragment;
import vn.mvv.xconnect.presentations.fragments.tabs.near.MainNearByFragment;

/**
 * Created by dgm-dev3 on 5/19/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MainHomeFragment();
            case 1:
                return new MainNearByFragment();
            case 2:
                return new MainFavoriteFragment();
            case 3:
                return new MainCategoryFragment();
            case 4:
                return new MainStoreFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}