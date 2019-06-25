package com.dicicilaja.app.Activity.BusinessReward.ui.DetailProduct.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailProduk.DetailProduk;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiClient;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiService;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {

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
    @BindView(R.id.spek_barang_detail)
    RelativeLayout spekBarangDetail;
    @BindView(R.id.klaim)
    Button klaim;

    String name;
    int point, thumbnail, id;
    @BindView(R.id.deskripsi)
    TextView deskripsi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
//        name = intent.getStringExtra("Name");
//        point = (int) intent.getIntExtra("Point", 0);
//        thumbnail = intent.getIntExtra("Thumbnail", 0);

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<DetailProduk> call = apiService.getDetailProduk(Integer.valueOf(id));
        call.enqueue(new Callback<DetailProduk>() {
            @Override
            public void onResponse(Call<DetailProduk> call, Response<DetailProduk> response) {
                titleBarang.setText(response.body().getData().getAttributes().getNama());
                tvPoint.setText(response.body().getData().getAttributes().getPoint() + " Point");
                Glide.with(getBaseContext()).load(response.body().getData().getAttributes().getFoto()).into(barangPicture);
                deskripsi.setText(response.body().getData().getAttributes().getDeskripsi());
            }

            @Override
            public void onFailure(Call<DetailProduk> call, Throwable t) {

            }
        });
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
        Intent intent = new Intent(getBaseContext(), DetailProduct2Activity.class);
        intent.putExtra("ID", id);
        intent.putExtra("Name", name);
        intent.putExtra("Point", point);
        intent.putExtra("Thumbnail", thumbnail);
        startActivity(intent);
    }
}
