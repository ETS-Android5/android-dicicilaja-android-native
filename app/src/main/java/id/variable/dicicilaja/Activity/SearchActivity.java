package id.variable.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfacePromo;
import id.variable.dicicilaja.API.Item.Promo.Datum;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.Adapter.ListPromoAdapter;
import id.variable.dicicilaja.R;
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
        id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo apiService =
                NewRetrofitClient.getClient().create(id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo.class);

        Call<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call = apiService.getPromo();
        call.enqueue(new Callback<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo>() {
            @Override
            public void onResponse(Call<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Response<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> response) {
                final List<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos = response.body().getData();

                search.setAdapter(new ListPromoAdapter(promos, getBaseContext()));
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
