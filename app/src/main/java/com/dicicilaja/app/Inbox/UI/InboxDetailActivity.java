package com.dicicilaja.app.Inbox.UI;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InboxDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.category)
    ImageView category;

    String title_text, date_text, message_text, category_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        try {
            title_text = getIntent().getStringExtra("title");
            date_text = getIntent().getStringExtra("date");
            message_text = getIntent().getStringExtra("message");
            category_text = getIntent().getStringExtra("category");
        } catch (Exception ex) {}


        title.setText(title_text);
        date.setText(date_text);
        message.setText(message_text);

        switch (category_text) {
            case "Message":
                Glide.with(this).load(R.drawable.notif_message).centerCrop().into(category);
                break;
            case "Registration":
                Glide.with(this).load(R.drawable.notif_registration).centerCrop().into(category);
                break;
            case "Point Rewards":
                Glide.with(this).load(R.drawable.notif_point_rewards).centerCrop().into(category);
                break;
            case "Point Trip":
                Glide.with(this).load(R.drawable.notif_point_trip).centerCrop().into(category);
                break;
            case "Info AXI":
                Glide.with(this).load(R.drawable.notif_info_axi).centerCrop().into(category);
                break;
            case "Gathering":
                Glide.with(this).load(R.drawable.notif_gathering).centerCrop().into(category);
                break;
            case "Anual Fee":
                Glide.with(this).load(R.drawable.notif_anual_fee).centerCrop().into(category);
                break;
        }
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
