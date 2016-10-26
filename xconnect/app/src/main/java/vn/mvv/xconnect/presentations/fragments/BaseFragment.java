package vn.mvv.xconnect.presentations.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by phuc.nguyen on 5/24/2016.
 */
public abstract class BaseFragment extends Fragment {
    protected ProgressBar mLoading;
    protected ProgressBar mLoadMore;
    protected TextView mNoData;
    protected View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void showLoading(){
        if(mLoading != null)        mLoading.setVisibility(View.VISIBLE);
        if(mLoadMore != null)       mLoadMore.setVisibility(View.INVISIBLE);
    }

    protected void showLoadMore(){
        if(mLoading != null)        mLoading.setVisibility(View.INVISIBLE);
        if(mLoadMore != null)       mLoadMore.setVisibility(View.VISIBLE);
    }

    protected void hideLoading(){
        if(mLoading != null)        mLoading.setVisibility(View.INVISIBLE);
        if(mLoadMore != null)       mLoadMore.setVisibility(View.INVISIBLE);
    }

    public abstract void loadData();

}
