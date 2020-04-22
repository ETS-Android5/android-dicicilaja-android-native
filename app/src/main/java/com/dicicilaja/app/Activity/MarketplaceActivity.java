package com.dicicilaja.app.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dicicilaja.app.API.Client.ApiClient;
import com.dicicilaja.app.API.Interface.InterfaceLogout;
import com.dicicilaja.app.BranchOffice.UI.AreaBranchOffice.Activity.AreaBranchOfficeActivity;
import com.dicicilaja.app.Inbox.Data.Popup.Datum;
import com.dicicilaja.app.Inbox.Data.Popup.Popup;
import com.dicicilaja.app.Inbox.UI.PopUpActivity;
import com.dicicilaja.app.Model.Logout;
import com.dicicilaja.app.Inbox.UI.InboxActivity;
import com.dicicilaja.app.NewSimulation.UI.NewSimulation.NewSimulationActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dicicilaja.app.Adapter.ViewPagerAdapter;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.WebView.AboutAxiMarketplaceActivity;
import com.dicicilaja.app.WebView.AboutDicicilajaActivity;
import com.dicicilaja.app.WebView.AboutMaxiMarketplaceActivity;
import com.dicicilaja.app.WebView.InfoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.FALSE;

public class MarketplaceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SessionManager session;
    String apiKey;
    private ViewPagerAdapter viewPagerAdapter;
    ProgressDialog progress, progress_popup;
    NavigationView navigationView;
    Dialog InAppDialog;
    com.dicicilaja.app.Inbox.Network.ApiService apiService4;
    List<Datum> dataPopups;
    ImageView thumbnail;
    TextView detail, nanti;
    Boolean openInbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        openInbox = getIntent().getBooleanExtra("openInbox", false);
        if(openInbox) {
            Intent intent = new Intent(getBaseContext(), InboxActivity.class);
            startActivityForResult(intent, 77);
        }

        try {
            session = new SessionManager(getApplicationContext());
            apiKey = "Bearer " + session.getToken();
        } catch (Exception err) {}
        Log.d("disini", "onCreate: " + session.getToken());
        if(session.getToken() == null) {
            navigationView = findViewById(R.id.nav_view) ;
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.navbar_keluar).setVisible(false);
        }else {
            navigationView = findViewById(R.id.nav_view) ;
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.navbar_keluar).setVisible(false);
        }

        apiService4 = com.dicicilaja.app.Inbox.Network.ApiClient.getClient().create(com.dicicilaja.app.Inbox.Network.ApiService.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        tabLayout = findViewById(R.id.tab_marketplace);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 4,getBaseContext()));
        session = new SessionManager(getApplicationContext());

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);

        final TabLayout.Tab beranda = tabLayout.newTab();
        final TabLayout.Tab pengajuan = tabLayout.newTab();
        final TabLayout.Tab bantuan = tabLayout.newTab();
        final TabLayout.Tab akun = tabLayout.newTab();

        beranda.setIcon(R.drawable.tab_home_active);
        beranda.setText("BERANDA");

        pengajuan.setIcon(R.drawable.tab_order);
        pengajuan.setText("PENGAJUAN");

        bantuan.setIcon(R.drawable.tab_help);
        bantuan.setText("BANTUAN");

        akun.setIcon(R.drawable.tab_account);
        akun.setText("AKUN SAYA");

        tabLayout.addTab(beranda, 0);
        tabLayout.addTab(pengajuan, 1);
        tabLayout.addTab(bantuan, 2);
        tabLayout.addTab(akun, 3);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        beranda.setIcon(R.drawable.tab_home_active);
                        pengajuan.setIcon(R.drawable.tab_order);
                        bantuan.setIcon(R.drawable.tab_help);
                        akun.setIcon(R.drawable.tab_account);
                        break;
                    case 1:
                        beranda.setIcon(R.drawable.tab_home);
                        pengajuan.setIcon(R.drawable.tab_order_active);
                        bantuan.setIcon(R.drawable.tab_help);
                        akun.setIcon(R.drawable.tab_account);
                        break;
                    case 2:
                        beranda.setIcon(R.drawable.tab_home);
                        pengajuan.setIcon(R.drawable.tab_order);
                        bantuan.setIcon(R.drawable.tab_help_active);
                        akun.setIcon(R.drawable.tab_account);
                        break;
                    case 3:
                        beranda.setIcon(R.drawable.tab_home);
                        pengajuan.setIcon(R.drawable.tab_order);
                        bantuan.setIcon(R.drawable.tab_help);
                        akun.setIcon(R.drawable.tab_account_active);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        tabLayout.addTab(tabLayout.newTab().setText("BERANDA"));
