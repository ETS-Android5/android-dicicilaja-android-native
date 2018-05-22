package com.dicicilaja.dicicilaja.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dicicilaja.dicicilaja.Adapter.ViewPagerAdapter;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;

public class MarketplaceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SessionManager session;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        tabLayout = findViewById(R.id.tab_marketplace);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 4));
        session = new SessionManager(getApplicationContext());

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
//                    case R.id.navbar_dashboard:
//                        break;
//                    case R.id.navbar_create_request:
//                        intent = new Intent(getBaseContext(), CreateRequestActivity.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.navbar_poin:
//                        break;
//                    case R.id.navbar_jaringan:
//                        break;
//                    case R.id.navbar_news:
//                        break;
//                    case R.id.navbar_materi:
//                        break;
                    case R.id.navbar_keluar:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MarketplaceActivity.this);

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
            super.onBackPressed();
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
            Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.search) {
            Intent intent = new Intent(getBaseContext(), SearchActivity.class);
            startActivity(intent);
            return true;
        }

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
}
