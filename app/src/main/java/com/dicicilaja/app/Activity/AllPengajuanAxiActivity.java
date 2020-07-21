package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import com.dicicilaja.app.API.Client.ApiBff;
import com.dicicilaja.app.API.Client.ApiBffNew;
import com.dicicilaja.app.API.Interface.InterfacePengajuanAxi;
import com.dicicilaja.app.API.Model.PengajuanAxi.Datum;
import com.dicicilaja.app.API.Model.PengajuanAxi.PengajuanAxi;
import com.dicicilaja.app.Adapter.PengajuanAxiAllAdapter;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.Model.RequestMeta;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPengajuanAxiActivity extends AppCompatActivity {

    List<Datum> pengajuan;
    String apiKey;
    LinearLayout order;
    ProgressDialog progress;

    RecyclerView recyclerView;

    PengajuanAxiAllAdapter adapter;

    int totalData = 1;
    int totalPage = 1;
    int currentPage = 1;
    boolean isLoading = false;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pengajuan_axi);

        order = findViewById(R.id.order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        order.setVisibility(View.GONE);

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);

        recyclerView =  findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        pengajuan = new ArrayList<>();
        adapter = new PengajuanAxiAllAdapter(pengajuan, R.layout.card_pengajuan, getBaseContext());

        // Load Data
        doLoadData();

        // Init listener
        initListener();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }

    private void doLoadData() {
        showLoading();

        InterfacePengajuanAxi apiService =
                ApiBffNew.getClient().create(InterfacePengajuanAxi.class);

        Call<PengajuanAxi> call2 = apiService.getPengajuanAxi(apiKey, currentPage);
        call2.enqueue(new Callback<PengajuanAxi>() {
            @Override
            public void onResponse(Call<PengajuanAxi> call, Response<PengajuanAxi> response) {

                List<Datum> items = response.body().getData();

                if(response.body().getData().size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    order.setVisibility(View.VISIBLE);
                } else {
                    RequestMeta meta = response.body().getMeta();

                    totalPage = meta.getLastPage();
                    totalData = meta.getTotal();
                    currentPage = meta.getCurrentPage();

                    if( meta.getCurrentPage() == 1 ) {
                        pengajuan.clear();
                        pengajuan.addAll(items);

                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.refreshAdapter(items);
                    }
                }
                recyclerView.setAdapter(new PengajuanAxiAllAdapter(pengajuan, R.layout.card_pengajuan, getBaseContext()));
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Intent intent = new Intent(getBaseContext(), DetailRequestActivity.class);
                        intent.putExtra("EXTRA_REQUEST_ID", pengajuan.get(position).getId().toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));

                hideLoading();
            }

            @Override
            public void onFailure(Call<PengajuanAxi> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AllPengajuanAxiActivity.this);
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                hideLoading();
                alertDialog.show();
            }
        });
    }

    private void initListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int countItem = layoutManager.getItemCount();

                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                boolean isLastPosition = countItem - 1 == lastVisiblePosition;

                if( !isLoading && isLastPosition && currentPage < totalPage ) {
                    currentPage = currentPage + 1;
                    doLoadData();
                }
            }
        });
    }

    private void showLoading() {
        isLoading = true;
        progress.show();
    }

    private void hideLoading() {
        isLoading = false;
        progress.dismiss();
    }
}
