package vn.mvv.xconnect.presentations.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.mvv.xconnect.R;

/**
 * Created by dgm-dev3 on 5/19/2016.
 */
public class SearchFragment extends Fragment {
    private View convertView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        convertView = inflater.inflate(R.layout.fragment_search, container, false);

        return convertView;
    }

}
