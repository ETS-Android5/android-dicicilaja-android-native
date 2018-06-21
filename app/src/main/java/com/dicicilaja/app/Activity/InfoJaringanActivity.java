package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.app.API.Client.NewRetrofitClient;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceInfoJaringan;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemInfoJaringan.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemInfoJaringan.InfoJaringan;
import com.dicicilaja.app.Adapter.InfoJaringanAxiAdapter;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoJaringanActivity extends AppCompatActivity {

    TextView title_info, title_rb, value_rb, title_jaringan, value_jaringan, title_daftar, value_daftar;
    String apiKey;
    List<Data> infoJaringan;
    String total_rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jaringan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        title_info = findViewById(R.id.title_info);
        title_rb = findViewById(R.id.title_rb);
        value_rb = findViewById(R.id.value_rb);
        title_jaringan = findViewById(R.id.title_jaringan);
        value_jaringan = findViewById(R.id.value_jaringan);
        title_daftar = findViewById(R.id.title_daftar);
        value_daftar = findViewById(R.id.value_daftar);
        total_rb = getIntent().getStringExtra("total_rb");

        value_rb.setText(total_rb);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_info.setTypeface(opensans_bold);
        title_rb.setTypeface(opensans_bold);
        value_rb.setTypeface(opensans_bold);
        title_jaringan.setTypeface(opensans_bold);
        value_jaringan.setTypeface(opensans_bold);
        title_daftar.setTypeface(opensans_bold);
        value_daftar.setTypeface(opensans_bold);

        value_daftar.setText("1");

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        InterfaceInfoJaringan apiService =
                RetrofitClient.getClient().create(InterfaceInfoJaringan.class);

        final RecyclerView recyclerView =  findViewById(R.id.recycler_rb);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        Call<InfoJaringan> call2 = apiService.getInfoJaringan(apiKey);
        call2.enqueue(new Callback<InfoJaringan>() {
            @Override
            public void onResponse(Call<InfoJaringan> call, Response<InfoJaringan> response) {
//                progress.dismiss();
                infoJaringan = response.body().getData();
                recyclerView.setAdapter(new InfoJaringanAxiAdapter(infoJaringan, R.layout.card_rb, getBaseContext()));
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, final int position) {
                        Intent intent = new Intent(getBaseContext(), RekanBisnisActivity.class);
                        intent.putExtra("level", value_daftar.getText().toString());
                        intent.putExtra("EXTRA_REQUEST_ID", infoJaringan.get(position).getIdAxi().toString());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClick(View view, int position) {
                    }
                }));
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<InfoJaringan> call, Throwable t) {
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
