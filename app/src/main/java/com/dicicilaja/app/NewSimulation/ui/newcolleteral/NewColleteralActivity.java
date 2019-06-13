package com.dicicilaja.app.NewSimulation.ui.newcolleteral;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.dicicilaja.app.NewSimulation.data.objekbrand.ObjekBrand;
import com.dicicilaja.app.NewSimulation.network.ApiClient;
import com.dicicilaja.app.NewSimulation.network.ApiService;
import com.dicicilaja.app.NewSimulation.ui.bantuan.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.ui.newloan.NewLoanActivity;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
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
    @BindView(R.id.next)
    Button next;

    String tipe_objek_id, area_id, tahun_kendaraan, model_id, type_id;

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
        setContentView(R.layout.activity_new_colleteral);
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

        tipe_objek_id = getIntent().getStringExtra("tipe_objek_id");

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        AREA_DATA.put(0, "0");
        AREA_DATA.put(1, "1");
        AREA_DATA.put(2, "2");
        AREA_DATA.put(3, "3");
        AREA_DATA.put(4, "4");

        AREA_ITEMS.add("Pilih Area Domisili");
        AREA_ITEMS.add("Jabodetabekser");
        AREA_ITEMS.add("Jawa Barat");
        AREA_ITEMS.add("Jawa Tengah");
        AREA_ITEMS.add("Jawa Timur");

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
                            MERK_DATA.put(response.body().getData().get(i).getId(), String.valueOf(response.body().getData().get(i).getId()));
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

        spinnerType.setEnabled(false);

        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerType.setEnabled(true);
//                if (Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())) > 0) {
//                    Call<ObjekModel> callArea = apiService.getObjekModel(Integer.parseInt(MERK_DATA.get(spinnerBrand.getSelectedItemPosition())));
//                    callArea.enqueue(new Callback<ObjekModel>() {
//                        @Override
//                        public void onResponse(Call<ObjekModel> call, Response<ObjekModel> response) {
//                            TYPE_DATA.clear();
//                            TYPE_ITEMS.clear();
//
//                            if (response.body().getData().size() > 0) {
//                                TYPE_DATA.put(0, "0");
//                                TYPE_ITEMS.add("Tipe Kendaraan");
//
//                                try {
//                                    for ( int i = 0; i < response.body().getData().size() ; i++ ) {
//                                        TYPE_DATA.put(response.body().getData().get(i).getId(), String.valueOf(response.body().getData().get(i).getId()));
//                                        TYPE_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNamaObjek()));
//                                    }
//                                }catch (Exception ex) {
//
//                                }
//                            } else {
//                                TYPE_DATA.put(0, "0");
//                                TYPE_ITEMS.add("Tipe Kendaraan");
//                            }
//
//                            ArrayAdapter<String> brand_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, MERK_ITEMS);
//                            brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                            spinnerBrand.setEnabled(true);
//                            spinnerBrand.setAdapter(brand_adapter);
//                            spinnerBrand.setTitle("");
//                            spinnerBrand.setPositiveButton("OK");
//                        }
//
//                        @Override
//                        public void onFailure(Call<ObjekModel> call, Throwable t) {
//                            TYPE_DATA.clear();
//                            TYPE_ITEMS.clear();
//
//                            TYPE_DATA.put(0, "0");
//                            TYPE_ITEMS.add("Tipe Kendaraan");
//
//                            spinnerBrand.setEnabled(false);
//                        }
//                    });
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        TYPE_DATA.clear();
        TYPE_ITEMS.clear();

        TYPE_DATA.put(0, "0");
        TYPE_DATA.put(1, "1");
        TYPE_DATA.put(2, "2");

        TYPE_ITEMS.add("Tipe Kendaraan");
        TYPE_ITEMS.add("New Crv 2.0 M/T DOHC");
        TYPE_ITEMS.add("Skyline GTR R35 Putih");

        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TYPE_ITEMS);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(type_adapter);
        spinnerType.setTitle("");
        spinnerType.setPositiveButton("OK");

        YEAR_DATA.clear();
        YEAR_ITEMS.clear();

        YEAR_DATA.put(0, "0");
        YEAR_DATA.put(1, "1");
        YEAR_DATA.put(2, "2");
        YEAR_DATA.put(3, "3");
        YEAR_DATA.put(4, "4");
        YEAR_DATA.put(5, "5");
        YEAR_DATA.put(6, "6");
        YEAR_DATA.put(7, "7");
        YEAR_DATA.put(8, "8");
        YEAR_DATA.put(9, "9");
        YEAR_DATA.put(10, "10");
        YEAR_DATA.put(11, "11");
        YEAR_DATA.put(12, "12");
        YEAR_DATA.put(13, "13");
        YEAR_DATA.put(14, "14");

        YEAR_ITEMS.add("Tahun Kendaraan");
        YEAR_ITEMS.add("2006");
        YEAR_ITEMS.add("2007");
        YEAR_ITEMS.add("2008");
        YEAR_ITEMS.add("2009");
        YEAR_ITEMS.add("2010");
        YEAR_ITEMS.add("2011");
        YEAR_ITEMS.add("2012");
        YEAR_ITEMS.add("2013");
        YEAR_ITEMS.add("2014");
        YEAR_ITEMS.add("2015");
        YEAR_ITEMS.add("2016");
        YEAR_ITEMS.add("2017");
        YEAR_ITEMS.add("2018");
        YEAR_ITEMS.add("2019");

        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, YEAR_ITEMS);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(year_adapter);
        spinnerYear.setTitle("");
        spinnerYear.setPositiveButton("OK");
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
            tahun_kendaraan = YEAR_DATA.get(spinnerYear.getSelectedItemPosition());
            model_id = MERK_DATA.get(spinnerBrand.getSelectedItemPosition());
            type_id = TYPE_DATA.get(spinnerType.getSelectedItemPosition());
        } catch (Exception ex) {

        }

        if (validateForm(area_id, tahun_kendaraan, model_id, type_id)) {
            Intent intent = new Intent(getBaseContext(), NewLoanActivity.class);
            intent.putExtra("tipe_objek_id", tipe_objek_id);
            intent.putExtra("area_id", area_id);
            intent.putExtra("tahun_kendaraan", tahun_kendaraan);
            intent.putExtra("objek_model_id", model_id + type_id);
            startActivity(intent);
        }
    }

    private boolean validateForm(String area_id, String tahun_kendaraan, String model_id, String type_id) {
        if (area_id == null || area_id.trim().length() == 0 || area_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewColleteralActivity.this);
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