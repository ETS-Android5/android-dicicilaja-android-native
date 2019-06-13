package com.dicicilaja.app.NewSimulation.ui.motorcolleteral;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.NewSimulation.data.hitungsimulasi.HitungSimulasi;
import com.dicicilaja.app.NewSimulation.data.objekbrand.ObjekBrand;
import com.dicicilaja.app.NewSimulation.data.objekmodel.ObjekModel;
import com.dicicilaja.app.NewSimulation.data.objektahun.ObjekTahun;
import com.dicicilaja.app.NewSimulation.data.tahunkendaraan.TahunKendaraan;
import com.dicicilaja.app.NewSimulation.network.ApiClient;
import com.dicicilaja.app.NewSimulation.network.ApiService;
import com.dicicilaja.app.NewSimulation.ui.bantuan.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.ui.newloan.NewLoanActivity;
import com.dicicilaja.app.NewSimulation.ui.newsimulationresult.NewSimulationResultActivity;
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

public class MotorColleteralActivity extends AppCompatActivity {

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
    @BindView(R.id.spinnerTenor)
    SearchableSpinner spinnerTenor;
    @BindView(R.id.layoutTenor)
    TextInputLayout layoutTenor;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.next)
    Button next;

    List<ObjekTahun> objekTahuns;

    String tipe_objek_id, area_id, tahun_kendaraan, model_id, type_id, tenor;

    String text_total, text_tenor, text_angsuran, text_tenor_angsuran, text_colleteral, text_merk, text_type, text_year, text_insurance, text_area;

    final List<String> TENOR_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TENOR_MAP = new HashMap<Integer, String>();

    final List<String> AREA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> AREA_DATA = new HashMap<Integer, String>();

    final List<String> MERK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> MERK_DATA = new HashMap<Integer, String>();

    final List<String> TYPE_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TYPE_DATA = new HashMap<Integer, String>();

    final List<String> YEAR_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> YEAR_DATA = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_colleteral);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Informasi Jaminan");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        progressBar.setVisibility(View.GONE);

        tipe_objek_id = getIntent().getStringExtra("tipe_objek_id");

        objekTahuns = new ArrayList<>();

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        TENOR_MAP.clear();
        TENOR_ITEMS.clear();

        TENOR_MAP.put(0, "0");
        TENOR_MAP.put(1, "1");
        TENOR_MAP.put(2, "2");
        TENOR_MAP.put(3, "3");
        TENOR_MAP.put(4, "4");

        TENOR_ITEMS.add("Pilih Tenor Pinjaman");
        TENOR_ITEMS.add("1 Tahun");
        TENOR_ITEMS.add("2 Tahun");
        TENOR_ITEMS.add("3 Tahun");
        TENOR_ITEMS.add("4 Tahun");

        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTenor.setAdapter(tenor_adapter);
        spinnerTenor.setTitle("");
        spinnerTenor.setPositiveButton("OK");

        AREA_DATA.put(0, "0");
        AREA_DATA.put(1, "9");
        AREA_DATA.put(2, "10");
        AREA_DATA.put(3, "11");
        AREA_DATA.put(4, "12");
        AREA_DATA.put(5, "13");
        AREA_DATA.put(6, "14");
        AREA_DATA.put(7, "15");
        AREA_DATA.put(8, "16");
        AREA_DATA.put(9, "17");

        AREA_ITEMS.add("Pilih Area Domisili");
        AREA_ITEMS.add("Jabodetabekser");
        AREA_ITEMS.add("Jawa Barat");
        AREA_ITEMS.add("Jawa Tengah");
        AREA_ITEMS.add("Jawa Timur");
        AREA_ITEMS.add("Bali & Nusa Tenggara");
        AREA_ITEMS.add("Sumbagut");
        AREA_ITEMS.add("Sumbagsel");
        AREA_ITEMS.add("Kalimantan");
        AREA_ITEMS.add("Sulampapua");

        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(area_adapter);
        spinnerArea.setTitle("");
        spinnerArea.setPositiveButton("OK");

        Call<ObjekBrand> callArea = apiService.getObjekBrand(Integer.parseInt(tipe_objek_id));
        callArea.enqueue(new Callback<ObjekBrand>() {
            @Override
            public void onResponse(Call<ObjekBrand> call, Response<ObjekBrand> response) {
                MERK_DATA.clear();
                MERK_ITEMS.clear();

                if (response.body().getData().size() > 0) {
                    MERK_DATA.put(0, "0");
                    MERK_ITEMS.add("Merk Kendaraan");

                    try {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            MERK_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                            MERK_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                        }
                    } catch (Exception ex) {

                    }
                } else {
                    MERK_DATA.put(0, "0");
                    MERK_ITEMS.add("Tidak ada Kendaraan");
                }

                ArrayAdapter<String> brand_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, MERK_ITEMS);
                brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerBrand.setAdapter(brand_adapter);
                spinnerBrand.setTitle("");
                spinnerBrand.setPositiveButton("OK");
            }

            @Override
            public void onFailure(Call<ObjekBrand> call, Throwable t) {
                MERK_DATA.clear();
                MERK_ITEMS.clear();

                MERK_DATA.put(0, "0");
                MERK_ITEMS.add("Tidak ada Kendaraan");
            }
        });

        TYPE_DATA.clear();
        TYPE_ITEMS.clear();

        TYPE_DATA.put(0, "0");
        TYPE_ITEMS.add("Tipe Kendaraan");

        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TYPE_ITEMS);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerType.setAdapter(type_adapter);
        spinnerType.setTitle("");
        spinnerType.setPositiveButton("OK");

        spinnerType.setEnabled(false);


        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerType.setEnabled(false);

                if (Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())) > 0) {
                    Call<ObjekModel> callArea = apiService.getObjekModel(Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())));
                    callArea.enqueue(new Callback<ObjekModel>() {
                        @Override
                        public void onResponse(Call<ObjekModel> call, Response<ObjekModel> response) {
                            TYPE_DATA.clear();
                            TYPE_ITEMS.clear();

                            TYPE_DATA.put(0, "0");
                            TYPE_ITEMS.add("Tipe Kendaraan");

                            if (response.body().getData().size() > 0) {
                                try {
                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        TYPE_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                        TYPE_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNamaObjek()));
                                    }
                                } catch (Exception ex) {

                                }
                                spinnerType.setEnabled(true);
                            } else {

                            }

                            ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TYPE_ITEMS);
                            type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinnerType.setAdapter(type_adapter);
                            spinnerType.setTitle("");
                            spinnerType.setPositiveButton("OK");
                        }

                        @Override
                        public void onFailure(Call<ObjekModel> call, Throwable t) {
                            TYPE_DATA.clear();
                            TYPE_ITEMS.clear();

                            TYPE_DATA.put(0, "0");
                            TYPE_ITEMS.add("Tipe Kendaraan");

                            spinnerType.setEnabled(false);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        YEAR_DATA.clear();
        YEAR_ITEMS.clear();

        YEAR_DATA.put(0, "0");

        YEAR_ITEMS.add("Tahun Kendaraan");

        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, YEAR_ITEMS);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(year_adapter);
        spinnerYear.setTitle("");
        spinnerYear.setPositiveButton("OK");

        spinnerYear.setEnabled(false);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (Integer.parseInt(TYPE_DATA.get(spinnerType.getSelectedItemPosition())) > 0) {

                    Call<TahunKendaraan> callArea = apiService.getTahunKendaraan(Integer.parseInt(TYPE_DATA.get(spinnerType.getSelectedItemPosition())), Integer.parseInt(AREA_DATA.get(spinnerArea.getSelectedItemPosition())));
                    callArea.enqueue(new Callback<TahunKendaraan>() {
                        @Override
                        public void onResponse(Call<TahunKendaraan> call, Response<TahunKendaraan> response) {
                            YEAR_DATA.clear();
                            YEAR_ITEMS.clear();

                            YEAR_DATA.put(0, "0");
                            YEAR_ITEMS.add("Tahun Kendaraan");

                            if (response.body().getData().size() > 0) {
                                try {
                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        YEAR_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                        YEAR_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getTahun()));
                                    }
                                } catch (Exception ex) {

                                }

                                spinnerYear.setEnabled(true);
                            } else {

                            }

                            ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, YEAR_ITEMS);
                            year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerYear.setAdapter(year_adapter);
                            spinnerYear.setTitle("");
                            spinnerYear.setPositiveButton("OK");

                        }

                        @Override
                        public void onFailure(Call<TahunKendaraan> call, Throwable t) {
                            YEAR_DATA.clear();
                            YEAR_ITEMS.clear();

                            YEAR_DATA.put(0, "0");
                            YEAR_ITEMS.add("Tahun Kendaraan");

                            spinnerYear.setEnabled(false);
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
            model_id = MERK_DATA.get(spinnerBrand.getSelectedItemPosition());
            type_id = TYPE_DATA.get(spinnerType.getSelectedItemPosition());
            area_id = AREA_DATA.get(spinnerArea.getSelectedItemPosition());
            tahun_kendaraan = YEAR_DATA.get(spinnerYear.getSelectedItemPosition());
            tenor = TENOR_MAP.get(spinnerTenor.getSelectedItemPosition());
        } catch (Exception ex) {

        }

        if (validateForm(tenor, area_id, tahun_kendaraan, model_id, type_id)) {

            progressBar.setVisibility(View.VISIBLE);

            tipe_objek_id = getIntent().getStringExtra("tipe_objek_id");

            ApiService apiService = ApiClient.getClient().create(ApiService.class);

            Log.d("MOTORMOTOR", "tipe_objek_id: " + tipe_objek_id);
            Log.d("MOTORMOTOR", "type_id: " + type_id);
            Log.d("MOTORMOTOR", "tahun_kendaraan: " + tahun_kendaraan);
            Log.d("MOTORMOTOR", "area_id: " + area_id);
            Log.d("MOTORMOTOR", "tenor: " + tenor);

            Call<HitungSimulasi> call = apiService.hitungMcy(tipe_objek_id, type_id, tahun_kendaraan, area_id, tenor);
            call.enqueue(new Callback<HitungSimulasi>() {

                @Override
                public void onResponse(Call<HitungSimulasi> call, Response<HitungSimulasi> response) {
                    Log.d("MOTORMOTOR", "code: " + response.code());
                    progressBar.setVisibility(View.VISIBLE);

                    if (response.isSuccessful()) {

                        text_total = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterima();
                        text_tenor = String.valueOf(response.body().getData().getAttributes().getInformasiJaminan().getTenor());
                        text_angsuran = response.body().getData().getAttributes().getHasilSimulasi().getAngsuranPerBulan();
                        text_tenor_angsuran = "x " + response.body().getData().getAttributes().getInformasiJaminan().getTenor() + " Bulan";
                        text_colleteral = response.body().getData().getAttributes().getInformasiJaminan().getKendaraan();
                        text_merk = response.body().getData().getAttributes().getInformasiJaminan().getMerkKendaraan();
                        text_type = response.body().getData().getAttributes().getInformasiJaminan().getTypeKendaraan();
                        text_year = response.body().getData().getAttributes().getInformasiJaminan().getTahunKendaraan();
                        text_insurance = response.body().getData().getAttributes().getInformasiJaminan().getTipeAsuransi();
                        text_area = response.body().getData().getAttributes().getInformasiJaminan().getArea();

                        progressBar.setVisibility(View.GONE);

                        Intent intent = new Intent(getBaseContext(), NewSimulationResultActivity.class);
                        intent.putExtra("text_total", text_total);
                        intent.putExtra("text_tenor", text_tenor);
                        intent.putExtra("text_angsuran", text_angsuran);
                        intent.putExtra("text_tenor_angsuran", text_tenor_angsuran);
                        intent.putExtra("text_colleteral", text_colleteral);
                        intent.putExtra("text_merk", text_merk);
                        intent.putExtra("text_type", text_type);
                        intent.putExtra("text_year", text_year);
                        intent.putExtra("text_insurance", text_insurance);
                        intent.putExtra("text_area", text_area);
                        startActivity(intent);


                    } else {

                    }
                }

                @Override
                public void onFailure(Call<HitungSimulasi> call, Throwable t) {

                }
            });
        }
    }

    private boolean validateForm(String tenor, String area_id, String tahun_kendaraan, String model_id, String type_id) {

        if (tenor == null || tenor.trim().length() == 0 || tenor.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MotorColleteralActivity.this);
            alertDialog.setMessage("Pilih Tenor Pinjaman");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerTenor);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerTenor.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (area_id == null || area_id.trim().length() == 0 || area_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MotorColleteralActivity.this);
            alertDialog.setMessage("Pilih Area Domisili");

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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MotorColleteralActivity.this);
            alertDialog.setMessage("Pilih Merk Kendaraan");

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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MotorColleteralActivity.this);
            alertDialog.setMessage("Pilih Tipe Kendaraan");

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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MotorColleteralActivity.this);
            alertDialog.setMessage("Pilih Tahun Kendaraaan");

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

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
