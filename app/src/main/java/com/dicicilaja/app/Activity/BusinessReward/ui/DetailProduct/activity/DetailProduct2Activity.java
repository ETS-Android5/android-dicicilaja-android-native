package com.dicicilaja.app.Activity.BusinessReward.ui.DetailProduct.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.Activity.RedeemConfirmationActivity;
import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailProduct2Activity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_tujuan)
    TextView titleTujuan;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_alamat)
    TextView tvAlamat;
    @BindView(R.id.detail_user)
    LinearLayout detailUser;
    @BindView(R.id.pengirim)
    RelativeLayout pengirim;
    @BindView(R.id.barang_picture)
    ImageView barangPicture;
    @BindView(R.id.title_barang)
    TextView titleBarang;
    @BindView(R.id.point)
    TextView tvPoint;
    @BindView(R.id.barang_image)
    RelativeLayout barangImage;
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
    @BindView(R.id.spek_barang_detail)
    RelativeLayout spekBarangDetail;
    @BindView(R.id.klaim)
    Button klaim;

    String id, name;
    int point, thumbnail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product2);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("Name");
        point = (int) intent.getIntExtra("Point",0);
        thumbnail = intent.getIntExtra("Thumbnail", 0);

        titleBarang.setText(name);
        tvPoint.setText(String.valueOf(point)+ " Point");
        Glide.with(this).load(thumbnail).into(barangPicture);
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

    @OnClick(R.id.klaim)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), RedeemConfirmationActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("Name", name);
        intent.putExtra("Point", point);
        intent.putExtra("Thumbnail", thumbnail);
        startActivity(intent);
    }
}
