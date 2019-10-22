package com.dicicilaja.app.OrderIn.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.OrderIn.Network.ApiClient;
import com.dicicilaja.app.OrderIn.Network.ApiService;
import com.dicicilaja.app.OrderIn.Session.SessionOrderIn;
import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

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
    @BindView(R.id.txt_data_calon_pinjaman)
    TextView txtDataCalonPinjaman;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    SessionOrderIn session;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pengajuan);
        ButterKnife.bind(this);

        session = new SessionOrderIn(KonfirmasiPengajuanActivity.this);

        initToolbar();
        initAction();
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
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    private void initShowData() {
        Log.d("DONE", "jenis_pengajuan: " + session.getJenis_pengajuan());
        Log.d("DONE", "program_id: " + session.getProgram_id());
        Log.d("DONE", "product_id: " + session.getProduct_id());
        Log.d("DONE", "qty: " + session.getQty());
        Log.d("DONE", "agen_id: " + session.getAgen_id());
        Log.d("DONE", "amount: " + session.getAmount());
        Log.d("DONE", "ktp_image: " + session.getKtp_image());
        Log.d("DONE", "bpkb: " + session.getBpkb());
        Log.d("DONE", "vehicle_id: " + session.getVehicle_id());
        Log.d("DONE", "voucher_code_id: " + session.getVoucher_code_id());
        Log.d("DONE", "status_data_calon: " + session.getStatus_data_calon());
        Log.d("DONE", "name: " + session.getName());
        Log.d("DONE", "no_ktp: " + session.getNo_ktp());
        Log.d("DONE", "email: " + session.getEmail());
        Log.d("DONE", "no_hp: " + session.getNo_hp());
        Log.d("DONE", "province_id: " + session.getProvince_id());
        Log.d("DONE", "province: " + session.getProvince());
        Log.d("DONE", "city_id: " + session.getCity_id());
        Log.d("DONE", "city: " + session.getCity());
        Log.d("DONE", "district_id: " + session.getDistrict_id());
        Log.d("DONE", "district: " + session.getDistrict());
        Log.d("DONE", "address: " + session.getAddress());
        Log.d("DONE", "postal_code: " + session.getPostal_code());
        Log.d("DONE", "status_informasi_jaminan: " + session.getStatus_informasi_jaminan());
        Log.d("DONE", "jaminan_id: " + session.getJaminan_id());
        Log.d("DONE", "jaminan: " + session.getJaminan());
        Log.d("DONE", "area_id: " + session.getArea_id());
        Log.d("DONE", "area: " + session.getArea());
        Log.d("DONE", "objek_brand_id: " + session.getObjek_brand_id());
        Log.d("DONE", "brand: " + session.getBrand());
        Log.d("DONE", "objek_model_id: " + session.getObjek_model_id());
        Log.d("DONE", "model: " + session.getModel());
        Log.d("DONE", "year: " + session.getYear());
        Log.d("DONE", "tenor_simulasi_id: " + session.getTenor_simulasi_id());
        Log.d("DONE", "tenor_simulasi: " + session.getTenor_simulasi());
        Log.d("DONE", "tipe_asuransi_id: " + session.getTipe_asuransi_id());
        Log.d("DONE", "tipe_asuransi: " + session.getTipe_asuransi());
        Log.d("DONE", "jenis_angsuran_id: " + session.getJenis_angsuran_id());
        Log.d("DONE", "jenis_angsuran: " + session.getJenis_angsuran());
        Log.d("DONE", "branch_id: " + session.getBranch_id());
        Log.d("DONE", "branch: " + session.getBranch());
        Log.d("DONE", "branch_address: " + session.getBranch_address());
    }


    private void initLoadData() {

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
        Intent intent = new Intent(getBaseContext(), PengajuanSuksesActivity.class);
        startActivity(intent);
    }
}
