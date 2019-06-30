package com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.Activity.BusinessReward.dataAPI.getDetailKategori.Data;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.getDetailKategori.DetailKategori;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.getDetailKategori.Included;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.produk.Datum;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.produk.Produk;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiClient;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiService;
import com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.adapter.ListAllProductAdapter;
import com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogResultActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    private List<Produk> productCatalogList;
    ListProductCatalogAdapter adapter;
    List<Datum> requests;

    @BindView(R.id.recycler_catalog)
    RecyclerView recyclerCatalog;

    String id, size;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_result);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        size = intent.getStringExtra("SIZE");

        Log.d("IDNYAINITEH", id);
        Log.d("IDNYAINITEH", size);

        recyclerCatalog.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        recyclerCatalog.setHasFixedSize(true);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<DetailKategori> call = apiService.getDetailKategori(Integer.parseInt(id));
        call.enqueue(new Callback<DetailKategori>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<DetailKategori> call, Response<DetailKategori> response) {
                final Data dataItems = response.body().getData();
                final List<Included> dataItems2 = response.body().getIncluded();

                Log.d("Cek1", "" + response.code());

                recyclerCatalog.setAdapter(new ListAllProductAdapter(dataItems, dataItems2, getBaseContext(), id, size));
            }

            @Override
            public void onFailure(Call<DetailKategori> call, Throwable t) {
                Log.d("dadada", t.getMessage());
            }
        });
    }
}
