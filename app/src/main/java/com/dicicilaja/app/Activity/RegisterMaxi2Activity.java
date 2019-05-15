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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

public class RegisterMaxi2Activity extends AppCompatActivity {


    Button btnLanjut;
    EditText inputNamaPerusahaan, inputAlamatPerusahaan, inputKelurahan, inputKota, inputTelp;
    String namapemilik, alamatpemilik, nohp, noktp, jk;
    String namaperusahaan, alamatperusahaan, kelurahan, kota, telp;
    SessionManager session;
    String apiKey;
    TextView title;
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
        inputKelurahan = findViewById(R.id.inputKelurahan);
        inputKota = findViewById(R.id.inputKota);
        inputTelp = findViewById(R.id.inputTelp);
        title = findViewById(R.id.title);

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

        title.setTypeface(opensans_bold);



        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    namaperusahaan = inputNamaPerusahaan.getText().toString();
                    alamatperusahaan = inputAlamatPerusahaan.getText().toString();
                    kelurahan = inputKelurahan.getText().toString();
                    kota = inputKota.getText().toString();
                    telp = inputTelp.getText().toString();
                } catch (Exception ex) {

                }

                if(validateForm(namaperusahaan, alamatperusahaan, kelurahan, kota, telp)) {

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
                    intent.putExtra("kelurahan", kelurahan);
                    intent.putExtra("kota", kota);
                    intent.putExtra("telp", telp);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateForm(String namaperusahaan, String alamatperusahaan, String kelurahan, String kota, String telp) {
        if (namaperusahaan == null || namaperusahaan.trim().length() == 0 || namaperusahaan.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setMessage("Masukan nama perusahaan");

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
        }

        if (kelurahan == null || kelurahan.trim().length() == 0 || kelurahan.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setMessage("Masukan nama kelurahan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKelurahan);
                }
            });
            alertDialog.show();
            return false;
        }

        if (kota == null || kota.trim().length() == 0 || kota.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi2Activity.this);
            alertDialog.setMessage("Masukan nama kota");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKota);
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
