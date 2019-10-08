package com.dicicilaja.app.OrderIn.UI.KantorCabang.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.CabangRekomendasi;
import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.Datum;
import com.dicicilaja.app.OrderIn.Data.CabangRekomendasi.Included;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.dicicilaja.app.OrderIn.UI.KantorCabang.Adapter.RekomendasiAdapter;
import com.dicicilaja.app.OrderIn.UI.KonfirmasiPengajuanActivity;
import com.dicicilaja.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KantorCabangActivity extends AppCompatActivity {

    ApiService2 apiService;
    List<Datum> dataItems;
    List<Included> dataIncludeds;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_rekomendasi)
    RecyclerView recyclerRekomendasi;
    @BindView(R.id.card_rekomendasi)
    LinearLayout cardRekomendasi;
    @BindView(R.id.recycler_terdekat)
    RecyclerView recyclerTerdekat;
    @BindView(R.id.card_terdekat)
    LinearLayout cardTerdekat;
    @BindView(R.id.recycler_lainnya)
    RecyclerView recyclerLainnya;
    @BindView(R.id.card_lainnya)
    LinearLayout cardLainnya;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kantor_cabang);
        ButterKnife.bind(this);

        initToolbar();
        initAction();
        initLoadData();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pilih Kantor Cabang");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initAction() {
        //Initialize
        progressBar.setVisibility(View.GONE);

        recyclerRekomendasi.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        //Network
        apiService = ApiClient2.getClient().create(ApiService2.class);

//        hideRekomendasi();
//        hideLainnya();
//        hideTerdekat();

    }

    private void hideRekomendasi() {
        cardRekomendasi.setVisibility(View.GONE);
    }

    private void hideTerdekat() {
        cardTerdekat.setVisibility(View.GONE);
    }

    private void hideLainnya() {
        cardLainnya.setVisibility(View.GONE);
    }

    private void showRekomendasi() {
        cardRekomendasi.setVisibility(View.VISIBLE);
    }

    private void showTerdekat() {
        cardTerdekat.setVisibility(View.VISIBLE);
    }

    private void showLainnya() {
        cardLainnya.setVisibility(View.VISIBLE);
    }


    public void initLoadData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<CabangRekomendasi> call = apiService.getCabangRekomendasi("3171090", "village.district.city");
        call.enqueue(new Callback<CabangRekomendasi>() {
            @Override
            public void onResponse(Call<CabangRekomendasi> call, Response<CabangRekomendasi> response) {
                if (response.isSuccessful()) {
                    try {
                        dataItems = response.body().getData();
                        dataIncludeds = response.body().getIncluded();
                        if (dataItems.size() > 0) {
                            showRekomendasi();
                            recyclerRekomendasi.setAdapter(new RekomendasiAdapter(dataItems, dataIncludeds, getBaseContext()));
                            progressBar.setVisibility(View.GONE);
                        } else {
                            hideRekomendasi();
                            progressBar.setVisibility(View.GONE);
                        }

                    } catch (Exception ex) { }
                    progressBar.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CabangRekomendasi> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), KonfirmasiPengajuanActivity.class);
        startActivity(intent);
    }
}
