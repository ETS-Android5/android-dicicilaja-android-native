package com.dicicilaja.dicicilaja.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

import fr.ganfra.materialspinner.MaterialSpinner;

import com.dicicilaja.dicicilaja.Fragment.RegisterAxiFragment;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;

import static java.lang.Boolean.TRUE;

public class RegisterAxi2Activity extends AppCompatActivity {

    int year, month, day;
    Button btnLanjut;
    EditText inputKtp, inputTempatLahir, inputTanggal, inputAlamat, inputRtRw, inputKelurahan, inputKecamatan, inputKota, inputProvinsi, inputKodepos;
    TextInputLayout inputLayoutKtp, inputLayoutTempatLahir, inputLayoutAlamat, inputLayoutRtRw, inputLayoutKelurahan, inputLayoutKecamatan, inputLayoutKota,inputLayoutProvinsi, inputLayoutKodepos;
    MaterialSpinner spinnerJenisKelamin, spinnerStatus;
    TextView title;
    SessionManager session;
    String apiKey, axi_id, nama, email, hp, namaibu, area, cabang;
    String no_ktp, tempat_lahir, tanggal, alamat, rtrw, kelurahan, kecamatan, kota, provinsi, kodepos, jk, status;
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
        inputKelurahan          = findViewById(R.id.inputKelurahan);
        inputKecamatan          = findViewById(R.id.inputKecamatan);
        inputKota               = findViewById(R.id.inputKota);
        inputProvinsi           = findViewById(R.id.inputProvinsi);
        inputKodepos            = findViewById(R.id.inputKodepos);
        inputLayoutKtp          = findViewById(R.id.inputLayoutKtp);
        inputLayoutTempatLahir  = findViewById(R.id.inputLayoutTempatLahir);
        inputLayoutAlamat       = findViewById(R.id.inputLayoutAlamat);
        inputLayoutRtRw         = findViewById(R.id.inputLayoutRtRw);
        inputLayoutKelurahan    = findViewById(R.id.inputLayoutKelurahan);
        inputLayoutKecamatan    = findViewById(R.id.inputLayoutKecamatan);
        inputLayoutKota         = findViewById(R.id.inputLayoutKota);
        inputLayoutProvinsi     = findViewById(R.id.inputLayoutProvinsi);
        inputLayoutKodepos      = findViewById(R.id.inputLayoutKodepos);
        spinnerJenisKelamin     = findViewById(R.id.spinnerJenisKelamin);
        spinnerStatus           = findViewById(R.id.spinnerStatus);
        inputTanggal            = findViewById(R.id.inputTanggal);
        title                   = findViewById(R.id.title);

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


        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    no_ktp = inputKtp.getText().toString();
                    tempat_lahir = inputTempatLahir.getText().toString();
                    tanggal = inputTanggal.getText().toString();
                    alamat = inputAlamat.getText().toString();
                    rtrw = inputRtRw.getText().toString();
                    kelurahan = inputKelurahan.getText().toString();
                    kecamatan = inputKecamatan.getText().toString();
                    kota = inputKota.getText().toString();
                    provinsi = inputProvinsi.getText().toString();
                    kodepos = inputKodepos.getText().toString();
                    jk = JK_DATA.get(spinnerJenisKelamin.getSelectedItemPosition());
                    status = STATUS_DATA.get(spinnerStatus.getSelectedItemPosition());
                } catch (Exception ex) {

                }

                android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
                alertDialog.setMessage("axi_id: " + axi_id + "\n"
                        + "nama: " + nama + "\n"
                        + "email: " + email + "\n"
                        + "hp: " + hp + "\n"
                        + "namaibu: " + namaibu + "\n"
                        + "area: " + area + "\n"
                        + "cabang: " + cabang + "\n"
                        + "no_ktp: " + no_ktp + "\n"
                        + "tempat_lahir: " + tempat_lahir + "\n"
                        + "tanggal: " + tanggal + "\n"
                        + "alamat: " + alamat + "\n"
                        + "rtrw: " + rtrw + "\n"
                        + "kelurahan: " + kelurahan + "\n"
                        + "kecamatan: " + kecamatan + "\n"
                        + "kota: " + kota + "\n"
                        + "provinsi: " + provinsi + "\n"
                        + "kodepos: " + kodepos + "\n"
                        + "jk: " + jk + "\n"
                        + "status: " + status + "\n");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(validateForm(no_ktp, tempat_lahir, tanggal, alamat, rtrw, kelurahan, kecamatan, kota, provinsi, kodepos, jk, status)) {
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
                            intent.putExtra("kelurahan",kelurahan);
                            intent.putExtra("kecamatan",kecamatan);
                            intent.putExtra("kota",kota);
                            intent.putExtra("provinsi",provinsi);
                            intent.putExtra("kodepos",kodepos);
                            intent.putExtra("jk",jk);
                            intent.putExtra("status",status);
                            startActivity(intent);
                        }
                    }
                });
                alertDialog.show();
            }
        });
    }

    private boolean validateForm(String no_ktp, String tempat_lahir, String tanggal, String alamat, String rtrw, String kelurahan, String kecamatan, String kota, String provinsi, String kodepos, String jk, String status) {
        if (no_ktp == null || no_ktp.trim().length() == 0 || no_ktp.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan no.KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKtp);
                }
            });
            alertDialog.show();
            return false;
        }
        if (tempat_lahir == null || tempat_lahir.trim().length() == 0 || tempat_lahir.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
        if (kelurahan == null || kelurahan.trim().length() == 0 || kelurahan.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan kelurahan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKelurahan);
                }
            });
            alertDialog.show();
            return false;
        }
        if (kecamatan == null || kecamatan.trim().length() == 0 || kecamatan.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan kecamatan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKecamatan);
                }
            });
            alertDialog.show();
            return false;
        }
        if (kota == null || kota.trim().length() == 0 || kota.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi2Activity.this);
            alertDialog.setMessage("Masukan kode pos");

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

}