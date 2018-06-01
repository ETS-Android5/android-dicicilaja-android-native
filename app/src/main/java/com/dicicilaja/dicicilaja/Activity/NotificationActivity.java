package com.dicicilaja.dicicilaja.Activity;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceNotification;
import com.dicicilaja.dicicilaja.API.Item.Notification.Notification;
import com.dicicilaja.dicicilaja.Adapter.NotifAdapter;
import com.dicicilaja.dicicilaja.Listener.ClickListener;
import com.dicicilaja.dicicilaja.Listener.RecyclerTouchListener;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationActivity extends AppCompatActivity {
    List<com.dicicilaja.dicicilaja.API.Item.Notification.Datum> notifs;
    String apiKey;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        final RecyclerView recyclerView =  findViewById(R.id.recycler_notif);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                InterfaceNotification apiService =
                        RetrofitClient.getClient().create(InterfaceNotification.class);

                Call<Notification> call = apiService.getNotification(apiKey);
                call.enqueue(new Callback<Notification>() {
                    @Override
                    public void onResponse(Call<Notification> call, Response<Notification> response) {
                        if ( response.isSuccessful() ) {
                            notifs = response.body().getData();
                            recyclerView.setAdapter(new NotifAdapter(notifs, R.layout.card_notif, getBaseContext()));


                            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                                @Override
                                public void onClick(View view, final int position) {
                                    Intent intent = new Intent(getBaseContext(), DetailRequestActivity.class);
                                    intent.putExtra("EXTRA_REQUEST_ID", notifs.get(position).getTransaction_id().toString());
                                    intent.putExtra("STATUS", true);
                                    startActivity(intent);

                                }

                                @Override
                                public void onLongClick(View view, int position) {
                                }
                            }));
                            progress.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<Notification> call, Throwable t) {
                        progress.dismiss();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
                        alertDialog.setMessage("Koneksi internet tidak ditemukan");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.show();
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        InterfaceNotification apiService =
                RetrofitClient.getClient().create(InterfaceNotification.class);

        Call<Notification> call = apiService.getNotification(apiKey);
        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                if ( response.isSuccessful() ) {
                    notifs = response.body().getData();
                    recyclerView.setAdapter(new NotifAdapter(notifs, R.layout.card_notif, getBaseContext()));


                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                        @Override
                        public void onClick(View view, final int position) {
                            Intent intent = new Intent(getBaseContext(), DetailRequestActivity.class);
                            intent.putExtra("EXTRA_REQUEST_ID", notifs.get(position).getTransaction_id().toString());
                            intent.putExtra("STATUS", true);
                            startActivity(intent);

                        }

                        @Override
                        public void onLongClick(View view, int position) {
                        }
                    }));
                    progress.dismiss();
                }

            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
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
                super.finish();
        }
        return true;
    }
}
