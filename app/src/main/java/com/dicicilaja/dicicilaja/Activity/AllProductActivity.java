package com.dicicilaja.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAllProductPartner;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAllProductPartner.AllProductPartner;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemAllProductPartner.Datum;
import com.dicicilaja.dicicilaja.Adapter.ListProductPartnerAdapter;
import com.dicicilaja.dicicilaja.Adapter.ListPromoAdapter;
import com.dicicilaja.dicicilaja.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductActivity extends AppCompatActivity {

    RecyclerView recycler_all_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        recycler_all_product = findViewById(R.id.recycler_all_product);
        recycler_all_product.setHasFixedSize(true);
        recycler_all_product.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false));
        InterfaceAllProductPartner apiService =
                RetrofitClient.getClient().create(InterfaceAllProductPartner.class);

        Log.d("AllProductActivity","id : " + getIntent().getStringExtra("EXTRA_REQUEST_ID"));
        Call<AllProductPartner> call = apiService.getProduct(getIntent().getStringExtra("EXTRA_REQUEST_ID"));
        call.enqueue(new Callback<AllProductPartner>() {
            @Override
            public void onResponse(Call<AllProductPartner> call, Response<AllProductPartner> response) {
                final List<Datum> product_partner = response.body().getData();

                recycler_all_product.setAdapter(new ListProductPartnerAdapter(product_partner, getBaseContext()));
            }

            @Override
            public void onFailure(Call<AllProductPartner> call, Throwable t) {

            }
        });
    }
}
