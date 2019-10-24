package com.dicicilaja.app.OrderIn.UI;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.OrderIn.Data.Profile.Profile;
import com.dicicilaja.app.OrderIn.Data.Transaksi.Transaksi;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.OrderIn.Session.SessionOrderIn;
import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiPengajuanActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.icon_help1)
    RelativeLayout iconHelp1;
    @BindView(R.id.total)
    TextView total;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pengajuan);
        ButterKnife.bind(this);

        session = new SessionOrderIn(KonfirmasiPengajuanActivity.this);

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
    }

    private void initAction() {
        //Initialize
        progressBar.setVisibility(View.GONE);

        //Network
        apiService2 = ApiClient2.getClient().create(ApiService2.class);
        apiService3 = ApiClient2.getClient().create(ApiService3.class);
    }

    private void initView() {
        if (session.getJaminan_id().equals("1")) {
            angsuranAsuransi.setVisibility(View.VISIBLE);
        } else {
            angsuranAsuransi.setVisibility(View.GONE);
        }

        if (session.getVoucher_code_id() == "") {
            voucherCard.setVisibility(View.GONE);
        } else {
            voucherCard.setVisibility(View.VISIBLE);
        }

        if (session.getAgen_id() == null) {
            axiCard.setVisibility(View.GONE);
        } else {
            axiCard.setVisibility(View.VISIBLE);
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
        Log.d("ORDERDONE", "postal_code: " + session.getPostal_code());
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
        Log.d("ORDERDONE", "tenor_simulasi_id: " + session.getTenor_simulasi_id());
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
        jumlahPinjaman.setText(session.getAmount());
        jumlahTenor.setText(session.getTenor_simulasi_id());
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
        axiReff.setText(session.getAgen_name() + " (" + session.getAgen_id() + ")");

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
                progressBar.setVisibility(View.VISIBLE);

                Call<Profile> call = apiService3.postProfil(
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
                        Log.d("HERE", "onResponse: " + response.code());
                        if (response.isSuccessful()) {
                            try {
                                String calon_nasabah_id = response.body().getData().getId();

                                Call<Transaksi> call1 = apiService3.postTransaksi(
                                        session.getAgen_id(),
                                        calon_nasabah_id,
                                        session.getArea_id(),
                                        session.getBranch_id(),
                                        session.getProduct_id(),
                                        session.getProgram_id(),
                                        session.getAmount(),
                                        "15",
                                        session.getVehicle_id(),
                                        "1",
                                        session.getVoucher_code_id(),
                                        session.getObjek_brand_id(),
                                        session.getObjek_model_id(),
                                        session.getTipe_asuransi_id(),
                                        session.getJenis_angsuran_id(),
                                        session.getTenor_simulasi(),
                                        session.getKtp_image(),
                                        session.getBpkb()
                                );

                                call1.enqueue(new Callback<Transaksi>() {
                                    @Override
                                    public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(getBaseContext(), PengajuanSuksesActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    }

                                    @Override
                                    public void onFailure(Call<Transaksi> call, Throwable t) {

                                    }
                                });

                            } catch (Exception ex) {
                                progressBar.setVisibility(View.GONE);
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
                            progressBar.setVisibility(View.GONE);
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
                        progressBar.setVisibility(View.GONE);
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
