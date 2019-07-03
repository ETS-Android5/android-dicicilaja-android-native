package com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity;

import android.content.DialogInterface;
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
import com.dicicilaja.app.BusinessReward.dataAPI.area.Area;
import com.dicicilaja.app.BusinessReward.dataAPI.branch.Branch;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
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

    final List<String> AREA = new ArrayList<>();
    final HashMap<Integer, String> AREA_DATA = new HashMap<Integer, String>();
    final List<String> CABANG = new ArrayList<>();
    final HashMap<Integer, String> CABANG_DATA = new HashMap<Integer, String>();
    ApiService apiService, apiService2;
    InterfaceAxiDetail apiServices;
    @BindView(R.id.not_available)
    TextView notAvailable;

    String tipe_objek_id;

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

        spinnerCabang.setEnabled(false);

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
        AREA.clear();
        AREA_DATA.put(0, "0");
//        AREA.add("Pilih Area");
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(area_adapter);
        spinnerArea.setTitle("");
        spinnerArea.setPositiveButton("OK");
    }

    private void clearCabang() {
        CABANG_DATA.clear();
        CABANG.clear();
        CABANG_DATA.put(0, "0");
//        CABANG.add("Pilih Cabang");
        ArrayAdapter<String> cabang_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, CABANG);
        cabang_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCabang.setAdapter(cabang_adapter);
        spinnerCabang.setTitle("");
        spinnerCabang.setPositiveButton("OK");
        spinnerCabang.setEnabled(false);
    }

    private void initLoadData() {
        initAction();
        progressBar0.setVisibility(View.VISIBLE);

        Call<Area> area = apiService.getArea();
        area.enqueue(new Callback<Area>() {
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getData().size() > 0) {
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                AREA_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                AREA.add(String.valueOf(response.body().getData().get(i).getName()));
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
                        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA);
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
            public void onFailure(Call<Area> call, Throwable t) {
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
                if(Integer.parseInt(AREA.get(spinnerArea.getSelectedItemPosition())) > 0){
                    progressBar.setVisibility(View.VISIBLE);
                    Call<Branch> branchCall = apiService.getCabang(Integer.parseInt(AREA.get(spinnerArea.getSelectedItemPosition())));
                    branchCall.enqueue(new Callback<Branch>() {
                        @Override
                        public void onResponse(Call<Branch> call, Response<Branch> response) {
                            if (response.isSuccessful()){
                                try {
                                    if(response.body().getData().size() > 0){
                                        for (int i = 0; i < response.body().getData().size(); i++) {
                                            CABANG_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                            CABANG.add(String.valueOf(response.body().getData().get(i).getName()));
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    }else{
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
                                }catch (Exception ex){
                                    ArrayAdapter<String> cabang_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, CABANG);
                                    cabang_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    spinnerCabang.setAdapter(cabang_adapter);
                                    spinnerCabang.setTitle("");
                                    spinnerCabang.setPositiveButton("OK");
                                    spinnerCabang.setEnabled(true);
                                }
                            }else{
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
                        public void onFailure(Call<Branch> call, Throwable t) {
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
                if(Integer.parseInt(CABANG.get(spinnerCabang.getSelectedItemPosition())) > 0){
                    progressBar3.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
