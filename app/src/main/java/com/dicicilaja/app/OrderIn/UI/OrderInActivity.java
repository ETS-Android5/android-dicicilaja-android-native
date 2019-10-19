package com.dicicilaja.app.OrderIn.UI;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.OrderIn.Data.Axi.Axi;
import com.dicicilaja.app.OrderIn.Data.PlatNomor.PlatNomor;
import com.dicicilaja.app.OrderIn.Data.VoucherCode.VoucherCode;
import com.dicicilaja.app.OrderIn.Network.ApiClient2;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.dicicilaja.app.OrderIn.Network.ApiService3;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.Utils.Helper;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderInActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.txt_program_cicilan)
    TextView txtProgramCicilan;
    @BindView(R.id.hide)
    RelativeLayout hide;
    @BindView(R.id.et_axi_id_reff)
    EditText etAxiIdReff;
    @BindView(R.id.et_jumlah_pinjaman)
    EditText etJumlahPinjaman;
    @BindView(R.id.txt_data_calon_pinjaman)
    TextView txtDataCalonPinjaman;
    @BindView(R.id.chev)
    ImageView chev;
    @BindView(R.id.txt_informasi_jaminan)
    TextView txtInformasiJaminan;
    @BindView(R.id.chev1)
    ImageView chev1;
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
    @BindView(R.id.et_voucher)
    EditText etVoucher;
    @BindView(R.id.cb_confirm)
    CheckBox cbConfirm;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Intent intent;
    @BindView(R.id.progressBar)
    MaterialProgressBar progressBar;
    @BindView(R.id.cari_axi)
    TextView cariAxi;
    @BindView(R.id.axi_available)
    TextView axiAvailable;
    @BindView(R.id.axi_not_available)
    TextView axiNotAvailable;
    @BindView(R.id.cari_plat_nomor)
    TextView cariPlatNomor;
    @BindView(R.id.et_plat_nomor)
    EditText etPlatNomor;
    @BindView(R.id.plat_nomor_available)
    TextView platNomorAvailable;
    @BindView(R.id.plat_nomor_not_available)
    TextView platNomorNotAvailable;
    @BindView(R.id.cari_voucher)
    TextView cariVoucher;
    @BindView(R.id.voucher_available)
    TextView voucherAvailable;
    @BindView(R.id.voucher_not_available)
    TextView voucherNotAvailable;
    @BindView(R.id.cari_axi_close)
    ImageView cariAxiClose;
    @BindView(R.id.btn_cari_axi)
    RelativeLayout btnCariAxi;
    @BindView(R.id.btn_cari_plat_nomor)
    RelativeLayout btnCariPlatNomor;
    @BindView(R.id.btn_cari_voucher)
    RelativeLayout btnCariVoucher;
    @BindView(R.id.cari_plat_nomor_close)
    ImageView cariPlatNomorClose;
    @BindView(R.id.cari_voucher_close)
    ImageView cariVoucherClose;
    @BindView(R.id.txt_data_calon_pinjaman_value)
    TextView txtDataCalonPinjamanValue;
    @BindView(R.id.detail_data_calon_pinjaman)
    LinearLayout detailDataCalonPinjaman;
    @BindView(R.id.edit_data_calon_pinjaman)
    LinearLayout editDataCalonPinjaman;
    @BindView(R.id.txt_informasi_jaminan_value)
    TextView txtInformasiJaminanValue;
    @BindView(R.id.detail_informasi_jaminan)
    LinearLayout detailInformasiJaminan;
    @BindView(R.id.edit_informasi_jaminan)
    LinearLayout editInformasiJaminan;

    private Bitmap bitmap;
    private int PICK_IMAGE_KTP = 100;
    private int PICK_IMAGE_BPKB = 200;
    private static final String TAG = OrderInActivity.class.getSimpleName();
    private static final int READ_REQUEST_CODE = 400;
    private Uri uri;
    MultipartBody.Part file_ktp, file_bpkb;
    RequestBody ktp_desc, bpkb_desc;


    String fKtp, fBpkb, axiId, district_id, district, province_id, province, city_id, city;
    String jaminan, area, brand, model, angsuran, tipe_asuransi;
    SessionManager session;

    ApiService3 apiService3;
    ApiService2 apiService2;

    String jenis_pengajuan, product_id, agen_id, qty, area_id, photo_bpkp, photo_ktp, tipe_asuransi_id, jenis_angsuran, status_data_calon, status_informasi_jaminan, name, email, no_ktp, no_hp, year, jaminan_id, address, postal_code, program_id, amount, vehicle_id, voucher_code_id, objek_brand_id, objek_model_id, tenor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_in);
        ButterKnife.bind(this);

        initToolbar();
        initAction();
        initLoadData();
        initView();

        etJumlahPinjaman.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etJumlahPinjaman.removeTextChangedListener(this);
                try {
                    String rp = getResources().getString(R.string.rp_string);
                    String originalString = editable.toString();
                    originalString = originalString.replaceAll("\\.", "").replaceFirst(",", ".");
                    originalString = originalString.replaceAll("[A-Z]", "").replaceAll("[a-z]", "");
                    Double doubleval = Double.parseDouble(originalString);
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setDecimalSeparator(',');
                    symbols.setGroupingSeparator('.');
                    String pattern = "#,###.##";
                    DecimalFormat formatter = new DecimalFormat(pattern, symbols);
                    String formattedString = rp + formatter.format(doubleval);
                    etJumlahPinjaman.setText(formattedString);
                    etJumlahPinjaman.setSelection(etJumlahPinjaman.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                etJumlahPinjaman.addTextChangedListener(this);
            }
        });
    }

    private void initLoadData() {
        //===============
        jenis_pengajuan = "1";

        if (jenis_pengajuan.equals("1")) {
            //Data Multi Guna
        } else {
            //Produk
        }

        program_id = "1";
        agen_id = "111111111";
        product_id = "1";
        qty = "1";
        amount = "15000000";



        //===============
        status_data_calon = "0";
        if (getIntent().getStringExtra("status_data_calon") != null) {
            try {
                status_data_calon = getIntent().getStringExtra("status_data_calon");
            } catch (Exception ex) {}
        }

        if (status_data_calon.equals("1")) {
            name = getIntent().getStringExtra("name");
            no_ktp = getIntent().getStringExtra("no_ktp");
            email = getIntent().getStringExtra("email");
            no_hp = getIntent().getStringExtra("no_hp");
            province_id = getIntent().getStringExtra("province_id");
            province = getIntent().getStringExtra("province");
            city_id = getIntent().getStringExtra("city_id");
            city = getIntent().getStringExtra("city");
            district_id = getIntent().getStringExtra("district_id");
            district = getIntent().getStringExtra("district");
            address = getIntent().getStringExtra("address");
            postal_code = getIntent().getStringExtra("postal_code");
        }

        Log.d("TAGTAG2", "name: " + name);
        Log.d("TAGTAG2", "no_ktp: " + no_ktp);
        Log.d("TAGTAG2", "email: " + email);
        Log.d("TAGTAG2", "no_hp: " + no_hp);
        Log.d("TAGTAG2", "province_id: " + province_id);
        Log.d("TAGTAG2", "province: " + province);
        Log.d("TAGTAG2", "city_id: " + city_id);
        Log.d("TAGTAG2", "city: " + city);
        Log.d("TAGTAG2", "district_id: " + district_id);
        Log.d("TAGTAG2", "district: " + district);
        Log.d("TAGTAG2", "address: " + address);
        Log.d("TAGTAG2", "postal_code: " + postal_code);

        //===============
        status_informasi_jaminan = "2";
        jaminan_id = "1";
        jaminan = "Mobil";
        area_id = "1";
        area = "Jawa Barat";
        objek_brand_id = "1";
        brand = "Honda";
        objek_model_id = "1";
        model = "CRV";
        year = "2019";
        tenor = "12";
        jenis_angsuran = "addb";
        angsuran = "Angsuran Per Bulan (ADDB)";
        tipe_asuransi_id = "1";
        tipe_asuransi = "Total Lost Only";



        //===============
        photo_ktp = null;
        photo_bpkp = null;
        vehicle_id = null;
        voucher_code_id = null;
    }

    private void initView() {
        if (!agen_id.equals(null) || !agen_id.equals("") || !agen_id.equals(" ")) {
            etAxiIdReff.setText(agen_id);
        }

        if (!amount.equals(null) || !amount.equals("") || !amount.equals(" ")) {
            try {
                String rp = getResources().getString(R.string.rp_string);
                String originalString = amount;
                originalString = originalString.replaceAll("\\.", "").replaceFirst(",", ".");
                originalString = originalString.replaceAll("[A-Z]", "").replaceAll("[a-z]", "");
                Double doubleval = Double.parseDouble(originalString);
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                symbols.setGroupingSeparator('.');
                String pattern = "#,###.##";
                DecimalFormat formatter = new DecimalFormat(pattern, symbols);
                String formattedString = rp + formatter.format(doubleval);
                etJumlahPinjaman.setText(formattedString);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        } else {
            etJumlahPinjaman.setText("");
        }

        if (status_data_calon.equals("1")) {
            txtDataCalonPinjamanValue.setVisibility(View.VISIBLE);
            txtDataCalonPinjaman.setVisibility(View.GONE);

            editDataCalonPinjaman.setVisibility(View.VISIBLE);
            detailDataCalonPinjaman.setVisibility(View.GONE);

            //Data Calon Peminjam
            txtDataCalonPinjamanValue.setVisibility(View.VISIBLE);
            txtDataCalonPinjaman.setVisibility(View.GONE);
            detailDataCalonPinjaman.setVisibility(View.GONE);
            editDataCalonPinjaman.setVisibility(View.VISIBLE);
            String value = name + ", " + no_ktp + ", " + email + ", " + no_hp + ", " + province + ", " + city + ", " + district + ", " + address + ", " + postal_code;
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

        if (status_informasi_jaminan.equals("1")) {
            txtInformasiJaminanValue.setVisibility(View.VISIBLE);
            txtInformasiJaminan.setVisibility(View.GONE);

            editInformasiJaminan.setVisibility(View.VISIBLE);
            detailInformasiJaminan.setVisibility(View.GONE);

            //Data Calon Peminjam
            txtInformasiJaminanValue.setVisibility(View.VISIBLE);
            txtInformasiJaminan.setVisibility(View.GONE);
            detailInformasiJaminan.setVisibility(View.GONE);
            editInformasiJaminan.setVisibility(View.VISIBLE);
            String value = jaminan + ", " + area + ", " + brand + ", " + model + ", " + angsuran + ", " + tipe_asuransi;
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
        session = new SessionManager(getBaseContext());
        axiId = session.getAxiId();

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
    }

    private void clearAxi() {
        axiAvailable.setVisibility(View.GONE);
        axiNotAvailable.setVisibility(View.GONE);
    }

    private void closeAxi() {
        etAxiIdReff.setEnabled(true);
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
        etVoucher.setEnabled(true);
        etVoucher.setText("");
        etVoucher.setFocusable(true);
        clearVoucher();
        cariVoucher.setVisibility(View.VISIBLE);
        cariVoucherClose.setVisibility(View.GONE);
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
//                Intent intent = new Intent(getBaseContext(), BantuanNewSimulationActivity.class);
//                startActivity(intent);
                return true;
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode " + requestCode);
        Log.d(TAG, "onActivityResult: resultCode == RESULT_OK ? " + (resultCode == RESULT_OK));
        if (requestCode == PICK_IMAGE_KTP && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 750);

                    imageKtp.setImageBitmap(resizedBitmap);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fKtp = Helper.ConvertBitmapToString(resizedBitmap);

                    btnUploadKtp.setVisibility(View.GONE);
                    viewUploadKtp.setVisibility(View.VISIBLE);
                    String filePath = getRealPathFromURIPath(uri, this);
                    File file = new File(filePath);

//                    Log.d("REGISTER AXI:::", fileKtp);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
//                file_ktp = MultipartBody.Part.createFormData("file", file.getName(), mFile);
//                Log.d(TAG, "file_ktp " + file_ktp);
//                ktp_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        } else if (requestCode == PICK_IMAGE_BPKB && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 750);

                    imageBpkb.setImageBitmap(getResizedBitmap(bitmap, 350));
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fBpkb = Helper.ConvertBitmapToString(resizedBitmap);

                    btnUploadBpkb.setVisibility(View.GONE);
                    viewUploadBpkb.setVisibility(View.VISIBLE);
                    String filePath = getRealPathFromURIPath(uri, this);
                    File file = new File(filePath);

//                    Log.d("REGISTER AXI:::", fileNpwp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                String filePath = getRealPathFromURIPath(uri, this);
//                File file = new File(filePath);
//                Log.d(TAG, "Filename " + file.getName());
////                RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
//                file_colleteral = MultipartBody.Part.createFormData("file", file.getName(), mFile);
//                colleteral_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    private String getRealPathFromURIPath(Uri uri, Activity activity) {
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(activity, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(activity, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(activity, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(activity, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == PICK_IMAGE_KTP) {
            if (uri != null) {
                String filePath = getRealPathFromURIPath(uri, this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                file_ktp = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                ktp_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            }
        } else if (requestCode == PICK_IMAGE_BPKB) {
            if (uri != null) {
                String filePath = getRealPathFromURIPath(uri, this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                file_bpkb = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                bpkb_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
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

    private String getDataColumn(Context context, Uri uri, String selection,
                                 String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean validateForm(String fKtp, String nomorKtp) {
        if (fKtp == null || fKtp.trim().length() == 0 || fKtp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan upload foto KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_KTP);
                }
            });
            alertDialog.show();
            return false;
        }

        if (fBpkb == null || fBpkb.trim().length() == 0 || fBpkb.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan upload foto KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_BPKB);
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

    @OnClick({R.id.edit_informasi_jaminan, R.id.edit_data_calon_pinjaman ,R.id.cari_axi, R.id.cari_axi_close, R.id.cari_plat_nomor_close, R.id.cari_voucher_close, R.id.cari_plat_nomor, R.id.cari_voucher, R.id.detail_data_calon_pinjaman, R.id.detail_informasi_jaminan, R.id.btn_upload_ktp, R.id.btn_upload_bpkb, R.id.next, R.id.change_ktp, R.id.change_bpkb})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.edit_data_calon_pinjaman:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
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
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(OrderInActivity.this);
                alertDialog2.setTitle("Perhatian");
                alertDialog2.setMessage("Data jaminan & pinjaman yang anda isi akan hilang, apakah anda setuju?");

                alertDialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getBaseContext(), DataCalonPeminjamActivity.class);
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
                intent = new Intent(getBaseContext(), InformasiJaminanActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_upload_ktp:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_KTP);
                break;
            case R.id.btn_upload_bpkb:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_BPKB);
                break;
            case R.id.change_ktp:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_KTP);
                break;
            case R.id.change_bpkb:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_BPKB);
                break;
            case R.id.next:

//                if (validateForm(jumlah_pinjaman, data_calon_peminjam, jaminan_pinjaman, plat_nomor, checkmark)) {

                //DONE
//                Log.d("TAGTAG", "district_id: " + district_id);
//                Log.d("TAGTAG", "city_id: " + city_id);
//                Log.d("TAGTAG", "province_id: " + province_id);
//
//                Log.d("TAGTAG", "agen_id: " + agen_id);
//                Log.d("TAGTAG", "calon_nasabah_id: " + calon_nasabah_id);
//                Log.d("TAGTAG", "pk_number: " + pk_number);
//                Log.d("TAGTAG", "area_id: " + area_id);
//                Log.d("TAGTAG", "cabang_id: " + cabang_id);
//                Log.d("TAGTAG", "subproduk_id: " + subproduk_id);
//                Log.d("TAGTAG", "program_id: " + program_id);
//                Log.d("TAGTAG", "current_pic: " + current_pic);
//                Log.d("TAGTAG", "amount: " + amount);
//                Log.d("TAGTAG", "final_amount: " + final_amount);
//                Log.d("TAGTAG", "status: " + status);
//                Log.d("TAGTAG", "vehicle_id: " + vehicle_id);
//                Log.d("TAGTAG", "no_pengajuan: " + no_pengajuan);
//                Log.d("TAGTAG", "channel_id: " + channel_id);
//                Log.d("TAGTAG", "voucher_code_id: " + voucher_code_id);
//                Log.d("TAGTAG", "objek_brand_id: " + objek_brand_id);
//                Log.d("TAGTAG", "objek_model_id: " + objek_model_id);
//                Log.d("TAGTAG", "rate_asuransi_id: " + rate_asuransi_id);
//                Log.d("TAGTAG", "mrp_id: " + mrp_id);
//                Log.d("TAGTAG", "tenor: " + tenor);
//
//                intent = new Intent(getBaseContext(), KantorCabangActivity.class);
//                intent.putExtra("district_id", district_id);
//                intent.putExtra("city_id", city_id);
//                intent.putExtra("province_id", province_id);
//
//                intent.putExtra("agen_id", agen_id);
//                intent.putExtra("calon_nasabah_id", calon_nasabah_id);
//                intent.putExtra("pk_number", pk_number);
//                intent.putExtra("area_id", area_id);
//                intent.putExtra("cabang_id", cabang_id);
//                intent.putExtra("subproduk_id", subproduk_id);
//                intent.putExtra("program_id", program_id);
//                intent.putExtra("current_pic", current_pic);
//                intent.putExtra("amount", amount);
//                intent.putExtra("final_amount", final_amount);
//                intent.putExtra("status", status);
//                intent.putExtra("vehicle_id", vehicle_id);
//                intent.putExtra("no_pengajuan", no_pengajuan);
//                intent.putExtra("channel_id", channel_id);
//                intent.putExtra("voucher_code_id", voucher_code_id);
//                intent.putExtra("objek_brand_id", objek_brand_id);
//                intent.putExtra("objek_model_id", objek_model_id);
//                intent.putExtra("rate_asuransi_id", rate_asuransi_id);
//                intent.putExtra("mrp_id", mrp_id);
//                intent.putExtra("tenor", tenor);

                startActivity(intent);

//                }
                break;
            case R.id.cari_axi:
                progressBar.setVisibility(View.VISIBLE);
                etAxiIdReff.setEnabled(false);
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
                                    axiAvailable.setText(response.body().getData().get(0).getAttributes().getNomorAxiId());
                                } else {
                                    clearAxi();
                                    progressBar.setVisibility(View.GONE);
                                    axiNotAvailable.setVisibility(View.VISIBLE);

                                }
                            } catch (Exception ex) {
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
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
                    public void onFailure(Call<Axi> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
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
            case R.id.cari_axi_close:
                closeAxi();
                break;
            case R.id.cari_plat_nomor:
                progressBar.setVisibility(View.VISIBLE);
                etPlatNomor.setEnabled(false);
                cariPlatNomorClose.setVisibility(View.VISIBLE);
                cariPlatNomor.setVisibility(View.GONE);

                Call<PlatNomor> platNomor = apiService3.getPlatNomor(etPlatNomor.getText().toString());
                platNomor.enqueue(new Callback<PlatNomor>() {
                    @Override
                    public void onResponse(Call<PlatNomor> call, Response<PlatNomor> response) {

                        if (response.isSuccessful()) {
                            try {
                                if (response.body().getData().size() > 0) {
                                    clearPlatNomor();
                                    progressBar.setVisibility(View.GONE);
                                    platNomorNotAvailable.setVisibility(View.VISIBLE);
                                } else {
                                    clearPlatNomor();
                                    progressBar.setVisibility(View.GONE);
                                    platNomorAvailable.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
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
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
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
                                clearVoucher();
                                progressBar.setVisibility(View.GONE);
                                voucherAvailable.setVisibility(View.VISIBLE);
                                voucherAvailable.setText(response.body().getData().getAttributes().getDeskripsi());
                            } catch (Exception ex) {
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
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
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderInActivity.this);
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

    @OnClick(R.id.cari_axi_close)
    public void onViewClicked() {
    }
}
