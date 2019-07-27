package com.dicicilaja.app.BusinessReward.ui.Transaction.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.ClaimRewards;
import com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward.Datum;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.Transaction.adapter.ListClaimRewardAdapter;
import com.dicicilaja.app.Adapter.TransaksiAdapter;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

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

    SessionManager session;

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

        session = new SessionManager(getBaseContext());

        recyclerTransaksi.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerTransaksi.setHasFixedSize(true);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<ClaimRewards> call = apiService.getClaim(Integer.parseInt(session.getAxiId()));
        call.enqueue(new Callback<ClaimRewards>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<ClaimRewards> call, Response<ClaimRewards> response) {
                final List<Datum> dataItems = response.body().getData();
                Log.d("Cek1", "" + response.body().getData());

                if (dataItems.size() == 0) {
                    dataTrans.setVisibility(View.GONE);
                    warn.setVisibility(View.VISIBLE);
                }else{
                    recyclerTransaksi.setAdapter(new ListClaimRewardAdapter(dataItems, getBaseContext()));
                }
            }

            @Override
            public void onFailure(Call<ClaimRewards> call, Throwable t) {

            }
        });
    }
}
