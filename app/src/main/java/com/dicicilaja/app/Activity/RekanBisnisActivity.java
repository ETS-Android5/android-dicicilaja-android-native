package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceRekanBisnis;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRekanBisnis.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRekanBisnis.InfoJaringan;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRekanBisnis.RekanBisnis;
import com.dicicilaja.app.Adapter.RekanBisnisAdapter;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RekanBisnisActivity extends AppCompatActivity {

    TextView title_info, title_nama, value_nama, title_axi, value_axi, title_reward, value_reward, title_trip, value_trip, title_hp, value_hp, title_daftar, value_daftar;
    String apiKey;
    String id;
    Integer level;
    Integer newlevel;
    Data data;
    List<InfoJaringan> rekanBisnis;
    List<com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRekanBisnis.InfoJaringan> infoJaringans;

    RelativeLayout title_rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekan_bisnis);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();
        id = getIntent().getStringExtra("EXTRA_REQUEST_ID");
        level = Integer.parseInt(getIntent().getStringExtra("level"));

        newlevel = level + 1;

        getSupportActionBar().setTitle("Rekan Bisnis " + level);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        title_info = findViewById(R.id.title_info);
        title_nama = findViewById(R.id.title_nama);
        value_nama = findViewById(R.id.value_nama);
        title_axi = findViewById(R.id.title_axi);
        value_axi = findViewById(R.id.value_axi);
        title_reward = findViewById(R.id.title_reward);
        value_reward = findViewById(R.id.value_reward);
        title_trip = findViewById(R.id.title_trip);
        value_trip = findViewById(R.id.value_trip);
        title_hp = findViewById(R.id.title_hp);
        value_hp = findViewById(R.id.value_hp);
        title_daftar = findViewById(R.id.title_daftar);
        value_daftar = findViewById(R.id.value_daftar);
        title_rb = findViewById(R.id.title_rb);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_info.setTypeface(opensans_bold);
        title_nama.setTypeface(opensans_bold);
        value_nama.setTypeface(opensans_bold);
        title_axi.setTypeface(opensans_bold);
        value_axi.setTypeface(opensans_bold);
        title_reward.setTypeface(opensans_bold);
        value_reward.setTypeface(opensans_bold);
        title_trip.setTypeface(opensans_bold);
        value_trip.setTypeface(opensans_bold);
        title_hp.setTypeface(opensans_bold);
        value_hp.setTypeface(opensans_bold);
        title_daftar.setTypeface(opensans_bold);
        value_daftar.setTypeface(opensans_bold);
        value_daftar.setText(String.valueOf(newlevel));

        final RecyclerView recyclerView = findViewById(R.id.recycler_rb);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        value_hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" + value_hp.getText() ));
                startActivity(call);
            }
        });

        if(level == 10) {
            title_rb.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }else{
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Sedang memuat data...");
            progress.setCanceledOnTouchOutside(false);
            progress.show();

            InterfaceRekanBisnis apiService =
                    RetrofitClient.getClient().create(InterfaceRekanBisnis.class);



            Call<RekanBisnis> call2 = apiService.getRekanBisnis(apiKey,id);
            call2.enqueue(new Callback<RekanBisnis>() {
                @Override
                public void onResponse(Call<RekanBisnis> call, Response<RekanBisnis> response) {
                    rekanBisnis = response.body().getData().getInfoJaringan();
                    data = response.body().getData();
                    value_nama.setText(data.getNamaMentor());
                    value_axi.setText(data.getAxiRefferal());
                    value_reward.setText(data.getPointReward());
                    value_trip.setText(data.getPointTrip());
                    value_hp.setText(data.getNoHp());
                    recyclerView.setAdapter(new RekanBisnisAdapter(rekanBisnis, R.layout.card_rb, getBaseContext()));
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                        @Override
                        public void onClick(View view, final int position) {
                            Intent intent = new Intent(getBaseContext(), RekanBisnisActivity.class);
                            intent.putExtra("level", value_daftar.getText().toString());
                            intent.putExtra("EXTRA_REQUEST_ID", rekanBisnis.get(position).getIdAxi().toString());
                            startActivity(intent);

                        }

                        @Override
                        public void onLongClick(View view, int position) {
                        }
                    }));
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<RekanBisnis> call, Throwable t) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
