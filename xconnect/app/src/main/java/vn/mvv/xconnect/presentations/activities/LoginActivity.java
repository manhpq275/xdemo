package vn.mvv.xconnect.presentations.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.XConnectApplication;
import vn.mvv.xconnect.interfaces.ILoginCallback;
import vn.mvv.xconnect.models.ConsumerView;
import vn.mvv.xconnect.models.ErrorView;
import vn.mvv.xconnect.models.enums.ClientType;
import vn.mvv.xconnect.services.ConsumerService;
import vn.mvv.xconnect.utils.AppUtils;
import vn.mvv.xconnect.utils.XLog;

/**
 * Created by admin on 6/16/16.
 */
public class LoginActivity extends AppCompatActivity {

    EditText consumerEmail,consumerPass;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        consumerEmail = (EditText) findViewById(R.id.eConsumerEmail);
        consumerPass = (EditText) findViewById(R.id.eConsumerPass);
        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsumerService.getInstance().login(consumerEmail.getText().toString(), consumerPass.getText().toString(), ClientType.AndroidApp, new ILoginCallback() {
                    @Override
                    public void onSuccess(ConsumerView item) {
                        AppUtils.setConsumer(getApplicationContext(),item.getJson());
                        onBackPressed();
                        XLog.d(EventDetail.class, "======>ConsumerView: " + XConnectApplication.getInstance().getGson().toJson(item));
                    }

                    @Override
                    public void onError(ArrayList<ErrorView> errors) {

                    }
                });
            }
        });
    }
}
