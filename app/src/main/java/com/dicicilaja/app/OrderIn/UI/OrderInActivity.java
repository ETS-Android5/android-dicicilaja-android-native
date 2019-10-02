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
    @BindView(R.id.btn_data_calon_peminjam)
    RelativeLayout btnDataCalonPeminjam;
    @BindView(R.id.txt_informasi_jaminan)
    TextView txtInformasiJaminan;
    @BindView(R.id.chev1)
    ImageView chev1;
    @BindView(R.id.btn_informasi_jaminan)
    RelativeLayout btnInformasiJaminan;
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
    Button cariAxi;
    @BindView(R.id.axi_available)
    TextView axiAvailable;
    @BindView(R.id.axi_not_available)
    TextView axiNotAvailable;
    @BindView(R.id.cari_plat_nomor)
    Button cariPlatNomor;
    @BindView(R.id.et_plat_nomor)
    EditText etPlatNomor;
    @BindView(R.id.plat_nomor_available)
    TextView platNomorAvailable;
    @BindView(R.id.plat_nomor_not_available)
    TextView platNomorNotAvailable;
    @BindView(R.id.cari_voucher)
    Button cariVoucher;
    @BindView(R.id.voucher_available)
    TextView voucherAvailable;
    @BindView(R.id.voucher_not_available)
    TextView voucherNotAvailable;

    private Bitmap bitmap;
    private int PICK_IMAGE_KTP = 100;
    private int PICK_IMAGE_BPKB = 200;
    private static final String TAG = OrderInActivity.class.getSimpleName();
    private static final int READ_REQUEST_CODE = 400;
    private Uri uri;
    MultipartBody.Part file_ktp, file_bpkb;
    RequestBody ktp_desc, bpkb_desc;


    String fKtp, fBpkb, axiId;
    SessionManager session;

    ApiService3 apiService3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_in);
        ButterKnife.bind(this);

        initToolbar();
        initAction();

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

    @OnClick({R.id.cari_axi, R.id.cari_plat_nomor, R.id.cari_voucher, R.id.btn_data_calon_peminjam, R.id.btn_informasi_jaminan, R.id.btn_upload_ktp, R.id.btn_upload_bpkb, R.id.next, R.id.change_ktp, R.id.change_bpkb})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_data_calon_peminjam:
                intent = new Intent(getBaseContext(), DataCalonPeminjamActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_informasi_jaminan:
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
                intent = new Intent(getBaseContext(), KantorCabangActivity.class);
                startActivity(intent);
                break;
            case R.id.cari_axi:
                progressBar.setVisibility(View.VISIBLE);
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
            case R.id.cari_plat_nomor:
                progressBar.setVisibility(View.VISIBLE);
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
            case R.id.cari_voucher:
                progressBar.setVisibility(View.VISIBLE);
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
                                } else {
                                    clearVoucher();
                                    progressBar.setVisibility(View.GONE);
                                    voucherNotAvailable.setVisibility(View.VISIBLE);

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
        }
    }
}
