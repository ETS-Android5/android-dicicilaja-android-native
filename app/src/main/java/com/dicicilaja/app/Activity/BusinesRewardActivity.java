package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.asksira.webviewsuite.WebViewSuite;
import com.dicicilaja.app.API.Model.ProductCatalog.ProductCatalog;
import com.dicicilaja.app.Adapter.ListProductCatalog2Adapter;
import com.dicicilaja.app.Adapter.ListProductCatalog3Adapter;
import com.dicicilaja.app.Adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.R;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BusinesRewardActivity extends AppCompatActivity {

    TextView title_pc, value_point, redeem_point, download_katalog;
    ImageView reward1, reward2, reward3, reward4, reward5, reward6, reward7;
    WebViewSuite webviewPoint;

    /*update*/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile_picture_page)
    CircleImageView profilePicturePage;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_point)
    TextView profilePoint;
    @BindView(R.id.coin)
    ImageView coin;
    @BindView(R.id.bar_point)
    RelativeLayout barPoint;
    @BindView(R.id.title_pc)
    TextView titlePc;
    @BindView(R.id.txt_all_promo)
    TextView txtAllPromo;
    @BindView(R.id.show_all_promo)
    RelativeLayout showAllPromo;
    @BindView(R.id.top_pengajuan)
    RelativeLayout topPengajuan;
    @BindView(R.id.recycler_motor)
    RecyclerView recyclerMotor;
    @BindView(R.id.rl_motor)
    RelativeLayout rlMotor;
    @BindView(R.id.title_pc2)
    TextView titlePc2;
    @BindView(R.id.txt_all_promo2)
    TextView txtAllPromo2;
    @BindView(R.id.show_all_promo2)
    RelativeLayout showAllPromo2;
    @BindView(R.id.top_pengajuan2)
    RelativeLayout topPengajuan2;
    @BindView(R.id.recycler_hp)
    RecyclerView recyclerHp;
    @BindView(R.id.rl_hp)
    RelativeLayout rlHp;
    @BindView(R.id.title_pc3)
    TextView titlePc3;
    @BindView(R.id.txt_all_promo3)
    TextView txtAllPromo3;
    @BindView(R.id.show_all_promo3)
    RelativeLayout showAllPromo3;
    @BindView(R.id.top_pengajuan3)
    RelativeLayout topPengajuan3;
    @BindView(R.id.recycler_elektronika)
    RecyclerView recyclerElektronika;
    @BindView(R.id.rl_elektronik)
    RelativeLayout rlElektronik;

    private List<ProductCatalog> productCatalogList;
    private List<ProductCatalog> productCatalogList2;
    private List<ProductCatalog> productCatalogList3;

    ListProductCatalogAdapter adapter;
    ListProductCatalog2Adapter adapter2;
    ListProductCatalog3Adapter adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_reward);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        titlePc.setTypeface(opensans_bold);
        titlePc2.setTypeface(opensans_bold);
        titlePc3.setTypeface(opensans_bold);


//        download_katalog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropbox.com/s/7uiwbvyqk52ppq5/Katalog%20AXI%202018.pdf?dl=0"));
//                startActivity(browserIntent);
//            }
//        });
//
//        redeem_point.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/bQLGvEJ9weGkjdOf2"));
//                startActivity(browserIntent);
//            }
//        });

        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

