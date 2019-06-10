package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.API.Model.ProductCatalog.ProductCatalog;
import com.dicicilaja.app.Adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.R;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import de.hdodenhof.circleimageview.CircleImageView;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BusinesRewardActivity extends AppCompatActivity {

    @BindView(R.id.profile_picture_page)
    CircleImageView profilePicturePage;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_point)
    TextView profilePoint;
    @BindView(R.id.coin)
    ImageView coin;
    @BindView(R.id.info)
    ImageView info;
    @BindView(R.id.profile_note)
    TextView profileNote;
    @BindView(R.id.title_motor)
    TextView titleMotor;
    @BindView(R.id.all_motor)
    TextView allMotor;
    @BindView(R.id.recycler_motor)
    RecyclerView recyclerMotor;
    @BindView(R.id.title_handphone)
    TextView titleHandphone;
    @BindView(R.id.all_handphone)
    TextView allHandphone;
    @BindView(R.id.recycler_hp)
    RecyclerView recyclerHp;
    @BindView(R.id.title_elektronik)
    TextView titleElektronik;
    @BindView(R.id.all_elektronik)
    TextView allElektronik;
    @BindView(R.id.recycler_elektronika)
    RecyclerView recyclerElektronika;

    private ArrayList<ProductCatalog> productCatalogList;
    private ArrayList<ProductCatalog> productCatalogList2;
    private ArrayList<ProductCatalog> productCatalogList3;

    ListProductCatalogAdapter adapter;
    ListProductCatalogAdapter adapter2;
    ListProductCatalogAdapter adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_reward);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        productCatalogList = new ArrayList<>();
        productCatalogList2 = new ArrayList<>();
        productCatalogList3 = new ArrayList<>();

        adapter = new ListProductCatalogAdapter(productCatalogList, this);
        adapter2 = new ListProductCatalogAdapter(productCatalogList2, this);
        adapter3 = new ListProductCatalogAdapter(productCatalogList3, this);

        //motor
        recyclerMotor.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerMotor.setHasFixedSize(true);
        recyclerMotor.setAdapter(adapter);

        SnapHelper snapHelperMotor = new GravitySnapHelper(Gravity.START);
        snapHelperMotor.attachToRecyclerView(recyclerMotor);

        //handphone
        recyclerHp.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerHp.setHasFixedSize(true);
        recyclerHp.setAdapter(adapter2);

        SnapHelper snapHelperMotor2 = new GravitySnapHelper(Gravity.START);
        snapHelperMotor2.attachToRecyclerView(recyclerMotor);

        //handphone
        recyclerElektronika.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerElektronika.setHasFixedSize(true);
        recyclerElektronika.setAdapter(adapter3);

        SnapHelper snapHelperMotor3 = new GravitySnapHelper(Gravity.START);
        snapHelperMotor3.attachToRecyclerView(recyclerMotor);

//        InterfaceRecommendation apiService2 =
//                RetrofitClient.getClient().create(InterfaceRecommendation.class);
//
//        Call<Recommendation> call2 = apiService2.getRecommend();
//        call2.enqueue(new Callback<Recommendation>() {
//            @Override
//            public void onResponse(Call<Recommendation> call, Response<Recommendation> response) {
//                final List<Datum> recommends = response.body().getData();
//
//                recyclerRekomendasi.setAdapter(new ListRekomendasiAdapter(recommends, getBaseContext()));
//            }
//
//            @Override
//            public void onFailure(Call<Recommendation> call, Throwable t) {
//
//            }
//        });

        createDummyData();
    }

    private void createDummyData() {

        int[] motor = new int[]{
                R.drawable.motor1,
                R.drawable.motor2,
                R.drawable.motor3};

        //motor
        productCatalogList.add(new ProductCatalog("Yamaha New Fino 125",1, motor[0],2000));
        productCatalogList.add(new ProductCatalog("Yamaha Mio M3",2, motor[1],1900));
        productCatalogList.add(new ProductCatalog("Honda Vario 150",3, motor[2],2100));
        productCatalogList.add(new ProductCatalog("Yamaha New Fino 125",4, motor[0],2000));
        adapter.notifyDataSetChanged();

        //hp
        productCatalogList2.add(new ProductCatalog("Xiaomi A2 Lite", 1, motor[0], 450));
        productCatalogList2.add(new ProductCatalog("Huawei Y6", 2, motor[1], 250));
        productCatalogList2.add(new ProductCatalog("Samsung Galaxy A2", 3, motor[2], 350));
        productCatalogList2.add(new ProductCatalog("Xiaomi A2 Lite", 4, motor[0], 410));
        adapter2.notifyDataSetChanged();

        //elektronik
        productCatalogList3.add(new ProductCatalog("Speaker JBL", 1, motor[0], 250));
        productCatalogList3.add(new ProductCatalog("JBL Flip 4", 2, motor[1], 200));
        productCatalogList3.add(new ProductCatalog("Sony Extra Bass", 3, motor[2], 150));
        productCatalogList3.add(new ProductCatalog("Speaker JBL", 4, motor[0], 125));
        adapter3.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.business_reward_menu, menu);
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
        } else if (id == R.id.search) {
            Intent intent = new Intent(getBaseContext(), SearchResultActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.all_motor, R.id.all_handphone, R.id.all_elektronik})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_motor:
                Intent intent = new Intent(getBaseContext(), CatalogResultActivity.class);
                startActivity(intent);
                break;
            case R.id.all_handphone:
                break;
            case R.id.all_elektronik:
                break;
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                super.finish();
//        }
//        return true;
//    }
}
