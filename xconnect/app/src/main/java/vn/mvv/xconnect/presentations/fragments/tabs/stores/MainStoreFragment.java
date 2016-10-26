package vn.mvv.xconnect.presentations.fragments.tabs.stores;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by dgm-dev3 on 5/16/2016.
 */
public class MainStoreFragment  extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View V = inflater.inflate(R.layout.tab_store_main, container, false);
        XLog.d(this.getClass(), "=======> STORE");
        return V;
    }
    public void loadData() {}
}
