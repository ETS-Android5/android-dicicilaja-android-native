package com.dicicilaja.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceMerchant;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPartner.Datum;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPartner.Partner;
import com.dicicilaja.app.Adapter.ListMerchantAdapter;
import com.dicicilaja.app.R;
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
