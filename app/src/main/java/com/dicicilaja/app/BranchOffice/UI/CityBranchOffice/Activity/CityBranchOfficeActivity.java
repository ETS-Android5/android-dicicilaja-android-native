package com.dicicilaja.app.BranchOffice.UI.CityBranchOffice.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dicicilaja.app.BranchOffice.Data.KotaBranchOffice.DataItem;
import com.dicicilaja.app.BranchOffice.Data.KotaBranchOffice.KotaBranchOffice;
import com.dicicilaja.app.BranchOffice.Network.ApiClient;
import com.dicicilaja.app.BranchOffice.Network.ApiService;
import com.dicicilaja.app.BranchOffice.UI.BranchOffice.Activity.BranchOfficeActivity;
import com.dicicilaja.app.BranchOffice.UI.CityBranchOffice.Adapter.CityBranchOfficeAdapter;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.R;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CityBranchOfficeActivity extends AppCompatActivity {

    List<DataItem> dataItems;
    ApiService apiService;
    String kota;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_city)
    RecyclerView recyclerCity;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_branch);
        ButterKnife.bind(this);

        initToolbar();
        initAction();
        initLoadData();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pilih Kota");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
    }

    private void initAction() {
        kota = getIntent().getStringExtra("city");
        progressBar.setVisibility(View.GONE);
        recyclerCity.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    private void initLoadData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<KotaBranchOffice> call = apiService.getKota(kota);
        call.enqueue(new Callback<KotaBranchOffice>() {
            @Override
            public void onResponse(Call<KotaBranchOffice> call, Response<KotaBranchOffice> response) {
                if (response.isSuccessful()) {
                    try {
                        dataItems = response.body().getData();
                        if (dataItems.size() > 0) {
                            recyclerCity.setAdapter(new CityBranchOfficeAdapter(dataItems, getBaseContext()));
                            recyclerCity.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerCity, new ClickListener() {
                                @Override
                                public void onClick(View view, final int position) {
                                    Intent intent = new Intent(getBaseContext(), BranchOfficeActivity.class);
                                    intent.putExtra("city", dataItems.get(position).getAttributes().getKota());
                                    startActivity(intent);
                                }

                                @Override
                                public void onLongClick(View view, int position) {
                                }
                            }));
                            progressBar.setVisibility(View.GONE);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(CityBranchOfficeActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data kota belum tersedia, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    } catch (Exception ex) { }
                } else {
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(CityBranchOfficeActivity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(getIntent());
                        }
                    });
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<KotaBranchOffice> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CityBranchOfficeActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
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
