package vn.mvv.xconnect.presentations.customize;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.mvv.xconnect.R;
/**
 * Created by admin on 6/13/16.
 */
public class CustomDialog extends Dialog {
    private Button btnClose;
    private TextView tvHeader;
    private RelativeLayout rlContent;
    private Activity activity;

    private String txtHeader;
    private RelativeLayout Content;
    public CustomDialog(Activity a) {
        super(a, R.style.custom_dialog_theme);
        // TODO Auto-generated constructor stub
        activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide notification bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        tvHeader = (TextView) findViewById(R.id.tvHeader);
        btnClose = (Button) findViewById(R.id.btnClose);
        rlContent = (RelativeLayout)findViewById(R.id.rlContent);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // dismiss dialog box
                dismiss();
            }
        });
        setData();

    }
    private void setData(){
        tvHeader.setText(txtHeader);
        if(Content!=null)
            rlContent.addView(Content);
    }
    public void setHeader(String txtHeader){
        this.txtHeader = txtHeader;
    }
    public void setContent(RelativeLayout iContent){
        this.Content =iContent;
    }
}
