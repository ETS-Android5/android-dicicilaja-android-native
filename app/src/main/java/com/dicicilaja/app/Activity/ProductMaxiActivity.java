package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView;
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
import com.dicicilaja.app.Adapter.ListRelatedAdapter;
import com.dicicilaja.app.Adapter.ProductImageSliderAdapter;
import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.OrderIn.UI.OrderInProductActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceDetailProgramMaxi;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi.DetailProgramMaxi;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.FALSE;

public class ProductMaxiActivity extends AppCompatActivity {

    SliderView sliderView;
    List<Data> detailProducts;
    SliderView head_image;
    RelativeLayout rute, syarat, deskripsi_lengkap;
    HashMap<Integer, String> file_maps = new HashMap<Integer, String>();
    WebView wv_deskripsi_lengkap;
    String apiKey, agen_id, agen_axi_id, agen_name;
    //    MaterialSpinner spinnerJaminan;
    TextView tv_title, tv_mitra, tv_harga;
    Button ajukan_produk;
    String extra_id;
    MaterialProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_product_maxi);

        ApiService3 apiService3;
        apiService3 = ApiClient2.getClient().create(ApiService3.class);

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
//        spinnerJaminan = findViewById(R.id.jaminan);
        progressBar = findViewById(R.id.progressBar);
//        tv_tenor = findViewById(R.id.tv_tenor);
        tv_title = findViewById(R.id.tv_title);
        tv_mitra = findViewById(R.id.tv_mitra);
        tv_harga = findViewById(R.id.tv_harga);
        ajukan_produk = findViewById(R.id.ajukan_produk);
        wv_deskripsi_lengkap = findViewById(R.id.wv_deskripsi_lengkap);
        deskripsi_lengkap = findViewById(R.id.deskripsi_lengkap);

        progressBar.setVisibility(View.GONE);

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

//        final List<String> TENOR_ITEMS = new ArrayList<>();
//        final HashMap<String, String> TENOR_MAP = new HashMap<String, String>();

        final RecyclerView recyclerPromo = (RecyclerView) findViewById(R.id.recycler_related);
        recyclerPromo.setHasFixedSize(true);
        recyclerPromo.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));


        final WebSettings webSettings = wv_deskripsi_lengkap.getSettings();
        webSettings.setDefaultFontSize(12);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBlockNetworkImage(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setGeolocationEnabled(false);
        webSettings.setNeedInitialFocus(false);
        webSettings.setSaveFormData(false);


        SnapHelper snapHelperPromo = new GravitySnapHelper(Gravity.START);
        snapHelperPromo.attachToRecyclerView(recyclerPromo);

        InterfaceDetailProgramMaxi apiService = RetrofitClient.getClient().create(InterfaceDetailProgramMaxi.class);

        try {
            extra_id = getIntent().getStringExtra("EXTRA_REQUEST_ID");
        } catch (Exception ex) {}

        Call<DetailProgramMaxi> call = apiService.getDetail(apiKey, extra_id);
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
                file_maps.put(0, detailProducts.get(0).getImageUrl());
                Uri uri = Uri.parse(detailProducts.get(0).getImageUrl());
                setSliderView(getBaseContext(),1,file_maps);
//                head_image.setImageURI(uri);
                tv_title.setText(detailProducts.get(0).getTitleProgram());

                final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
                AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
                collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));
                collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorApp));
                appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    boolean isShow = true;
                    int scrollRange = -1;

                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (scrollRange == -1) {
                            scrollRange = appBarLayout.getTotalScrollRange();
                        }
                        if (scrollRange + verticalOffset == 0) {
                            collapsingToolbarLayout.setTitle(detailProducts.get(0).getTitleProgram());
                            isShow = true;
                        } else if(isShow) {
                            collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                            isShow = false;
                        }
                    }
                });


//                getSupportActionBar().setTitle(detailProducts.get(0).getTitleProgram());
                tv_mitra.setText(detailProducts.get(0).getPartner());
                tv_harga.setText(detailProducts.get(0).getPrice());
                wv_deskripsi_lengkap.loadDataWithBaseURL(null, getIntent().getStringExtra("DESC"), "text/html", "utf-8", null);

//                TENOR_MAP.clear();
//                TENOR_ITEMS.clear();
//                TENOR_ITEMS.add("12");
//                TENOR_ITEMS.add("18");
//                TENOR_ITEMS.add("24");
//                TENOR_ITEMS.add("30");
//                TENOR_ITEMS.add("36");
//                TENOR_ITEMS.add("42");
//                TENOR_ITEMS.add("48");
//
//                for (int i = 0; i < response.body().getData().get(0).getTenor().size(); i++) {
//                    TENOR_MAP.put(response.body().getData().get(0).getTenor().get(i).getBulan(), response.body().getData().get(0).getTenor().get(i).getCicilan());
//                }
//
//                ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
//                tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinnerJaminan.setAdapter(tenor_adapter);
//                spinnerJaminan.setSelection(1);

            }

            @Override
            public void onFailure(Call<DetailProgramMaxi> call, Throwable t) {
                progress.dismiss();

            }
        });

