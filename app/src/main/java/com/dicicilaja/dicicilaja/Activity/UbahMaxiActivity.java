package com.dicicilaja.dicicilaja.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahMaxi;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahMaxi.UbahMaxi;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahMaxiActivity extends AppCompatActivity {

    MaterialEditText inputNamaPerusahaan, inputAlamatPerusahaan, inputNPWPPerusahaan, inputNamaPemilik, inputAlamatEmail, inputTelephone, inputHandphone, inputAlamatPemilik, inputKelurahan, inputKota, inputKTPPemilik, inputNPWPPemilik;
    MaterialSpinner spinnerJenisKelamin;
    String apiKey;
    Button save;

    String jk, namaPerusahaan, alamatPerusahaan, NPWPPerusahaan, namaPemilik, alamatEmail, telephone, handphone, alamatPemilik, kelurahan, kota, KTPPemilik, NPWPPemilik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_maxi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        inputNamaPerusahaan     = findViewById(R.id.inputNamaPerusahaan);
        inputAlamatPerusahaan   = findViewById(R.id.inputAlamatPerusahaan);
        inputNPWPPerusahaan     = findViewById(R.id.inputNPWPPerusahaan);
        inputNamaPemilik        = findViewById(R.id.inputNamaPemilik);
        inputAlamatEmail        = findViewById(R.id.inputAlamatEmail);
        inputTelephone          = findViewById(R.id.inputTelephone);
        inputHandphone          = findViewById(R.id.inputHandphone);
        inputAlamatPemilik      = findViewById(R.id.inputAlamatPemilik);
        inputKelurahan          = findViewById(R.id.inputKelurahan);
        inputKota               = findViewById(R.id.inputKota);
        inputKTPPemilik         = findViewById(R.id.inputKTPPemilik);
        inputNPWPPemilik        = findViewById(R.id.inputNPWPPemilik);
        save                    = findViewById(R.id.save);
        spinnerJenisKelamin     = findViewById(R.id.spinnerJenisKelamin);

        inputNamaPerusahaan.setText(getIntent().getStringExtra("api_name"));
        inputAlamatPerusahaan.setText(getIntent().getStringExtra("api_alamat"));
        inputNPWPPerusahaan.setText(getIntent().getStringExtra("api_npwp"));
        inputNamaPemilik.setText(getIntent().getStringExtra("api_name_user"));
        inputAlamatEmail.setText(getIntent().getStringExtra("api_email_user"));
        inputTelephone.setText(getIntent().getStringExtra("api_telp"));
        inputHandphone.setText(getIntent().getStringExtra("api_handphone"));
        inputAlamatPemilik.setText(getIntent().getStringExtra("api_alamat_pemilik"));
        inputKelurahan.setText(getIntent().getStringExtra("api_kelurahan"));
        inputKota.setText(getIntent().getStringExtra("api_kota"));
        inputKTPPemilik.setText(getIntent().getStringExtra("api_ktp"));
        inputNPWPPemilik.setText(getIntent().getStringExtra("api_npwp_pemilik"));
        spinnerJenisKelamin.setSelection(Integer.parseInt(getIntent().getStringExtra("api_jk")));

        final List<String> JK_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JK_DATA = new HashMap<Integer, String>();

        JK_ITEMS.clear();
        JK_DATA.clear();

        JK_DATA.put(1, "Laki-laki");
        JK_DATA.put(2, "Perempuan");
        JK_ITEMS.add("Laki-laki");
        JK_ITEMS.add("Perempuan");


        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JK_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(tenor_adapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    namaPerusahaan = inputNamaPerusahaan.getText().toString();
                    alamatPerusahaan = inputAlamatPerusahaan.getText().toString();
                    NPWPPerusahaan = inputNPWPPerusahaan.getText().toString();
                    namaPemilik = inputNamaPemilik.getText().toString();
                    alamatEmail = inputAlamatEmail.getText().toString();
                    telephone = inputTelephone.getText().toString();
                    handphone = inputHandphone.getText().toString();
                    alamatPemilik = inputAlamatPemilik.getText().toString();
                    kelurahan = inputKelurahan.getText().toString();
                    kota = inputKota.getText().toString();
                    KTPPemilik = inputKTPPemilik.getText().toString();
                    NPWPPemilik = inputNPWPPemilik.getText().toString();
                    jk = JK_DATA.get(spinnerJenisKelamin.getSelectedItemPosition());
                } catch (Exception ex) {

                }
                if(validateForm(jk, namaPerusahaan, alamatPerusahaan, NPWPPerusahaan, namaPemilik, alamatEmail, telephone, handphone, alamatPemilik, kelurahan, kota, KTPPemilik, NPWPPemilik)) {
                    ubahMaxi(apiKey,jk, namaPerusahaan, alamatPerusahaan, NPWPPerusahaan, namaPemilik, alamatEmail, telephone, handphone, alamatPemilik, kelurahan, kota, KTPPemilik, NPWPPemilik);

                }
            }
        });
    }
    private void ubahMaxi(final String apiKey, final String jk, final String namaPerusahaan, final String alamatPerusahaan, final String NPWPPerusahaan, final String namaPemilik, final String alamatEmail, final String telephone, final String handphone, final String alamatPemilik, final String kelurahan, final String kota, final String KTPPemilik, final String NPWPPemilik) {
        InterfaceUbahMaxi apiService =
                NewRetrofitClient.getClient().create(InterfaceUbahMaxi.class);

        Call<UbahMaxi> call = apiService.change(apiKey,jk, namaPerusahaan, alamatPerusahaan, NPWPPerusahaan, namaPemilik, alamatEmail, telephone, handphone, alamatPemilik, kelurahan, kota, KTPPemilik, NPWPPemilik);
        call.enqueue(new Callback<UbahMaxi>() {
            @Override
            public void onResponse(Call<UbahMaxi> call, Response<UbahMaxi> response) {
                try {
                    Toast.makeText(getBaseContext(),"Data Anda berhasil diubah",Toast.LENGTH_SHORT).show();
                    finish();
                } catch(Exception ex) {
                    Log.w("Process Exception :", ex.getMessage());
                    Toast.makeText(getBaseContext(), "Gagal mengubah data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UbahMaxi> call, Throwable t) {

            }
        });
    }

    private boolean validateForm(String jk, String namaPerusahaan, String alamatPerusahaan, String NPWPPerusahaan, String namaPemilik, String alamatEmail, String telephone, String handphone, String alamatPemilik, String kelurahan, String kota, String KTPPemilik, String NPWPPemilik) {
        if(namaPerusahaan == null || namaPerusahaan.trim().length() == 0 || namaPerusahaan.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan nama perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaPerusahaan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(alamatPerusahaan == null || alamatPerusahaan.trim().length() == 0 || alamatPerusahaan.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan alamat perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamatPerusahaan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(NPWPPerusahaan == null || NPWPPerusahaan.trim().length() == 0 || NPWPPerusahaan.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan NPWP perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWPPerusahaan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(namaPemilik == null || namaPemilik.trim().length() == 0 || namaPemilik.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan nama pemilik perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaPemilik);
                }
            });
            alertDialog.show();
            return false;
        }

        if(alamatEmail == null || alamatEmail.trim().length() == 0 || alamatEmail.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamatEmail);
                }
            });
            alertDialog.show();
            return false;
        }

        if(telephone == null || telephone.trim().length() == 0 || telephone.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan no.telephone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTelephone);
                }
            });
            alertDialog.show();
            return false;
        }

        if(handphone == null || handphone.trim().length() == 0 || handphone.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan no.handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputHandphone);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kelurahan == null || kelurahan.trim().length() == 0 || kelurahan.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan kelurahan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKelurahan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(alamatPemilik == null || alamatPemilik.trim().length() == 0 || alamatPemilik.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan alamat pemilik perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamatPemilik);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kelurahan == null || kelurahan.trim().length() == 0 || kelurahan.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan kelurahan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKelurahan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kota == null || kota.trim().length() == 0 || kota.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan kota");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKota);
                }
            });
            alertDialog.show();
            return false;
        }

        if(jk == null || jk.trim().length() == 0 || jk.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Pilih jenis kelamin");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJenisKelamin);
                }
            });
            alertDialog.show();
            return false;
        }

        if(KTPPemilik == null || KTPPemilik.trim().length() == 0 || KTPPemilik.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan no.ktp pemilik perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKTPPemilik);
                }
            });
            alertDialog.show();
            return false;
        }

        if(NPWPPemilik == null || NPWPPemilik.trim().length() == 0 || NPWPPemilik.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahMaxiActivity.this);
            alertDialog.setMessage("Masukan NPWP pemilik");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWPPemilik);
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
