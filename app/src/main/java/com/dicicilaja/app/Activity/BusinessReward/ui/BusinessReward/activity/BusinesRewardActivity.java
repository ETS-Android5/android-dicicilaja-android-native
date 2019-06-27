package com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiClient;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiService;
import com.dicicilaja.app.Activity.SearchResultActivity;
import com.dicicilaja.app.Activity.TransactionActivity;
import com.dicicilaja.app.Activity.UploadKTPActivity;
import com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.R;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinesRewardActivity extends AppCompatActivity {

    @BindView(R.id.profile_picture_page)
    CircleImageView profilePicturePage;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_point)
    TextView profilePoint;
    @BindView(R.id.coin)
    ImageView coin;
    @BindView(R.id.info)
    ImageView info;
    @BindView(R.id.profile_note)
    TextView profileNote;
    @BindView(R.id.recycler_produk)
    RecyclerView recyclerProduk;
    @BindView(R.id.upload)
    Button upload;

//    private ArrayList<Produk> productCatalogList;
//    private ArrayList<Produk> productCatalogList2;
//    private ArrayList<Produk> productCatalogList3;
//
//    private ArrayList<Produk> produks;

    ListProductCatalogAdapter adapter;
    ListProductCatalogAdapter adapter2;
    ListProductCatalogAdapter adapter3;

    ListProductCatalogAdapter adapterProduk;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_reward);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

//        productCatalogList = new ArrayList<>();
//        productCatalogList2 = new ArrayList<>();
//        productCatalogList3 = new ArrayList<>();
//
//        produks = new ArrayList<>();

//        adapter = new ListProductCatalogAdapter(productCatalogList, this);
//        adapter2 = new ListProductCatalogAdapter(productCatalogList2, this);
//        adapter3 = new ListProductCatalogAdapter(productCatalogList3, this);
//
//        adapterProduk = new ListProductCatalogAdapter(produks, this);

        //motor
        recyclerProduk.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerProduk.setHasFixedSize(true);
        recyclerProduk.setAdapter(adapter);

        SnapHelper snapHelperMotor = new GravitySnapHelper(Gravity.START);
        snapHelperMotor.attachToRecyclerView(recyclerProduk);

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<KategoriProduk> call = apiService.getKategori();
        call.enqueue(new Callback<KategoriProduk>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<KategoriProduk> call, Response<KategoriProduk> response) {
                final List<Datum> dataItems = response.body().getData();
                final List<Included> dataItems2 = response.body().getIncluded();
                Log.d("Cek1", ""+response.body().getData());
                Log.d("Cek2", ""+response.body().getIncluded());

                recyclerProduk.setAdapter(new ListProductCatalogAdapter(dataItems, dataItems2, getBaseContext()));
            }

            @Override
            public void onFailure(Call<KategoriProduk> call, Throwable t) {

            }
        });

//        createDummyData();
    }

//    private void createDummyData() {
//
//        int[] motor = new int[]{
//                R.drawable.motor1,
//                R.drawable.motor2,
//                R.drawable.motor3};
//
//        //motor
//        productCatalogList.add(new ProductCatalog("Yamaha New Fino 125", 1, motor[0], 2000));
//        productCatalogList.add(new ProductCatalog("Yamaha Mio M3", 2, motor[1], 1900));
//        productCatalogList.add(new ProductCatalog("Honda Vario 150", 3, motor[2], 2100));
//        productCatalogList.add(new ProductCatalog("Yamaha New Fino 125", 4, motor[0], 2000));
//        adapter.notifyDataSetChanged();
//
//        //hp
//        productCatalogList2.add(new ProductCatalog("Xiaomi A2 Lite", 1, motor[0], 450));
//        productCatalogList2.add(new ProductCatalog("Huawei Y6", 2, motor[1], 250));
//        productCatalogList2.add(new ProductCatalog("Samsung Galaxy A2", 3, motor[2], 350));
//        productCatalogList2.add(new ProductCatalog("Xiaomi A2 Lite", 4, motor[0], 410));
//        adapter2.notifyDataSetChanged();
//
//        //elektronik
//        productCatalogList3.add(new ProductCatalog("Speaker JBL", 1, motor[0], 250));
//        productCatalogList3.add(new ProductCatalog("JBL Flip 4", 2, motor[1], 200));
//        productCatalogList3.add(new ProductCatalog("Sony Extra Bass", 3, motor[2], 150));
//        productCatalogList3.add(new ProductCatalog("Speaker JBL", 4, motor[0], 125));
//        adapter3.notifyDataSetChanged();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.business_reward_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notif) {
            Intent intent = new Intent(getBaseContext(), TransactionActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.search) {
            Intent intent = new Intent(getBaseContext(), SearchResultActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.upload)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), UploadKTPActivity.class);
        startActivity(intent);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                super.finish();
//        }
//        return true;
//    }
}
