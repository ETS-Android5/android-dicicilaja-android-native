package com.dicicilaja.app.OrderIn.UI;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BuildConfig;
import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Data.PlatNomor.PlatNomor;
import com.dicicilaja.app.OrderIn.Data.VoucherCode.VoucherCode;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.OrderIn.Session.SessionOrderIn;
import com.dicicilaja.app.OrderIn.UI.BantuanOrderIn.BantuanOrderInActivity;
import com.dicicilaja.app.OrderIn.UI.KantorCabang.Activity.KantorCabangActivity;
import com.dicicilaja.app.OrderIn.Utility.FileCompressor;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.Utils.Helper;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderInProductActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.gambar)
    ImageView gambar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_mitra)
    TextView tvMitra;
    @BindView(R.id.tv_harga)
    TextView tvHarga;
    @BindView(R.id.minus)
    ImageView minus;
    @BindView(R.id.value)
    TextView value;
    @BindView(R.id.plus)
    ImageView plus;
    @BindView(R.id.txt_program_cicilan)
    TextView txtProgramCicilan;
    @BindView(R.id.hide)
    RelativeLayout hide;
    @BindView(R.id.cari_axi)
    TextView cariAxi;
    @BindView(R.id.cari_axi_close)
    TextView cariAxiClose;
    @BindView(R.id.btn_cari_axi)
    RelativeLayout btnCariAxi;
    @BindView(R.id.et_axi_id_reff)
    EditText etAxiIdReff;
    @BindView(R.id.axi_available)
    TextView axiAvailable;
    @BindView(R.id.axi_not_available)
    TextView axiNotAvailable;
    @BindView(R.id.txt_data_calon_pinjaman)
    TextView txtDataCalonPinjaman;
    @BindView(R.id.txt_data_calon_pinjaman_value)
    TextView txtDataCalonPinjamanValue;
    @BindView(R.id.chev)
    ImageView chev;
    @BindView(R.id.detail_data_calon_pinjaman)
    LinearLayout detailDataCalonPinjaman;
    @BindView(R.id.edit_data_calon_pinjaman)
    LinearLayout editDataCalonPinjaman;
    @BindView(R.id.txt_informasi_jaminan)
    TextView txtInformasiJaminan;
    @BindView(R.id.txt_informasi_jaminan_value)
    TextView txtInformasiJaminanValue;
    @BindView(R.id.chev1)
    ImageView chev1;
    @BindView(R.id.detail_informasi_jaminan)
    LinearLayout detailInformasiJaminan;
    @BindView(R.id.edit_informasi_jaminan)
    LinearLayout editInformasiJaminan;
    @BindView(R.id.btn_upload_ktp)
    RelativeLayout btnUploadKtp;
    @BindView(R.id.change_ktp)
    TextView changeKtp;
    @BindView(R.id.image_ktp)
    ImageView imageKtp;
    @BindView(R.id.view_upload_ktp)
    RelativeLayout viewUploadKtp;
    @BindView(R.id.btn_upload_bpkb)
    RelativeLayout btnUploadBpkb;
    @BindView(R.id.change_bpkb)
    TextView changeBpkb;
    @BindView(R.id.image_bpkb)
    ImageView imageBpkb;
    @BindView(R.id.view_upload_bpkb)
    RelativeLayout viewUploadBpkb;
    @BindView(R.id.cari_plat_nomor)
    TextView cariPlatNomor;
    @BindView(R.id.cari_plat_nomor_close)
    TextView cariPlatNomorClose;
    @BindView(R.id.btn_cari_plat_nomor)
    RelativeLayout btnCariPlatNomor;
    @BindView(R.id.et_plat_nomor)
    EditText etPlatNomor;
    @BindView(R.id.plat_nomor_available)
    TextView platNomorAvailable;
    @BindView(R.id.plat_nomor_not_available)
    TextView platNomorNotAvailable;
    @BindView(R.id.plat_nomor_error)
    TextView platNomorError;
    @BindView(R.id.cari_voucher)
    TextView cariVoucher;
    @BindView(R.id.cari_voucher_close)
    TextView cariVoucherClose;
    @BindView(R.id.btn_cari_voucher)
    RelativeLayout btnCariVoucher;
    @BindView(R.id.et_voucher)
    EditText etVoucher;
    @BindView(R.id.voucher_available)
    TextView voucherAvailable;
    @BindView(R.id.voucher_not_available)
    TextView voucherNotAvailable;
    @BindView(R.id.cb_confirm)
    CheckBox cbConfirm;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;

    SessionOrderIn session;
    SessionManager sessionUser;

    private int PICK_IMAGE_KTP = 100;
    private int PICK_IMAGE_BPKB = 200;
    private static final String TAG = OrderInProductActivity.class.getSimpleName();

    String jumlah_pinjaman, plat_nomor, voucher_code, axi_reff, fKtp, fBpkb, program_id;

    boolean data_calon_peminjam, jaminan_pinjaman;

    ApiService3 apiService3;
    ApiService2 apiService2;

    File mPhotoFile;
    Bitmap mPhotoBitmap;
    FileCompressor mCompressor;

    Integer qty, hasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_in_product);
        ButterKnife.bind(this);

        mCompressor = new FileCompressor(this);

        session = new SessionOrderIn(OrderInProductActivity.this);
        sessionUser = new SessionManager(OrderInProductActivity.this);

        session.clearOrderIn();

        initToolbar();
        initAction();
        initShowData();
        initLoadData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSoftKeyboard();
        Log.d("REFRESH", "status_data_calon: " + session.getStatus_data_calon() + "status_informasi_jaminan: " + session.getStatus_informasi_jaminan());
        initView();
        initShowData();
    }

    private void initLoadData() {

        if (getIntent().getStringExtra("program").equals("MAXI Travel")) {
            program_id = "2";
            session.setProgram_id("2");
        } else if (getIntent().getStringExtra("program").equals("MAXI Edukasi")) {
            program_id = "3";
            session.setProgram_id("3");
        } else if (getIntent().getStringExtra("program").equals("MAXI Usaha")) {
            program_id = "4";
            session.setProgram_id("4");
        } else if (getIntent().getStringExtra("program").equals("MAXI Sehat")) {
            program_id = "5";
            session.setProgram_id("5");
        } else if (getIntent().getStringExtra("program").equals("MAXI Ekstraguna")) {
            program_id = "6";
            session.setProgram_id("6");
        }

        session.setHarga(getIntent().getStringExtra("amount"));

        session.setGambar(getIntent().getStringExtra("gambar"));
        session.setTitle(getIntent().getStringExtra("title"));
        session.setMitra(getIntent().getStringExtra("mitra"));

        session.createOrderInSession(
                "1",
                program_id,
                getIntent().getStringExtra("product_id"),
                "1",
                getIntent().getStringExtra("agen_id"),
                getIntent().getStringExtra("agen_name"),
                getIntent().getStringExtra("amount"),
                "",
                "",
                "",
                getIntent().getBooleanExtra("status_data_calon", false),
                getIntent().getStringExtra("name"),
                getIntent().getStringExtra("no_ktp"),
                getIntent().getStringExtra("email"),
                getIntent().getStringExtra("no_hp"),
                getIntent().getStringExtra("province_id"),
                getIntent().getStringExtra("province"),
                getIntent().getStringExtra("city"),
                getIntent().getStringExtra("city"),
                getIntent().getStringExtra("district_id"),
                getIntent().getStringExtra("district"),
                getIntent().getStringExtra("village_id"),
                getIntent().getStringExtra("village"),
                getIntent().getStringExtra("address"),
                getIntent().getStringExtra("postal_code"),
                getIntent().getBooleanExtra("status_informasi_jaminan", false),
                getIntent().getStringExtra("jaminan_id"),
                getIntent().getStringExtra("jaminan"),
                getIntent().getStringExtra("area_id"),
                getIntent().getStringExtra("area"),
                getIntent().getStringExtra("objek_brand_id"),
                getIntent().getStringExtra("brand"),
                getIntent().getStringExtra("objek_model_id"),
                getIntent().getStringExtra("model"),
                getIntent().getStringExtra("year"),
                getIntent().getStringExtra("tenor"),
                getIntent().getStringExtra("tipe_asuransi_id"),
                getIntent().getStringExtra("tipe_asuransi"),
                getIntent().getStringExtra("jenis_angsuran_id"),
                getIntent().getStringExtra("jenis_angsuran"),
                "",
                "",
                "",
                ""
        );
    }

    private void initView() {

        if (sessionUser.getRole().equals("axi")) {
            etAxiIdReff.setText(session.getAgen_id());
            etAxiIdReff.setEnabled(false);
            etAxiIdReff.setTextColor(getResources().getColor(R.color.colorBackground));
            cariAxiClose.setVisibility(View.GONE);
            cariAxi.setVisibility(View.GONE);
            axiAvailable.setVisibility(View.VISIBLE);
            axiAvailable.setText(session.getAgen_name());
        }

        if (session.getQty() == null) {
            qty = 1;
            value.setText(String.valueOf(qty));
        } else {
            qty = Integer.parseInt(session.getQty());
            value.setText(String.valueOf(qty));
        }

        if (session.getAmount() == null) {
            hasil = Integer.parseInt(getIntent().getStringExtra("amount"));
            try {
                String rp = getResources().getString(R.string.rp_string);
                String originalString = String.valueOf(hasil);
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
        } else {
            String cleanString = session.getAmount().replaceAll("[A-Z]", "").replaceAll("[a-z]", "").replaceAll("[$,.]", "");

            hasil = Integer.parseInt(cleanString);
            try {
                String rp = getResources().getString(R.string.rp_string);
                String originalString = String.valueOf(hasil);
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
        }

        tvMitra.setText(getIntent().getStringExtra("mitra").toUpperCase());
        tvTitle.setText(getIntent().getStringExtra("title"));

        txtProgramCicilan.setText(getIntent().getStringExtra("program"));

        Glide.with(OrderInProductActivity.this)
                .load(getIntent().getStringExtra("gambar"))
                .centerCrop()
                .into(gambar);

        if (session.getStatus_data_calon()) {
            txtDataCalonPinjamanValue.setVisibility(View.VISIBLE);
            txtDataCalonPinjaman.setVisibility(View.GONE);

            editDataCalonPinjaman.setVisibility(View.VISIBLE);
            detailDataCalonPinjaman.setVisibility(View.GONE);
            txtDataCalonPinjamanValue.setVisibility(View.VISIBLE);
            txtDataCalonPinjaman.setVisibility(View.GONE);
            detailDataCalonPinjaman.setVisibility(View.GONE);
            editDataCalonPinjaman.setVisibility(View.VISIBLE);
            String value = session.getName() + ", " + session.getNo_ktp() + ", " + session.getEmail() + ", " + session.getNo_hp() + ", " + session.getProvince() + ", " + session.getCity() + ", " + session.getDistrict() + ", " + session.getAddress() + ", " + session.getPostal_code();
            if (value.length() >= 35) {
                value = value.substring(0, 36) + "...";
                txtDataCalonPinjamanValue.setText(value);
            } else {
                txtDataCalonPinjamanValue.setText(value);
            }
        } else {
            txtDataCalonPinjaman.setVisibility(View.VISIBLE);
            txtDataCalonPinjamanValue.setVisibility(View.GONE);

            detailDataCalonPinjaman.setVisibility(View.VISIBLE);
            editDataCalonPinjaman.setVisibility(View.GONE);
        }

        if (session.getStatus_informasi_jaminan()) {
            txtInformasiJaminanValue.setVisibility(View.VISIBLE);
            txtInformasiJaminan.setVisibility(View.GONE);

            editInformasiJaminan.setVisibility(View.VISIBLE);
            detailInformasiJaminan.setVisibility(View.GONE);

            txtInformasiJaminanValue.setVisibility(View.VISIBLE);
            txtInformasiJaminan.setVisibility(View.GONE);
            detailInformasiJaminan.setVisibility(View.GONE);
            editInformasiJaminan.setVisibility(View.VISIBLE);
            String value = session.getJaminan() + ", " + session.getArea() + ", " + session.getBrand() + ", " + session.getModel() + ", " + session.getJenis_angsuran() + ", " + session.getTipe_asuransi();
            if (value.length() >= 35) {
                value = value.substring(0, 36) + "...";
                txtInformasiJaminanValue.setText(value);
            } else {
                txtInformasiJaminanValue.setText(value);
            }
        } else {
            txtInformasiJaminan.setVisibility(View.VISIBLE);
            txtInformasiJaminanValue.setVisibility(View.GONE);

            detailInformasiJaminan.setVisibility(View.VISIBLE);
            editInformasiJaminan.setVisibility(View.GONE);
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buat Pengajuan");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initAction() {
        progressBar.setVisibility(View.GONE);

        fKtp = "0";
        fBpkb = "0";

        apiService3 = ApiClient2.getClient().create(ApiService3.class);
        apiService2 = ApiClient2.getClient().create(ApiService2.class);
        clearVoucher();
        clearPlatNomor();
        clearAxi();
    }

    private void clearVoucher() {
        voucherAvailable.setVisibility(View.GONE);
        voucherNotAvailable.setVisibility(View.GONE);
    }

    private void clearPlatNomor() {
        platNomorAvailable.setVisibility(View.GONE);
        platNomorNotAvailable.setVisibility(View.GONE);
        platNomorError.setVisibility(View.GONE);
    }

    private void clearAxi() {
        axiAvailable.setVisibility(View.GONE);
        axiNotAvailable.setVisibility(View.GONE);
    }

    private void closeAxi() {
        session.setAgen_id("");
        session.setAgen_name("");
        etAxiIdReff.setEnabled(true);
        etAxiIdReff.setTextColor(getResources().getColor(R.color.colorBlack));
        etAxiIdReff.setText("");
        etAxiIdReff.setFocusable(true);
        clearAxi();
        cariAxi.setVisibility(View.VISIBLE);
        cariAxiClose.setVisibility(View.GONE);
    }

    private void closePlatNomor() {
        etPlatNomor.setEnabled(true);
        etPlatNomor.setText("");
        etPlatNomor.setFocusable(true);
        clearPlatNomor();
        cariPlatNomor.setVisibility(View.VISIBLE);
        cariPlatNomorClose.setVisibility(View.GONE);
    }

    private void closeVoucher() {
        session.setVoucher_code_id(null);
        session.setVoucher_code(null);
        etVoucher.setEnabled(true);
        etVoucher.setText("");
        etVoucher.setFocusable(true);
        clearVoucher();
        cariVoucher.setVisibility(View.VISIBLE);
        cariVoucherClose.setVisibility(View.GONE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simulasi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.help:
                Intent intent = new Intent(getBaseContext(), BantuanOrderInActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private boolean validateSecond(String axi_reff, String plat_nomor, String voucher_code) {

        return true;
    }

    private boolean validateForm(boolean data_calon_peminjam, boolean jaminan_pinjaman, String axi_reff, String plat_nomor, String voucher_code) {

        if (!jaminan_pinjaman) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan data jaminan & pinjaman");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    detailInformasiJaminan.setFocusable(true);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (!data_calon_peminjam) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan data calon peminjam");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    detailDataCalonPinjaman.setFocusable(true);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if ( (axi_reff.trim().length() != 0) && (session.getAgen_id() == null || session.getAgen_id().trim().length() == 0 || session.getAgen_id().equals("") || session.getAgen_id().equals(" ")) ) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan tekan cari AXI Refferal");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    etAxiIdReff.setFocusable(true);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (plat_nomor == null || plat_nomor.trim().length() == 0 || plat_nomor.equals("0") || plat_nomor.equals("") || plat_nomor.equals(" ")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan plat nomor kendaraan jaminan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(etPlatNomor);
                }
            });
            alertDialog.show();
            return false;
        } else if ( (plat_nomor.trim().length() != 0) && (session.getVehicle_id() == null || session.getVehicle_id().trim().length() == 0 || session.getVehicle_id().equals("") || session.getVehicle_id().equals(" ")) ) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan tekan cari Plat Nomor");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    etPlatNomor.setFocusable(true);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if ( (voucher_code.trim().length() != 0) && (session.getVoucher_code_id() == null || session.getVoucher_code_id().trim().length() == 0 || session.getVoucher_code_id().equals("") || session.getVoucher_code_id().equals(" ")) ) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan tekan cari Kode Voucher");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    etVoucher.setFocusable(true);
                    hideSoftKeyboard();
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

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    @OnClick({R.id.plus, R.id.minus, R.id.edit_informasi_jaminan, R.id.edit_data_calon_pinjaman, R.id.cari_axi, R.id.cari_axi_close, R.id.cari_plat_nomor_close, R.id.cari_voucher_close, R.id.cari_plat_nomor, R.id.cari_voucher, R.id.detail_data_calon_pinjaman, R.id.detail_informasi_jaminan, R.id.btn_upload_ktp, R.id.btn_upload_bpkb, R.id.next, R.id.change_ktp, R.id.change_bpkb})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.plus:
                if (qty <= 99) {
                    if (qty != 99) {
                        qty = qty + 1;
                    }
                }
                value.setText(String.valueOf(qty));
                hasil = qty * Integer.valueOf(session.getHarga());

                session.setAmount(hasil.toString());
                session.setQty(qty.toString());

                try {
                    String rp = getResources().getString(R.string.rp_string);
                    String originalString = hasil.toString();
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
                break;
            case R.id.minus:
                if (qty > 1 && qty <= 99) {
                    qty = qty - 1;
                }
                value.setText(String.valueOf(qty));
                hasil = qty * Integer.valueOf(session.getHarga());

                session.setAmount(hasil.toString());
                session.setQty(qty.toString());

                try {
                    String rp = getResources().getString(R.string.rp_string);
                    String originalString = hasil.toString();
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
                break;
            case R.id.edit_data_calon_pinjaman:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data calon peminjam yang anda isi akan hilang, apakah anda setuju?");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getBaseContext(), DataCalonPeminjamActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
                break;
            case R.id.edit_informasi_jaminan:
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(OrderInProductActivity.this);
                alertDialog2.setTitle("Perhatian");
                alertDialog2.setMessage("Data jaminan & pinjaman yang anda isi akan hilang, apakah anda setuju?");

                alertDialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getBaseContext(), InformasiJaminan2Activity.class);
                        startActivity(intent);
                    }
                });
                alertDialog2.show();
                break;
            case R.id.detail_data_calon_pinjaman:
                intent = new Intent(getBaseContext(), DataCalonPeminjamActivity.class);
                startActivity(intent);
                break;
            case R.id.detail_informasi_jaminan:
                intent = new Intent(getBaseContext(), InformasiJaminan2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_upload_ktp:
                requestStoragePermission(true, PICK_IMAGE_KTP);
                break;
            case R.id.btn_upload_bpkb:
                requestStoragePermission(true, PICK_IMAGE_BPKB);
                break;
            case R.id.change_ktp:
                requestStoragePermission(true, PICK_IMAGE_KTP);
                break;
            case R.id.change_bpkb:
                requestStoragePermission(true, PICK_IMAGE_BPKB);
                break;
            case R.id.next:
                try {
                    session.setAmount(tvHarga.getText().toString());
                    jumlah_pinjaman = tvHarga.getText().toString();
                    data_calon_peminjam = session.getStatus_data_calon();
                    jaminan_pinjaman = session.getStatus_informasi_jaminan();

                    axi_reff = etAxiIdReff.getText().toString();
                    plat_nomor = etPlatNomor.getText().toString();
                    voucher_code = etVoucher.getText().toString();
                } catch (Exception ex) {
                }
                if (validateForm(data_calon_peminjam, jaminan_pinjaman, axi_reff, plat_nomor, voucher_code)) {
                    if (cbConfirm.isChecked()) {
                        session.setQty(qty.toString());
                        session.setAmount(hasil.toString());
                        intent = new Intent(getBaseContext(), KantorCabangActivity.class);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(OrderInProductActivity.this);
                        alertDialog3.setTitle("Perhatian");
                        alertDialog3.setMessage("Anda belum menyetujui syarat dan ketentuan yang berlaku. Silakan centang pada kotak yang tersedia.");

                        alertDialog3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                cbConfirm.setFocusable(true);
                            }
                        });
                        alertDialog3.show();

                    }
                }
                break;
            case R.id.cari_axi:
                progressBar.setVisibility(View.VISIBLE);
                etAxiIdReff.setEnabled(false);
                etAxiIdReff.setTextColor(getResources().getColor(R.color.colorBackground));
                cariAxiClose.setVisibility(View.VISIBLE);
                cariAxi.setVisibility(View.GONE);

                Call<Axi> axiReff = apiService3.getAxi(etAxiIdReff.getText().toString());
                axiReff.enqueue(new Callback<Axi>() {
                    @Override
                    public void onResponse(Call<Axi> call, Response<Axi> response) {
                        if (response.isSuccessful()) {
                            try {
                                if (response.body().getData().size() > 0) {
                                    clearAxi();
                                    progressBar.setVisibility(View.GONE);
                                    axiAvailable.setVisibility(View.VISIBLE);
                                    axiAvailable.setText(response.body().getData().get(0).getAttributes().getNama());
                                    session.setAgen_id(response.body().getData().get(0).getAttributes().getNomorAxiId());
                                    session.setAgen_name(response.body().getData().get(0).getAttributes().getNama());
                                } else {
                                    clearAxi();
                                    progressBar.setVisibility(View.GONE);
                                    axiNotAvailable.setVisibility(View.VISIBLE);
                                    session.setAgen_id(null);
                                    session.setAgen_name(null);

                                }
                            } catch (Exception ex) {
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                    public void onFailure(Call<Axi> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Data axi gagal dipanggil, silahkan coba beberapa saat lagi.");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        alertDialog.show();
                    }
                });
                break;
            case R.id.cari_axi_close:
                closeAxi();
                break;
            case R.id.cari_plat_nomor:
                if (!isVehicleIdValid(etPlatNomor.getText().toString())){
                    AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(OrderInProductActivity.this);
                    alertDialog5.setTitle("Perhatian");
                    alertDialog5.setMessage("Masukan plat nomor dengan benar");

                    alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            requestFocus(etPlatNomor);
                        }
                    });
                    alertDialog5.show();
                } else {
                    session.setVehicle_id("");
                    progressBar.setVisibility(View.VISIBLE);
                    etPlatNomor.setEnabled(false);
                    cariPlatNomorClose.setVisibility(View.VISIBLE);
                    cariPlatNomor.setVisibility(View.GONE);

                    Call<PlatNomor> platNomor = apiService3.getPlatNomor(etPlatNomor.getText().toString());
                    platNomor.enqueue(new Callback<PlatNomor>() {
                        @Override
                        public void onResponse(Call<PlatNomor> call, Response<PlatNomor> response) {

                            if (response.code() == 400) {
                                clearPlatNomor();
                                progressBar.setVisibility(View.GONE);
                                platNomorError.setVisibility(View.VISIBLE);
                            } else if (response.isSuccessful()) {
                                try {
                                    if (response.body().getData().size() > 0) {
                                        clearPlatNomor();
                                        progressBar.setVisibility(View.GONE);
                                        platNomorNotAvailable.setVisibility(View.VISIBLE);
                                        session.setVehicle_id(null);
                                    } else {
                                        clearPlatNomor();
                                        progressBar.setVisibility(View.GONE);
                                        platNomorAvailable.setVisibility(View.VISIBLE);
                                        session.setVehicle_id(etPlatNomor.getText().toString());
                                    }
                                } catch (Exception ex) {
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Data plat nomor gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                        public void onFailure(Call<PlatNomor> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data plat nomor gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                break;
            case R.id.cari_plat_nomor_close:
                closePlatNomor();
                break;
            case R.id.cari_voucher:
                progressBar.setVisibility(View.VISIBLE);
                etVoucher.setEnabled(false);
                cariVoucherClose.setVisibility(View.VISIBLE);
                cariVoucher.setVisibility(View.GONE);

                Call<VoucherCode> voucherCode = apiService3.getVoucherCode(etVoucher.getText().toString());
                voucherCode.enqueue(new Callback<VoucherCode>() {
                    @Override
                    public void onResponse(Call<VoucherCode> call, Response<VoucherCode> response) {
                        if (response.isSuccessful()) {
                            try {
                                if (response.body().getData().size() > 0) {
                                    clearVoucher();
                                    progressBar.setVisibility(View.GONE);
                                    voucherAvailable.setVisibility(View.VISIBLE);
                                    voucherAvailable.setText(response.body().getData().get(0).getAttributes().getDeskripsi());
                                    session.setVoucher_code_id(String.valueOf(response.body().getData().get(0).getId()));
                                    session.setVoucher_code(String.valueOf(response.body().getData().get(0).getAttributes().getCode()));
                                } else {
                                    clearVoucher();
                                    progressBar.setVisibility(View.GONE);
                                    voucherNotAvailable.setVisibility(View.VISIBLE);
                                    session.setVoucher_code_id(null);
                                    session.setVoucher_code(null);
                                }

                            } catch (Exception ex) {
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data voucher gagal dipanggil, silahkan coba beberapa saat lagi.");

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
                    public void onFailure(Call<VoucherCode> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInProductActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Data voucher gagal dipanggil, silahkan coba beberapa saat lagi.");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        alertDialog.show();
                    }
                });
                break;
            case R.id.cari_voucher_close:
                closeVoucher();
                break;
        }
    }

    private void requestStoragePermission(boolean isCamera, int requestCode) {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            if (isCamera) {
                                dispatchTakePictureIntent(requestCode);
                            }
                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                   PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(
                        error -> Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT)
                                .show())
                .onSameThread()
                .check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_KTP) {
                try {
                    mPhotoBitmap = mCompressor.compressToBitmap(mPhotoFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Bitmap resizedBitmap = getResizedBitmap(mPhotoBitmap, 750);

                btnUploadKtp.setVisibility(View.GONE);
                viewUploadKtp.setVisibility(View.VISIBLE);
                Glide.with(OrderInProductActivity.this)
                        .load(mPhotoFile)
                        .centerCrop()
                        .into(imageKtp);

                session.setKtp_image("data:image/jpeg;base64," + Helper.ConvertBitmapToString(resizedBitmap));
            } else if (requestCode == PICK_IMAGE_BPKB) {
                try {
                    mPhotoBitmap = mCompressor.compressToBitmap(mPhotoFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Bitmap resizedBitmap = getResizedBitmap(mPhotoBitmap, 750);

                btnUploadBpkb.setVisibility(View.GONE);
                viewUploadBpkb.setVisibility(View.VISIBLE);
                Glide.with(OrderInProductActivity.this)
                        .load(mPhotoFile)
                        .centerCrop()
                        .into(imageBpkb);

                session.setBpkb("data:image/jpeg;base64," + Helper.ConvertBitmapToString(resizedBitmap));
            }
        }
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Need Permissions");
        builder.setMessage(
                "This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        return mFile;
    }

    private void dispatchTakePictureIntent(int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);

                mPhotoFile = photoFile;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, requestCode);
            }
        }
    }

    public static boolean isVehicleIdValid(String vehicle_id) {
        String expression = "^[A-Z]{1,2}[0-9]{1,4}[A-Z]{0,3}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(vehicle_id);
        return matcher.matches();
    }
}
