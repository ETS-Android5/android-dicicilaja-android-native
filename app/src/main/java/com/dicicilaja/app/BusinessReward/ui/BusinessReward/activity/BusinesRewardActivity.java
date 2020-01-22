package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp.FotoKtpNpwp;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.GetCart;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.point.DataItem;
import com.dicicilaja.app.BusinessReward.dataAPI.point.ExistingPoint;
import com.dicicilaja.app.BusinessReward.dataAPI.point.Point;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiClient3;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.network.ApiService3;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListProductAdapter;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.BusinessReward.ui.Cart.CartActivity;
import com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity.DetailProductActivity;
import com.dicicilaja.app.BusinessReward.ui.KtpNpwp.activity.UploadKTPActivity;
import com.dicicilaja.app.BusinessReward.ui.Search.activity.SearchResultActivity;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.TransactionActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinesRewardActivity extends AppCompatActivity implements ListProductAdapter.ProductCallback {

    public ProgressDialog progress;
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.uploadKTP)
    LinearLayout uploadKTP;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    SessionManager session;
    public static String final_point;
    public static String ktpnpwp, no_ktp, no_npwp, point_reward;
    ApiService apiService, api;
    ApiService3 apiService3;

    String apiKey;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.idProduct)
    LinearLayout idProduct;
    @BindView(R.id.image_cart)
    ImageView imageCart;
    @BindView(R.id.point_cart)
    TextView pointCart;
    @BindView(R.id.text_cart)
    TextView textCart;
    @BindView(R.id.floating_cart)
    RelativeLayout floatingCart;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_reward);
        ButterKnife.bind(this);

        initToolbar();
        initAction();
        initLoadData();

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initLoadData();
                swipeToRefresh.setRefreshing(false);
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        progress = new ProgressDialog(this);
//        progress.setMessage("Sedang memuat data...");
//        progress.setCanceledOnTouchOutside(false);
//
//        progress.show();
//
//        Call<GetCart> callCart = apiService3.getCart(apiKey);
//        callCart.enqueue(new Callback<GetCart>() {
//            @Override
//            public void onResponse(Call<GetCart> call, Response<GetCart> response2) {
//                if (response2.body().getData().getAttributes().getItems().size() != 0 ) {
//                    progress.hide();
//                    int total_points = response2.body().getData().getAttributes().getTotalPoints();
//                    int total_items = response2.body().getData().getAttributes().getTotalItems();
//
//                    floatingCart.setVisibility(View.VISIBLE);
//                    textCart.setText(total_items + " Barang dikeranjang");
//                    pointCart.setText(total_points + " Poin");
//                } else {
//                    progress.hide();
//                    floatingCart.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetCart> call, Throwable t) {
//
//            }
//        });
//    }

    private void initToolbar() {
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
    }

    private void initAction() {
        session = new SessionManager(getBaseContext());
        Log.d("asd", "initAction: " + session.getToken());
        apiKey = "Bearer " + session.getToken();
        try {
            final_point = getIntent().getStringExtra("POINT_REWARD");
            String imageUrl = session.getPhoto();
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(profilePicturePage);
        } catch (Exception ex) {
        }
        swipeToRefresh.setColorSchemeResources(R.color.colorAccent);
        recyclerProduk.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                RecyclerView.VERTICAL, false));
        recyclerProduk.setHasFixedSize(true);
        recyclerProduk.setNestedScrollingEnabled(false);
        profilePoint.setText(final_point);

        apiService = ApiClient.getClient().create(ApiService.class);
        api = ApiClient3.getClient().create(ApiService.class);
        apiService3 = ApiClient3.getClient().create(ApiService3.class);
    }

    private void initLoadData() {
        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        Call<GetCart> callCart = apiService3.getCart(apiKey);
        callCart.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart> response2) {
                if (response2.body().getData().getAttributes().getItems().size() != 0 ) {
                    progress.hide();
                    int total_points = response2.body().getData().getAttributes().getTotalPoints();
                    int total_items = response2.body().getData().getAttributes().getTotalItems();

                    floatingCart.setVisibility(View.VISIBLE);
                    textCart.setText(total_items + " Barang dikeranjang");
                    pointCart.setText(total_points + " Poin");
                } else {
                    progress.hide();
                    floatingCart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {

            }
        });

        Call<ExistingPoint> call2 = api.getExistingPoint(session.getUserId());
