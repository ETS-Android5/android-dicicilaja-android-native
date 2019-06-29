package com.dicicilaja.app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward.ClaimReward;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward.ClaimRewards;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward.Datum;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiClient;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiService;
import com.dicicilaja.app.Activity.BusinessReward.ui.Transaction.adapter.ListClaimRewardAdapter;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedeemConfirmation2Activity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_tujuan)
    TextView titleTujuan;
    @BindView(R.id.tv_alamat)
    TextView tvAlamat;
    @BindView(R.id.detail_user)
    LinearLayout detailUser;
    @BindView(R.id.pengirim)
    RelativeLayout pengirim;
    @BindView(R.id.title_penukaran)
    TextView titlePenukaran;
    @BindView(R.id.tv_no_trans)
    TextView tvNoTrans;
    @BindView(R.id.detail_penukaran)
    LinearLayout detailPenukaran;
    @BindView(R.id.tv_tgl_trans)
    TextView tvTglTrans;
    @BindView(R.id.detail_penukaran2)
    LinearLayout detailPenukaran2;
    @BindView(R.id.notrans)
    LinearLayout notrans;
    @BindView(R.id.tv_tgl_pen)
    TextView tvTglPen;
    @BindView(R.id.tv_tgl_pen2)
    TextView tvTglPen2;
    @BindView(R.id.tgltrans)
    LinearLayout tgltrans;
    @BindView(R.id.stat_kirim)
    TextView statKirim;
    @BindView(R.id.stat_kirim2)
    LinearLayout statKirim2;
    @BindView(R.id.status_pengiriman)
    LinearLayout statusPengiriman;
    @BindView(R.id.stat_selesai)
    TextView statSelesai;
    @BindView(R.id.selesai)
    LinearLayout selesai;
    @BindView(R.id.info_penukaran)
    RelativeLayout infoPenukaran;
    @BindView(R.id.spek_penukaran)
    TextView spekPenukaran;
    @BindView(R.id.title_barang)
    TextView titleBarang;
    @BindView(R.id.point)
    TextView point;
    @BindView(R.id.detail_produk)
    LinearLayout detailProduk;
    @BindView(R.id.barang_picture)
    ImageView barangPicture;
    @BindView(R.id.spek_barang_detail)
    RelativeLayout spekBarangDetail;
    @BindView(R.id.title_ulasan)
    TextView titleUlasan;
    @BindView(R.id.ulasan_produk)
    RelativeLayout ulasanProduk;

    String id, name, userId;
    int poin, thumbnail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_confirmation2);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("PRODUK_ID");

        final SessionManager session = new SessionManager(getBaseContext());
        userId = session.getUserId();
//        name = intent.getStringExtra("Name");
//        poin = (int) intent.getIntExtra("Point",0);
//        thumbnail = intent.getIntExtra("Thumbnail", 0);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<ClaimRewards> call = apiService.getClaim(Integer.parseInt(userId));
        call.enqueue(new Callback<ClaimRewards>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<ClaimRewards> call, Response<ClaimRewards> response) {
                final List<Datum> dataItems = response.body().getData();
                Log.d("Cek1", ""+response.body().getData());

//                recyclerTransaksi.setAdapter(new ListClaimRewardAdapter(dataItems, getBaseContext()));
            }

            @Override
            public void onFailure(Call<ClaimRewards> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.ulasan_produk)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), ReviewActivity.class);
        startActivity(intent);
    }
}
