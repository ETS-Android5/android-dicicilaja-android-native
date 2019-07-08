package com.dicicilaja.app.BranchOffice.UI.BranchOffice.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
    String kota, fax1, fax2, fax3, nama, alamat, link;
    String phone11, phone12, phone13, phone21, phone22, phone23, phone31, phone32, phone33;
    int phone1size, phone2size, phone3size;

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
        Call<BranchOffice> call = apiService.getBranch(kota.toLowerCase());
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

                                    nama = "-";
                                    alamat = "-";
                                    kota = "-";
                                    fax1 = "-";
                                    fax2 = "-";
                                    fax3 = "-";

                                    try {
                                        nama = dataItems.get(position).getAttributes().getNama();
                                        alamat = dataItems.get(position).getAttributes().getDetail().getAlamat();
                                        kota = dataItems.get(position).getAttributes().getDetail().getKota();
                                        fax1 = dataItems.get(position).getAttributes().getDetail().getFax1();
                                        fax2 = dataItems.get(position).getAttributes().getDetail().getFax2();
                                        fax3 = dataItems.get(position).getAttributes().getDetail().getFax3();
                                        link = dataItems.get(position).getAttributes().getLokasi();
                                        phone1size = dataItems.get(position).getAttributes().getDetail().getTelp1().size();
                                        phone2size = dataItems.get(position).getAttributes().getDetail().getTelp2().size();
                                        phone3size = dataItems.get(position).getAttributes().getDetail().getTelp3().size();
                                        phone11 = dataItems.get(position).getAttributes().getDetail().getTelp1().get(0);
                                        phone12 = dataItems.get(position).getAttributes().getDetail().getTelp1().get(1);
                                        phone13 = dataItems.get(position).getAttributes().getDetail().getTelp1().get(2);
                                        phone21 = dataItems.get(position).getAttributes().getDetail().getTelp2().get(0);
                                        phone22 = dataItems.get(position).getAttributes().getDetail().getTelp2().get(1);
                                        phone23 = dataItems.get(position).getAttributes().getDetail().getTelp2().get(2);
                                        phone31 = dataItems.get(position).getAttributes().getDetail().getTelp3().get(0);
                                        phone32 = dataItems.get(position).getAttributes().getDetail().getTelp3().get(1);
                                        phone33 = dataItems.get(position).getAttributes().getDetail().getTelp3().get(2);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    Intent intent = new Intent(getBaseContext(), DetailBranchOfficeActivity.class);
                                    intent.putExtra("nama", nama);
                                    intent.putExtra("alamat", alamat);
                                    intent.putExtra("kota", kota);
                                    intent.putExtra("fax1", String.valueOf(fax1));
                                    intent.putExtra("fax2", String.valueOf(fax2));
                                    intent.putExtra("fax3", String.valueOf(fax3));
                                    intent.putExtra("link", String.valueOf(link));

                                    intent.putExtra("phone1size", String.valueOf(phone1size));
                                    intent.putExtra("phone2size", String.valueOf(phone2size));
                                    intent.putExtra("phone3size", String.valueOf(phone3size));

                                    intent.putExtra("phone11", String.valueOf(phone11));
                                    intent.putExtra("phone12", String.valueOf(phone12));
                                    intent.putExtra("phone13", String.valueOf(phone13));

                                    intent.putExtra("phone21", String.valueOf(phone21));
                                    intent.putExtra("phone22", String.valueOf(phone22));
                                    intent.putExtra("phone23", String.valueOf(phone23));

                                    intent.putExtra("phone31", String.valueOf(phone31));
                                    intent.putExtra("phone32", String.valueOf(phone32));
                                    intent.putExtra("phone33", String.valueOf(phone33));

                                    Log.d("TAGTAG", "phonesize 1: " + String.valueOf(phone1size));
                                    Log.d("TAGTAG", "phonesize 2: " + String.valueOf(phone2size));
                                    Log.d("TAGTAG", "phonesize 3: " + String.valueOf(phone3size));

                                    Log.d("TAGTAG", "phone 1-1: " + String.valueOf(phone11));
                                    Log.d("TAGTAG", "phone 1-2: " + String.valueOf(phone12));
                                    Log.d("TAGTAG", "phone 1-3: " + String.valueOf(phone13));

                                    Log.d("TAGTAG", "phone 2-1: " + String.valueOf(phone21));
                                    Log.d("TAGTAG", "phone 2-2: " + String.valueOf(phone22));
                                    Log.d("TAGTAG", "phone 2-3: " + String.valueOf(phone23));

                                    Log.d("TAGTAG", "phone 3-1: " + String.valueOf(phone31));
                                    Log.d("TAGTAG", "phone 3-2: " + String.valueOf(phone32));
                                    Log.d("TAGTAG", "phone 3-3: " + String.valueOf(phone33));


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
                            alertDialog.setMessage("Data kantor cabang belum tersedia, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(BranchOfficeActivity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data kantor cabang gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                alertDialog.setMessage("Data kantor cabang gagal dipanggil, silahkan coba beberapa saat lagi.");

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
