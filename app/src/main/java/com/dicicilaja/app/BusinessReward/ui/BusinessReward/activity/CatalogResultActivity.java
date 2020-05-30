package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Data;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.DetailKategori;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Produk;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListAllProductAdapter;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity.PilihCabangVendorActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogResultActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //    private List<Produk> productCatalogList;
    ListProductCatalogAdapter adapter;
    List<Datum> requests;

    List<String> stringList;

    @BindView(R.id.recycler_catalog)
    RecyclerView recyclerCatalog;

    String id, size, order_by;
    int status_order = 0;

    ApiService apiService;
    RadioButton rb;
    RadioGroup rg;
    Button btnOk;
    SessionManager session;
    String apiKey;

    final HashMap<Integer, String> KATEGORI_ITEMS = new HashMap<Integer, String>();
    final HashMap<Integer, String> KATEGORI_DATA = new HashMap<Integer, String>();
    @BindView(R.id.kat_icon)
    ImageView katIcon;
    @BindView(R.id.pilih_kategori)
    RelativeLayout pilihKategori;
    @BindView(R.id.sort_icon)
    ImageView sortIcon;
    @BindView(R.id.pilih_urutkan)
    RelativeLayout pilihUrutkan;

    Call<DetailKategori> call;
    Call<Produk> call2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_result);
        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiService = ApiClient.getClient().create(ApiService.class);


        stringList = new ArrayList<>();

        stringList.clear();
        KATEGORI_DATA.clear();
        KATEGORI_ITEMS.clear();

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        size = intent.getStringExtra("SIZE");
        order_by = intent.getStringExtra("ORDERBY");

        if(order_by == null){
            status_order = 0;
        }else{
            status_order = 1;
        }

//        Log.d("IDNYAINITEH", id);
//        Log.d("IDNYAINITEHSIZE", size);

        recyclerCatalog.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        recyclerCatalog.setHasFixedSize(true);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        if(status_order == 0){
//            call = apiService.getDetailKategori(Integer.parseInt(id));
//
//            call.enqueue(new Callback<DetailKategori>() {
//                @SuppressLint("WrongConstant")
//                @Override
//                public void onResponse(Call<DetailKategori> call, Response<DetailKategori> response) {
//                    final Data dataItems = response.body().getData();
//                    final List<Included> dataItems2 = response.body().getIncluded();
//                    getSupportActionBar().setTitle(dataItems.getAttributes().getNama());
//
//                    Log.d("Cek1", "" + response.code());
//
//                    recyclerCatalog.setAdapter(new ListAllProductAdapter(dataItems, dataItems2, getBaseContext(), id, size));
//                }
//
//                @Override
//                public void onFailure(Call<DetailKategori> call, Throwable t) {
//                    Log.d("dadada", t.getMessage());
//                }
//            });

            call2 = apiService.getProdukAll(apiKey, id);

            call2.enqueue(new Callback<Produk>() {
                @SuppressLint("WrongConstant")
                @Override
                public void onResponse(Call<Produk> call, Response<Produk> response) {
                    if (response.code() == 401) {
                        session.logoutUser();
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        final List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> dataItems = response.body().getData();
//                    getSupportActionBar().setTitle(dataItems.getAttributes().getNama());

                    Log.d("Cek2", "" + response.code());

                    recyclerCatalog.setAdapter(new ListAllProductAdapter(dataItems, getBaseContext(), id, size));

                    }
                }

                @Override
                public void onFailure(Call<Produk> call, Throwable t) {
                    Log.d("dadada", t.getMessage());
                }
            });
        }
        else{
            call2 = apiService.getProdukSort(apiKey, id, "nama", order_by);

            call2.enqueue(new Callback<Produk>() {
                @SuppressLint("WrongConstant")
                @Override
                public void onResponse(Call<Produk> call, Response<Produk> response) {
                    if (response.code() == 401) {
                        session.logoutUser();
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        final List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> dataItems = response.body().getData();
//                    getSupportActionBar().setTitle(dataItems.getAttributes().getNama());

                    Log.d("Cek2", "" + response.code());

                    recyclerCatalog.setAdapter(new ListAllProductAdapter(dataItems, getBaseContext(), id, size));
                    }
                }

                @Override
                public void onFailure(Call<Produk> call, Throwable t) {
                    Log.d("dadada", t.getMessage());
                }
            });
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.filter_menu, menu);
//        return true;
//    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.filter) {
//            Intent intent = new Intent(getBaseContext(), FIlterActivity.class);
//            startActivity(intent);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                order_by = data.getStringExtra("ORDERBY");
                status_order = 1;
                Log.d("ordernyaaeuy", order_by);
            }
        }
    }

    @OnClick({R.id.pilih_kategori, R.id.pilih_urutkan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pilih_kategori:
                status_order = 0;
                Intent intent = new Intent(getBaseContext(), ListKategori.class);
                startActivity(intent);
                finish();
                break;
            case R.id.pilih_urutkan:
                Intent intent2 = new Intent(getBaseContext(), ListUrutkan.class);
                intent2.putExtra("ID", id);
                intent2.putExtra("SIZE", size);
                startActivity(intent2);
                break;
        }
    }
}
