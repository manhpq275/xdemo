package vn.mvv.xconnect.presentations.customize;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import vn.mvv.xconnect.R;

/**
 * Created by admin on 6/13/16.
 */
public class MapDialog extends Dialog implements OnMapReadyCallback {
    private Button btnClose;
    private TextView tvHeader;
    private FragmentActivity activity;

    private String txtHeader;
    private GoogleMap googleMap;
    private double latitude,longitude;
    private boolean isShow = false;

    public MapDialog(FragmentActivity a) {
        super(a, R.style.custom_dialog_theme);
        // TODO Auto-generated constructor stub
        activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide notification bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map_dialog);
        tvHeader = (TextView) findViewById(R.id.tvHeader);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // dismiss dialog box
                dismiss();
            }
        });

        SupportMapFragment mapFragment =
                (SupportMapFragment) activity.getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void setData() {
        tvHeader.setText(txtHeader);
    }

    public void setHeader(String txtHeader) {
        this.txtHeader = txtHeader;
    }

    public void setContent(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(!isShow) this.googleMap = googleMap;
        setData();
        LatLng sydney = new LatLng(this.latitude, this.longitude);
        googleMap.clear();
        googleMap.addMarker(new MarkerOptions().position(sydney).title(this.txtHeader));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,12));
        isShow = true;
    }

    @Override
    public  void show(){
        super.show();
        if(isShow) onMapReady(googleMap);
    }

}
