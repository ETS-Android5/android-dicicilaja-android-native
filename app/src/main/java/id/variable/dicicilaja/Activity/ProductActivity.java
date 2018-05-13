package id.variable.dicicilaja.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceDetailProduct;
import id.variable.dicicilaja.API.Interface.InterfaceDetailRequest;
import id.variable.dicicilaja.API.Interface.InterfacePromo;
import id.variable.dicicilaja.API.Item.DetailProduct.Datum;
import id.variable.dicicilaja.API.Item.DetailProduct.DetailProduct;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.Adapter.ListPromoAdapter;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
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

        id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo apiService2 =
                NewRetrofitClient.getClient().create(id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo.class);

        Call<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call2 = apiService2.getPromo();
        call2.enqueue(new Callback<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemPromo.Promo>() {
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
