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
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateMitra;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateCustomer.CreateCustomer;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateMitra.CreateMitra;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Upload.services.Service;
import com.dicicilaja.app.Utils.Helper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterMaxi5Activity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    Button btnDaftar;
    String apiKey, namapemilik, alamatpemilik, nohp, noktp, jk;
    String namaperusahaan, alamatperusahaan, kelurahan, desa, telp;
    String npwppemilik, npwpperusahaan;
    String katasandi, email, program_id, program_nama;
    String ktp_image, npwp_image;
    TextView upload_ktp, upload_npwp;
    ImageView image_ktp, image_npwp;
    ProgressDialog progress;
    CheckBox check;
    TextView textCheck;

    String fileKtp, fileNpwp;


    private Bitmap bitmap;
    private int PICK_IMAGE_KTP = 100;
    private int PICK_IMAGE_NPWP = 200;
    private static final String TAG = RegisterMaxi5Activity.class.getSimpleName();
    private static final int READ_REQUEST_CODE = 300;
    private Uri uri;
    MultipartBody.Part file_ktp, file_npwp;
    RequestBody ktp_desc, npwp_desc;

    Service service;
    String mCurrentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_maxi5);

        service = new Service();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnDaftar = findViewById(R.id.btnLanjut);

        namapemilik = getIntent().getStringExtra("namapemilik");
        alamatpemilik = getIntent().getStringExtra("alamatpemilik");
        nohp = getIntent().getStringExtra("nohp");
        noktp = getIntent().getStringExtra("noktp");
        jk = getIntent().getStringExtra("jk");
        namaperusahaan = getIntent().getStringExtra("namaperusahaan");
        alamatperusahaan = getIntent().getStringExtra("alamatperusahaan");
        desa = getIntent().getStringExtra("desa");
        telp = getIntent().getStringExtra("telp");
        apiKey = getIntent().getStringExtra("apiKey");
        npwppemilik = getIntent().getStringExtra("npwppemilik");
        npwpperusahaan = getIntent().getStringExtra("npwpperusahaan");
        katasandi = getIntent().getStringExtra("katasandi");
        email = getIntent().getStringExtra("email");
        program_id = getIntent().getStringExtra("program_id");
        program_nama = getIntent().getStringExtra("program_nama");

        check = findViewById(R.id.check);
        upload_ktp = findViewById(R.id.upload_ktp);
        image_ktp = findViewById(R.id.image_ktp);
        upload_npwp = findViewById(R.id.upload_npwp);
        image_npwp = findViewById(R.id.image_npwp);
        textCheck = findViewById(R.id.textCheck);

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

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);


        btnDaftar = findViewById(R.id.btnLanjut);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ktp_image = "http://dicicilaja.com/public/assets/images/not-found.jpg";
                    npwp_image = "http://dicicilaja.com/public/assets/images/not-found.jpg";
                } catch (Exception ex) {

                }

                if(validateForm(fileKtp)) {
                    if(check.isChecked()) {
                        Log.d("ajukanpengajuan","apiKey : " + apiKey);
                        Log.d("ajukanpengajuan","namapemilik : " + namapemilik);
                        Log.d("ajukanpengajuan","jk : " + jk);
                        Log.d("ajukanpengajuan","alamatpemilik : " + alamatpemilik);
                        Log.d("ajukanpengajuan","nohp : " + nohp);
                        Log.d("ajukanpengajuan","noktp : " + noktp);
                        Log.d("ajukanpengajuan","namaperusahaan : " + namaperusahaan);
                        Log.d("ajukanpengajuan","alamatperusahaan : " + alamatperusahaan);
                        Log.d("ajukanpengajuan","desa : " + desa);
                        Log.d("ajukanpengajuan","telp : " + telp);
                        Log.d("ajukanpengajuan","npwppemilik : " + npwppemilik);
                        Log.d("ajukanpengajuan","npwpperusahaan : " + npwpperusahaan);
                        Log.d("ajukanpengajuan","email : " + email);
                        Log.d("ajukanpengajuan","program_id : " + program_id);
                        Log.d("ajukanpengajuan","program_nama : " + program_nama);
                        Log.d("ajukanpengajuan","fileKtp : " + fileKtp);
                        Log.d("ajukanpengajuan","fileNpwp : " + fileNpwp);
                        progress.show();
                        doCreate(apiKey, namapemilik, alamatpemilik, nohp, noktp, jk, namaperusahaan, alamatperusahaan, desa, telp, npwppemilik, npwpperusahaan, email, program_id, program_nama, fileKtp, fileNpwp);
                    }else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterMaxi5Activity.this);
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

    private boolean validateForm(String fileKtp) {
        if (fileKtp == null || fileKtp.trim().length() == 0 || fileKtp.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterMaxi5Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Unggah foto KTP");

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
        if(requestCode == PICK_IMAGE_KTP && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
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
            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        } else if(requestCode == PICK_IMAGE_NPWP && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 750);

                    image_npwp.setImageBitmap(resizedBitmap);
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
            }else{
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
        }

    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

    private void doCreate(final String apiKey, final String namapemilik, final String alamatpemilik, final String nohp, final String noktp, final String jk, final String namaperusahaan, final String alamatperusahaan, final String desa, final String telp, final String npwppemilik, final String npwpperusahaan, final String email, final String program_id, final String program_nama, final String fileKtp, final String fileNpwp) {
        InterfaceCreateMitra apiService =
                ComposserClient.getClient().create(InterfaceCreateMitra.class);


        Call<CreateMitra> call = apiService.create(apiKey, namaperusahaan, namapemilik, jk, alamatpemilik, nohp, telp, noktp, npwppemilik, npwpperusahaan, desa, alamatperusahaan, email, program_nama, program_id, fileKtp, fileNpwp);
        call.enqueue(new Callback<CreateMitra>() {
            @Override
            public void onResponse(Call<CreateMitra> call, Response<CreateMitra> response) {
                if(response.isSuccessful()){
                    progress.dismiss();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Intent intent = new Intent(getBaseContext(), RegisterMaxiDone.class);
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
            public void onFailure(Call<CreateMitra> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
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
