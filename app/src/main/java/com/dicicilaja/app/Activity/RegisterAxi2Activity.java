package com.dicicilaja.app.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import com.dicicilaja.app.API.Client.RetrofitClient2;
import com.dicicilaja.app.API.Interface.InterfaceAreaBank;
import com.dicicilaja.app.API.Model.AreaBankRequest.Provinsi.Provinsi;
import com.dicicilaja.app.API.Model.AreaBankRequest.Kota.Kota;
import com.dicicilaja.app.API.Model.AreaBankRequest.Distrik.Distrik;
import com.dicicilaja.app.API.Model.AreaBankRequest.Desa.Desa;
import com.dicicilaja.app.API.Model.AreaBankRequest.Bank.Bank;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import fr.ganfra.materialspinner.MaterialSpinner;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import static java.lang.Boolean.TRUE;

public class RegisterAxi2Activity extends AppCompatActivity {

    Button btnLanjut;
    EditText inputKtp, inputTempatLahir, inputTanggal, inputAlamat, inputRtRw, inputDesa, inputDistrik, inputKota, inputProvinsi, inputKodepos;
    TextInputLayout inputLayoutKtp, inputLayoutTempatLahir, inputLayoutAlamat, inputLayoutRtRw;
    MaterialSpinner spinnerJenisKelamin, spinnerStatus;
    TextView title;
    SessionManager session;
    String apiKey, axi_id, nama, email, hp, namaibu, area, cabang;
    String no_ktp, tempat_lahir, tanggal, alamat, rtrw, desa, distrik, kota, provinsi, kodepos, jk, status;

    SearchableSpinner spinnerProvinsi, spinnerKota, spinnerDistrik, spinnerDesa;
    MaterialProgressBar progressBar;

    InterfaceAreaBank apiServiceArea;

    final List<String> PROVINSI_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> PROVINSI_DATA = new HashMap<Integer, String>();
    final List<String> KOTA_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> KOTA_DATA = new HashMap<Integer, String>();
    final List<String> DISTRIK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> DISTRIK_DATA = new HashMap<Integer, String>();
    final List<String> DESA_ITEMS = new ArrayList<>();
    final List<String> DESA_KODEPOS = new ArrayList<>();
    final HashMap<Integer, String> DESA_DATA = new HashMap<Integer, String>();
    private static final String KTP_PATTERN =
            "^(?!0)([0-9]{12,12})(?=[0-9]{4,4})(?!(0{4})).{4,4}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(RegisterAxi2Activity.this);
        if(session.isLoggedIn() == TRUE){
            getSupportActionBar().setTitle("Tambah Rekan Bisnis");
        }

        btnLanjut               = findViewById(R.id.btnLanjut);
        inputKtp                = findViewById(R.id.inputKtp);
        inputTempatLahir        = findViewById(R.id.inputTempatLahir);
        inputAlamat             = findViewById(R.id.inputAlamat);
        inputRtRw               = findViewById(R.id.inputRtRw);
        inputKodepos            = findViewById(R.id.inputKodepos);
        inputLayoutKtp          = findViewById(R.id.inputLayoutKtp);
        inputLayoutTempatLahir  = findViewById(R.id.inputLayoutTempatLahir);
        inputLayoutAlamat       = findViewById(R.id.inputLayoutAlamat);
        inputLayoutRtRw         = findViewById(R.id.inputLayoutRtRw);

        spinnerJenisKelamin     = findViewById(R.id.spinnerJenisKelamin);
        spinnerStatus           = findViewById(R.id.spinnerStatus);
        inputTanggal            = findViewById(R.id.inputTanggal);
        title                   = findViewById(R.id.title);
        progressBar             = findViewById(R.id.progressBar);

        spinnerProvinsi         = findViewById(R.id.spinnerProvinsi);
        spinnerKota             = findViewById(R.id.spinnerKota);
        spinnerDistrik          = findViewById(R.id.spinnerDistrik);
        spinnerDesa             = findViewById(R.id.spinnerDesa);

        try {
            apiKey = getIntent().getStringExtra("apiKey");
        }catch (Exception ex){
            final SessionManager session = new SessionManager(getBaseContext());
            apiKey = "Bearer " + session.getToken();
        }
        axi_id  = getIntent().getStringExtra("axi_id");
        nama    = getIntent().getStringExtra("nama");
        email   = getIntent().getStringExtra("email");
        hp      = getIntent().getStringExtra("hp");
        namaibu = getIntent().getStringExtra("namaibu");
        area    = getIntent().getStringExtra("area");
        cabang  = getIntent().getStringExtra("cabang");

