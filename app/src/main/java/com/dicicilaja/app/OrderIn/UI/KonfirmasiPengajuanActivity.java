package com.dicicilaja.app.OrderIn.UI;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.OrderIn.Data.Profile.Profile;
import com.dicicilaja.app.OrderIn.Data.Transaksi.Transaksi;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.OrderIn.Session.SessionOrderIn;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.dicicilaja.app.Session.SessionManager;

public class KonfirmasiPengajuanActivity extends AppCompatActivity {

    SessionManager sessionAuth;
    String apiKey;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.angsuran)
    TextView angsuran;
    @BindView(R.id.tenor_angsuran)
    TextView tenorAngsuran;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    SessionOrderIn session;

    ApiService2 apiService2;
    ApiService3 apiService3;

    @BindView(R.id.jumlah_pinjaman)
    TextView jumlahPinjaman;
    @BindView(R.id.jumlah_tenor)
    TextView jumlahTenor;
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.no_ktp)
    TextView noKtp;
    @BindView(R.id.alamat)
    TextView alamat;
    @BindView(R.id.jenis_jaminan)
    TextView jenisJaminan;
    @BindView(R.id.merk)
    TextView merk;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.tahun)
    TextView tahun;
    @BindView(R.id.plat)
    TextView plat;
    @BindView(R.id.asuransi)
    TextView asuransi;
    @BindView(R.id.angsuran_asuransi)
    LinearLayout angsuranAsuransi;
    @BindView(R.id.nama_cabang)
    TextView namaCabang;
    @BindView(R.id.alamat_cabang)
    TextView alamatCabang;
    @BindView(R.id.kota_cabang)
    TextView kotaCabang;
    @BindView(R.id.voucher)
    TextView voucher;
    @BindView(R.id.axi_reff)
    TextView axiReff;
    @BindView(R.id.voucher_card)
    RelativeLayout voucherCard;
    @BindView(R.id.axi_card)
    RelativeLayout axiCard;
    @BindView(R.id.gambar)
    ImageView gambar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_mitra)
    TextView tvMitra;
    @BindView(R.id.tv_harga)
    TextView tvHarga;
    @BindView(R.id.tv_package)
    TextView tvPackage;
    @BindView(R.id.product_maxi)
    LinearLayout productMaxi;

    ProgressDialog progress;
    @BindView(R.id.program_cicilan)
    TextView programCicilan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pengajuan);
        ButterKnife.bind(this);

        session = new SessionOrderIn(KonfirmasiPengajuanActivity.this);

        sessionAuth = new SessionManager(getBaseContext());
        apiKey = "Bearer " + sessionAuth.getToken();

        initToolbar();
        initAction();
        initView();
        initShowData();
        initLoadData();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Konfirmasi Pengajuan");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);
    }

    private void initAction() {
        //Initialize
        progressBar.setVisibility(View.GONE);

        //Network
        apiService2 = ApiClient2.getClient().create(ApiService2.class);
        apiService3 = ApiClient2.getClient().create(ApiService3.class);


        if (session.getProgram_id().equals("2")) {
            programCicilan.setText("MAXI Travel");
        } else if (session.getProgram_id().equals("3")) {
            programCicilan.setText("MAXI Edukasi");
        } else if (session.getProgram_id().equals("4")) {
            programCicilan.setText("MAXI Usaha");
        } else if (session.getProgram_id().equals("5")) {
            programCicilan.setText("MAXI Sehat");
        } else if (session.getProgram_id().equals("6")) {
            programCicilan.setText("MAXI Ekstraguna");
        } else {
            programCicilan.setText("Pembiayaan Dana Multiguna");
        }
    }

    private void initView() {

        if (session.getProgram_id().equals("1")) {
            productMaxi.setVisibility(View.GONE);
        } else {
            productMaxi.setVisibility(View.VISIBLE);
            try {
                try {
                    String rp = getResources().getString(R.string.rp_string);
                    String originalString = String.valueOf(session.getAmount());
                    originalString = originalString.replaceAll("\\.", "").replaceFirst(",", ".");
                    originalString = originalString.replaceAll("[A-Z]", "").replaceAll("[a-z]", "");
                    Double doubleval = Double.parseDouble(originalString);
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    symbols.setGroupingSeparator('.');
                    String pattern = "#,###.##";
                    DecimalFormat formatter = new DecimalFormat(pattern, symbols);
                    String formattedString = rp + formatter.format(doubleval);
                    tvHarga.setText(formattedString);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                tvMitra.setText(session.getMitra().toUpperCase());
                tvTitle.setText(session.getTitle());

                Glide.with(KonfirmasiPengajuanActivity.this)
                        .load(session.getGambar())
                        .centerCrop()
                        .into(gambar);

                if (Integer.parseInt(session.getQty()) > 1) {
                    tvPackage.setText(session.getQty() + " Packages");
                } else {
                    tvPackage.setText(session.getQty() + " Package");
                }
            } catch (Exception ex) {
            }

        }


        if (session.getJaminan_id().equals("1")) {
            angsuranAsuransi.setVisibility(View.VISIBLE);
        } else {
            angsuranAsuransi.setVisibility(View.GONE);
        }

        if (session.getVoucher_code_id() != "") {
            voucherCard.setVisibility(View.VISIBLE);
        } else {
            voucherCard.setVisibility(View.GONE);
        }

        if (session.getAgen_id() != "") {
            axiCard.setVisibility(View.VISIBLE);
        } else {
            axiCard.setVisibility(View.GONE);
        }
    }

    private void initShowData() {
        Log.d("ORDERDONE", "jenis_pengajuan: " + session.getJenis_pengajuan());
        Log.d("ORDERDONE", "program_id: " + session.getProgram_id());
        Log.d("ORDERDONE", "product_id: " + session.getProduct_id());
        Log.d("ORDERDONE", "qty: " + session.getQty());
        Log.d("ORDERDONE", "agen_id: " + session.getAgen_id());
        Log.d("ORDERDONE", "agen_name: " + session.getAgen_name());
        Log.d("ORDERDONE", "amount: " + session.getAmount());
        Log.d("ORDERDONE", "ktp_image: " + session.getKtp_image());
        Log.d("ORDERDONE", "bpkb: " + session.getBpkb());
        Log.d("ORDERDONE", "vehicle_id: " + session.getVehicle_id());
        Log.d("ORDERDONE", "voucher_code_id: " + session.getVoucher_code_id());
        Log.d("ORDERDONE", "voucher_code: " + session.getVoucher_code());
        Log.d("ORDERDONE", "status_data_calon: " + session.getStatus_data_calon());
        Log.d("ORDERDONE", "name: " + session.getName());
        Log.d("ORDERDONE", "no_ktp: " + session.getNo_ktp());
        Log.d("ORDERDONE", "email: " + session.getEmail());
        Log.d("ORDERDONE", "no_hp: " + session.getNo_hp());
        Log.d("ORDERDONE", "province_id: " + session.getProvince_id());
        Log.d("ORDERDONE", "province: " + session.getProvince());
        Log.d("ORDERDONE", "city_id: " + session.getCity_id());
        Log.d("ORDERDONE", "city: " + session.getCity());
        Log.d("ORDERDONE", "district_id: " + session.getDistrict_id());
        Log.d("ORDERDONE", "district: " + session.getDistrict());
        Log.d("ORDERDONE", "village_id: " + session.getVillage_id());
        Log.d("ORDERDONE", "village: " + session.getVillage());
        Log.d("ORDERDONE", "address: " + session.getAddress());
        Log.d("ORDERDONE", "status_informasi_jaminan: " + session.getStatus_informasi_jaminan());
        Log.d("ORDERDONE", "jaminan_id: " + session.getJaminan_id());
        Log.d("ORDERDONE", "jaminan: " + session.getJaminan());
        Log.d("ORDERDONE", "area_id: " + session.getArea_id());
        Log.d("ORDERDONE", "area: " + session.getArea());
        Log.d("ORDERDONE", "objek_brand_id: " + session.getObjek_brand_id());
        Log.d("ORDERDONE", "brand: " + session.getBrand());
        Log.d("ORDERDONE", "objek_model_id: " + session.getObjek_model_id());
        Log.d("ORDERDONE", "model: " + session.getModel());
        Log.d("ORDERDONE", "year: " + session.getYear());
        Log.d("ORDERDONE", "tenor_simulasi: " + session.getTenor_simulasi());
        Log.d("ORDERDONE", "tipe_asuransi_id: " + session.getTipe_asuransi_id());
        Log.d("ORDERDONE", "tipe_asuransi: " + session.getTipe_asuransi());
        Log.d("ORDERDONE", "jenis_angsuran_id: " + session.getJenis_angsuran_id());
        Log.d("ORDERDONE", "jenis_angsuran: " + session.getJenis_angsuran());
        Log.d("ORDERDONE", "branch_id: " + session.getBranch_id());
        Log.d("ORDERDONE", "branch: " + session.getBranch());
        Log.d("ORDERDONE", "branch_address: " + session.getBranch_address());
        Log.d("ORDERDONE", "branch_district: " + session.getBranch_district());
    }

    private void initLoadData() {
        try {
            String rp = getResources().getString(R.string.rp_string);
            String originalString = String.valueOf(session.getAmount());
            originalString = originalString.replaceAll("\\.", "").replaceFirst(",", ".");
            originalString = originalString.replaceAll("[A-Z]", "").replaceAll("[a-z]", "");
            Double doubleval = Double.parseDouble(originalString);
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            symbols.setGroupingSeparator('.');
            String pattern = "#,###.##";
            DecimalFormat formatter = new DecimalFormat(pattern, symbols);
            String formattedString = rp + formatter.format(doubleval);
            jumlahPinjaman.setText(formattedString);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        jumlahTenor.setText(session.getTenor_simulasi());
        nama.setText(session.getName());
        noKtp.setText(session.getNo_ktp());
        alamat.setText(session.getAddress());
        jenisJaminan.setText(session.getJaminan());
        merk.setText(session.getBranch());
        type.setText(session.getModel());
        tahun.setText(session.getYear());
        plat.setText(session.getVehicle_id());
        angsuran.setText(session.getJenis_angsuran());
        asuransi.setText(session.getTipe_asuransi());
        namaCabang.setText(session.getBranch());
        alamatCabang.setText(session.getBranch_address());
        kotaCabang.setText(session.getBranch_district());
        voucher.setText(session.getVoucher_code());
        axiReff.setText(session.getAgen_axi_id() + " (" + session.getAgen_name() + ")");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.save)
    public void onViewClicked() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(KonfirmasiPengajuanActivity.this);
        alertDialog.setTitle("Perhatian");
        alertDialog.setMessage("Apakah data yang Anda masukan sudah benar?");

        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                progress.show();
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                Intent intent = new Intent(getBaseContext(), PengajuanSuksesActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);


                Call<Profile> call = apiService3.postProfil(
                        apiKey,
                        session.getName(),
                        session.getEmail(),
                        session.getNo_hp(),
                        session.getNo_ktp(),
                        session.getVillage_id(),
                        session.getAddress()
                );
                call.enqueue(new Callback<Profile>() {

                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.code() == 401) {
                            progress.hide();
                            sessionAuth.logoutUser();
                            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (response.isSuccessful()) {
                            try {
                                String calon_nasabah_id = response.body().getData().getId();

                                Log.d("HASIL", "agen_id: " + session.getAgen_id());
                                Log.d("HASIL", "calon_nasabah_id: " + calon_nasabah_id);
                                Log.d("HASIL", "area_id: " + session.getArea_id());
                                Log.d("HASIL", "branch_id: " + session.getBranch_id());
                                Log.d("HASIL", "produk_id: " + session.getProduct_id());
                                Log.d("HASIL", "program_id: " + session.getProgram_id());
                                Log.d("HASIL", "amount: " + session.getAmount());
                                Log.d("HASIL", "vehicle_id: " + session.getVehicle_id());
                                Log.d("HASIL", "channel_id: " + "1");
                                Log.d("HASIL", "voucher_code_id: " + session.getVoucher_code_id());
                                Log.d("HASIL", "objek_brand_id: " + session.getObjek_brand_id());
                                Log.d("HASIL", "objek_model_id: " + session.getObjek_model_id());
                                Log.d("HASIL", "tipe_asuransi_id: " + session.getTipe_asuransi_id());
                                Log.d("HASIL", "jenis_angsuran_id: " + session.getJenis_angsuran_id());
                                Log.d("HASIL", "jaminan_id: " + session.getJaminan_id());
                                Log.d("HASIL", "tenor_simulasi: " + session.getTenor_simulasi());
                                Log.d("HASIL", "ktp_image: " + session.getKtp_image());
                                Log.d("HASIL", "bpkb: " + session.getBpkb());


                                Call<Transaksi> call1 = apiService3.postTransaksi(
                                        apiKey,
                                        session.getAgen_id(),
                                        calon_nasabah_id,
                                        session.getArea_id(),
                                        session.getBranch_id(),
                                        session.getProduct_id(),
                                        session.getProgram_id(),
                                        String.valueOf(session.getAmount()),
                                        session.getVehicle_id(),
                                        "1",
                                        session.getVoucher_code_id(),
                                        session.getObjek_brand_id(),
                                        session.getObjek_model_id(),
                                        session.getTipe_asuransi_id(),
                                        session.getJenis_angsuran_id(),
                                        session.getTenor_simulasi(),
                                        session.getJaminan_id(),
                                        session.getKtp_image(),
                                        session.getBpkb(),
                                        session.getYear(),
                                        session.getQty(),
                                        session.getTempat_lahir(),
                                        session.getTanggal_lahir(),
                                        session.getNama_ibu_kandung(),
                                        session.getTanggal_janji_survey(),
                                        session.getPunya_npwp_id(),
                                        session.getPekerjaan_id(),
                                        session.getVehicles_id()
                                );

                                call1.enqueue(new Callback<Transaksi>() {
                                    @Override
                                    public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
                                        Log.d("HASIL", "code: " + response.code());
                                        if (response.code() == 401) {
                                            progress.dismiss();
                                            sessionAuth.logoutUser();
                                            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else if (response.isSuccessful()) {

                                            progress.hide();
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                            Intent intent = new Intent(getBaseContext(), PengajuanSuksesActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            progress.hide();
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(KonfirmasiPengajuanActivity.this);
                                            alertDialog.setTitle("Perhatian");
                                            alertDialog.setMessage("Gagal melakukan pengajuan, silahkan coba beberapa saat lagi.");

                                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                    startActivity(getIntent());
                                                }
                                            });
                                            alertDialog.show();
                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<Transaksi> call, Throwable t) {
                                        progress.hide();
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(KonfirmasiPengajuanActivity.this);
                                        alertDialog.setTitle("Perhatian");
                                        alertDialog.setMessage("Gagal melakukan pengajuan, silahkan coba beberapa saat lagi.");

                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                });

                            } catch (Exception ex) {
                                progress.hide();
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(KonfirmasiPengajuanActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Gagal melakukan pengajuan, silahkan coba beberapa saat lagi.");

                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        startActivity(getIntent());
                                    }
                                });
                                alertDialog.show();
                            }


                        } else {
                            progress.hide();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(KonfirmasiPengajuanActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Gagal melakukan pengajuan, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        progress.hide();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(KonfirmasiPengajuanActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Gagal melakukan pengajuan, silahkan coba beberapa saat lagi.");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        alertDialog.show();
                    }
                });
            }
        });
        alertDialog.show();
    }
}
