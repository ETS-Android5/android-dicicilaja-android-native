package com.dicicilaja.app.Inbox.UI;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.Inbox.Adapter.InboxAdapter;
import com.dicicilaja.app.Inbox.Data.Notif.Datum;
import com.dicicilaja.app.Inbox.Data.Notif.Notif;
import com.dicicilaja.app.Inbox.Network.ApiClient;
import com.dicicilaja.app.Inbox.Network.ApiService;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InboxActivity extends AppCompatActivity {

    SessionManager session;
    String apiKey;
    List<Datum> notifs;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ProgressDialog progress;
    @BindView(R.id.recycler_notif)
    RecyclerView recyclerNotif;
    ApiService apiService;
    @BindView(R.id.order)
    LinearLayout order;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.layout_loader)
    SpinKitView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();

        initAction();
        initLoadData();

        swipeToRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initLoadData();
                swipeToRefresh.setRefreshing(false);
            }
        });

    }

    private void initAction() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getBaseContext());

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);

        apiService = ApiClient.getClient().create(ApiService.class);

        recyclerNotif.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    private void initLoadData() {
//        progress.show();
        loader.setVisibility(View.VISIBLE);
        Call<Notif> call = apiService.getNotifPersonal(apiKey, session.getUserIdOneSignal());
        call.enqueue(new Callback<Notif>() {
            @Override
            public void onResponse(Call<Notif> call, Response<Notif> response) {
                if (response.code() == 401) {
                    loader.setVisibility(View.GONE);
                    session.logoutUser();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if (response.code() == 200) {
                    loader.setVisibility(View.GONE);
                    notifs = response.body().getData();
                    if (notifs.size() == 0) {
                        recyclerNotif.setVisibility(View.GONE);
                        order.setVisibility(View.VISIBLE);
                    } else {
                        recyclerNotif.setVisibility(View.VISIBLE);
                        order.setVisibility(View.GONE);
                        recyclerNotif.setAdapter(new InboxAdapter(notifs, R.layout.card_notification, getBaseContext()));
                        recyclerNotif.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerNotif, new ClickListener() {
                            @Override
                            public void onClick(View view, final int position) {
                                Intent intent = new Intent(getBaseContext(), InboxDetailActivity.class);
                                intent.putExtra("category", notifs.get(position).getAttributes().getCategory());
                                intent.putExtra("title", notifs.get(position).getAttributes().getTitle());
                                intent.putExtra("message", notifs.get(position).getAttributes().getMessage());
                                intent.putExtra("date", notifs.get(position).getCreatedAtReadable());
                                intent.putExtra("url", notifs.get(position).getAttributes().getUrl());
                                startActivity(intent);
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                            }
                        }));
                    }
                } else {
                    loader.setVisibility(View.GONE);
                    recyclerNotif.setVisibility(View.GONE);
                    order.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Sepertinya terjadi sesuatu, silakan refresh", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Notif> call, Throwable t) {
//                progress.dismiss();
                loader.setVisibility(View.GONE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InboxActivity.this);
                alertDialog.setMessage("Koneksi internet tidak ditemukan");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                super.finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
