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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiClient;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiService;
import com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.Activity.BusinessReward.ui.KtpNpwp.activity.UploadKTPActivity;
import com.dicicilaja.app.Activity.BusinessReward.ui.Transaction.activity.TransactionActivity;
import com.dicicilaja.app.Activity.SearchResultActivity;
import com.dicicilaja.app.Component.SwipeRefreshLayoutWithEmpty;
import com.dicicilaja.app.R;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

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

    int currentPage = 1;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayoutWithEmpty swipeToRefresh;

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

        swipeToRefresh.setColorSchemeResources(R.color.colorAccent);

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

        doLoadData();

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doLoadData();
                swipeToRefresh.setRefreshing(false);
            }
        });
    }

    private void doLoadData(){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<KategoriProduk> call = apiService.getKategori();
        call.enqueue(new Callback<KategoriProduk>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<KategoriProduk> call, Response<KategoriProduk> response) {
                final List<Datum> dataItems = response.body().getData();
                final List<Included> dataItems2 = response.body().getIncluded();
                Log.d("Cek1", "" + response.body().getData());
                Log.d("Cek2", "" + response.body().getIncluded());

                recyclerProduk.setAdapter(new ListProductCatalogAdapter(dataItems, dataItems2, getBaseContext()));
            }

            @Override
            public void onFailure(Call<KategoriProduk> call, Throwable t) {

            }
        });

    }

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
