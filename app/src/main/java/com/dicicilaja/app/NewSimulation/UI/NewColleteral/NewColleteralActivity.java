package com.dicicilaja.app.NewSimulation.UI.NewColleteral;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.NewSimulation.Data.AreaSimulasi.AreaSimulasi;
import com.dicicilaja.app.NewSimulation.Data.ObjekBrand.ObjekBrand;
import com.dicicilaja.app.NewSimulation.Data.ObjekModel.ObjekModel;
import com.dicicilaja.app.NewSimulation.Data.ObjekTahun.ObjekTahun;
import com.dicicilaja.app.NewSimulation.Data.TahunKendaraan.TahunKendaraan;
import com.dicicilaja.app.NewSimulation.Network.ApiClient;
import com.dicicilaja.app.NewSimulation.Network.ApiClient2;
import com.dicicilaja.app.NewSimulation.Network.ApiService;
import com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.UI.NewLoan.NewLoanActivity;
import com.dicicilaja.app.OrderIn.Data.Vehicles.Vehicles;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewColleteralActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.spinnerYear)
    SearchableSpinner spinnerYear;
    @BindView(R.id.layoutYear)
    TextInputLayout layoutYear;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.progressBar2)
    MaterialProgressBar progressBar2;
    @BindView(R.id.progressBar3)
    MaterialProgressBar progressBar3;
    @BindView(R.id.not_available)
    TextView notAvailable;
    @BindView(R.id.progressBar0)
    MaterialProgressBar progressBar0;
    @BindView(R.id.next)
    Button next;

    String tipe_objek_id, area_id, tahun_kendaraan, model_id, type_id, text_area, text_merk, text_tipe, text_tahun, vehicles, vehicles_id;;
    boolean data_tahun;
    List<ObjekTahun> objekTahuns;
    ApiService apiService, apiService2;
    ApiService3 apiService3;

    final List<String> AREA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> AREA_DATA = new HashMap<Integer, String>();
    final List<String> MERK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> MERK_DATA = new HashMap<Integer, String>();
    final List<String> TYPE_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TYPE_DATA = new HashMap<Integer, String>();
    final List<String> YEAR_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> YEAR_DATA = new HashMap<Integer, String>();
    final HashMap<Integer, String> MERK_ACC_CODE = new HashMap<Integer, String>();
    final HashMap<Integer, String> MERK_ACC_NAME = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_colleteral);
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
    }

    private void initAction() {
        //Initialize
        progressBar0.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        progressBar2.setVisibility(View.GONE);
        progressBar3.setVisibility(View.GONE);
        notAvailable.setVisibility(View.GONE);
        tipe_objek_id = "1";
        data_tahun = true;
        objekTahuns = new ArrayList<>();
        spinnerBrand.setEnabled(false);
        spinnerType.setEnabled(false);
        spinnerYear.setEnabled(false);

        clearArea();
        clearBrand();
        clearType();
        clearYear();

        //Network
        apiService = ApiClient.getClient().create(ApiService.class);
        apiService2 = ApiClient2.getClient().create(ApiService.class);
        apiService3 = com.dicicilaja.app.OrderIn.Network.ApiClient2.getClient().create(ApiService3.class);
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
    }

    private void clearBrand() {
        MERK_DATA.clear();
        MERK_ACC_CODE.clear();
        MERK_ACC_NAME.clear();
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

    private void clearYear() {
        YEAR_DATA.clear();
        YEAR_ITEMS.clear();
        YEAR_DATA.put(0, "0");
        YEAR_ITEMS.add("Pilih Tahun Kendaraan");
        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, YEAR_ITEMS);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(year_adapter);
        spinnerYear.setTitle("");
        spinnerYear.setPositiveButton("OK");
        spinnerYear.setEnabled(false);
    }

    private void initLoadData() {
        initAction();
        progressBar0.setVisibility(View.VISIBLE);
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
                            progressBar0.setVisibility(View.GONE);
                        } else {
                            clearArea();
                            progressBar0.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
                    } catch (Exception ex) { }

                    ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
                    area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerArea.setAdapter(area_adapter);
                    spinnerArea.setTitle("");
                    spinnerArea.setPositiveButton("OK");
                } else {
                    clearArea();
                    progressBar0.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
            public void onFailure(Call<AreaSimulasi> call, Throwable t) {
                progressBar0.setVisibility(View.GONE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clearBrand();
                clearType();
                clearYear();
                if (Integer.parseInt(AREA_DATA.get(spinnerArea.getSelectedItemPosition())) > 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    String jaminan = "002";
                    Call<List<Vehicles>> objekBrandCall = apiService3.getVehicles(jaminan);
                    objekBrandCall.enqueue(new Callback<List<Vehicles>>() {
                        @Override
                        public void onResponse(Call<List<Vehicles>> call, Response<List<Vehicles>> response) {
                            if (response.isSuccessful()) {
                                try {
                                    if (response.body().size() > 0) {
                                        for (int i = 0; i < response.body().size(); i++) {
                                            MERK_DATA.put(i + 1, String.valueOf(response.body().get(i).getId()));
                                            MERK_ACC_CODE.put(i + 1, String.valueOf(response.body().get(i).getVehicleCode()));
                                            MERK_ACC_NAME.put(i + 1, String.valueOf(response.body().get(i).getVehicleName()));
                                            MERK_ITEMS.add(String.valueOf(response.body().get(i).getNama()));
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        clearBrand();
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
                        public void onFailure(Call<List<Vehicles>> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
                            clearYear();
                            notAvailable.setVisibility(View.GONE);
                            data_tahun = true;
                            if (Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())) > 0) {
                                progressBar2.setVisibility(View.VISIBLE);
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
                                                    progressBar2.setVisibility(View.GONE);

                                                } else {
                                                    clearType();
                                                    progressBar2.setVisibility(View.GONE);
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
                                            progressBar2.setVisibility(View.GONE);
                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
                                        progressBar2.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
                                        clearYear();
                                        notAvailable.setVisibility(View.GONE);
                                        data_tahun = true;
                                        if (Integer.parseInt(TYPE_DATA.get(spinnerType.getSelectedItemPosition())) > 0) {
                                            progressBar3.setVisibility(View.VISIBLE);
                                            Call<TahunKendaraan> tahunKendaraanCall = apiService.getTahunKendaraan(Integer.parseInt(TYPE_DATA.get(spinnerType.getSelectedItemPosition())), Integer.parseInt(AREA_DATA.get(spinnerArea.getSelectedItemPosition())));
                                            tahunKendaraanCall.enqueue(new Callback<TahunKendaraan>() {
                                                @Override
                                                public void onResponse(Call<TahunKendaraan> call, Response<TahunKendaraan> response) {
                                                    if (response.isSuccessful()) {
                                                        try {
                                                            if (response.body().getData().size() == 0) {
                                                                data_tahun = false;
                                                                clearYear();
                                                                notAvailable.setVisibility(View.VISIBLE);
                                                                spinnerYear.setEnabled(false);
                                                            } else if (response.body().getData().size() > 0) {
                                                                data_tahun = true;
                                                                for (int i = 0; i < response.body().getData().size(); i++) {
                                                                    YEAR_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                                                    YEAR_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getTahun()));
                                                                }
                                                                progressBar3.setVisibility(View.GONE);
                                                                notAvailable.setVisibility(View.GONE);
                                                                spinnerYear.setEnabled(true);
                                                            } else {
                                                                clearYear();
                                                                progressBar3.setVisibility(View.GONE);
                                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
                                                                alertDialog.setTitle("Perhatian");
                                                                alertDialog.setMessage("Data tahun kendaraan belum tersedia, silahkan coba beberapa saat lagi.");

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

                                                        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, YEAR_ITEMS);
                                                        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                        spinnerYear.setAdapter(year_adapter);
                                                        spinnerYear.setTitle("");
                                                        spinnerYear.setPositiveButton("OK");
                                                        progressBar3.setVisibility(View.GONE);
                                                    } else {
                                                        clearYear();
                                                        progressBar3.setVisibility(View.GONE);
                                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
                                                        alertDialog.setTitle("Perhatian");
                                                        alertDialog.setMessage("Data tahun kendaraan gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                                public void onFailure(Call<TahunKendaraan> call, Throwable t) {
                                                    clearYear();
                                                    progressBar3.setVisibility(View.GONE);
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
                                                    alertDialog.setTitle("Perhatian");
                                                    alertDialog.setMessage("Data tahun kendaraan gagal dipanggil, silahkan coba beberapa saat lagi.");

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simulasi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.help:
                Intent intent = new Intent(getBaseContext(), BantuanNewSimulationActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.next)
    public void onViewClicked() {
        try {
            area_id = AREA_DATA.get(spinnerArea.getSelectedItemPosition());
            text_area = AREA_ITEMS.get(spinnerArea.getSelectedItemPosition());
            text_merk = MERK_ITEMS.get(spinnerBrand.getSelectedItemPosition());
            vehicles_id = MERK_ACC_CODE.get(spinnerBrand.getSelectedItemPosition());
            vehicles = MERK_ACC_NAME.get(spinnerBrand.getSelectedItemPosition());
            text_tipe = TYPE_ITEMS.get(spinnerType.getSelectedItemPosition());
        } catch (Exception ex) {
        }

        if (!data_tahun) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
            alertDialog.setTitle("Hubungi Tasya");
            alertDialog.setMessage("Silahkan hubungi tasya melalui WhatsApp untuk menindaklanjuti pengajuan.");
            alertDialog.setIcon(R.drawable.ic_whatsapp_icon);

            alertDialog.setPositiveButton("Hubungi", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (area_id.equals("9")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119125005&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("10")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628111475505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("11")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("12")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("13")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : Bali %26 Nusa Tenggara\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("14")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("15")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628111475505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("16")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628119555051&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    } else if (area_id.equals("17")) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=628118845505&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20simulasi%20cicilan\n\nArea : " + text_area + "\nMerk Kendaraan : " + text_merk + "\nTipe Kendaraan : " + text_tipe + "\n\nMengapa belum tersedia?"));
                        startActivity(browserIntent);
                    }
                }
            });
            alertDialog.show();
        } else {
            try {
                area_id = AREA_DATA.get(spinnerArea.getSelectedItemPosition());
                tahun_kendaraan = YEAR_DATA.get(spinnerYear.getSelectedItemPosition());
                model_id = MERK_DATA.get(spinnerBrand.getSelectedItemPosition());
                vehicles_id = MERK_ACC_CODE.get(spinnerBrand.getSelectedItemPosition());
                vehicles = MERK_ACC_NAME.get(spinnerBrand.getSelectedItemPosition());
                type_id = TYPE_DATA.get(spinnerType.getSelectedItemPosition());
            } catch (Exception ex) {
            }

            if (validateForm(area_id, tahun_kendaraan, model_id, type_id)) {
                Intent intent = new Intent(getBaseContext(), NewLoanActivity.class);
                intent.putExtra("objek_brand_id", model_id);
                intent.putExtra("objek_model_id", type_id);
                intent.putExtra("area_id", area_id);
                intent.putExtra("tipe_objek_id", tipe_objek_id);
                intent.putExtra("tahun_kendaraan", tahun_kendaraan);
                intent.putExtra("vehicles",vehicles);
                intent.putExtra("vehicles_id",vehicles_id);
                startActivity(intent);
            }
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateForm(String area_id, String tahun_kendaraan, String model_id, String type_id) {
        if (area_id == null || area_id.trim().length() == 0 || area_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih area domisili");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerArea);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerArea.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (model_id == null || model_id.trim().length() == 0 || model_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih merk kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerBrand);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerBrand.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (type_id == null || type_id.trim().length() == 0 || type_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih tipe kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerType);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerType.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (tahun_kendaraan == null || tahun_kendaraan.trim().length() == 0 || tahun_kendaraan.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih tahun kendaraaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerYear);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerYear.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }
}
