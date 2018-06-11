package com.dicicilaja.dicicilaja.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProgramMaxi;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProgramMaxi.Data;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProgramMaxi.ProgramMaxi;
import com.dicicilaja.dicicilaja.Adapter.ProgramMaxiAllAdapter;
import com.dicicilaja.dicicilaja.Listener.ClickListener;
import com.dicicilaja.dicicilaja.Listener.RecyclerTouchListener;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProgramMaxiActivity extends AppCompatActivity {
    List<Data> programMaxi;

    String apiKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_program_maxi);

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

        InterfaceProgramMaxi apiService3 =
                RetrofitClient.getClient().create(InterfaceProgramMaxi.class);

        final RecyclerView recyclerView2 =  findViewById(R.id.recycler_program);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        Call<ProgramMaxi> call5 = apiService3.getProgramMaxi(apiKey);
        call5.enqueue(new Callback<ProgramMaxi>() {
            @Override
            public void onResponse(Call<ProgramMaxi> call, Response<ProgramMaxi> response) {
                programMaxi = response.body().getData();

                recyclerView2.setAdapter(new ProgramMaxiAllAdapter(programMaxi, R.layout.card_program, getBaseContext()));
                recyclerView2.setNestedScrollingEnabled(false);
                recyclerView2.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView2, new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Intent intent = new Intent(getBaseContext(), ProductMaxiActivity.class);
                        intent.putExtra("EXTRA_REQUEST_ID", programMaxi.get(position).getId().toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ProgramMaxi> call, Throwable t) {
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
}
