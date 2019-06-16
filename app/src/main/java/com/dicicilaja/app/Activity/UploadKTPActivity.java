package com.dicicilaja.app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.R;

import butterknife.ButterKnife;

public class UploadKTPActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_ktp);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upload KTP & NPWP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
