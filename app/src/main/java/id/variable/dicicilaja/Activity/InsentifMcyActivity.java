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

public class InsentifMcyActivity extends AppCompatActivity {

    TextView title_info, title_mentor, value_mentor, title_bulanan, value_bulanan, title_group, value_group, title_tahunan, value_tahunan, title_layout, value_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insentif_mcy);

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
        title_mentor = findViewById(R.id.title_mentor);
        value_mentor = findViewById(R.id.value_mentor);
        title_bulanan = findViewById(R.id.title_bulanan);
        value_bulanan = findViewById(R.id.value_bulanan);
        title_group = findViewById(R.id.title_group);
        value_group = findViewById(R.id.value_group);
        title_tahunan = findViewById(R.id.title_tahunan);
        value_tahunan = findViewById(R.id.value_tahunan);
        title_layout = findViewById(R.id.title_layout);
        value_layout = findViewById(R.id.value_layout);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_info.setTypeface(opensans_bold);
        title_mentor.setTypeface(opensans_bold);
        value_mentor.setTypeface(opensans_bold);
        title_bulanan.setTypeface(opensans_bold);
        value_bulanan.setTypeface(opensans_bold);
        title_group.setTypeface(opensans_bold);
        value_group.setTypeface(opensans_bold);
        title_tahunan.setTypeface(opensans_bold);
        value_tahunan.setTypeface(opensans_bold);
        title_layout.setTypeface(opensans_bold);
        value_layout.setTypeface(opensans_bold);
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
