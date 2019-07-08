package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.API.Model.LayananPPOB.PPOB;
import com.dicicilaja.app.Adapter.ListPPOBAdapter;
import com.dicicilaja.app.BranchOffice.UI.AreaBranchOffice.Activity.AreaBranchOfficeActivity;
import com.dicicilaja.app.NewSimulation.UI.NewSimulation.NewSimulationActivity;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.AxiSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.Datum;
import com.dicicilaja.app.WebView.MateriActivity;
import com.dicicilaja.app.WebView.NewsActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceInfoJaringan;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.AXIDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemInfoJaringan.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemInfoJaringan.InfoJaringan;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AxiDashboardActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SessionManager session;
    String token;
    List<com.dicicilaja.app.API.Model.PengajuanAxi.Datum> pengajuan;
    com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.Data itemDetail;

    RelativeLayout allpengajuan;
    InterfaceAxiDetail interfaceAxiDetail;
    SliderLayout mDemoSlider;
    ImageView icon1_web, icon2_web, copy_link;
    TextView link_web;
    List<Data> infoJaringan;
    TextView title_pengumuman, title_info, title_info_jaringan, title_replika, total_view, title_ppob;
    TextView title_box1, content_box1, title_box2, content_box2, title_box3, content_box3, title_box4, content_box4, title_box5, content_box5, title_box6, content_box6;

    String apiKey;
    RelativeLayout allpromo;
    LinearLayout insentif_car, insentif_mcy, point_reward, point_trip, button_rb, button_kedalaman_rb, footer_item_1;

    HashMap<String, String> file_maps;

    ProgressDialog progress;


    /* Update to Microservices - Variable */
    private List<PPOB> ppobList;
    RecyclerView recyclerView;
    ListPPOBAdapter adapter;

    int totalData = 1;
    int totalPage = 1;
    int currentPage = 1;
    boolean isLoading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axi_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();
        session.checkLogin();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        icon2_web = findViewById(R.id.icon2_web);
        copy_link = findViewById(R.id.copy_link);
        icon1_web = findViewById(R.id.icon1_web);
        link_web = findViewById(R.id.link_web);
        title_info = findViewById(R.id.title_info);
        title_info_jaringan = findViewById(R.id.title_info_jaringan);
        title_replika = findViewById(R.id.title_replika);
        title_ppob = findViewById(R.id.title_ppob);
        total_view = findViewById(R.id.total_view);
        title_box1 = findViewById(R.id.title_box1);
        content_box1 = findViewById(R.id.content_box1);
        title_box2 = findViewById(R.id.title_box2);
        content_box2 = findViewById(R.id.content_box2);
        title_box3 = findViewById(R.id.title_box3);
        content_box3 = findViewById(R.id.content_box3);
        title_box4 = findViewById(R.id.title_box4);
        content_box4 = findViewById(R.id.content_box4);
        title_box5 = findViewById(R.id.title_box5);
        content_box5 = findViewById(R.id.content_box5);
        title_box6 = findViewById(R.id.title_box6);
        content_box6 = findViewById(R.id.content_box6);
        insentif_car = findViewById(R.id.insentif_car);
        insentif_mcy = findViewById(R.id.insentif_mcy);
        point_reward = findViewById(R.id.point_reward);
        point_trip= findViewById(R.id.point_trip);
        button_kedalaman_rb = findViewById(R.id.button_kedalaman_rb);
        button_rb = findViewById(R.id.button_rb);
        footer_item_1 = findViewById(R.id.footer_item_1);
        allpengajuan = findViewById(R.id.allpengajuan);

        /* Update to Microservices - Data */
        ppobList = new ArrayList<>();
        adapter = new ListPPOBAdapter(ppobList, this);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_info.setTypeface(opensans_bold);
        title_ppob.setTypeface(opensans_bold);
        title_info_jaringan.setTypeface(opensans_bold);
        title_replika.setTypeface(opensans_bold);
        total_view.setTypeface(opensans_bold);
        title_box1.setTypeface(opensans_bold);
        content_box1.setTypeface(opensans_reguler);
        title_box2.setTypeface(opensans_bold);
        content_box2.setTypeface(opensans_reguler);
        title_box3.setTypeface(opensans_bold);
        content_box3.setTypeface(opensans_reguler);
        title_box4.setTypeface(opensans_bold);
        content_box4.setTypeface(opensans_reguler);
        title_box5.setTypeface(opensans_bold);
        content_box5.setTypeface(opensans_reguler);
        title_box6.setTypeface(opensans_bold);
        content_box6.setTypeface(opensans_reguler);

