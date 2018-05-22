package com.dicicilaja.dicicilaja.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceDetailProgramMaxi;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.Data;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.DetailProgramMaxi;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductMaxiActivity extends AppCompatActivity {

    Data detailProducts;
    ImageView head_image;
    RelativeLayout rute, syarat;
    String apiKey;
    MaterialSpinner spinnerJaminan;
    TextView tv_tenor, tv_title, tv_mitra, tv_harga;
    Button ajukan_produk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_maxi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }


        head_image = findViewById(R.id.head_image);
        spinnerJaminan = findViewById(R.id.jaminan);
        tv_tenor = findViewById(R.id.tv_tenor);
        tv_title = findViewById(R.id.tv_title);
        tv_mitra = findViewById(R.id.tv_mitra);
        tv_harga = findViewById(R.id.tv_harga);
        ajukan_produk = findViewById(R.id.ajukan_produk);

        rute = findViewById(R.id.rute);
        syarat = findViewById(R.id.syarat);
        rute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RutePerjalananActivity.class);
                startActivity(intent);

            }
        });
        syarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SyaratKetentuanActivity.class);
                startActivity(intent);
            }
        });


        final List<String> TENOR_ITEMS = new ArrayList<>();
        final HashMap<String, String> TENOR_MAP = new HashMap<String, String>();

        InterfaceDetailProgramMaxi apiService = NewRetrofitClient.getClient().create(InterfaceDetailProgramMaxi.class);

        Call<DetailProgramMaxi> call = apiService.getDetail(apiKey,getIntent().getStringExtra("EXTRA_REQUEST_ID"));
        call.enqueue(new Callback<DetailProgramMaxi>() {
            @Override
            public void onResponse(Call<DetailProgramMaxi> call, Response<DetailProgramMaxi> response) {
                detailProducts = response.body().getData();
                Picasso.with(getBaseContext()).load(detailProducts.getImageUrl()).into(head_image);
                tv_title.setText(detailProducts.getTitleProgram());
                tv_mitra.setText(detailProducts.getPartner());
                tv_harga.setText(detailProducts.getPrice());

                TENOR_MAP.clear();
                TENOR_ITEMS.clear();
                TENOR_ITEMS.add("12");
                TENOR_ITEMS.add("18");
                TENOR_ITEMS.add("24");
                TENOR_ITEMS.add("30");
                TENOR_ITEMS.add("36");
                TENOR_ITEMS.add("42");
                TENOR_ITEMS.add("48");

                for (int i = 0; i < response.body().getData().getTenor().size(); i++) {
                    TENOR_MAP.put(response.body().getData().getTenor().get(i).getBulan(), response.body().getData().getTenor().get(i).getCicilan());
                }

                ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
                tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerJaminan.setAdapter(tenor_adapter);
                spinnerJaminan.setSelection(1);

            }

            @Override
            public void onFailure(Call<DetailProgramMaxi> call, Throwable t) {
                TENOR_MAP.clear();
                TENOR_ITEMS.clear();

            }
        });

        spinnerJaminan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv_tenor.setText(TENOR_MAP.get(String.valueOf(spinnerJaminan.getSelectedItemPosition())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ajukan_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AjukanPengajuanMaxiActivity.class);
                intent.putExtra("id_program",detailProducts.getIdProgram());
                intent.putExtra("gambar", detailProducts.getImageUrl());
                intent.putExtra("title", detailProducts.getTitleProgram());
                intent.putExtra("mitra", detailProducts.getPartner());
                intent.putExtra("harga", detailProducts.getPrice());
                intent.putExtra("tenor", TENOR_MAP.get(String.valueOf(spinnerJaminan.getSelectedItemPosition())));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_maxi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.fav) {
            Toast.makeText(getBaseContext(),"Anda menyukai produk ini",Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.share) {
            Toast.makeText(getBaseContext(),"Bagikan",Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.home) {
            super.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
