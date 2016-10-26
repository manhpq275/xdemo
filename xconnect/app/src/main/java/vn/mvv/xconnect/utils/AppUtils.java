package vn.mvv.xconnect.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TimeZone;
import java.util.TreeMap;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.models.ConsumerView;
import vn.mvv.xconnect.models.ErrorView;

/**
 * Created by phuc.nguyen on 5/17/2016.
 */
public class AppUtils {

    public static String buildTokenValue(String token) {
        return "Bearer " + token;
    }

    public static ArrayList<ErrorView> parseErrorViews(String json) {
        Gson gson = createGsonInstance(FieldNamingPolicy.IDENTITY);
        ArrayList<ErrorView> errorViews = new ArrayList<>();
        ErrorView[] parses = gson.fromJson(json, ErrorView[].class);
        errorViews.addAll(Arrays.asList(parses));
        return errorViews;
    }

    public static int getCategoryIcon(String uniqueKey) {
        switch (uniqueKey) {
            case "food":
                return R.drawable.ic_food;
            case "beauty":
                return R.drawable.ic_fashion;
            case "dulich":
                return R.drawable.ic_vocation;
            case "realestate":
                return R.drawable.ic_drink;
        }
        return 0;
    }

    public static String getImageUrlBySize(String originUrl, String imageSizeKey){
        int lastDotIndex = originUrl.lastIndexOf('.');
        return originUrl.substring(0, lastDotIndex) +"-"+ imageSizeKey + originUrl.substring(lastDotIndex, originUrl.length());
    }

    public static Gson createGsonInstance(FieldNamingPolicy fieldNamingPolicy) {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(fieldNamingPolicy)
                .serializeNulls()
                .create();
    }

    public static String getResourceStringByKey(String key) {
        int text_id = XConnectApplication.getInstance().getResources().getIdentifier(key, "string", XConnectApplication.getInstance().getPackageName());
        try {
            return XConnectApplication.getInstance().getString(text_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static boolean hasInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static String imageSizeKey;

    public static void displayImage(String imageUrl, ImageView imageView) {
        try {
            ImageLoader.getInstance().displayImage(imageUrl, imageView);
        } catch (Exception ex) {
            XLog.d(AppUtils.class, ex.getMessage());
        }
    }

    public static void displayImage(String imageUrl, final ImageView imageView, final int failResImage, final int loadingResImage) {
        try {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .displayer(new FadeInBitmapDisplayer(500))
                    .resetViewBeforeLoading(true)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .showImageOnFail(failResImage)
                    .showImageOnLoading(loadingResImage)
                    .build();
            ImageLoader.getInstance().displayImage(imageUrl, imageView, options);
        } catch (Exception ex) {
            XLog.d(AppUtils.class, ex.getMessage());
        }
    }

    public static void displayImage(String imageUrl, final ImageView imageView, final AVLoadingIndicatorView avLoadingIndicatorView) {
        try {
            avLoadingIndicatorView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .displayer(new FadeInBitmapDisplayer(500))
                    .resetViewBeforeLoading(true)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    //.showImageOnFail(failResImage)
                    .build();
            ImageLoader.getInstance().displayImage(imageUrl, imageView, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                    avLoadingIndicatorView.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    avLoadingIndicatorView.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {
                    avLoadingIndicatorView.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception ex) {
            XLog.d(AppUtils.class, ex.getMessage());
        }
    }


    public static String convertToLocalTime(Date dateTimeUtc){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(dateTimeUtc);
    }

    public static void setImageSizeKey(int widthScreen)
    {
        if(widthScreen<=50){
            imageSizeKey = AppConstants.IMAGEX50KEY;
        }else if (widthScreen <= 100){
            imageSizeKey = AppConstants.IMAGEX100KEY;
        }else if (widthScreen <= 200){
            imageSizeKey = AppConstants.IMAGEX200KEY;
        }else if (widthScreen <=400){
            imageSizeKey = AppConstants.IMAGEX400KEY;
        }else if (widthScreen <=600){
            imageSizeKey = AppConstants.IMAGEX600KEY;
        }else if (widthScreen <=800){
            imageSizeKey = AppConstants.IMAGEX800KEY;
        }else{
            imageSizeKey = AppConstants.IMAGEX1200KEY;
        }
    }

    public static String formatMoney(long value) {
        if (value == Long.MIN_VALUE) return formatMoney(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + formatMoney(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "m");
        suffixes.put(1_000_000_000L, "b");
        suffixes.put(1_000_000_000_000L, "t");
        suffixes.put(1_000_000_000_000_000L, "p");
        suffixes.put(1_000_000_000_000_000_000L, "e");
    }

    public static String convertDateToAgoTime(Context context, Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date d = df.parse(df.format(date));
            return prettyTime(context, d);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static String prettyTime(Context context, Date past) {
        Date currentDate = new Date();
        long mills = currentDate.getTime() - past.getTime();
        float sec = Math.abs(mills) / 1000f;
        float mins = sec / 60f;
        float hours = mins / 60f;
        float days = hours / 24f;
        float years = days / 365f;

        if (sec < 45) return context.getString(R.string.just_now);
        if (sec < 90)
            return context.getString(R.string.one) +" "+ context.getString(R.string.minutes_ago);
        if (mins < 45) return Math.round(mins) +" "+ context.getString(R.string.minutes_ago);
        if (mins < 90)
            return context.getString(R.string.one) +" "+ context.getString(R.string.hours_ago);
        if (hours < 24) return Math.round(hours) +" "+ context.getString(R.string.hours_ago);
        if (hours < 42)
            return context.getString(R.string.one) +" "+ context.getString(R.string.days_ago);
        if (days < 30) return Math.round(days) +" "+ context.getString(R.string.days_ago);
        if (days < 45)
            return context.getString(R.string.one) +" "+ context.getString(R.string.months_ago);
        if (days < 365) return Math.round(days / 30) +" "+ context.getString(R.string.months_ago);
        if (years < 1.5)
            return context.getString(R.string.one) +" "+ context.getString(R.string.years_ago);
        return years +" "+ context.getString(R.string.years_ago);
    }

    public static Bitmap drawTextToBitmap(Context gContext,
                                   int gResId,
                                   String gText) {
        Resources resources = gContext.getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap bitmap =
                BitmapFactory.decodeResource(resources, gResId);

        android.graphics.Bitmap.Config bitmapConfig =
                bitmap.getConfig();
        // set default bitmap config if none
        if(bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        bitmap = bitmap.copy(bitmapConfig, true);

        Canvas canvas = new Canvas(bitmap);
        // new antialised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // text color - #3D3D3D
        paint.setColor(Color.rgb(61, 61, 61));
        // text size in pixels
        paint.setTextSize((int) (80 * scale));
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(gText, 0, gText.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width())/2;
        int y = (bitmap.getHeight() + bounds.height())/2;

        canvas.drawText(gText, x, y, paint);

        return bitmap;
    }

    public static ConsumerView getConsumer(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences("Consumer", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = myPrefs.getString("Consumer", "");
        XLog.d(AppUtils.class,"==============>getConsumerJson = "+json);
        if(json.length()==0) return null;
        ConsumerView consumer = gson.fromJson(json, ConsumerView.class);
        XLog.d(AppUtils.class,"==============>getConsumerJson = "+json);
        return consumer;
    }

    public static void setConsumer(Context context,String consumerJson) {
        SharedPreferences myPrefs = context.getSharedPreferences("Consumer", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString("Consumer", consumerJson);
        prefsEditor.commit();

    }
}
