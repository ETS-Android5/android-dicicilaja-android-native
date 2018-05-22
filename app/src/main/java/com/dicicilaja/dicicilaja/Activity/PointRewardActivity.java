package com.dicicilaja.dicicilaja.Activity;

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

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import com.dicicilaja.dicicilaja.R;

public class PointRewardActivity extends AppCompatActivity {

    TextView title_point, value_point, redeem_point, download_katalog;
    ImageView reward;
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
        redeem_point = findViewById(R.id.redeem_point);
        download_katalog = findViewById(R.id.download_katalog);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_point.setTypeface(opensans_bold);
        value_point.setTypeface(opensans_bold);

        download_katalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
                startActivity(browserIntent);
            }
        });

        redeem_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
                startActivity(browserIntent);
            }
        });

        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

        value_point.setText(formatter.format(Integer.parseInt(String.valueOf(getIntent().getStringExtra("POINT_REWARD")))).replace(",","."));
        ImageView reward = findViewById(R.id.reward);
        String imageUrl = "https://dicicilaja.com/uploads/reward/rewards.png";
        Picasso.with(getApplicationContext()).load(imageUrl).into(reward);
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
