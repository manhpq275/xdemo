package vn.mvv.xconnect.presentations.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.models.CommentView;
import vn.mvv.xconnect.utils.AppUtils;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by admin on 6/15/16.
 */
public class DetailEventCommentsAdapder extends BaseExpandableListAdapter {


    private Context _context;
    private ArrayList<CommentView> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<CommentView>> _listDataChild;


    public DetailEventCommentsAdapder(Context context, ArrayList<CommentView> listDataHeader,
                                      HashMap<String, ArrayList<CommentView>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;

    }

    @Override
    public CommentView getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getId())
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View cView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).getConsumerName();

        if (cView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cView = infalInflater.inflate(R.layout.detail_event_comment_item, null);
            cView.setPadding(80, 0, 0, 0);
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.imConsumerAvatar = (CircularImageView) cView.findViewById(R.id.imConsumerAvatar);
        viewHolder.tvComment = (TextView) cView.findViewById(R.id.tvComment);
        viewHolder.tvCommentDate = (TextView) cView.findViewById(R.id.tvCommentDate);
        viewHolder.tvReply = (TextView) cView.findViewById(R.id.tvReply);
        viewHolder.tvReply.setVisibility(View.GONE);
        viewHolder.tvConsumerName = (TextView) cView.findViewById(R.id.tvConsumerName);
        setData(viewHolder, getChild(groupPosition, childPosition));
        return cView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getId())
                .size();
    }

    @Override
    public CommentView getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View cView, ViewGroup parent) {
        if (cView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cView = infalInflater.inflate(R.layout.detail_event_comment_item, null);
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.imConsumerAvatar = (CircularImageView) cView.findViewById(R.id.imConsumerAvatar);
        viewHolder.tvComment = (TextView) cView.findViewById(R.id.tvComment);
        viewHolder.tvCommentDate = (TextView) cView.findViewById(R.id.tvCommentDate);
        viewHolder.tvReply = (TextView) cView.findViewById(R.id.tvReply);
        viewHolder.tvConsumerName = (TextView) cView.findViewById(R.id.tvConsumerName);
        setData(viewHolder, _listDataHeader.get(groupPosition));
        return cView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolder {
        CircularImageView imConsumerAvatar;
        TextView tvConsumerName, tvCommentDate, tvComment, tvReply;
        RelativeLayout rlGroupPhone, rlGroupMap, lsChild;
    }

    private void setData(ViewHolder viewHolder, CommentView item) {
        if(item.getConsumerAvatarUrl()==null){
            String name = item.getConsumerName();
            String tmpDraw = "";
            if(name.contains(" "))
            {
                tmpDraw += name.split(" ")[0].charAt(0) +name.split(" ")[1].charAt(0);
            }
            Bitmap bmp =AppUtils.drawTextToBitmap(_context,R.drawable.ic_avatar,tmpDraw);
            viewHolder.imConsumerAvatar.setImageBitmap(bmp);
        }else
        {
            AppUtils.displayImage(item.getConsumerAvatarUrl(), viewHolder.imConsumerAvatar);
        }


        viewHolder.tvComment.setText(item.getContents());
        viewHolder.tvCommentDate.setText(AppUtils.convertDateToAgoTime(_context, item.getCommentDate()));
        viewHolder.tvConsumerName.setText(item.getConsumerName());
    }


}
