package com.dicicilaja.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMaxi4Activity extends AppCompatActivity {

    Button btnLanjut;
    RadioButton radio1, radio2, radio3, radio4, radio5, radio6;
    EditText inputEmail;
    TextInputLayout inputLayoutEmail;
    String apiKey, namapemilik, alamatpemilik, nohp, noktp, jk;
    String namaperusahaan, alamatperusahaan, kelurahan, kota, telp, desa;
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
        inputEmail = findViewById(R.id.inputEmail);
        inputLayoutEmail = findViewById(R.id.inputLayoutEmail);
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
        desa = getIntent().getStringExtra("desa");
        telp = getIntent().getStringExtra("telp");
        apiKey = getIntent().getStringExtra("apiKey");
        npwppemilik = getIntent().getStringExtra("npwppemilik");
        npwpperusahaan = getIntent().getStringExtra("npwpperusahaan");

        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputEmail.removeTextChangedListener(this);
                validateEmail();
                inputEmail.addTextChangedListener(this);
            }
        });

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
                    email = inputEmail.getText().toString();
                } catch (Exception ex) {

                }

                if(validateForm(email, program_id)) {

                    Log.d("ajukanpengajuan","apiKey : " + apiKey);
                    Log.d("ajukanpengajuan","namapemilik : " + namapemilik);
                    Log.d("ajukanpengajuan","jk : " + jk);
                    Log.d("ajukanpengajuan","alamatpemilik : " + alamatpemilik);
                    Log.d("ajukanpengajuan","nohp : " + nohp);
                    Log.d("ajukanpengajuan","noktp : " + noktp);
                    Log.d("ajukanpengajuan","namaperusahaan : " + namaperusahaan);
                    Log.d("ajukanpengajuan","alamatperusahaan : " + alamatperusahaan);
                    Log.d("ajukanpengajuan","desa : " + desa);
                    Log.d("ajukanpengajuan","telp : " + telp);
                    Log.d("ajukanpengajuan","npwppemilik : " + npwppemilik);
                    Log.d("ajukanpengajuan","npwpperusahaan : " + npwpperusahaan);
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
                    intent.putExtra("desa", desa);
                    intent.putExtra("telp", telp);
                    intent.putExtra("npwppemilik", npwppemilik);
                    intent.putExtra("npwpperusahaan", npwpperusahaan);
                    intent.putExtra("email", email);
                    intent.putExtra("program_id", program_id);
                    intent.putExtra("program_nama", program_nama);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean validateForm(String email, String program_id) {


        if(email == null || email.trim().length() == 0 || email.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi4Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan alamat email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isEmailValid(email)) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi4Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan alamat email dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
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

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateEmail() {
        if (inputEmail.getText().toString().trim().isEmpty()) {
            inputLayoutEmail.setErrorEnabled(false);
        } else {
            String emailId = inputEmail.getText().toString();
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutEmail.setError("Masukan alamat email dengan benar\ncontoh: budi.susanto@gmail.com");
                requestFocus(inputEmail);
                return false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
            }
        }
        return true;
    }
}
