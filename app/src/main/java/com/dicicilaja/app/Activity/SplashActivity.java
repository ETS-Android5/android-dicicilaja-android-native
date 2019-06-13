package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.dicicilaja.app.API.Model.CheckVersion;
import com.dicicilaja.app.Activity.Addon.UpgradeActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Remote.ApiUtils;
import com.dicicilaja.app.Remote.UserService;
import com.instabug.library.Instabug;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 2000;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorWhite));
        }

//        Instabug.setUserAttribute("USER_ID", null);
//        Instabug.setUserAttribute("LOGIN", "False");

        userService = ApiUtils.getUserService();

        Call<CheckVersion> call = userService.checkVersion();
        call.enqueue(new Callback<CheckVersion>() {
            @Override
            public void onResponse(Call<CheckVersion> call, Response<CheckVersion> response) {
                if(response.isSuccessful()) {
                    int verName = 0;
                    try {
                        verName = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                    if( verName != 0 && response.body().getData() > verName ) {
                        showUpgradeActivity();
                    } else {
                        showIntroActivity();
                    }
                } else {
                    showIntroActivity();
                }
            }

            @Override
            public void onFailure(Call<CheckVersion> call, Throwable t) {
                showIntroActivity();
            }
        });


    }

    private void showUpgradeActivity() {
        Intent i = new Intent( SplashActivity.this, UpgradeActivity.class);
        startActivity(i);
        finish();
    }

    private void showIntroActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
