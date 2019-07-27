package com.dicicilaja.app.Activity;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.List;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceNotification;
import com.dicicilaja.app.API.Model.Notification.Notification;
import com.dicicilaja.app.Adapter.NotifAdapter;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationActivity extends AppCompatActivity {
    List<com.dicicilaja.app.API.Model.Notification.Datum> notifs;
    String apiKey;

    LinearLayout order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        order = findViewById(R.id.order);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        final RecyclerView recyclerView =  findViewById(R.id.recycler_notif);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        InterfaceNotification apiService =
                RetrofitClient.getClient().create(InterfaceNotification.class);

        Call<Notification> call = apiService.getNotification(apiKey);
        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                if ( response.isSuccessful() ) {
                    notifs = response.body().getData();
                    if(notifs.size() == 0){
                        recyclerView.setVisibility(View.GONE);
                        order.setVisibility(View.VISIBLE);
                    }else{
                        recyclerView.setVisibility(View.VISIBLE);
                        order.setVisibility(View.GONE);
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
                    }
                    progress.dismiss();
                }
                progress.dismiss();

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
