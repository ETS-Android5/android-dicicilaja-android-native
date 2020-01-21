package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKategori extends AppCompatActivity {
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

        rg = (RadioGroup) findViewById(R.id.radio_group);

        rg.clearCheck();
        radioGroup.clearCheck();
        radioGroup.removeAllViews();
        KATEGORI_DATA.clear();
        KATEGORI_ITEMS.clear();
//        rg.removeAllViews();

        stringList = new ArrayList<>();

        apiService = ApiClient.getClient().create(ApiService.class);

        Call<KategoriProduk> call = apiService.getKategori();
        call.enqueue(new Callback<KategoriProduk>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<KategoriProduk> call, Response<KategoriProduk> response) {
                final List<Datum> dataItems = response.body().getData();

                for (int i = 0; i < response.body().getData().size(); i++) {
                    KATEGORI_DATA.put(i+1, String.valueOf(dataItems.get(i).getId()));
                    KATEGORI_ITEMS.put(i+1, String.valueOf(dataItems.get(i).getRelationships().getProductCatalogs().getData().size()));
//                    KATEGORI_ITEMS.put(response.body().getData().size(), String.valueOf(response.body().getData().get(i).getId()));
                    stringList.add(response.body().getData().get(i).getAttributes().getNama());

                    rb = new RadioButton(ListKategori.this); // dynamically creating RadioButton and adding to RadioGroup.
                    rb.setText(stringList.get(i));
//                    radioGroup.setId(0);
                    rg.addView(rb);
                }

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int id = rg.getCheckedRadioButtonId();

                        if(id % response.body().getData().size() == 0){
                            id = response.body().getData().size();
                        }else{
                            id = id % response.body().getData().size();
                        }

                        selected = KATEGORI_DATA.get(id);
                        size = KATEGORI_ITEMS.get(id);
                    }
                });

                pilihKat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), CatalogResultActivity.class);

                        intent.putExtra("ID", selected);
                        intent.putExtra("SIZE", size);
//                        intent.putExtra("ID", String.valueOf(3));
//                        intent.putExtra("SIZE", String.valueOf(5));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
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
