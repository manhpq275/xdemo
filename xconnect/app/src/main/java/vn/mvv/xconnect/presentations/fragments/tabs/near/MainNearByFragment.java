package vn.mvv.xconnect.presentations.fragments.tabs.near;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;

/**
 * Created by dgm-dev3 on 5/16/2016.
 */
public class MainNearByFragment  extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View V = inflater.inflate(R.layout.tab_near_by_main, container, false);
        return V;
    }
    public void loadData() {}
}
