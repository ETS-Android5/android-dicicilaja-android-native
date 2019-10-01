package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.*;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfacePengajuanAxi;
import com.dicicilaja.app.API.Model.LayananPPOB.PPOB;
import com.dicicilaja.app.API.Model.PengajuanAxi.PengajuanAxi;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceInfoJaringan;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.AXIDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.AxiSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.Datum;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemInfoJaringan.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemInfoJaringan.InfoJaringan;
import com.dicicilaja.app.Adapter.ListPPOBAdapter;
import com.dicicilaja.app.BranchOffice.UI.AreaBranchOffice.Activity.AreaBranchOfficeActivity;
import com.dicicilaja.app.BusinessReward.dataAPI.point.Point;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity.AvailableBRActivity;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity.BusinesRewardActivity;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.TransactionActivity;
import com.dicicilaja.app.NewSimulation.UI.NewSimulation.NewSimulationActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.WebView.MateriActivity;
import com.dicicilaja.app.WebView.NewsActivity;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AxiDashboardActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SessionManager session;
    String token;
    List<com.dicicilaja.app.API.Model.PengajuanAxi.Datum> pengajuan;
    com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.Data itemDetail;

    SliderLayout mDemoSlider;
    List<Data> infoJaringan;

    String apiKey;
    RelativeLayout allpromo;

    HashMap<String, String> file_maps;

    /*update*/
    ProgressDialog progress;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.sliderBannerIndicator)
    PagerIndicator sliderBannerIndicator;
    @BindView(R.id.title_info)
    TextView titleInfo;
    @BindView(R.id.title_box1)
    TextView titleBox1;
    @BindView(R.id.content_box1)
    TextView contentBox1;
    @BindView(R.id.point_reward)
    LinearLayout pointReward;
    @BindView(R.id.title_box2)
    TextView titleBox2;
    @BindView(R.id.content_box2)
    TextView contentBox2;
    @BindView(R.id.point_trip)
    LinearLayout pointTrip;
    @BindView(R.id.card1)
    LinearLayout card1;
    @BindView(R.id.title_box3)
    TextView titleBox3;
    @BindView(R.id.content_box3)
    TextView contentBox3;
    @BindView(R.id.insentif_car)
    LinearLayout insentifCar;
    @BindView(R.id.title_box4)
    TextView titleBox4;
    @BindView(R.id.content_box4)
    TextView contentBox4;
    @BindView(R.id.insentif_mcy)
    LinearLayout insentifMcy;
    @BindView(R.id.card2)
    LinearLayout card2;
    @BindView(R.id.title_info_jaringan)
    TextView titleInfoJaringan;
    @BindView(R.id.top_jaringan)
    RelativeLayout topJaringan;
    @BindView(R.id.title_box5)
    TextView titleBox5;
    @BindView(R.id.content_box5)
    TextView contentBox5;
    @BindView(R.id.button_rb)
    LinearLayout buttonRb;
    @BindView(R.id.title_box6)
    TextView titleBox6;
    @BindView(R.id.content_box6)
    TextView contentBox6;
    @BindView(R.id.button_kedalaman_rb)
    LinearLayout buttonKedalamanRb;
//    @BindView(R.id.title_ppob)
//    TextView titlePpob;
//    @BindView(R.id.desc_ppob)
//    TextView descPpob;
//    @BindView(R.id.icon_history)
//    ImageView iconHistory;
//    @BindView(R.id.see_all_history)
//    TextView seeAllHistory;
//    @BindView(R.id.see_history)
//    RelativeLayout seeHistory;
//    @BindView(R.id.top_pengajuan)
//    RelativeLayout topPengajuan;
//    @BindView(R.id.recycler_ppob)
//    RecyclerView recyclerPpob;
    @BindView(R.id.title_replika)
    TextView titleReplika;
    @BindView(R.id.total_view)
    TextView totalView;
    @BindView(R.id.icon1_web)
    ImageView icon1Web;
    @BindView(R.id.link_web)
    TextView linkWeb;
    @BindView(R.id.copy_link)
    ImageView copyLink;
    @BindView(R.id.icon2_web)
    ImageView icon2Web;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.nav_view3)
    NavigationView navView3;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


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
        ButterKnife.bind(this);
        mDemoSlider = findViewById(R.id.slider);
        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();
        session.checkLogin();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        /* Update to Microservices - Data */
        ppobList = new ArrayList<>();
        adapter = new ListPPOBAdapter(ppobList, this);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        titleInfo.setTypeface(opensans_bold);
