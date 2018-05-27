package com.dicicilaja.dicicilaja.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateAXI;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahAxi;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateAXI.CreateAXI;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahAxi.UbahAxi;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;

public class RegisterAxi5Activity extends AppCompatActivity {

    Button upload, btnDaftar;
    CheckBox check;
    ImageView gambar;
    TextView title;
    String apiKey;
    SessionManager session;
    String axi_id, nama, email, hp, namaibu, area, cabang;
    String no_ktp, tempat_lahir, tanggal, alamat, rtrw, kelurahan, kecamatan, kota, provinsi, kodepos, jk, status;
    String nama_bank, no_rekening, cabang_bank, an_rekening, kota_bank;
    String npwp, nama_npwp, status_npwp, pkp_status;
    String imageKtp, imageNpwp, imageCover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi5);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(RegisterAxi5Activity.this);
        if(session.isLoggedIn() == TRUE){
            getSupportActionBar().setTitle("Tambah Rekan Bisnis");
        }

        title = findViewById(R.id.title);
        btnDaftar = findViewById(R.id.btnDaftar);
        check = findViewById(R.id.check);

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
        no_rekening     = getIntent().getStringExtra("no_rekening");
        cabang_bank     = getIntent().getStringExtra("cabang_bank");
        an_rekening     = getIntent().getStringExtra("an_rekening");
        kota_bank       = getIntent().getStringExtra("kota_bank");
        npwp            = getIntent().getStringExtra("npwp");
        nama_npwp       = getIntent().getStringExtra("nama_npwp");
        status_npwp     = getIntent().getStringExtra("status_npwp");
        pkp_status      = getIntent().getStringExtra("pkp_status");

        imageKtp = "http://dicicilaja.com/public/assets/images/not-found.jpg";
        imageNpwp = "http://dicicilaja.com/public/assets/images/not-found.jpg";
        imageCover = "http://dicicilaja.com/public/assets/images/not-found.jpg";
        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title.setTypeface(opensans_bold);

        btnDaftar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (check.isChecked()){
                    try {
                        imageKtp = "http://dicicilaja.com/";
                        imageNpwp = "http://dicicilaja.com/";
                        imageCover = "http://dicicilaja.com/";
                    } catch (Exception ex) {

                    }
                    android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi5Activity.this);
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
                            + "kota_bank: " + kota_bank + "\n"
                            + "npwp: " + npwp + "\n"
                            + "nama_npwp: " + nama_npwp + "\n"
                            + "status_npwp: " + status_npwp + "\n"
                            + "pkp_status: " + pkp_status + "\n"
                            + "imageKtp: " + imageKtp + "\n"
                            + "imageNpwp: " + imageNpwp + "\n"
                            + "imageCover: " + imageCover + "\n");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(validateForm(imageKtp, imageNpwp, imageCover)) {
                                buatAkun(apiKey, area, cabang, axi_id, nama, no_ktp, tempat_lahir, tanggal, status, alamat, rtrw, provinsi, kota, kecamatan, kelurahan, kodepos, jk, email, hp, namaibu, nama_bank, no_rekening, cabang_bank, an_rekening, kota_bank, npwp, nama_npwp, status_npwp, pkp_status, imageKtp, imageNpwp, imageCover);
                            }
                        }
                    });
                    alertDialog.show();
                }else {
                    android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi5Activity.this);
                    alertDialog.setMessage("Harap setujui syarat dan ketentuan jika ingin mendaftar");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }

            }
        });
    }

    private void buatAkun(final String apiKey, String area, String cabang, String axi_id, String nama, String no_ktp, String tempat_lahir, String tanggal, String status, String alamat, String rtrw, String provinsi, String kota, String kecamatan, String kelurahan, String kodepos, String jk, String email, String hp, String namaibu, String nama_bank, String no_rekening, String cabang_bank, String an_rekening, String kota_bank, String npwp, String nama_npwp, String status_npwp, String pkp_status, String imageKtp, String imageNpwp, String imageCover) {
        InterfaceCreateAXI apiService =
                NewRetrofitClient.getClient().create(InterfaceCreateAXI.class);

        Call<CreateAXI> call = apiService.create(apiKey, area, cabang, axi_id, nama, no_ktp, tempat_lahir, tanggal, status, alamat, rtrw, provinsi, kota, kecamatan, kelurahan, kodepos, jk, email, hp, namaibu, nama_bank, no_rekening, cabang_bank, an_rekening, kota_bank, npwp, nama_npwp, status_npwp, pkp_status, imageKtp, imageNpwp, imageCover);
        call.enqueue(new Callback<CreateAXI>() {
            @Override
            public void onResponse(Call<CreateAXI> call, Response<CreateAXI> response) {
                Toast.makeText(getBaseContext(),"Pendaftaran berhasil. Kami akan lakukan verifikasi data Anda.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<CreateAXI> call, Throwable t) {

            }
        });
    }

    private boolean validateForm(String imageKtp, String imageNpwp, String imageCover) {
        if (imageKtp == null || imageKtp.trim().length() == 0 || imageKtp.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi5Activity.this);
            alertDialog.setMessage("Unggah foto KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
            return false;
        }
        if (imageNpwp == null || imageNpwp.trim().length() == 0 || imageNpwp.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi5Activity.this);
            alertDialog.setMessage("Unggah foto NPWP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
            return false;
        }
        if (imageCover == null || imageCover.trim().length() == 0 || imageCover.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi5Activity.this);
            alertDialog.setMessage("Unggah foto cover buku tabungan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
            return false;
        }
        return true;
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
