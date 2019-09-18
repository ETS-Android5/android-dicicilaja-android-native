package com.dicicilaja.app.OrderIn.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.OrderIn.Network.ApiClient;
import com.dicicilaja.app.OrderIn.Network.ApiService;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class DataCalonPeminjamActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_nama_lengkap)
    EditText etNamaLengkap;
    @BindView(R.id.et_nomor_ktp)
    EditText etNomorKtp;
    @BindView(R.id.et_alamat_email)
    EditText etAlamatEmail;
    @BindView(R.id.et_no_handphone)
    EditText etNoHandphone;
    @BindView(R.id.spinnerProvinsi)
    SearchableSpinner spinnerProvinsi;
    @BindView(R.id.layoutProvinsi)
    TextInputLayout layoutProvinsi;
    @BindView(R.id.layoutKota)
    TextInputLayout layoutKota;
    @BindView(R.id.et_kecamatan)
    EditText etKecamatan;
    @BindView(R.id.et_alamat)
    EditText etAlamat;
    @BindView(R.id.et_kode_pos)
    EditText etKodePos;
    @BindView(R.id.spinnerKota)
    SearchableSpinner spinnerKota;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.save)
    Button save;

    ApiService apiService;

    final List<String> PROVINSI_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> PROVINSI_DATA = new HashMap<Integer, String>();
    final List<String> KOTA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> KOTA_DATA = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_calon_peminjam);
        ButterKnife.bind(this);

        initToolbar();
        initAction();
//        initLoadData();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data Calon Peminjam");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
    }

    private void initAction() {
        //Initialize
        progressBar.setVisibility(View.GONE);
        spinnerKota.setEnabled(false);

        clearProvinsi();
        clearKota();

        //Network
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    private void clearProvinsi() {
        PROVINSI_DATA.clear();
        PROVINSI_ITEMS.clear();
        PROVINSI_DATA.put(0, "0");
        PROVINSI_ITEMS.add("Pilih Provinsi");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, PROVINSI_ITEMS);
        provinsi_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvinsi.setAdapter(provinsi_adapter);
        spinnerProvinsi.setTitle("");
        spinnerProvinsi.setPositiveButton("OK");
    }

    private void clearKota() {
        KOTA_DATA.clear();
        KOTA_ITEMS.clear();
        KOTA_DATA.put(0, "0");
        KOTA_ITEMS.add("Pilih Kota");
        ArrayAdapter<String> kota_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KOTA_ITEMS);
        kota_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKota.setAdapter(kota_adapter);
        spinnerKota.setTitle("");
        spinnerKota.setPositiveButton("OK");
    }

//    private void initLoadData() {
//        initAction();
//        progressBar.setVisibility(View.VISIBLE);
//        Call<AreaBranchOffice> call = apiService.getArea();
//        call.enqueue(new Callback<AreaBranchOffice>() {
//            @Override
//            public void onResponse(Call<AreaBranchOffice> call, Response<AreaBranchOffice> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        dataItems = response.body().getData();
//                        if (dataItems.size() > 0) {
//                            recyclerArea.setAdapter(new AreaBranchOfficeAdapter(dataItems, getBaseContext()));
//                            recyclerArea.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerArea, new ClickListener() {
//                                @Override
//                                public void onClick(View view, final int position) {
//                                    Intent intent = new Intent(getBaseContext(), CityBranchOfficeActivity.class);
//                                    intent.putExtra("area", dataItems.get(position).getAttributes().getRegion());
//                                    startActivity(intent);
//                                }
//
//                                @Override
//                                public void onLongClick(View view, int position) {
//                                }
//                            }));
//                            progressBar.setVisibility(View.GONE);
//                        } else {
//                            progressBar.setVisibility(View.GONE);
//                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AreaBranchOfficeActivity.this);
//                            alertDialog.setTitle("Perhatian");
//                            alertDialog.setMessage("Data area belum tersedia, silahkan coba beberapa saat lagi.");
//
//                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
//                                    startActivity(getIntent());
//                                }
//                            });
//                            alertDialog.show();
//                        }
//                    } catch (Exception ex) { }
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(AreaBranchOfficeActivity.this);
//                    alertDialog.setTitle("Perhatian");
//                    alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                            startActivity(getIntent());
//                        }
//                    });
//                    alertDialog.show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AreaBranchOffice> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AreaBranchOfficeActivity.this);
//                alertDialog.setTitle("Perhatian");
//                alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                        startActivity(getIntent());
//                    }
//                });
//                alertDialog.show();
//            }
//        });
//
//
//        Call<AreaSimulasi> areaSimulasiCall = apiService2.getAreaSimulasi(true);
//        areaSimulasiCall.enqueue(new Callback<AreaSimulasi>() {
//            @Override
//            public void onResponse(Call<AreaSimulasi> call, Response<AreaSimulasi> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        if (response.body().getData().size() > 0) {
//                            for (int i = 0; i < response.body().getData().size(); i++) {
//                                AREA_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                                AREA_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
//                            }
//                            progressBar0.setVisibility(View.GONE);
//                        } else {
//                            clearArea();
//                            progressBar0.setVisibility(View.GONE);
//                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
//                            alertDialog.setTitle("Perhatian");
//                            alertDialog.setMessage("Data area belum tersedia, silahkan coba beberapa saat lagi.");
//
//                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
//                                    startActivity(getIntent());
//                                }
//                            });
//                            alertDialog.show();
//                        }
//                    } catch (Exception ex) { }
//
//                    ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
//                    area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinnerArea.setAdapter(area_adapter);
//                    spinnerArea.setTitle("");
//                    spinnerArea.setPositiveButton("OK");
//                } else {
//                    clearArea();
//                    progressBar0.setVisibility(View.GONE);
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
//                    alertDialog.setTitle("Perhatian");
//                    alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                            startActivity(getIntent());
//                        }
//                    });
//                    alertDialog.show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AreaSimulasi> call, Throwable t) {
//                progressBar0.setVisibility(View.GONE);
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
//                alertDialog.setTitle("Perhatian");
//                alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                        startActivity(getIntent());
//                    }
//                });
//                alertDialog.show();
//            }
//        });
//    }

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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
        alertDialog.setTitle("Perhatian");
        alertDialog.setMessage("Apakah data yang Anda masukan sudah benar?");

        alertDialog.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();
    }
}