package com.dicicilaja.app.OrderIn.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.OrderIn.Data.AreaSimulasi.AreaSimulasi;
import com.dicicilaja.app.OrderIn.Data.ObjekBrand.ObjekBrand;
import com.dicicilaja.app.OrderIn.Data.ObjekModel.ObjekModel;
import com.dicicilaja.app.OrderIn.Data.TipeObjek.TipeObjek;
import com.dicicilaja.app.OrderIn.Network.ApiClient;
import com.dicicilaja.app.OrderIn.Network.ApiClient3;
import com.dicicilaja.app.OrderIn.Network.ApiService;
import com.dicicilaja.app.OrderIn.Session.SessionOrderIn;
import com.dicicilaja.app.OrderIn.UI.BantuanOrderIn.JenisAngsuranActivity;
import com.dicicilaja.app.OrderIn.UI.BantuanOrderIn.TipeAsuransiActivity;
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

public class InformasiJaminan2Activity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerJaminan)
    SearchableSpinner spinnerJaminan;
    @BindView(R.id.layoutJaminan)
    TextInputLayout layoutJaminan;
    @BindView(R.id.spinnerArea)
    SearchableSpinner spinnerArea;
    @BindView(R.id.layoutArea)
    TextInputLayout layoutArea;
    @BindView(R.id.spinnerBrand)
    SearchableSpinner spinnerBrand;
    @BindView(R.id.layoutBrand)
    TextInputLayout layoutBrand;
    @BindView(R.id.spinnerType)
    SearchableSpinner spinnerType;
    @BindView(R.id.layoutType)
    TextInputLayout layoutType;
    @BindView(R.id.et_tahun_kendaraan)
    EditText etTahunKendaraan;
    @BindView(R.id.icon_help1)
    RelativeLayout iconHelp1;
    @BindView(R.id.spinnerInstallment)
    SearchableSpinner spinnerInstallment;
    @BindView(R.id.layoutInstallment)
    TextInputLayout layoutInstallment;
    @BindView(R.id.icon_help2)
    RelativeLayout iconHelp2;
    @BindView(R.id.spinnerInsurance)
    SearchableSpinner spinnerInsurance;
    @BindView(R.id.layoutInsurance)
    TextInputLayout layoutInsurance;
    @BindView(R.id.angsuran_asuransi)
    LinearLayout angsuranAsuransi;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    SessionOrderIn session;
    private String jaminan_id, jaminan, area_id, area, objek_brand_id, brand, objek_model_id, model, year, tenor_simulasi, tipe_asuransi_id, tipe_asuransi, jenis_angsuran_id, jenis_angsuran;
    ApiService apiService, apiService2;

    final List<String> JAMINAN_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JAMINAN_DATA = new HashMap<Integer, String>();
    final List<String> AREA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> AREA_DATA = new HashMap<Integer, String>();
    final List<String> MERK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> MERK_DATA = new HashMap<Integer, String>();
    final List<String> TYPE_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TYPE_DATA = new HashMap<Integer, String>();
    final List<String> JENISANGSURAN_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JENISANGSURAN_MAP = new HashMap<Integer, String>();
    final List<String> TIPEASURANSI_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TIPEASURANSI_MAP = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_jaminan2);
        ButterKnife.bind(this);

        session = new SessionOrderIn(InformasiJaminan2Activity.this);

        initToolbar();
        initAction();
        initLoadData();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Informasi Jaminan");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initAction() {
        //Initialize
        progressBar.setVisibility(View.GONE);
        spinnerArea.setEnabled(false);
        spinnerBrand.setEnabled(false);
        spinnerType.setEnabled(false);
        spinnerInstallment.setEnabled(false);
        spinnerInsurance.setEnabled(false);
        angsuranAsuransi.setVisibility(View.VISIBLE);

        clearJaminan();
        clearArea();
        clearBrand();
        clearType();
        clearAngsuran();
        clearAsuransi();

        //Network
        apiService = ApiClient.getClient().create(ApiService.class);
        apiService2 = ApiClient3.getClient().create(ApiService.class);
    }

    private void clearJaminan() {
        JAMINAN_DATA.clear();
        JAMINAN_ITEMS.clear();
        JAMINAN_DATA.put(0, "0");
        JAMINAN_ITEMS.add("Pilih Jaminan");
        ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
        jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJaminan.setAdapter(jaminan_adapter);
        spinnerJaminan.setTitle("");
        spinnerJaminan.setPositiveButton("OK");
    }

    private void clearArea() {
        AREA_DATA.clear();
        AREA_ITEMS.clear();
        AREA_DATA.put(0, "0");
        AREA_ITEMS.add("Pilih Area Domisili");
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(area_adapter);
        spinnerArea.setTitle("");
        spinnerArea.setPositiveButton("OK");
        spinnerArea.setEnabled(false);
    }

    private void clearBrand() {
        MERK_DATA.clear();
        MERK_ITEMS.clear();
        MERK_DATA.put(0, "0");
        MERK_ITEMS.add("Pilih Merk Kendaraan");
        ArrayAdapter<String> brand_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, MERK_ITEMS);
        brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(brand_adapter);
        spinnerBrand.setTitle("");
        spinnerBrand.setPositiveButton("OK");
        spinnerBrand.setEnabled(false);
    }

    private void clearType() {
        TYPE_DATA.clear();
        TYPE_ITEMS.clear();
        TYPE_DATA.put(0, "0");
        TYPE_ITEMS.add("Pilih Tipe Kendaraan");
        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TYPE_ITEMS);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(type_adapter);
        spinnerType.setTitle("");
        spinnerType.setPositiveButton("OK");
        spinnerType.setEnabled(false);
    }

    private void clearAngsuran() {
        JENISANGSURAN_ITEMS.clear();
        JENISANGSURAN_MAP.clear();

        JENISANGSURAN_MAP.put(0, "0");
        JENISANGSURAN_MAP.put(1, "1");
        JENISANGSURAN_MAP.put(2, "2");

        JENISANGSURAN_ITEMS.add("Pilih Jenis Angsuran");
        JENISANGSURAN_ITEMS.add("Angsuran Per Bulan (ADDB)");
        JENISANGSURAN_ITEMS.add("Pembayaran Pertama (ADDM)");

        ArrayAdapter<String> jenisangsuran_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JENISANGSURAN_ITEMS);
        jenisangsuran_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstallment.setAdapter(jenisangsuran_adapter);
        spinnerInstallment.setTitle("");
        spinnerInstallment.setPositiveButton("OK");
        spinnerInstallment.setEnabled(false);
    }

    private void clearAsuransi() {
        TIPEASURANSI_MAP.clear();
        TIPEASURANSI_ITEMS.clear();

        TIPEASURANSI_MAP.put(0, "0");
        TIPEASURANSI_MAP.put(1, "1");
        TIPEASURANSI_MAP.put(2, "2");

        TIPEASURANSI_ITEMS.add("Pilih Tipe Asuransi");
        TIPEASURANSI_ITEMS.add("Total Lost Only");
        TIPEASURANSI_ITEMS.add("All Risk");
        ArrayAdapter<String> tipeasuransi_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TIPEASURANSI_ITEMS);
        tipeasuransi_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInsurance.setAdapter(tipeasuransi_adapter);
        spinnerInsurance.setTitle("");
        spinnerInsurance.setPositiveButton("OK");
        spinnerInsurance.setEnabled(false);
    }

    private void initLoadData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<TipeObjek> tipeObjekCall = apiService.getTipeObjek();
        tipeObjekCall.enqueue(new Callback<TipeObjek>() {
            @Override
            public void onResponse(Call<TipeObjek> call, Response<TipeObjek> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getData().size() > 0) {
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                JAMINAN_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                switch (String.valueOf(response.body().getData().get(i).getAttributes().getNama())) {
                                    case "car":
                                        JAMINAN_ITEMS.add("BPKB Mobil");
                                        break;

                                    case "mcy":
                                        JAMINAN_ITEMS.add("BPKB Motor");
                                        break;

                                    default:
                                        JAMINAN_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                                        break;
                                }
                            }
                            progressBar.setVisibility(View.GONE);
                        } else {
                            clearJaminan();
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data jaminan belum tersedia, silahkan coba beberapa saat lagi." + response.message());

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

                    ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
                    jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJaminan.setAdapter(jaminan_adapter);
                    spinnerJaminan.setTitle("");
                    spinnerJaminan.setPositiveButton("OK");
                } else {
                    clearJaminan();
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data jaminan gagal dipanggil, silahkan coba beberapa saat lagi." + response.message());

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
            public void onFailure(Call<TipeObjek> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data jaminan gagal dipanggil, silahkan coba beberapa saat lagi." + t.getMessage());

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                alertDialog.show();
            }
        });

        spinnerJaminan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clearArea();
                clearBrand();
                clearType();
                if (Integer.parseInt(JAMINAN_DATA.get(spinnerJaminan.getSelectedItemPosition())) > 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (Integer.parseInt(JAMINAN_DATA.get(spinnerJaminan.getSelectedItemPosition())) == 1) {
                        jaminan_id = "1";
                        angsuranAsuransi.setVisibility(View.VISIBLE);
                    } else {
                        jaminan_id = "2";
                        angsuranAsuransi.setVisibility(View.GONE);
                    }
                    Call<AreaSimulasi> areaSimulasiCall = apiService2.getAreaSimulasi(true);
                    areaSimulasiCall.enqueue(new Callback<AreaSimulasi>() {
                        @Override
                        public void onResponse(Call<AreaSimulasi> call, Response<AreaSimulasi> response) {
                            if (response.isSuccessful()) {
                                try {
                                    if (response.body().getData().size() > 0) {
                                        for (int i = 0; i < response.body().getData().size(); i++) {
                                            AREA_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                            AREA_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        clearArea();
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Data area belum tersedia, silahkan coba beberapa saat lagi." + response.message());

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

                                ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
                                area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerArea.setAdapter(area_adapter);
                                spinnerArea.setTitle("");
                                spinnerArea.setPositiveButton("OK");
                                spinnerArea.setEnabled(true);
                            } else {
                                clearArea();
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi." + response.message());

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
                        public void onFailure(Call<AreaSimulasi> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi." + t.getMessage());

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
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            clearBrand();
                            clearType();
                            clearAngsuran();
                            clearAsuransi();
                            if (Integer.parseInt(AREA_DATA.get(spinnerArea.getSelectedItemPosition())) > 0) {
                                progressBar.setVisibility(View.VISIBLE);
                                Log.d("DASH::", "onItemSelected: " + Integer.parseInt(jaminan_id));
                                Call<ObjekBrand> objekBrandCall = apiService.getObjekBrand(Integer.parseInt(jaminan_id));
                                objekBrandCall.enqueue(new Callback<ObjekBrand>() {
                                    @Override
                                    public void onResponse(Call<ObjekBrand> call, Response<ObjekBrand> response) {
                                        if (response.isSuccessful()) {
                                            try {
                                                if (response.body().getData().size() > 0) {
                                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                                        MERK_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                                        MERK_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                                                    }
                                                    progressBar.setVisibility(View.GONE);
                                                } else {
                                                    clearBrand();
                                                    progressBar.setVisibility(View.GONE);
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                                                    alertDialog.setTitle("Perhatian");
                                                    alertDialog.setMessage("Data merk kendaraan belum tersedia, silahkan coba beberapa saat lagi.");

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

                                            ArrayAdapter<String> brand_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, MERK_ITEMS);
                                            brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                            spinnerBrand.setAdapter(brand_adapter);
                                            spinnerBrand.setTitle("");
                                            spinnerBrand.setPositiveButton("OK");
                                            spinnerBrand.setEnabled(true);
                                        } else {
                                            clearBrand();
                                            progressBar.setVisibility(View.GONE);
                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                                            alertDialog.setTitle("Perhatian");
                                            alertDialog.setMessage("Data merk kendaraan gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                    public void onFailure(Call<ObjekBrand> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Data merk kendaraan gagal dipanggil, silahkan coba beberapa saat lagi.");

                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                });

                                spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        clearType();
                                        clearAngsuran();
                                        clearAsuransi();
                                        if (Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())) > 0) {
                                            progressBar.setVisibility(View.VISIBLE);
                                            Call<ObjekModel> objekModelCall = apiService.getObjekModel(Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())), Integer.parseInt(area_id = AREA_DATA.get(spinnerArea.getSelectedItemPosition())), false);
                                            objekModelCall.enqueue(new Callback<ObjekModel>() {
                                                @Override
                                                public void onResponse(Call<ObjekModel> call, Response<ObjekModel> response) {
                                                    if (response.isSuccessful()) {
                                                        try {
                                                            if (response.body().getData().size() > 0) {
                                                                for (int i = 0; i < response.body().getData().size(); i++) {
                                                                    TYPE_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                                                    TYPE_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNamaObjek()));
                                                                }
                                                                progressBar.setVisibility(View.GONE);

                                                            } else {
                                                                clearType();
                                                                progressBar.setVisibility(View.GONE);
                                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                                                                alertDialog.setTitle("Perhatian");
                                                                alertDialog.setMessage("Data tipe kendaraan belum tersedia, silahkan coba beberapa saat lagi.");

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

                                                        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TYPE_ITEMS);
                                                        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                        spinnerType.setAdapter(type_adapter);
                                                        spinnerType.setTitle("");
                                                        spinnerType.setPositiveButton("OK");
                                                        spinnerType.setEnabled(true);
                                                    } else {
                                                        clearType();
                                                        progressBar.setVisibility(View.GONE);
                                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                                                        alertDialog.setTitle("Perhatian");
                                                        alertDialog.setMessage("Data tipe kendaraan gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                                public void onFailure(Call<ObjekModel> call, Throwable t) {
                                                    clearType();
                                                    progressBar.setVisibility(View.GONE);
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                                                    alertDialog.setTitle("Perhatian");
                                                    alertDialog.setMessage("Data tipe kendaraan gagal dipanggil, silahkan coba beberapa saat lagi.");

                                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                            startActivity(getIntent());
                                                        }
                                                    });
                                                    alertDialog.show();
                                                }
                                            });

                                            spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                    if (Integer.parseInt(TYPE_DATA.get(spinnerType.getSelectedItemPosition())) > 0) {
                                                        clearAngsuran();
                                                        clearAsuransi();
                                                        spinnerInstallment.setEnabled(true);

                                                        spinnerInstallment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                if (Integer.parseInt(JENISANGSURAN_MAP.get(spinnerInstallment.getSelectedItemPosition())) > 0) {
                                                                    clearAsuransi();
                                                                    spinnerInsurance.setEnabled(true);
                                                                }
                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                            }
                                                        });
                                                    }

                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

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

    @OnClick({R.id.save, R.id.icon_help1, R.id.icon_help2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_help1:
                Intent intent = new Intent(getBaseContext(), JenisAngsuranActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_help2:
                Intent intent2 = new Intent(getBaseContext(), TipeAsuransiActivity.class);
                startActivity(intent2);
                break;
            case R.id.save:
                try {
                    jaminan_id = JAMINAN_DATA.get(spinnerJaminan.getSelectedItemPosition());
                    jaminan = JAMINAN_ITEMS.get(spinnerJaminan.getSelectedItemPosition());
                    area_id = AREA_DATA.get(spinnerArea.getSelectedItemPosition());
                    area = AREA_ITEMS.get(spinnerArea.getSelectedItemPosition());
                    objek_brand_id = MERK_DATA.get(spinnerBrand.getSelectedItemPosition());
                    brand = MERK_ITEMS.get(spinnerBrand.getSelectedItemPosition());
                    objek_model_id = TYPE_DATA.get(spinnerType.getSelectedItemPosition());
                    model = TYPE_ITEMS.get(spinnerType.getSelectedItemPosition());
                    year = etTahunKendaraan.getText().toString();
                    tipe_asuransi_id = TIPEASURANSI_MAP.get(spinnerInsurance.getSelectedItemPosition());
                    tipe_asuransi = TIPEASURANSI_ITEMS.get(spinnerInsurance.getSelectedItemPosition());
                    jenis_angsuran_id = JENISANGSURAN_MAP.get(spinnerInstallment.getSelectedItemPosition());
                    jenis_angsuran = JENISANGSURAN_ITEMS.get(spinnerInstallment.getSelectedItemPosition());

                } catch (Exception ex) {
                }

                Log.d("TAGTAG", "jaminan_id: " + jaminan_id);
                Log.d("TAGTAG", "jaminan: " + jaminan);
                Log.d("TAGTAG", "area_id: " + area_id);
                Log.d("TAGTAG", "area: " + area);
                Log.d("TAGTAG", "objek_brand_id: " + objek_brand_id);
                Log.d("TAGTAG", "brand: " + brand);
                Log.d("TAGTAG", "objek_model_id: " + objek_model_id);
                Log.d("TAGTAG", "model: " + model);
                Log.d("TAGTAG", "year: " + year);
                Log.d("TAGTAG", "tenor_simulasi: " + tenor_simulasi);
                Log.d("TAGTAG", "tipe_asuransi_id: " + tipe_asuransi_id);
                Log.d("TAGTAG", "tipe_asuransi: " + tipe_asuransi);
                Log.d("TAGTAG", "jenis_angsuran_id: " + jenis_angsuran_id);
                Log.d("TAGTAG", "jenis_angsuran: " + jenis_angsuran);


                if (jaminan_id.equals("1")) {
                    if (validateFormMobil(jaminan_id, area_id, objek_brand_id, objek_model_id, year, jenis_angsuran_id, tipe_asuransi_id)) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Apakah data yang Anda masukan sudah benar?");

                        alertDialog.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                session.editInformasiJaminan(true,
                                        jaminan_id,
                                        jaminan,
                                        area_id,
                                        area,
                                        objek_brand_id,
                                        brand, objek_model_id,
                                        model,
                                        year,
                                        tenor_simulasi,
                                        tipe_asuransi_id,
                                        tipe_asuransi,
                                        jenis_angsuran_id,
                                        jenis_angsuran
                                );
                                finish();
                            }
                        });
                        alertDialog.show();
                    }

                } else if (jaminan_id.equals("2")) {
                    if (validateFormMotor(jaminan_id, area_id, objek_brand_id, objek_model_id, year, tenor_simulasi)) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Apakah data yang Anda masukan sudah benar?");

                        alertDialog.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                session.editInformasiJaminan(true,
                                        jaminan_id,
                                        jaminan,
                                        area_id,
                                        area,
                                        objek_brand_id,
                                        brand, objek_model_id,
                                        model,
                                        year,
                                        tenor_simulasi,
                                        "1",
                                        "1",
                                        "1",
                                        "1"
                                );
                                finish();
                            }
                        });
                        alertDialog.show();
                    }
                } else {
                    if (jaminan_id == null || jaminan_id.trim().length() == 0 || jaminan_id.equals("0") || jaminan_id.equals("") || jaminan_id.equals(" ")) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Silahkan pilih jaminan");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestFocus(spinnerJaminan);
                                MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                                spinnerJaminan.dispatchTouchEvent(motionEvent);
                            }
                        });
                        alertDialog.show();
                    }
                }
                break;
        }
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            showSoftKeyboard(view);
        }
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    private boolean validateFormMobil(String jaminan_id, String area_id, String objek_brand_id, String objek_model_id, String year, String jenis_angsuran_id, String tipe_asuransi_id) {

        if (jaminan_id == null || jaminan_id.trim().length() == 0 || jaminan_id.equals("0") || jaminan_id.equals("") || jaminan_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih jaminan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJaminan);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerJaminan.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (area_id == null || area_id.trim().length() == 0 || area_id.equals("0") || area_id.equals("") || area_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih area");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerArea);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerArea.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (objek_brand_id == null || objek_brand_id.trim().length() == 0 || objek_brand_id.equals("0") || objek_brand_id.equals("") || objek_brand_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih merk kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerBrand);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerBrand.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (objek_model_id == null || objek_model_id.trim().length() == 0 || objek_model_id.equals("0") || objek_model_id.equals("") || objek_model_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih tipe kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerType);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerType.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (year == null || year.trim().length() == 0 || year.equals("0") || year.equals("") || year.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan tahun kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etTahunKendaraan);
                }
            });
            alertDialog.show();
            return false;
        } else if (year.length() < 4) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan tahun kendaraan dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etTahunKendaraan);
                }
            });
            alertDialog.show();
            return false;
        }

        if (jenis_angsuran_id == null || jenis_angsuran_id.trim().length() == 0 || jenis_angsuran_id.equals("0") || jenis_angsuran_id.equals("") || jenis_angsuran_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih jenis angsuran");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerInstallment);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerInstallment.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (tipe_asuransi_id == null || tipe_asuransi_id.trim().length() == 0 || tipe_asuransi_id.equals("0") || tipe_asuransi_id.equals("") || tipe_asuransi_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih tipe asuransi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerInsurance);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerInsurance.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }

    private boolean validateFormMotor(String jaminan_id, String area_id, String objek_brand_id, String objek_model_id, String year, String tenor_simulasi) {

        if (jaminan_id == null || jaminan_id.trim().length() == 0 || jaminan_id.equals("0") || jaminan_id.equals("") || jaminan_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih jaminan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJaminan);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerJaminan.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (area_id == null || area_id.trim().length() == 0 || area_id.equals("0") || area_id.equals("") || area_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih area");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerArea);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerArea.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (objek_brand_id == null || objek_brand_id.trim().length() == 0 || objek_brand_id.equals("0") || objek_brand_id.equals("") || objek_brand_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih merk kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerBrand);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerBrand.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (objek_model_id == null || objek_model_id.trim().length() == 0 || objek_model_id.equals("0") || objek_model_id.equals("") || objek_model_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih tipe kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerType);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerType.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (year == null || year.trim().length() == 0 || year.equals("0") || year.equals("") || year.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan tahun kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etTahunKendaraan);
                }
            });
            alertDialog.show();
            return false;
        } else if (year.length() < 4) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminan2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan tahun kendaraan dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etTahunKendaraan);
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }
}
