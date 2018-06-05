package com.dicicilaja.dicicilaja.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAreaBranch;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch.Branch;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Colleteral.Colleteral;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjukanPengajuanAxiActivity extends AppCompatActivity {

    MaterialSpinner spinnerJaminan, spinnerTahun, spinnerArea, spinnerCabang, spinnerTenor;
    EditText inputId, inputMerk, inputPinjaman;
    Button next;
    String apiKey;
    TextInputLayout inputLayoutMerk, inputLayoutPinjaman, inputLayoutJaminan;
    String axi_id, merk, pinjam, jaminan, tahun, waktu, area, cabang;

    String s_area, s_jaminan, s_tenor, s_harga;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajukan_pengajuan_axi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

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
        inputLayoutMerk = findViewById(R.id.inputLayoutMerk);
        inputLayoutPinjaman = findViewById(R.id.inputLayoutPinjaman);
        inputLayoutJaminan = findViewById(R.id.inputLayoutJaminan);

        try {
            if(session.getRole().equals("axi")){
                inputId.setText(session.getAxiId());
            }
        }catch (Exception ex){

        }

        inputMerk.addTextChangedListener(new AjukanPengajuanAxiActivity.MyTextWatcher(inputMerk));
        inputPinjaman.addTextChangedListener(new AjukanPengajuanAxiActivity.MyTextWatcher(inputPinjaman));

        setCurrency(inputPinjaman);

        final List<String> JAMINAN_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JAMINAN_MAP = new HashMap<Integer, String>();

        final List<String> AREA_ITEMS = new ArrayList<>();;
        final HashMap<Integer, String> AREA_MAP = new HashMap<Integer, String>();

        final List<String> CABANG_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> CABANG_MAP = new HashMap<Integer, String>();

        InterfaceAreaBranch apiServiceColleteral = RetrofitClient.getClient().create(InterfaceAreaBranch.class);

        Call<Colleteral> callcolleteral = apiServiceColleteral.getColleteral();
        callcolleteral.enqueue(new Callback<Colleteral>() {
            @Override
            public void onResponse(Call<Colleteral> call, Response<Colleteral> response) {

                JAMINAN_ITEMS.clear();
                JAMINAN_MAP.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    JAMINAN_MAP.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getId().toString());
                    JAMINAN_ITEMS.add(response.body().getData().get(i).getName());
                }

                ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
                jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerJaminan.setAdapter(jaminan_adapter);
                try {
                    s_jaminan = getIntent().getStringExtra("spinner_jaminan");
                    if(s_jaminan != null) {
                        spinnerJaminan.setSelection(Integer.parseInt(s_jaminan));

                    }
                }catch (Exception ex) {

                }
            }

            @Override
            public void onFailure(Call<Colleteral> call, Throwable t) {
                JAMINAN_MAP.clear();
                JAMINAN_ITEMS.clear();
                Log.e("Error", t.getMessage());
            }
        });
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
                    AREA_MAP.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getId().toString());
                    AREA_ITEMS.add(response.body().getData().get(i).getName());
                }

                ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
                area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerArea.setAdapter(area_adapter);
                spinnerCabang.setEnabled(false);
                try {
                    s_tenor = getIntent().getStringExtra("spinner_tenor");
                    s_area = getIntent().getStringExtra("spinner_area");
                    if(s_tenor != null && s_area != null){
                        spinnerTenor.setSelection(Integer.parseInt(s_tenor));
                        spinnerArea.setSelection(Integer.parseInt(s_area));
                    }

                }catch (Exception ex) {

                }
            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                AREA_MAP.clear();
                AREA_ITEMS.clear();
                Log.e("Area Error", t.getMessage());
            }
        });



        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                InterfaceAreaBranch apiServiceBranch = RetrofitClient.getClient().create(InterfaceAreaBranch.class);


                Call<Branch> callBranch = apiServiceBranch.getBranch(AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                callBranch.enqueue(new Callback<Branch>() {
                    @Override
                    public void onResponse(Call<Branch> call, Response<Branch> response) {
                        CABANG_MAP.clear();
                        CABANG_ITEMS.clear();

                        for ( int i = 0; i < response.body().getData().size(); i++ ) {
                            CABANG_MAP.put(i+1, response.body().getData().get(i).getId());
                            CABANG_ITEMS.add(response.body().getData().get(i).getName());
                        }

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

//        inputMerk.setHint(Html.fromHtml("Merk <font color='#ff0000'>*</font>"));
//        inputLayoutMerk.setHint(Html.fromHtml("Merk <font color='#ff0000'>*</font>"));
//        spinnerJaminan.setHint(Html.fromHtml("Pilih Jaminan <font color='#ff0000'>*</font>"));
//        spinnerTahun.setHint(Html.fromHtml("Pilih Tahun <font color='#ff0000'>*</font>"));
//        spinnerTenor.setHint(Html.fromHtml("Jangka Waktu <font color='#ff0000'>*</font>"));
//        inputPinjaman.setHint(Html.fromHtml("Nilai Pinjaman <font color='#ff0000'>*</font>"));
//        spinnerArea.setHint(Html.fromHtml("Pilih Area <font color='#ff0000'>*</font>"));
//        spinnerCabang.setHint(Html.fromHtml("Pilih Area <font color='#ff0000'>*</font>"));

        try {
            s_harga = getIntent().getStringExtra("text_harga");
            if(s_harga != null){
                inputPinjaman.setText(s_harga);
            }
        }catch (Exception ex) {

        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    axi_id = inputId.getText().toString();
                    merk = inputMerk.getText().toString();
                    pinjam = inputPinjaman.getText().toString().replace(".","");
                    jaminan = JAMINAN_MAP.get(spinnerJaminan.getSelectedItemPosition());
                    tahun = spinnerTahun.getSelectedItem().toString();
                    waktu = TENOR_DATA.get(spinnerTenor.getSelectedItemPosition());
                    area = AREA_MAP.get(spinnerArea.getSelectedItemPosition());
                    cabang = CABANG_MAP.get(spinnerCabang.getSelectedItemPosition());
                } catch (Exception ex) {

                }
                Log.d("ajukanpengajuan","program_id:" + "1");
                Log.d("ajukanpengajuan","axi_referral:" + axi_id);
                Log.d("ajukanpengajuan","colleteral_id:" + JAMINAN_MAP.get(spinnerJaminan.getSelectedItemPosition()));
                Log.d("ajukanpengajuan","manufacturer:" + inputMerk.getText().toString());
                Log.d("ajukanpengajuan","year:" + spinnerTahun.getSelectedItem());
                Log.d("ajukanpengajuan","tenor:" + TENOR_DATA.get(spinnerTenor.getSelectedItemPosition()));
                Log.d("ajukanpengajuan","amount:" + inputPinjaman.getText().toString().replace(".",""));
                Log.d("ajukanpengajuan","area_id:" + AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                Log.d("ajukanpengajuan","branch_id:" + CABANG_MAP.get(spinnerCabang.getSelectedItemPosition()));


                if(validateForm(merk, pinjam, jaminan, tahun, waktu, area, cabang)) {
                    Intent intent = new Intent(getBaseContext(), AjukanPengajuanAxi2Activity.class);
                    intent.putExtra("program_id","1");
                    intent.putExtra("axi_referral",axi_id);
                    intent.putExtra("colleteral_id",JAMINAN_MAP.get(spinnerJaminan.getSelectedItemPosition()));
                    intent.putExtra("manufacturer", inputMerk.getText().toString());
                    intent.putExtra("year", spinnerTahun.getSelectedItem().toString());
                    intent.putExtra("tenor", TENOR_DATA.get(spinnerTenor.getSelectedItemPosition()));
                    intent.putExtra("ammount", inputPinjaman.getText().toString().replace(".",""));
                    intent.putExtra("area_id", AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                    intent.putExtra("branch_id", CABANG_MAP.get(spinnerCabang.getSelectedItemPosition()));
                    startActivity(intent);
                }
            }
        });

    }


    private boolean validateForm(String merk, String pinjam, String jaminan, String tahun, String waktu, String area, String cabang) {
        if(jaminan == null || jaminan.trim().length() == 0 || jaminan.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxiActivity.this);
            alertDialog.setMessage("Pilih jaminan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJaminan);
                    spinnerJaminan.performClick();
                }
            });
            alertDialog.show();
            return false;
        }

        if(merk == null || merk.trim().length() == 0 || merk.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxiActivity.this);
            alertDialog.setMessage("Masukan merk kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputMerk);
                    hideSoftKeyboard(inputMerk);
                    showSoftKeyboard(inputMerk);
                }
            });
            alertDialog.show();
            return false;
        }

        if(tahun == null || tahun.trim().length() == 0 || tahun.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxiActivity.this);
            alertDialog.setMessage("Pilih tahun kendaraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerTahun);
                    spinnerTahun.performClick();
                }
            });
            alertDialog.show();
            return false;
        }

        if(waktu == null || waktu.trim().length() == 0 || waktu.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxiActivity.this);
            alertDialog.setMessage("Pilih jangka waktu");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerTenor);
                    spinnerTenor.performClick();
                }
            });
            alertDialog.show();
            return false;
        }

        if(pinjam == null || pinjam.trim().length() == 0 || pinjam.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxiActivity.this);
            alertDialog.setMessage("Masukan nilai pinjaman");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputPinjaman);
                }
            });
            alertDialog.show();
            return false;
        }

        if(area == null || area.trim().length() == 0 || area.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxiActivity.this);
            alertDialog.setMessage("Pilih area pengajuan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerArea);
                    spinnerArea.performClick();
                }
            });
            alertDialog.show();
            return false;
        }

        if(cabang == null || cabang.trim().length() == 0 || cabang.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxiActivity.this);
            alertDialog.setMessage("Pilih cabang pengajuan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerCabang);
                    spinnerCabang.performClick();
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }

    private boolean validateMerk() {
        if (inputMerk.getText().toString().trim().isEmpty()) {
            inputLayoutMerk.setError("Masukan merk produk");
            requestFocus(inputMerk);
            return false;
        } else {
            inputLayoutMerk.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePinjam() {
        if (inputPinjaman.getText().toString().trim().isEmpty()) {
            inputLayoutPinjaman.setError("Masukan nilai pinjaman Anda");
            requestFocus(inputPinjaman);
            return false;
        } else {
            inputLayoutPinjaman.setErrorEnabled(false);
        }

        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.inputMerk:
                    validateMerk();
                    break;

                case R.id.inputPinjaman:
                    validatePinjam();
                    break;
            }
        }
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
