package vn.mvv.xconnect.presentations.adapters;

/**
 * Created by admin on 6/13/16.
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.models.SliderView;
import vn.mvv.xconnect.presentations.activities.EventDetail;
import vn.mvv.xconnect.utils.AppUtils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wang.avi.AVLoadingIndicatorView;

public class ImageSlideAdapter extends PagerAdapter {
    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private ImageLoadingListener imageListener;
    FragmentActivity activity;
    List<SliderView> products;
    EventDetail homeFragment;
    private AVLoadingIndicatorView avloadingIndicatorView;

    public ImageSlideAdapter(FragmentActivity activity, List<SliderView> products,
                             EventDetail homeFragment) {
        this.activity = activity;
        this.homeFragment = homeFragment;
        this.products = products;

        imageListener = new ImageDisplayListener();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vp_image, container, false);

        ImageView mImageView = (ImageView) view
                .findViewById(R.id.image_display);

        avloadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avloadingIndicatorView);
        AppUtils.displayImage(products.get(position).getImageUrl(), mImageView,avloadingIndicatorView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private static class ImageDisplayListener extends
            SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}