package com.dicicilaja.dicicilaja.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dicicilaja.dicicilaja.R;

public class RegisterMaxi4Activity extends AppCompatActivity {

    Button btnLanjut;
    RadioButton radio1, radio2, radio3, radio4, radio5, radio6;
    EditText inputKataSandi, inputEmail;
    String apiKey, namapemilik, alamatpemilik, nohp, noktp, jk;
    String namaperusahaan, alamatperusahaan, kelurahan, kota, telp;
    String npwppemilik, npwpperusahaan;
    String katasandi, email, program_id, program_nama;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_maxi4);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inputKataSandi = findViewById(R.id.inputKataSandi);
        inputEmail = findViewById(R.id.inputEmail);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radio5 = findViewById(R.id.radio5);
        radio6 = findViewById(R.id.radio6);
        title = findViewById(R.id.title);

        namapemilik = getIntent().getStringExtra("namapemilik");
        alamatpemilik = getIntent().getStringExtra("alamatpemilik");
        nohp = getIntent().getStringExtra("nohp");
        noktp = getIntent().getStringExtra("noktp");
        jk = getIntent().getStringExtra("jk");
        namaperusahaan = getIntent().getStringExtra("namaperusahaan");
        alamatperusahaan = getIntent().getStringExtra("alamatperusahaan");
        kelurahan = getIntent().getStringExtra("kelurahan");
        kota = getIntent().getStringExtra("kota");
        telp = getIntent().getStringExtra("telp");
        apiKey = getIntent().getStringExtra("apiKey");
        npwppemilik = getIntent().getStringExtra("npwppemilik");
        npwpperusahaan = getIntent().getStringExtra("npwpperusahaan");

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title.setTypeface(opensans_bold);

        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program_id = "2";
                program_nama = "Maxi Travel";

                radio1.setChecked(true);
                radio2.setChecked(false);
                radio3.setChecked(false);
                radio4.setChecked(false);
                radio5.setChecked(false);
                radio6.setChecked(false);
            }
        });

        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program_id = "3";
                program_nama = "Maxi Edukasi";

                radio1.setChecked(false);
                radio2.setChecked(true);
                radio3.setChecked(false);
                radio4.setChecked(false);
                radio5.setChecked(false);
                radio6.setChecked(false);
            }
        });

        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program_id = "4";
                program_nama = "Maxi Usaha";

                radio1.setChecked(false);
                radio2.setChecked(false);
                radio3.setChecked(true);
                radio4.setChecked(false);
                radio5.setChecked(false);
                radio6.setChecked(false);
            }
        });
        radio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program_id = "5";
                program_nama = "Maxi Sehat";

                radio1.setChecked(false);
                radio2.setChecked(false);
                radio3.setChecked(false);
                radio4.setChecked(true);
                radio5.setChecked(false);
                radio6.setChecked(false);
            }
        });
        radio5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program_id = "6";
                program_nama = "Maxi Griya";

                radio1.setChecked(false);
                radio2.setChecked(false);
                radio3.setChecked(false);
                radio4.setChecked(false);
                radio5.setChecked(true);
                radio6.setChecked(false);
            }
        });
        radio6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program_id = "7";
                program_nama = "Maxi Extraguna";

                radio1.setChecked(false);
                radio2.setChecked(false);
                radio3.setChecked(false);
                radio5.setChecked(false);
                radio4.setChecked(false);
                radio6.setChecked(true);
            }
        });




        btnLanjut = findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    katasandi = inputKataSandi.getText().toString();
                    email = inputEmail.getText().toString();
                } catch (Exception ex) {

                }

                if(validateForm(katasandi, email, program_id)) {

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
                    Log.d("ajukanpengajuan","katasandi : " + katasandi);
                    Log.d("ajukanpengajuan","email : " + email);
                    Log.d("ajukanpengajuan","program_id : " + program_id);
                    Log.d("ajukanpengajuan","program_nama : " + program_nama);


                    Intent intent = new Intent(getBaseContext(), RegisterMaxi5Activity.class);
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
                    intent.putExtra("npwppemilik", npwppemilik);
                    intent.putExtra("npwpperusahaan", npwpperusahaan);
                    intent.putExtra("katasandi", katasandi);
                    intent.putExtra("email", email);
                    intent.putExtra("program_id", program_id);
                    intent.putExtra("program_nama", program_nama);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean validateForm(String katasandi, String email, String program_id) {


        if(email == null || email.trim().length() == 0 || email.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterMaxi4Activity.this);
            alertDialog.setMessage("Masukan email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        }
        if (katasandi == null || katasandi.trim().length() == 0 || katasandi.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi4Activity.this);
            alertDialog.setMessage("Masukan kata sandi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKataSandi);
                }
            });
            alertDialog.show();
            return false;
        }
        if (program_id == null || program_id.trim().length() == 0 || program_id.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi4Activity.this);
            alertDialog.setMessage("Pilih program kemitraan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

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
