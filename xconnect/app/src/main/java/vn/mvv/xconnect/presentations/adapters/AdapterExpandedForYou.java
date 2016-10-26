package vn.mvv.xconnect.presentations.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.models.GroupExpandHomeForyou;
import vn.mvv.xconnect.models.VendorView;

/**
 * Created by dgm-dev3 on 5/30/2016.
 */
public class AdapterExpandedForYou extends BaseExpandableListAdapter {
    Context context;
    ArrayList<GroupExpandHomeForyou> groupItems;

    public AdapterExpandedForYou(Context context, ArrayList<GroupExpandHomeForyou> groupItems) {
        this.context = context;
        this.groupItems = groupItems;
    }

    @Override
    public int getGroupCount() {
        return groupItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<EventView> chList = groupItems.get(groupPosition).getArrItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<EventView> chList = groupItems.get(groupPosition).getArrItems();
        return chList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupExpandHomeForyou group_expandable = (GroupExpandHomeForyou) getGroup(groupPosition);
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.custom_group_expand_tab_main_for_you, null);
            holder=new ViewHolder();
            VendorView vendorView=group_expandable.getVendorView();
            holder.descriptionEvent.setText(vendorView.getName());
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHolder {
        private TextView titleVenDor;
        private TextView descriptionEvent;
        private TextView promotionValue;
        private TextView endDate;
        private ImageView LogoVendor;
    }
}
