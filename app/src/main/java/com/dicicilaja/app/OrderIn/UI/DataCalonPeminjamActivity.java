package com.dicicilaja.app.OrderIn.UI;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.dicicilaja.app.OrderIn.Data.Kecamatan.Kecamatan;
import com.dicicilaja.app.OrderIn.Data.Kelurahan.Kelurahan;
import com.dicicilaja.app.OrderIn.Data.Kota.Kota;
import com.dicicilaja.app.OrderIn.Data.Pekerjaan.Pekerjaan;
import com.dicicilaja.app.OrderIn.Data.Provinsi.Provinsi;
import com.dicicilaja.app.OrderIn.Data.Vehicles.Vehicles;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiClient3;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.OrderIn.Session.SessionOrderIn;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.ganfra.materialspinner.MaterialSpinner;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCalonPeminjamActivity extends AppCompatActivity {

    SessionManager sessionAuth;
    String apiKey;
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
    @BindView(R.id.inputTempatLahir)
    EditText inputTempatLahir;
    @BindView(R.id.inputTangalLahir)
    EditText inputTangalLahir;
    @BindView(R.id.inputIbuKandung)
    EditText inputIbuKandung;
    @BindView(R.id.inputJanjiSurvey)
    EditText inputJanjiSurvey;
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
    @BindView(R.id.layout_nama_lengkap)
    TextInputLayout layoutNamaLengkap;
    @BindView(R.id.layout_email)
    TextInputLayout layoutEmail;
    @BindView(R.id.layout_hp)
    TextInputLayout layoutHp;
    @BindView(R.id.layout_alamat)
    TextInputLayout layoutAlamat;
    @BindView(R.id.spinnerPekerjaan)
    MaterialSpinner spinnerPekerjaan;
    @BindView(R.id.spinnerPunyaNpwp)
    MaterialSpinner spinnerPunyaNpwp;
    @BindView(R.id.inputLayoutTanggalLahir)
    TextInputLayout inputLayoutTanggalLahir;
    @BindView(R.id.inputLayoutJanjiSurvey)
    TextInputLayout inputLayoutJanjiSurvey;

    DatePickerDialog picker;

    SessionOrderIn session;

    ApiService2 apiServiceArea;
    ApiService3 apiService3;

    String name, no_ktp, email, no_hp, province_id, province, city_id, city, district_id, district, village_id, village, address, postal_code, agen_id, amount, ktp_image, bpkb, vehicle_id, voucher_code_id, tempat_lahir, tanggal_lahir, nama_ibu_kandung, tanggal_janji_survey, punya_npwp, punya_npwp_id, pekerjaan, pekerjaan_id;
    ;

    final List<String> PROVINSI_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> PROVINSI_DATA = new HashMap<Integer, String>();
    final List<String> KOTA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> KOTA_DATA = new HashMap<Integer, String>();
    final List<String> KECAMATAN_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> KECAMATAN_DATA = new HashMap<Integer, String>();
    final List<String> KELURAHAN_ITEMS = new ArrayList<>();
    final List<String> KELURAHAN_KODEPOS = new ArrayList<>();
    final HashMap<Integer, String> KELURAHAN_DATA = new HashMap<Integer, String>();

    final List<String> JOB_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JOB_DATA = new HashMap<Integer, String>();

    final List<String> PUNYA_NPWP_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> PUNYA_NPWP_DATA = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_calon_peminjam);
        ButterKnife.bind(this);

        sessionAuth = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + sessionAuth.getToken();
        session = new SessionOrderIn(DataCalonPeminjamActivity.this);

        initToolbar();
        initAction();
        initLoadData();
        try {
            if (getIntent().getStringExtra("edited") != null) {
                etNamaLengkap.setText(session.getName());
                etNomorKtp.setText(session.getNo_ktp());
                etAlamatEmail.setText(session.getEmail());
                etNoHandphone.setText(session.getNo_hp());
                etAlamat.setText(session.getAddress());

//                spinnerProvinsi.setEnabled(true);
//                spinnerKota.setEnabled(true);
//                spinnerKecamatan.setEnabled(true);
//                spinnerKelurahan.setEnabled(true);
//
//                progressBar.setVisibility(View.GONE);
//
//                editProvinsi();
//                editKota();
//                editKecamatan();
//                editKelurahan();
//
//                apiServiceArea = ApiClient2.getClient().create(ApiService2.class);
//
//                //PROVINSI
//                progressBar.setVisibility(View.VISIBLE);
//                Call<Provinsi> call = apiServiceArea.getProvinsi(1000);
//                call.enqueue(new Callback<Provinsi>() {
//                    @Override
//                    public void onResponse(Call<Provinsi> call, Response<Provinsi> response) {
//                        if (response.isSuccessful()) {
//                            try {
//                                if (response.body().getData().size() > 0) {
//                                    for (int i = 0; i < response.body().getData().size(); i++) {
//                                        PROVINSI_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                                        PROVINSI_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
//                                    }
//                                    progressBar.setVisibility(View.GONE);
//                                } else {
//                                    editProvinsi();
//                                    progressBar.setVisibility(View.GONE);
//                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                    alertDialog.setTitle("Perhatian");
//                                    alertDialog.setMessage("Data provinsi belum tersedia, silahkan coba beberapa saat lagi.");
//
//                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            finish();
//                                            startActivity(getIntent());
//                                        }
//                                    });
//                                    alertDialog.show();
//                                }
//                            } catch (Exception ex) {
//                            }
//
//                            ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, PROVINSI_ITEMS);
//                            area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            spinnerProvinsi.setAdapter(area_adapter);
//                            spinnerProvinsi.setTitle("");
//                            spinnerProvinsi.setPositiveButton("OK");
//
//                            spinnerProvinsi.setSelection(Integer.parseInt(session.getProvince_position()));
//                            //KOTA
//                            progressBar.setVisibility(View.VISIBLE);
//                            Call<Kota> call2 = apiServiceArea.getKota(PROVINSI_DATA.get(Integer.parseInt(session.getProvince_position())), 1000);
//                            call2.enqueue(new Callback<Kota>() {
//                                @Override
//                                public void onResponse(Call<Kota> call, Response<Kota> response) {
//                                    if (response.isSuccessful()) {
//                                        try {
//                                            if (response.body().getData().size() > 0) {
//                                                for (int i = 0; i < response.body().getData().size(); i++) {
//                                                    KOTA_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                                                    KOTA_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
//                                                }
//                                                progressBar.setVisibility(View.GONE);
//                                            } else {
//                                                editKota();
//                                                progressBar.setVisibility(View.GONE);
//                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                                alertDialog.setTitle("Perhatian");
//                                                alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        finish();
//                                                        startActivity(getIntent());
//                                                    }
//                                                });
//                                                alertDialog.show();
//                                            }
//                                        } catch (Exception ex) {
//                                        }
//
//                                        ArrayAdapter<String> brand_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KOTA_ITEMS);
//                                        brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                        spinnerKota.setAdapter(brand_adapter);
//                                        spinnerKota.setTitle("");
//                                        spinnerKota.setPositiveButton("OK");
//
//                                        spinnerKota.setSelection(Integer.parseInt(session.getCity_position()));
//
//                                        //KECAMATAN
//                                        progressBar.setVisibility(View.VISIBLE);
//                                        Call<Kecamatan> call3 = apiServiceArea.getKecamatan(KOTA_DATA.get(Integer.parseInt(session.getCity_position())), 1000);
//                                        call3.enqueue(new Callback<Kecamatan>() {
//                                            @Override
//                                            public void onResponse(Call<Kecamatan> call, Response<Kecamatan> response) {
//                                                if (response.isSuccessful()) {
//                                                    try {
//                                                        if (response.body().getData().size() > 0) {
//                                                            for (int i = 0; i < response.body().getData().size(); i++) {
//                                                                KECAMATAN_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                                                                KECAMATAN_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
//                                                            }
//                                                            progressBar.setVisibility(View.GONE);
//                                                        } else {
//                                                            editKecamatan();
//                                                            progressBar.setVisibility(View.GONE);
//                                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                                            alertDialog.setTitle("Perhatian");
//                                                            alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                                public void onClick(DialogInterface dialog, int which) {
//                                                                    finish();
//                                                                    startActivity(getIntent());
//                                                                }
//                                                            });
//                                                            alertDialog.show();
//                                                        }
//                                                    } catch (Exception ex) {
//                                                    }
//
//                                                    ArrayAdapter<String> kecamatan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KECAMATAN_ITEMS);
//                                                    kecamatan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                                    spinnerKecamatan.setAdapter(kecamatan_adapter);
//                                                    spinnerKecamatan.setTitle("");
//                                                    spinnerKecamatan.setPositiveButton("OK");
//
//                                                    spinnerKecamatan.setSelection(Integer.parseInt(session.getDistrict_position()));
//
//                                                    //KELURAHAN
//                                                    progressBar.setVisibility(View.VISIBLE);
//                                                    Call<Kelurahan> call4 = apiServiceArea.getKelurahan(KECAMATAN_DATA.get(Integer.parseInt(session.getDistrict_position())), 1000);
//                                                    call4.enqueue(new Callback<Kelurahan>() {
//                                                        @Override
//                                                        public void onResponse(Call<Kelurahan> call, Response<Kelurahan> response) {
//                                                            if (response.isSuccessful()) {
//                                                                try {
//                                                                    if (response.body().getData().size() > 0) {
//                                                                        for (int i = 0; i < response.body().getData().size(); i++) {
//                                                                            KELURAHAN_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                                                                            KELURAHAN_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
//                                                                            KELURAHAN_KODEPOS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getKodePos())));
//                                                                        }
//                                                                        progressBar.setVisibility(View.GONE);
//                                                                    } else {
//                                                                        editKelurahan();
//                                                                        progressBar.setVisibility(View.GONE);
//                                                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                                                        alertDialog.setTitle("Perhatian");
//                                                                        alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                                            public void onClick(DialogInterface dialog, int which) {
//                                                                                finish();
//                                                                                startActivity(getIntent());
//                                                                            }
//                                                                        });
//                                                                        alertDialog.show();
//                                                                    }
//                                                                } catch (Exception ex) {
//                                                                }
//
//                                                                ArrayAdapter<String> kelurahan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KELURAHAN_ITEMS);
//                                                                kelurahan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                                                spinnerKelurahan.setAdapter(kelurahan_adapter);
//                                                                spinnerKelurahan.setTitle("");
//                                                                spinnerKelurahan.setPositiveButton("OK");
//
//                                                                spinnerKelurahan.setSelection(Integer.parseInt(session.getVillage_position()));
//
//                                                                loadData2();
//                                                            } else {
//                                                                editKelurahan();
//                                                                progressBar.setVisibility(View.GONE);
//                                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                                                alertDialog.setTitle("Perhatian");
//                                                                alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                                    public void onClick(DialogInterface dialog, int which) {
//                                                                        finish();
//                                                                        startActivity(getIntent());
//                                                                    }
//                                                                });
//                                                                alertDialog.show();
//                                                            }
//                                                        }
//
//                                                        @Override
//                                                        public void onFailure(Call<Kelurahan> call, Throwable t) {
//                                                            progressBar.setVisibility(View.GONE);
//                                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                                            alertDialog.setTitle("Perhatian");
//                                                            alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                                public void onClick(DialogInterface dialog, int which) {
//                                                                    finish();
//                                                                    startActivity(getIntent());
//                                                                }
//                                                            });
//                                                            alertDialog.show();
//                                                        }
//                                                    });
//                                                } else {
//                                                    editKecamatan();
//                                                    progressBar.setVisibility(View.GONE);
//                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                                    alertDialog.setTitle("Perhatian");
//                                                    alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                        public void onClick(DialogInterface dialog, int which) {
//                                                            finish();
//                                                            startActivity(getIntent());
//                                                        }
//                                                    });
//                                                    alertDialog.show();
//                                                }
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<Kecamatan> call, Throwable t) {
//                                                progressBar.setVisibility(View.GONE);
//                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                                alertDialog.setTitle("Perhatian");
//                                                alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        finish();
//                                                        startActivity(getIntent());
//                                                    }
//                                                });
//                                                alertDialog.show();
//                                            }
//                                        });
//                                    } else {
//                                        editKota();
//                                        progressBar.setVisibility(View.GONE);
//                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                        alertDialog.setTitle("Perhatian");
//                                        alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                finish();
//                                                startActivity(getIntent());
//                                            }
//                                        });
//                                        alertDialog.show();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<Kota> call, Throwable t) {
//                                    progressBar.setVisibility(View.GONE);
//                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                    alertDialog.setTitle("Perhatian");
//                                    alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            finish();
//                                            startActivity(getIntent());
//                                        }
//                                    });
//                                    alertDialog.show();
//                                }
//                            });
//                        } else {
//                            editProvinsi();
//                            progressBar.setVisibility(View.GONE);
//                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                            alertDialog.setTitle("Perhatian");
//                            alertDialog.setMessage("Data provinsi gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
//                                    startActivity(getIntent());
//                                }
//                            });
//                            alertDialog.show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Provinsi> call, Throwable t) {
//                        progressBar.setVisibility(View.GONE);
//                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                        alertDialog.setTitle("Perhatian");
//                        alertDialog.setMessage("Data provinsi gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                                startActivity(getIntent());
//                            }
//                        });
//                        alertDialog.show();
//                    }
//                });


            }
        } catch (Exception ex) {
        }

        inputTangalLahir.setInputType(InputType.TYPE_NULL);
        inputTangalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DataCalonPeminjamActivity.this,
                        android.app.AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                cldr.set(Calendar.YEAR, year);
                                cldr.set(Calendar.MONTH, monthOfYear);
                                cldr.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                String myFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"; //In which you need put here
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                                tanggal_lahir = sdf.format(cldr.getTime());

                                if (monthOfYear+1 < 10){
                                    inputTangalLahir.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                }else{
                                    inputTangalLahir.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }

                            }
                        }, year, month, day);
                picker.show();
