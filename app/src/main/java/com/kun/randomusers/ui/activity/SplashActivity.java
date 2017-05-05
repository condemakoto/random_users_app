package com.kun.randomusers.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.kun.randomusers.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class SplashActivity extends BaseActivity {

    private final Handler handler = new Handler();
    private final int TIME_TO_START = 3000;
    @Bind(R.id.versionTV)
    protected TextView versionTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        try {
            String version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            versionTV.setText(getString(R.string.txtVersion, version));
        } catch (Exception ex) {
            Log.i(SplashActivity.class.getSimpleName(), "Can't retrieve the info about the version");
        }


    }

    @Override
    public void onResume() {
        super.onResume();

        handler.postDelayed(startHomeActivityRunnable, TIME_TO_START);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(startHomeActivityRunnable);
    }

    private final Runnable startHomeActivityRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, UserActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
