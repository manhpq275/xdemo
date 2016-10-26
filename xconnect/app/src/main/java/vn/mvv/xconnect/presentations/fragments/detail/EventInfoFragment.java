package vn.mvv.xconnect.presentations.fragments.detail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.glxn.qrgen.android.QRCode;

import java.util.ArrayList;
import java.util.Locale;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.interfaces.IGetPromotionCodeCallback;
import vn.mvv.xconnect.models.ConsumerView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.EventView;
import vn.mvv.xconnect.models.PromotionView;
import vn.mvv.xconnect.presentations.activities.LoginActivity;
import vn.mvv.xconnect.presentations.customize.CustomDialog;
import vn.mvv.xconnect.presentations.fragments.BaseFragment;
import vn.mvv.xconnect.services.PromotionService;
import vn.mvv.xconnect.utils.AppUtils;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by admin on 6/10/16.
 */
public class EventInfoFragment extends BaseFragment implements  View.OnClickListener{
    private EventView event;
    private String txtDescription;
    private TextView tvDescription,tvVendorName;
    private RelativeLayout rlGroupVendorInfo,rlGroupGuide,rlGetCoupon,rlView;
    private ImageView imgLogoVendor;
    public EventInfoFragment(EventView event) {
        this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.detail_event_info_fragment, container, false);
        initView(mView);
        XLog.d(EventInfoFragment.class,"Locale.getDefault().getDisplayLanguage() = "+ Locale.getDefault().getDisplayLanguage());
        return mView;
    }

    private void initView(View mView) {
        imgLogoVendor = (ImageView)mView.findViewById(R.id.imLogoVendor);
        tvDescription = (TextView)mView.findViewById(R.id.tvDescription);
        tvVendorName = (TextView)mView.findViewById(R.id.tvVendorName);
        rlGroupVendorInfo = (RelativeLayout)mView.findViewById(R.id.rlGroupVendorInfo);
        rlGroupVendorInfo.setOnClickListener(this);
        rlGroupGuide = (RelativeLayout)mView.findViewById(R.id.rlGroupGuide);
        rlGroupGuide.setOnClickListener(this);
        rlGetCoupon = (RelativeLayout)mView.findViewById(R.id.rlGroupButtonGetCoupon);
        rlGetCoupon.setOnClickListener(this);
        loadData();
    }

    @Override
    public void loadData() {
        if(Locale.getDefault().getDisplayLanguage().contains("English"))
        {
            tvDescription.setText(event.getDescription().get(2));
        }else
        {
            tvDescription.setText(event.getDescription().get(1));
        }
        tvVendorName.setText(event.getVendor().getName());
        AppUtils.displayImage(event.getVendor().getLogoUrl(),imgLogoVendor);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.rlGroupVendorInfo:
                ClickVendorInfo();
                break;
            case R.id.rlGroupGuide:
                ClickGuide();
                break;
            case R.id.rlGroupButtonGetCoupon:
                ClickGetCoupon();
                break;
        }

    }

    private void ClickVendorInfo() {
        XLog.d(EventInfoFragment.class,"ClickVendorInfo");
    }
    private void ClickGuide() {
        XLog.d(EventInfoFragment.class,"ClickGuide");
        RelativeLayout content = new RelativeLayout(getActivity());
        TextView txtContent = new TextView(getContext());
        if(Locale.getDefault().getDisplayLanguage().contains("English"))
        {
            txtContent.setText(event.getGuideline().get(2));
        }else
        {
            txtContent.setText(event.getGuideline().get(1));
        }

//        Bitmap myBitmap = BitmapFactory.decodeFile(QRCode.from("www.example.orgwww.example.orgwww.example.orgwww.example.org").withSize(1000,1000).file().getAbsolutePath());

//        ImageView myImage = new ImageView(getContext());
//        myImage.setImageBitmap(myBitmap);
//        content.setGravity(Gravity.CENTER);
//        ViewGroup.LayoutParams layoutParams= new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        content.setLayoutParams(layoutParams);



        content.addView(txtContent);
        CustomDialog dialog = new CustomDialog(getActivity());
        dialog.setHeader(getString(R.string.txt_guide));
        dialog.setContent(content);

        dialog.show();
    }
    private void ClickGetCoupon() {
        ConsumerView consumerView = AppUtils.getConsumer(getContext());
        if(consumerView==null)
        {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        }else
        {
            PromotionService.getInstance().getPromotionCode(consumerView.getAccess_token(),event.getId(), new IGetPromotionCodeCallback() {
                @Override
                public void onSuccess(PromotionView item) {
                    XLog.d(EventInfoFragment.class, "======>PromotionView: " + XConnectApplication.getInstance().getGson().toJson(item));
                }

                @Override
                public void onError(ArrayList<ErrorView> errors) {

                }
            });

        }


        XLog.d(EventInfoFragment.class,"ClickGetCoupon");
    }
}
