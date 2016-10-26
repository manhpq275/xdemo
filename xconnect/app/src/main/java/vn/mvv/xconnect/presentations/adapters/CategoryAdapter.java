package vn.mvv.xconnect.presentations.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import vn.mvv.xconnect.models.CategoryView;
import vn.mvv.xconnect.R;
import vn.mvv.xconnect.utils.AppUtils;

import java.util.List;

/**
 * Created by dgm-dev3 on 5/17/2016.
 */
public class CategoryAdapter extends BaseAdapter {
    Context context;
    List<CategoryView> items;

    public CategoryAdapter(Context context, List<CategoryView> items) {
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

            convertView = inflater.inflate(R.layout.custom_gridview, null);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.txtCategory = (TextView) convertView.findViewById(R.id.txtCategory);
            viewHolder.imgCategory = (ImageView) convertView.findViewById(R.id.imgCategory);
            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CategoryView categoryView = items.get(position);
        viewHolder.imgCategory.setImageResource(categoryView.getImgIcon());
        viewHolder.txtCategory.setText(categoryView.getName());
        return convertView;
    }

    static class ViewHolder {
        ImageView imgCategory;
        TextView txtCategory;
    }
}
