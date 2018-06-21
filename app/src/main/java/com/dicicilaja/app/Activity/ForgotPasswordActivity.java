package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextView titleSection, bodySection, detailSection, judulButuhBantuan, butuhBantuan;
    EditText inputEmail;
    Button btnVerifikasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        btnVerifikasi = (Button) findViewById(R.id.btnVerifikasi);
        titleSection = (TextView) findViewById(R.id.titleSection);
        bodySection = (TextView) findViewById(R.id.bodySection);
        judulButuhBantuan = (TextView) findViewById(R.id.judulButuhBantuan);
        butuhBantuan = (TextView) findViewById(R.id.butuhBantuan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        if (android.os.Build.VERSION.SDK_INT >= 21) {
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorBlack));
//        }


        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        btnVerifikasi.setTypeface(opensans_bold);
        inputEmail.setTypeface(opensans_semibold);
        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        judulButuhBantuan.setTypeface(opensans_reguler);
        butuhBantuan.setTypeface(opensans_semibold);

        butuhBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
