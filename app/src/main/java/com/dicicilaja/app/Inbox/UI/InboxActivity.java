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
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.Inbox.Adapter.InboxAdapter;
import com.dicicilaja.app.Inbox.Data.Notif.Datum;
import com.dicicilaja.app.Inbox.Data.Notif.Notif;
import com.dicicilaja.app.Inbox.Network.ApiClient;
import com.dicicilaja.app.Inbox.Network.ApiService;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InboxActivity extends AppCompatActivity {

    List<Datum> notifs;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ProgressDialog progress;
    @BindView(R.id.recycler_notif)
    RecyclerView recyclerNotif;
    ApiService apiService;
    @BindView(R.id.order)
    LinearLayout order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());

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

        progress.show();
        Call<Notif> call = apiService.getNotifPersonal(session.getUserIdOneSignal());
        call.enqueue(new Callback<Notif>() {
            @Override
            public void onResponse(Call<Notif> call, Response<Notif> response) {
                if (response.isSuccessful()) {
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
                    progress.dismiss();
                }
                progress.dismiss();

            }

            @Override
            public void onFailure(Call<Notif> call, Throwable t) {
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