//        allpromo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(),AllPromoActivity.class);
//                startActivity(intent);
//            }
//        });

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);

//        recyclerView =  findViewById(R.id.recycler_pengajuan);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        final RecyclerView recyclerView = findViewById(R.id.recycler_ppob);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


//        recyclerPPOB.setHasFixedSize(true);
//        recyclerPPOB.setLayoutManager(new LinearLayoutManager(getBaseContext(),
//                LinearLayoutManager.HORIZONTAL, false));

//        SnapHelper snapHelperPromo = new GravitySnapHelper(Gravity.START);
//        snapHelperPromo.attachToRecyclerView(recyclerPPOB);

        createDummyData();

        try {
            Glide.with(this).load(R.drawable.ic_home).into((ImageView) findViewById(R.id.icon_image));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo apiService =
//                com.dicicilaja.app.Activity.RemoteMarketplace.Client.RetrofitClient.getClient().create(com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfacePromo.class);
//
//        Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call = apiService.getPromo();
//        call.enqueue(new Callback<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo>() {
//            @Override
//            public void onResponse(Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, ObjekModel<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> response) {
//                final List<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Datum> promos = response.body().getData();
//
//                recyclerPPOB.setAdapter(new ListPromoAdapter(promos, getBaseContext()));
//            }
//
//            @Override
//            public void onFailure(Call<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemPromo.Promo> call, Throwable t) {
//
//            }
//        });

        // Load Data Pengajuan
//        doLoadData();

        link_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link_web.getText().toString()));
                startActivity(browserIntent);
            }
        });
        icon1_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link_web.getText().toString()));
                startActivity(browserIntent);
            }
        });
        icon2_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Temukan solusi kebutuhan Anda disini "+link_web.getText().toString());
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Link web replika");
                startActivity(Intent.createChooser(intent, "Bagikan link web replika Anda"));
            }
        });
        copy_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("link",link_web.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(),"Berhasil menyalin link web replika",Toast.LENGTH_SHORT).show();
            }
        });
//        allpengajuan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), AllPengajuanAxiActivity.class);
//                startActivity(intent);
//            }
//        });

        InterfaceAxiDetail apiService5 =
                RetrofitClient.getClient().create(InterfaceAxiDetail.class);

