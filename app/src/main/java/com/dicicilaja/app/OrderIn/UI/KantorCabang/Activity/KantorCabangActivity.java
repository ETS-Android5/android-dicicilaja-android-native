package com.dicicilaja.app.OrderIn.UI.KantorCabang.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_data_calon_pinjaman)
    TextView txtDataCalonPinjaman;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.card_rekomendasi)
    LinearLayout cardRekomendasi;
    @BindView(R.id.card_terdekat)
    LinearLayout cardTerdekat;
    @BindView(R.id.card_lainnya)
    LinearLayout cardLainnya;
    @BindView(R.id.recycler_rekomendasi)
    RecyclerView recyclerRekomendasi;
    @BindView(R.id.recycler_terdekat)
    RecyclerView recyclerTerdekat;
    @BindView(R.id.recycler_lainnya)
    RecyclerView recyclerLainnya;

    ApiService2 apiService;
    List<DataItem> cabangRekomendasiList;

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

        //Network
        apiService = ApiClient2.getClient().create(ApiService2.class);

        hideRekomendasi();
        hideTerdekat();
        hideLainnya();
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
        Call<CabangRekomendasi> call = apiService.getCabangRekomendasi(10);
        call.enqueue(new Callback<CabangRekomendasi>() {
            @Override
            public void onResponse(Call<CabangRekomendasi> call, Response<CabangRekomendasi> response) {
                if (response.isSuccessful()) {
                    cabangRekomendasiList = response.body().getData();
                    if (cabangRekomendasiList.size() == 0) {
                        hideRekomendasi();
                    } else {
                        showRekomendasi();
                        recyclerRekomendasi.setAdapter(new NotifAdapter(notifs, R.layout.card_notif, getBaseContext()));

                        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                            @Override
                            public void onClick(View view, final int position) {
                                Intent intent = new Intent(getBaseContext(), DetailRequestActivity.class);
                                intent.putExtra("EXTRA_REQUEST_ID", notifs.get(position).getTransaction_id().toString());
                                intent.putExtra("STATUS", true);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                            }
                        }));
                    }
                    progress.dismiss();
                }
                progress.dismiss();

            }

            @Override
            public void onFailure(Call<CabangRekomendasi> call, Throwable t) {
                progress.dismiss();
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
