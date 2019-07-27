package com.dicicilaja.app.BusinessReward.ui.Transaction.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dicicilaja.app.Adapter.TransaksiAdapter;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.ClaimRewards;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.Meta;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.Transaction.adapter.ListClaimRewardAdapter;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_transaksi)
    RecyclerView recyclerTransaksi;

    TransaksiAdapter adapter;
    @BindView(R.id.dataTrans)
    LinearLayout dataTrans;
    @BindView(R.id.warn)
    LinearLayout warn;
    @BindView(R.id.fabScrollTop)
    FloatingActionButton fabScrollTop;

    SessionManager session;

    List<Datum> dataTemp;
    ListClaimRewardAdapter listClaimRewardAdapter;

    int totalData = 1;
    int totalPage = 1;
    int currentPage = 1;
    boolean isLoading = false;
    ProgressDialog progress;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;

    String id;

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabScrollTop.bringToFront();
//        apiKey = "Bearer " + session.getToken();

        session = new SessionManager(getBaseContext());
        dataTemp = new ArrayList<>();

        listClaimRewardAdapter = new ListClaimRewardAdapter(getBaseContext(), dataTemp, this);

        recyclerTransaksi.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerTransaksi.setHasFixedSize(true);

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                doLoadData();
                swipeToRefresh.setRefreshing(false);
            }
        });

        // Load data
        doLoadData();

        // Init recyleview listener
        initListener();

        // Hide recyle
        hideEmpty();
    }

    private void doLoadData() {
        showLoading(false);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<ClaimRewards> call = apiService.getClaim(session.getAxiId(), currentPage, "id", "desc");
        call.enqueue(new Callback<ClaimRewards>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<ClaimRewards> call, Response<ClaimRewards> response) {
                if (response.isSuccessful()) {
                    final List<Datum> dataItems = response.body().getData();
                    Meta meta = response.body().getMeta();
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                    String total = formatter.format(meta.getTotal()).replace(",", ".");

                    totalPage = meta.getLastPage();
                    totalData = meta.getTotal();
                    currentPage = meta.getCurrentPage();
                    Log.d("Page1", String.valueOf(totalPage));
                    Log.d("Page2", String.valueOf(totalData));
                    Log.d("Page3", String.valueOf(currentPage));

                    if (dataItems.size() == 0) {
                        dataTrans.setVisibility(View.GONE);
                        warn.setVisibility(View.VISIBLE);
                    } else {
                        if (meta.getCurrentPage() == 1) {
                            dataTemp.clear();
                            dataTemp.addAll(dataItems);

                            listClaimRewardAdapter.notifyDataSetChanged();
                            recyclerTransaksi.setAdapter(listClaimRewardAdapter);

//                            recyclerTransaksi.setAdapter(new ListClaimRewardAdapter(dataItems, getBaseContext()));
                        } else {
                            listClaimRewardAdapter.refreshAdapter(dataItems);
                        }
                    }
                } else {
//                    session.logoutUser();
                }
                hideLoading();
            }

            @Override
            public void onFailure(Call<ClaimRewards> call, Throwable t) {
                progress.dismiss();
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        doLoadData();
                    }
                });
                hideLoading();
                alertDialog.show();
            }
        });
    }

    private void initListener() {
        recyclerTransaksi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int countItem = layoutManager.getItemCount();

                int visiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if (visiblePosition >= 3) {
                    fabScrollTop.setVisibility(View.VISIBLE);
                } else {
                    fabScrollTop.setVisibility(View.GONE);
                }

                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                boolean isLastPosition = countItem - 1 == lastVisiblePosition;

                if (!isLoading && isLastPosition && currentPage < totalPage) {
                    showLoading(false);
                    currentPage = currentPage + 1;
                    doLoadData();
                }
            }
        });
        fabScrollTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerTransaksi.scrollToPosition(0);
                fabScrollTop.setVisibility(View.GONE);
            }
        });
    }

    private void showLoading(boolean isRefresh) {
        isLoading = true;
        progress.show();
    }

    private void hideLoading() {
        isLoading = false;
        progress.dismiss();
    }

    private void hideEmpty() {
        recyclerTransaksi.setVisibility(View.VISIBLE);
//        layoutEmpty.setVisibility(View.GONE);
    }
}