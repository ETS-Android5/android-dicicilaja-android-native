package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Data;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.DetailKategori;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListAllProductAdapter;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

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

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

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
                getSupportActionBar().setTitle(dataItems.getAttributes().getNama());

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
