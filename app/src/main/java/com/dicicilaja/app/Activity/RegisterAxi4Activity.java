package com.dicicilaja.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ganfra.materialspinner.MaterialSpinner;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

import static java.lang.Boolean.TRUE;

public class RegisterAxi4Activity extends AppCompatActivity {

    EditText inputNPWP, inputNamaNPWP;
    SearchableSpinner spinnerPKPStatus, spinnerStatusNPWP;
    TextInputLayout inputLayoutNPWP, inputLayoutStatusNPWP, inputLayoutPKPStatus, inputLayoutNamaNPWP;
    Button btnLanjut;
    TextView title;
    SessionManager session;
    String apiKey,axi_id, nama, email, hp, namaibu, area, cabang;
    String no_ktp_pasangan, nama_pasangan, no_ktp, tempat_lahir, tanggal, alamat, rt, rw, kelurahan, kecamatan, kota, provinsi, kodepos, jk, status;
    String nama_bank, kode_bank, no_rekening, cabang_bank, an_rekening, kota_bank;
    String npwp, nama_npwp, status_npwp, pkp_status;

    MaterialProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi4);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(RegisterAxi4Activity.this);
        if(session.isLoggedIn() == TRUE){
            getSupportActionBar().setTitle("Tambah Rekan Bisnis");
        }

        title                   = findViewById(R.id.title);
        inputNPWP               = findViewById(R.id.inputNPWP);
        inputNamaNPWP           = findViewById(R.id.inputNamaNPWP);
        spinnerStatusNPWP       = findViewById(R.id.spinnerStatusNPWP);
        spinnerPKPStatus        = findViewById(R.id.spinnerPKPStatus);
        inputLayoutNPWP         = findViewById(R.id.inputLayoutNPWP);
        inputLayoutNamaNPWP         = findViewById(R.id.inputLayoutNamaNPWP);
        inputLayoutStatusNPWP   = findViewById(R.id.inputLayoutStatusNPWP);
        inputLayoutPKPStatus    = findViewById(R.id.inputLayoutPKPStatus);
        progressBar             = findViewById(R.id.progressBar);

        apiKey          = getIntent().getStringExtra("apiKey");
        axi_id          = getIntent().getStringExtra("axi_id");
        nama            = getIntent().getStringExtra("nama");
        email           = getIntent().getStringExtra("email");
        hp              = getIntent().getStringExtra("hp");
        namaibu         = getIntent().getStringExtra("namaibu");
        area            = getIntent().getStringExtra("area");
        cabang          = getIntent().getStringExtra("cabang");
        no_ktp          = getIntent().getStringExtra("no_ktp");
        tempat_lahir    = getIntent().getStringExtra("tempat_lahir");
        tanggal         = getIntent().getStringExtra("tanggal");
        alamat          = getIntent().getStringExtra("alamat");
        rt            = getIntent().getStringExtra("rt");
        rw            = getIntent().getStringExtra("rw");
        kelurahan       = getIntent().getStringExtra("kelurahan");
        kecamatan       = getIntent().getStringExtra("kecamatan");
        kota            = getIntent().getStringExtra("kota");
        provinsi        = getIntent().getStringExtra("provinsi");
        kodepos         = getIntent().getStringExtra("kodepos");
        jk              = getIntent().getStringExtra("jk");
        status          = getIntent().getStringExtra("status");
        nama_bank       = getIntent().getStringExtra("nama_bank");
        kode_bank       = getIntent().getStringExtra("kode_bank");
        no_rekening     = getIntent().getStringExtra("no_rekening");
        cabang_bank     = getIntent().getStringExtra("cabang_bank");
        an_rekening     = getIntent().getStringExtra("an_rekening");
        kota_bank       = getIntent().getStringExtra("kota_bank");
        nama_pasangan          = getIntent().getStringExtra("nama_pasangan");
        no_ktp_pasangan          = getIntent().getStringExtra("no_ktp_pasangan");

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");


        final List<String> NPWP_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> NPWP_DATA = new HashMap<Integer, String>();

        NPWP_ITEMS.clear();
        NPWP_DATA.clear();

        NPWP_DATA.put(0, "0");
        NPWP_DATA.put(1, "1");
        NPWP_DATA.put(2, "2");
        NPWP_ITEMS.add("Pilih Status NPWP");
        NPWP_ITEMS.add("Pribadi");
        NPWP_ITEMS.add("Perusahaan");



        ArrayAdapter<String> npwp_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, NPWP_ITEMS);
        npwp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusNPWP.setAdapter(npwp_adapter);
        spinnerStatusNPWP.setTitle("");
        spinnerStatusNPWP.setPositiveButton("OK");

        final List<String> PKP_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> PKP_DATA = new HashMap<Integer, String>();

        PKP_ITEMS.clear();
        PKP_DATA.clear();

        PKP_DATA.put(0, "0");
        PKP_DATA.put(1, "1");
        PKP_DATA.put(2, "2");
        PKP_ITEMS.add("Pilih PKP Status");
        PKP_ITEMS.add("PKP");
        PKP_ITEMS.add("Non PKP");


        ArrayAdapter<String> pkp_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, PKP_ITEMS);
        pkp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPKPStatus.setAdapter(pkp_adapter);
        spinnerPKPStatus.setTitle("");
        spinnerPKPStatus.setPositiveButton("OK");

        btnLanjut = findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    npwp = inputNPWP.getText().toString();
                    nama_npwp = inputNamaNPWP.getText().toString();
                    status_npwp = NPWP_DATA.get(spinnerStatusNPWP.getSelectedItemPosition());
                    pkp_status = PKP_DATA.get(spinnerPKPStatus.getSelectedItemPosition());
                } catch (Exception ex) {

                }

                if(validateForm(npwp, nama_npwp)) {
                    Intent intent = new Intent(getBaseContext(), RegisterAxi5Activity.class);
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
                    intent.putExtra("rt",rt);
                    intent.putExtra("rw",rw);
                    intent.putExtra("kelurahan",kelurahan);
                    intent.putExtra("kecamatan",kecamatan);
                    intent.putExtra("kota",kota);
                    intent.putExtra("provinsi",provinsi);
                    intent.putExtra("kodepos",kodepos);
                    intent.putExtra("jk",jk);
                    intent.putExtra("status",status);
                    intent.putExtra("nama_bank",nama_bank);
                    intent.putExtra("kode_bank",kode_bank);
                    intent.putExtra("no_rekening",no_rekening);
                    intent.putExtra("cabang_bank",cabang_bank);
                    intent.putExtra("an_rekening",an_rekening);
                    intent.putExtra("kota_bank",kota_bank);
                    intent.putExtra("npwp",npwp);
                    intent.putExtra("nama_npwp",nama_npwp);
                    intent.putExtra("status_npwp",status_npwp);
                    intent.putExtra("pkp_status",pkp_status);
                    intent.putExtra("nama_pasangan",nama_pasangan);
                    intent.putExtra("no_ktp_pasangan",no_ktp_pasangan);
                    startActivity(intent);
                }
            }
        });

        inputNamaNPWP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputNamaNPWP.removeTextChangedListener(this);
                validateIsNamaNPWP();
                inputNamaNPWP.addTextChangedListener(this);
            }
        });
    }
    private boolean validateForm(String npwp, String nama_npwp) {
        if (npwp == null || npwp.trim().length() == 0 || npwp.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi4Activity.this);
            alertDialog.setMessage("Masukan no. NPWP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWP);
                }
            });
            alertDialog.show();
            return false;
        } else if (npwp.trim().length() < 15){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi4Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("No. NPWP harus 15 digit");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWP);
                }
            });
            alertDialog.show();
            return false;
        }

        if (nama_npwp == null || nama_npwp.trim().length() == 0 || nama_npwp.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi4Activity.this);
            alertDialog.setMessage("Masukan atas nama NPWP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaNPWP);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isNamaNPWP(nama_npwp)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxi4Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan atas nama NPWP dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaNPWP);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }

    public static boolean isNamaNPWP(String alamat) {
        String expression = "^[a-z.'/ A-Z]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateIsNamaNPWP() {
        if (inputNamaNPWP.getText().toString().trim().isEmpty()) {
            inputLayoutNamaNPWP.setErrorEnabled(false);
        } else {
            String emailId = inputNamaNPWP.getText().toString();
            String expression = "^[a-z.'/ A-Z]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutNamaNPWP.setError("Format atas nama NPWP salah");
                requestFocus(inputNamaNPWP);
                return false;
            } else {
                inputLayoutNamaNPWP.setErrorEnabled(false);
            }
        }
        return true;
    }
}
