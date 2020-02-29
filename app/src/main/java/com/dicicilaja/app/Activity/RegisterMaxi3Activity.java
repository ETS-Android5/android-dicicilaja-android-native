package com.dicicilaja.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterMaxi3Activity extends AppCompatActivity {

    Button btnLanjut;
    EditText inputNPWPPemilik, inputNPWPPerusahaan;
    TextInputLayout inputLayoutNPWPPemilik, inputLayoutNPWPPerusahaan;
    String apiKey, namapemilik, alamatpemilik, nohp, noktp, jk;
    String namaperusahaan, alamatperusahaan, kelurahan, kota, telp;
    String npwppemilik, npwpperusahaan, desa;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_maxi3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputNPWPPemilik = findViewById(R.id.inputNPWPPemilik);
        inputNPWPPerusahaan = findViewById(R.id.inputNPWPPerusahaan);
        inputLayoutNPWPPemilik  = findViewById(R.id.inputLayoutNPWPPemilik);
        inputLayoutNPWPPerusahaan  = findViewById(R.id.inputLayoutNPWPPerusahaan);
        title = findViewById(R.id.title);

        namapemilik = getIntent().getStringExtra("namapemilik");
        alamatpemilik = getIntent().getStringExtra("alamatpemilik");
        nohp = getIntent().getStringExtra("nohp");
        noktp = getIntent().getStringExtra("noktp");
        jk = getIntent().getStringExtra("jk");
        namaperusahaan = getIntent().getStringExtra("namaperusahaan");
        alamatperusahaan = getIntent().getStringExtra("alamatperusahaan");
        desa = getIntent().getStringExtra("desa");
        telp = getIntent().getStringExtra("telp");
        apiKey = getIntent().getStringExtra("apiKey");


        btnLanjut = findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    npwppemilik = inputNPWPPemilik.getText().toString();
                    npwpperusahaan = inputNPWPPerusahaan.getText().toString();
                } catch (Exception ex) {

                }

                if(validateForm(npwppemilik, npwpperusahaan)) {

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
                    Log.d("ajukanpengajuan","npwppemilik : " + npwppemilik);
                    Log.d("ajukanpengajuan","npwpperusahaan : " + npwpperusahaan);


                    Intent intent = new Intent(getBaseContext(), RegisterMaxi4Activity.class);
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
                    intent.putExtra("npwppemilik", npwppemilik);
                    intent.putExtra("npwpperusahaan", npwpperusahaan);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateForm(String npwppemilik, String npwpperusahaan) {
        if (npwppemilik == null || npwppemilik.trim().length() == 0 || npwppemilik.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi3Activity.this);
            alertDialog.setMessage("Masukan no. NPWP pemilik");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWPPemilik);
                }
            });
            alertDialog.show();
            return false;
        }  else if (npwppemilik.trim().length() < 15){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("No. NPWP pemilik harus 15 digit");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWPPemilik);
                }
            });
            alertDialog.show();
            return false;
        }

        if(npwpperusahaan == null || npwpperusahaan.trim().length() == 0 || npwpperusahaan.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi3Activity.this);
            alertDialog.setMessage("Masukan no. NPWP perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWPPerusahaan);
                }
            });
            alertDialog.show();
            return false;
        }  else if (npwpperusahaan.trim().length() < 15){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("No. NPWP perusahaan harus 15 digit");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWPPerusahaan);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
