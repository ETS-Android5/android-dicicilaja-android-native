package com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiDetail;
import com.dicicilaja.app.BusinessReward.dataAPI.area2.Area2;
import com.dicicilaja.app.BusinessReward.dataAPI.cabang.Cabang;
import com.dicicilaja.app.BusinessReward.network.ApiClient2;
import com.dicicilaja.app.BusinessReward.network.ApiService;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihCabangVendorActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerArea)
    SearchableSpinner spinnerArea;
    @BindView(R.id.layoutArea)
    TextInputLayout layoutArea;
    @BindView(R.id.spinnerCabang)
    SearchableSpinner spinnerCabang;
    @BindView(R.id.layoutBrand)
    TextInputLayout layoutBrand;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.progressBar0)
    MaterialProgressBar progressBar0;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.progressBar2)
    MaterialProgressBar progressBar2;
    @BindView(R.id.progressBar3)
    MaterialProgressBar progressBar3;

    final List<String> AREA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> AREA_DATA = new HashMap<Integer, String>();
    final List<String> CABANG_ITEMS = new ArrayList<>();
    final List<String> ALAMAT_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> CABANG_DATA = new HashMap<Integer, String>();

    ApiService apiService, apiService2;
    InterfaceAxiDetail apiServices;
    @BindView(R.id.not_available)
    TextView notAvailable;

    String tipe_objek_id, cabang_text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabang_vendor);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

//        spinnerCabang.setEnabled(false);

        initAction();
        initLoadData();
    }

    private void initAction() {
        progressBar0.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        progressBar2.setVisibility(View.GONE);
        progressBar3.setVisibility(View.GONE);
        notAvailable.setVisibility(View.GONE);
        tipe_objek_id = "1";

        clearArea();
        clearCabang();

        apiService = ApiClient2.getClient().create(ApiService.class);
        apiServices = RetrofitClient.getClient().create(InterfaceAxiDetail.class);
    }

    private void clearArea() {
        AREA_DATA.clear();
        AREA_ITEMS.clear();
        AREA_DATA.put(0, "0");
        AREA_ITEMS.add("PilihArea");
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(area_adapter);
        spinnerArea.setTitle("");
        spinnerArea.setPositiveButton("OK");
    }

    private void clearCabang() {
        CABANG_DATA.clear();
        CABANG_ITEMS.clear();
        ALAMAT_ITEMS.clear();
        CABANG_DATA.put(0, "0");
        CABANG_ITEMS.add("PilihCabang");
        ArrayAdapter<String> cabang_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, CABANG_ITEMS);
        cabang_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCabang.setAdapter(cabang_adapter);
        spinnerCabang.setTitle("");
        spinnerCabang.setPositiveButton("OK");
        spinnerCabang.setEnabled(false);
    }

    private void initLoadData() {
        initAction();
        progressBar0.setVisibility(View.VISIBLE);

        Call<Area2> area = apiService.getArea();
        area.enqueue(new Callback<Area2>() {
            @Override
            public void onResponse(Call<Area2> call, Response<Area2> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getData().size() > 0) {
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                AREA_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                AREA_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                            }
                            progressBar0.setVisibility(View.GONE);
                        } else {
                            clearArea();
                            progressBar0.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(PilihCabangVendorActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data area belum tersedia, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    } catch (Exception ex) {
                        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
                        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerArea.setAdapter(area_adapter);
                        spinnerArea.setTitle("");
                        spinnerArea.setPositiveButton("OK");
                    }
                } else {
                    clearArea();
                    Log.d("gagaleuy", String.valueOf(response.message()));
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(PilihCabangVendorActivity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi.");

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
            public void onFailure(Call<Area2> call, Throwable t) {
                progressBar0.setVisibility(View.GONE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PilihCabangVendorActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi.");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                alertDialog.show();
            }
        });

        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clearCabang();
                if (Integer.parseInt(AREA_DATA.get(spinnerArea.getSelectedItemPosition())) > 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    Call<Cabang> branchCall = apiService.getAllCabang(Integer.parseInt(AREA_DATA.get(spinnerArea.getSelectedItemPosition())));
                    branchCall.enqueue(new Callback<Cabang>() {
                        @Override
                        public void onResponse(Call<Cabang> call, Response<Cabang> response) {
                            if (response.isSuccessful()) {
                                try {
                                    if (response.body().getData().size() > 0) {
                                        for (int i = 0; i < response.body().getData().size(); i++) {
                                            CABANG_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                            CABANG_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                                            ALAMAT_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getAlamat()));
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        clearCabang();
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PilihCabangVendorActivity.this);
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
                                ArrayAdapter<String> cabang_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, CABANG_ITEMS);
                                cabang_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                spinnerCabang.setAdapter(cabang_adapter);
                                spinnerCabang.setTitle("");
                                spinnerCabang.setPositiveButton("OK");
                                spinnerCabang.setEnabled(true);
                            } else {
                                clearCabang();
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PilihCabangVendorActivity.this);
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
                        public void onFailure(Call<Cabang> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(PilihCabangVendorActivity.this);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                notAvailable.setVisibility(View.GONE);
                if (Integer.parseInt(CABANG_DATA.get(spinnerCabang.getSelectedItemPosition())) > 0) {
                    progressBar3.setVisibility(View.GONE);
//                    CABANG_DATA.get(spinnerCabang.getSelectedItemPosition()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @OnClick(R.id.next)
    public void onViewClicked() {
//        Call<Cabang> branchCall = apiService.getAllCabang(Integer.parseInt(AREA_DATA.get(spinnerArea.getSelectedItemPosition())));
        cabang_text = ALAMAT_ITEMS.get(spinnerArea.getSelectedItemPosition());
//        Log.d("cabangtext", cabang_text);
        Log.d("cabangtext", CABANG_ITEMS.get(spinnerCabang.getSelectedItemPosition()));
        Log.d("cabangtext", ALAMAT_ITEMS.get(spinnerCabang.getSelectedItemPosition()));

        Intent intent = new Intent(getBaseContext(),DetailProduct2Activity.class);
        intent.putExtra("CABANGNYA", String.valueOf(CABANG_ITEMS.get(spinnerCabang.getSelectedItemPosition())));
        intent.putExtra("ALAMATNYA", String.valueOf(ALAMAT_ITEMS.get(spinnerCabang.getSelectedItemPosition())));
        intent.putExtra("AREANYA", String.valueOf(AREA_ITEMS.get(spinnerArea.getSelectedItemPosition())));
        intent.putExtra("AREAIDNYA", String.valueOf(AREA_DATA.get(spinnerArea.getSelectedItemPosition())));
        intent.putExtra("CABANGIDNYA", String.valueOf(CABANG_DATA.get(spinnerCabang.getSelectedItemPosition())));
        setResult(RESULT_OK, intent);
        finish();
    }
}
