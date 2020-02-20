package com.dicicilaja.app.InformAXI.ui.incentive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.dicicilaja.app.InformAXI.model.Incentive;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.dicicilaja.app.InformAXI.utils.Constants.BIKE;
import static com.dicicilaja.app.InformAXI.utils.Constants.CAR;
import static com.dicicilaja.app.InformAXI.utils.Constants.INCENTIVE;
import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE_ID;

public class IncentiveActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvAppreciate;
    private TextView tvBtl;
    private TextView tvGroup;
    private TextView tvYearly;
    private TextView tvLoyalty;
    private TextView tvMentor;

    private Incentive data = null;

    private String incentiveType = null;
    private int id = -1;

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incentive);

        initVariables();
        initToolbar();
        getIncentive();
    }

    private void initVariables() {
        toolbar = findViewById(R.id.toolbar);
        tvAppreciate = findViewById(R.id.tv_appreciate_incentive);
        tvBtl = findViewById(R.id.tv_btl_incentive);
        tvGroup = findViewById(R.id.tv_group_incentive);
        tvYearly = findViewById(R.id.tv_yearly_incentive);
        tvLoyalty = findViewById(R.id.tv_loyalty_incentive);
        tvMentor = findViewById(R.id.tv_mentor_incentive);

        if (getIntent().getExtras() != null) {
            switch (getIntent().getStringExtra(INCENTIVE)) {
                case CAR:
                    incentiveType = "mobil";
                    break;
                case BIKE:
                    incentiveType = "motor";
                    break;
            }

            id = getIntent().getIntExtra(PROFILE_ID, -1);
        }

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance();
        jsonApi = retrofit.create(NetworkInterface.class);
    }

    private void initToolbar() {
        if (getIntent().getExtras() != null) {
            switch (getIntent().getStringExtra(INCENTIVE)) {
                case CAR:
                    toolbar.setTitle("Insentif Mobil");
                    break;
                case BIKE:
                    toolbar.setTitle("Insentif Motor");
                    break;
            }
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getIncentive() {
        mCompositeDisposable.add(
                jsonApi.getBikeIncentive(id, incentiveType)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessGetIncentive, this::onError)
        );
    }

    private void onSuccessGetIncentive(Incentive data) {
        if (data != null) {
            this.data = data;
            populateData();
        }
    }

    private void onError(Throwable t) {
        Tools.showLongToast(this, t.getMessage());
    }

    private void populateData() {
        if (data != null) {
            Incentive.DataBean data = this.data.getData();
            if (data.getApresiasi() > -1)
                tvAppreciate.setText(String.valueOf(data.getApresiasi()));

            if (data.getBtl() > -1)
                tvBtl.setText(String.valueOf(data.getBtl()));

            if (data.getGroup() > -1)
                tvGroup.setText(String.valueOf(data.getGroup()));

            if (data.getLoyalti() > -1)
                tvLoyalty.setText(String.valueOf(data.getLoyalti()));

            if (data.getTahunan() > -1)
                tvYearly.setText(String.valueOf(data.getTahunan()));

            if (data.getMentor() > -1)
                tvMentor.setText(String.valueOf(data.getMentor()));
        }
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
