package com.dicicilaja.app.InformAXI.ui.register;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dicicilaja.app.InformAXI.adapter.AxiHomeAdapter;
import com.dicicilaja.app.InformAXI.model.AxiHome;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DetailRegisterActivity extends AppCompatActivity {

    SessionManager session;
    String apiKey;

    private SwipeRefreshLayout swipeHome;
    private NestedScrollView nestedHome;

    /* UI Region */
    private Toolbar toolbar;
    private RecyclerView rvDetailRegister;
    private ProgressBar pbDetail;
    private RelativeLayout emptyContainer;

    private String toolbarTitle = "";
    private String monthId = "";

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    private final int START_PAGE = 1;
    private int page = START_PAGE, lastPage = 0;
    private boolean isRefresh = false, isLastPage = false;

    private AxiHomeAdapter adapter;

    private int branchId = -1;

    private List<AxiHome.DataBean> regDetailList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_register_axi);

        initVariables();
        initToolbar();
        initRecyclerView();
        initListener();
        getRegListDetail(START_PAGE);
    }

    private void initVariables() {
        toolbarTitle = getIntent().getStringExtra("monthName");
        monthId = getIntent().getStringExtra("monthId");

        swipeHome = findViewById(R.id.swipe_home);
        nestedHome = findViewById(R.id.nested_home);
        toolbar = findViewById(R.id.toolbar);
        rvDetailRegister = findViewById(R.id.rv_detail);
        pbDetail = findViewById(R.id.pb_detail);
        emptyContainer = findViewById(R.id.empty_container);

        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();
        branchId = Integer.valueOf(session.getBranchId());

        regDetailList = new ArrayList<>();

        adapter = new AxiHomeAdapter(regDetailList, this);

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance();
        jsonApi = retrofit.create(NetworkInterface.class);
    }

    private void initToolbar() {
        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initRecyclerView() {
        LinearLayoutManager lm = new LinearLayoutManager(this);

        rvDetailRegister.setLayoutManager(lm);
        rvDetailRegister.setHasFixedSize(true);
        rvDetailRegister.setAdapter(adapter);
    }

    private void initListener() {

        nestedHome.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            emptyContainer.setVisibility(View.GONE);
            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                if (page < lastPage) {
                    pbDetail.setVisibility(View.VISIBLE);
                    page += 1;
                    getRegListDetail(page);
                }
            }
        });

        swipeHome.setOnRefreshListener(() -> {
            isRefresh = true;
            isLastPage = false;
            page = START_PAGE;
            regDetailList.clear();
            adapter.notifyDataSetChanged();
            pbDetail.setVisibility(View.VISIBLE);
            getRegListDetail(page);
        });
    }

    private void getRegListDetail(int page) {
        mCompositeDisposable.add(
                jsonApi.getRegListDetail(apiKey, monthId, 10, page, branchId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> pbDetail.setVisibility(View.VISIBLE))
                        .doOnComplete(() -> pbDetail.setVisibility(View.GONE))
                        .subscribe(this::onSuccessGetRegListDetail, this::onError)
        );
    }

    private void onSuccessGetRegListDetail(AxiHome data) {

        if (data != null && data.getData() != null && data.getData().size() > 0) {
            if (isRefresh) {
                regDetailList.clear();
                adapter.notifyDataSetChanged();
                isRefresh = false;
            }

            regDetailList.addAll(data.getData());
            lastPage = data.getMeta().getLastPage();
            adapter.notifyDataSetChanged();
        } else {
            if (page == START_PAGE) {
                emptyContainer.setVisibility(View.VISIBLE);
            } else {
                emptyContainer.setVisibility(View.GONE);
            }
        }
        pbDetail.setVisibility(View.GONE);
        swipeHome.setRefreshing(false);
    }

    private void onError(Throwable t) {
        Tools.showToast(this, t.getMessage());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
