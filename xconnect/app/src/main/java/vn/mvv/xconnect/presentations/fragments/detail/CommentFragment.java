package vn.mvv.xconnect.presentations.fragments.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.interfaces.IGetCommentsCallback;
import vn.mvv.xconnect.models.CommentView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.presentations.adapters.DetailEventCommentsAdapder;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;
import vn.mvv.xconnect.services.CommentService;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by admin on 6/10/16.
 */
public class CommentFragment extends BaseFragment {

    DetailEventCommentsAdapder listAdapter;
    ExpandableListView expListView;
    ArrayList<CommentView> listDataHeader;
    HashMap<String, ArrayList<CommentView>> listDataChild;

    private int mPage = 0;
    private boolean isLoading;
    public EventView event;
    private long curentTime = 0;

    public CommentFragment(EventView event) {
        this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.detail_event_comments_fragment, container, false);
        initAdapder();
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<String, ArrayList<CommentView>>();
        expListView = (ExpandableListView) mView.findViewById(R.id.expandedComments);
        mPage = 0;

        listAdapter = new DetailEventCommentsAdapder(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        loadData();

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        // initAdapder();
        //  showLoading();

    }

    @Override
    protected void showLoading() {
        super.showLoading();
      //  this.lvCommentView.setVisibility(View.INVISIBLE);
        mPage = 0;

    }

    private void initAdapder() {
//        listCommentObjs = new ArrayList<>();
//        commentsAdapter=new DetailEventCommentsAdapder(getActivity(),listCommentObjs,false);
//        lvCommentView.setAdapter(commentsAdapter);
        //  lvCommentView.setOnItemClickListener(commentsAdapter);
        mLoading = (ProgressBar) mView.findViewById(R.id.progress_loading);
        mLoadMore = (ProgressBar) mView.findViewById(R.id.progress_loading_more);
        mNoData = (TextView) mView.findViewById(R.id.tvNoData);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void loadData() {
        mPage++;

        CommentService.getInstance().getCommentsForEvent(mPage, 10, this.event.getId(), new IGetCommentsCallback() {
            @Override
            public void onSuccess(ArrayList<CommentView> items) {
//                lvCommentView.setVisibility(View.VISIBLE);
                for (int i = 0; i < items.size(); i++) {
                    listDataHeader.add(items.get(i));

                    // Adding child data
                    ArrayList<CommentView> childs = items.get(i).getChilds();
                    listDataChild.put(listDataHeader.get(i).getId(), childs);
                }
                if (items.size() == 0) {
                    isLoading = false;
                    if (curentTime == 0) {
                        hideLoading();
                        mNoData.setVisibility(View.VISIBLE);
                    }
                } else {
                    isLoading = true;
                }

                listAdapter.notifyDataSetChanged();
                hideLoading();
                XLog.d(CommentFragment.class, "======>CommentFragment New Success: " + XConnectApplication.getInstance().getGson().toJson(items));
                //    swipeRefreshLayout.setRefreshing(false);
                curentTime = System.currentTimeMillis();
            }

            @Override
            public void onError(ArrayList<ErrorView> errors) {
                XLog.e(CommentFragment.class, "=====>ERRORS: " + XConnectApplication.getInstance().getGson().toJson(errors));
//                hideLoading();
//                swipeRefreshLayout.setRefreshing(false);
                mNoData.setVisibility(View.VISIBLE);
            }
        });
    }

}
