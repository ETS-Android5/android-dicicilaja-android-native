package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.webviewsuite.WebViewSuite;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import com.dicicilaja.app.R;

public class PointRewardActivity extends AppCompatActivity {

    TextView title_point, value_point, redeem_point, download_katalog;
    ImageView reward1, reward2, reward3, reward4, reward5, reward6, reward7;
    WebViewSuite webviewPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_reward);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        title_point = findViewById(R.id.title_point);
        value_point = findViewById(R.id.value_point);
//        redeem_point = findViewById(R.id.redeem_point);
//        download_katalog = findViewById(R.id.download_katalog);
        webviewPoint = findViewById(R.id.webviewPoint);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_point.setTypeface(opensans_bold);
        value_point.setTypeface(opensans_bold);

//        download_katalog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropbox.com/s/7uiwbvyqk52ppq5/Katalog%20AXI%202018.pdf?dl=0"));
//                startActivity(browserIntent);
//            }
//        });
//
//        redeem_point.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/bQLGvEJ9weGkjdOf2"));
//                startActivity(browserIntent);
//            }
//        });

        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
//
        value_point.setText(formatter.format(Integer.parseInt(String.valueOf(getIntent().getStringExtra("POINT_REWARD")))).replace(",","."));
//        reward1 = findViewById(R.id.reward1);
//        reward2 = findViewById(R.id.reward2);
//        reward3 = findViewById(R.id.reward3);
//        reward4 = findViewById(R.id.reward4);
//        reward5 = findViewById(R.id.reward5);
//        reward6 = findViewById(R.id.reward6);
//        reward7 = findViewById(R.id.reward7);
//
//        String imageUrl1 = "https://dicicilaja.com/uploads/reward/reward1.jpg";
//        String imageUrl2 = "https://dicicilaja.com/uploads/reward/reward2.jpg";
//        String imageUrl3 = "https://dicicilaja.com/uploads/reward/reward3.jpg";
//        String imageUrl4 = "https://dicicilaja.com/uploads/reward/reward4.jpg";
//        String imageUrl5 = "https://dicicilaja.com/uploads/reward/reward5.jpg";
//        String imageUrl6 = "https://dicicilaja.com/uploads/reward/reward6.jpg";
//        String imageUrl7 = "https://dicicilaja.com/uploads/reward/reward7.jpg";
//
//        Picasso.with(getApplicationContext()).load(imageUrl1).into(reward1);
//        Picasso.with(getApplicationContext()).load(imageUrl2).into(reward2);
//        Picasso.with(getApplicationContext()).load(imageUrl3).into(reward3);
//        Picasso.with(getApplicationContext()).load(imageUrl4).into(reward4);
//        Picasso.with(getApplicationContext()).load(imageUrl5).into(reward5);
//        Picasso.with(getApplicationContext()).load(imageUrl6).into(reward6);
//        Picasso.with(getApplicationContext()).load(imageUrl7).into(reward7);

        String pointForm = "http://bit.ly/dicicilajareward";
        webviewPoint.startLoading(pointForm);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
