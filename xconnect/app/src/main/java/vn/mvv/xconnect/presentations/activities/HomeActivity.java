package vn.mvv.xconnect.presentations.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;


import java.util.ArrayList;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.models.TabInfo;
import vn.mvv.xconnect.services.RegionService;
import vn.mvv.xconnect.utils.XLog;
import vn.mvv.xconnect.presentations.adapters.PagerAdapter;
/**
 * Created by admin on 6/3/16.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener,TabLayout.OnTabSelectedListener {

    private static final String TAG = "HomeActivity";
    private Spinner spnRegions;
    private TabLayout mTabCategories;
    private int mTabSelect;
    private android.support.v4.widget.DrawerLayout mMenu;
    private AlphaAnimation fadeOut = new AlphaAnimation(1.0f , 0.0f );
    private PagerAdapter mViewPagerAdapter;
    private ViewPager mPager;
    private ArrayList<TabInfo> tabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

    }

    private void initView(){
        findViewById(R.id.btnMenu).setOnClickListener(this);
        findViewById(R.id.btnSearch).setOnClickListener(this);
        this.mPager = (ViewPager) super.findViewById(R.id.pager);
        this.mTabCategories = (TabLayout) findViewById(R.id.tab_layout);
        this.mTabCategories.setOnTabSelectedListener(this);
        this.mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabCategories));
        this.spnRegions = (Spinner) findViewById(R.id.spinnerRegions);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_city, RegionService.getInstance().getRegions());
        spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_city);
        this.spnRegions.setAdapter(spinnerArrayAdapter);
        this.mMenu = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return false;
            }
        });
        initializeTabHost();
        initializeViewPager();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnMenu:
            {
                mMenu.openDrawer(GravityCompat.START);
                XLog.d(TAG,"======> btnMenuClick");
                break;
            }
            case R.id.btnSearch:
            {
                XLog.d(TAG,"======> btnSearchClick");
                break;
            }
        }

    }

    private void initializeTabHost() {
        tabs.add(new TabInfo(getString(R.string.tab_home), R.drawable.ic_home));
        tabs.add(new TabInfo(getString(R.string.tab_nearby), R.drawable.ic_location));
        tabs.add(new TabInfo(getString(R.string.tab_favorite), R.drawable.ic_heart));
        tabs.add(new TabInfo(getString(R.string.tag_category), R.drawable.ic_categories));
        tabs.add(new TabInfo(getString(R.string.tab_store), R.drawable.ic_user));
        fadeOut.setDuration(3000);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                for (int i = 0; i < mTabCategories.getTabCount(); i++) {
                    mTabCategories.getTabAt(i).setText("");
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        for (int i = 0; i < tabs.size(); i++) {
            TabInfo tabObj = tabs.get(i);
            mTabCategories.addTab(mTabCategories.newTab().setText(tabObj.getTitle()).setIcon(tabObj.getIcon()));
            TextView tv = (TextView)(((LinearLayout)((LinearLayout)mTabCategories.getChildAt(0)).getChildAt(i)).getChildAt(1));
            tv.startAnimation(fadeOut);
        }
        aminationTitleTab();
    }
    private void aminationTitleTab(){
        for (int i = 0; i < mTabCategories.getTabCount(); i++) {
            TabInfo tabInfo = tabs.get(i);
            mTabCategories.getTabAt(i).setText(tabInfo.getTitle());
            TextView tv = (TextView)(((LinearLayout)((LinearLayout)mTabCategories.getChildAt(0)).getChildAt(i)).getChildAt(1));
            tv.startAnimation(fadeOut);
        }
    }
    private void initializeViewPager() {
        this.mViewPagerAdapter = new PagerAdapter(getSupportFragmentManager(),mTabCategories.getTabCount());
        this.mPager.setAdapter(this.mViewPagerAdapter);
        mViewPagerAdapter.notifyDataSetChanged();

    }
    @Override
    public void onBackPressed() {
        if (mMenu.isDrawerOpen(GravityCompat.START)) {
            mMenu.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mTabSelect = tab.getPosition();
        XLog.d(TAG,"======> mTabSelect : "+mTabSelect);
        aminationTitleTab();
        this.mPager.setCurrentItem(mTabSelect);
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
