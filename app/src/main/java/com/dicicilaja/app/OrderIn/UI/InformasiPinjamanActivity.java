package com.dicicilaja.app.OrderIn.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation.JenisAngsuranActivity;
import com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation.TipeAsuransiActivity;
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

public class InformasiPinjamanActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerTenor)
    SearchableSpinner spinnerTenor;
    @BindView(R.id.layoutTenor)
    TextInputLayout layoutTenor;
    @BindView(R.id.icon_help1)
    RelativeLayout iconHelp1;
    @BindView(R.id.spinnerInstallment)
    SearchableSpinner spinnerInstallment;
    @BindView(R.id.layoutInstallment)
    TextInputLayout layoutInstallment;
    @BindView(R.id.icon_help2)
    RelativeLayout iconHelp2;
    @BindView(R.id.spinnerInsurance)
    SearchableSpinner spinnerInsurance;
    @BindView(R.id.layoutInsurance)
    TextInputLayout layoutInsurance;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    ApiService apiService;

    final List<String> TENOR_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TENOR_MAP = new HashMap<Integer, String>();

    final List<String> JENISANGSURAN_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JENISANGSURAN_MAP = new HashMap<Integer, String>();

    final List<String> TIPEASURANSI_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TIPEASURANSI_MAP = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_pinjaman);
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

        //Network
        apiService = ApiClient.getClient().create(ApiService.class);

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

        JENISANGSURAN_ITEMS.clear();
        JENISANGSURAN_MAP.clear();

        JENISANGSURAN_MAP.put(0, "0");
        JENISANGSURAN_MAP.put(1, "1");
        JENISANGSURAN_MAP.put(2, "2");

        JENISANGSURAN_ITEMS.add("Pilih Jenis Angsuran");
        JENISANGSURAN_ITEMS.add("Angsuran Per Bulan (ADDB)");
        JENISANGSURAN_ITEMS.add("Pembayaran Pertama (ADDM)");

        ArrayAdapter<String> jenisangsuran_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JENISANGSURAN_ITEMS);
        jenisangsuran_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstallment.setAdapter(jenisangsuran_adapter);
        spinnerInstallment.setTitle("");
        spinnerInstallment.setPositiveButton("OK");

        TIPEASURANSI_MAP.clear();
        TIPEASURANSI_ITEMS.clear();

        TIPEASURANSI_MAP.put(0, "0");
        TIPEASURANSI_MAP.put(1, "1");
        TIPEASURANSI_MAP.put(2, "2");

        TIPEASURANSI_ITEMS.add("Pilih Tipe Asuransi");
        TIPEASURANSI_ITEMS.add("Total Lost Only");
        TIPEASURANSI_ITEMS.add("All Risk");
        ArrayAdapter<String> tipeasuransi_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, TIPEASURANSI_ITEMS);
        tipeasuransi_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInsurance.setAdapter(tipeasuransi_adapter);
        spinnerInsurance.setTitle("");
        spinnerInsurance.setPositiveButton("OK");
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

    @OnClick({R.id.icon_help1, R.id.icon_help2, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_help1:
                Intent intent = new Intent(getBaseContext(), JenisAngsuranActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_help2:
                Intent intent2 = new Intent(getBaseContext(), TipeAsuransiActivity.class);
                startActivity(intent2);
                break;
            case R.id.save:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InformasiPinjamanActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Apakah data yang Anda pilih sudah benar?");

                alertDialog.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alertDialog.show();
                break;
        }
    }
}