//        tabLayout.addTab(tabLayout.newTab().setText("PENGAJUAN"));
//        tabLayout.addTab(tabLayout.newTab().setText("BANTUAN"));
//        tabLayout.addTab(tabLayout.newTab().setText("AKUN SAYA"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
//
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//        tabLayout.getTabAt(3).setIcon(tabIcons[3]);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.navbar_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                switch( menuItem.getItemId() ) {
                    case R.id.navbar_home:
                        break;
                    case R.id.navbar_axi:
                        Intent intent1 = new Intent(getBaseContext(), AboutAxiMarketplaceActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.navbar_mitra:
                        Intent intent2 = new Intent(getBaseContext(), AboutMaxiMarketplaceActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.navbar_simulasi:
                        Intent intent3 = new Intent(getBaseContext(), NewSimulationActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.navbar_semua_produk:
                        Intent intent4 = new Intent(getBaseContext(), SearchActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.navbar_gudang_info:
                        Intent intent6 = new Intent(getBaseContext(), InfoActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.navbar_tentang:
                        Intent intent7 = new Intent(getBaseContext(), AboutDicicilajaActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.navbar_branch:
                        Intent intent8 = new Intent(getBaseContext(), AreaBranchOfficeActivity.class);
                        startActivity(intent8);
                        break;
                    case R.id.navbar_bantuan:
                        viewPager.setCurrentItem(2);
                        drawer.closeDrawers();
                        break;
                    case R.id.navbar_keluar:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MarketplaceActivity.this);

                        // Setting Dialog Title
                        alertDialog.setTitle("Konfirmasi");

                        // Setting Dialog Message
                        alertDialog.setMessage("Apakah Anda yakin ingin keluar?");


                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                progress.show();
                                InterfaceLogout apiService =
                                        ApiClient.getClient().create(InterfaceLogout.class);

                                Call<Logout> call2 = apiService.logout(apiKey);
                                call2.enqueue(new Callback<Logout>() {
                                    @Override
                                    public void onResponse(Call<Logout> call, Response<Logout> response2) {
                                        try {
                                            if (response2.isSuccessful()) {
                                                progress.dismiss();
                                                session.logoutUser();
                                            }
                                        } catch (Exception ex) {
                                            progress.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Logout> call, Throwable t) {
                                        progress.dismiss();
                                    }
                                });
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
        TextView nameView = navigationView.getHeaderView(0).findViewById(R.id.nameView);
        RelativeLayout logout = navigationView.getHeaderView(0).findViewById(R.id.logout);
        RelativeLayout login = navigationView.getHeaderView(0).findViewById(R.id.login);
        View navbarView = navigationView.getHeaderView(0);

        session = new SessionManager(getApplicationContext());
        logout.setVisibility(View.GONE);
        login.setVisibility(View.VISIBLE);

        try{
            if(getIntent().getStringExtra("profile").equals("profile")){
                viewPager.setCurrentItem(3);
                drawer.closeDrawers();
            }
        }catch (Exception ex){

        }

        if(session.isLoggedIn() == FALSE) {
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(3);
                    drawer.closeDrawers();
//                    Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
//                    startActivity(intent);
                }
            });
        }else{
            try {
                nameView.setText(session.getName());
                String imageUrl = session.getPhoto();
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.avatar)
                        .error(R.drawable.avatar)
                        .into(profilePictures);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(3);
                        drawer.closeDrawers();
//                    Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
//                    startActivity(intent);
                    }
                });
            }catch (Exception ex){


            }
        }

        inAppDialog();

//
//        viewPager = findViewById(R.id.pager);
//        viewPager.setAdapter(new MarketplacePagerAdapter(getSupportFragmentManager(), 4));
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        setupTabIcons();
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//                if(tab.getPosition() == 0) {
//                    tabLayout.getTabAt(tab.getPosition()).getCustomView().setSelected(true);
//                }else if(tab.getPosition() == 1) {
//                } else if(tab.getPosition() == 2) {
//                }else if(tab.getPosition() == 3) {
//
//                    Intent intent = new Intent(getBaseContext(), GuestActivity.class);
//                    startActivity(intent);
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new BerandaFragment(), "Beranda");
//        adapter.addFragment(new PengajuanFragment(), "Pengajuan");
//        adapter.addFragment(new BantuanFragment(), "Bantuan");
//        adapter.addFragment(new AkunFragment(), "Akun Saya");
//        viewPager.setAdapter(adapter);
//
//    }

