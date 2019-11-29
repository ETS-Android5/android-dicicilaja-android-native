package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAsuransi;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceEdukasi;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceExtraguna;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceSehat;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceTravel;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUsaha;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.AxiSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.Datum;
import com.dicicilaja.app.Adapter.AxiImageSliderAdapter;
import com.dicicilaja.app.Adapter.BerandaImageSliderAdapter;
import com.dicicilaja.app.Adapter.ListMaxiAdapter;
import com.dicicilaja.app.Adapter.ProductImageSliderAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dicicilaja.app.API.Model.Product.SectionDataModel;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceMaxiProgram;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiProgram.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiProgram.MaxiProgram;
import com.dicicilaja.app.Content.PromoModel;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoryActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderView sliderView;
    SliderLayout mDemoSlider;
    private ArrayList<PromoModel> promoData;
    private SnapHelper snapHelper;
    String content;
    String apiKey;
    private ArrayList<SectionDataModel> allSampleData;
    HashMap<Integer, String> file_maps = new HashMap<Integer, String>();
    int maxSlide;

    RecyclerView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
//        Fresco.initialize(getBaseContext());
        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        promoData = new ArrayList<>();
        allSampleData = new ArrayList<>();
        snapHelper = new GravitySnapHelper(Gravity.START);

        content = getIntent().getStringExtra("content");

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        if(content.equals("travel")){
            InterfaceTravel apiSlider =
                    RetrofitClient.getClient().create(InterfaceTravel.class);
            Call<AxiSlider> call5 = apiSlider.getSlider();
            call5.enqueue(new Callback<AxiSlider>() {
                @Override
                public void onResponse(Call<AxiSlider> call, Response<AxiSlider> response) {
                    progress.dismiss();
                    List<Datum> slider = response.body().getData();
                    maxSlide = slider.size();
//                    if (position < slider.size()) {
                    for (int i = 0; i < slider.size(); i++) {
                        Log.d("sliderasuransi", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                        file_maps.put(i, slider.get(i).getImage());
                    }
                    setSliderView(getBaseContext(),maxSlide,file_maps);
//                    }
                }

                @Override
                public void onFailure(Call<AxiSlider> call, Throwable t) {
                }
            });

        }
        else if(content.equals("edukasi")){
            InterfaceEdukasi apiSlider =
                    RetrofitClient.getClient().create(InterfaceEdukasi.class);

            Call<AxiSlider> call5 = apiSlider.getSlider();
            call5.enqueue(new Callback<AxiSlider>() {
                @Override
                public void onResponse(Call<AxiSlider> call, Response<AxiSlider> response) {
                    progress.dismiss();
                    List<Datum> slider = response.body().getData();
                    maxSlide = slider.size();
                    for (int i = 0; i < slider.size(); i++) {
                        Log.d("slideredukasi", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                        file_maps.put(i, slider.get(i).getImage());
                    }
                    setSliderView(getBaseContext(),maxSlide,file_maps);
                }

                @Override
                public void onFailure(Call<AxiSlider> call, Throwable t) {
                }
            });

        }else if(content.equals("usaha")){
            InterfaceUsaha apiSlider =
                    RetrofitClient.getClient().create(InterfaceUsaha.class);

            Call<AxiSlider> call5 = apiSlider.getSlider();
            call5.enqueue(new Callback<AxiSlider>() {
                @Override
                public void onResponse(Call<AxiSlider> call, Response<AxiSlider> response) {
                    progress.dismiss();
                    List<Datum> slider = response.body().getData();
                    maxSlide = slider.size();
                    for (int i = 0; i < slider.size(); i++) {
                        Log.d("sliderusaha", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                        file_maps.put(i, slider.get(i).getImage());
                    }
                    setSliderView(getBaseContext(),maxSlide,file_maps);
                }

                @Override
                public void onFailure(Call<AxiSlider> call, Throwable t) {
                }
            });

        }else if(content.equals("sehat")){
            InterfaceSehat apiSlider =
                    RetrofitClient.getClient().create(InterfaceSehat.class);

            Call<AxiSlider> call5 = apiSlider.getSlider();
            call5.enqueue(new Callback<AxiSlider>() {
                @Override
                public void onResponse(Call<AxiSlider> call, Response<AxiSlider> response) {
                    progress.dismiss();
                    List<Datum> slider = response.body().getData();
                    maxSlide = slider.size();
                    for (int i = 0; i < slider.size(); i++) {
                        Log.d("slidersehat", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                        file_maps.put(i, slider.get(i).getImage());
                    }
                    setSliderView(getBaseContext(),maxSlide,file_maps);
                }

                @Override
                public void onFailure(Call<AxiSlider> call, Throwable t) {
                }
            });

        }else if(content.equals("asuransi")){
            InterfaceAsuransi apiSlider =
                    RetrofitClient.getClient().create(InterfaceAsuransi.class);

            Call<AxiSlider> call5 = apiSlider.getSlider();
            call5.enqueue(new Callback<AxiSlider>() {
                @Override
                public void onResponse(Call<AxiSlider> call, Response<AxiSlider> response) {
                    progress.dismiss();
                    List<Datum> slider = response.body().getData();
                    maxSlide = slider.size();
                    for (int i = 0; i < slider.size(); i++) {
                        Log.d("sliderasuransi", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                        file_maps.put(i, slider.get(i).getImage());
                    }
                    setSliderView(getBaseContext(),maxSlide,file_maps);
                }

                @Override
                public void onFailure(Call<AxiSlider> call, Throwable t) {
                }
            });

        }else if(content.equals("extraguna")){
            InterfaceExtraguna apiSlider =
                    RetrofitClient.getClient().create(InterfaceExtraguna.class);

            Call<AxiSlider> call5 = apiSlider.getSlider();
            call5.enqueue(new Callback<AxiSlider>() {
                @Override
                public void onResponse(Call<AxiSlider> call, Response<AxiSlider> response) {
                    progress.dismiss();
                    List<Datum> slider = response.body().getData();
                    maxSlide = slider.size();
                    for (int i = 0; i < slider.size(); i++) {
                        Log.d("sliderextraguna", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                        file_maps.put(i, slider.get(i).getImage());
                    }
                    setSliderView(getBaseContext(),maxSlide,file_maps);
                }

                @Override
                public void onFailure(Call<AxiSlider> call, Throwable t) {
                }
            });

        }

        search = findViewById(R.id.recycler_search);
        search.setHasFixedSize(false);
        search.setLayoutManager(new GridLayoutManager(this, 2));

        InterfaceMaxiProgram apiService =
                RetrofitClient.getClient().create(InterfaceMaxiProgram.class);

        Call<MaxiProgram> call = apiService.getProduct(apiKey, content);
        call.enqueue(new Callback<MaxiProgram>() {
            @Override
            public void onResponse(Call<MaxiProgram> call, Response<MaxiProgram> response) {
                List<Data> maxi = response.body().getData();
                progress.dismiss();
                search.setAdapter(new ListMaxiAdapter(maxi, getBaseContext()));
            }

            @Override
            public void onFailure(Call<MaxiProgram> call, Throwable t) {

            }
        });



    }

    private void setSliderView(Context context, int maxSlide, HashMap<Integer,String> file_maps){
        sliderView = (SliderView) findViewById(R.id.imageSlider);

        sliderView.setSliderAdapter(new ProductImageSliderAdapter(context,maxSlide,file_maps));
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.product_category, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
                return true;
//            case R.id.search:
//                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
//                startActivity(intent);
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
//        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        Intent intent = new Intent(getBaseContext(), PromoActivity.class);
        intent.putExtra("ID",slider.getBundle().get("extra").toString());
        startActivity(intent);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
