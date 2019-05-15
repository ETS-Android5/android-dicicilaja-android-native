package com.dicicilaja.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dicicilaja.app.WebView.CekStatusActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import com.dicicilaja.app.Adapter.TCHomePagerAdapter;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

public class EmployeeDashboardActivity extends AppCompatActivity {

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        TabLayout tabLayout = findViewById(R.id.tab_tc);
        tabLayout.addTab(tabLayout.newTab().setText("PENGAJUAN MASUK"));
        tabLayout.addTab(tabLayout.newTab().setText("STATUS APLIKASI"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent2));

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
                        intent = new Intent(getBaseContext(), AjukanPengajuanAxiActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_cek:
                        intent = new Intent(getBaseContext(), CekStatusActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_simulation:
                        intent = new Intent(getBaseContext(), com.dicicilaja.app.Activity.SimulationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_statistics:
                        intent = new Intent(getBaseContext(), StatisticActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_exit:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(EmployeeDashboardActivity.this);
                        alertDialog.setTitle("Konfirmasi");
                        alertDialog.setMessage("Apakah Anda yakin ingin keluar?");
                        alertDialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                session.logoutUser();
                            }
                        });
                        alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();
                        break;
                }
                drawer.closeDrawers();
                return true;

            }

        });
        CircleImageView profilePictures =  navigationView.getHeaderView(0).findViewById(R.id.profile_picture_user);
        TextView branch = navigationView.getHeaderView(0).findViewById(R.id.branch);
        TextView area = navigationView.getHeaderView(0).findViewById(R.id.area);
        View navbarView = navigationView.getHeaderView(0);
        LinearLayout open_profile = navbarView.findViewById(R.id.open_profile);
        ImageView profile_pictures = navbarView.findViewById(R.id.imageView);
        TextView name = navbarView.findViewById(R.id.nameView);

        session = new SessionManager(getApplicationContext());

        area.setText(session.getArea());
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
                Intent intent = new Intent(getBaseContext(), ProfileEmployeeActivity.class);
                startActivity(intent);
            }
        });

        final ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new TCHomePagerAdapter(getSupportFragmentManager(), 2));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employee_dashboard, menu);
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

}
