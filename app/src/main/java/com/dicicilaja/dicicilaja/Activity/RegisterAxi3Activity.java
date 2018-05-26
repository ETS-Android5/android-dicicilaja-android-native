package com.dicicilaja.dicicilaja.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.dicicilaja.R;

public class RegisterAxi3Activity extends AppCompatActivity {

    Button btnLanjut;
    TextView title;

    EditText inputNamaBank, inputNoRekening, inputCabangBank, inputANRekening, inputKotaBank;
    TextInputLayout inputLayoutNamaBank, inputLayoutNoRekening, inputLayoutCabangBank, inputLayoutANRekening, inputLayoutKotaBank;
    String axi_id, nama, email, hp, namaibu, area, cabang;
    String no_ktp, tempat_lahir, tanggal, alamat, rtrw, kelurahan, kecamatan, kota, provinsi, kodepos, jk, status;
    String nama_bank, no_rekening, cabang_bank, an_rekening, kota_bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title                   = findViewById(R.id.title);
        inputNamaBank           = findViewById(R.id.inputNamaBank);
        inputNoRekening         = findViewById(R.id.inputNoRekening);
        inputCabangBank         = findViewById(R.id.inputCabangBank);
        inputANRekening         = findViewById(R.id.inputANRekening);
        inputKotaBank           = findViewById(R.id.inputKotaBank);
        inputLayoutNamaBank     = findViewById(R.id.inputLayoutNamaBank);
        inputLayoutNoRekening   = findViewById(R.id.inputLayoutNoRekening);
        inputLayoutCabangBank   = findViewById(R.id.inputLayoutCabangBank);
        inputLayoutANRekening   = findViewById(R.id.inputLayoutANRekening);
        inputLayoutKotaBank     = findViewById(R.id.inputLayoutKotaBank);

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


        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title.setTypeface(opensans_bold);


        btnLanjut = findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    nama_bank   = inputNamaBank.getText().toString();
                    no_rekening = inputNoRekening.getText().toString();
                    cabang_bank = inputCabangBank.getText().toString();
                    an_rekening = inputANRekening.getText().toString();
                    kota_bank   = inputKotaBank.getText().toString();
                } catch (Exception ex) {

                }
                android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi3Activity.this);
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
                        + "status: " + status + "\n"
                        + "nama_bank: " + nama_bank + "\n"
                        + "no_rekening: " + no_rekening + "\n"
                        + "cabang_bank: " + cabang_bank + "\n"
                        + "an_rekening: " + an_rekening + "\n"
                        + "kota_bank: " + kota_bank + "\n");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(validateForm(nama_bank, no_rekening, cabang_bank, an_rekening, kota_bank)) {
                            Intent intent = new Intent(getBaseContext(), RegisterAxi4Activity.class);
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
                            intent.putExtra("no_rekening",no_rekening);
                            intent.putExtra("cabang_bank",cabang_bank);
                            intent.putExtra("an_rekening",an_rekening);
                            intent.putExtra("kota_bank",kota_bank);
                            startActivity(intent);
                        }
                    }
                });
                alertDialog.show();
            }
        });
    }
    private boolean validateForm(String nama_bank, String no_rekening, String cabang_bank, String an_rekening, String kota_bank) {
        if (nama_bank == null || nama_bank.trim().length() == 0 || nama_bank.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setMessage("Masukan nama bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaBank);
                }
            });
            alertDialog.show();
            return false;
        }
        if (no_rekening == null || no_rekening.trim().length() == 0 || no_rekening.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setMessage("Masukan no.rekening");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoRekening);
                }
            });
            alertDialog.show();
            return false;
        }
        if (cabang_bank == null || cabang_bank.trim().length() == 0 || cabang_bank.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setMessage("Masukan cabang bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputCabangBank);
                }
            });
            alertDialog.show();
            return false;
        }
        if (an_rekening == null || an_rekening.trim().length() == 0 || an_rekening.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setMessage("Masukan a/n rekening");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputANRekening);
                }
            });
            alertDialog.show();
            return false;
        }
        if (kota_bank == null || kota_bank.trim().length() == 0 || kota_bank.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setMessage("Masukan kota bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKotaBank);
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
