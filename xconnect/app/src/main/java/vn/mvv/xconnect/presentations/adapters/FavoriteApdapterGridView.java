package vn.mvv.xconnect.presentations.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.models.VendorView;

/**
 * Created by dgm-dev3 on 5/30/2016.
 */
public class FavoriteApdapterGridView extends BaseAdapter {
    Context context;
    List<VendorView> items;

    public FavoriteApdapterGridView(Context context, List<VendorView> items) {
        this.context = context;
        this.items = items;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(R.layout.custom_grid_view_more_vendor_favorite, null);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.tvTitleVenDor = (TextView) convertView.findViewById(R.id.tvVenDorFavorite);
            viewHolder.imgLogoUrlVendor = (ImageView) convertView.findViewById(R.id.imgVenDorFavorite);
            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        VendorView vendor = items.get(position);
        if (vendor != null) {
            viewHolder.tvTitleVenDor.setText(vendor.getName());
//            Picasso.with(context)
//                    .load(vendor.getLogoUrl()).placeholder(R.drawable.progress_animation)
//                    .into(viewHolder.imgLogoUrlVendor);
        }

        //TODO: Convert UTC time to local time
        //viewHolder.tvDateTimeHomeNew.setText(event.getEndDate().toString());
        //viewHolder.tvCommentHomeNew.setText(homeNew.getTxtCommentCoupons());
        //viewHolder.tvBigCouponHomeNew.setText(homeNew.getTxtBigPerCentCoupons());
        //viewHolder.tvFavoritesHomeNew.setText(homeNew.getTxtFavoritesCoupons());
        return convertView;
    }

    static class ViewHolder {
        ImageView imgLogoUrlVendor;
        TextView tvTitleVenDor;

    }

}