//        spinnerJaminan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                tv_tenor.setText(TENOR_MAP.get(String.valueOf(spinnerJaminan.getSelectedItemPosition())));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
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
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if(session.isLoggedIn() == FALSE) {
                    Intent intent = new Intent(getBaseContext(), GuestActivity.class);
                    startActivity(intent);
                }else{
                    progress.show();
//                    Intent intent = new Intent(getBaseContext(), AjukanPengajuanMaxiActivity.class);
//                    intent.putExtra("pinjaman", String.valueOf(detailProducts.get(0).getPriceWithoutRp()));
//                    intent.putExtra("spinner_tenor",String.valueOf(spinnerJaminan.getSelectedItemPosition()));
//                    intent.putExtra("program",detailProducts.get(0).getJenisProgram());
//                    intent.putExtra("gambar", detailProducts.get(0).getImageUrl());
//                    intent.putExtra("title", detailProducts.get(0).getTitleProgram());
//                    intent.putExtra("mitra", detailProducts.get(0).getPartner());
//                    intent.putExtra("harga", detailProducts.get(0).getPrice());
//                    intent.putExtra("tenor", TENOR_MAP.get(String.valueOf(spinnerJaminan.getSelectedItemPosition())));
//                    intent.putExtra("id_partner",detailProducts.get(0).getIdPartner().toString());
//                    startActivity(intent);

                    Call<Axi> axiReff = apiService3.getAxi(session.getNomorAxiId());
                    axiReff.enqueue(new Callback<Axi>() {
                        @Override
                        public void onResponse(Call<Axi> call, Response<Axi> response) {
                            if (response.isSuccessful()) {
                                try {
                                    if (response.body().getData().size() > 0) {
                                        agen_id = String.valueOf(response.body().getData().get(0).getAttributes().getProfileId());
                                        agen_axi_id = String.valueOf(response.body().getData().get(0).getAttributes().getNomorAxiId());
                                        agen_name = response.body().getData().get(0).getAttributes().getNama();
                                        Intent intent = new Intent(getBaseContext(), OrderInProductActivity.class);
                                        intent.putExtra("amount", String.valueOf(detailProducts.get(0).getPriceWithoutRp()));
                                        intent.putExtra("product_id",detailProducts.get(0).getId());
                                        intent.putExtra("program",detailProducts.get(0).getJenisProgram());
                                        intent.putExtra("gambar", detailProducts.get(0).getImageUrl());
                                        intent.putExtra("title", detailProducts.get(0).getTitleProgram());
//                                        intent.putExtra("tenor", TENOR_ITEMS.get(spinnerJaminan.getSelectedItemPosition() - 1));
                                        intent.putExtra("mitra", detailProducts.get(0).getPartner());
                                        intent.putExtra("agen_id", agen_id);
                                        intent.putExtra("agen_axi_id", agen_axi_id);
                                        intent.putExtra("agen_name", agen_name);
                                        startActivity(intent);
                                        progress.hide();
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    } else {
                                        agen_id = null;
                                        agen_axi_id = null;
                                        agen_name = null;
                                        Intent intent = new Intent(getBaseContext(), OrderInProductActivity.class);
                                        intent.putExtra("amount", String.valueOf(detailProducts.get(0).getPriceWithoutRp()));
                                        intent.putExtra("product_id",detailProducts.get(0).getId());
                                        intent.putExtra("program",detailProducts.get(0).getJenisProgram());
                                        intent.putExtra("gambar", detailProducts.get(0).getImageUrl());
                                        intent.putExtra("title", detailProducts.get(0).getTitleProgram());
//                                        intent.putExtra("tenor", TENOR_ITEMS.get(spinnerJaminan.getSelectedItemPosition() - 1));
                                        intent.putExtra("mitra", detailProducts.get(0).getPartner());
                                        intent.putExtra("agen_id", agen_id);
                                        intent.putExtra("agen_axi_id", agen_axi_id);
                                        intent.putExtra("agen_name", agen_name);
                                        startActivity(intent);
                                        progress.hide();
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    }


                                } catch (Exception ex) {
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProductMaxiActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        startActivity(getIntent());
                                    }
                                });
                                alertDialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Axi> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProductMaxiActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

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

    private void setSliderView(Context context, int maxSlide, HashMap<Integer,String> file_maps){
        sliderView = (SliderView) findViewById(R.id.head_image);

        sliderView.setSliderAdapter(new ProductImageSliderAdapter(context,maxSlide,file_maps));
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
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
