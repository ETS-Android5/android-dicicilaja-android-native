package com.dicicilaja.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.Adapter.ListPromoAdapter;
import com.dicicilaja.dicicilaja.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    RecyclerView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        search = findViewById(R.id.recycler_search);
        search.setHasFixedSize(true);
        search.setLayoutManager(new GridLayoutManager(this, 2));
        com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo apiService =
                RetrofitClient.getClient().create(com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo.class);

        Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call = apiService.getPromo();
        call.enqueue(new Callback<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo>() {
            @Override
            public void onResponse(Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Response<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> response) {
                final List<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos = response.body().getData();

                search.setAdapter(new ListPromoAdapter(promos, getBaseContext()));
            }

            @Override
            public void onFailure(Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Throwable t) {

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
