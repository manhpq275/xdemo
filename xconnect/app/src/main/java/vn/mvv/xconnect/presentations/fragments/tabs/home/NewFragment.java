package vn.mvv.xconnect.presentations.fragments.tabs.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.interfaces.IGetEventListCallBack;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.models.enums.SortBy;
import vn.mvv.xconnect.models.enums.SortDirection;
import vn.mvv.xconnect.presentations.activities.EventDetail;
import vn.mvv.xconnect.presentations.adapters.HomeNewAdapter;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;
import vn.mvv.xconnect.services.EventService;
import vn.mvv.xconnect.utils.XLog;


/**
 * Created by dgm-dev3 on 5/17/2016.
 */
public class NewFragment  extends BaseFragment {
    private View mView;
    private ListView lvNews;
    private HomeNewAdapter homeNewAdapter;
    private int mPage;
    private long curentTime;
    private final long timeOut = 500;
    public SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<EventView> newsObjs;
    private boolean isLoading = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        curentTime = 0;
        this.mView = inflater.inflate(R.layout.tab_home_new, container, false);
        this.lvNews=(ListView)mView.findViewById(R.id.lvNews);
        this.swipeRefreshLayout = (SwipeRefreshLayout)mView.findViewById(R.id.swipe_refresh_layout);
        this.mLoadMore = (ProgressBar)mView.findViewById(R.id.progress_loading_more);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefesh();
            }
        });
        this.lvNews.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (lvNews == null || lvNews.getChildCount() == 0) ?
                                0 : lvNews.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
                if (isLoading && visibleItemCount > 0) {
                    if ((firstVisibleItem + visibleItemCount) >= totalItemCount) {
                        loadMore();
                    }
                }
            }
        });
        newsObjs = new ArrayList<>();
        homeNewAdapter=new HomeNewAdapter(getActivity(),newsObjs);
        lvNews.setAdapter(homeNewAdapter);
        lvNews.setOnItemClickListener(onClickItemListNews);
        mLoading = (ProgressBar) mView.findViewById(R.id.progress_loading);
        mLoadMore = (ProgressBar) mView.findViewById(R.id.progress_loading_more);
        mNoData = (TextView) mView.findViewById(R.id.tvNoData);
        showLoading();
        return mView;
    }

    @Override
    protected void showLoading()
    {
        super.showLoading();
        this.lvNews.setVisibility(View.INVISIBLE);
        mPage = 0;
        loadData();
    }

    private void loadMore()
    {
        if(System.currentTimeMillis() - curentTime < timeOut){
            return;
        }
        isLoading = false;
        XLog.d(NewFragment.class,"Load More");
        showLoadMore();
        loadData();
    }
    private void pullRefesh()
    {
        if(System.currentTimeMillis() - curentTime < timeOut){
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        XLog.d(NewFragment.class,"Pull Refesh");
        mPage=0;
        newsObjs.clear();
        swipeRefreshLayout.setRefreshing(true);
        loadData();

    }



    public void loadData() {
        mPage++;
        EventService.getInstance().getItems(mPage,10, "", SortBy.EditedBy, SortDirection.Descending, new IGetEventListCallBack() {
            @Override
            public void onSuccess(ArrayList<EventView> items) {
                lvNews.setVisibility(View.VISIBLE);
                for(int i = 0;i<items.size();i++)
                {
                    newsObjs.add(items.get(i));
                }
                if(items.size()==0){
                    isLoading = false;
                    if(curentTime == 0)
                    {
                        hideLoading();
                        mNoData.setVisibility(View.VISIBLE);
                    }
                }else{
                    isLoading = true;
                }
                homeNewAdapter.notifyDataSetChanged();
                hideLoading();
                XLog.d(NewFragment.class, "======>Event New Success: " + XConnectApplication.getInstance().getGson().toJson(items));
                swipeRefreshLayout.setRefreshing(false);
                curentTime =System.currentTimeMillis();
            }
            @Override
            public void onError(ArrayList<ErrorView> errors) {
                XLog.e(NewFragment.class, "=====>ERRORS: "+ XConnectApplication.getInstance().getGson().toJson(errors));
                hideLoading();
                swipeRefreshLayout.setRefreshing(false);
                mNoData.setVisibility(View.VISIBLE);
            }
        });
    }

    private AdapterView.OnItemClickListener onClickItemListNews = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), EventDetail.class);
            intent.putExtra("EventId",newsObjs.get(position).getId());
            XLog.e(NewFragment.class, "=====>EventId: "+ newsObjs.get(position).getId());
            startActivity(intent);
        }
    };
}
