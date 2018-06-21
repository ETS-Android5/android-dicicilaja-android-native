package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dicicilaja.app.API.Client.JodyRetrofitClient;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceStatistics;
import com.dicicilaja.app.API.Item.Statistics.Data;
import com.dicicilaja.app.API.Item.Statistics.Statistics;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticActivity extends AppCompatActivity {

    Data statistics;
    String apiKey;
    TextView title_point, title_pengajuan_masuk, title_pengajuan_diproses, title_semua_pengajuan, title_terkirim, title_verifikasi, title_proses, title_survey, title_pending, title_analisa_kredit, title_ditolak, title_pencairan;
    TextView jumlah_pengajuan_masuk, jumlah_pengajuan_diproses, jumlah_terkirim, jumlah_verifikasi, jumlah_proses, jumlah_survey, jumlah_pending, jumlah_analisa_kredit, jumlah_ditolak, jumlah_pencairan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        title_point = findViewById(R.id.title_point);
        title_pengajuan_masuk = findViewById(R.id.title_pengajuan_masuk);
        title_pengajuan_diproses = findViewById(R.id.title_pengajuan_diproses);
        title_semua_pengajuan = findViewById(R.id.title_semua_pengajuan);
        title_terkirim = findViewById(R.id.title_terkirim);
        title_verifikasi = findViewById(R.id.title_verifikasi);
        title_proses = findViewById(R.id.title_proses);
        title_survey = findViewById(R.id.title_survey);
        title_pending = findViewById(R.id.title_pending);
        title_analisa_kredit = findViewById(R.id.title_analisa_kredit);
        title_ditolak = findViewById(R.id.title_ditolak);
        title_pencairan = findViewById(R.id.title_pencairan);

        jumlah_pengajuan_masuk = findViewById(R.id.jumlah_pengajuan_masuk);
        jumlah_pengajuan_diproses = findViewById(R.id.jumlah_pengajuan_diproses);
        jumlah_terkirim = findViewById(R.id.jumlah_terkirim);
        jumlah_verifikasi = findViewById(R.id.jumlah_verifikasi);
        jumlah_proses = findViewById(R.id.jumlah_proses);
        jumlah_survey = findViewById(R.id.jumlah_survey);
        jumlah_pending = findViewById(R.id.jumlah_pending);
        jumlah_analisa_kredit = findViewById(R.id.jumlah_analisa_kredit);
        jumlah_ditolak = findViewById(R.id.jumlah_ditolak);
        jumlah_pencairan = findViewById(R.id.jumlah_pencairan);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_point.setTypeface(opensans_bold);
        title_pengajuan_masuk.setTypeface(opensans_semibold);
        title_pengajuan_diproses.setTypeface(opensans_semibold);
        title_semua_pengajuan.setTypeface(opensans_bold);
        title_terkirim.setTypeface(opensans_semibold);
        title_verifikasi.setTypeface(opensans_semibold);
        title_proses.setTypeface(opensans_semibold);
        title_survey.setTypeface(opensans_semibold);
        title_pending.setTypeface(opensans_semibold);
        title_analisa_kredit.setTypeface(opensans_semibold);
        title_ditolak.setTypeface(opensans_semibold);
        title_pencairan.setTypeface(opensans_semibold);

        jumlah_pengajuan_masuk.setTypeface(opensans_bold);
        jumlah_pengajuan_diproses.setTypeface(opensans_bold);
        jumlah_terkirim.setTypeface(opensans_bold);
        jumlah_verifikasi.setTypeface(opensans_bold);
        jumlah_proses.setTypeface(opensans_bold);
        jumlah_survey.setTypeface(opensans_bold);
        jumlah_pending.setTypeface(opensans_bold);
        jumlah_analisa_kredit.setTypeface(opensans_bold);
        jumlah_ditolak.setTypeface(opensans_bold);
        jumlah_pencairan.setTypeface(opensans_bold);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        InterfaceStatistics apiService =
                RetrofitClient.getClient().create(InterfaceStatistics.class);

        Call<Statistics> call2 = apiService.statistics(apiKey);
        call2.enqueue(new Callback<Statistics>() {
            @Override
            public void onResponse(Call<Statistics> call, Response<Statistics> response) {

                List<Data> statistics = response.body().getData();

                jumlah_pengajuan_masuk.setText(String.valueOf(statistics.get(0).getPengajuanMasuk()));
                jumlah_pengajuan_diproses.setText(String.valueOf(statistics.get(0).getPengajuanDiproses()));
                jumlah_terkirim.setText(String.valueOf(statistics.get(0).getTerkirim()));
                jumlah_verifikasi.setText(String.valueOf(statistics.get(0).getVerifikasi()));
                jumlah_proses.setText(String.valueOf(statistics.get(0).getProses()));
                jumlah_survey.setText(String.valueOf(statistics.get(0).getSurvey()));
//                jumlah_pending.setText(String.valueOf(statistics.get(0).getPending()));
                jumlah_analisa_kredit.setText(String.valueOf(statistics.get(0).getAnalisaKredit()));
                jumlah_ditolak.setText(String.valueOf(statistics.get(0).getDitolak()));
                jumlah_pencairan.setText(String.valueOf(statistics.get(0).getPencairan()));
                progress.dismiss();

            }

            @Override
            public void onFailure(Call<Statistics> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(StatisticActivity.this);
                alertDialog.setMessage("Koneksi internet tidak ditemukan" + t.getMessage());

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
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
