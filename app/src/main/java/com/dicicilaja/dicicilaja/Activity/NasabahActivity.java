package com.dicicilaja.dicicilaja.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.API.Item.Login.Login;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateAXI;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateCustomer;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateAXI.CreateAXI;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateCustomer.CreateCustomer;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasabahActivity extends AppCompatActivity {

    TextView sudahPunyaAkun, textCheck;
    EditText inputNamaLengkap, inputEmail, inputNoHp, inputKataSandi, inputKonfirmasi;
    String nama, email, nohp, katasandi, konfirmasi;
    Button btnDaftar;
    CheckBox check;
    String apiKey;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasabah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sudahPunyaAkun = findViewById(R.id.sudahPunyaAkun);
        inputNamaLengkap = findViewById(R.id.inputNamaLengkap);
        inputEmail = findViewById(R.id.inputEmail);
        inputNoHp = findViewById(R.id.inputNoHp);
        inputKataSandi = findViewById(R.id.inputKataSandi);
        inputKonfirmasi = findViewById(R.id.inputKonfirmasi);
        btnDaftar = findViewById(R.id.btnDaftar);
        check = findViewById(R.id.check);
        textCheck = findViewById(R.id.textCheck);

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);

        textCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {
                    check.setChecked(false);
                }
                else {
                    check.setChecked(true);
                }
            }
        });

        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    nama = inputNamaLengkap.getText().toString();
                    email = inputEmail.getText().toString();
                    nohp = inputNoHp.getText().toString();
                    katasandi = inputKataSandi.getText().toString();
                    konfirmasi = inputKonfirmasi.getText().toString();
                } catch (Exception ex) {

                }
                if(validateForm(nama, email, nohp, katasandi, konfirmasi)) {

                    if(!katasandi.equals(konfirmasi)) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NasabahActivity.this);
                        alertDialog.setMessage("Kata sandi dan konfirmasi kata sandi berbeda");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestFocus(inputKataSandi);
                            }
                        });
                        alertDialog.show();
                    }else{
                        if(check.isChecked()){
                            Log.d("ajukanpengajuan","nama : " + nama);
                            Log.d("ajukanpengajuan", "email : " + email);
                            Log.d("ajukanpengajuan", "nohp : " + nohp);
                            Log.d("ajukanpengajuan", "katasandi : " + katasandi);
                            Log.d("ajukanpengajuan", "konfirmasi : " + konfirmasi);
                            progress.show();
                            buatAkun(nama, email, nohp, katasandi);

                        }else {
                            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(NasabahActivity.this);
                            alertDialog.setMessage("Harap setujui syarat dan ketentuan jika ingin mendaftar");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }

                    }

                }

            }
        });
    }

    private void buatAkun(final String nama, final String email, final String nohp, final String katasandi) {
        InterfaceCreateCustomer apiService =
                RetrofitClient.getClient().create(InterfaceCreateCustomer.class);
        String area = "1";
        String branch = "1";
        String address = "-";
        String province = "-";
        String city = "-";
        String district = "-";
        String subdistrict = "-";
        String gender = "-";
        String birth_date = "-";
        Call<CreateCustomer> call = apiService.create(email, katasandi, nama, nohp, area, branch, address, province, city, district, subdistrict, gender, birth_date);
        call.enqueue(new Callback<CreateCustomer>() {
            @Override
            public void onResponse(Call<CreateCustomer> call, Response<CreateCustomer> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getBaseContext(),"Selamat! Link verifikasi telah dikirimkan ke email Anda, Silahkan cek untuk melengkapi proses registrasi.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, siolahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CreateCustomer> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, siolahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private boolean validateForm(String nama, String email, String nohp, String katasandi, String konfirmasi) {
        if (nama == null || nama.trim().length() == 0 || nama.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(NasabahActivity.this);
            alertDialog.setMessage("Masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaLengkap);
                }
            });
            alertDialog.show();
            return false;
        }

        if (email == null || email.trim().length() == 0 || email.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(NasabahActivity.this);
            alertDialog.setMessage("Masukan email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        }

        if (nohp == null || nohp.trim().length() == 0 || nohp.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(NasabahActivity.this);
            alertDialog.setMessage("Masukan no. Handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoHp);
                }
            });
            alertDialog.show();
            return false;
        }

        if (katasandi == null || katasandi.trim().length() == 0 || katasandi.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(NasabahActivity.this);
            alertDialog.setMessage("Masukan kata sandi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKataSandi);
                }
            });
            alertDialog.show();
            return false;
        }

        if (konfirmasi == null || konfirmasi.trim().length() == 0 || konfirmasi.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(NasabahActivity.this);
            alertDialog.setMessage("Masukan konfirmasi kata sandi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKonfirmasi);
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
