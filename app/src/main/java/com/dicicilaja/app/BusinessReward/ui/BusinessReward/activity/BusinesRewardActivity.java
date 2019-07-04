package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.dicicilaja.app.BusinessReward.ui.Search.activity.SearchResultActivity;
import com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp.FotoKtpNpwp;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.point.Point;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.BusinessReward.ui.KtpNpwp.activity.UploadKTPActivity;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.TransactionActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

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
    ApiService apiService;

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

    private  void initToolbar() {
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
        try {
            final_point = getIntent().getStringExtra("POINT_REWARD");
            Log.d("POINTGOBLOG", final_point);
            String imageUrl = session.getPhoto();
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(profilePicturePage);
        } catch (Exception ex) { }
        swipeToRefresh.setColorSchemeResources(R.color.colorAccent);
        recyclerProduk.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                RecyclerView.VERTICAL, false));
        recyclerProduk.setHasFixedSize(true);
        recyclerProduk.setNestedScrollingEnabled(false);
        profilePoint.setText(final_point);

        apiService = ApiClient.getClient().create(ApiService.class);
    }

    private void initLoadData() {
        Call<Point> call2 = apiService.getPoint(Integer.parseInt(session.getUserId()));
        call2.enqueue(new Callback<Point>() {
            @Override
            public void onResponse(Call<Point> call, Response<Point> response2) {

                final List<com.dicicilaja.app.BusinessReward.dataAPI.point.Datum> dataItems = response2.body().getData();
                profilePoint.setText(String.valueOf(response2.body().getData().get(0).getAttributes().getPointReward()));
                point_reward = String.valueOf(response2.body().getData().get(0).getAttributes().getPointReward());
            }

            @Override
            public void onFailure(Call<Point> call, Throwable t) {

            }
        });


        Call<FotoKtpNpwp> callKtp = apiService.getFoto(Integer.parseInt(session.getUserId()));
        callKtp.enqueue(new Callback<FotoKtpNpwp>() {
            @Override
            public void onResponse(Call<FotoKtpNpwp> call, Response<FotoKtpNpwp> response) {
                final List<com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp.Datum> dataItems = response.body().getData();
                Log.d("sizenyaaa", String.valueOf(dataItems.size()));
                if (dataItems.size() == 0) {
                    uploadKTP.setVisibility(View.VISIBLE);
                    ktpnpwp = "Tidak";
                } else {
                    ktpnpwp = "Ada";
                    no_ktp = response.body().getData().get(0).getAttributes().getNoKtp();
                    no_npwp = response.body().getData().get(0).getAttributes().getNoNpwp();
                }

            }

            @Override
            public void onFailure(Call<FotoKtpNpwp> call, Throwable t) {

            }
        });

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

    @OnClick(R.id.upload)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), UploadKTPActivity.class);
        startActivity(intent);
    }
}
