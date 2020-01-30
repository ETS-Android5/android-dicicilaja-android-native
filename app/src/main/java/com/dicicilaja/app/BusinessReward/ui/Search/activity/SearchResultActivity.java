package com.dicicilaja.app.BusinessReward.ui.Search.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Meta;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Produk;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.Search.adapter.ListSearchAdapter;
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

public class SearchResultActivity extends AppCompatActivity {
    private static final String TAG = SearchResultActivity.class.getSimpleName();

    ListSearchAdapter listSearchAdapter;
    @BindView(R.id.recycler_search)
    RecyclerView recyclerSearch;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.fabScrollTop)
    FloatingActionButton fabScrollTop;
    @BindView(R.id.dataSearch)
    LinearLayout dataSearch;
    @BindView(R.id.warn)
    LinearLayout warn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inputCariBarang)
    EditText inputCariBarang;
    String barang_dicari;

    ProgressDialog progress;

    SessionManager session;

    int totalData = 1;
    int totalPage = 1;
    int currentPage = 1;
    boolean isLoading = false;

    List<Datum> dataTemp;

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        fabScrollTop.bringToFront();
        session = new SessionManager(getBaseContext());
        dataTemp = new ArrayList<>();

        listSearchAdapter = new ListSearchAdapter(getBaseContext(), dataTemp);

        recyclerSearch.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        recyclerSearch.setHasFixedSize(true);

        progress = new ProgressDialog(this);

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                doLoadData();
                swipeToRefresh.setRefreshing(false);
            }
        });

        inputCariBarang.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    barang_dicari = inputCariBarang.getText().toString();

                    progress.setMessage("Sedang memuat data...");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();

                    doLoadData();

                    // Init recyleview listener
                    initListener();

                    // Hide recyle
                    hideEmpty();
                }
                return true;
            }
        });

    }

    private void doLoadData() {
        showLoading(false);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<Produk> call = apiService.getProduk(barang_dicari);
        call.enqueue(new Callback<Produk>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<Produk> call, Response<Produk> response) {
                if (response.isSuccessful()) {
                    warn.setVisibility(View.GONE);
                    dataSearch.setVisibility(View.VISIBLE);

                    final List<Datum> dataItems = response.body().getData();
                    Meta meta = response.body().getMeta();
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                    String total = formatter.format(meta.getTotal()).replace(",", ".");

                    totalPage = meta.getLastPage();
                    totalData = meta.getTotal();
                    currentPage = meta.getCurrentPage();

                    if (dataItems.size() == 0) {
                        dataSearch.setVisibility(View.GONE);
                        warn.setVisibility(View.VISIBLE);
                    } else {
                        if (meta.getCurrentPage() == 1) {
                            warn.setVisibility(View.GONE);
                            dataSearch.setVisibility(View.VISIBLE);

                            dataTemp.clear();
                            dataTemp.addAll(dataItems);

                            listSearchAdapter.notifyDataSetChanged();
                            recyclerSearch.setAdapter(listSearchAdapter);

                        } else {
                            listSearchAdapter.refreshAdapter(dataItems);
                        }
                    }
                } else {
                }
                hideLoading();
            }

            @Override
            public void onFailure(Call<Produk> call, Throwable t) {
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
        recyclerSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                recyclerSearch.scrollToPosition(0);
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
        recyclerSearch.setVisibility(View.VISIBLE);
    }
}