//    class ViewPagerAdapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public ViewPagerAdapter(FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }

//    private void setupTabIcons() {
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("Beranda");
//        tabOne.setAllCaps(true);
//        tabOne.setTextSize(12);
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_home, 0, 0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
//
//        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabTwo.setText("Pengajuan");
//        tabTwo.setAllCaps(true);
//        tabTwo.setTextSize(12);
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_order, 0, 0);
//        tabLayout.getTabAt(1).setCustomView(tabTwo);
//
//        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabThree.setText("Bantuan");
//        tabThree.setAllCaps(true);
//        tabThree.setTextSize(12);
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_help, 0, 0);
//        tabLayout.getTabAt(2).setCustomView(tabThree);
//
//        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabFour.setText("Akun Saya");
//        tabFour.setAllCaps(true);
//        tabFour.setTextSize(12);
//        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_account, 0, 0);
//        tabLayout.getTabAt(3).setCustomView(tabFour);
//
////        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
////        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
////        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
////        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
//    }
//
//    public void setCustomFont() {
//
//        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
//        int tabsCount = vg.getChildCount();
//
//        for (int j = 0; j < tabsCount; j++) {
//            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
//
//            int tabChildsCount = vgTab.getChildCount();
//
//            for (int i = 0; i < tabChildsCount; i++) {
//                View tabViewChild = vgTab.getChildAt(i);
//                if (tabViewChild instanceof TextView) {
//                    //Put your font in assests folder
//                    //assign name of the font here (Must be case sensitive)
//                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf"));
//                    ((TextView) tabViewChild).setTextSize(55);
//                }
//            }
//        }
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            new AlertDialog.Builder(this)
                    .setTitle("Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
//                            MarketplaceActivity.super.onBackPressed();
                            finishAffinity();
                        }
                    }).create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.marketplace, menu);
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
            if(session.isLoggedIn() == FALSE){
                Intent intent = new Intent(getBaseContext(), GuestActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getBaseContext(), InboxActivity.class);
                startActivity(intent);
            }

            return true;
        }
//        else if (id == R.id.search) {
//            Intent intent = new Intent(getBaseContext(), SearchActivity.class);
//            startActivity(intent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void inAppDialog() {
        progress_popup = new ProgressDialog(this);
        progress_popup.setMessage("Sedang memuat data...");
        progress_popup.setCanceledOnTouchOutside(false);

        InAppDialog = new Dialog(MarketplaceActivity.this);
        InAppDialog.setContentView(R.layout.in_app_dialog);
        InAppDialog.setCanceledOnTouchOutside(false);
        InAppDialog.setCancelable(false);
        InAppDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        thumbnail = InAppDialog.findViewById(R.id.thumbnail);
        detail = InAppDialog.findViewById(R.id.detail);
        nanti = InAppDialog.findViewById(R.id.nanti);

        nanti.setVisibility(View.GONE);
        detail.setVisibility(View.GONE);
        detail.setEnabled(false);
        nanti.setEnabled(false);

        progress_popup.show();
        Call<Popup> popupCall = apiService4.getPopup(apiKey, session.getRole());
        popupCall.enqueue(new Callback<Popup>() {
            @Override
            public void onResponse(Call<Popup> call, Response<Popup> response) {
                progress_popup.dismiss();
                if (response.isSuccessful()) {
                    dataPopups = response.body().getData();
                    if (dataPopups.size() != 0) {
                        try {
                            Glide.with(MarketplaceActivity.this)
                                    .load(dataPopups.get(0).getAttributes().getImage())
                                    .fitCenter()
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            nanti.setVisibility(View.VISIBLE);
                                            detail.setVisibility(View.VISIBLE);
                                            detail.setEnabled(true);
                                            nanti.setEnabled(true);
                                            return false;
                                        }
                                    })
                                    .into(thumbnail);


                            detail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    InAppDialog.cancel();
                                    Intent intent = new Intent(getBaseContext(), PopUpActivity.class);
                                    intent.putExtra("url", dataPopups.get(0).getAttributes().getUrl());
                                    startActivity(intent);
                                }
                            });

                            nanti.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    InAppDialog.cancel();
                                }
                            });

//                            close.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    InAppDialog.cancel();
//                                }
//                            });

                            InAppDialog.show();
                        } catch (Exception ex) {
                        }
                    }
                    progress_popup.dismiss();
                } else {
                    progress_popup.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Popup> call, Throwable t) {
                progress_popup.dismiss();
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (progress_popup != null && progress_popup.isShowing()) {
            progress_popup.dismiss();
        }
    }
}
