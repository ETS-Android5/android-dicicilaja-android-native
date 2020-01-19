package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import com.dicicilaja.app.API.Client.ApiClient;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAllProduk;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllProduct.AllProduk;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAllProduct.Datum;
import com.dicicilaja.app.Adapter.ListProdukAdapter;
import com.dicicilaja.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RecyclerView search;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progress = new ProgressDialog(SearchActivity.this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        search = findViewById(R.id.recycler_search);
        search.setHasFixedSize(true);
        search.setLayoutManager(new GridLayoutManager(this, 2));
        InterfaceAllProduk apiService =
                ApiClient.getClient().create(InterfaceAllProduk.class);

        Call<AllProduk> call = apiService.getProduct();
        call.enqueue(new Callback<AllProduk>() {
            @Override
            public void onResponse(Call<AllProduk> call, Response<AllProduk> response) {
                final List<Datum> produk = response.body().getData();
                progress.dismiss();
                search.setAdapter(new ListProdukAdapter(produk, getBaseContext()));
            }

            @Override
            public void onFailure(Call<AllProduk> call, Throwable t) {

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
