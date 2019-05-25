package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.ganfra.materialspinner.MaterialSpinner;

public class NewLoanActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerTenor)
    MaterialSpinner spinnerTenor;
    @BindView(R.id.layoutTenor)
    TextInputLayout layoutTenor;
    @BindView(R.id.spinnerArea)
    MaterialSpinner spinnerArea;
    @BindView(R.id.layoutArea)
    TextInputLayout layoutArea;
    @BindView(R.id.icon_help1)
    ImageView iconHelp1;
    @BindView(R.id.spinnerInstallment)
    MaterialSpinner spinnerInstallment;
    @BindView(R.id.layoutInstallment)
    TextInputLayout layoutInstallment;
    @BindView(R.id.icon_help2)
    ImageView iconHelp2;
    @BindView(R.id.spinnerInsurance)
    MaterialSpinner spinnerInsurance;
    @BindView(R.id.layoutInsurance)
    TextInputLayout layoutInsurance;
    @BindView(R.id.next)
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_loan);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Informasi Pinjaman");

        final List<String> TENOR_ITEMS = new ArrayList<>();
        final List<String> AREA_ITEMS = new ArrayList<>();
        final List<String> JENISANGSURAN_ITEMS = new ArrayList<>();
        final List<String> TIPEASURANSI_ITEMS = new ArrayList<>();

        TENOR_ITEMS.clear();
        TENOR_ITEMS.add("6 Bulan");
        TENOR_ITEMS.add("12 Bulan");
        TENOR_ITEMS.add("24 Bulan");
        TENOR_ITEMS.add("36 Bulan");
        TENOR_ITEMS.add("72 Bulan");
        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TENOR_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTenor.setAdapter(tenor_adapter);

        AREA_ITEMS.clear();
        AREA_ITEMS.add("Jabodetabekser");
        AREA_ITEMS.add("Jawa Barat");
        AREA_ITEMS.add("Jawa Tengah");
        AREA_ITEMS.add("Jawa Timur");
        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(area_adapter);

        JENISANGSURAN_ITEMS.clear();
        JENISANGSURAN_ITEMS.add("Angsuran Per Minggu");
        JENISANGSURAN_ITEMS.add("Angsuran Per Bulan");
        JENISANGSURAN_ITEMS.add("Angsuran Per Tahun");
        ArrayAdapter<String> jenisangsuran_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JENISANGSURAN_ITEMS);
        jenisangsuran_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstallment.setAdapter(jenisangsuran_adapter);

        TIPEASURANSI_ITEMS.clear();
        TIPEASURANSI_ITEMS.add("Total Risk Only");
        TIPEASURANSI_ITEMS.add("Total Risk and Task");
        ArrayAdapter<String> tipeasuransi_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TIPEASURANSI_ITEMS);
        tipeasuransi_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInsurance.setAdapter(tipeasuransi_adapter);
    }

    @OnClick({R.id.icon_help1, R.id.icon_help2, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_help1:
                break;
            case R.id.icon_help2:
                break;
            case R.id.next:
                Intent intent = new Intent(getBaseContext(), NewSimulationResultActivity.class);
                startActivity(intent);
                break;
        }
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
            case R.id.notif:
                Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
