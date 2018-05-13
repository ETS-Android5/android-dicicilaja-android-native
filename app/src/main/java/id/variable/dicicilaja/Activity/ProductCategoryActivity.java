package id.variable.dicicilaja.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.variable.dicicilaja.API.Client.ClientDatabaseEmployee;
import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceDatabaseEmployee;
import id.variable.dicicilaja.API.Interface.InterfacePromo;
import id.variable.dicicilaja.API.Interface.InterfaceRecommend;
import id.variable.dicicilaja.API.Item.DatabaseEmployee;
import id.variable.dicicilaja.API.Item.DatabaseEmployeeResponse;
import id.variable.dicicilaja.API.Item.Product.SectionDataModel;
import id.variable.dicicilaja.API.Item.Product.SingleItemModel;
import id.variable.dicicilaja.API.Item.Promo.Datum;
import id.variable.dicicilaja.API.Item.Promo.Promo;
import id.variable.dicicilaja.API.Item.Recommend.Recommend;
import id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceMaxiTravel;
import id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceMaxiUsaha;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiTravel.Data;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiTravel.MaxiTravel;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiTravel.Product;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiUsaha.MaxiUsaha;
import id.variable.dicicilaja.Adapter.DatabaseEmployeeAdapter;
import id.variable.dicicilaja.Adapter.ListPromoAdapter;
import id.variable.dicicilaja.Adapter.ListRekomendasiAdapter;
import id.variable.dicicilaja.Adapter.RecyclerViewDataAdapter;
import id.variable.dicicilaja.Content.PromoModel;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoryActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mDemoSlider;
    private ArrayList<PromoModel> promoData;
    private SnapHelper snapHelper;
    String content;
    String apiKey;
    private ArrayList<SectionDataModel> allSampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);

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

        if(content.equals("maxi_travel")){
            InterfaceMaxiTravel apiService =
                    NewRetrofitClient.getClient().create(InterfaceMaxiTravel.class);

            Call<MaxiTravel> call2 = apiService.getProduct(apiKey);
            call2.enqueue(new Callback<MaxiTravel>() {
                @Override
                public void onResponse(Call<MaxiTravel> call, Response<MaxiTravel> response) {
                    List<Data> maxi = response.body().getData();
                    for (int i = 0; i < maxi.size(); i++) {
                        SectionDataModel dm = new SectionDataModel();
                        dm.setHeaderTitle(maxi.get(i).getTag());
                        ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();

                        List<Product> product = maxi.get(i).getProduct();
                        for (int j = 0; j < maxi.get(i).getProduct().size(); j++) {

                            singleItemModels.add(new SingleItemModel(product.get(j).getName(), product.get(j).getImage(), product.get(j).getPartner(), product.get(j).getPrice(), product.get(j).getExcerpt()));
                        }
                        dm.setAllItemInSection(singleItemModels);
                        allSampleData.add(dm);
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<MaxiTravel> call, Throwable t) {
                    progress.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
                    alertDialog.setMessage("Koneksi internet tidak ditemukan");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
            });
        }else if(content.equals("maxi_usaha")){
            InterfaceMaxiUsaha apiService =
                    NewRetrofitClient.getClient().create(InterfaceMaxiUsaha.class);

            Call<MaxiUsaha> call2 = apiService.getProduct(apiKey);
            call2.enqueue(new Callback<MaxiUsaha>() {
                @Override
                public void onResponse(Call<MaxiUsaha> call, Response<MaxiUsaha> response) {
                    List<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiUsaha.Data> maxi = response.body().getData();
                    for (int i = 0; i < maxi.size(); i++) {
                        SectionDataModel dm = new SectionDataModel();
                        dm.setHeaderTitle(maxi.get(i).getTag());
                        ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();

                        List<id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiUsaha.Product> product = maxi.get(i).getProduct();
                        for (int j = 0; j < maxi.get(i).getProduct().size(); j++) {

                            singleItemModels.add(new SingleItemModel(product.get(j).getName(), product.get(j).getImage(), product.get(j).getPartner(), product.get(j).getPrice(), product.get(j).getExcerpt()));
                        }
                        dm.setAllItemInSection(singleItemModels);
                        allSampleData.add(dm);
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<MaxiUsaha> call, Throwable t) {
                    progress.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
                    alertDialog.setMessage("Koneksi internet tidak ditemukan");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
            });
        }else if(content.equals("maxi_sehat")){

        }else if(content.equals("maxi_edukasi")){

        }else if(content.equals("maxi_griya")){

        }else if(content.equals("maxi_extraguna")){

        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData, this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        mDemoSlider = (SliderLayout) findViewById(R.id.slider_product);

        final HashMap<Integer, String> file_maps = new HashMap<Integer, String>();
        file_maps.put(1,"https://dicicilaja.com/uploads/banner/1523528108Homebanner%20Tasya.jpg");
        file_maps.put(2,"https://dicicilaja.com/uploads/banner/banner-dana-tunai.jpg");


        for(Integer name : file_maps.keySet()){
            final DefaultSliderView sliderView = new DefaultSliderView(getBaseContext());
            // initialize a SliderLayout
            sliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderView.setOnSliderClickListener(this);
            sliderView.bundle(new Bundle());
            sliderView.getBundle()
                    .putString("extra",name.toString());
            mDemoSlider.addSlider(sliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator_product));
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
                return true;
            case R.id.search:
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
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
