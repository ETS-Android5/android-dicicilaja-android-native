package com.dicicilaja.app.OrderIn.UI;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.OrderIn.Data.Kecamatan.Kecamatan;
import com.dicicilaja.app.OrderIn.Data.Kelurahan.Kelurahan;
import com.dicicilaja.app.OrderIn.Data.Kota.Kota;
import com.dicicilaja.app.OrderIn.Data.Provinsi.Provinsi;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.dicicilaja.app.OrderIn.Session.SessionOrderIn;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCalonPeminjamActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_nama_lengkap)
    EditText etNamaLengkap;
    @BindView(R.id.et_nomor_ktp)
    EditText etNomorKtp;
    @BindView(R.id.et_alamat_email)
    EditText etAlamatEmail;
    @BindView(R.id.et_no_handphone)
    EditText etNoHandphone;
    @BindView(R.id.spinnerProvinsi)
    SearchableSpinner spinnerProvinsi;
    @BindView(R.id.layoutProvinsi)
    TextInputLayout layoutProvinsi;
    @BindView(R.id.layoutKota)
    TextInputLayout layoutKota;
    @BindView(R.id.et_alamat)
    EditText etAlamat;
    @BindView(R.id.spinnerKota)
    SearchableSpinner spinnerKota;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.spinnerKecamatan)
    SearchableSpinner spinnerKecamatan;
    @BindView(R.id.layoutKecamatan)
    TextInputLayout layoutKecamatan;
    @BindView(R.id.spinnerKelurahan)
    SearchableSpinner spinnerKelurahan;
    @BindView(R.id.layoutKelurahan)
    TextInputLayout layoutKelurahan;

    SessionOrderIn session;

    ApiService2 apiServiceArea;

    String name, no_ktp, email, no_hp, province_id, province, city_id, city, district_id, district, village_id, village, address, postal_code, agen_id, amount, ktp_image, bpkb, vehicle_id, voucher_code_id;;

    final List<String> PROVINSI_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> PROVINSI_DATA = new HashMap<Integer, String>();
    final List<String> KOTA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> KOTA_DATA = new HashMap<Integer, String>();
    final List<String> KECAMATAN_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> KECAMATAN_DATA = new HashMap<Integer, String>();
    final List<String> KELURAHAN_ITEMS = new ArrayList<>();
    final List<String> KELURAHAN_KODEPOS = new ArrayList<>();
    final HashMap<Integer, String> KELURAHAN_DATA = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_calon_peminjam);
        ButterKnife.bind(this);

        session = new SessionOrderIn(DataCalonPeminjamActivity.this);

        initToolbar();
        initAction();
        initLoadData();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data Calon Peminjam");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initAction() {

        agen_id = getIntent().getStringExtra("agen_id");
        amount = getIntent().getStringExtra("amount");
        ktp_image = getIntent().getStringExtra("ktp_image");
        bpkb = getIntent().getStringExtra("bpkb");
        vehicle_id = getIntent().getStringExtra("vehicle_id");
        voucher_code_id = getIntent().getStringExtra("voucher_code_id");

        //Initialize
        progressBar.setVisibility(View.GONE);
        spinnerKota.setEnabled(false);
        spinnerKecamatan.setEnabled(false);
        spinnerKelurahan.setEnabled(false);

        clearProvinsi();
        clearKota();
        clearKecamatan();
        clearKelurahan();

        //Network
        apiServiceArea = ApiClient2.getClient().create(ApiService2.class);
    }

    private void clearProvinsi() {
        PROVINSI_DATA.clear();
        PROVINSI_ITEMS.clear();
        PROVINSI_DATA.put(0, "0");
        PROVINSI_ITEMS.add("Pilih Provinsi");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, PROVINSI_ITEMS);
        provinsi_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvinsi.setAdapter(provinsi_adapter);
        spinnerProvinsi.setTitle("");
        spinnerProvinsi.setPositiveButton("OK");
    }

    private void clearKota() {
        KOTA_DATA.clear();
        KOTA_ITEMS.clear();
        KOTA_DATA.put(0, "0");
        KOTA_ITEMS.add("Pilih Kota");
        ArrayAdapter<String> kota_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KOTA_ITEMS);
        kota_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKota.setAdapter(kota_adapter);
        spinnerKota.setTitle("");
        spinnerKota.setPositiveButton("OK");
        spinnerKota.setEnabled(false);
    }

    private void clearKecamatan() {
        KECAMATAN_DATA.clear();
        KECAMATAN_ITEMS.clear();
        KECAMATAN_DATA.put(0, "0");
        KECAMATAN_ITEMS.add("Pilih Kecamatan");
        ArrayAdapter<String> kecamatan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KECAMATAN_ITEMS);
        kecamatan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKecamatan.setAdapter(kecamatan_adapter);
        spinnerKecamatan.setTitle("");
        spinnerKecamatan.setPositiveButton("OK");
        spinnerKecamatan.setEnabled(false);
    }

    private void clearKelurahan() {
        KELURAHAN_DATA.clear();
        KELURAHAN_ITEMS.clear();
        KELURAHAN_KODEPOS.clear();
        KELURAHAN_DATA.put(0, "0");
        KELURAHAN_ITEMS.add("Pilih Kelurahan");
        KELURAHAN_KODEPOS.add("0");
        ArrayAdapter<String> kelurahan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KELURAHAN_ITEMS);
        kelurahan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKelurahan.setAdapter(kelurahan_adapter);
        spinnerKelurahan.setTitle("");
        spinnerKelurahan.setPositiveButton("OK");
        spinnerKelurahan.setEnabled(false);
    }

    private void initLoadData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<Provinsi> call = apiServiceArea.getProvinsi(1000);
        call.enqueue(new Callback<Provinsi>() {
            @Override
            public void onResponse(Call<Provinsi> call, Response<Provinsi> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getData().size() > 0) {
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                PROVINSI_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                PROVINSI_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
                            }
                            progressBar.setVisibility(View.GONE);
                        } else {
                            clearProvinsi();
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data provinsi belum tersedia, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    } catch (Exception ex) {
                    }

                    ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, PROVINSI_ITEMS);
                    area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProvinsi.setAdapter(area_adapter);
                    spinnerProvinsi.setTitle("");
                    spinnerProvinsi.setPositiveButton("OK");
                } else {
                    clearProvinsi();
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data provinsi gagal dipanggil, silahkan coba beberapa saat lagi.");

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
            public void onFailure(Call<Provinsi> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data provinsi gagal dipanggil, silahkan coba beberapa saat lagi.");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                alertDialog.show();
            }
        });

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                clearKota();
                clearKecamatan();
                clearKelurahan();
                if (Integer.parseInt(PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition())) > 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    Call<Kota> call = apiServiceArea.getKota(PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition()), 1000);
                    call.enqueue(new Callback<Kota>() {
                        @Override
                        public void onResponse(Call<Kota> call, Response<Kota> response) {
                            if (response.isSuccessful()) {
                                try {
                                    if (response.body().getData().size() > 0) {
                                        for (int i = 0; i < response.body().getData().size(); i++) {
                                            KOTA_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                            KOTA_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        clearKota();
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");

                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                } catch (Exception ex) {
                                }

                                ArrayAdapter<String> brand_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KOTA_ITEMS);
                                brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                spinnerKota.setAdapter(brand_adapter);
                                spinnerKota.setTitle("");
                                spinnerKota.setPositiveButton("OK");
                                spinnerKota.setEnabled(true);
                            } else {
                                clearKota();
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                        public void onFailure(Call<Kota> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    });

                    spinnerKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            clearKecamatan();
                            clearKelurahan();
                            if (Integer.parseInt(KOTA_DATA.get(spinnerKota.getSelectedItemPosition())) > 0) {
                                progressBar.setVisibility(View.VISIBLE);
                                Call<Kecamatan> call = apiServiceArea.getKecamatan(KOTA_DATA.get(spinnerKota.getSelectedItemPosition()), 1000);
                                call.enqueue(new Callback<Kecamatan>() {
                                    @Override
                                    public void onResponse(Call<Kecamatan> call, Response<Kecamatan> response) {
                                        if (response.isSuccessful()) {
                                            try {
                                                if (response.body().getData().size() > 0) {
                                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                                        KECAMATAN_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                                        KECAMATAN_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
                                                    }
                                                    progressBar.setVisibility(View.GONE);
                                                } else {
                                                    clearKecamatan();
                                                    progressBar.setVisibility(View.GONE);
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                                                    alertDialog.setTitle("Perhatian");
                                                    alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");

                                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                            startActivity(getIntent());
                                                        }
                                                    });
                                                    alertDialog.show();
                                                }
                                            } catch (Exception ex) {
                                            }

                                            ArrayAdapter<String> kecamatan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KECAMATAN_ITEMS);
                                            kecamatan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                            spinnerKecamatan.setAdapter(kecamatan_adapter);
                                            spinnerKecamatan.setTitle("");
                                            spinnerKecamatan.setPositiveButton("OK");
                                            spinnerKecamatan.setEnabled(true);
                                        } else {
                                            clearKecamatan();
                                            progressBar.setVisibility(View.GONE);
                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                                            alertDialog.setTitle("Perhatian");
                                            alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                    public void onFailure(Call<Kecamatan> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");

                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                });

                                spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        clearKelurahan();
                                        if (Integer.parseInt(KECAMATAN_DATA.get(spinnerKecamatan.getSelectedItemPosition())) > 0) {
                                            progressBar.setVisibility(View.VISIBLE);
                                            Call<Kelurahan> call = apiServiceArea.getKelurahan(KECAMATAN_DATA.get(spinnerKecamatan.getSelectedItemPosition()), 1000);
                                            call.enqueue(new Callback<Kelurahan>() {
                                                @Override
                                                public void onResponse(Call<Kelurahan> call, Response<Kelurahan> response) {
                                                    if (response.isSuccessful()) {
                                                        try {
                                                            if (response.body().getData().size() > 0) {
                                                                for (int i = 0; i < response.body().getData().size(); i++) {
                                                                    KELURAHAN_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                                                    KELURAHAN_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
                                                                    KELURAHAN_KODEPOS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getKodePos())));
                                                                }
                                                                progressBar.setVisibility(View.GONE);
                                                            } else {
                                                                clearKelurahan();
                                                                progressBar.setVisibility(View.GONE);
                                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                                                                alertDialog.setTitle("Perhatian");
                                                                alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");

                                                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        finish();
                                                                        startActivity(getIntent());
                                                                    }
                                                                });
                                                                alertDialog.show();
                                                            }
                                                        } catch (Exception ex) {
                                                        }

                                                        ArrayAdapter<String> kelurahan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KELURAHAN_ITEMS);
                                                        kelurahan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                        spinnerKelurahan.setAdapter(kelurahan_adapter);
                                                        spinnerKelurahan.setTitle("");
                                                        spinnerKelurahan.setPositiveButton("OK");
                                                        spinnerKelurahan.setEnabled(true);
                                                    } else {
                                                        clearKelurahan();
                                                        progressBar.setVisibility(View.GONE);
                                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                                                        alertDialog.setTitle("Perhatian");
                                                        alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                                public void onFailure(Call<Kelurahan> call, Throwable t) {
                                                    progressBar.setVisibility(View.GONE);
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                                                    alertDialog.setTitle("Perhatian");
                                                    alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");

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

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        try {
            name = etNamaLengkap.getText().toString();
            no_ktp = etNomorKtp.getText().toString();
            email = etAlamatEmail.getText().toString();
            no_hp = etNoHandphone.getText().toString();
            province_id = PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition());
            province = PROVINSI_ITEMS.get(spinnerProvinsi.getSelectedItemPosition());
            city_id = KOTA_DATA.get(spinnerKota.getSelectedItemPosition());
            city = KOTA_ITEMS.get(spinnerKota.getSelectedItemPosition());
            district_id = KECAMATAN_DATA.get(spinnerKecamatan.getSelectedItemPosition());
            district = KECAMATAN_ITEMS.get(spinnerKecamatan.getSelectedItemPosition());
            village_id = KELURAHAN_DATA.get(spinnerKelurahan.getSelectedItemPosition());
            village = KELURAHAN_ITEMS.get(spinnerKelurahan.getSelectedItemPosition());
            postal_code = KELURAHAN_KODEPOS.get(spinnerKelurahan.getSelectedItemPosition());
            address = etAlamat.getText().toString();

        } catch (Exception ex) {
        }

        Log.d("TAGTAG", "name: " + name);
        Log.d("TAGTAG", "no_ktp: " + no_ktp);
        Log.d("TAGTAG", "email: " + email);
        Log.d("TAGTAG", "no_hp: " + no_hp);
        Log.d("TAGTAG", "province_id: " + province_id);
        Log.d("TAGTAG", "province: " + province);
        Log.d("TAGTAG", "city_id: " + city_id);
        Log.d("TAGTAG", "city: " + city);
        Log.d("TAGTAG", "district_id: " + district_id);
        Log.d("TAGTAG", "district: " + district);
        Log.d("TAGTAG", "village_id: " + village_id);
        Log.d("TAGTAG", "village: " + village);
        Log.d("TAGTAG", "postal_code: " + postal_code);
        Log.d("TAGTAG", "address: " + address);

        if (validateForm(name, no_ktp, email, no_hp, province_id, city_id, district_id, village_id, address)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Apakah data yang Anda masukan sudah benar?");

            alertDialog.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    session.editDataCalonPeminjam(true,
                            name,
                            no_ktp,
                            email,
                            no_hp,
                            province_id,
                            province,
                            city_id,
                            city,
                            district_id,
                            district,
                            address,
                            postal_code
                    );
                    finish();
                }
            });
            alertDialog.show();

        }
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            showSoftKeyboard(view);
        }
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    private boolean validateForm(String name, String no_ktp, String email, String no_hp, String province_id, String city_id, String district_id, String village_id, String address) {
        if (name == null || name.trim().length() == 0 || name.equals("0") || name.equals("") || name.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etNamaLengkap);
                }
            });
            alertDialog.show();
            return false;
        }

        if (no_ktp == null || no_ktp.trim().length() == 0 || no_ktp.equals("0") || no_ktp.equals("") || no_ktp.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan nomor KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etNomorKtp);
                }
            });
            alertDialog.show();
            return false;
        } else if (no_ktp.length() < 16) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan nomor KTP dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etNomorKtp);
                }
            });
            alertDialog.show();
            return false;
        }

        if (email == null || email.trim().length() == 0 || email.equals("0") || email.equals("") || email.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan alamat email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etAlamatEmail);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isEmailValid(email)){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan alamat email dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etAlamatEmail);
                }
            });
            alertDialog.show();
            return false;
        }

        if (no_hp == null || no_hp.trim().length() == 0 || no_hp.equals("0") || no_hp.equals("") || no_hp.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan nomor HP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etNoHandphone);
                }
            });
            alertDialog.show();
            return false;
        }

        if (province_id == null || province_id.trim().length() == 0 || province_id.equals("0") || province_id.equals("") || province_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih provinsi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerProvinsi);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerProvinsi.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (city_id == null || city_id.trim().length() == 0 || city_id.equals("0") || city_id.equals("") || city_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih kota");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerKota);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerKota.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (district_id == null || district_id.trim().length() == 0 || district_id.equals("0") || district_id.equals("") || district_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih kecamatan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerKecamatan);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerKecamatan.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (village_id == null || village_id.trim().length() == 0 || village_id.equals("0") || village_id.equals("") || village_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih kelurahan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerKelurahan);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerKelurahan.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (address == null || address.trim().length() == 0 || address.equals("0") || address.equals("") || address.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan alamat");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etAlamat);
                }
            });
            alertDialog.show();
            return false;
        }

//        if (postal_code == null || postal_code.trim().length() == 0 || postal_code.equals("0") || postal_code.equals("") || postal_code.equals(" ")) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Silahkan masukan kode pos");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(etKodePos);
//                }
//            });
//            alertDialog.show();
//            return false;
//        }
        return true;
    }

    public static String toTitleCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
