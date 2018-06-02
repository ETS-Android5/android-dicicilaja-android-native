package com.dicicilaja.dicicilaja.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceSimulation;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceSimulationProcess;
import com.dicicilaja.dicicilaja.API.Item.Colleteral.Colleteral;
import com.dicicilaja.dicicilaja.API.Item.Simulation.Simulation;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAreaBranch;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimulationActivity extends AppCompatActivity {

    Button btn_hitung;
    com.dicicilaja.dicicilaja.Remote.AreaService AreaService;
    MaterialSpinner spinnerJaminan,spinnerArea,spinnerTenor;
    Integer jaminan_value, area_value, tenor_value;

    EditText harga_simulasi;
    fr.ganfra.materialspinner.MaterialSpinner jaminan, tenor, arearequest;


    String v_harga_simulasi;
    String v_tenor;

    HashMap<Integer, String> JAMINAN_DATA;
    HashMap<Integer, String> TENOR_DATA;
    HashMap<Integer, String> AREA_DATA;
    TextView simulasi_title, simulasi_subtitle;

    String s_area, s_jaminan, s_tenor, s_harga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        simulasi_title = findViewById(R.id.simulasi_title);
        simulasi_subtitle = findViewById(R.id.simulasi_subtitle);
        btn_hitung = findViewById(R.id.btn_hitung);
        harga_simulasi = findViewById(R.id.harga_simulasi);
        jaminan = findViewById(R.id.jaminan);
        tenor = findViewById(R.id.tenor);
        arearequest = findViewById(R.id.arearequest);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        simulasi_title.setTypeface(opensans_semibold);
        simulasi_subtitle.setTypeface(opensans_reguler);

        setCurrency(harga_simulasi);

        final List<String> JAMINAN_ITEMS = new ArrayList<>();
        JAMINAN_DATA = new HashMap<Integer, String>();

        InterfaceSimulation apiServiceColleteral =
                NewRetrofitClient.getClient().create(InterfaceSimulation.class);

        Call<Colleteral> callcolleteral = apiServiceColleteral.getColleteral();
        callcolleteral.enqueue(new Callback<Colleteral>() {
            @Override
            public void onResponse(Call<Colleteral> call, Response<Colleteral> response) {

                JAMINAN_ITEMS.clear();
                JAMINAN_DATA.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    JAMINAN_DATA.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getName());
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

        spinnerJaminan = findViewById(R.id.jaminan);
        spinnerJaminan.setAdapter(jaminan_adapter);
        spinnerJaminan.setTypeface(opensans_semibold);


        final List<String> AREA_ITEMS = new ArrayList<>();
        AREA_DATA = new HashMap<Integer, String>();

//        InterfaceSimulation apiServiceArea =
//                NewRetrofitClient.getClient().create(InterfaceSimulation.class);


        InterfaceAreaBranch apiServiceArea1 = RetrofitClient.getClient().create(InterfaceAreaBranch.class);

        Call<Area> callarea = apiServiceArea1.getArea();
        callarea.enqueue(new Callback<Area>() {
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {

                AREA_ITEMS.clear();
                AREA_DATA.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    AREA_DATA.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getName());
                    AREA_ITEMS.add(response.body().getData().get(i).getName());
                }


            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                AREA_DATA.clear();
                AREA_ITEMS.clear();
                Log.e("Error", t.getMessage());
            }
        });

        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerArea = findViewById(R.id.arearequest);
        spinnerArea.setAdapter(area_adapter);
        spinnerArea.setTypeface(opensans_semibold);

        final List<String> TENOR_ITEMS = new ArrayList<>();
        TENOR_DATA = new HashMap<Integer, String>();

        TENOR_ITEMS.clear();
        TENOR_DATA.clear();

        TENOR_ITEMS.add("12");
        TENOR_ITEMS.add("18");
        TENOR_ITEMS.add("24");
        TENOR_ITEMS.add("30");
        TENOR_ITEMS.add("36");
        TENOR_ITEMS.add("42");
        TENOR_ITEMS.add("48");
        TENOR_DATA.put(1, "12");
        TENOR_DATA.put(2, "18");
        TENOR_DATA.put(3, "24");
        TENOR_DATA.put(4, "30");
        TENOR_DATA.put(5, "36");
        TENOR_DATA.put(6, "42");
        TENOR_DATA.put(7, "48");

        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTenor = findViewById(R.id.tenor);
        spinnerTenor.setAdapter(tenor_adapter);
        spinnerTenor.setTypeface(opensans_semibold);

        harga_simulasi = findViewById(R.id.harga_simulasi);
        jaminan = findViewById(R.id.jaminan);
        tenor = findViewById(R.id.tenor);
        arearequest = findViewById(R.id.arearequest);

        tenor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                v_tenor = tenor.getItemAtPosition(i).toString();
                tenor_value = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        jaminan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jaminan_value = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        arearequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                area_value = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        v_harga_simulasi = harga_simulasi.getText().toString();



        btn_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    s_area = String.valueOf(area_value);
                    s_jaminan = String.valueOf(jaminan_value);
                    s_tenor = String.valueOf(tenor_value);
                    s_harga = harga_simulasi.getText().toString();
                }catch (Exception ex) {

                }
                if(validateForm(s_area, s_jaminan, s_tenor, s_harga)) {
                    hitungSimulasi(area_value.toString(), jaminan_value.toString(), harga_simulasi.getText().toString(), v_tenor);
                }
            }
        });

    }

    private boolean validateForm(String s_area, String s_jaminan, String s_tenor, String s_harga) {
        if (s_harga == null || s_harga.trim().length() == 0 || s_harga.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
            alertDialog.setMessage("Masukan jumlah pinjaman");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(harga_simulasi);
                    harga_simulasi.performClick();
                }
            });
            alertDialog.show();
            return false;
        }
        if (s_jaminan == null || s_jaminan.trim().length() == 0 || s_jaminan.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
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

        if (s_tenor == null || s_tenor.trim().length() == 0 || s_tenor.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
            alertDialog.setMessage("Pilih tenor");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerTenor);
                    spinnerTenor.performClick();
                }
            });
            alertDialog.show();
            return false;
        }
        if (s_area == null || s_area.trim().length() == 0 || s_area.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
            alertDialog.setMessage("Pilih area");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerArea);
                    spinnerArea.performClick();
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

    private void hitungSimulasi(final String area, final String jaminan, final String harga, final String tenor) {
        InterfaceSimulationProcess interfaceSimulationProcess =
                NewRetrofitClient.getClient().create(InterfaceSimulationProcess.class);

        Call<Simulation> call = interfaceSimulationProcess.assign(area,jaminan, harga, tenor);
        call.enqueue(new Callback<Simulation>() {
            @Override
            public void onResponse(Call<Simulation> call, Response<Simulation> response) {
                Simulation simulation = response.body();

                Intent intent = new Intent(getBaseContext(), SimulasiActivity.class);
                intent.putExtra("HARGA_SIMULASI", harga_simulasi.getText().toString().replace(".",""));
                intent.putExtra("JAMINAN",JAMINAN_DATA.get(jaminan_value));
                intent.putExtra("TENOR",TENOR_DATA.get(tenor_value));
                intent.putExtra("AREAREQUEST",AREA_DATA.get(area_value));
                intent.putExtra("HASIL",simulation.getInstallmentAmount().toString());

                intent.putExtra("text_harga", harga_simulasi.getText().toString().replace(".",""));
                intent.putExtra("spinner_jaminan",String.valueOf(spinnerJaminan.getSelectedItemPosition()));
                intent.putExtra("spinner_tenor",String.valueOf(spinnerTenor.getSelectedItemPosition()));
                intent.putExtra("spinner_area",String.valueOf(spinnerArea.getSelectedItemPosition()));
//                Toast.makeText(getContext(),"area : " + AREA_DATA.get(area_value) + " kode_area : " + spinnerArea.getSelectedItemPosition(),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Simulation> call, Throwable t) {

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
