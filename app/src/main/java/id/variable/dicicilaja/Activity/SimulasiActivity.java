package id.variable.dicicilaja.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceSimulation;
import id.variable.dicicilaja.API.Interface.InterfaceSimulationProcess;
import id.variable.dicicilaja.API.Item.AreaRequest.AreaRequest;
import id.variable.dicicilaja.API.Item.Colleteral.Colleteral;
import id.variable.dicicilaja.API.Item.Simulation.Simulation;
import id.variable.dicicilaja.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimulasiActivity extends AppCompatActivity {

    MaterialSpinner spinnerJaminan,spinnerArea,spinnerTenor;
    EditText harga_simulasi;

    Integer jaminan_int, area_int, tenor_int;
    String harga_value, jaminan_value, area_value, tenor_value;
    TextView hasil;
    Button btn_hitung;

    Integer jaminan_value1, area_value1, tenor_value1;

    String v_harga_simulasi;
    String v_tenor;


    fr.ganfra.materialspinner.MaterialSpinner jaminan, tenor, arearequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulasi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        harga_simulasi = findViewById(R.id.harga_simulasi);
        hasil = findViewById(R.id.hasil);
        harga_value = getIntent().getStringExtra("HARGA_SIMULASI");
        jaminan_value = getIntent().getStringExtra("JAMINAN");
        jaminan_int = Integer.parseInt(jaminan_value);
        area_value = getIntent().getStringExtra("AREAREQUEST");
        area_int = Integer.parseInt(area_value);
        tenor_value = getIntent().getStringExtra("TENOR");
        tenor_int = Integer.parseInt(tenor_value);
        hasil.setText(getIntent().getStringExtra("HASIL"));
        btn_hitung = findViewById(R.id.btn_hitung);

        Toast.makeText(getBaseContext(),"harga:"+harga_value+ " jaminan:" + jaminan_value + " area:" + area_value + " tenor:" + tenor_value,Toast.LENGTH_SHORT).show();




        harga_simulasi.setText(harga_value);

        final List<String> JAMINAN_ITEMS = new ArrayList<>();

        InterfaceSimulation apiServiceColleteral =
                NewRetrofitClient.getClient().create(InterfaceSimulation.class);

        Call<Colleteral> callcolleteral = apiServiceColleteral.getColleteral();
        callcolleteral.enqueue(new Callback<Colleteral>() {
            @Override
            public void onResponse(Call<Colleteral> call, Response<Colleteral> response) {

                JAMINAN_ITEMS.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    JAMINAN_ITEMS.add(response.body().getData().get(i).getName());
                }


            }

            @Override
            public void onFailure(Call<Colleteral> call, Throwable t) {
                JAMINAN_ITEMS.clear();
                Log.e("Error", t.getMessage());
            }
        });

        ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
        jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerJaminan = findViewById(R.id.jaminan);
        spinnerJaminan.setAdapter(jaminan_adapter);
        spinnerJaminan.setTypeface(opensans_semibold);
//        spinnerJaminan.setSelection(jaminan_int);


        final List<String> AREA_ITEMS = new ArrayList<>();

        InterfaceSimulation apiServiceArea =
                NewRetrofitClient.getClient().create(InterfaceSimulation.class);

        Call<AreaRequest> callarea = apiServiceArea.getAreaRequest();
        callarea.enqueue(new Callback<AreaRequest>() {
            @Override
            public void onResponse(Call<AreaRequest> call, Response<AreaRequest> response) {

                AREA_ITEMS.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    AREA_ITEMS.add(response.body().getData().get(i).getName());
                }


            }

            @Override
            public void onFailure(Call<AreaRequest> call, Throwable t) {
                AREA_ITEMS.clear();
                Log.e("Error", t.getMessage());
            }
        });

        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerArea = findViewById(R.id.arearequest);
        spinnerArea.setAdapter(area_adapter);
        spinnerArea.setTypeface(opensans_semibold);
        spinnerArea.setSelection(area_int);

        final List<String> TENOR_ITEMS = new ArrayList<>();
        TENOR_ITEMS.add("12");
        TENOR_ITEMS.add("18");
        TENOR_ITEMS.add("24");
        TENOR_ITEMS.add("30");
        TENOR_ITEMS.add("36");
        TENOR_ITEMS.add("42");
        TENOR_ITEMS.add("48");

        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTenor = findViewById(R.id.tenor);
        spinnerTenor.setAdapter(tenor_adapter);
        spinnerTenor.setTypeface(opensans_semibold);
        spinnerTenor.setSelection(tenor_int);


        harga_simulasi = findViewById(R.id.harga_simulasi);
        jaminan = findViewById(R.id.jaminan);
        tenor = findViewById(R.id.tenor);
        arearequest = findViewById(R.id.arearequest);

        tenor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                v_tenor = tenor.getItemAtPosition(i).toString();
                tenor_value1 = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        jaminan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jaminan_value1 = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        arearequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                area_value1 = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        v_harga_simulasi = harga_simulasi.getText().toString();

        btn_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ge(),"area : " + area_value.toString() + " jaminan : " + jaminan_value.toString() + "harga : " + harga_simulasi.getText().toString() + " tenor : " + v_tenor,Toast.LENGTH_SHORT).show();
                hitungSimulasi(area_value1.toString(), jaminan_value1.toString(), harga_simulasi.getText().toString(), v_tenor);
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

                Toast.makeText(getBaseContext(),"code : " + response.code(),Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getBaseContext(), SimulasiActivity.class);
//                intent.putExtra("HARGA_SIMULASI", harga_simulasi.getText().toString());
//                intent.putExtra("JAMINAN",jaminan_value.toString());
//                intent.putExtra("TENOR",tenor_value.toString());
//                intent.putExtra("AREAREQUEST",area_value.toString());
//                intent.putExtra("HASIL",simulation.getInstallmentAmount().toString());
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Simulation> call, Throwable t) {

            }
        });
    }

}
