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
import com.dicicilaja.dicicilaja.API.Interface.InterfaceMerchant;
import com.dicicilaja.dicicilaja.API.Item.Mechant.Merchant;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPartner.Datum;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemPartner.Partner;
import com.dicicilaja.dicicilaja.Adapter.ListMerchantAdapter;
import com.dicicilaja.dicicilaja.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPartnerActivity extends AppCompatActivity {

    RecyclerView partner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_partner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        partner = findViewById(R.id.recycler_partner);
        partner.setHasFixedSize(true);
        partner.setLayoutManager(new GridLayoutManager(this, 3));
        InterfaceMerchant apiService =
                RetrofitClient.getClient().create(InterfaceMerchant.class);

        Call<Partner> call = apiService.getPartner();
        call.enqueue(new Callback<Partner>() {
            @Override
            public void onResponse(Call<Partner> call, Response<Partner> response) {
                final List<Datum> merchants = response.body().getData();

                partner.setAdapter(new ListMerchantAdapter(merchants, getBaseContext()));
            }

            @Override
            public void onFailure(Call<Partner> call, Throwable t) {

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