//        titlePpob.setTypeface(opensans_bold);
        titleInfoJaringan.setTypeface(opensans_bold);
        titleReplika.setTypeface(opensans_bold);
        totalView.setTypeface(opensans_bold);
        titleBox1.setTypeface(opensans_bold);
        contentBox1.setTypeface(opensans_reguler);
        titleBox2.setTypeface(opensans_bold);
        contentBox2.setTypeface(opensans_reguler);
        titleBox3.setTypeface(opensans_bold);
        contentBox3.setTypeface(opensans_reguler);
        titleBox4.setTypeface(opensans_bold);
        contentBox4.setTypeface(opensans_reguler);
        titleBox5.setTypeface(opensans_bold);
        contentBox5.setTypeface(opensans_reguler);
        titleBox6.setTypeface(opensans_bold);
        contentBox6.setTypeface(opensans_reguler);

        doLoadData();
        swipeToRefresh.setColorSchemeResources(R.color.colorAccent);

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doLoadData();
                swipeToRefresh.setRefreshing(false);
            }
        });

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

        //KOMEN SEMENTARA PPOB
//        final RecyclerView recyclerView = findViewById(R.id.recycler_ppob);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
//        recyclerView.setLayoutManager(mLayoutManager);
////        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);


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

//        allpengajuan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), AllPengajuanAxiActivity.class);
//                startActivity(intent);
//            }
//        });



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
                switch (menuItem.getItemId()) {
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
                    case R.id.navbar_semua_produk:
                        Intent intent4 = new Intent(getBaseContext(), SearchActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.navbar_jaringan:
                        intent = new Intent(getBaseContext(), InfoJaringanActivity.class);
                        intent.putExtra("total_rb", contentBox5.getText().toString());
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

        CircleImageView profilePictures = navigationView.getHeaderView(0).findViewById(R.id.profile_picture_axi);
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
                List<Datum> slider = response.body().getData();
                Log.d("SLIDER AXI", slider.toString());
                for (int i = 0; i < slider.size(); i++) {
                    Log.d("slideraxi", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                    file_maps.put(slider.get(i).getUrl(), slider.get(i).getImage());
                }

                for (final Datum s : slider) {
                    Log.d("DASH::::", s.getImage());
                    DefaultSliderView sliderBannerItem = new DefaultSliderView(AxiDashboardActivity.this);
                    sliderBannerItem
                            .image(s.getImage())
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
//        mDemoSlider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Left_Bottom);
        mDemoSlider.setDuration(4000);
//        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
//        mDemoSlider.addOnPageChangeListener(this);
    }

    private void doLoadData() {

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<Point> call2 = apiService.getPoint(session.getUserId());
        call2.enqueue(new Callback<Point>() {
            @Override
            public void onResponse(Call<Point> call, Response<Point> response2) {
                Log.d("TAGTAGTAG", "status:" + response2.code());
                try {
                    if (response2.isSuccessful()) {
                        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
                        contentBox1.setText(formatter.format(Integer.parseInt(String.valueOf(response2.body().getData().get(0).getAttributes().getPointReward()))).replace(",", "."));
                    }
                } catch (Exception ex) {}
            }

            @Override
            public void onFailure(Call<Point> call, Throwable t) {
                Log.d("TAGTAGTAG", "data:" + t.getMessage());
            }
        });

        InterfacePengajuanAxi apiService2 =
                RetrofitClient.getClient().create(InterfacePengajuanAxi.class);

        Call<PengajuanAxi> call3 = apiService2.getPengajuanAxi(apiKey, currentPage);
        call3.enqueue(new Callback<PengajuanAxi>() {
            @Override
            public void onResponse(Call<PengajuanAxi> call, Response<PengajuanAxi> response) {
                if(response.isSuccessful()) {
                    pengajuan = response.body().getData();
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                    contentBox6.setText(formatter.format(Integer.parseInt(String.valueOf(pengajuan.size()))).replace(",", "."));
                }
            }

            @Override
            public void onFailure(Call<PengajuanAxi> call, Throwable t) {

            }
        });

        InterfaceAxiDetail apiService5 =
                RetrofitClient.getClient().create(InterfaceAxiDetail.class);

//        showLoading();
        Call<AXIDetail> callProfile = apiService5.getDetail(apiKey);
        callProfile.enqueue(new Callback<AXIDetail>() {
            @Override
            public void onResponse(Call<AXIDetail> call, Response<AXIDetail> response) {
                if (response.isSuccessful()) {
                    Log.e("AAAA::::", "AXI Profile loaded");
                    itemDetail = response.body().getData();

                    Locale localeID = new Locale("in", "ID");
                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

//                    contentBox1.setText(formatter.format(Integer.parseInt(String.valueOf(itemDetail.getPointReward()))).replace(",", "."));
                    contentBox2.setText(formatter.format(Integer.parseInt(String.valueOf(itemDetail.getPointTrip()))).replace(",", "."));
                    contentBox3.setText(formatRupiah.format((float) Float.parseFloat(itemDetail.getIncentiveCar())));
                    contentBox4.setText(formatRupiah.format((float) Float.parseFloat(itemDetail.getIncentiveMcy())));
                    linkWeb.setText(itemDetail.getReplicaWebLink());
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
                if (response.isSuccessful()) {
                    infoJaringan = response.body().getData();
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                    contentBox5.setText(formatter.format(Integer.parseInt(String.valueOf(infoJaringan.size()))).replace(",", "."));

                }

            }

            @Override
            public void onFailure(Call<InfoJaringan> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AxiDashboardActivity.this);
                alertDialog.setMessage(t.getMessage());
                alertDialog.show();
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

//    private void doLoadData() {
//        showLoading();
//
//        InterfacePengajuanAxi apiService =
//                RetrofitClient.getClient().create(InterfacePengajuanAxi.class);
//
//        Call<PengajuanAxi> call2 = apiService.getPengajuanAxi(apiKey, currentPage);
//        call2.enqueue(new Callback<PengajuanAxi>() {
//            @Override
//            public void onResponse(Call<PengajuanAxi> call, Response<PengajuanAxi> response) {
//                if(response.isSuccessful()) {
//                    pengajuan = response.body().getData();
//                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
//
//                    contentBox6.setText(formatter.format(Integer.parseInt(String.valueOf(pengajuan.size()))).replace(",", "."));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PengajuanAxi> call, Throwable t) {
//
//            }
//        });
//
//        call2.enqueue(new Callback<PengajuanAxi>() {
//            @Override
//            public void onResponse(Call<PengajuanAxi> call, ObjekModel<PengajuanAxi> response) {
//                if(response.isSuccessful()) {
//                    pengajuan = response.body().getData();
//                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
//
//                    content_box6.setText(formatter.format(Integer.parseInt(String.valueOf(pengajuan.size()))).replace(",","."));
//
////                    recyclerView.setAdapter(new PengajuanAxiAdapter(pengajuan, R.layout.card_pengajuan, getBaseContext(), 3));
////                    recyclerView.setNestedScrollingEnabled(false);
////                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
////                        @Override
////                        public void onClick(View view, final int position) {
////                            Intent intent = new Intent(getBaseContext(), DetailRequestActivity.class);
////                            intent.putExtra("EXTRA_REQUEST_ID", pengajuan.get(position).getId().toString());
////                            startActivity(intent);
////
////                        }
////
////                        @Override
////                        public void onLongClick(View view, int position) {
////                        }
////                    }));
//                }
//                progress.dismiss();
//                hideLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PengajuanAxi> call, Throwable t) {
//                hideLoading();
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

    @OnClick(R.id.link_web)
    public void onViewClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkWeb.getText().toString()));
        startActivity(browserIntent);
    }

    @OnClick({R.id.icon1_web, R.id.copy_link, R.id.icon2_web, R.id.insentif_car, R.id.insentif_mcy, R.id.point_reward, R.id.point_trip, R.id.button_rb, R.id.button_kedalaman_rb})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.icon1_web:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkWeb.getText().toString()));
                startActivity(browserIntent);
                break;
            case R.id.copy_link:
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("link", linkWeb.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(), "Berhasil menyalin link web replika", Toast.LENGTH_SHORT).show();
                break;
            case R.id.icon2_web:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Temukan solusi kebutuhan Anda disini " + linkWeb.getText().toString());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Link web replika");
                startActivity(Intent.createChooser(intent, "Bagikan link web replika Anda"));
                break;
            case R.id.point_reward:
//                intent = new Intent(getBaseContext(), BusinesRewardActivity.class);
//                intent.putExtra("POINT_REWARD", contentBox1.getText());
//                startActivity(intent);

                intent = new Intent(getBaseContext(), AvailableBRActivity.class);
                startActivity(intent);
                break;
            case R.id.point_trip:
                intent = new Intent(getBaseContext(), PointTripActivity.class);
                intent.putExtra("POINT_TRIP", itemDetail.getPointTrip().toString());
                startActivity(intent);
                break;
            case R.id.insentif_car:
                intent = new Intent(getBaseContext(), InsentifCarActivity.class);
                intent.putExtra("MENTOR", itemDetail.getIncentiveCarMentor().toString());
                intent.putExtra("EXTRA_BULANAN", itemDetail.getIncentiveCarExtraBulanan().toString());
                intent.putExtra("GROUP", itemDetail.getIncentiveCarGroup().toString());
                intent.putExtra("BONUS_TAHUNAN", itemDetail.getIncentiveCarBonusTahunan().toString());
                intent.putExtra("BONUS_LAYOUT", itemDetail.getIncentiveCarBonusLayout().toString());
                startActivity(intent);
                break;
            case R.id.insentif_mcy:
                intent = new Intent(getBaseContext(), InsentifMcyActivity.class);
                intent.putExtra("MENTOR", itemDetail.getIncentiveMcyMentor().toString());
                intent.putExtra("EXTRA_BULANAN", itemDetail.getIncentiveMcyExtraBulanan().toString());
                intent.putExtra("GROUP", itemDetail.getIncentiveMcyGroup().toString());
                intent.putExtra("BONUS_TAHUNAN", itemDetail.getIncentiveMcyBonusTahunan().toString());
                intent.putExtra("BONUS_LAYOUT", itemDetail.getIncentiveMcyBonusLayout().toString());
                startActivity(intent);
                break;
            case R.id.card2:
                break;
            case R.id.button_rb:
                intent = new Intent(getBaseContext(), InfoJaringanActivity.class);
                intent.putExtra("total_rb", contentBox5.getText().toString());
                startActivity(intent);
                break;
            case R.id.button_kedalaman_rb:
                intent = new Intent(getBaseContext(), AllPengajuanAxiActivity.class);
                startActivity(intent);
                break;
//            case R.id.footer_item_1:
//                intent = new Intent(getBaseContext(), MarketplaceActivity.class);
//                startActivity(intent);
//                break;
        }
    }

    ;
}