package id.variable.dicicilaja.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import id.variable.dicicilaja.Adapter.EmployeeDashboardPagerAdapter;
import id.variable.dicicilaja.Fragment.AxiAjukanFragment;
import id.variable.dicicilaja.Fragment.AxiAkunFragment;
import id.variable.dicicilaja.Fragment.AxiHomeFragment;
import id.variable.dicicilaja.Fragment.AxiJaringanFragment;
import id.variable.dicicilaja.Fragment.AxiPengajuanFragment;
import id.variable.dicicilaja.Fragment.HomeFragment;
import id.variable.dicicilaja.Fragment.ProfileFragment;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import id.variable.dicicilaja.WebView.CekStatusActivity;
import id.variable.dicicilaja.WebView.CreateRequestActivity;
import id.variable.dicicilaja.WebView.SimulationActivity;

public class AxiDashboardActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SessionManager session;
    String token;
    SliderLayout mDemoSlider;

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
//                case R.id.nav_ajukan:
//                    AxiAjukanFragment axiAjukanFragment = new AxiAjukanFragment();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiAjukanFragment)
//                            .addToBackStack(null)
//                            .commit();
//                    return true;
//                case R.id.nav_jaringan:
//                    AxiJaringanFragment axiJaringanFragment = new AxiJaringanFragment();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiJaringanFragment)
//                            .addToBackStack(null)
//                            .commit();
//                    return true;
//                case R.id.nav_akun:
//                    AxiAkunFragment axiAkunFragment = new AxiAkunFragment();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, axiAkunFragment)
//                            .addToBackStack(null)
//                            .commit();
//                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axi_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        String token = session.getToken();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);




        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.navbar_request);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                switch( menuItem.getItemId() ) {
                    case R.id.navbar_request:
                        break;
                    case R.id.navbar_create_request:
                        intent = new Intent(getBaseContext(), CreateRequestActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_cek:
                        intent = new Intent(getBaseContext(), CekStatusActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_simulation:
                        intent = new Intent(getBaseContext(), SimulationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_statistics:
                        break;
                    case R.id.navbar_profile:
                        intent = new Intent(getBaseContext(), ProfileActivity.class);
                        startActivity(intent);
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

        session = new SessionManager(getApplicationContext());

        String imageUrl = session.getPhoto().toString();
        Picasso.with(getApplicationContext()).load(imageUrl).into(profilePictures);

        name.setText(session.getName());

        open_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        final HashMap<Integer, String> file_maps = new HashMap<Integer, String>();
        file_maps.put(1,"https://dicicilaja.com/uploads/banner/1523528108Homebanner%20Tasya.jpg");
        file_maps.put(2,"https://dicicilaja.com/uploads/banner/banner-dana-tunai.jpg");


        for(Integer name1 : file_maps.keySet()){
            final DefaultSliderView sliderView = new DefaultSliderView(getBaseContext());
            // initialize a SliderLayout
            sliderView
                    .image(file_maps.get(name1))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderView.setOnSliderClickListener(this);
            sliderView.bundle(new Bundle());
            sliderView.getBundle()
                    .putString("extra",name1.toString());
            mDemoSlider.addSlider(sliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Left_Bottom);
        mDemoSlider.setDuration(4000);
        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        mDemoSlider.addOnPageChangeListener(this);
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
