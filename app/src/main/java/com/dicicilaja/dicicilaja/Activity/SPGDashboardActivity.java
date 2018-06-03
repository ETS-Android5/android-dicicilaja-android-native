package com.dicicilaja.dicicilaja.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceRequestProgress;
import com.dicicilaja.dicicilaja.API.Item.RequestProgress.Datum;
import com.dicicilaja.dicicilaja.API.Item.RequestProgress.RequestProgress;
import com.dicicilaja.dicicilaja.Adapter.RequestProgressAdapter;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import com.dicicilaja.dicicilaja.WebView.SimulationActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SPGDashboardActivity extends AppCompatActivity implements RequestProgressAdapter.RequestProgressAdapterListener {

    SessionManager session;
    private static final String TAG = SPGDashboardActivity.class.getSimpleName();
    List<Datum> requestProgress;
    RequestProgressAdapter requestProgressAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RelativeLayout top_attribut;
    SearchView search;
    String apiKey;
    CardView search_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spgdashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        search = findViewById(R.id.search);
        search_toggle = findViewById(R.id.search_toggle);
        top_attribut = findViewById(R.id.top_attribut);
        search.setVisibility(View.GONE);
        search_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search.getVisibility() == View.GONE) {
                    top_attribut.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                    search.requestFocus();
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(search, InputMethodManager.SHOW_IMPLICIT);
                }else {
                    top_attribut.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);
                    search.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.navbar_request1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                switch( menuItem.getItemId() ) {
                    case R.id.navbar_request:
                        break;
                    case R.id.navbar_create_request:
                        break;
                    case R.id.navbar_cek:
                        break;
                    case R.id.navbar_simulation:
                        intent = new Intent(getBaseContext(), SimulationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navbar_statistics:
                        break;
                    case R.id.navbar_exit:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SPGDashboardActivity.this);

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
        TextView branch = navigationView.getHeaderView(0).findViewById(R.id.branch);
        TextView area = navigationView.getHeaderView(0).findViewById(R.id.area);
        View navbarView = navigationView.getHeaderView(0);
        LinearLayout open_profile = navbarView.findViewById(R.id.open_profile);
        ImageView profile_pictures = navbarView.findViewById(R.id.imageView);
        TextView name = navbarView.findViewById(R.id.nameView);

        area.setText(session.getArea());
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

        TextView title_pengumuman = findViewById(R.id.title_pengumuman);
        final TextView jumlah_pengajuan = findViewById(R.id.jumlah_pengajuan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_pengumuman.setTypeface(opensans_bold);
        jumlah_pengajuan.setTypeface(opensans_bold);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        final RecyclerView recyclerView =  findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        requestProgress = new ArrayList<>();
        requestProgressAdapter = new RequestProgressAdapter(getBaseContext(), requestProgress, this);

        search = findViewById(R.id.search);
        search_toggle = findViewById(R.id.search_toggle);
        top_attribut = findViewById(R.id.top_attribut);
        search.setVisibility(View.GONE);

        EditText searchBox = search.findViewById (android.support.v7.appcompat.R.id.search_src_text);
        searchBox.setTextSize(16);
        searchBox.setTextColor(Color.parseColor("#000000"));
        searchBox.setCursorVisible(false);

        ImageView searchClose = search.findViewById (android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setColorFilter (Color.parseColor("#F89E4C"), PorterDuff.Mode.SRC_ATOP);
        searchClose.setImageResource(R.drawable.ic_close);

        search_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search.getVisibility() == View.GONE) {
                    top_attribut.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                    search.requestFocus();
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(search, InputMethodManager.SHOW_IMPLICIT);
                }else {
                    top_attribut.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);
                    search.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                InterfaceRequestProgress apiService =
                        RetrofitClient.getClient().create(InterfaceRequestProgress.class);

                Call<RequestProgress> call = apiService.getRequestProgress(apiKey);
                call.enqueue(new Callback<RequestProgress>() {
                    @Override
                    public void onResponse(Call<RequestProgress> call, Response<RequestProgress> response) {
                        if ( response.isSuccessful() ) {
                            List<Datum> items = response.body().getData();
                            jumlah_pengajuan.setText(Integer.toString(items.size()));
                            requestProgress.clear();
                            requestProgress.addAll(items);

                            requestProgressAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(requestProgressAdapter);

//                            requestProgress = response.body().getData();
//                            jumlah_pengajuan.setText(Integer.toString(requestProgress.size()));
//                            recyclerView.setAdapter(new RequestProgressAdapter(requestProgress, R.layout.card_pengajuan, getContext()));
//
//
//                            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
//                                @Override
//                                public void onClick(View view, int position) {
//                                    Intent intent = new Intent(getContext(), DetailRequestActivity.class);
//                                    intent.putExtra("EXTRA_REQUEST_ID", requestProgress.get(position).getId().toString());
//                                    intent.putExtra("STATUS", true);
//                                    startActivity(intent);
//                                }
//
//                                @Override
//                                public void onLongClick(View view, int position) {
//                                }
//                            }));


                        }

                    }

                    @Override
                    public void onFailure(Call<RequestProgress> call, Throwable t) {

                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        InterfaceRequestProgress apiService =
                RetrofitClient.getClient().create(InterfaceRequestProgress.class);

        Call<RequestProgress> call = apiService.getRequestProgress(apiKey);
        call.enqueue(new Callback<RequestProgress>() {
            @Override
            public void onResponse(Call<RequestProgress> call, Response<RequestProgress> response) {
                if ( response.isSuccessful() ) {
                    List<Datum> items = response.body().getData();
                    jumlah_pengajuan.setText(Integer.toString(items.size()));
                    requestProgress.clear();
                    requestProgress.addAll(items);

                    requestProgressAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(requestProgressAdapter);


//                    requestProgress = response.body().getData();
//                    jumlah_pengajuan.setText(Integer.toString(requestProgress.size()));
//                    recyclerView.setAdapter(new RequestProgressAdapter(requestProgress, R.layout.card_pengajuan, getContext()));
//
//
//                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
//                        @Override
//                        public void onClick(View view, int position) {
//                            Intent intent = new Intent(getContext(), DetailRequestActivity.class);
//                            intent.putExtra("EXTRA_REQUEST_ID", requestProgress.get(position).getId().toString());
//                            intent.putExtra("STATUS", true);
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void onLongClick(View view, int position) {
//                        }
//                    }));


                }

            }

            @Override
            public void onFailure(Call<RequestProgress> call, Throwable t) {

            }
        });

        SearchView search = findViewById(R.id.search);
        search.setActivated(true);
        search.setQueryHint("Ketik disini untuk mencari");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                requestProgressAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                requestProgressAdapter.getFilter().filter(query);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spgdashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notif1) {
            Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataSelected(Datum datum) {

    }
}
