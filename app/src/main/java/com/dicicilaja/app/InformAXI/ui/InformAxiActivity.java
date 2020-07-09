package com.dicicilaja.app.InformAXI.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dicicilaja.app.API.Client.ApiBff;
import com.dicicilaja.app.API.Interface.InterfaceLogout;
import com.dicicilaja.app.Activity.AssignSurveyorWebViewActivity;
import com.dicicilaja.app.Activity.ProfileActivity;
import com.dicicilaja.app.Activity.SearchActivity;
import com.dicicilaja.app.Inbox.Data.Popup.Datum;
import com.dicicilaja.app.Inbox.Data.Popup.Popup;
import com.dicicilaja.app.Inbox.UI.InboxActivity;
import com.dicicilaja.app.Inbox.UI.PopUpActivity;
import com.dicicilaja.app.InformAXI.OrderTrackingActivity;
import com.dicicilaja.app.InformAXI.ui.gathering.GatheringActivity;
import com.dicicilaja.app.InformAXI.ui.home.HomeFragment;
import com.dicicilaja.app.InformAXI.ui.register.RegisterActivity;
import com.dicicilaja.app.InformAXI.ui.trip.TripActivity;
import com.dicicilaja.app.Model.Logout;
import com.dicicilaja.app.NewSimulation.UI.NewSimulation.NewSimulationActivity;
import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.OrderIn.UI.OrderInActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.WebView.MateriActivity;
import com.dicicilaja.app.WebView.NewsActivity;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformAxiActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private View navHeader;
    private TextView tvName;
    SessionManager session;
    private LinearLayout tvLookProfile;
    private CircularImageView ivAvatar;
    private ImageButton ibMenu, ibNotification;
    String agen_axi_id, agen_id, agen_name;

    List<Datum> dataPopups;
    String apiKey;


    ProgressDialog progress;

    Dialog InAppDialog;

    public static int navItemIndex = 0;
    public static String CURRENT_TAG = "home";
    public boolean isDialogShowing = false;

    private Handler mHandler;

    com.dicicilaja.app.Inbox.Network.ApiService apiService4;

    ProgressDialog progress_popup;

    TextView detail, nanti;
    ImageView thumbnail, close;
    Boolean openInbox;
    ApiService3 apiService3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_axi);

        initVariables();
        initToolbar();
        initListener();
        loadHomeFragment();

        apiService3 = ApiClient2.getClient().create(ApiService3.class);

        openInbox = getIntent().getBooleanExtra("openInbox", false);
        if(openInbox) {
            Intent intent = new Intent(getBaseContext(), InboxActivity.class);
            startActivityForResult(intent, 77);
        }
    }

    private void initVariables() {
        toolbar = findViewById(R.id.toolbar);
        ibMenu = findViewById(R.id.ib_menu);
        ibNotification = findViewById(R.id.ib_notification);
        navView = findViewById(R.id.nav_view);
        navHeader = navView.getHeaderView(0);
        drawerLayout = findViewById(R.id.drawer_layout);
        tvName = navHeader.findViewById(R.id.tv_name);
        tvLookProfile = navHeader.findViewById(R.id.tv_look_profile);
        ivAvatar = navHeader.findViewById(R.id.iv_avatar);

        mHandler = new Handler();
        session = new SessionManager(this);
        apiKey = "Bearer " + session.getToken();

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);

        loadNavHeader();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        apiService4 = com.dicicilaja.app.Inbox.Network.ApiClient.getClient().create(com.dicicilaja.app.Inbox.Network.ApiService.class);
        inAppDialog();
    }

    private void initListener() {
        ibMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));

        ibNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), InboxActivity.class);
                startActivity(intent);
            }
        });

        navView.setNavigationItemSelectedListener(this);

        tvLookProfile.setOnClickListener(view -> {
            // intent to profile detail
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });
    }

    private void loadNavHeader() {
        String name = session.getName();
        tvName.setText(name);

        String photo = session.getPhoto();
        if (photo != null && !photo.isEmpty())
            Glide.with(this).load(photo)
                    .placeholder(R.drawable.avatar)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivAvatar);
        else
            Glide.with(this).load(R.drawable.avatar)
                    .placeholder(R.drawable.avatar)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivAvatar);
    }

    private void loadHomeFragment() {
        // select appropriate nav menu item
        selectNavMenu();

        // if user select the current navigation, don't do anything
        // just close the drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = () -> {
            Fragment fragment = getFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.replace(R.id.frame_layout, fragment, CURRENT_TAG);
            transaction.commitAllowingStateLoss();
        };

        mHandler.post(mPendingRunnable);

        drawerLayout.closeDrawers();
        invalidateOptionsMenu();
    }

    private void selectNavMenu() {
        navView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private Fragment getFragment() {
        switch (CURRENT_TAG) {
            case "home":
                return HomeFragment.newInstance("home");
            case "register":
                return HomeFragment.newInstance("register");
            case "trip":
                return HomeFragment.newInstance("home");
            default:
                return HomeFragment.newInstance("home");
        }
    }

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
            case R.id.nav_submission:
                progress.show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Call<Axi> axiReff = apiService3.getAxi(apiKey, session.getNomorAxiId());
                axiReff.enqueue(new Callback<Axi>() {
                    @Override
                    public void onResponse(Call<Axi> call, Response<Axi> response) {
                        if (response.isSuccessful()) {
                            try {
                                if (response.body().getData().size() > 0) {
                                    agen_id = String.valueOf(response.body().getData().get(0).getAttributes().getProfileId());
                                    agen_axi_id = String.valueOf(response.body().getData().get(0).getAttributes().getNomorAxiId());
                                    agen_name = response.body().getData().get(0).getAttributes().getNama();
                                    Intent intent2 = new Intent(getBaseContext(), OrderInActivity.class);
                                    intent2.putExtra("agen_id", agen_id);
                                    intent2.putExtra("agen_axi_id", agen_axi_id);
                                    intent2.putExtra("agen_name", agen_name);
                                    startActivity(intent2);
                                    progress.hide();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                } else {
                                    agen_id = null;
                                    agen_axi_id = null;
                                    agen_name = null;
                                    Intent intent2 = new Intent(getBaseContext(), OrderInActivity.class);
                                    intent2.putExtra("agen_id", agen_id);
                                    intent2.putExtra("agen_axi_id", agen_axi_id);
                                    intent2.putExtra("agen_name", agen_name);
                                    startActivity(intent2);
                                    progress.hide();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                }


                            } catch (Exception ex) {
                            }
                        } else {
                            progress.hide();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformAxiActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Axi> call, Throwable t) {
                        progress.hide();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformAxiActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        alertDialog.show();
                    }
                });
                break;
            case R.id.nav_financing:
                Intent intent4 = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_tracking:
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, OrderTrackingActivity.class));
                break;
            case R.id.nav_simulation:
                Intent intent5 = new Intent(getBaseContext(), NewSimulationActivity.class);
                startActivity(intent5);
                break;
            case R.id.nav_news:
                Intent intent6 = new Intent(getBaseContext(), NewsActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_download:
                Intent intent7 = new Intent(getBaseContext(), MateriActivity.class);
                startActivity(intent7);
                break;
            case R.id.nav_assign_surveyor:
                Intent intent8 = new Intent(getBaseContext(), AssignSurveyorWebViewActivity.class);
                startActivity(intent8);
                break;
            case R.id.nav_logout:
                showDialogLogout();
                break;
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

    private void showDialogLogout() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformAxiActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Konfirmasi");

        // Setting Dialog Message
        alertDialog.setMessage("Apakah Anda yakin ingin keluar?");


        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                progress.show();
                InterfaceLogout apiService =
                        ApiBff.getClient().create(InterfaceLogout.class);

                Call<Logout> call2 = apiService.logout(apiKey);
                call2.enqueue(new Callback<Logout>() {
                    @Override
                    public void onResponse(Call<Logout> call, Response<Logout> response2) {
                        try {
                            if (response2.isSuccessful()) {
                                progress.hide();
                                session.logoutUser();
                            }
                        } catch (Exception ex) {
                            progress.hide();
                        }
                    }

                    @Override
                    public void onFailure(Call<Logout> call, Throwable t) {
                        progress.hide();
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
            loadHomeFragment();
            selectNavMenu();
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNavHeader();

        if (navItemIndex != 0) {
            navItemIndex = 0;
            CURRENT_TAG = getString(R.string.home_tag);
            loadHomeFragment();
            selectNavMenu();
            return;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void inAppDialog() {
        progress_popup = new ProgressDialog(this);
        progress_popup.setMessage("Sedang memuat data...");
        progress_popup.setCanceledOnTouchOutside(false);

        InAppDialog = new Dialog(InformAxiActivity.this);
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
                progress_popup.hide();
                if (response.isSuccessful()) {
                    dataPopups = response.body().getData();
                    if (dataPopups.size() != 0) {
                        try {
                            Glide.with(InformAxiActivity.this)
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
                    progress_popup.hide();
                } else {
                    progress_popup.hide();
                }
            }

            @Override
            public void onFailure(Call<Popup> call, Throwable t) {
                progress_popup.hide();
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
