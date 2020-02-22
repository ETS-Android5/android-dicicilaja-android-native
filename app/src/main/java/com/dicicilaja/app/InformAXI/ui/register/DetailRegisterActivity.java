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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Husni with ❤
 */

public class DetailRegisterActivity extends AppCompatActivity {

    /* UI Region */
    private Toolbar toolbar;
    private RecyclerView rvDetailRegister;
    private ProgressBar pbDetail;
    private RelativeLayout emptyContainer;

    private String toolbarTitle = "";
    private String monthId = "";

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    private SessionManager session;
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
        getRegListDetail();
    }

    private void initVariables() {
        toolbarTitle = getIntent().getStringExtra("monthName");
        monthId = getIntent().getStringExtra("monthId");

        toolbar = findViewById(R.id.toolbar);
        rvDetailRegister = findViewById(R.id.rv_detail);
        pbDetail = findViewById(R.id.pb_detail);
        emptyContainer = findViewById(R.id.empty_container);

        session = new SessionManager(this);
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

    private void getRegListDetail() {
        mCompositeDisposable.add(
                jsonApi.getRegListDetail(monthId, branchId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> pbDetail.setVisibility(View.VISIBLE))
                        .doOnComplete(() -> pbDetail.setVisibility(View.GONE))
                        .subscribe(this::onSuccessGetRegListDetail, this::onError)
        );
    }

    private void onSuccessGetRegListDetail(AxiHome data) {
        if (data != null) {
            if (data.getData() != null && !data.getData().isEmpty()) {
                regDetailList.addAll(data.getData());
                adapter.notifyDataSetChanged();
            } else {
                emptyContainer.setVisibility(View.VISIBLE);
            }
        }
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