//                DialogFragment dialogfragment = new DatePickerDialogTheme4();
//
//                dialogfragment.show(getSupportFragmentManager(), "Theme 4");
            }
        });

        inputJanjiSurvey.setInputType(InputType.TYPE_NULL);
        inputJanjiSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DataCalonPeminjamActivity.this,
                android.app.AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                cldr.set(Calendar.YEAR, year);
                                cldr.set(Calendar.MONTH, monthOfYear);
                                cldr.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                String myFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"; //In which you need put here
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                                inputJanjiSurvey.setText(sdf.format(cldr.getTime()));

                                tanggal_janji_survey = sdf.format(cldr.getTime());
                                Log.d("tanggal_janji_survey : ",tanggal_janji_survey);

                                if (monthOfYear+1 < 10){
                                        inputJanjiSurvey.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                    }else{
                                        inputJanjiSurvey.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }
                        }, year, month, day);
                picker.show();
//                DialogFragment dialogfragment = new DatePickerDialogTheme4();
//
//                dialogfragment.show(getSupportFragmentManager(), "Theme 4");
            }
        });


        PUNYA_NPWP_ITEMS.clear();
        PUNYA_NPWP_DATA.clear();

        PUNYA_NPWP_DATA.put(1, "1");
        PUNYA_NPWP_DATA.put(2, "2");
        PUNYA_NPWP_ITEMS.add("Ya");
        PUNYA_NPWP_ITEMS.add("Tidak");


        ArrayAdapter<String> punya_npwp_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, PUNYA_NPWP_ITEMS);
        punya_npwp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPunyaNpwp.setAdapter(punya_npwp_adapter);



        progressBar.setVisibility(View.VISIBLE);
        Call<List<Pekerjaan>> call = apiService3.getPekerjaan();
        call.enqueue(new Callback<List<Pekerjaan>>() {
            @Override
            public void onResponse(Call<List<Pekerjaan>> call, Response<List<Pekerjaan>> response) {

                if (response.isSuccessful()) {
                    try {
                        if (response.body().size() > 0) {
                            for (int i = 0; i < response.body().size(); i++) {
                                JOB_DATA.put(i + 1, String.valueOf(response.body().get(i).getJobCode()));
                                JOB_ITEMS.add(toTitleCase(String.valueOf(response.body().get(i).getJobName())));
                            }
                            progressBar.setVisibility(View.GONE);
                        } else {
                            clearPekerjaan();
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data Pekerjaan gagal dipanggil, silahkan coba beberapa saat lagi.");

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

                    ArrayAdapter<String> pekerjaan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JOB_ITEMS);
                    pekerjaan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerPekerjaan.setAdapter(pekerjaan_adapter);
                    spinnerPekerjaan.setEnabled(true);
                } else {
                    clearPekerjaan();
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data Pekerjaan gagal dipanggil, silahkan coba beberapa saat lagi.");

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
            public void onFailure(Call<List<Pekerjaan>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data Pekerjaan gagal dipanggil, silahkan coba beberapa saat lagi."+t.getMessage());

                Log.d("Connection Failed",t.getMessage());
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
        apiService3 = ApiClient2.getClient().create(ApiService3.class);

        etNamaLengkap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etNamaLengkap.removeTextChangedListener(this);
                validateName();
                etNamaLengkap.addTextChangedListener(this);
            }
        });

        etAlamatEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etAlamatEmail.removeTextChangedListener(this);
                validateEmail();
                etAlamatEmail.addTextChangedListener(this);
            }
        });

        etAlamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etAlamat.removeTextChangedListener(this);
                validateAlamat();
                etAlamat.addTextChangedListener(this);
            }
        });

        etNoHandphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etNoHandphone.removeTextChangedListener(this);
                validateHp();
                etNoHandphone.addTextChangedListener(this);
            }
        });
    }

    public static boolean isEmail(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateEmail() {
        if (etAlamatEmail.getText().toString().trim().isEmpty()) {
            layoutEmail.setErrorEnabled(false);
        } else {
            String emailId = etAlamatEmail.getText().toString();
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                layoutEmail.setError("Masukan alamat email dengan benar\ncontoh: budi.susanto@gmail.com");
                requestFocus(etAlamatEmail);
                return false;
            } else {
                layoutEmail.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isName(String alamat) {
        String expression = "^[a-z.'/ A-Z]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateName() {
        if (etNamaLengkap.getText().toString().trim().isEmpty()) {
            layoutNamaLengkap.setErrorEnabled(false);
        } else {
            String emailId = etNamaLengkap.getText().toString();
            String expression = "^[a-z.'/ A-Z]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                layoutNamaLengkap.setError("Masukan nama lengkap dengan benar\ncontoh: Budi Susanto");
                requestFocus(etNamaLengkap);
                return false;
            } else {
                layoutNamaLengkap.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isAlamat(String alamat) {
        String expression = "^[a-z.'/ A-Z0-9-]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateAlamat() {
        if (etAlamat.getText().toString().trim().isEmpty()) {
            layoutAlamat.setErrorEnabled(false);
        } else {
            String emailId = etAlamat.getText().toString();
            String expression = "^[a-z.'/ A-Z0-9-]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                layoutAlamat.setError("Masukan alamat dengan benar");
                requestFocus(etAlamat);
                return false;
            } else {
                layoutAlamat.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isHp(String hp) {
        String expression = "^62[0-9]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(hp);
        return matcher.matches();
    }

    private boolean validateHp() {
        if (etNoHandphone.getText().toString().trim().isEmpty()) {
            layoutHp.setErrorEnabled(false);
        } else {
            String emailId = etNoHandphone.getText().toString();
            String expression = "^62[0-9]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                layoutHp.setError("Format nomor HP salah\ncontoh: 6281234567891");
                requestFocus(etNoHandphone);
                return false;
            } else {
                layoutHp.setErrorEnabled(false);
            }
        }
        return true;
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

    private void editProvinsi() {
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

    private void editKota() {
        KOTA_DATA.clear();
        KOTA_ITEMS.clear();
        KOTA_DATA.put(0, "0");
        KOTA_ITEMS.add("Pilih Kota");
        ArrayAdapter<String> kota_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KOTA_ITEMS);
        kota_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKota.setAdapter(kota_adapter);
        spinnerKota.setTitle("");
        spinnerKota.setPositiveButton("OK");
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

    private void editKecamatan() {
        KECAMATAN_DATA.clear();
        KECAMATAN_ITEMS.clear();
        KECAMATAN_DATA.put(0, "0");
        KECAMATAN_ITEMS.add("Pilih Kecamatan");
        ArrayAdapter<String> kecamatan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KECAMATAN_ITEMS);
        kecamatan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKecamatan.setAdapter(kecamatan_adapter);
        spinnerKecamatan.setTitle("");
        spinnerKecamatan.setPositiveButton("OK");
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

    private void editKelurahan() {
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
    }

    private void clearPekerjaan() {
        JOB_DATA.clear();
        JOB_ITEMS.clear();
        JOB_DATA.put(0, "0");
        JOB_ITEMS.add("Pilih Pekerjaan");
        ArrayAdapter<String> pekerjaan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JOB_ITEMS);
        pekerjaan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPekerjaan.setAdapter(pekerjaan_adapter);
        spinnerPekerjaan.setEnabled(false);
    }

    private void initLoadData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<Provinsi> call = apiServiceArea.getProvinsi(apiKey, 1000);
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

        loadData();
    }

    private void loadData() {
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

//    private void loadData2() {
//        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("TAGTAGTAG", "onItemSelected: " + i);
//                Log.d("TAGTAGTAG", "onItemSelected: " + session.getProvince_position());
//                if (i != Integer.parseInt(session.getProvince_position())) {
//                    clearKota();
//                    clearKecamatan();
//                    clearKelurahan();
//                    if (Integer.parseInt(PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition())) > 0) {
//                        progressBar.setVisibility(View.VISIBLE);
//                        Call<Kota> call = apiServiceArea.getKota(PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition()), 1000);
//                        call.enqueue(new Callback<Kota>() {
//                            @Override
//                            public void onResponse(Call<Kota> call, Response<Kota> response) {
//                                if (response.isSuccessful()) {
//                                    try {
//                                        if (response.body().getData().size() > 0) {
//                                            for (int i = 0; i < response.body().getData().size(); i++) {
//                                                KOTA_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                                                KOTA_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
//                                            }
//                                            progressBar.setVisibility(View.GONE);
//                                        } else {
//                                            clearKota();
//                                            progressBar.setVisibility(View.GONE);
//                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                            alertDialog.setTitle("Perhatian");
//                                            alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    finish();
//                                                    startActivity(getIntent());
//                                                }
//                                            });
//                                            alertDialog.show();
//                                        }
//                                    } catch (Exception ex) {
//                                    }
//
//                                    ArrayAdapter<String> brand_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KOTA_ITEMS);
//                                    brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                    spinnerKota.setAdapter(brand_adapter);
//                                    spinnerKota.setTitle("");
//                                    spinnerKota.setPositiveButton("OK");
//                                    spinnerKota.setEnabled(true);
//                                } else {
//                                    clearKota();
//                                    progressBar.setVisibility(View.GONE);
//                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                    alertDialog.setTitle("Perhatian");
//                                    alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            finish();
//                                            startActivity(getIntent());
//                                        }
//                                    });
//                                    alertDialog.show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<Kota> call, Throwable t) {
//                                progressBar.setVisibility(View.GONE);
//                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                alertDialog.setTitle("Perhatian");
//                                alertDialog.setMessage("Data kota gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
//                                        startActivity(getIntent());
//                                    }
//                                });
//                                alertDialog.show();
//                            }
//                        });
//
//
//
//                    }
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spinnerKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("TAGTAGTAG", "onItemSelected: " + i);
//                Log.d("TAGTAGTAG", "onItemSelected: " + session.getCity_position());
//                if (i != Integer.parseInt(session.getCity_position())) {
//                    clearKecamatan();
//                    clearKelurahan();
//                    if (Integer.parseInt(KOTA_DATA.get(spinnerKota.getSelectedItemPosition())) > 0) {
//                        progressBar.setVisibility(View.VISIBLE);
//                        Call<Kecamatan> call = apiServiceArea.getKecamatan(KOTA_DATA.get(spinnerKota.getSelectedItemPosition()), 1000);
//                        call.enqueue(new Callback<Kecamatan>() {
//                            @Override
//                            public void onResponse(Call<Kecamatan> call, Response<Kecamatan> response) {
//                                if (response.isSuccessful()) {
//                                    try {
//                                        if (response.body().getData().size() > 0) {
//                                            for (int i = 0; i < response.body().getData().size(); i++) {
//                                                KECAMATAN_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                                                KECAMATAN_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
//                                            }
//                                            progressBar.setVisibility(View.GONE);
//                                        } else {
//                                            clearKecamatan();
//                                            progressBar.setVisibility(View.GONE);
//                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                            alertDialog.setTitle("Perhatian");
//                                            alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    finish();
//                                                    startActivity(getIntent());
//                                                }
//                                            });
//                                            alertDialog.show();
//                                        }
//                                    } catch (Exception ex) {
//                                    }
//
//                                    ArrayAdapter<String> kecamatan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KECAMATAN_ITEMS);
//                                    kecamatan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                    spinnerKecamatan.setAdapter(kecamatan_adapter);
//                                    spinnerKecamatan.setTitle("");
//                                    spinnerKecamatan.setPositiveButton("OK");
//                                    spinnerKecamatan.setEnabled(true);
//                                } else {
//                                    clearKecamatan();
//                                    progressBar.setVisibility(View.GONE);
//                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                    alertDialog.setTitle("Perhatian");
//                                    alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            finish();
//                                            startActivity(getIntent());
//                                        }
//                                    });
//                                    alertDialog.show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<Kecamatan> call, Throwable t) {
//                                progressBar.setVisibility(View.GONE);
//                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                alertDialog.setTitle("Perhatian");
//                                alertDialog.setMessage("Data kecamatan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
//                                        startActivity(getIntent());
//                                    }
//                                });
//                                alertDialog.show();
//                            }
//                        });
//
//
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("TAGTAGTAG", "onItemSelected: " + i);
//                Log.d("TAGTAGTAG", "onItemSelected: " + session.getDistrict_position());
//                if (i != Integer.parseInt(session.getDistrict_position())) {
//                    clearKelurahan();
//                    if (Integer.parseInt(KECAMATAN_DATA.get(spinnerKecamatan.getSelectedItemPosition())) > 0) {
//                        progressBar.setVisibility(View.VISIBLE);
//                        Call<Kelurahan> call = apiServiceArea.getKelurahan(KECAMATAN_DATA.get(spinnerKecamatan.getSelectedItemPosition()), 1000);
//                        call.enqueue(new Callback<Kelurahan>() {
//                            @Override
//                            public void onResponse(Call<Kelurahan> call, Response<Kelurahan> response) {
//                                if (response.isSuccessful()) {
//                                    try {
//                                        if (response.body().getData().size() > 0) {
//                                            for (int i = 0; i < response.body().getData().size(); i++) {
//                                                KELURAHAN_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                                                KELURAHAN_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
//                                                KELURAHAN_KODEPOS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getKodePos())));
//                                            }
//                                            progressBar.setVisibility(View.GONE);
//                                        } else {
//                                            clearKelurahan();
//                                            progressBar.setVisibility(View.GONE);
//                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                            alertDialog.setTitle("Perhatian");
//                                            alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    finish();
//                                                    startActivity(getIntent());
//                                                }
//                                            });
//                                            alertDialog.show();
//                                        }
//                                    } catch (Exception ex) {
//                                    }
//
//                                    ArrayAdapter<String> kelurahan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KELURAHAN_ITEMS);
//                                    kelurahan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                    spinnerKelurahan.setAdapter(kelurahan_adapter);
//                                    spinnerKelurahan.setTitle("");
//                                    spinnerKelurahan.setPositiveButton("OK");
//                                    spinnerKelurahan.setEnabled(true);
//                                } else {
//                                    clearKelurahan();
//                                    progressBar.setVisibility(View.GONE);
//                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                    alertDialog.setTitle("Perhatian");
//                                    alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            finish();
//                                            startActivity(getIntent());
//                                        }
//                                    });
//                                    alertDialog.show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<Kelurahan> call, Throwable t) {
//                                progressBar.setVisibility(View.GONE);
//                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
//                                alertDialog.setTitle("Perhatian");
//                                alertDialog.setMessage("Data kelurahan gagal dipanggil, silahkan coba beberapa saat lagi.");
//
//                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
//                                        startActivity(getIntent());
//                                    }
//                                });
//                                alertDialog.show();
//                            }
//                        });
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }

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
            tempat_lahir = inputTempatLahir.getText().toString();
//            tanggal_lahir = inputTangalLahir.getText().toString();
            nama_ibu_kandung = inputIbuKandung.getText().toString();
//            tanggal_janji_survey = inputJanjiSurvey.getText().toString();
            punya_npwp_id = PUNYA_NPWP_DATA.get(spinnerPunyaNpwp.getSelectedItemPosition());
            punya_npwp = PUNYA_NPWP_ITEMS.get(spinnerPunyaNpwp.getSelectedItemPosition());
            pekerjaan_id= JOB_DATA.get(spinnerPekerjaan.getSelectedItemPosition());
            pekerjaan = JOB_ITEMS.get(spinnerPekerjaan.getSelectedItemPosition());


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
        Log.d("TAGTAG", "tempat_lahir: " + tempat_lahir);
        Log.d("TAGTAG", "tanggal_lahir: " + tanggal_lahir);
        Log.d("TAGTAG", "nama_ibu_kandung: " + nama_ibu_kandung);
        Log.d("TAGTAG", "tanggal_janji_survey: " + tanggal_janji_survey);
        Log.d("TAGTAG", "punya_npwp_id: " + punya_npwp_id);
        Log.d("TAGTAG", "punya_npwp: " + punya_npwp);
        Log.d("TAGTAG", "pekerjaan_id: " + pekerjaan_id);
        Log.d("TAGTAG", "pekerjaan: " + pekerjaan);

        if (validateForm(name, no_ktp, email, no_hp, province_id, city_id, district_id, village_id, address, tempat_lahir, tanggal_lahir, nama_ibu_kandung, tanggal_janji_survey, punya_npwp_id, pekerjaan_id)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Apakah data yang Anda masukan sudah benar?");

            alertDialog.setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    session.setProvince_position(String.valueOf(spinnerProvinsi.getSelectedItemPosition()));
                    session.setCity_position(String.valueOf(spinnerKota.getSelectedItemPosition()));
                    session.setDistrict_position(String.valueOf(spinnerKecamatan.getSelectedItemPosition()));
                    session.setVillage_position(String.valueOf(spinnerKelurahan.getSelectedItemPosition()));
                    session.setPunyaNpwp_Position(String.valueOf(spinnerPunyaNpwp.getSelectedItemPosition()));
                    session.setPekerjaan_Position(String.valueOf(spinnerPekerjaan.getSelectedItemPosition()));

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
                            village_id,
                            village,
                            address,
                            postal_code,
                            tempat_lahir,
                            tanggal_lahir,
                            nama_ibu_kandung,
                            tanggal_janji_survey,
                            punya_npwp_id,
                            punya_npwp,
                            pekerjaan_id,
                            pekerjaan
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
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    private boolean validateForm(String name, String no_ktp, String email, String no_hp, String province_id, String city_id, String district_id, String village_id, String address, String tempat_lahir, String tanggal_lahir, String nama_ibu_kandung, String tanggal_janji_survey, String punya_npwp_id,String pekerjaan_id) {
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
        } else if (!isName(name)) {
            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog5.setTitle("Perhatian");
            alertDialog5.setMessage("Masukan nama lengkap yang benar");

            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etNamaLengkap);
                }
            });
            alertDialog5.show();
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
        } else if (!isEmailValid(email)) {
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
        } else if (!isHp(no_hp)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nomor HP dengan benar");

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
        } else if (!isAlamat(address)) {
            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog5.setTitle("Perhatian");
            alertDialog5.setMessage("Masukan alamat yang benar");

            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etAlamat);
                }
            });
            alertDialog5.show();
            return false;
        }

        if (tempat_lahir == null || tempat_lahir.trim().length() == 0 || tempat_lahir.equals("0") || tempat_lahir.equals("") || tempat_lahir.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan Tempat Lahir");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTempatLahir);
                }
            });
            alertDialog.show();
            return false;
        }

        if (tanggal_lahir == null || tanggal_lahir.trim().length() == 0 || tanggal_lahir.equals("0") || tanggal_lahir.equals("") || tanggal_lahir.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan Tanggal Lahir");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTangalLahir);
                }
            });
            alertDialog.show();
            return false;
        }

        if (nama_ibu_kandung == null || nama_ibu_kandung.trim().length() == 0 || nama_ibu_kandung.equals("0") || nama_ibu_kandung.equals("") || nama_ibu_kandung.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan Nama Ibu Kandung");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputIbuKandung);
                }
            });
            alertDialog.show();
            return false;
        }

        if (tanggal_janji_survey == null || tanggal_janji_survey.trim().length() == 0 || tanggal_janji_survey.equals("0") || tanggal_janji_survey.equals("") || tanggal_janji_survey.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan Tanggal Janji Survey");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputJanjiSurvey);
                }
            });
            alertDialog.show();
            return false;
        }

        if (punya_npwp_id == null || punya_npwp_id.trim().length() == 0 || punya_npwp_id.equals("0") || punya_npwp_id.equals("") || punya_npwp_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih keterangan punya NPWP / tidak");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerPunyaNpwp);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerPunyaNpwp.dispatchTouchEvent(motionEvent);
                }
            });
            alertDialog.show();
            return false;
        }

        if (pekerjaan_id == null || pekerjaan_id.trim().length() == 0 || pekerjaan_id.equals("0") || pekerjaan_id.equals("") || pekerjaan_id.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DataCalonPeminjamActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan pilih Pekerjaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerPekerjaan);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerPekerjaan.dispatchTouchEvent(motionEvent);
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

    public static class DatePickerDialogTheme4 extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    android.app.AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

            return datepickerdialog;
        }
        public void onDateSet(DatePicker view, int year, int month, int day){

            EditText textview = (EditText)getActivity().findViewById(R.id.inputTangalLahir);

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateInString = day + "/" + (month + 1) + "/" + year;
                Date date = formatter.parse(dateInString);

                formatter = new SimpleDateFormat("dd/MM/yyyy");

                textview.setText(formatter.format(date).toString());

            } catch (Exception ex) {

            }

        }
    }
}
