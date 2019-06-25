package com.dicicilaja.app.NewSimulation.UI.NewLoan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.NewSimulation.Data.HitungSimulasi.HitungSimulasi;
import com.dicicilaja.app.NewSimulation.Network.ApiClient;
import com.dicicilaja.app.NewSimulation.Network.ApiService;
import com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation.JenisAngsuranActivity;
import com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation.TipeAsuransiActivity;
import com.dicicilaja.app.NewSimulation.UI.NewSimulationResult.NewSimulationResultActivity;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewLoanActivity extends AppCompatActivity {

    String tipe_objek_id, area_id, tahun_kendaraan, objek_model_id, tenor, tenor_simulasi, tipe_asuransi_id, value_tipe_angsuran_id, tipe_angsuran_id;

    String text_total, text_tenor, text_angsuran, text_tenor_angsuran, text_colleteral, text_merk, text_type, text_year, text_insurance, text_area, text_angsuran_baru;

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
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

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
        progressBar.setVisibility(View.GONE);

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

    @OnClick({R.id.icon_help1, R.id.icon_help2, R.id.next})
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
            case R.id.next:

                try {
                    tenor_simulasi = TENOR_MAP.get(spinnerTenor.getSelectedItemPosition());
                    tipe_asuransi_id = TIPEASURANSI_MAP.get(spinnerInsurance.getSelectedItemPosition());
                    tipe_angsuran_id = JENISANGSURAN_MAP.get(spinnerInstallment.getSelectedItemPosition());
                } catch (Exception ex) { }

                if (validateForm(tenor_simulasi, tipe_asuransi_id, tipe_angsuran_id)) {

                    progressBar.setVisibility(View.VISIBLE);

                    tipe_objek_id = getIntent().getStringExtra("tipe_objek_id");
                    area_id = getIntent().getStringExtra("area_id");
                    tahun_kendaraan = getIntent().getStringExtra("tahun_kendaraan");
                    objek_model_id = getIntent().getStringExtra("objek_model_id");
                    if (tipe_angsuran_id.equals("1")) {
                        value_tipe_angsuran_id = "addb";
                    } else if (tipe_asuransi_id.equals("2")) {
                        value_tipe_angsuran_id = "addm";
                    }

                    ApiService apiService = ApiClient.getClient().create(ApiService.class);

                    Call<HitungSimulasi> call = apiService.hitungCar(tipe_objek_id, objek_model_id, tahun_kendaraan, area_id, tenor_simulasi, tipe_asuransi_id, value_tipe_angsuran_id);
                    call.enqueue(new Callback<HitungSimulasi>() {

                        @Override
                        public void onResponse(Call<HitungSimulasi> call, Response<HitungSimulasi> response) {
                            if (response.isSuccessful()) {
                                try {
                                    progressBar.setVisibility(View.GONE);
                                    text_total = response.body().getData().getAttributes().getHasilSimulasi().getDanaDiterima();
                                    text_tenor = String.valueOf(response.body().getData().getAttributes().getInformasiJaminan().getTenor());
                                    text_angsuran = response.body().getData().getAttributes().getHasilSimulasi().getAngsuranPerBulan();
                                    text_tenor_angsuran = "x " + response.body().getData().getAttributes().getInformasiJaminan().getTenor() + " Bulan";
                                    text_colleteral = response.body().getData().getAttributes().getInformasiJaminan().getKendaraan();
                                    text_merk = response.body().getData().getAttributes().getInformasiJaminan().getMerkKendaraan();
                                    text_type = response.body().getData().getAttributes().getInformasiJaminan().getTypeKendaraan();
                                    text_year = response.body().getData().getAttributes().getInformasiJaminan().getTahunKendaraan();
                                    text_angsuran_baru = response.body().getData().getAttributes().getInformasiJaminan().getTipeAngsuran();
                                    text_insurance = response.body().getData().getAttributes().getInformasiJaminan().getTipeAsuransi();
                                    text_area = response.body().getData().getAttributes().getInformasiJaminan().getArea();

                                    Intent intent = new Intent(getBaseContext(), NewSimulationResultActivity.class);
                                    intent.putExtra("text_total", text_total);
                                    intent.putExtra("text_tenor", text_tenor);
                                    intent.putExtra("text_angsuran", text_angsuran);
                                    intent.putExtra("text_tenor_angsuran", text_tenor_angsuran);
                                    intent.putExtra("text_colleteral", text_colleteral);
                                    intent.putExtra("text_merk", text_merk);
                                    intent.putExtra("text_type", text_type);
                                    intent.putExtra("text_year", text_year);
                                    intent.putExtra("text_insurance", text_insurance);
                                    intent.putExtra("text_angsuran_baru", text_angsuran_baru);
                                    intent.putExtra("text_area", text_area);
                                    intent.putExtra("area_id", area_id);
                                    startActivity(intent);
                                } catch (Exception ex) {
                                    progressBar.setVisibility(View.GONE);
                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewLoanActivity.this);
                                    alertDialog.setTitle("Perhatian");
                                    alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            startActivity(getIntent());
                                        }
                                    });
                                    alertDialog.show();
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewLoanActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        startActivity(getIntent());
                                    }
                                });
                                alertDialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<HitungSimulasi> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewLoanActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Gagal melakukan perhitungan simulasi, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    });
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
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih tenor pinjaman");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerTenor);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerTenor.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (tipe_angsuran_id == null || tipe_angsuran_id.trim().length() == 0 || tipe_angsuran_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewLoanActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih jenis angsuran");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerInstallment);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerInstallment.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (tipe_asuransi_id == null || tipe_asuransi_id.trim().length() == 0 || tipe_asuransi_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewLoanActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih tipe asuransi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerInsurance);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
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
