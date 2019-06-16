package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    String id, name;
    int poin, thumbnail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_confirmation2);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("Name");
        poin = (int) intent.getIntExtra("Point",0);
        thumbnail = intent.getIntExtra("Thumbnail", 0);


    }

    @OnClick(R.id.ulasan_produk)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), ReviewActivity.class);
        startActivity(intent);
    }
}
