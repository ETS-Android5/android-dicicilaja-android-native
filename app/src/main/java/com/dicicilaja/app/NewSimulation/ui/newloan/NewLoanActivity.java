package com.dicicilaja.app.NewSimulation.ui.newloan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.NewSimulation.ui.bantuan.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.ui.newsimulationresult.NewSimulationResultActivity;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewLoanActivity extends AppCompatActivity {

    String tipe_objek_id, area_id, tahun_kendaraan, objek_model_id, tenor, tipe_asuransi_id, tipe_angsuran_id;

    final List<String> TENOR_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TENOR_MAP = new HashMap<Integer, String>();

    final List<String> JENISANGSURAN_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JENISANGSURAN_MAP = new HashMap<Integer, String>();

    final List<String> TIPEASURANSI_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> TIPEASURANSI_MAP = new HashMap<Integer, String>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerTenor)
    SearchableSpinner spinnerTenor;
    @BindView(R.id.layoutTenor)
    TextInputLayout layoutTenor;
    @BindView(R.id.icon_help1)
    ImageView iconHelp1;
    @BindView(R.id.spinnerInstallment)
    SearchableSpinner spinnerInstallment;
    @BindView(R.id.layoutInstallment)
    TextInputLayout layoutInstallment;
    @BindView(R.id.icon_help2)
    ImageView iconHelp2;
    @BindView(R.id.spinnerInsurance)
    SearchableSpinner spinnerInsurance;
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

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        tipe_objek_id = getIntent().getStringExtra("tipe_objek_id");
        area_id = getIntent().getStringExtra("area_id");
        tahun_kendaraan = getIntent().getStringExtra("tahun_kendaraan");
        objek_model_id = getIntent().getStringExtra("objek_model_id");

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
        JENISANGSURAN_ITEMS.add("Angsuran Per Bulan");
        JENISANGSURAN_ITEMS.add("Pembayaran Pertama");

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

    @OnClick({R.id.icon_help1, R.id.icon_help2, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_help1:
                Intent icon1 = new Intent(getBaseContext(), BantuanNewSimulationActivity.class);
                startActivity(icon1);
                break;
            case R.id.icon_help2:
                Intent icon2 = new Intent(getBaseContext(), BantuanNewSimulationActivity.class);
                startActivity(icon2);
                break;
            case R.id.next:
                try {
                    tenor = TENOR_MAP.get(spinnerTenor.getSelectedItemPosition());
                    tipe_asuransi_id = TIPEASURANSI_MAP.get(spinnerInsurance.getSelectedItemPosition());
                    tipe_angsuran_id = JENISANGSURAN_MAP.get(spinnerInstallment.getSelectedItemPosition());
                } catch (Exception ex) {

                }

                if (validateForm(tenor, tipe_asuransi_id, tipe_angsuran_id)) {
                    Intent intent = new Intent(getBaseContext(), NewSimulationResultActivity.class);
                    intent.putExtra("tenor", tenor);
                    intent.putExtra("tipe_asuransi_id", tipe_asuransi_id);
                    if (tipe_angsuran_id.equals("1")) {
                        intent.putExtra("tipe_angsuran_id", "addb");
                    } else if (tipe_asuransi_id.equals("2")) {
                        intent.putExtra("tipe_angsuran_id", "addm");
                    }
                    intent.putExtra("tipe_objek_id", tipe_objek_id);
                    intent.putExtra("area_id", area_id);
                    intent.putExtra("tahun_kendaraan", tahun_kendaraan);
                    intent.putExtra("objek_model_id", objek_model_id);
                    startActivity(intent);
                }
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
            case R.id.help:
                Intent intent = new Intent(getBaseContext(), BantuanNewSimulationActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean validateForm(String tenor, String tipe_asuransi_id, String tipe_angsuran_id) {
        if (tenor == null || tenor.trim().length() == 0 || tenor.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewLoanActivity.this);
            alertDialog.setMessage("Pilih Tenor Pinjaman");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerTenor);
                    MotionEvent motionEvent = MotionEvent.obtain( 0, 0, MotionEvent.ACTION_UP, 0, 0, 0 );
                    spinnerTenor.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (tipe_angsuran_id == null || tipe_angsuran_id.trim().length() == 0 || tipe_angsuran_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewLoanActivity.this);
            alertDialog.setMessage("Pilih Jenis Angsuran");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerInstallment);
                    MotionEvent motionEvent = MotionEvent.obtain( 0, 0, MotionEvent.ACTION_UP, 0, 0, 0 );
                    spinnerInstallment.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (tipe_asuransi_id == null || tipe_asuransi_id.trim().length() == 0 || tipe_asuransi_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewLoanActivity.this);
            alertDialog.setMessage("Pilih Tipe Asuransi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerInsurance);
                    MotionEvent motionEvent = MotionEvent.obtain( 0, 0, MotionEvent.ACTION_UP, 0, 0, 0 );
                    spinnerInsurance.dispatchTouchEvent(motionEvent);
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
