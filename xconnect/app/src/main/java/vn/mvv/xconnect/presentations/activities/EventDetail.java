package vn.mvv.xconnect.presentations.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.interfaces.IBaseGetItemCallback;
import vn.mvv.xconnect.interfaces.PageIndicator;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.models.SliderView;
import vn.mvv.xconnect.models.TabInfo;
import vn.mvv.xconnect.models.enums.PromotionType;
import vn.mvv.xconnect.presentations.adapters.DetailEventAdaper;
import vn.mvv.xconnect.presentations.adapters.ImageSlideAdapter;
import vn.mvv.xconnect.services.EventService;
import vn.mvv.xconnect.services.RegionService;
import vn.mvv.xconnect.utils.CirclePageIndicator;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by admin on 6/3/16.
 */
public class EventDetail extends AppCompatActivity implements View.OnClickListener,TabLayout.OnTabSelectedListener {

    private TabLayout mTabCategories;
    private DetailEventAdaper mViewPagerAdapter;
    private ViewPager mPager;
    private ArrayList<TabInfo> tabs = new ArrayList<>();
    private ImageView btBack,imgEventPromotionGift;
    private EventView event;
    private String eventId;

    private TextView tvEventName,tvEventName2,tvNumberOfLikes,tvNumberOfComments,tvEventPromotionValue;



    private ViewPager mViewPagerSlider;
    PageIndicator mIndicatorSlider;
    List<SliderView> products;
    boolean stopSliding = false;
    private Handler handler;
    private Runnable animateViewPager;
    private static final long ANIM_VIEWPAGER_DELAY = 5000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null) onBackPressed();
        eventId = bundle.getString("EventId");
        initView();

    }

    private void initView(){
        this.mPager = (ViewPager) super.findViewById(R.id.pager);
        this.mTabCategories = (TabLayout) findViewById(R.id.tab_layout);
        this.mTabCategories.setOnTabSelectedListener(this);
        this.mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabCategories));
        this.btBack = (ImageView)findViewById(R.id.btBack);
        this.btBack.setOnClickListener(this);
        tvEventName = (TextView) findViewById(R.id.tvEventName);
        tvEventName2 = (TextView)findViewById(R.id.tvEventNameInfo);
        tvNumberOfLikes = (TextView)findViewById(R.id.tvNumberOfLikes);
        tvNumberOfComments = (TextView)findViewById(R.id.tvNumberOfComments);
        tvEventPromotionValue = (TextView)findViewById(R.id.tvEventPromotionValue);
        imgEventPromotionGift = (ImageView)findViewById(R.id.imgEventPromotionGift);
        loadData();
        initializeTabHost();

    }
    private void initializeTabHost() {
        tabs.add(new TabInfo(getString(R.string.txt_info), R.drawable.ic_user));
        tabs.add(new TabInfo(getString(R.string.txt_comment), R.drawable.ic_location));
        tabs.add(new TabInfo(getString(R.string.txt_location), R.drawable.ic_location));

        for (int i = 0; i < tabs.size(); i++) {
            TabInfo tabObj = tabs.get(i);
            mTabCategories.addTab(mTabCategories.newTab().setText(tabObj.getTitle()));
        }
    }
    private void initializeViewPager(EventView event) {
        this.mViewPagerAdapter = new DetailEventAdaper(getSupportFragmentManager(),mTabCategories.getTabCount(),event);
        this.mPager.setAdapter(this.mViewPagerAdapter);
        mViewPagerAdapter.notifyDataSetChanged();
    }
    private void loadData(){
        EventService.getInstance().getItem(eventId, new IBaseGetItemCallback<EventView>() {
            @Override
            public void onSuccess(EventView item) {
                XLog.d(EventDetail.class, "======>Event New Success: " + XConnectApplication.getInstance().getGson().toJson(item));
                event = item;
                initializeViewPager(item);
                setData();
            }
            @Override
            public void onError(ArrayList<ErrorView> errors) {
                XLog.d(EventDetail.class,"=====>ERRORS: "+ XConnectApplication.getInstance().getGson().toJson(errors));
                onBackPressed();
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btBack:
                onBackPressed();
                break;
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    private void setData(){
        tvEventName.setText(event.getName());
        tvEventName2.setText(event.getName());
        tvNumberOfLikes.setText(String.valueOf(event.getNumberOfLikes()));
        tvNumberOfComments.setText(String.valueOf(event.getNumberOfComments()));
        ArrayList<String> list =  event.getImageUrls();
        if(PromotionType.fromInteger(event.getPromotionType())==PromotionType.Discount)
        {
            imgEventPromotionGift.setVisibility(View.GONE);
        }
        products = new ArrayList<>();
        if(list==null) return;
        for(int i=0;i<list.size();i++){
            products.add(new SliderView(i,String.valueOf(i),list.get(i)));
        }
        initSlider();

    }


    private void initSlider(){
        mViewPagerSlider = (ViewPager) findViewById(R.id.view_pager);
        mIndicatorSlider = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicatorSlider.setOnPageChangeListener(new PageChangeListener());
        mViewPagerSlider.addOnPageChangeListener(new PageChangeListener());
        mViewPagerSlider.setAdapter(new ImageSlideAdapter(
                this, products, this));

        mIndicatorSlider.setViewPager(mViewPagerSlider);
        mViewPagerSlider.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_CANCEL:
                        break;

                    case MotionEvent.ACTION_UP:
                        // calls when touch release on ViewPager
                        if (products != null && products.size() != 0) {
                            stopSliding = false;
                            runnable(products.size());
                            handler.postDelayed(animateViewPager,
                                    ANIM_VIEWPAGER_DELAY_USER_VIEW);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // calls when ViewPager touch
                        if (handler != null && stopSliding == false) {
                            stopSliding = true;
                            handler.removeCallbacks(animateViewPager);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {

            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }

    public void runnable(final int size) {
        handler = new Handler();
        animateViewPager = new Runnable() {
            public void run() {
                if (!stopSliding) {
                    if (mViewPagerSlider.getCurrentItem() == size - 1) {
                        mViewPagerSlider.setCurrentItem(0);
                    } else {
                        mViewPagerSlider.setCurrentItem(
                                mViewPagerSlider.getCurrentItem() + 1, true);
                    }
                    handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
                }
            }
        };
    }

}
