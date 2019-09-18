package com.dicicilaja.app.OrderIn.UI;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.OrderIn.Network.ApiClient;
import com.dicicilaja.app.OrderIn.Network.ApiService;
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
    @BindView(R.id.spinnerYear)
    SearchableSpinner spinnerYear;
    @BindView(R.id.layoutYear)
    TextInputLayout layoutYear;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    ApiService apiService;

    final List<String> JAMINAN_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JAMINAN_DATA = new HashMap<Integer, String>();
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
        setContentView(R.layout.activity_informasi_jaminan);
        ButterKnife.bind(this);

        initToolbar();
        initAction();
//        initLoadData();
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
        progressBar.setVisibility(View.GONE);
        spinnerArea.setEnabled(false);
        spinnerBrand.setEnabled(false);
        spinnerType.setEnabled(false);
        spinnerYear.setEnabled(false);

        clearArea();
        clearBrand();
        clearType();
        clearYear();

        //Network
        apiService = ApiClient.getClient().create(ApiService.class);

        JAMINAN_ITEMS.clear();
        JAMINAN_DATA.clear();

        JAMINAN_DATA.put(0, "0");
        JAMINAN_DATA.put(1, "1");
        JAMINAN_DATA.put(2, "2");

        JAMINAN_ITEMS.add("Pilih Jaminan");
        JAMINAN_ITEMS.add("Mobil");
        JAMINAN_ITEMS.add("Motor");

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
