package id.variable.dicicilaja.Activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import id.variable.dicicilaja.R;

public class RekanBisnisActivity extends AppCompatActivity {

    TextView title_info, title_nama, value_nama, title_axi, value_axi, title_reward, value_reward, title_trip, value_trip, title_hp, value_hp, title_daftar, value_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekan_bisnis);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        title_info = findViewById(R.id.title_info);
        title_nama = findViewById(R.id.title_nama);
        value_nama = findViewById(R.id.value_nama);
        title_axi = findViewById(R.id.title_axi);
        value_axi = findViewById(R.id.value_axi);
        title_reward = findViewById(R.id.title_reward);
        value_reward = findViewById(R.id.value_reward);
        title_trip = findViewById(R.id.title_trip);
        value_trip = findViewById(R.id.value_trip);
        title_hp = findViewById(R.id.title_hp);
        value_hp = findViewById(R.id.value_hp);
        title_daftar = findViewById(R.id.title_daftar);
        value_daftar = findViewById(R.id.value_daftar);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_info.setTypeface(opensans_bold);
        title_nama.setTypeface(opensans_bold);
        value_nama.setTypeface(opensans_bold);
        title_axi.setTypeface(opensans_bold);
        value_axi.setTypeface(opensans_bold);
        title_reward.setTypeface(opensans_bold);
        value_reward.setTypeface(opensans_bold);
        title_trip.setTypeface(opensans_bold);
        value_trip.setTypeface(opensans_bold);
        title_hp.setTypeface(opensans_bold);
        value_hp.setTypeface(opensans_bold);
        title_daftar.setTypeface(opensans_bold);
        value_daftar.setTypeface(opensans_bold);
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
