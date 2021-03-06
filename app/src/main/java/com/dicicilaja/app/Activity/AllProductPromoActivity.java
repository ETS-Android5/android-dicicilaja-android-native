package com.dicicilaja.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Datum;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo;
import com.dicicilaja.app.Adapter.ListProductPromoAdapter;
import com.dicicilaja.app.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductPromoActivity extends AppCompatActivity {

    RecyclerView recycler_all_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product_promo);

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
        InterfacePromo apiService =
                RetrofitClient.getClient().create(InterfacePromo.class);

        Log.d("AllProductActivity","id : " + getIntent().getStringExtra("EXTRA_REQUEST_ID"));
        Call<Promo> call = apiService.getPromo();
        call.enqueue(new Callback<Promo>() {
            @Override
            public void onResponse(Call<Promo> call, Response<Promo> response) {
                final List<Datum> promo = response.body().getData();

                recycler_all_product.setAdapter(new ListProductPromoAdapter(promo, getBaseContext()));
            }

            @Override
            public void onFailure(Call<Promo> call, Throwable t) {

            }
        });
    }
}
