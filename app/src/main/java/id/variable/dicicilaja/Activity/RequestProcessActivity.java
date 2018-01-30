package id.variable.dicicilaja.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import id.variable.dicicilaja.Adapter.RequestProcessCRH2Adapter;
import id.variable.dicicilaja.Adapter.RequestProcessCRHAdapter;
import id.variable.dicicilaja.Adapter.RequestProcessCROAdapter;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;

public class RequestProcessActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_process);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        String apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        TabLayout tabLayout = findViewById(R.id.tab_process);
        tabLayout.addTab(tabLayout.newTab().setText("RANGKUMAN"));
        tabLayout.addTab(tabLayout.newTab().setText("TUGAS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent2));

        viewPager = findViewById(R.id.pager);

        if(session.getRole().equals("crh")){
            if(getIntent().getStringExtra("STATUS_SURVEY").equals(1)){
                viewPager.setAdapter(new RequestProcessCRH2Adapter(getSupportFragmentManager(), 2));
            }else{
                viewPager.setAdapter(new RequestProcessCRHAdapter(getSupportFragmentManager(), 2));
            }

        }else if(session.getRole().equals("cro")){
            viewPager.setAdapter(new RequestProcessCROAdapter(getSupportFragmentManager(), 2));
        }


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
    }
}