//        Call<Point> call2 = apiService.getPoint(Integer.parseInt(session.getUserId()));
        call2.enqueue(new Callback<ExistingPoint>() {
            @Override
            public void onResponse(Call<ExistingPoint> call, Response<ExistingPoint> response2) {

                if (response2.isSuccessful()) {
                    final List<ExistingPoint.Data> dataItems = response2.body().getData();
                    if (dataItems.size() == 0) {
                        Toast.makeText(getBaseContext(), "Belum ada data point.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (response2.body().getData().get(0).getAttributes().getPointReward() == 0) {
                            profilePoint.setText("0");
                        } else {
                            profilePoint.setText(String.valueOf(response2.body().getData().get(0).getAttributes().getPointReward()));
                        }
                        point_reward = String.valueOf(response2.body().getData().get(0).getAttributes().getPointReward());
                    }
                } else {
                    Log.d("asd", "onResponse: " + new Gson().toJson(response2.body()));
                }
            }

            @Override
            public void onFailure(Call<ExistingPoint> call, Throwable t) {
                Log.d("asd", "onResponse: " + t.getMessage());
            }
        });


        Call<FotoKtpNpwp> callKtp = apiService.getFoto(session.getUserId());
        callKtp.enqueue(new Callback<FotoKtpNpwp>() {
            @Override
            public void onResponse(Call<FotoKtpNpwp> call, Response<FotoKtpNpwp> response) {
                final List<com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp.Datum> dataItems = response.body().getData();
                if (dataItems.size() == 0) {
                    uploadKTP.setVisibility(View.VISIBLE);
                    ktpnpwp = "Tidak";
                } else {
                    uploadKTP.setVisibility(View.GONE);
                    ktpnpwp = "Ada";
                    no_ktp = response.body().getData().get(0).getAttributes().getNoKtp();
                    no_npwp = response.body().getData().get(0).getAttributes().getNoNpwp();
                }

            }

            @Override
            public void onFailure(Call<FotoKtpNpwp> call, Throwable t) {
                Log.d("sizenyaaa", "data: " + t.getMessage());
            }
        });

        Call<KategoriProduk> call = apiService.getKategori();
        call.enqueue(new Callback<KategoriProduk>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<KategoriProduk> call, Response<KategoriProduk> response) {
                final List<Datum> dataItems = response.body().getData();
                final List<Included> dataItems2 = response.body().getIncluded();

                recyclerProduk.setAdapter(new ListProductCatalogAdapter(dataItems, dataItems2, getBaseContext(), BusinesRewardActivity.this));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<KategoriProduk> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BusinesRewardActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Gagal memuat data, silahkan coba beberapa saat lagi.");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                alertDialog.show();
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
        if (id == R.id.history) {
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

    @OnClick({R.id.upload, R.id.floating_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload:
                Intent intent = new Intent(getBaseContext(), UploadKTPActivity.class);
                startActivity(intent);
                break;
            case R.id.floating_cart:
                Intent intent2 = new Intent(getBaseContext(), CartActivity.class);
                intent2.putExtra("point", point_reward);
                startActivityForResult(intent2, 98);
                break;
        }
    }

    @Override
    public void onClickProduct(List<Included> pcList2, int finalj) {
        Intent intent = new Intent(this, DetailProductActivity.class);
        intent.putExtra("ID", pcList2.get(finalj).getId());
        intent.putExtra("IMAGE", pcList2.get(finalj).getAttributes().getFoto());
        intent.putExtra("TITLE", pcList2.get(finalj).getAttributes().getNama());
        intent.putExtra("DETAIL", pcList2.get(finalj).getAttributes().getDeskripsi());
        intent.putExtra("POINT_PRODUCT", pcList2.get(finalj).getAttributes().getPoint());
        intent.putExtra("POINT_REWARD", BusinesRewardActivity.point_reward);
        intent.putExtra("KTP", ktpnpwp);
        intent.putExtra("NOKTP", no_ktp);
        intent.putExtra("NONPWP", no_npwp);
        startActivityForResult(intent, 99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * Kode 99 untuk handle detail produk
         * Kode 98 untuk handle update cart (update quantity/delete product)
         */
        if (requestCode == 99 || requestCode == 98) {
            if (resultCode == RESULT_OK) {
                initLoadData();
                setResult(RESULT_OK);
            }
        }
    }
}
