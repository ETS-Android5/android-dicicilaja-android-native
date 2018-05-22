package com.dicicilaja.dicicilaja.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dicicilaja.dicicilaja.R;

public class RegisterMaxi2Activity extends AppCompatActivity {


    Button btnLanjut;
    EditText namaPerusahaan, alamatUsaha, kota, kelurahan, noNPWP, noNPWPPribadi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_maxi2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnLanjut = findViewById(R.id.btnLanjut);
        namaPerusahaan = (EditText) findViewById(R.id.namaPerusahaan);
        alamatUsaha = (EditText) findViewById(R.id.alamatUsaha);
        kota = (EditText) findViewById(R.id.kota);
        kelurahan = (EditText) findViewById(R.id.kelurahan);
        noNPWP = (EditText) findViewById(R.id.noNPWP);
        noNPWPPribadi = (EditText) findViewById(R.id.noNPWPPribadi);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterMaxi3Activity.class);
                startActivity(intent);
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
