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
import id.variable.dicicilaja.Session.SessionManager;

public class InfoJaringanActivity extends AppCompatActivity {

    TextView title_info, title_rb, value_rb, title_jaringan, value_jaringan, title_daftar, value_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jaringan);

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
        title_rb = findViewById(R.id.title_rb);
        value_rb = findViewById(R.id.value_rb);
        title_jaringan = findViewById(R.id.title_jaringan);
        value_jaringan = findViewById(R.id.value_jaringan);
        title_daftar = findViewById(R.id.title_daftar);
        value_daftar = findViewById(R.id.value_daftar);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_info.setTypeface(opensans_bold);
        title_rb.setTypeface(opensans_bold);
        value_rb.setTypeface(opensans_bold);
        title_jaringan.setTypeface(opensans_bold);
        value_jaringan.setTypeface(opensans_bold);
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
