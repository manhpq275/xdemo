package vn.mvv.xconnect.presentations.fragments.tabs.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.interfaces.IGetCategoryListCallback;
import vn.mvv.xconnect.models.CategoryView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.presentations.activities.HomeActivity;
import vn.mvv.xconnect.presentations.adapters.CategoryAdapter;
import vn.mvv.xconnect.R;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;
import vn.mvv.xconnect.services.CategoryService;
import vn.mvv.xconnect.utils.XLog;

import java.util.ArrayList;

/**
 * Created by dgm-dev3 on 5/16/2016.
 */
public class MainCategoryFragment extends BaseFragment {
    private CategoryAdapter categoryAdapter;
    private ArrayList<CategoryView> categories;
    private GridView gridViewCategories;
    private View convertView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        convertView = inflater.inflate(R.layout.tab_category_main, container, false);
        return convertView;
    }

    public void loadData() {
        CategoryService.getInstance().getCategories(new IGetCategoryListCallback() {
            @Override
            public void onSuccess(ArrayList<CategoryView> items) {
                XLog.d(HomeActivity.class, "======>Success: " + XConnectApplication.getInstance().getGson().toJson(items));
                gridViewCategories = (GridView) convertView.findViewById(R.id.gridCategories);
                categoryAdapter = new CategoryAdapter(getActivity(), items);
                gridViewCategories.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                XLog.e(HomeActivity.class, "=====>ERRORS: "+ XConnectApplication.getInstance().getGson().toJson(errors));
            }
        });
    }
}
