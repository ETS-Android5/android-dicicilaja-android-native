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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Data;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.DetailKategori;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Produk;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListAllProductAdapter;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity.DetailProductActivity;
import com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity.PilihCabangVendorActivity;
import com.dicicilaja.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogResultActivity extends AppCompatActivity implements ListAllProductAdapter.AllProductCallback {

    private static final String TAG = CatalogResultActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
    @BindView(R.id.pb_catalog)
    ProgressBar pbCatalog;

    Call<DetailKategori> call;
    Call<Produk> call2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_result);
        ButterKnife.bind(this);

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

        //checkStatusOrder();

        recyclerCatalog.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        recyclerCatalog.setHasFixedSize(true);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        loadData();
    }

    private void checkStatusOrder() {
        if (order_by == null) {
            status_order = 0;
        } else {
            status_order = 1;
        }
    }

    private void showProgress(boolean isShow) {
        if (isShow) {
            pbCatalog.setVisibility(View.VISIBLE);
            recyclerCatalog.setVisibility(View.GONE);
        } else {
            pbCatalog.setVisibility(View.GONE);
            recyclerCatalog.setVisibility(View.VISIBLE);
        }
    }

    private void loadData() {
        showProgress(true);
        checkStatusOrder();
        if (status_order == 0) {

            call2 = apiService.getProdukAll(id);

            call2.enqueue(new Callback<Produk>() {
                @SuppressLint("WrongConstant")
                @Override
                public void onResponse(Call<Produk> call, Response<Produk> response) {
                    if (response.isSuccessful()) {
                        final List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> dataItems = response.body().getData();

                        recyclerCatalog.setAdapter(new ListAllProductAdapter(dataItems, getBaseContext(), id, size, CatalogResultActivity.this));
                    } else {
                        Toast.makeText(CatalogResultActivity.this, "Terjadi kesalahan server, mohon coba lagi beberapa saat!", Toast.LENGTH_SHORT).show();
                    }
                    showProgress(false);
                }

                @Override
                public void onFailure(Call<Produk> call, Throwable t) {
                    Toast.makeText(CatalogResultActivity.this, "Terjadi kesalahan server, mohon coba lagi beberapa saat!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, t.getMessage());
                    showProgress(false);
                }
            });
        } else {
            call2 = apiService.getProdukSort(id, "nama", order_by);

            call2.enqueue(new Callback<Produk>() {
                @SuppressLint("WrongConstant")
                @Override
                public void onResponse(Call<Produk> call, Response<Produk> response) {
                    if (response.isSuccessful()) {
                        final List<com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum> dataItems = response.body().getData();

                        recyclerCatalog.setAdapter(new ListAllProductAdapter(dataItems, getBaseContext(), id, size, CatalogResultActivity.this));
                    } else {
                        Toast.makeText(CatalogResultActivity.this, "Terjadi kesalahan server, mohon coba lagi beberapa saat!", Toast.LENGTH_SHORT).show();
                    }
                    showProgress(false);
                }

                @Override
                public void onFailure(Call<Produk> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
                    Toast.makeText(CatalogResultActivity.this, "Terjadi kesalahan server, mohon coba lagi beberapa saat!", Toast.LENGTH_SHORT).show();
                    showProgress(false);
                }
            });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                order_by = data.getStringExtra("ORDERBY");
                status_order = 1;
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                id = data.getStringExtra("ID");
                loadData();
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                order_by = data.getStringExtra("ORDERBY");
                loadData();
            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    @OnClick({R.id.pilih_kategori, R.id.pilih_urutkan})
    public void onViewClicked(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.pilih_kategori:
                status_order = 0;
                i = new Intent(getBaseContext(), ListKategori.class);
                i.putExtra("ID", id);
                startActivityForResult(i, 2);
                //finish();
                break;
            case R.id.pilih_urutkan:
                i = new Intent(getBaseContext(), ListUrutkan.class);
                i.putExtra("ID", id);
                i.putExtra("SIZE", size);
                if (order_by == null) order_by = "asc";
                i.putExtra("ORDERBY", order_by);
                startActivityForResult(i, 3);
                break;
        }
    }

    @Override
    public void onClickProduct(Datum datum) {
        Intent intent = new Intent(this, DetailProductActivity.class);
        intent.putExtra("ID", datum.getId());

        intent.putExtra("ID", datum.getId());
        intent.putExtra("IMAGE", datum.getAttributes().getFoto());
        intent.putExtra("TITLE", datum.getAttributes().getNama());
        intent.putExtra("DETAIL", datum.getAttributes().getDeskripsi());
        intent.putExtra("POINT_PRODUCT", datum.getAttributes().getPoint());
        intent.putExtra("POINT_REWARD", BusinesRewardActivity.point_reward);
        intent.putExtra("KTP", BusinesRewardActivity.ktpnpwp);
        intent.putExtra("NOKTP", BusinesRewardActivity.no_ktp);
        intent.putExtra("NONPWP", BusinesRewardActivity.no_npwp);
        startActivityForResult(intent, 4);
    }

    @Override
    public void onClickProduct(Included included) {
        Intent intent = new Intent(this, DetailProductActivity.class);
        intent.putExtra("ID", included.getId());

        intent.putExtra("ID", included.getId());
        intent.putExtra("IMAGE", included.getAttributes().getFoto());
        intent.putExtra("TITLE", included.getAttributes().getNama());
        intent.putExtra("DETAIL", included.getAttributes().getDeskripsi());
        intent.putExtra("POINT_PRODUCT", included.getAttributes().getPoint());
        intent.putExtra("POINT_REWARD", BusinesRewardActivity.point_reward);
        intent.putExtra("KTP", BusinesRewardActivity.ktpnpwp);
        intent.putExtra("NOKTP", BusinesRewardActivity.no_ktp);
        intent.putExtra("NONPWP", BusinesRewardActivity.no_npwp);
        startActivityForResult(intent, 4);
    }
}
