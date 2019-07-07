package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.pilih_kat)
    Button pilihKat;

    RadioButton rb;
    int id;

    String idnya, size;

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

        Intent intent = getIntent();
        idnya = intent.getStringExtra("ID");
        size = intent.getStringExtra("SIZE");

        rb = new RadioButton(ListUrutkan.this); // dynamically creating RadioButton and adding to RadioGroup.
        rb.setText("Ascending");
        radioGroup.addView(rb);
        rb = new RadioButton(ListUrutkan.this); // dynamically creating RadioButton and adding to RadioGroup.
        rb.setText("Descending");
        radioGroup.addView(rb);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id = radioGroup.getCheckedRadioButtonId();

                if(id % 2 == 0){
                    id = 2;
                }else{
                    id = id % 2;
                }
            }
        });

        pilihKat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                                Log.d("selectednya", selected);
                String orderby;
                Intent intent = new Intent(getBaseContext(), CatalogResultActivity.class);
                if(id == 1){
                    orderby = "asc";
                }else{
                    orderby = "desc";
                }
                intent.putExtra("ORDERBY", orderby);
                intent.putExtra("ID", idnya);
                intent.putExtra("SIZE", size);
                Log.d("ordernya", orderby);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
