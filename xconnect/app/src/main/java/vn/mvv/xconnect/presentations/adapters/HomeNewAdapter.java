package vn.mvv.xconnect.presentations.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.models.VendorView;
import vn.mvv.xconnect.models.enums.PromotionType;
import vn.mvv.xconnect.models.enums.PromotionUnit;
import vn.mvv.xconnect.utils.AppUtils;

/**
 * Created by mr_phuc on 5/25/2016.
 */
public class HomeNewAdapter extends BaseAdapter {
    Context context;
    List<EventView> items;

    public HomeNewAdapter(Context context, List<EventView> items) {
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

            convertView = inflater.inflate(R.layout.custom_listview_tab_home_new, null);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.tvVendorName = (TextView) convertView.findViewById(R.id.tvVenDorName);
            viewHolder.tvEventName = (TextView) convertView.findViewById(R.id.tvEventName);
            viewHolder.avloadingIndicatorView = (AVLoadingIndicatorView) convertView.findViewById(R.id.avloadingIndicatorView);
            viewHolder.avloadingIndicatorLogo = (AVLoadingIndicatorView) convertView.findViewById(R.id.avloadingIndicatorLogo);
            viewHolder.tvEventEndDate = (TextView) convertView.findViewById(R.id.tvEventEndDate);
            viewHolder.tvEventNumberOfComments = (TextView) convertView.findViewById(R.id.tvNumberOfComments);
            viewHolder.tvEventPromotionValue = (TextView) convertView.findViewById(R.id.tvEventPromotionValue);
            viewHolder.tvEventNumberOfLikes = (TextView) convertView.findViewById(R.id.tvNumberOfLikes);
            viewHolder.imgLogoUrlVendor = (ImageView) convertView.findViewById(R.id.imgVendorLogo);
            viewHolder.coverImageUrl = (ImageView) convertView.findViewById(R.id.imEventCover);
            ImageView x =  (ImageView)convertView.findViewById(R.id.imgOverLay);
            x.setAlpha(Float.parseFloat("0.15"));
            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        EventView event = items.get(position);

        AppUtils.displayImage(event.getCoverImageUrl(), viewHolder.coverImageUrl, viewHolder.avloadingIndicatorView);
        VendorView vendorView = event.getVendor();
        if (vendorView != null) {
            viewHolder.tvVendorName.setText(vendorView.getName());
            AppUtils.displayImage(vendorView.getLogoUrl(), viewHolder.imgLogoUrlVendor,viewHolder.avloadingIndicatorLogo);
        }
        checkEventPromotionType(event, viewHolder.tvEventPromotionValue);
        viewHolder.tvEventName.setText(event.getName());
        viewHolder.tvEventEndDate.setText(AppUtils.convertToLocalTime(event.getEndDate()));
        viewHolder.tvEventNumberOfComments.setText(String.valueOf(event.getNumberOfComments()));
        viewHolder.tvEventNumberOfLikes.setText(String.valueOf(event.getNumberOfLikes()));
        return convertView;
    }

    static class ViewHolder {
        ImageView imgLogoUrlVendor, coverImageUrl;
        TextView tvEventName, tvEventPromotionValue, tvEventEndDate, tvEventNumberOfLikes, tvEventNumberOfComments, tvVendorName;
        private AVLoadingIndicatorView avloadingIndicatorView,avloadingIndicatorLogo;

    }

    private void checkEventPromotionType(EventView event, TextView tvEventPromotionValue) {
        if (event == null)
            return;
        tvEventPromotionValue.setVisibility(View.VISIBLE);
        PromotionType eventPromotionType = PromotionType.fromInteger(event.getPromotionType());
        if (eventPromotionType == PromotionType.Discount) {
            PromotionUnit eventPromotionUnit = PromotionUnit.fromInteger(event.getPromotionUnit());
            long promotionValue= Math.round(event.getPromotionValue());
            switch (eventPromotionUnit) {
                case Percent:
                    tvEventPromotionValue.setText(String.valueOf(promotionValue) + "%");
                    break;
                case Usd:
                case Vnd:
                    tvEventPromotionValue.setText(AppUtils.formatMoney(promotionValue));
                    break;
            }

        } else {
            tvEventPromotionValue.setVisibility(View.GONE);
        }
    }
}
