package id.variable.dicicilaja.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;
import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceAreaBranch;
import id.variable.dicicilaja.API.Interface.InterfaceCreateOrder;
import id.variable.dicicilaja.API.Item.CreateOrder.Area.Area;
import id.variable.dicicilaja.API.Item.CreateOrder.Branch.Branch;
import id.variable.dicicilaja.API.Item.CreateOrder.Colleteral.Colleteral;
import id.variable.dicicilaja.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjukanPengajuanActivity extends AppCompatActivity {

    MaterialSpinner spinnerJaminan, spinnerTahun, spinnerArea, spinnerCabang, spinnerTenor;
    EditText inputId, inputMerk, inputPinjaman;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajukan_pengajuan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        spinnerJaminan = findViewById(R.id.spinnerJaminan);
        spinnerTahun = findViewById(R.id.spinnerTahun);
        spinnerTenor = findViewById(R.id.spinnerWaktu);
        inputPinjaman = findViewById(R.id.inputPinjaman);
        next = findViewById(R.id.next);
        inputId = findViewById(R.id.inputId);
        inputMerk = findViewById(R.id.inputMerk);
        inputPinjaman = findViewById(R.id.inputPinjaman);
        spinnerArea = findViewById(R.id.spinnerArea);
        spinnerCabang = findViewById(R.id.spinnerCabang);

        setCurrency(inputPinjaman);

        final List<String> JAMINAN_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JAMINAN_DATA = new HashMap<Integer, String>();

        final List<String> AREA_ITEMS = new ArrayList<>();;
        final HashMap<Integer, String> AREA_MAP = new HashMap<Integer, String>();

        final List<String> CABANG_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> CABANG_MAP = new HashMap<Integer, String>();

        final InterfaceCreateOrder apiServiceColleteral =
                RetrofitClient.getClient().create(InterfaceCreateOrder.class);

        Call<Colleteral> callcolleteral = apiServiceColleteral.getColleteral();
        callcolleteral.enqueue(new Callback<Colleteral>() {
            @Override
            public void onResponse(Call<Colleteral> call, Response<Colleteral> response) {

                JAMINAN_ITEMS.clear();
                JAMINAN_DATA.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    JAMINAN_DATA.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getId().toString());
                    JAMINAN_ITEMS.add(response.body().getData().get(i).getName());
                }
            }

            @Override
            public void onFailure(Call<Colleteral> call, Throwable t) {
                JAMINAN_DATA.clear();
                JAMINAN_ITEMS.clear();
                Log.e("Error", t.getMessage());
            }
        });

        ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
        jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJaminan.setAdapter(jaminan_adapter);

        //=============================================================================================//

        final List<String> TAHUN_MOBIL_ITEMS = new ArrayList<>();
        final List<String> TAHUN_MOTOR_ITEMS = new ArrayList<>();


        spinnerTahun.setEnabled(false);

        spinnerJaminan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerJaminan.getSelectedItemPosition() == 1) {
                    TAHUN_MOBIL_ITEMS.clear();
                    TAHUN_MOBIL_ITEMS.add("2006");
                    TAHUN_MOBIL_ITEMS.add("2007");
                    TAHUN_MOBIL_ITEMS.add("2008");
                    TAHUN_MOBIL_ITEMS.add("2009");
                    TAHUN_MOBIL_ITEMS.add("2010");
                    TAHUN_MOBIL_ITEMS.add("2011");
                    TAHUN_MOBIL_ITEMS.add("2012");
                    TAHUN_MOBIL_ITEMS.add("2013");
                    TAHUN_MOBIL_ITEMS.add("2014");
                    TAHUN_MOBIL_ITEMS.add("2015");
                    TAHUN_MOBIL_ITEMS.add("2016");
                    TAHUN_MOBIL_ITEMS.add("2017");
                    TAHUN_MOBIL_ITEMS.add("2018");
                    ArrayAdapter<String> tahun_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TAHUN_MOBIL_ITEMS);
                    tahun_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTahun.setEnabled(true);
                    spinnerTahun = findViewById(R.id.spinnerTahun);
                    spinnerTahun.setAdapter(tahun_adapter);
                } else if (spinnerJaminan.getSelectedItemPosition() == 2) {
                    TAHUN_MOTOR_ITEMS.clear();
                    TAHUN_MOTOR_ITEMS.add("2011");
                    TAHUN_MOTOR_ITEMS.add("2012");
                    TAHUN_MOTOR_ITEMS.add("2013");
                    TAHUN_MOTOR_ITEMS.add("2014");
                    TAHUN_MOTOR_ITEMS.add("2015");
                    TAHUN_MOTOR_ITEMS.add("2016");
                    TAHUN_MOTOR_ITEMS.add("2017");
                    TAHUN_MOTOR_ITEMS.add("2018");
                    ArrayAdapter<String> tahun_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TAHUN_MOTOR_ITEMS);
                    tahun_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTahun.setEnabled(true);
                    spinnerTahun.setAdapter(tahun_adapter);
                } else {
                    spinnerTahun.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final List<String> TENOR_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> TENOR_DATA = new HashMap<Integer, String>();

        TENOR_ITEMS.clear();
        TENOR_DATA.clear();

        TENOR_DATA.put(1, "12");
        TENOR_DATA.put(2, "18");
        TENOR_DATA.put(3, "24");
        TENOR_DATA.put(4, "30");
        TENOR_DATA.put(5, "36");
        TENOR_DATA.put(6, "42");
        TENOR_DATA.put(7, "48");
        TENOR_ITEMS.add("12 Bulan");
        TENOR_ITEMS.add("18 Bulan");
        TENOR_ITEMS.add("24 Bulan");
        TENOR_ITEMS.add("30 Bulan");
        TENOR_ITEMS.add("36 Bulan");
        TENOR_ITEMS.add("42 Bulan");
        TENOR_ITEMS.add("48 Bulan");


        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTenor.setAdapter(tenor_adapter);


        //InterfaceCreateOrder apiServiceArea =
                //RetrofitClient.getClient().create(InterfaceCreateOrder.class);

        InterfaceAreaBranch apiServiceArea = RetrofitClient.getClient().create(InterfaceAreaBranch.class);

        Call<Area> callArea = apiServiceArea.getArea();
        callArea.enqueue(new Callback<Area>() {
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {

                AREA_MAP.clear();
                AREA_ITEMS.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    AREA_MAP.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getName());
                    AREA_ITEMS.add(response.body().getData().get(i).getName());
                    Log.d("Area Loop", "Masuk Loop");
                }

                Log.d("Area ", Arrays.asList(AREA_MAP).toString());
                Log.d("Area Result ", String.valueOf(response.body().getData().size()));
            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                AREA_MAP.clear();
                AREA_ITEMS.clear();
                Log.e("Area Error", t.getMessage());
            }
        });

        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerArea.setAdapter(area_adapter);
        spinnerCabang.setEnabled(false);




        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Log.d("Area 2", );

                InterfaceAreaBranch apiServiceBranch = RetrofitClient.getClient().create(InterfaceAreaBranch.class);


                Call<Branch> callBranch = apiServiceBranch.getBranch(AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                callBranch.enqueue(new Callback<Branch>() {
                    @Override
                    public void onResponse(Call<Branch> call, Response<Branch> response) {

                        CABANG_MAP.clear();
                        CABANG_ITEMS.clear();

                        for ( int i = 0; i < response.body().getData().size(); i++ ) {
                            CABANG_MAP.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getId().toString());
                            CABANG_ITEMS.add(response.body().getData().get(i).getName());
                        }

                        Log.d("Area ", Arrays.asList(CABANG_MAP).toString());

                        ArrayAdapter<String> branch_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, CABANG_ITEMS);
                        branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerCabang.setEnabled(true);
                        spinnerCabang.setAdapter(branch_adapter);
                    }

                    @Override
                    public void onFailure(Call<Branch> call, Throwable t) {
                        CABANG_MAP.clear();
                        CABANG_ITEMS.clear();
                        spinnerCabang.setEnabled(false);

                        Log.e("Error Cabang", t.getMessage());
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                AREA_ITEMS.clear();
                AREA_MAP.clear();
                CABANG_MAP.clear();
                CABANG_ITEMS.clear();
                spinnerCabang.setEnabled(false);
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ajukanpengajuan","program_id:" + "1");
                Log.d("ajukanpengajuan","axi_referral:" + inputId.getText().toString());
                Log.d("ajukanpengajuan","colleteral_id:" + "1");
                Log.d("ajukanpengajuan","manufacturer:" + inputMerk.getText().toString());
                Log.d("ajukanpengajuan","year:" + spinnerTahun.getSelectedItem().toString());
                Log.d("ajukanpengajuan","tenor:" + TENOR_DATA.get(spinnerTenor.getSelectedItemPosition()));
                Log.d("ajukanpengajuan","amount:" + inputPinjaman.getText().toString().replace(".",""));
                Log.d("ajukanpengajuan","area_id:" + AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                Log.d("ajukanpengajuan","branch_id:" + CABANG_MAP.get(spinnerCabang.getSelectedItemPosition()));

                Intent intent = new Intent(getBaseContext(), AjukanPengajuan2Activity.class);
                intent.putExtra("program_id","1");
                intent.putExtra("axi_referral",inputId.getText().toString());
                intent.putExtra("colleteral_id","1");
                intent.putExtra("manufacturer", inputMerk.getText().toString());
                intent.putExtra("year", spinnerTahun.getSelectedItem().toString());
                intent.putExtra("tenor", TENOR_DATA.get(spinnerTenor.getSelectedItemPosition()));
                intent.putExtra("ammount", inputPinjaman.getText().toString().replace(".",""));
                intent.putExtra("area_id", AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                intent.putExtra("branch_id", CABANG_MAP.get(spinnerCabang.getSelectedItemPosition()));

                startActivity(intent);
            }
        });

    }

    private void setCurrency(final EditText edt) {
        edt.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    edt.removeTextChangedListener(this);

                    Locale local = new Locale("id", "id");
                    String replaceable = String.format("[Rp,.\\s]",
                            NumberFormat.getCurrencyInstance().getCurrency()
                                    .getSymbol(local));
                    String cleanString = s.toString().replaceAll(replaceable,
                            "");

                    double parsed;
                    try {
                        parsed = Double.parseDouble(cleanString);
                    } catch (NumberFormatException e) {
                        parsed = 0.00;
                    }

                    NumberFormat formatter = NumberFormat
                            .getCurrencyInstance(local);
                    formatter.setMaximumFractionDigits(0);
                    formatter.setParseIntegerOnly(true);
                    String formatted = formatter.format((parsed));

                    String replace = String.format("[Rp\\s]",
                            NumberFormat.getCurrencyInstance().getCurrency()
                                    .getSymbol(local));
                    String clean = formatted.replaceAll(replace, "");

                    current = formatted;
                    edt.setText(clean);
                    edt.setSelection(clean.length());
                    edt.addTextChangedListener(this);
                }
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
}
