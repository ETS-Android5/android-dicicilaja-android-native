package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKategori extends AppCompatActivity {
    ApiService apiService;

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

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiService = ApiClient.getClient().create(ApiService.class);

//        Call<KategoriProduk> call = apiService.getKategori();
//        call.enqueue(new Callback<KategoriProduk>() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onResponse(Call<KategoriProduk> call, Response<KategoriProduk> response) {
//                final List<Datum> dataItems = response.body().getData();
//                final List<Included> dataItems2 = response.body().getIncluded();
//                recyclerKategori.setAdapter(new ListProductCatalogAdapter(dataItems, dataItems2, getBaseContext()));
//            }
//
//            @Override
//            public void onFailure(Call<KategoriProduk> call, Throwable t) {
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListKategori.this);
//                alertDialog.setTitle("Perhatian");
//                alertDialog.setMessage("Gagal memuat data, silahkan coba beberapa saat lagi.");
//
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                        startActivity(getIntent());
//                    }
//                });
//                alertDialog.show();
//            }
//        });
    }
}
