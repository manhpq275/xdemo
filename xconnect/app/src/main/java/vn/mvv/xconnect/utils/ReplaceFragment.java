package vn.mvv.xconnect.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import vn.mvv.xconnect.R;

/**
 * Created by admin on 6/8/16.
 */
public class ReplaceFragment {
    private static final String TAG = "ReplaceFragment";

    public void openOutsideTabFragment(Class<? extends Fragment> fragmentClass, Bundle... bundles) {

        Bundle bundle;
        if (bundles != null && bundles.length >= 1) {
            bundle = bundles[0];
        } else {
            bundle = new Bundle();
        }

//        openFragment(fragmentClass, bundle);
    }

    public void replace(FragmentManager manager, Fragment fragmentClazz,
                        Bundle args) {
        String backStateName = fragmentClazz.getClass().getName();//Class();

//        transaction.commitAllowingStateLoss();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        Log.d(TAG,"backStateName = " +backStateName);
        Log.d(TAG,"fragmentPopped = " +fragmentPopped);
        if (!fragmentPopped) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack(backStateName);
            transaction.replace(R.id.content, fragmentClazz);
//          Commit the transaction
            transaction.commit();
        }
    }
}
