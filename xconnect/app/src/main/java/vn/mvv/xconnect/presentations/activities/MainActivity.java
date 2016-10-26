package vn.mvv.xconnect.presentations.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.mvv.xconnect.R;
import vn.mvv.xconnect.utils.AppUtils;
import vn.mvv.xconnect.utils.XLog;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private String TAG  = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUtils.setImageSizeKey(getWidthScreen());
        new LongOperation().execute();
    }
    private class LongOperation extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... arg) {
            for (int i = 0; i <= 100; i++) {
                SystemClock.sleep(10);
                publishProgress(i);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
        }

        @Override
        protected void onPreExecute() {
        }
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            int value = values[0];
            progressBar.setProgress(value);
            XLog.d(TAG,"Loading "+String.valueOf(value)+"%");
            TextView txtmsg = (TextView) findViewById(R.id.tvPercxent);
            txtmsg.setText(value + "%");
            if (value == 100) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    public int getWidthScreen() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        return w;
    }
}
