package com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.BusinessReward.ui.RedeemConfirm.RedeemConfirmationActivity;
import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailProduct2Activity extends AppCompatActivity {

    String id, name, alamat, tgl, no_transaksi, no_transaksi2, tgl_penukaran, status_pengiriman, produk_id, nama, nama_barang, gambar_barang, tot_point;
    int point, thumbnail, sisa, point_barang;
    @BindView(R.id.uploadid1)
    ImageView uploadid1;
    @BindView(R.id.tambah_alamat)
    RelativeLayout tambahAlamat;
    @BindView(R.id.ubahB)
    TextView ubahB;
    @BindView(R.id.ubah_form)
    RelativeLayout ubahForm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_alamat)
    TextView tvAlamat;
    @BindView(R.id.detail_user)
    LinearLayout detailUser;
    @BindView(R.id.barang_picture)
    ImageView barangPicture;
    @BindView(R.id.title_barang)
    TextView titleBarang;
    @BindView(R.id.point)
    TextView tvPoint;
    @BindView(R.id.spek_title)
    TextView spekTitle;
    @BindView(R.id.title_kategori)
    TextView titleKategori;
    @BindView(R.id.tv_total_point)
    TextView tvTotalPoint;
    @BindView(R.id.title_processor)
    TextView titleProcessor;
    @BindView(R.id.tv_point_barang)
    TextView tvPointBarang;
    @BindView(R.id.div)
    View div;
    @BindView(R.id.title_os)
    TextView titleOs;
    @BindView(R.id.tv_sisa_point)
    TextView tvSisaPoint;
    @BindView(R.id.klaim)
    Button klaim;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product2);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        Intent intent = getIntent();
        tgl = intent.getStringExtra("DATE");
        alamat = intent.getStringExtra("ALAMAT");
        no_transaksi = intent.getStringExtra("NO_TRANSAKSI");
        no_transaksi2 = intent.getStringExtra("NO_TRANSAKSI2");
        tgl_penukaran = intent.getStringExtra("TGL_PENUKARAN");
        status_pengiriman = intent.getStringExtra("STATUS_PENGIRIMAN");

        produk_id = intent.getStringExtra("PRODUK_ID");
        tot_point = intent.getStringExtra("TOTAL_POINT");
        point_barang = intent.getIntExtra("POINT_BARANG", 0);
        nama = intent.getStringExtra("NAMA");
        nama_barang = intent.getStringExtra("NAMA_BARANG");
        gambar_barang = intent.getStringExtra("GAMBAR_BARANG");

//        tvNama.setText(nama);
//        tvAlamat.setText(alamat);
        tvPointBarang.setText(String.valueOf(intent.getIntExtra("POINT_BARANG", 0)));
        tvTotalPoint.setText(tot_point);
        sisa = Integer.parseInt(tot_point) - point_barang;
        tvSisaPoint.setText(String.valueOf(sisa));

        titleBarang.setText(nama_barang);
        tvPoint.setText(point_barang + " Point");
        Glide.with(this).load(gambar_barang).into(barangPicture);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.axi_dasboard, menu);
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
            Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @OnClick(R.id.klaim)
//    public void onViewClicked() {
//        Intent intent = new Intent(getBaseContext(), RedeemConfirmationActivity.class);
//        intent.putExtra("ID", id);
//        intent.putExtra("Name", name);
//        intent.putExtra("Point", point);
//        intent.putExtra("Thumbnail", thumbnail);
//        startActivity(intent);
//    }

    @OnClick(R.id.tambah_alamat)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), PilihCabangVendorActivity.class);
        startActivity(intent);
    }
}
