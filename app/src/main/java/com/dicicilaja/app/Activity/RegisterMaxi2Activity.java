package com.dicicilaja.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.OrderIn.Data.Kecamatan.Kecamatan;
import com.dicicilaja.app.OrderIn.Data.Kelurahan.Kelurahan;
import com.dicicilaja.app.OrderIn.Data.Kota.Kota;
import com.dicicilaja.app.OrderIn.Data.Provinsi.Provinsi;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterMaxi2Activity extends AppCompatActivity {


    Button btnLanjut;
    EditText inputNamaPerusahaan, inputAlamatPerusahaan, inputKelurahan, inputKota, inputTelp;
    String namapemilik, alamatpemilik, nohp, noktp, jk;
    String namaperusahaan, alamatperusahaan, kelurahan, kota, telp, provinsi, distrik, desa;
    SessionManager session;
    String apiKey;
    TextView title;

    TextInputLayout inputLayoutNamaPerusahaan, inputLayoutAlamatPerusahaan, inputLayoutTelp;

    SearchableSpinner spinnerProvinsi, spinnerKota, spinnerDistrik, spinnerDesa;
    MaterialProgressBar progressBar;

    ApiService2 apiServiceArea;

    final List<String> PROVINSI_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> PROVINSI_DATA = new HashMap<Integer, String>();
    final List<String> KOTA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> KOTA_DATA = new HashMap<Integer, String>();
    final List<String> DISTRIK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> DISTRIK_DATA = new HashMap<Integer, String>();
    final List<String> DESA_ITEMS = new ArrayList<>();
    final List<String> DESA_KODEPOS = new ArrayList<>();
    final HashMap<Integer, String> DESA_DATA = new HashMap<Integer, String>();
    final List<String> JK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JK_DATA = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_maxi2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnLanjut = findViewById(R.id.btnLanjut);
        inputNamaPerusahaan = findViewById(R.id.inputNamaPerusahaan);
        inputAlamatPerusahaan = findViewById(R.id.inputAlamatPerusahaan);
        spinnerProvinsi         = findViewById(R.id.spinnerProvinsi);
        spinnerKota             = findViewById(R.id.spinnerKota);
        spinnerDistrik          = findViewById(R.id.spinnerDistrik);
        spinnerDesa             = findViewById(R.id.spinnerDesa);
        inputTelp = findViewById(R.id.inputTelp);
        title = findViewById(R.id.title);
        progressBar             = findViewById(R.id.progressBar);
        inputLayoutNamaPerusahaan = findViewById(R.id.inputLayoutNamaPerusahaan);
        inputLayoutAlamatPerusahaan = findViewById(R.id.inputLayoutAlamatPerusahaan);
        inputLayoutTelp = findViewById(R.id.inputLayoutTelp);

        try {
            apiKey = getIntent().getStringExtra("apiKey");
        }catch (Exception ex){
            final SessionManager session = new SessionManager(getBaseContext());
            apiKey = "Bearer " + session.getToken();
        }

        namapemilik = getIntent().getStringExtra("namapemilik");
        alamatpemilik = getIntent().getStringExtra("alamatpemilik");
        nohp = getIntent().getStringExtra("nohp");
        noktp = getIntent().getStringExtra("noktp");
        jk = getIntent().getStringExtra("jk");



        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");



        progressBar.setVisibility(View.GONE);
        spinnerKota.setEnabled(false);
        spinnerDistrik.setEnabled(false);
        spinnerDesa.setEnabled(false);

        initAction();
        initLoadData();


        inputNamaPerusahaan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputNamaPerusahaan.removeTextChangedListener(this);
                validateName();
                inputNamaPerusahaan.addTextChangedListener(this);
            }
        });

        inputAlamatPerusahaan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputAlamatPerusahaan.removeTextChangedListener(this);
                validateAlamat();
                inputAlamatPerusahaan.addTextChangedListener(this);
            }
        });

        inputTelp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputTelp.removeTextChangedListener(this);
                validateTel();
                inputTelp.addTextChangedListener(this);
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    namaperusahaan = inputNamaPerusahaan.getText().toString();
                    alamatperusahaan = inputAlamatPerusahaan.getText().toString();
                    provinsi = PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition());
                    kota = KOTA_DATA.get(spinnerKota.getSelectedItemPosition());
                    distrik = DISTRIK_DATA.get(spinnerDistrik.getSelectedItemPosition());
                    desa = DESA_DATA.get(spinnerDesa.getSelectedItemPosition());
                    telp = inputTelp.getText().toString();
                } catch (Exception ex) {

                }

                if(validateForm(namaperusahaan, alamatperusahaan, provinsi, kota, distrik, desa, telp)) {

                    Log.d("ajukanpengajuan","apiKey : " + apiKey);
                    Log.d("ajukanpengajuan","namapemilik : " + namapemilik);
                    Log.d("ajukanpengajuan","jk : " + jk);
                    Log.d("ajukanpengajuan","alamatpemilik : " + alamatpemilik);
                    Log.d("ajukanpengajuan","nohp : " + nohp);
                    Log.d("ajukanpengajuan","noktp : " + noktp);
                    Log.d("ajukanpengajuan","namaperusahaan : " + namaperusahaan);
                    Log.d("ajukanpengajuan","alamatperusahaan : " + alamatperusahaan);
                    Log.d("ajukanpengajuan","kelurahan : " + kelurahan);
                    Log.d("ajukanpengajuan","kota : " + kota);
                    Log.d("ajukanpengajuan","telp : " + telp);


                    Intent intent = new Intent(getBaseContext(), RegisterMaxi3Activity.class);
                    intent.putExtra("apiKey",apiKey);
                    intent.putExtra("namapemilik",namapemilik);
                    intent.putExtra("alamatpemilik",alamatpemilik);
                    intent.putExtra("nohp",nohp);
                    intent.putExtra("noktp", noktp);
                    intent.putExtra("jk", jk);
                    intent.putExtra("namaperusahaan", namaperusahaan);
                    intent.putExtra("alamatperusahaan", alamatperusahaan);
                    intent.putExtra("desa", desa);
                    intent.putExtra("telp", telp);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateForm(String namaperusahaan, String alamatperusahaan, String provinsi, String kota, String distrik, String desa, String telp) {
        if (namaperusahaan == null || namaperusahaan.trim().length() == 0 || namaperusahaan.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nama perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaPerusahaan);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isName(namaperusahaan)) {
            android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nama perusahaan dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaPerusahaan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(alamatperusahaan == null || alamatperusahaan.trim().length() == 0 || alamatperusahaan.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setMessage("Masukan alamat perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamatPerusahaan);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isAlamat(alamatperusahaan)) {
            android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan alamat perusahaan dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamatPerusahaan);
                }
            });
            alertDialog.show();
            return false;
        }

        if (provinsi == null || provinsi.trim().length() == 0 || provinsi.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan provinsi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerProvinsi);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerProvinsi.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (kota == null || kota.trim().length() == 0 || kota.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan kota");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerKota);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerKota.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (distrik == null || distrik.trim().length() == 0 || distrik.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan kecamatan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerDistrik);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerDistrik.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (desa == null || desa.trim().length() == 0 || desa.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan kelurahan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerDesa);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerDesa.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (telp == null || telp.trim().length() == 0 || telp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setMessage("Masukan no. telephone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTelp);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isTel(telp)) {
            android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan no. telephone dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTelp);
                }
            });
            alertDialog.show();
            return false;
        }

        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            showSoftKeyboard(view);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
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
                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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
                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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
                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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
                clearDistrik();
                clearDesa();
                if (Integer.parseInt(PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition())) > 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    Call<Kota> call = apiServiceArea.getKota(apiKey, PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition()), 1000);
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
                                        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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
                                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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
                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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
                            clearDistrik();
                            clearDesa();
                            if (Integer.parseInt(KOTA_DATA.get(spinnerKota.getSelectedItemPosition())) > 0) {
                                progressBar.setVisibility(View.VISIBLE);
                                Call<Kecamatan> call = apiServiceArea.getKecamatan(apiKey, KOTA_DATA.get(spinnerKota.getSelectedItemPosition()), 1000);
                                call.enqueue(new Callback<Kecamatan>() {
                                    @Override
                                    public void onResponse(Call<Kecamatan> call, Response<Kecamatan> response) {
                                        if (response.isSuccessful()) {
                                            try {
                                                if (response.body().getData().size() > 0) {
                                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                                        DISTRIK_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                                        DISTRIK_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
                                                    }
                                                    progressBar.setVisibility(View.GONE);
                                                } else {
                                                    clearDistrik();
                                                    progressBar.setVisibility(View.GONE);
                                                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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

                                            ArrayAdapter<String> distrik_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, DISTRIK_ITEMS);
                                            distrik_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                            spinnerDistrik.setAdapter(distrik_adapter);
                                            spinnerDistrik.setTitle("");
                                            spinnerDistrik.setPositiveButton("OK");
                                            spinnerDistrik.setEnabled(true);
                                        } else {
                                            clearDistrik();
                                            progressBar.setVisibility(View.GONE);
                                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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
                                        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
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

                                spinnerDistrik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        clearDesa();
                                        if (Integer.parseInt(DISTRIK_DATA.get(spinnerDistrik.getSelectedItemPosition())) > 0) {
                                            progressBar.setVisibility(View.VISIBLE);
                                            Call<Kelurahan> call = apiServiceArea.getKelurahan(apiKey, DISTRIK_DATA.get(spinnerDistrik.getSelectedItemPosition()), 1000);
                                            call.enqueue(new Callback<Kelurahan>() {
                                                @Override
                                                public void onResponse(Call<Kelurahan> call, Response<Kelurahan> response) {
                                                    if (response.isSuccessful()) {
                                                        try {
                                                            if (response.body().getData().size() > 0) {
                                                                for (int i = 0; i < response.body().getData().size(); i++) {
                                                                    DESA_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                                                    DESA_ITEMS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getNama())));
                                                                    DESA_KODEPOS.add(toTitleCase(String.valueOf(response.body().getData().get(i).getAttributes().getKodePos())));
                                                                }
                                                                progressBar.setVisibility(View.GONE);
                                                            } else {
                                                                clearDesa();
                                                                progressBar.setVisibility(View.GONE);
                                                                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
                                                                alertDialog.setTitle("Perhatian");
                                                                alertDialog.setMessage("Data desa gagal dipanggil, silahkan coba beberapa saat lagi.");

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

                                                        ArrayAdapter<String> desa_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, DESA_ITEMS);
                                                        desa_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                        spinnerDesa.setAdapter(desa_adapter);
                                                        spinnerDesa.setTitle("");
                                                        spinnerDesa.setPositiveButton("OK");
                                                        spinnerDesa.setEnabled(true);
                                                    } else {
                                                        clearDesa();
                                                        progressBar.setVisibility(View.GONE);
                                                        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
                                                        alertDialog.setTitle("Perhatian");
                                                        alertDialog.setMessage("Data desa gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi2Activity.this);
                                                    alertDialog.setTitle("Perhatian");
                                                    alertDialog.setMessage("Data desa gagal dipanggil, silahkan coba beberapa saat lagi.");

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
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }

    private void initAction() {
        //Initialize
        progressBar.setVisibility(View.GONE);
        spinnerKota.setEnabled(false);
        spinnerDistrik.setEnabled(false);
        spinnerDesa.setEnabled(false);

        clearProvinsi();
        clearKota();
        clearDistrik();
        clearDesa();

        //Network
        apiServiceArea = ApiClient2.getClient().create(ApiService2.class);
//        apiServiceArea = RetrofitClient2.getClient().create(InterfaceAreaBank.class);
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
        KOTA_ITEMS.add("Pilih Kabupaten");
        ArrayAdapter<String> kota_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, KOTA_ITEMS);
        kota_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKota.setAdapter(kota_adapter);
        spinnerKota.setTitle("");
        spinnerKota.setPositiveButton("OK");
        spinnerKota.setEnabled(false);
    }

    private void clearDistrik() {
        DISTRIK_DATA.clear();
        DISTRIK_ITEMS.clear();
        DISTRIK_DATA.put(0, "0");
        DISTRIK_ITEMS.add("Pilih Kecamatan");
        ArrayAdapter<String> distrik_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, DISTRIK_ITEMS);
        distrik_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrik.setAdapter(distrik_adapter);
        spinnerDistrik.setTitle("");
        spinnerDistrik.setPositiveButton("OK");
        spinnerDistrik.setEnabled(false);
    }

    private void clearDesa() {
        DESA_DATA.clear();
        DESA_ITEMS.clear();
        DESA_KODEPOS.clear();
        DESA_DATA.put(0, "0");
        DESA_ITEMS.add("Pilih Kelurahan");
        DESA_KODEPOS.add("0");
        ArrayAdapter<String> desa_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, DESA_ITEMS);
        desa_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesa.setAdapter(desa_adapter);
        spinnerDesa.setTitle("");
        spinnerDesa.setPositiveButton("OK");
        spinnerDesa.setEnabled(false);
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

    public static boolean isAlamat(String alamat) {
        String expression = "^[a-z.'/ A-Z0-9-]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateAlamat() {
        if (inputAlamatPerusahaan.getText().toString().trim().isEmpty()) {
            inputLayoutAlamatPerusahaan.setErrorEnabled(false);
        } else {
            String emailId = inputAlamatPerusahaan.getText().toString();
            String expression = "^[a-z.'/ A-Z0-9-]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutAlamatPerusahaan.setError("Masukan alamat perusahaan dengan benar");
                requestFocus(inputAlamatPerusahaan);
                return false;
            } else {
                inputLayoutAlamatPerusahaan.setErrorEnabled(false);
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
        if (inputNamaPerusahaan.getText().toString().trim().isEmpty()) {
            inputLayoutNamaPerusahaan.setErrorEnabled(false);
        } else {
            String emailId = inputNamaPerusahaan.getText().toString();
            String expression = "^[a-z.'/ A-Z]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutNamaPerusahaan.setError("Nama Perusahaan yang di input harus sesuai \ncontoh: Pernikahanku Wedding Package");
                requestFocus(inputNamaPerusahaan);
                return false;
            } else {
                inputLayoutNamaPerusahaan.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isTel(String hp) {
        String expression = "^0[0-9]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(hp);
        return matcher.matches();
    }

    private boolean validateTel() {
        if (inputTelp.getText().toString().trim().isEmpty()) {
            inputLayoutTelp.setErrorEnabled(false);
        } else {
            String emailId = inputTelp.getText().toString();
            String expression = "^0[0-9]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutTelp.setError("Format nomor telephone salah\ncontoh: 02155001333");
                requestFocus(inputTelp);
                return false;
            } else {
                inputLayoutTelp.setErrorEnabled(false);
            }
        }
        return true;
    }
}
