package com.dicicilaja.dicicilaja.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import com.dicicilaja.dicicilaja.Fragment.AxiAjukanFragment;
import com.dicicilaja.dicicilaja.Fragment.AxiAkunFragment;
import com.dicicilaja.dicicilaja.Fragment.AxiHomeFragment;
import com.dicicilaja.dicicilaja.Fragment.AxiJaringanFragment;
import com.dicicilaja.dicicilaja.Fragment.AxiPengajuanFragment;
import com.dicicilaja.dicicilaja.R;

public class AxiDashboardActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    AxiHomeFragment axiHomeFragment = new AxiHomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiHomeFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.nav_pengajuan:
                    AxiPengajuanFragment axiPengajuanFragment = new AxiPengajuanFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiPengajuanFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.nav_ajukan:
                    AxiAjukanFragment axiAjukanFragment = new AxiAjukanFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiAjukanFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.nav_jaringan:
                    AxiJaringanFragment axiJaringanFragment = new AxiJaringanFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiJaringanFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.nav_akun:
                    AxiAkunFragment axiAkunFragment = new AxiAkunFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiAkunFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axi_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Intent intent;

                switch( id ) {
//                    case R.id.navbar_home:
//                        intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
//                        startActivity(intent);
//                        break;
//
//                    case R.id.navbar_pref_menu:
//                        intent = new Intent(getBaseContext(), HomeActivity.class);
//                        startActivity(intent);
//                        break;
                }

                return true;
            }
        });

        AxiHomeFragment axiHomeFragment = new AxiHomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiHomeFragment)
                .addToBackStack(null)
                .commit();

        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);

        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        navigation.setTypeface(opensans_bold);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
