package com.dicicilaja.app.OrderIn.UI;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.OrderIn.Data.AreaSimulasi.AreaSimulasi;
import com.dicicilaja.app.OrderIn.Data.ObjekBrand.ObjekBrand;
import com.dicicilaja.app.OrderIn.Data.ObjekModel.ObjekModel;
import com.dicicilaja.app.OrderIn.Data.TipeObjek.TipeObjek;
import com.dicicilaja.app.OrderIn.Network.ApiClient;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiClient3;
import com.dicicilaja.app.OrderIn.Network.ApiService;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
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

public class InformasiJaminanActivity extends AppCompatActivity {

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
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.et_tahun_kendaraan)
    EditText etTahunKendaraan;

    String tipe_objek_id, area_id, tahun_kendaraan, model_id, type_id, text_area, text_merk, text_tipe, text_tahun;
    ApiService apiService, apiService2;

    final List<String> JAMINAN_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JAMINAN_DATA = new HashMap<Integer, String>();
    final List<String> AREA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> AREA_DATA = new HashMap<Integer, String>();
    final List<String> MERK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> MERK_DATA = new HashMap<Integer, String>();
    final List<String> TYPE_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TYPE_DATA = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_jaminan);
        ButterKnife.bind(this);

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

        clearJaminan();
        clearArea();
        clearBrand();
        clearType();

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
                                        JAMINAN_ITEMS.add("Mobil");
                                        break;

                                    case "mcy":
                                        JAMINAN_ITEMS.add("Motor");
                                        break;

                                    default:
                                        JAMINAN_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                                        break;
                                }
                            }
                            progressBar.setVisibility(View.GONE);
                        } else {
                            clearArea();
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                    } catch (Exception ex) { }

                    ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
                    jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerJaminan.setAdapter(jaminan_adapter);
                    spinnerJaminan.setTitle("");
                    spinnerJaminan.setPositiveButton("OK");
                } else {
                    clearArea();
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data jaminan gagal dipanggil, silahkan coba beberapa saat lagi."  + t.getMessage());

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
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                                } catch (Exception ex) { }

                                ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
                                area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerArea.setAdapter(area_adapter);
                                spinnerArea.setTitle("");
                                spinnerArea.setPositiveButton("OK");
                                spinnerArea.setEnabled(true);
                            } else {
                                clearArea();
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data area gagal dipanggil, silahkan coba beberapa saat lagi."  + t.getMessage());

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
                            if (Integer.parseInt(AREA_DATA.get(spinnerArea.getSelectedItemPosition())) > 0) {
                                progressBar.setVisibility(View.VISIBLE);
                                Call<ObjekBrand> objekBrandCall = apiService2.getObjekBrand(Integer.parseInt(tipe_objek_id));
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
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                                        if (Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())) > 0) {
                                            progressBar.setVisibility(View.VISIBLE);
                                            Call<ObjekModel> objekModelCall = apiService2.getObjekModel(Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())), Integer.parseInt(area_id = AREA_DATA.get(spinnerArea.getSelectedItemPosition())), false);
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
                                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
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

    @OnClick(R.id.save)
    public void onViewClicked() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiJaminanActivity.this);
        alertDialog.setTitle("Perhatian");
        alertDialog.setMessage("Apakah data yang Anda pilih sudah benar?");

        alertDialog.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();
    }
}