//        showLoading();
        Call<AXIDetail> callProfile = apiService5.getDetail(apiKey);
        callProfile.enqueue(new Callback<AXIDetail>() {
            @Override
            public void onResponse(Call<AXIDetail> call, Response<AXIDetail> response) {
                if(response.isSuccessful()) {
                    Log.e("AAAA::::", "AXI Profile loaded");
                    itemDetail = response.body().getData();

                    Locale localeID = new Locale("in", "ID");
                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                    content_box1.setText(formatter.format(Integer.parseInt(String.valueOf(itemDetail.getPointReward()))).replace(",", "."));
                    content_box2.setText(formatter.format(Integer.parseInt(String.valueOf(itemDetail.getPointTrip()))).replace(",", "."));
                    content_box3.setText(formatRupiah.format((float) Float.parseFloat(itemDetail.getIncentiveCar())));
                    content_box4.setText(formatRupiah.format((float) Float.parseFloat(itemDetail.getIncentiveMcy())));
                    link_web.setText(itemDetail.getReplicaWebLink());
                }
                hideLoading();
            }

            @Override
            public void onFailure(Call<AXIDetail> call, Throwable t) {
                hideLoading();
                t.printStackTrace();
            }
        });

        InterfaceInfoJaringan apiService4 =
                RetrofitClient.getClient().create(InterfaceInfoJaringan.class);

        Call<InfoJaringan> call4 = apiService4.getInfoJaringan(apiKey);
        call4.enqueue(new Callback<InfoJaringan>() {
            @Override
            public void onResponse(Call<InfoJaringan> call, Response<InfoJaringan> response) {
                if(response.isSuccessful()){
                    infoJaringan = response.body().getData();
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                    content_box5.setText(formatter.format(Integer.parseInt(String.valueOf(infoJaringan.size()))).replace(",","."));

                }

            }

            @Override
            public void onFailure(Call<InfoJaringan> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AxiDashboardActivity.this);
                alertDialog.setMessage(t.getMessage());
                alertDialog.show();
            }
        });

        insentif_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),InsentifCarActivity.class);
                intent.putExtra("MENTOR", itemDetail.getIncentiveCarMentor().toString());
                intent.putExtra("EXTRA_BULANAN", itemDetail.getIncentiveCarExtraBulanan().toString());
                intent.putExtra("GROUP", itemDetail.getIncentiveCarGroup().toString());
                intent.putExtra("BONUS_TAHUNAN", itemDetail.getIncentiveCarBonusTahunan().toString());
                intent.putExtra("BONUS_LAYOUT", itemDetail.getIncentiveCarBonusLayout().toString());
                startActivity(intent);
            }
        });

        point_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),PointRewardActivity.class);
                intent.putExtra("POINT_REWARD", String.valueOf(itemDetail.getPointReward()));
                startActivity(intent);
            }
        });

        point_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),PointTripActivity.class);
                intent.putExtra("POINT_TRIP", itemDetail.getPointTrip().toString());
                startActivity(intent);
            }
        });

        insentif_mcy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),InsentifMcyActivity.class);
                intent.putExtra("MENTOR", itemDetail.getIncentiveMcyMentor().toString());
                intent.putExtra("EXTRA_BULANAN", itemDetail.getIncentiveMcyExtraBulanan().toString());
                intent.putExtra("GROUP", itemDetail.getIncentiveMcyGroup().toString());
                intent.putExtra("BONUS_TAHUNAN", itemDetail.getIncentiveMcyBonusTahunan().toString());
                intent.putExtra("BONUS_LAYOUT", itemDetail.getIncentiveMcyBonusLayout().toString());
                startActivity(intent);
            }
        });
        button_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),InfoJaringanActivity.class);
                intent.putExtra("total_rb", content_box5.getText().toString());
                startActivity(intent);
            }
        });
        button_kedalaman_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AllPengajuanAxiActivity.class);
                startActivity(intent);
            }
        });
        footer_item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),MarketplaceActivity.class);
                startActivity(intent);
            }
        });


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view3);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.navbar_dashboard);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                switch( menuItem.getItemId() ) {
                    case R.id.navbar_dashboard:
                        break;
                    case R.id.navbar_create_request:
                        intent = new Intent(getBaseContext(), AjukanPengajuanAxiActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_simulation:
                        intent = new Intent(getBaseContext(), NewSimulationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_jaringan:
                        intent = new Intent(getBaseContext(), InfoJaringanActivity.class);
                        intent.putExtra("total_rb", content_box5.getText().toString());
                        startActivity(intent);
                        break;
                    case R.id.navbar_news:
                        Intent intent5 = new Intent(getBaseContext(), NewsActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.navbar_materi:
                        Intent intent6 = new Intent(getBaseContext(), MateriActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.navbar_add:
                        Intent intent2 = new Intent(getBaseContext(), RegisterAxi1Activity.class);
                        startActivity(intent2);
                        break;
                    case R.id.branch_office:
                        Intent intent3 = new Intent(getBaseContext(), AreaBranchOfficeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.navbar_exit:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AxiDashboardActivity.this);

                        // Setting Dialog Title
                        alertDialog.setTitle("Konfirmasi");

                        // Setting Dialog Message
                        alertDialog.setMessage("Apakah Anda yakin ingin keluar?");


                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                session.logoutUser();
                            }
                        });

                        // Setting Negative "NO" Button
                        alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
                        break;
                }
                drawer.closeDrawers();
                return true;

            }

        });

        CircleImageView profilePictures =  navigationView.getHeaderView(0).findViewById(R.id.profile_picture_axi);
        View navbarView = navigationView.getHeaderView(0);
        LinearLayout open_profile = navbarView.findViewById(R.id.open_profile);
        TextView name = navbarView.findViewById(R.id.nameView);

        String imageUrl = session.getPhoto();
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(profilePictures);
        name.setText(session.getName());

        open_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        file_maps = new HashMap<String, String>();

