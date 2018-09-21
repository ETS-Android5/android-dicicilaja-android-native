package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
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

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceFavorite;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateRequest.CreateRequest;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.Related;
import com.dicicilaja.app.Adapter.ListPromoAdapter;
import com.dicicilaja.app.Adapter.ListRelatedAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import com.dicicilaja.app.API.Client.NewRetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceDetailProgramMaxi;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.DetailProgramMaxi;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.FALSE;

public class ProductMaxiActivity extends AppCompatActivity {

    List<Data> detailProducts;
    SimpleDraweeView head_image;
    RelativeLayout rute, syarat, deskripsi_lengkap;
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
        deskripsi_lengkap = findViewById(R.id.deskripsi_lengkap);

//        rute = findViewById(R.id.rute);
//        syarat = findViewById(R.id.syarat);
//        rute.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), RutePerjalananActivity.class);
//                startActivity(intent);
//
//            }
//        });
//        syarat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), SyaratKetentuanActivity.class);
//                startActivity(intent);
//            }
//        });

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        final List<String> TENOR_ITEMS = new ArrayList<>();
        final HashMap<String, String> TENOR_MAP = new HashMap<String, String>();

        final RecyclerView recyclerPromo = (RecyclerView) findViewById(R.id.recycler_related);
        recyclerPromo.setHasFixedSize(true);
        recyclerPromo.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));

        SnapHelper snapHelperPromo = new GravitySnapHelper(Gravity.START);
        snapHelperPromo.attachToRecyclerView(recyclerPromo);

        InterfaceDetailProgramMaxi apiService = RetrofitClient.getClient().create(InterfaceDetailProgramMaxi.class);

        Call<DetailProgramMaxi> call = apiService.getDetail(apiKey,getIntent().getStringExtra("EXTRA_REQUEST_ID"));
        call.enqueue(new Callback<DetailProgramMaxi>() {
            @Override
            public void onResponse(Call<DetailProgramMaxi> call, Response<DetailProgramMaxi> response) {
                progress.dismiss();

                final List<Related> related = response.body().getData().get(0).getRelated();

                recyclerPromo.setAdapter(new ListRelatedAdapter(related, getBaseContext()));

                detailProducts = response.body().getData();
                //Picasso.with(ProductMaxiActivity.this)
                //        .load(detailProducts.get(0).getImageUrl())
                //        .into(head_image);
                Uri uri = Uri.parse(detailProducts.get(0).getImageUrl());
                head_image.setImageURI(uri);
                tv_title.setText(detailProducts.get(0).getTitleProgram());
                tv_mitra.setText(detailProducts.get(0).getPartner());
                tv_harga.setText(detailProducts.get(0).getPrice());

                TENOR_MAP.clear();
                TENOR_ITEMS.clear();
                TENOR_ITEMS.add("12");
                TENOR_ITEMS.add("18");
                TENOR_ITEMS.add("24");
                TENOR_ITEMS.add("30");
                TENOR_ITEMS.add("36");
                TENOR_ITEMS.add("42");
                TENOR_ITEMS.add("48");

                for (int i = 0; i < response.body().getData().get(0).getTenor().size(); i++) {
                    TENOR_MAP.put(response.body().getData().get(0).getTenor().get(i).getBulan(), response.body().getData().get(0).getTenor().get(i).getCicilan());
                }

                ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
                tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerJaminan.setAdapter(tenor_adapter);
                spinnerJaminan.setSelection(1);

            }

            @Override
            public void onFailure(Call<DetailProgramMaxi> call, Throwable t) {
                progress.dismiss();
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
        deskripsi_lengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DeskripsiLengkapActivity.class);
                intent.putExtra("description",detailProducts.get(0).getDescription());
                startActivity(intent);
            }
        });
        ajukan_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(session.isLoggedIn() == FALSE) {
                    Intent intent = new Intent(getBaseContext(), GuestActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getBaseContext(), AjukanPengajuanMaxiActivity.class);
                    intent.putExtra("pinjaman", String.valueOf(detailProducts.get(0).getPriceWithoutRp()));
                    intent.putExtra("spinner_tenor",String.valueOf(spinnerJaminan.getSelectedItemPosition()));
                    intent.putExtra("program",detailProducts.get(0).getJenisProgram());
                    intent.putExtra("gambar", detailProducts.get(0).getImageUrl());
                    intent.putExtra("title", detailProducts.get(0).getTitleProgram());
                    intent.putExtra("mitra", detailProducts.get(0).getPartner());
                    intent.putExtra("harga", detailProducts.get(0).getPrice());
                    intent.putExtra("tenor", TENOR_MAP.get(String.valueOf(spinnerJaminan.getSelectedItemPosition())));
                    intent.putExtra("id_partner",detailProducts.get(0).getIdPartner().toString());
                    startActivity(intent);
                }

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
            doFav(apiKey,detailProducts.get(0).getId());

            return true;
        } else if (id == R.id.share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, detailProducts.get(0).getTitleProgram()+" "+detailProducts.get(0).getLink());
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Link produk");
            startActivity(Intent.createChooser(intent, "Bagikan produk"));
            return true;
        } else if (id == R.id.home) {
            super.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doFav(final String apiKey, final String product_id) {
        InterfaceFavorite apiService =
                RetrofitClient.getClient().create(InterfaceFavorite.class);

        Call<CreateRequest> call = apiService.assign(apiKey, product_id);
        call.enqueue(new Callback<CreateRequest>() {
            @Override
            public void onResponse(Call<CreateRequest> call, Response<CreateRequest> response) {
                Toast.makeText(getBaseContext(),"Anda menyukai produk ini",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CreateRequest> call, Throwable t) {

            }
        });
    }
}
