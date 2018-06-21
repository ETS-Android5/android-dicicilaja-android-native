package com.dicicilaja.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import com.dicicilaja.app.API.Client.NewRetrofitClient;
import com.dicicilaja.app.Adapter.ListPromoAdapter;
import com.dicicilaja.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPartnerActivity extends AppCompatActivity {

    private SnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_partner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        snapHelper = new GravitySnapHelper(Gravity.START);

        final RecyclerView recyclerPromo = (RecyclerView) findViewById(R.id.recycler_promo);
        recyclerPromo.setHasFixedSize(true);

        recyclerPromo.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerPromo);

        com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo apiService =
                NewRetrofitClient.getClient().create(com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo.class);

        Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call = apiService.getPromo();
        call.enqueue(new Callback<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo>() {
            @Override
            public void onResponse(Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Response<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> response) {
                final List<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos = response.body().getData();

                recyclerPromo.setAdapter(new ListPromoAdapter(promos, getBaseContext()));
            }

            @Override
            public void onFailure(Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Throwable t) {

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
