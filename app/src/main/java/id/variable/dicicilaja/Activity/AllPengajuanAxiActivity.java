package id.variable.dicicilaja.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfacePengajuanAxi;
import id.variable.dicicilaja.API.Item.PengajuanAxi.Datum;
import id.variable.dicicilaja.API.Item.PengajuanAxi.PengajuanAxi;
import id.variable.dicicilaja.Adapter.PengajuanAxiAdapter;
import id.variable.dicicilaja.Adapter.PengajuanAxiAllAdapter;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPengajuanAxiActivity extends AppCompatActivity {

    List<Datum> pengajuan;
    String apiKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pengajuan_axi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        InterfacePengajuanAxi apiService =
                NewRetrofitClient.getClient().create(InterfacePengajuanAxi.class);

        final RecyclerView recyclerView =  findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        Call<PengajuanAxi> call2 = apiService.getPengajuanAxi(apiKey);
        call2.enqueue(new Callback<PengajuanAxi>() {
            @Override
            public void onResponse(Call<PengajuanAxi> call, Response<PengajuanAxi> response) {
                pengajuan = response.body().getData();
                recyclerView.setAdapter(new PengajuanAxiAllAdapter(pengajuan, R.layout.card_pengajuan, getBaseContext()));
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Intent intent = new Intent(getBaseContext(), DetailRequestActivity.class);
                        intent.putExtra("EXTRA_REQUEST_ID", pengajuan.get(position).getId().toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<PengajuanAxi> call, Throwable t) {
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
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
