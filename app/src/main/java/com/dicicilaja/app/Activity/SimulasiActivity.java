package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.DecimalFormat;

import com.dicicilaja.app.R;

public class SimulasiActivity extends AppCompatActivity {

    TextView harga_simulasi, hasil,jaminan, tenor, arearequest;
    Button btn_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulasi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        harga_simulasi = findViewById(R.id.harga_simulasi);
        jaminan = findViewById(R.id.jaminan);
        tenor = findViewById(R.id.tenor);
        arearequest = findViewById(R.id.arearequest);
        hasil = findViewById(R.id.hasil);
        btn_order = findViewById(R.id.btn_order);

        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
        harga_simulasi.setText("Rp " +  formatter.format(Integer.parseInt(String.valueOf(getIntent().getStringExtra("HARGA_SIMULASI")))).replace(",","."));
        hasil.setText("Rp " +  formatter.format(Integer.parseInt(String.valueOf(getIntent().getStringExtra("HASIL")))).replace(",","."));
        jaminan.setText(getIntent().getStringExtra("JAMINAN"));
        tenor.setText(getIntent().getStringExtra("TENOR"));
        arearequest.setText(getIntent().getStringExtra("AREAREQUEST"));

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),AjukanPengajuanAxiActivity.class);
                intent.putExtra("text_harga", getIntent().getStringExtra("text_harga"));
                intent.putExtra("spinner_jaminan",getIntent().getStringExtra("spinner_jaminan"));
                intent.putExtra("spinner_tenor",getIntent().getStringExtra("spinner_tenor"));
                intent.putExtra("spinner_area",getIntent().getStringExtra("spinner_area"));
//                Toast.makeText(getBaseContext()," kode_area : " + getIntent().getStringExtra("spinner_area"),Toast.LENGTH_SHORT).show();
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
