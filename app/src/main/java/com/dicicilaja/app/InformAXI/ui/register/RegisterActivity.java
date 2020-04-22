package com.dicicilaja.app.InformAXI.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.dicicilaja.app.InformAXI.adapter.AxiRegisterAdapter;
import com.dicicilaja.app.InformAXI.model.AxiRegister;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    SessionManager session;
    String apiKey;

    /* UI Region */
    private Toolbar toolbar;
    private RecyclerView rvRegister;
    private ProgressBar pbRegister;

    private AxiRegisterAdapter regAdapter;

    private List<AxiRegister.DataBean> regList;

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    private SharedPreferences pref;

    private int branchId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informaxi_register);

        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();

        initVariables();
        initToolbar();
        initRecyclerView();
        getRegistration();
    }

    private void initVariables() {
        toolbar = findViewById(R.id.toolbar);
        rvRegister = findViewById(R.id.rv_register);
        pbRegister = findViewById(R.id.pb_register);

        regList = new ArrayList<>();

        regAdapter = new AxiRegisterAdapter(regList, this);

        //pref = getSharedPreferences("Pref", Context.MODE_PRIVATE);
        //if (!pref.getString("branch_id", "").isEmpty())
        //    branchId = Integer.valueOf(pref.getString("branch_id", ""));

        branchId = Integer.valueOf(session.getBranchId());

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance();
        jsonApi = retrofit.create(NetworkInterface.class);
    }

    private void initToolbar() {
        toolbar.setTitle("AXI Terdaftar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initRecyclerView() {
        LinearLayoutManager lm = new LinearLayoutManager(this);

        rvRegister.setLayoutManager(lm);
        rvRegister.setHasFixedSize(true);
        rvRegister.setAdapter(regAdapter);
    }

    private void getRegistration() {
        mCompositeDisposable.add(
                jsonApi.getRegistration(apiKey, branchId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessGetRegistration, this::onError)
        );
    }

    private void onSuccessGetRegistration(AxiRegister data) {
        if (data != null && data.getData() != null && data.getData().size() > 0) {
            regList.clear();
            regList.addAll(data.getData());
            regAdapter.notifyDataSetChanged();
        }
        pbRegister.setVisibility(View.GONE);
    }

    private void onError(Throwable t) {
        Tools.showLongToast(this, t.getMessage());
        pbRegister.setVisibility(View.GONE);
    }

    /**
     * private void populateData() {
     * for (int i = 0; i < 10; i++) {
     * AxiRegister reg = new AxiRegister();
     * reg.setMonth("November 201" + i);
     * reg.setCount(i * 20);
     * regList.add(reg);
     * }
     * }
     */

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
