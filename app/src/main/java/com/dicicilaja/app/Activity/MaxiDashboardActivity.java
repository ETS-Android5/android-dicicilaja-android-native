package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.dicicilaja.app.BranchOffice.UI.AreaBranchOffice.Activity.AreaBranchOfficeActivity;
import com.dicicilaja.app.NewSimulation.UI.NewSimulation.NewSimulationActivity;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceMitraSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.AxiSlider;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiSlider.Datum;
import com.dicicilaja.app.Model.RequestMeta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import com.dicicilaja.app.API.Interface.InterfacePengajuanMaxi;
import com.dicicilaja.app.API.Model.PengajuanMaxi.PengajuanMaxi;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProgramMaxi;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProgramMaxi.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProgramMaxi.ProgramMaxi;
import com.dicicilaja.app.Adapter.PengajuanMaxiAdapter;
import com.dicicilaja.app.Adapter.ProgramMaxiAdapter;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaxiDashboardActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SessionManager session;
    List<com.dicicilaja.app.API.Model.PengajuanMaxi.Datum> pengajuan;
    List<Data> programMaxi;
    String token;
    SliderLayout mDemoSlider;
    TextView title_info, title_pengumuman, title_replika, total_view, title_status, title_program;
    TextView title_box1, content_box1, title_box2, content_box2, title_box3, content_box3, title_box4, content_box4, title_box5, content_box5, title_box6, content_box6;

    String apiKey;
    LinearLayout insentif_car, insentif_mcy, jumlah_program, total_pengajuan, button_rb, button_kedalaman_rb;

    RelativeLayout allpengajuan, allprogram;
    HashMap<String, String> file_maps;

    ProgressDialog progress;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    ProgramMaxiAdapter adapterProgram;
    PengajuanMaxiAdapter adapterRequest;

    int totalData = 1;
    int totalPage = 1;
    int currentPage = 1;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maxi_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        allprogram = findViewById(R.id.allprogram);
        allpengajuan = findViewById(R.id.allpengajuan);
        jumlah_program = findViewById(R.id.jumlah_program);
        total_pengajuan = findViewById(R.id.total_pengajuan);
        title_status = findViewById(R.id.title_status);
        title_box1 = findViewById(R.id.title_box1);
        content_box1 = findViewById(R.id.content_box1);
        title_box2 = findViewById(R.id.title_box2);
        content_box2 = findViewById(R.id.content_box2);
        button_kedalaman_rb = findViewById(R.id.button_kedalaman_rb);
        button_rb = findViewById(R.id.button_rb);
        title_info = findViewById(R.id.title_info);
        title_program = findViewById(R.id.title_program);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_status.setTypeface(opensans_bold);
        title_box1.setTypeface(opensans_bold);
        content_box1.setTypeface(opensans_reguler);
        title_box2.setTypeface(opensans_bold);
        content_box2.setTypeface(opensans_reguler);
        title_info.setTypeface(opensans_bold);
        title_program.setTypeface(opensans_bold);

        jumlah_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),AllProgramMaxiActivity.class);
                startActivity(intent);
            }
        });

        total_pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),AllPengajuanMaxiActivity.class);
                startActivity(intent);
            }
        });

        allpengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),AllPengajuanMaxiActivity.class);
                startActivity(intent);
            }
        });

        allprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),AllProgramMaxiActivity.class);
                startActivity(intent);
            }
        });

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        InterfaceProgramMaxi apiService3 =
                RetrofitClient.getClient().create(InterfaceProgramMaxi.class);

        recyclerView2 =  findViewById(R.id.recycler_program);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        adapterProgram = new ProgramMaxiAdapter(programMaxi, R.layout.card_program, getBaseContext());
        recyclerView2.setAdapter(adapterProgram);

        Call<ProgramMaxi> call5 = apiService3.getProgramMaxi(apiKey);
        call5.enqueue(new Callback<ProgramMaxi>() {
            @Override
            public void onResponse(Call<ProgramMaxi> call, Response<ProgramMaxi> response) {
                programMaxi = response.body().getData();

                recyclerView2.setAdapter(new ProgramMaxiAdapter(programMaxi, R.layout.card_program, getBaseContext()));
                recyclerView2.setNestedScrollingEnabled(false);
                recyclerView2.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView2, new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Intent intent = new Intent(getBaseContext(), ProductMaxiActivity.class);
                        intent.putExtra("EXTRA_REQUEST_ID", programMaxi.get(position).getId().toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ProgramMaxi> call, Throwable t) {
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

        recyclerView =  findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        adapterRequest = new PengajuanMaxiAdapter(pengajuan, R.layout.card_pengajuan, getBaseContext());
        recyclerView.setAdapter(adapterRequest);

        pengajuan = new ArrayList<>();

        // Load Data
        doLoadData();

        // Init Listener
        initListener();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.navbar_dashboard);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                switch( menuItem.getItemId() ) {
                    case R.id.navbar_dashboard:
                        break;

                    case R.id.navbar_program:
                        Intent intent1 = new Intent(getBaseContext(),AllProgramMaxiActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.navbar_simulation:
                        intent = new Intent(getBaseContext(), NewSimulationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_status:
                        Intent intent2 = new Intent(getBaseContext(),AllPengajuanMaxiActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.branch_office:
                        Intent intent3 = new Intent(getBaseContext(), AreaBranchOfficeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.navbar_exit:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MaxiDashboardActivity.this);

                        // Setting Dialog Title
                        alertDialog.setTitle("Konfirmasi");

                        // Setting Dialog Message
                        alertDialog.setMessage("Apakah Anda yakin ingin keluar?");


                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                session.logoutUser();
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
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

        CircleImageView profilePictures =  navigationView.getHeaderView(0).findViewById(R.id.profile_picture_user);
        View navbarView = navigationView.getHeaderView(0);
        LinearLayout open_profile = navbarView.findViewById(R.id.open_profile);
        ImageView profile_pictures = navbarView.findViewById(R.id.imageView);
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
                Intent intent = new Intent(getBaseContext(), ProfileMaxiActivity.class);
                startActivity(intent);
            }
        });

        file_maps = new HashMap<String, String>();

//        file_maps.put("https://dicicilaja.com/gudang-info/Saatnya-Jadi-AXI%21-Dan-Jadilah-Pahlawan-Bagi-Keluarga","https://dicicilaja.com/uploads/news/1510289120-saatnya-jadi-axi-dan-jadilah-pahlawan-bagi-keluarga.jpg");
//        file_maps.put("","https://dicicilaja.com/uploads/banner/0persen.jpg");
        InterfaceMitraSlider apiSlider =
                RetrofitClient.getClient().create(InterfaceMitraSlider.class);

        Call<AxiSlider> call = apiSlider.getSlider();
        call.enqueue(new Callback<AxiSlider>() {
            @Override
            public void onResponse(Call<AxiSlider> call, Response<AxiSlider> response) {
                List <Datum> slider = response.body().getData();
                for (int i = 0; i < slider.size(); i++) {
                    Log.d("slideraxi", slider.get(i).getUrl() + " " + slider.get(i).getImage());
                    file_maps.put(slider.get(i).getUrl(), slider.get(i).getImage());
                }

                /*for(final String name1 : file_maps.keySet()) {
                    final DefaultSliderView sliderView = new DefaultSliderView(getBaseContext());
                    // initialize a SliderLayout
                    sliderView
                            .image(file_maps.get(name1))
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop);
                    sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(name1.toString()));
                            startActivity(browserIntent);
                        }
                    });
                    mDemoSlider.addSlider(sliderView);
                }*/

                for(final Datum s: slider) {
                    Log.d("DASH::::", s.getImage());
                    DefaultSliderView sliderBannerItem = new DefaultSliderView(MaxiDashboardActivity.this);
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
            }
        });
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        //mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Left_Bottom);
        mDemoSlider.setDuration(4000);
//        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        //mDemoSlider.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maxi_dashboard, menu);
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

    private void doLoadData() {
        showLoading();

        InterfacePengajuanMaxi apiService =
                RetrofitClient.getClient().create(InterfacePengajuanMaxi.class);

        Call<PengajuanMaxi> call2 = apiService.getPengajuanMaxi(apiKey, currentPage);
        call2.enqueue(new Callback<PengajuanMaxi>() {
            @Override
            public void onResponse(Call<PengajuanMaxi> call, Response<PengajuanMaxi> response) {
                if(response.isSuccessful()){

                    List<com.dicicilaja.app.API.Model.PengajuanMaxi.Datum> items = response.body().getData();
                    RequestMeta meta = response.body().getMeta();

                    totalPage = meta.getLastPage();
                    totalData = meta.getTotal();
                    currentPage = meta.getCurrentPage();

                    if( currentPage == 1 ) {
                        pengajuan.clear();
                        pengajuan.addAll(items);

                        adapterRequest.notifyDataSetChanged();

                    } else {
                        adapterRequest.refreshAdapter(items);
                    }

                    /*pengajuan = response.body().getData();

                    recyclerView.setAdapter(new PengajuanMaxiAdapter(pengajuan, R.layout.card_pengajuan, getBaseContext()));
                    recyclerView.setNestedScrollingEnabled(false);*/
                }else{
                    session.logoutUser();
                }

                hideLoading();
            }

            @Override
            public void onFailure(Call<PengajuanMaxi> call, Throwable t) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MaxiDashboardActivity.this);
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                hideLoading();
                alertDialog.show();
            }
        });
    }

    private void initListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int countItem = layoutManager.getItemCount();

                /*int visiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if( visiblePosition >= 3 ) {
                    fabScrollTop.setVisibility(View.VISIBLE);
                } else {
                    fabScrollTop.setVisibility(View.GONE);
                }*/

                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                boolean isLastPosition = countItem - 1 == lastVisiblePosition;

                if( !isLoading && isLastPosition && currentPage < totalPage ) {
                    currentPage = currentPage + 1;
                    doLoadData();
                }
            }
        });
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
