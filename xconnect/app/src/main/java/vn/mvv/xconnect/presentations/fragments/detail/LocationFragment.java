package vn.mvv.xconnect.presentations.fragments.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.interfaces.iLocationViewCallback;
import vn.mvv.xconnect.models.LocationView;
import vn.mvv.xconnect.presentations.adapters.DetailEventLocationAdapder;
import vn.mvv.xconnect.presentations.customize.CustomDialog;
import vn.mvv.xconnect.presentations.customize.MapDialog;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by admin on 6/10/16.
 */
public class LocationFragment extends BaseFragment implements iLocationViewCallback{

    private ListView lvLocations;
    private ArrayList<LocationView> locationObjs;
    private DetailEventLocationAdapder locationAdapter;
    private MapDialog map;

    public LocationFragment(ArrayList<LocationView> locations) {
        this.locationObjs = locations;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.detail_event_location_fragment, container, false);
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        this.lvLocations = (ListView) mView.findViewById(R.id.lvNews);

        this.lvLocations.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mLoading = (ProgressBar) mView.findViewById(R.id.progress_loading);
        mLoadMore = (ProgressBar) mView.findViewById(R.id.progress_loading_more);
        mNoData = (TextView) mView.findViewById(R.id.tvNoData);
        showLoading();
        initAdapder();
    }

    private void initAdapder() {
        if (locationObjs == null) {
            mNoData.setVisibility(View.VISIBLE);
            this.lvLocations.setVisibility(View.GONE);
            return;
        }
        if (locationObjs.size() == 0) {
            mNoData.setVisibility(View.GONE);
            return;
        }
        XLog.d(LocationFragment.class, "=====> locationObjs.size() = " + locationObjs.size());
        locationAdapter = new DetailEventLocationAdapder(getActivity(), locationObjs, this);
        lvLocations.setAdapter(locationAdapter);
        locationAdapter.notifyDataSetChanged();
        this.lvLocations.setVisibility(View.VISIBLE);
        hideLoading();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void loadData() {
    }

    @Override
    public void onClickCall(int position) {
        XLog.d(LocationFragment.class, "=====> onClickCall: position = " + position);
        LocationView item = locationObjs.get(position);
        if (item.getPhone()==null) return;

        Intent i = new Intent(Intent.ACTION_DIAL);
        String p = "tel:" + item.getPhone();
        i.setData(Uri.parse(p));
        startActivity(i);
    }

    @Override
    public void onClickMaps(int position) {
        XLog.d(LocationFragment.class, "=====> onClickMaps: position = " + position);
        if(map==null ) map = new MapDialog(getActivity());
        if(map.isShowing()) return;
        LocationView item = locationObjs.get(position);
        XLog.d(LocationFragment.class, "=====> onClickMaps: getName() = " + item.getName());
        XLog.d(LocationFragment.class, "=====> onClickMaps: getLatitude() = " + item.getLatitude());
        XLog.d(LocationFragment.class, "=====> onClickMaps: getLongitude() = " + item.getLongitude());
        map.setHeader(item.getName());
        map.setContent(item.getLatitude(), item.getLongitude());
        map.show();
    }
}
