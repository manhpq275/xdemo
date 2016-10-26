package vn.mvv.xconnect.presentations.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.interfaces.iLocationViewCallback;
import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.models.LocationView;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by admin on 6/10/16.
 */
public class DetailEventLocationAdapder extends BaseAdapter  {

    Context context;
    ArrayList<LocationView> items;
    iLocationViewCallback callback;
    public DetailEventLocationAdapder(Context context, ArrayList<LocationView> items,iLocationViewCallback callback) {
        this.context = context;
        this.items = items;
        this.callback = callback;

    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View cView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (cView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            cView = inflater.inflate(R.layout.detail_event_location_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imgLogo = (ImageView) cView.findViewById(R.id.imLogo);
            viewHolder.tvLocation = (TextView) cView.findViewById(R.id.tvLocation);
            viewHolder.tvLocationName = (TextView) cView.findViewById(R.id.tvLocationName);
            viewHolder.tvPhoneNumber = (TextView) cView.findViewById(R.id.tvPhoneNumber);
            viewHolder.tvMaps = (TextView) cView.findViewById(R.id.tvMaps);
            viewHolder.rlGroupMap = (RelativeLayout) cView.findViewById(R.id.rlGroupMap);
            viewHolder.rlGroupMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickMaps(position);
                }
            });
            viewHolder.rlGroupPhone = (RelativeLayout) cView.findViewById(R.id.rlGroupPhone);
            viewHolder.rlGroupPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickCall(position);
                }
            });
            cView.setTag(viewHolder);
            XLog.d(DetailEventLocationAdapder.class, "Item size = " + items.size());
        } else {
            viewHolder = (ViewHolder) cView.getTag();
        }

        setData(viewHolder,items.get(position));
        return cView;
    }

    private void setData(ViewHolder viewHolder,LocationView locationView){
        viewHolder.tvLocation.setText(locationView.getAddress());
        viewHolder.tvLocationName.setText(locationView.getName());
        if((locationView.getPhone()==null) || locationView.getPhone().contains("")){
            viewHolder.rlGroupPhone.setVisibility(View.GONE);
        }else
        {
            viewHolder.rlGroupPhone.setVisibility(View.VISIBLE);
            viewHolder.tvPhoneNumber.setText(locationView.getPhone());
        }


    }
    private static class ViewHolder {
        ImageView imgLogo;
        TextView tvLocationName, tvLocation, tvPhoneNumber, tvMaps;
        RelativeLayout rlGroupPhone,rlGroupMap;
    }
}
