package com.dicicilaja.dicicilaja.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceNotificationAxi;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemNotificationAxi.Data;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemNotificationAxi.NotificationAxi;
import com.dicicilaja.dicicilaja.Adapter.NotifAdapter;
import com.dicicilaja.dicicilaja.Listener.ClickListener;
import com.dicicilaja.dicicilaja.Listener.RecyclerTouchListener;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationActivity extends AppCompatActivity {
    List<Data> notifs;
    String apiKey;
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

        final RecyclerView recyclerView =  findViewById(R.id.recycler_notif);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        InterfaceNotificationAxi apiService =
                NewRetrofitClient.getClient().create(InterfaceNotificationAxi.class);

        Call<NotificationAxi> call = apiService.getNotif(apiKey);
        call.enqueue(new Callback<NotificationAxi>() {
            @Override
            public void onResponse(Call<NotificationAxi> call, Response<NotificationAxi> response) {
                if ( response.isSuccessful() ) {
                    notifs = response.body().getData();
                    recyclerView.setAdapter(new NotifAdapter(notifs, R.layout.card_notif, getBaseContext()));


                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                        @Override
                        public void onClick(View view, final int position) {
                            Intent intent = new Intent(getBaseContext(), DetailRequestActivity.class);
                            intent.putExtra("EXTRA_REQUEST_ID", notifs.get(position).getTransactionId().toString());
                            intent.putExtra("STATUS", true);
                            startActivity(intent);

                        }

                        @Override
                        public void onLongClick(View view, int position) {
                        }
                    }));


                } else {
                    Toast.makeText(getBaseContext(), "Koneksi Internet Tidak Ditemukan", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<NotificationAxi> call, Throwable t) {

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