        inputTanggal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                DialogFragment dialogfragment = new DatePickerDialogTheme4();

                dialogfragment.show(getSupportFragmentManager(), "Theme 4");
            }
        });
        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title.setTypeface(opensans_bold);

        inputTanggal.setKeyListener(null);
        inputTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogfragment = new UbahAxiActivity.DatePickerDialogTheme4();

                dialogfragment.show(getSupportFragmentManager(), "Theme 4");
            }
        });

        final List<String> JK_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JK_DATA = new HashMap<Integer, String>();

        JK_ITEMS.clear();
        JK_DATA.clear();

        JK_DATA.put(1, "Laki-laki");
        JK_DATA.put(2, "Perempuan");
        JK_ITEMS.add("Laki-laki");
        JK_ITEMS.add("Perempuan");


        ArrayAdapter<String> jk_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JK_ITEMS);
        jk_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(jk_adapter);

        final List<String> STATUS_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> STATUS_DATA = new HashMap<Integer, String>();

        STATUS_ITEMS.clear();
        STATUS_DATA.clear();

        STATUS_DATA.put(1, "Menikah");
        STATUS_DATA.put(2, "Belum Menikah");
        STATUS_ITEMS.add("Menikah");
        STATUS_ITEMS.add("Belum Menikah");


        ArrayAdapter<String> status_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, STATUS_ITEMS);
        status_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(status_adapter);

        progressBar.setVisibility(View.GONE);
        spinnerKota.setEnabled(false);
        spinnerDistrik.setEnabled(false);
        spinnerDesa.setEnabled(false);

        initAction();
        initLoadData();

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    no_ktp = inputKtp.getText().toString();
                    tempat_lahir = inputTempatLahir.getText().toString();
                    tanggal = inputTanggal.getText().toString();
                    alamat = inputAlamat.getText().toString();
                    rtrw = inputRtRw.getText().toString();

                    provinsi = PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition());
                    kota = KOTA_DATA.get(spinnerKota.getSelectedItemPosition());
                    distrik = DISTRIK_DATA.get(spinnerDistrik.getSelectedItemPosition());
                    desa = DESA_DATA.get(spinnerDesa.getSelectedItemPosition());
                    kodepos = inputKodepos.getText().toString();

                    jk = JK_DATA.get(spinnerJenisKelamin.getSelectedItemPosition());
                    status = STATUS_DATA.get(spinnerStatus.getSelectedItemPosition());
                } catch (Exception ex) {

                }

                if(validateForm(no_ktp, tempat_lahir, tanggal, alamat, rtrw, desa, distrik, kota, provinsi, kodepos, jk, status)) {
                    Intent intent = new Intent(getBaseContext(), RegisterAxi3Activity.class);
                    intent.putExtra("apiKey",apiKey);
                    intent.putExtra("axi_id",axi_id);
                    intent.putExtra("nama",nama);
                    intent.putExtra("email",email);
                    intent.putExtra("hp", hp);
                    intent.putExtra("namaibu", namaibu);
                    intent.putExtra("area", area);
                    intent.putExtra("cabang", cabang);
                    intent.putExtra("no_ktp",no_ktp);
                    intent.putExtra("tempat_lahir",tempat_lahir);
                    intent.putExtra("tanggal",tanggal);
                    intent.putExtra("alamat",alamat);
                    intent.putExtra("rtrw",rtrw);
                    intent.putExtra("kelurahan",desa);
                    intent.putExtra("kecamatan",distrik);
                    intent.putExtra("kota",kota);
                    intent.putExtra("provinsi",provinsi);
                    intent.putExtra("kodepos",kodepos);
                    intent.putExtra("jk",jk);
                    intent.putExtra("status",status);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateForm(String no_ktp, String tempat_lahir, String tanggal, String alamat, String rtrw, String desa, String distrik, String kota, String provinsi, String kodepos, String jk, String status) {
        if (no_ktp == null || no_ktp.trim().length() == 0 || no_ktp.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan no.KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKtp);
                }
            });
            alertDialog.show();
            return false;
        } else if (no_ktp.trim().length() < 16){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("No.KTP harus 16 karakter");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKtp);
                }
            });
            alertDialog.show();
            return false;
        } else if (!no_ktp.matches(KTP_PATTERN))
        {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("No.KTP tidak valid");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKtp);
                }
            });
            alertDialog.show();
            return false;
        }
        if (tempat_lahir == null || tempat_lahir.trim().length() == 0 || tempat_lahir.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan tempat lahir");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTempatLahir);
                }
            });
            alertDialog.show();
            return false;
        }
        if (tanggal == null || tanggal.trim().length() == 0 || tanggal.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan tanggal lahir");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTanggal);
                }
            });
            alertDialog.show();
            return false;
        }
        if(jk == null || jk.trim().length() == 0 || jk.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Pilih jenis kelamin");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJenisKelamin);
                    spinnerJenisKelamin.performClick();
                }
            });
            alertDialog.show();
            return false;
        }
        if (alamat == null || alamat.trim().length() == 0 || alamat.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan alamat");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamat);
                }
            });
            alertDialog.show();
            return false;
        }
        if (rtrw == null || rtrw.trim().length() == 0 || rtrw.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan RT/RW");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputRtRw);
                }
            });
            alertDialog.show();
            return false;
        }
        if(status == null || status.trim().length() == 0 || status.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Pilih status perkawinan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerStatus);
                    spinnerStatus.performClick();
                }
            });
            alertDialog.show();
            return false;
        }
        if (desa == null || desa.trim().length() == 0 || desa.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan desa");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputDesa);
                }
            });
            alertDialog.show();
            return false;
        }
        if (distrik == null || distrik.trim().length() == 0 || distrik.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan distrik");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputDistrik);
                }
            });
            alertDialog.show();
            return false;
        }
        if (kota == null || kota.trim().length() == 0 || kota.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan kota");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKota);
                }
            });
            alertDialog.show();
            return false;
        }
        if (provinsi == null || provinsi.trim().length() == 0 || provinsi.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan provinsi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputProvinsi);
                }
            });
            alertDialog.show();
            return false;
        }
        if (kodepos == null || kodepos.trim().length() == 0 || kodepos.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan kode pos");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKodepos);
                }
            });
            alertDialog.show();
            return false;
        } else if (kodepos.trim().length() < 5){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Kode Pos harus 5 karakter");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKodepos);
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static class DatePickerDialogTheme4 extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

            return datepickerdialog;
        }
        public void onDateSet(DatePicker view, int year, int month, int day){

            EditText textview = (EditText)getActivity().findViewById(R.id.inputTanggal);

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

    private void initLoadData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<Provinsi> call = apiServiceArea.getProvinsi();
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
                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
                    Call<Kota> call = apiServiceArea.getKota(PROVINSI_DATA.get(spinnerProvinsi.getSelectedItemPosition()));
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
                                        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
                                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
                                Call<Distrik> call = apiServiceArea.getDistrik(KOTA_DATA.get(spinnerKota.getSelectedItemPosition()));
                                call.enqueue(new Callback<Distrik>() {
                                    @Override
                                    public void onResponse(Call<Distrik> call, Response<Distrik> response) {
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
                                                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
                                                    alertDialog.setTitle("Perhatian");
                                                    alertDialog.setMessage("Data distrik gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
                                            alertDialog.setTitle("Perhatian");
                                            alertDialog.setMessage("Data distrik gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                    public void onFailure(Call<Distrik> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Data distrik gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                                            Call<Desa> call = apiServiceArea.getDesa(DISTRIK_DATA.get(spinnerDistrik.getSelectedItemPosition()));
                                            call.enqueue(new Callback<Desa>() {
                                                @Override
                                                public void onResponse(Call<Desa> call, Response<Desa> response) {
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
                                                                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
                                                        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
                                                public void onFailure(Call<Desa> call, Throwable t) {
                                                    progressBar.setVisibility(View.GONE);
                                                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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

                                            spinnerDesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                    clearKodePos();
                                                    if (!DESA_KODEPOS.get(spinnerDesa.getSelectedItemPosition()).equals("Null") && Integer.parseInt(DESA_KODEPOS.get(spinnerDesa.getSelectedItemPosition())) > 0) {
                                                        inputKodepos.setText(DESA_KODEPOS.get(spinnerDesa.getSelectedItemPosition()));
                                                        inputKodepos.setEnabled(false);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        apiServiceArea = RetrofitClient2.getClient().create(InterfaceAreaBank.class);
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


    private void clearKodePos() {
        inputKodepos.setEnabled(true);
        inputKodepos.setText("");
    }


}