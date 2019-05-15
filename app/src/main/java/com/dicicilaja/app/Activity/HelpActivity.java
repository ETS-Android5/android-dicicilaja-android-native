package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dicicilaja.app.R;
import com.dicicilaja.app.WebView.ChatActivity;

public class HelpActivity extends AppCompatActivity {

    Button hotline, chat, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        if (android.os.Build.VERSION.SDK_INT >= 21) {
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorBlack));
//        }
        TextView titleSection = findViewById(R.id.titleSection);
        TextView bodySection = findViewById(R.id.bodySection);
        TextView detailSection = findViewById(R.id.detailSection);
        hotline = findViewById(R.id.hotline);
        chat    = findViewById(R.id.chat);
        email   = findViewById(R.id.email);

        hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "+628111465005";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ChatActivity.class);
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: dicicilaja@gmail.com"));
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));
            }
        });
        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
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
