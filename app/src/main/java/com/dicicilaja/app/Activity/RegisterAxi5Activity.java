package com.dicicilaja.app.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.dicicilaja.app.API.Client.ComposserClient;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateAXI;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateAXI.CreateAXI;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.Upload.services.Service;
import com.dicicilaja.app.Utils.Helper;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;

public class RegisterAxi5Activity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    Button upload, btnDaftar;
    CheckBox check;
    ImageView gambar;
    String apiKey;
    SessionManager session;
    String axi_id, nama, email, hp, namaibu, area, cabang;
    String no_ktp_pasangan, nama_pasangan, no_ktp, tempat_lahir, tanggal, alamat, rt, rw, kelurahan, kecamatan, kota, provinsi, kodepos, jk, status;
    String nama_bank, no_rekening, cabang_bank, an_rekening, kota_bank;
    String npwp, nama_npwp, status_npwp, pkp_status;
    String imageKtp, imageNpwp, imageCover, kode_bank;

    TextView upload_ktp, upload_npwp, upload_cover;
    ImageView image_ktp, image_npwp, image_cover;
    ProgressDialog progress;
    TextView textCheck;

    MaterialProgressBar progressBar;

    private Bitmap bitmap;
    private int PICK_IMAGE_KTP = 100;
    private int PICK_IMAGE_NPWP = 200;
    private int PICK_IMAGE_COVER = 300;
    private static final String TAG = RegisterAxi5Activity.class.getSimpleName();
    private static final int READ_REQUEST_CODE = 400;
    private Uri uri;
    MultipartBody.Part file_ktp, file_npwp, file_cover;
    RequestBody ktp_desc, npwp_desc, cover_desc;

    String fileKtp, fileNpwp, fileCover;

    Service service;
    String mCurrentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi5);

        service = new Service();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(RegisterAxi5Activity.this);
        if(session.isLoggedIn() == TRUE){
            getSupportActionBar().setTitle("Tambah Rekan Bisnis");
        }

        btnDaftar = findViewById(R.id.btnLanjut);

        check = findViewById(R.id.check);
        upload_ktp = findViewById(R.id.upload_ktp);
        image_ktp = findViewById(R.id.image_ktp);
        upload_npwp = findViewById(R.id.upload_npwp);
        image_npwp = findViewById(R.id.image_npwp);
        upload_cover = findViewById(R.id.upload_cover);
        image_cover = findViewById(R.id.image_cover);
        textCheck = findViewById(R.id.textCheck);
        progressBar             = findViewById(R.id.progressBar);

        textCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {
                    check.setChecked(false);
                }
                else {
                    check.setChecked(true);
                }
            }
        });

        upload_ktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih gambar"), PICK_IMAGE_KTP);
            }
        });

        upload_npwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih gambar"), PICK_IMAGE_NPWP);
            }
        });

        upload_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih gambar"), PICK_IMAGE_COVER);
            }
        });

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
        rt           = getIntent().getStringExtra("rt");
        rw            = getIntent().getStringExtra("rw");
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
        kode_bank       = getIntent().getStringExtra("kode_bank");
        nama_pasangan          = getIntent().getStringExtra("nama_pasangan");
        no_ktp_pasangan          = getIntent().getStringExtra("no_ktp_pasangan");

        imageKtp = "http://dicicilaja.com/public/assets/images/not-found.jpg";
        imageNpwp = "http://dicicilaja.com/public/assets/images/not-found.jpg";
        imageCover = "http://dicicilaja.com/public/assets/images/not-found.jpg";

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);

        btnDaftar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    imageKtp = "http://dicicilaja.com/public/assets/images/not-found.jpg";
                    imageNpwp = "http://dicicilaja.com/public/assets/images/not-found.jpg";
                    imageCover = "http://dicicilaja.com/public/assets/images/not-found.jpg";
                } catch (Exception ex) {

                }
                if(validateForm(fileKtp, fileCover)) {
                    if (check.isChecked()){
                        btnDaftar.setEnabled(false);
                        progress.show();
                        buatAkun(apiKey, area, cabang, axi_id, nama, no_ktp, tempat_lahir, tanggal, status, alamat, rt, rw, provinsi, kota, kecamatan, kelurahan, kodepos, jk, email, hp, namaibu, no_rekening, cabang_bank, an_rekening, kota_bank, npwp, nama_npwp, status_npwp, pkp_status, fileKtp, fileNpwp, fileCover, kode_bank, nama_pasangan, no_ktp_pasangan);
                        btnDaftar.setEnabled(true);
                    }else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxi5Activity.this);
                        alertDialog.setMessage("Anda belum menyetujui syarat dan ketentuan yang berlaku. Silakan centang pada kotak yang tersedia.");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.show();
                    }
                }

            }
        });
    }

    private boolean validateForm(String fileKtp, String fileCover) {
        if (fileKtp == null || fileKtp.trim().length() == 0 || fileKtp.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi5Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Unggah foto KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
            return false;
        }

        if (fileCover == null || fileCover.trim().length() == 0 || fileCover.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi5Activity.this);
            alertDialog.setTitle("Perhatian");
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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

    @Override
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

                    image_ktp.setImageBitmap(resizedBitmap);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fileKtp = Helper.ConvertBitmapToString(resizedBitmap);
                    Log.d("REGISTER AXI:::", fileKtp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                String filePath = getRealPathFromURIPath(uri, this);
//                File file = new File(filePath);
//                Log.d(TAG, "Filename " + file.getName());
//                //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
//                file_ktp = MultipartBody.Part.createFormData("file", file.getName(), mFile);
//                Log.d(TAG, "file_ktp " + file_ktp);
//                ktp_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        } else if (requestCode == PICK_IMAGE_NPWP && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 750);

                    image_npwp.setImageBitmap(getResizedBitmap(bitmap, 350));
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fileNpwp = Helper.ConvertBitmapToString(resizedBitmap);
                    Log.d("REGISTER AXI:::", fileNpwp);
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
        } else if (requestCode == PICK_IMAGE_COVER && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 750);

                    image_cover.setImageBitmap(getResizedBitmap(bitmap, 350));
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fileCover = Helper.ConvertBitmapToString(resizedBitmap);
                    Log.d("REGISTER AXI:::", fileCover);
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

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == PICK_IMAGE_KTP) {
            if(uri != null){
                String filePath = getRealPathFromURIPath(uri,this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                file_ktp = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                ktp_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            }
        } else if (requestCode == PICK_IMAGE_NPWP) {
            if(uri != null){
                String filePath = getRealPathFromURIPath(uri,this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                file_npwp = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                npwp_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            }
        } else if (requestCode == PICK_IMAGE_COVER) {
            if(uri != null){
                String filePath = getRealPathFromURIPath(uri,this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                file_cover = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                cover_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            }
        }

    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

    private void buatAkun(final String apiKey, String area, String cabang, String axi_id, String nama, String no_ktp, String tempat_lahir, String tanggal, String status, String alamat, String rt, String rw, String provinsi, String kota, String kecamatan, String kelurahan, String kodepos, String jk, String email, String hp, String namaibu, String no_rekening, String cabang_bank, String an_rekening, String kota_bank, String npwp, String nama_npwp, String status_npwp, String pkp_status, String imageKtp, String imageNpwp, String imageCover, String kode_bank, String nama_pasangan, String no_ktp_pasangan) {
        InterfaceCreateAXI apiService =
                ComposserClient.getClient().create(InterfaceCreateAXI.class);

        Log.d(TAG, "apiKey: " + apiKey);
        Log.d(TAG, "area_id: " + area);
        Log.d(TAG, "cabang_daftar: " + cabang);
        Log.d(TAG, "axi_reff: " + axi_id);
        Log.d(TAG, "nama: " + nama);
        Log.d(TAG, "no_ktp: " + no_ktp);
        Log.d(TAG, "kota_lahir: " + tempat_lahir);
        Log.d(TAG, "tanggal_lahir: " + tanggal);
        Log.d(TAG, "status_perkawinan: " + status);
        Log.d(TAG, "alamat_ktp: " + alamat);
        Log.d(TAG, "rt: " + rt);
        Log.d(TAG, "rw: " + rw);
        Log.d(TAG, "provinsi_id: " + provinsi);
        Log.d(TAG, "kota_id: " + kota);
        Log.d(TAG, "kelurahan_id: " + kelurahan);
        Log.d(TAG, "kecamatan_id: " + kecamatan);
        Log.d(TAG, "kode_pos: " + kodepos);
        Log.d(TAG, "jenis_kelamin: " + jk);
        Log.d(TAG, "email: " + email);
        Log.d(TAG, "no_hp: " + hp);
        Log.d(TAG, "nama_ibu_kandung: " + namaibu);
        Log.d(TAG, "no_rekening: " + no_rekening);
        Log.d(TAG, "cabang_bank: " + cabang_bank);
        Log.d(TAG, "an_rekening: " + an_rekening);
        Log.d(TAG, "kota_bank: " + kota_bank);
        Log.d(TAG, "no_npwp: " + npwp);
        Log.d(TAG, "nama_npwp: " + nama_npwp);
        Log.d(TAG, "tipe_npwp: " + status_npwp);
        Log.d(TAG, "pkp: " + pkp_status);
        Log.d(TAG, "foto_ktp: " + imageKtp);
        Log.d(TAG, "foto_npwp: " + imageNpwp);
        Log.d(TAG, "foto_rekening: " + imageCover);
        Log.d(TAG, "bank_id: " + kode_bank);
        Log.d(TAG, "nama_pasangan: " + nama_pasangan);
        Log.d(TAG, "no_ktp_pasangan: " + no_ktp_pasangan);

               Call<CreateAXI> call = apiService.create(apiKey, area, cabang, axi_id, nama, no_ktp, tempat_lahir, tanggal, status, alamat, rt, rw, provinsi, kota, kelurahan, kecamatan, kodepos, jk, email, hp, namaibu, no_rekening, cabang_bank, an_rekening, kota_bank, npwp, nama_npwp, status_npwp, pkp_status, imageKtp, imageNpwp, imageCover, kode_bank, nama_pasangan, no_ktp_pasangan);
        call.enqueue(new Callback<CreateAXI>() {
            @Override
            public void onResponse(Call<CreateAXI> call, Response<CreateAXI> response) {
                Log.d(TAG, "onResponse: " + response);
                if(response.isSuccessful()){
                    progress.dismiss();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Intent intent = new Intent(getBaseContext(), RegisterAxiDone.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (response.code() == 422){
                    progress.dismiss();
                    Toast.makeText(getBaseContext(),"Email Anda sudah terdaftar atau silahkan hubungi Tasya.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else{
                    progress.dismiss();
                    Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CreateAXI> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

//    private boolean validateForm(String imageKtp, String imageNpwp, String imageCover) {
//        if (imageKtp == null || imageKtp.trim().length() == 0 || imageKtp.equals("0")) {
//            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi5Activity.this);
//            alertDialog.setMessage("Unggah foto KTP");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            alertDialog.show();
//            return false;
//        }
//        if (imageNpwp == null || imageNpwp.trim().length() == 0 || imageNpwp.equals("0")) {
//            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi5Activity.this);
//            alertDialog.setMessage("Unggah foto NPWP");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            alertDialog.show();
//            return false;
//        }
//        if (imageCover == null || imageCover.trim().length() == 0 || imageCover.equals("0")) {
//            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterAxi5Activity.this);
//            alertDialog.setMessage("Unggah foto cover buku tabungan");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            alertDialog.show();
//            return false;
//        }
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}