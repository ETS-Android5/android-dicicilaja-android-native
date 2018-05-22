package com.dicicilaja.dicicilaja.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Item.DetailProduct.Datum;
import com.dicicilaja.dicicilaja.Adapter.ListPromoAdapter;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    List<Datum> detailProducts;
    ImageView head_image;
    RelativeLayout rute, syarat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        final SessionManager session = new SessionManager(getBaseContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        head_image = findViewById(R.id.head_image);

        rute = findViewById(R.id.rute);
        syarat = findViewById(R.id.syarat);
        rute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RutePerjalananActivity.class);
                startActivity(intent);

            }
        });
        syarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SyaratKetentuanActivity.class);
                startActivity(intent);
            }
        });


//        InterfaceDetailProduct apiService = NewRetrofitClient.getClient().create(InterfaceDetailProduct.class);
//
//        Call<DetailProduct> call = apiService.getDetailProduct(Integer.parseInt(getIntent().getStringExtra("ID")));
//        call.enqueue(new Callback<DetailProduct>() {
//            @Override
//            public void onResponse(Call<DetailProduct> call, Response<DetailProduct> response) {
//                detailProducts = response.body().getData();
//                Picasso.with(getBaseContext()).load(detailProducts.get(0).getImage().toString()).into(head_image);
//            }
//
//            @Override
//            public void onFailure(Call<DetailProduct> call, Throwable t) {
//
//            }
//        });

        final RecyclerView recyclerPromo = (RecyclerView) findViewById(R.id.recycler_related);
        recyclerPromo.setHasFixedSize(true);
        recyclerPromo.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperPromo = new GravitySnapHelper(Gravity.START);
        snapHelperPromo.attachToRecyclerView(recyclerPromo);

        com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo apiService2 =
                NewRetrofitClient.getClient().create(com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo.class);

        Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call2 = apiService2.getPromo();
        call2.enqueue(new Callback<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo>() {
            @Override
            public void onResponse(Call<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Response<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> response) {
                final List<com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos = response.body().getData();

                recyclerPromo.setAdapter(new ListPromoAdapter(promos, getBaseContext()));
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
