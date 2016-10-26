package vn.mvv.xconnect.presentations.fragments.tabs.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;

/**
 * Created by dgm-dev3 on 5/17/2016.
 */
public class HotFragment  extends BaseFragment {
    private View convertView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        convertView = inflater.inflate(R.layout.tab_home_hot, container, false);

        mLoading = (ProgressBar) convertView.findViewById(R.id.progress_loading);
        mLoadMore = (ProgressBar) convertView.findViewById(R.id.progress_loading_more);
        showLoading();
        return convertView;
    }

    @Override
    protected void showLoading()
    {
        super.showLoading();




    }
    public void loadData() {}
}
