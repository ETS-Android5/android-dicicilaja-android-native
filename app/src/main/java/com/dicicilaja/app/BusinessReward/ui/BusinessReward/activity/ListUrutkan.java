package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListUrutkan extends AppCompatActivity {
    private static final String TAG = ListUrutkan.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.pilih_kat)
    Button pilihKat;

    RadioButton rb;
    int id;

    String idnya, size, order_by;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_urutkan);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        idnya = intent.getStringExtra("ID");
        size = intent.getStringExtra("SIZE");
        order_by = intent.getStringExtra("ORDERBY");

        // 1 = "asc"
        // 2 = "desc"

        rb = new RadioButton(ListUrutkan.this); // dynamically creating RadioButton and adding to RadioGroup.
        rb.setText("A to Z");
        radioGroup.addView(rb);
        rb = new RadioButton(ListUrutkan.this); // dynamically creating RadioButton and adding to RadioGroup.
        rb.setText("Z to A");
        radioGroup.addView(rb);

        if (order_by.equals("asc")) {
            ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
            ((RadioButton) radioGroup.getChildAt(1)).setChecked(false);
        } else {
            ((RadioButton) radioGroup.getChildAt(0)).setChecked(false);
            ((RadioButton) radioGroup.getChildAt(1)).setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id = radioGroup.getCheckedRadioButtonId();

                if(id % 2 == 0){
                    id = 2;
                }else{
                    id = id % 2;
                }

                if (id == 2) {
                    ((RadioButton) radioGroup.getChildAt(1)).setChecked(true);
                    ((RadioButton) radioGroup.getChildAt(0)).setChecked(false);
                } else {
                    ((RadioButton) radioGroup.getChildAt(1)).setChecked(false);
                    ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);

                }
            }
        });

        pilihKat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = null;
                if (id == 1) {
                    i = getIntent().putExtra("ORDERBY", "asc");
                } else {
                    i = getIntent().putExtra("ORDERBY", "desc");
                }
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
