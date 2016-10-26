package vn.mvv.xconnect.presentations.fragments.tabs.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.interfaces.IGetVendorListCallback;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.VendorView;
import vn.mvv.xconnect.models.enums.SortBy;
import vn.mvv.xconnect.models.enums.SortDirection;
import vn.mvv.xconnect.presentations.activities.HomeActivity;
import vn.mvv.xconnect.presentations.adapters.FavoriteAdapterListView;
import vn.mvv.xconnect.presentations.adapters.FavoriteApdapterGridView;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;
import vn.mvv.xconnect.services.VendorService;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by dgm-dev3 on 5/16/2016.
 */
public class MainFavoriteFragment extends BaseFragment {
    private View convertView;
    private ListView lvFavorites;
    FavoriteAdapterListView lvFavoriteAdapter;
    FavoriteApdapterGridView gvFavoriteAdapter;
    private GridView gridViewFavorites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        convertView = inflater.inflate(R.layout.tab_favorite_main, container, false);
        init();
        return convertView;
    }

    public void init() {
        lvFavorites = (ListView) convertView.findViewById(R.id.lvFavorite);
        gridViewFavorites = (GridView) convertView.findViewById(R.id.membersListGridView);
    }

    //TODO: Just to test
    public void loadData() {
        VendorService.getInstance().getItems(1, 10, "", SortBy.Name, SortDirection.Ascending, new IGetVendorListCallback() {
            @Override
            public void onSuccess(ArrayList<VendorView> items) {
                lvFavoriteAdapter = new FavoriteAdapterListView(getActivity(), items);
                gvFavoriteAdapter = new FavoriteApdapterGridView(getActivity(), items);
                lvFavorites.setAdapter(lvFavoriteAdapter);
                gridViewFavorites.setAdapter(gvFavoriteAdapter);
                XLog.d(HomeActivity.class, "======>Success: " + XConnectApplication.getInstance().getGson().toJson(items));
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                XLog.e(HomeActivity.class, "=====>ERRORS: " + XConnectApplication.getInstance().getGson().toJson(errors));
            }
        });
    }
}
