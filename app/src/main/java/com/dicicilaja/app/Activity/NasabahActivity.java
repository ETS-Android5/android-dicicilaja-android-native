package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.app.API.Client.ApiClient2;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateCustomer;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateCustomer.CreateCustomer;
import com.dicicilaja.app.R;

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
                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(NasabahActivity.this);
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

    private void buatAkun(final String nama, final String email, final String no_hp, final String password) {
        InterfaceCreateCustomer apiService =
                ApiClient2.getClient().create(InterfaceCreateCustomer.class);
        Call<CreateCustomer> call = apiService.create(apiKey, nama, email, no_hp, password, password);
        call.enqueue(new Callback<CreateCustomer>() {
            @Override
            public void onResponse(Call<CreateCustomer> call, Response<CreateCustomer> response) {
                if(response.isSuccessful()){
                    progress.dismiss();
                    Toast.makeText(getBaseContext(),"Selamat! Link verifikasi telah dikirimkan ke email Anda, Silahkan cek untuk melengkapi proses registrasi.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    progress.dismiss();
                    Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CreateCustomer> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private boolean validateForm(String nama, String email, String nohp, String katasandi, String konfirmasi) {
        if (nama == null || nama.trim().length() == 0 || nama.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(NasabahActivity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(NasabahActivity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(NasabahActivity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(NasabahActivity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(NasabahActivity.this);
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
