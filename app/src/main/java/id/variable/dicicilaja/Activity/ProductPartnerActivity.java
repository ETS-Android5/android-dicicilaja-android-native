package id.variable.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfacePromo;
import id.variable.dicicilaja.API.Item.Promo.Datum;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.Adapter.ListPromoAdapter;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
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

        id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo apiService =
                NewRetrofitClient.getClient().create(id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo.class);

        Call<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call = apiService.getPromo();
        call.enqueue(new Callback<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo>() {
            @Override
            public void onResponse(Call<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Response<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> response) {
                final List<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos = response.body().getData();

                recyclerPromo.setAdapter(new ListPromoAdapter(promos, getBaseContext()));
            }

            @Override
            public void onFailure(Call<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Throwable t) {

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