//        file_maps.put("https://dicicilaja.com/gudang-info/Saatnya-Jadi-AXI%21-Dan-Jadilah-Pahlawan-Bagi-Keluarga","https://dicicilaja.com/uploads/news/1510289120-saatnya-jadi-axi-dan-jadilah-pahlawan-bagi-keluarga.jpg");
//        file_maps.put("","https://dicicilaja.com/uploads/banner/0persen.jpg");
        InterfaceAxiSlider apiSlider =
                RetrofitClient.getClient().create(InterfaceAxiSlider.class);

        Call<AxiSlider> call6 = apiSlider.getSlider();
        call6.enqueue(new Callback<AxiSlider>() {
            @Override
            public void onResponse(Call<AxiSlider> call6, Response<AxiSlider> response) {
                List <Datum> slider = response.body().getData();
                Log.d("SLIDER AXI", slider.toString());
                for (int i = 0; i < slider.size(); i++) {
                    Log.d("slideraxi", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                    file_maps.put(slider.get(i).getUrl(), slider.get(i).getImage());
                }

                for(final Datum s: slider) {
                    Log.d("DASH::::", s.getImage());
                    DefaultSliderView sliderBannerItem = new DefaultSliderView(AxiDashboardActivity.this);
                    sliderBannerItem
                            .image(s.getImage())
                            .description(s.getUrl())
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s.getUrl()));
                                    startActivity(browserIntent);
                                }
                            });
                    mDemoSlider.addSlider(sliderBannerItem);
                }

            }

            @Override
            public void onFailure(Call<AxiSlider> call, Throwable t) {
                Log.e("AXI SLIDER::::", t.toString());
            }
        });


        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Left_Bottom);
        mDemoSlider.setDuration(4000);
//        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        //mDemoSlider.addOnPageChangeListener(this);
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

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(slider.getBundle().get("extra").toString()));
        startActivity(browserIntent);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

//    private void doLoadData() {
//        showLoading();
//
//        InterfacePengajuanAxi apiService =
//                RetrofitClient.getClient().create(InterfacePengajuanAxi.class);
//
//        Call<PengajuanAxi> call2 = apiService.getPengajuanAxi(apiKey, currentPage);
//        call2.enqueue(new Callback<PengajuanAxi>() {
//            @Override
//            public void onResponse(Call<PengajuanAxi> call, ObjekModel<PengajuanAxi> response) {
//                if(response.isSuccessful()) {
//                    pengajuan = response.body().getData();
//                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
//
//                    content_box6.setText(formatter.format(Integer.parseInt(String.valueOf(pengajuan.size()))).replace(",","."));
//
//                    recyclerView.setAdapter(new PengajuanAxiAdapter(pengajuan, R.layout.card_pengajuan, getBaseContext(), 3));
//                    recyclerView.setNestedScrollingEnabled(false);
//                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
//                        @Override
//                        public void onClick(View view, final int position) {
//                            Intent intent = new Intent(getBaseContext(), DetailRequestActivity.class);
//                            intent.putExtra("EXTRA_REQUEST_ID", pengajuan.get(position).getId().toString());
//                            startActivity(intent);
//
//                        }
//
//                        @Override
//                        public void onLongClick(View view, int position) {
//                        }
//                    }));
//                }
//                //progress.dismiss();
////                hideLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PengajuanAxi> call, Throwable t) {
////                hideLoading();
//                t.printStackTrace();
//            }
//        });
//    }

    private void createDummyData() {
        int[] covers = new int[]{
                R.drawable.pln,
                R.drawable.bpjs,
                R.drawable.pdam};

        PPOB a = new PPOB("PLN", 1, covers[0]);
        ppobList.add(a);

        a = new PPOB("BPJS ", 2, covers[1]);
        ppobList.add(a);

        a = new PPOB("PDAM", 3, covers[2]);
        ppobList.add(a);

        adapter.notifyDataSetChanged();
    }

    private void initListener() {

    }

    private void showLoading() {
        isLoading = true;
        progress.show();
    }

    private void hideLoading() {
        isLoading = false;
        progress.dismiss();
    }
}