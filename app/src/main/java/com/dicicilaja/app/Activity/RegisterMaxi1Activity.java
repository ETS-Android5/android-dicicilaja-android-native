package com.dicicilaja.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.WebView.AboutMaxiActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class RegisterMaxi1Activity extends AppCompatActivity {

    Button btnLanjut;
    MaterialSpinner spinnerJenisKelamin;
    EditText inputNamaPemilik, inputAlamatPemilik, inputNoHp, inputKtp;
    String namapemilik, alamatpemilik, nohp, noktp, jk;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_maxi1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titleSection   = findViewById(R.id.titleSection);
        TextView bodySection    = findViewById(R.id.bodySection);
        TextView detailSection  = findViewById(R.id.detailSection);
        btnLanjut = findViewById(R.id.btnLanjut);

        inputNamaPemilik  = findViewById(R.id.inputNamaPemilik);
        inputAlamatPemilik  = findViewById(R.id.inputAlamatPemilik);
        inputNoHp  = findViewById(R.id.inputNoHp);
        inputKtp  = findViewById(R.id.inputKtp);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold      = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold  = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler   = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        detailSection.setTypeface(opensans_semibold);
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

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    namapemilik = inputNamaPemilik.getText().toString();
                    alamatpemilik = inputAlamatPemilik.getText().toString();
                    nohp = inputNoHp.getText().toString();
                    noktp = inputKtp.getText().toString();
                    jk = JK_DATA.get(spinnerJenisKelamin.getSelectedItemPosition());
                } catch (Exception ex) {

                }

                if(validateForm(namapemilik, jk, alamatpemilik, nohp, noktp)) {
                    Log.d("ajukanpengajuan","namapemilik : " + namapemilik);
                    Log.d("ajukanpengajuan","jk : " + jk);
                    Log.d("ajukanpengajuan","alamatpemilik : " + alamatpemilik);
                    Log.d("ajukanpengajuan","nohp : " + nohp);
                    Log.d("ajukanpengajuan","noktp : " + noktp);


                    Intent intent = new Intent(getBaseContext(), RegisterMaxi2Activity.class);
                    intent.putExtra("namapemilik",namapemilik);
                    intent.putExtra("alamatpemilik",alamatpemilik);
                    intent.putExtra("nohp",nohp);
                    intent.putExtra("noktp", noktp);
                    intent.putExtra("jk", jk);
                    startActivity(intent);
                }
            }
        });
        detailSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AboutMaxiActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateForm(String namapemilik, String jk, String alamatpemilik, String nohp, String noktp) {
        if (namapemilik == null || namapemilik.trim().length() == 0 || namapemilik.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi1Activity.this);
            alertDialog.setMessage("Masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaPemilik);
                }
            });
            alertDialog.show();
            return false;
        }

        if(jk == null || jk.trim().length() == 0 || jk.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterMaxi1Activity.this);
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

        if (alamatpemilik == null || alamatpemilik.trim().length() == 0 || alamatpemilik.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi1Activity.this);
            alertDialog.setMessage("Masukan alamat pemilik perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamatPemilik);
                }
            });
            alertDialog.show();
            return false;
        }

        if (nohp == null || nohp.trim().length() == 0 || nohp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi1Activity.this);
            alertDialog.setMessage("Masukan no. handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoHp);
                }
            });
            alertDialog.show();
            return false;
        }

        if (noktp == null || noktp.trim().length() == 0 || noktp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi1Activity.this);
            alertDialog.setMessage("Masukan no. KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKtp);
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
}
