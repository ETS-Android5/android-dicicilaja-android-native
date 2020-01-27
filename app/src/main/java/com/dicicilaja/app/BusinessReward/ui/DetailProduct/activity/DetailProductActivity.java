package com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.GetCart;
import com.dicicilaja.app.BusinessReward.dataAPI.postCart.PostCart;
import com.dicicilaja.app.BusinessReward.network.ApiClient3;
import com.dicicilaja.app.BusinessReward.network.ApiService3;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.Data;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class DetailProductActivity extends AppCompatActivity {

    private static final String TAG = DetailProductActivity.class.getSimpleName();

    public static String ktpnpwp, no_ktp, no_npwp;

    Data itemDetail;

    String name;
    int point, thumbnail, id;

    SessionManager session;
    String apiKey;

    int pointProduk;
    String noNpwp, noKtp, pointUser;

    String branchId, areaId, crhId, productCatalogId, statusId, noResi, noPo, ongkir, namaCabang, namaArea;
    String profileId, alamatC;
    String cabangText;
    String fotoKtp, fotoNpwp, nomorKtp, nomorNpwp;
    int product_id;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.barang_picture)
    ImageView barangPicture;
    @BindView(R.id.barang_image)
    RelativeLayout barangImage;
    @BindView(R.id.title_barang)
    TextView titleBarang;
    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.barang_detail)
    RelativeLayout barangDetail;
    @BindView(R.id.spek_title)
    TextView spekTitle;
    @BindView(R.id.deskripsi)
    TextView deskripsi;
    @BindView(R.id.spek_barang_detail)
    RelativeLayout spekBarangDetail;
    @BindView(R.id.cart)
    Button cart;

    String gambar_barang;
    ProgressDialog progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);

        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Mohon tunggu sebentar...");
        progressBar.setCanceledOnTouchOutside(false);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        gambar_barang = getIntent().getStringExtra("IMAGE");

        productCatalogId = String.valueOf(getIntent().getIntExtra("ID", 0));
        titleBarang.setText(getIntent().getStringExtra("TITLE"));
        tvPoint.setText(getIntent().getIntExtra("POINT_PRODUCT", 0) + " POINT");
        Glide.with(getBaseContext()).load(getIntent().getStringExtra("IMAGE")).into(barangPicture);

        String textt = getIntent().getStringExtra("DETAIL").replace("\\n", "\n");
        deskripsi.setText(textt);
        pointUser = getIntent().getStringExtra("POINT_REWARD");
        pointProduk = getIntent().getIntExtra("POINT_PRODUCT", 0);
        no_ktp = getIntent().getStringExtra("NOKTP");
        ktpnpwp = getIntent().getStringExtra("KTP");
        no_npwp = getIntent().getStringExtra("NONPWP");
        id = intent.getIntExtra("ID", 0);

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.show();

                ApiService3 apiService3 = ApiClient3.getClient().create(ApiService3.class);

                Call<PostCart> call = apiService3.postCart(apiKey, productCatalogId, null);
                call.enqueue(new Callback<PostCart>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(Call<PostCart> call, Response<PostCart> response) {

                        setResult(RESULT_OK);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<PostCart> call, Throwable t) {

                    }
                });

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.axi_dasboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.notif) {
            Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
