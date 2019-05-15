package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfacePengajuanMaxi;
import com.dicicilaja.app.API.Item.PengajuanMaxi.Datum;
import com.dicicilaja.app.API.Item.PengajuanMaxi.PengajuanMaxi;
import com.dicicilaja.app.Adapter.PengajuanMaxiAllAdapter;
import com.dicicilaja.app.Model.RequestMeta;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPengajuanMaxiActivity extends AppCompatActivity {
    List<Datum> pengajuan;
    SessionManager session;
    String apiKey;

    ProgressDialog progress;

    Toolbar toolbar;
    RecyclerView recyclerView;
    PengajuanMaxiAllAdapter adapter;
    FloatingActionButton fabScrollTop;

    int totalData = 1;
    int totalPage = 1;
    int currentPage = 1;
    boolean isLoading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pengajuan_maxi);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        adapter = new PengajuanMaxiAllAdapter(pengajuan, R.layout.card_pengajuan, getBaseContext());
        recyclerView.setAdapter(adapter);

        fabScrollTop = findViewById(R.id.fabScrollTop);

        // Load data
        doLoadData();

        // Init listener
        initListener();
    }

    private void doLoadData() {
        showLoading();

        InterfacePengajuanMaxi apiService =
                RetrofitClient.getClient().create(InterfacePengajuanMaxi.class);

        Call<PengajuanMaxi> call2 = apiService.getPengajuanMaxi(apiKey, currentPage);
        call2.enqueue(new Callback<PengajuanMaxi>() {
            @Override
            public void onResponse(Call<PengajuanMaxi> call, Response<PengajuanMaxi> response) {
                List<Datum> items = response.body().getData();
                RequestMeta meta = response.body().getMeta();

                totalPage = meta.getLastPage();
                totalData = meta.getTotal();
                currentPage = meta.getCurrentPage();

                if( currentPage == 1 ) {
                    pengajuan.clear();
                    pengajuan.addAll(items);

                    adapter.notifyDataSetChanged();

                } else {
                    adapter.refreshAdapter(items);
                }

                hideLoading();
            }

            @Override
            public void onFailure(Call<PengajuanMaxi> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
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

                int visiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if( visiblePosition >= 3 ) {
                    fabScrollTop.setVisibility(View.VISIBLE);
                } else {
                    fabScrollTop.setVisibility(View.GONE);
                }

                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                boolean isLastPosition = countItem - 1 == lastVisiblePosition;

                if( !isLoading && isLastPosition && currentPage < totalPage ) {
                    currentPage = currentPage + 1;
                    doLoadData();
                }
            }
        });
        fabScrollTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(0);
                fabScrollTop.setVisibility(View.GONE);
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
