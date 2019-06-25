package com.dicicilaja.app.BranchOffice.UI.BranchOffice.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.BranchOffice.Data.BranchOffice.BranchOffice;
import com.dicicilaja.app.BranchOffice.Data.BranchOffice.DataItem;
import com.dicicilaja.app.BranchOffice.Network.ApiClient;
import com.dicicilaja.app.BranchOffice.Network.ApiService;
import com.dicicilaja.app.BranchOffice.UI.AreaBranchOffice.Activity.AreaBranchOfficeActivity;
import com.dicicilaja.app.BranchOffice.UI.BranchOffice.Adapter.BranchOfficeAdapter;
import com.dicicilaja.app.BranchOffice.UI.DetailBranchOffice.DetailBranchOfficeActivity;
import com.dicicilaja.app.Listener.ClickListener;
import com.dicicilaja.app.Listener.RecyclerTouchListener;
import com.dicicilaja.app.R;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class BranchOfficeActivity extends AppCompatActivity {

    List<DataItem> dataItems;
    ApiService apiService;
    String kota;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_branch)
    RecyclerView recyclerBranch;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title_area)
    TextView titleArea;
    @BindView(R.id.card_branch)
    RelativeLayout cardBranch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_office);
        ButterKnife.bind(this);

        initToolbar();
        initAction();
        initLoadData();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kantor Cabang Adira");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
    }

    private void initAction() {
        kota = getIntent().getStringExtra("city");
        titleArea.setText(kota);
        progressBar.setVisibility(View.GONE);
        recyclerBranch.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    private void initLoadData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<BranchOffice> call = apiService.getBranch(kota);
        call.enqueue(new Callback<BranchOffice>() {
            @Override
            public void onResponse(Call<BranchOffice> call, Response<BranchOffice> response) {
                if (response.isSuccessful()) {
                    try {
                        dataItems = response.body().getData();
                        if (dataItems.size() > 0) {
                            recyclerBranch.setAdapter(new BranchOfficeAdapter(dataItems, getBaseContext()));
                            recyclerBranch.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerBranch, new ClickListener() {
                                @Override
                                public void onClick(View view, final int position) {
                                    Intent intent = new Intent(getBaseContext(), DetailBranchOfficeActivity.class);
                                    intent.putExtra("nama", dataItems.get(position).getAttributes().getNama());
                                    startActivity(intent);
                                }

                                @Override
                                public void onLongClick(View view, int position) {
                                }
                            }));
                            progressBar.setVisibility(View.GONE);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(BranchOfficeActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data cabang belum tersedia, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    } catch (Exception ex) {
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(BranchOfficeActivity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data cabang gagal dipanggil, silahkan coba beberapa saat lagi.");

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
            public void onFailure(Call<BranchOffice> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BranchOfficeActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data cabang gagal dipanggil, silahkan coba beberapa saat lagi.");

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

    @OnClick(R.id.card_branch)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), AreaBranchOfficeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
