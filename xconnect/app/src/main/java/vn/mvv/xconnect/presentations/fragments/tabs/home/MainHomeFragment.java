package vn.mvv.xconnect.presentations.fragments.tabs.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;
import vn.mvv.xconnect.utils.ReplaceFragment;

/**
 * Created by dgm-dev3 on 5/16/2016.
 */
public class MainHomeFragment extends BaseFragment{
    private static final int mTABHOT = 0;
    private static final int mTABFORYOU = 1;
    private static final int mTABNEW = 2;
    private View mView;

    private TextView txtTabHot;
    private TextView txtTabForYou;
    private TextView txtTabNew;

    private LinearLayout mTabHot;
    private LinearLayout mTabForYou;
    private LinearLayout mTabNew;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.tab_home_main, container, false);
        init(mView);
        return mView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    private void init(View view) {

        txtTabHot = (TextView) view.findViewById(R.id.tabHot);
        txtTabForYou = (TextView) view.findViewById(R.id.tabForYou);
        txtTabNew = (TextView) view.findViewById(R.id.tabNew);
        mTabHot = (LinearLayout)view.findViewById(R.id.ln_tabHot);
        mTabHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(0);
            }
        });
        mTabForYou = (LinearLayout)view.findViewById(R.id.ln_tabForYou);
        mTabForYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(1);
            }
        });
        mTabNew = (LinearLayout)view.findViewById(R.id.ln_tabNew);
        mTabNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(2);
            }
        });
        changeTab(-1);
    }

    public void loadData() {}



    private FragmentManager fragmentManager;
    public void changeTab(int position) {
        fragmentManager = getFragmentManager();
        ReplaceFragment replaceFragment = new ReplaceFragment();
        switch (position) {
            case mTABHOT:
                setTabSelected(txtTabHot,true);
                setTabSelected(txtTabForYou,false);
                setTabSelected(txtTabNew,false);
                replaceFragment.replace(fragmentManager, new HotFragment(), null);
                break;
            case mTABFORYOU:
                setTabSelected(txtTabHot,false);
                setTabSelected(txtTabForYou,true);
                setTabSelected(txtTabNew,false);
                replaceFragment.replace(fragmentManager, new ForYouFragment(), null);
                break;
            case mTABNEW:
                setTabSelected(txtTabHot,false);
                setTabSelected(txtTabForYou,false);
                setTabSelected(txtTabNew,true);
                replaceFragment.replace(fragmentManager, new NewFragment(), null);
                break;
            default:
                setTabSelected(txtTabHot,false);
                setTabSelected(txtTabForYou,false);
                setTabSelected(txtTabNew,true);
                fragmentManager
                        .beginTransaction()
                        .add(R.id.content, new NewFragment())
                        .commit();
                break;
        }
    }

    private void setTabSelected(TextView tab,boolean isSelected)
    {
        if (isSelected){
            tab.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorWhite));
        }else{
            tab.setTextColor(ContextCompat.getColor(getActivity(), R.color.nonSelectTab));
        }
    }
}
