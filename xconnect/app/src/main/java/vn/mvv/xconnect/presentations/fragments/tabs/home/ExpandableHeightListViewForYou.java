package vn.mvv.xconnect.presentations.fragments.tabs.home;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
/**
 * Created by dgm-dev3 on 5/25/2016.
 */
public class ExpandableHeightListViewForYou extends ListView{


        boolean expanded = false;

        public ExpandableHeightListViewForYou(Context context)
        {
            super(context);
        }

        public ExpandableHeightListViewForYou(Context context, AttributeSet attrs)
        {
            super(context, attrs);
        }

        public ExpandableHeightListViewForYou(Context context, AttributeSet attrs, int defStyle)
        {
            super(context, attrs, defStyle);
        }

    public boolean isExpanded()
    {
        return expanded;
    }



    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        if (isExpanded())
        {
            int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();

        }
        else
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
    }
}
