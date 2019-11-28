package com.dicicilaja.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

import static java.lang.Boolean.TRUE;

public class RegisterAxi4Activity extends AppCompatActivity {

    EditText inputNPWP, inputNamaNPWP;
    MaterialSpinner spinnerPKPStatus, spinnerStatusNPWP;
    TextInputLayout inputLayoutNPWP, inputLayoutStatusNPWP, inputLayoutPKPStatus;
    Button btnLanjut;
    TextView title;
    SessionManager session;
    String apiKey,axi_id, nama, email, hp, namaibu, area, cabang;
    String no_ktp, tempat_lahir, tanggal, alamat, rtrw, kelurahan, kecamatan, kota, provinsi, kodepos, jk, status;
    String nama_bank, kode_bank, no_rekening, cabang_bank, an_rekening, kota_bank;
    String npwp, nama_npwp, status_npwp, pkp_status;
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
        inputLayoutStatusNPWP   = findViewById(R.id.inputLayoutStatusNPWP);
        inputLayoutPKPStatus    = findViewById(R.id.inputLayoutPKPStatus);

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
        rtrw            = getIntent().getStringExtra("rtrw");
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

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title.setTypeface(opensans_bold);

        final List<String> NPWP_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> NPWP_DATA = new HashMap<Integer, String>();

        NPWP_ITEMS.clear();
        NPWP_DATA.clear();

        NPWP_DATA.put(1, "Perorangan");
        NPWP_DATA.put(2, "Badan");
        NPWP_ITEMS.add("Perorangan");
        NPWP_ITEMS.add("Badan");


        ArrayAdapter<String> npwp_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, NPWP_ITEMS);
        npwp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusNPWP.setAdapter(npwp_adapter);

        final List<String> PKP_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> PKP_DATA = new HashMap<Integer, String>();

        PKP_ITEMS.clear();
        PKP_DATA.clear();

        PKP_DATA.put(1, "PKP");
        PKP_DATA.put(2, "Non PKP");
        PKP_ITEMS.add("PKP");
        PKP_ITEMS.add("Non PKP");


        ArrayAdapter<String> pkp_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, PKP_ITEMS);
        pkp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPKPStatus.setAdapter(pkp_adapter);

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

                if(validateForm(npwp)) {
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
                    intent.putExtra("rtrw",rtrw);
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
                    startActivity(intent);
                }
            }
        });
    }
    private boolean validateForm(String npwp) {
        if (npwp == null || npwp.trim().length() == 0 || npwp.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi4Activity.this);
            alertDialog.setMessage("Masukan no. npwp");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWP);
                }
            });
            alertDialog.show();
            return false;
        } else if (npwp.trim().length() < 15){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi4Activity.this);
            alertDialog.setMessage("No.NPWP harus 15 karakter");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int which) {
                     requestFocus(inputNPWP);
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
}
