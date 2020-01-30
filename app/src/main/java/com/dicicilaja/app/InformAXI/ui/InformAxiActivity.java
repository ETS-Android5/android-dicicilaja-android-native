package com.dicicilaja.app.InformAXI.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dicicilaja.app.InformAXI.OrderTrackingActivity;
import com.dicicilaja.app.InformAXI.ui.gathering.GatheringActivity;
import com.dicicilaja.app.InformAXI.ui.home.HomeFragment;
import com.dicicilaja.app.InformAXI.ui.register.RegisterActivity;
import com.dicicilaja.app.InformAXI.ui.trip.TripActivity;
import com.dicicilaja.app.R;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

public class InformAxiActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private View navHeader;
    private TextView tvName;
    private TextView tvLookProfile;
    private CircularImageView ivAvatar;
    private ImageButton ibMenu, ibNotification;

    public static int navItemIndex = 0;
    public static String CURRENT_TAG = "home";

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_axi);

        initVariables();
        initToolbar();
        initListener();
//        loadHomeFragment();
    }

    private void initVariables() {
        toolbar = findViewById(R.id.toolbar);
        ibMenu = findViewById(R.id.ib_menu);
        navView = findViewById(R.id.nav_view);
        navHeader = navView.getHeaderView(0);
        drawerLayout = findViewById(R.id.drawer_layout);
        tvName = navHeader.findViewById(R.id.tv_name);
        tvLookProfile = navHeader.findViewById(R.id.tv_look_profile);
        ivAvatar = navHeader.findViewById(R.id.iv_avatar);

        mHandler = new Handler();

        loadNavHeader();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void initListener() {
        ibMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        navView.setNavigationItemSelectedListener(this);

        tvLookProfile.setOnClickListener(view -> {
            // intent to profile detail
        });
    }

    private void loadNavHeader() {
        tvName.setText(getString(R.string.app_name));

        Glide.with(this).load(getString(R.string.dummy_profile))
                .placeholder(R.drawable.ic_profile_account)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivAvatar);
    }

//    private void loadHomeFragment() {
//        // select appropriate nav menu item
//        selectNavMenu();
//
//        // if user select the current navigation, don't do anything
//        // just close the drawer
//        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
//            drawerLayout.closeDrawers();
//            return;
//        }
//
//        // Sometimes, when fragment has huge data, screen seems hanging
//        // when switching between navigation menus
//        // So using runnable, the fragment is loaded with cross fade effect
//        // This effect can be seen in GMail app
//        Runnable mPendingRunnable = () -> {
//            Fragment fragment = getFragment();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//            transaction.replace(R.id.frame_layout, fragment, CURRENT_TAG);
//            transaction.commitAllowingStateLoss();
//        };
//
//        mHandler.post(mPendingRunnable);
//
//        drawerLayout.closeDrawers();
//        invalidateOptionsMenu();
//    }

    private void selectNavMenu() {
        navView.getMenu().getItem(navItemIndex).setChecked(true);
    }

//    private Fragment getFragment() {
//        switch (CURRENT_TAG) {
//            case "home":
//                return HomeFragment.newInstance("home");
//            case "register":
//                return HomeFragment.newInstance("register");
//            case "trip":
//                return TripFragment.newInstance();
//            default:
//                return HomeFragment.newInstance("home");
//        }
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                navItemIndex = 0;
                CURRENT_TAG = getString(R.string.home_tag);
                break;
            case R.id.nav_regist:
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, RegisterActivity.class));
                navItemIndex = 1;
                //CURRENT_TAG = getString(R.string.regist_tag);
                break;
            case R.id.nav_trip:
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, TripActivity.class));
                navItemIndex = 2;
                //CURRENT_TAG = getString(R.string.trip_tag);
                break;
            case R.id.nav_gathering:
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, GatheringActivity.class));
                navItemIndex = 3;
                //CURRENT_TAG = getString(R.string.gathering_tag);
                break;
            case R.id.nav_tracking:
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, OrderTrackingActivity.class));
            default:
                navItemIndex = 4;
                //CURRENT_TAG = getString(R.string.home_tag);
                break;
        }

        //if (menuItem.isChecked()) {
        //    menuItem.setChecked(false);
        //} else {
        //    menuItem.setChecked(true);
        //}

        //menuItem.setChecked(true);
        //loadHomeFragment();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        if (navItemIndex != 0) {
            navItemIndex = 0;
            CURRENT_TAG = getString(R.string.home_tag);
//            loadHomeFragment();
            selectNavMenu();
            return;
        }


        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (navItemIndex != 0) {
            navItemIndex = 0;
            CURRENT_TAG = getString(R.string.home_tag);
//            loadHomeFragment();
            selectNavMenu();
            return;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