//
//        value_point.setText(formatter.format(Integer.parseInt(String.valueOf(getIntent().getStringExtra("POINT_REWARD")))).replace(",","."));
//        reward1 = findViewById(R.id.reward1);
//        reward2 = findViewById(R.id.reward2);
//        reward3 = findViewById(R.id.reward3);
//        reward4 = findViewById(R.id.reward4);
//        reward5 = findViewById(R.id.reward5);
//        reward6 = findViewById(R.id.reward6);
//        reward7 = findViewById(R.id.reward7);
//
//        String imageUrl1 = "https://dicicilaja.com/uploads/reward/reward1.jpg";
//        String imageUrl2 = "https://dicicilaja.com/uploads/reward/reward2.jpg";
//        String imageUrl3 = "https://dicicilaja.com/uploads/reward/reward3.jpg";
//        String imageUrl4 = "https://dicicilaja.com/uploads/reward/reward4.jpg";
//        String imageUrl5 = "https://dicicilaja.com/uploads/reward/reward5.jpg";
//        String imageUrl6 = "https://dicicilaja.com/uploads/reward/reward6.jpg";
//        String imageUrl7 = "https://dicicilaja.com/uploads/reward/reward7.jpg";
//
//        Picasso.with(getApplicationContext()).load(imageUrl1).into(reward1);
//        Picasso.with(getApplicationContext()).load(imageUrl2).into(reward2);
//        Picasso.with(getApplicationContext()).load(imageUrl3).into(reward3);
//        Picasso.with(getApplicationContext()).load(imageUrl4).into(reward4);
//        Picasso.with(getApplicationContext()).load(imageUrl5).into(reward5);
//        Picasso.with(getApplicationContext()).load(imageUrl6).into(reward6);
//        Picasso.with(getApplicationContext()).load(imageUrl7).into(reward7);

        String pointForm = "http://bit.ly/dicicilajareward";

        productCatalogList = new ArrayList<>();
        productCatalogList2 = new ArrayList<>();
        productCatalogList3 = new ArrayList<>();

        adapter = new ListProductCatalogAdapter(productCatalogList, this);
        adapter2 = new ListProductCatalog2Adapter(productCatalogList2, this);
        adapter3 = new ListProductCatalog3Adapter(productCatalogList3, this);

        //motor
        final RecyclerView recyclerMotor = (RecyclerView) findViewById(R.id.recycler_motor);
        recyclerMotor.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerMotor.setHasFixedSize(true);
        recyclerMotor.setAdapter(adapter);

        SnapHelper snapHelperMotor = new GravitySnapHelper(Gravity.START);
        snapHelperMotor.attachToRecyclerView(recyclerMotor);

        //handphone
        final RecyclerView recyclerHp = (RecyclerView) findViewById(R.id.recycler_hp);
        recyclerHp.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerHp.setHasFixedSize(true);
        recyclerHp.setAdapter(adapter2);

        SnapHelper snapHelperMotor2 = new GravitySnapHelper(Gravity.START);
        snapHelperMotor2.attachToRecyclerView(recyclerMotor);

        //handphone
        final RecyclerView recyclerELektronik = (RecyclerView) findViewById(R.id.recycler_elektronika);
        recyclerELektronik.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerELektronik.setHasFixedSize(true);
        recyclerELektronik.setAdapter(adapter3);

        SnapHelper snapHelperMotor3 = new GravitySnapHelper(Gravity.START);
        snapHelperMotor3.attachToRecyclerView(recyclerMotor);

//        InterfaceRecommendation apiService2 =
//                RetrofitClient.getClient().create(InterfaceRecommendation.class);

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
        int[] covers = new int[]{
                R.drawable.pln,
                R.drawable.bpjs,
                R.drawable.pdam};

        //motor
        ProductCatalog a = new ProductCatalog("Yamaha Fino", 1, covers[0], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 2, covers[1], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 3, covers[2], 2000);
        productCatalogList.add(a);

        adapter.notifyDataSetChanged();

        //hp
        ProductCatalog b = new ProductCatalog("Xiaomi A2 Lite", 1, covers[0], 450);
        productCatalogList2.add(b);

        b = new ProductCatalog("Xiaomi A2 Lite", 1, covers[0], 450);
        productCatalogList2.add(b);

        b = new ProductCatalog("Xiaomi A2 Lite", 1, covers[0], 450);
        productCatalogList2.add(b);

        adapter2.notifyDataSetChanged();

        //elektronik
        ProductCatalog c = new ProductCatalog("Speaker JBL", 1, covers[0], 250);
        productCatalogList3.add(c);

        c = new ProductCatalog("Speaker JBL", 1, covers[0], 250);
        productCatalogList3.add(c);

        c = new ProductCatalog("Speaker JBL", 1, covers[0], 250);
        productCatalogList3.add(c);

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
        }else if(id == R.id.search){
            Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
