package com.dicicilaja.app.NewSimulation.ui.newcolleteral;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.NewSimulation.ui.bantuan.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.ui.newloan.NewLoanActivity;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;
import fr.ganfra.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class NewColleteralActivity extends AppCompatActivity {


    String type;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerArea)
    MaterialSpinner spinnerArea;
    @BindView(R.id.layoutArea)
    TextInputLayout layoutArea;
    @BindView(R.id.spinnerBrand)
    MaterialSpinner spinnerBrand;
    @BindView(R.id.layoutBrand)
    TextInputLayout layoutBrand;
    @BindView(R.id.spinnerType)
    MaterialSpinner spinnerType;
    @BindView(R.id.layoutType)
    TextInputLayout layoutType;
    @BindView(R.id.spinnerYear)
    MaterialSpinner spinnerYear;
    @BindView(R.id.layoutYear)
    TextInputLayout layoutYear;
    @BindView(R.id.next)
    Button next;

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

        type = getIntent().getStringExtra("type");

        final List<String> AREA_ITEMS = new ArrayList<>();
        final List<String> MERK_ITEMS = new ArrayList<>();
        final List<String> TYPE_ITEMS = new ArrayList<>();
        final List<String> YEAR_ITEMS = new ArrayList<>();

        AREA_ITEMS.clear();
        AREA_ITEMS.add("Jabodetabekser");
        AREA_ITEMS.add("Jawa Barat");
        AREA_ITEMS.add("Jawa Tengah");
        AREA_ITEMS.add("Jawa Timur");
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(area_adapter);

        MERK_ITEMS.clear();
        MERK_ITEMS.add(type);
        MERK_ITEMS.add("Nissan");
        MERK_ITEMS.add("Suzuki");
        MERK_ITEMS.add("Mitsubishi");
        MERK_ITEMS.add("Alphard");
        MERK_ITEMS.add("Marcedez");
        MERK_ITEMS.add("Toyota");
        MERK_ITEMS.add("Ford");
        MERK_ITEMS.add("Hyundai");
        MERK_ITEMS.add("Chevrolet");
        ArrayAdapter<String> brand_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, MERK_ITEMS);
        brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(brand_adapter);

        TYPE_ITEMS.clear();
        TYPE_ITEMS.add("New Crv 2.0 M/T DOHC");
        TYPE_ITEMS.add("Skyline GTR R35 Putih");
        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TYPE_ITEMS);
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(type_adapter);

        YEAR_ITEMS.clear();
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
        Intent intent = new Intent(getBaseContext(), NewLoanActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
