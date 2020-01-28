package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKategori extends AppCompatActivity {
    private static final String TAG = ListKategori.class.getSimpleName();
    ApiService apiService;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    final HashMap<Integer, String> KATEGORI_ITEMS = new HashMap<Integer, String>();
    final HashMap<Integer, String> KATEGORI_DATA = new HashMap<Integer, String>();

    List<String> stringList;
    RadioButton rb;
    RadioGroup rg;
    String selected, size;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.pilih_kat)
    Button pilihKat;
    @BindView(R.id.pb_kategori)
    ProgressBar pbCategory;

    private String id = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kategori);
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

        id = getIntent().getStringExtra("ID");

        rg = (RadioGroup) findViewById(R.id.radio_group);

        rg.clearCheck();
        radioGroup.clearCheck();
        radioGroup.removeAllViews();
        KATEGORI_DATA.clear();
        KATEGORI_ITEMS.clear();

        stringList = new ArrayList<>();

        apiService = ApiClient.getClient().create(ApiService.class);

        pbCategory.setVisibility(View.VISIBLE);
        rg.setVisibility(View.GONE);

        Call<KategoriProduk> call = apiService.getKategori();
        call.enqueue(new Callback<KategoriProduk>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<KategoriProduk> call, Response<KategoriProduk> response) {
                if (response.isSuccessful()) {
                    final List<Datum> dataItems = response.body().getData();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        KATEGORI_DATA.put(i+1, String.valueOf(dataItems.get(i).getId()));
                        KATEGORI_ITEMS.put(i+1, String.valueOf(dataItems.get(i).getRelationships().getProductCatalogs().getData().size()));
                        stringList.add(response.body().getData().get(i).getAttributes().getNama());

                        rb = new RadioButton(ListKategori.this); // dynamically creating RadioButton and adding to RadioGroup.
                        rb.setId(i);
                        rb.setText(stringList.get(i));

                        rg.addView(rb);

                        if (Integer.valueOf(id) == (i + 1)) {
                            rb.setChecked(true);
                        }
                    }

                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {

                            for (int i = 0; i < rg.getChildCount(); i++) {
                                if (((RadioButton) rg.getChildAt(i)).getId() == checkedId) {
                                    ((RadioButton) rg.getChildAt(i)).setChecked(true);

                                    selected = String.valueOf(checkedId + 1);
                                } else {
                                    ((RadioButton) rg.getChildAt(i)).setChecked(false);
                                }
                            }
                        }
                    });

                    pilihKat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (selected == null) selected = id;
                            setResult(RESULT_OK, getIntent().putExtra("ID", selected));
                            finish();
                        }
                    });

                    pbCategory.setVisibility(View.GONE);
                    rg.setVisibility(View.VISIBLE);
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListKategori.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Gagal memuat data, silahkan coba beberapa saat lagi.");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(getIntent());
                        }
                    });
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<KategoriProduk> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListKategori.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Gagal memuat data, silahkan coba beberapa saat lagi.");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                alertDialog.show();
            }
        });
    }
}
