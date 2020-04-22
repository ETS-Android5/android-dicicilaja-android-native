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

import com.dicicilaja.app.API.Client.ApiClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileCustomer.Datum;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileCustomer.ProfileCustomer;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceCreateRequest;
import com.dicicilaja.app.API.Model.CreateRequest.CreateRequest;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProfileCustomer;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Remote.ApiUtils;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.Upload.services.Service;
import com.dicicilaja.app.Upload.utils.FileUtils;
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

public class AjukanPengajuanAxi2Activity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    Button ajukan;
    EditText inputNama, inputHp, inputAlamat, inputProvinsi, inputKota, inputKecamatan, inputEmail;
    String channel_id, qty;
    TextInputLayout inputLayoutNama, inputLayoutEmail, inputLayoutHp, inputLayoutAlamat, inputLayoutProvinsi, inputLayoutKota,inputLayoutKecamatan;
    String nama, email, hp, alamat, provinsi, kota, kecamatan;
    String axi, axi_referral, program_id, colleteral_id, status_id, manufacturer, year, tenor, amount, area_id, branch_id, client_name, address, district, city, province, ktp_image, colleteral_image;
    CheckBox check;
    InterfaceCreateRequest interfaceCreateRequest;
    Button upload_ktp, upload_colleteral;
    ImageView image_ktp, image_colleteral;
    TextView textCheck;
    private Bitmap bitmap;
    private int PICK_IMAGE_KTP = 100;
    private int PICK_IMAGE_COLLETERAL = 200;
    private static final String TAG = AjukanPengajuanAxi2Activity.class.getSimpleName();
    private static final int READ_REQUEST_CODE = 300;
    private Uri uri;
    MultipartBody.Part file_ktp, file_colleteral;
    String fileKtp, fileBpkb;
    RequestBody ktp_desc, colleteral_desc;
    ProgressDialog progress;
    String apiKey;
    SessionManager session;

    Service service;
    String mCurrentPhotoPath;

    Datum dataCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajukan_pengajuan_axi2);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        service = new Service();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        inputNama = findViewById(R.id.inputNama);
        inputHp = findViewById(R.id.inputHp);
        inputAlamat = findViewById(R.id.inputAlamat);
        inputProvinsi = findViewById(R.id.inputProvinsi);
        inputKota = findViewById(R.id.inputKota);
        inputKecamatan = findViewById(R.id.inputKecamatan);
        inputEmail = findViewById(R.id.inputEmail);
        ajukan = findViewById(R.id.ajukan);
        inputLayoutNama  = findViewById(R.id.inputLayoutNama);
        inputLayoutEmail = findViewById(R.id.inputLayoutEmail);
        inputLayoutHp = findViewById(R.id.inputLayoutHp);
        inputLayoutAlamat = findViewById(R.id.inputLayoutAlamat);
        inputLayoutProvinsi = findViewById(R.id.inputLayoutProvinsi);
        inputLayoutKota = findViewById(R.id.inputLayoutKota);
        inputLayoutKecamatan = findViewById(R.id.inputLayoutKecamatan);
        check = findViewById(R.id.check);
        upload_ktp = findViewById(R.id.upload_ktp);
        image_ktp = findViewById(R.id.image_ktp);
        upload_colleteral = findViewById(R.id.upload_colleteral);
        image_colleteral = findViewById(R.id.image_colleteral);
        textCheck = findViewById(R.id.textCheck);



        try {
            if(session.getRole().equals("basic")){
                final ProgressDialog progress1 = new ProgressDialog(this);
                progress1.setMessage("Sedang memuat data...");
                progress1.setCanceledOnTouchOutside(false);
                progress1.show();

                InterfaceProfileCustomer apiService =
                        ApiClient.getClient().create(InterfaceProfileCustomer.class);

                Call<ProfileCustomer> callProfile = apiService.getProfile(apiKey);
                callProfile.enqueue(new Callback<ProfileCustomer>() {
                    @Override
                    public void onResponse(Call<ProfileCustomer> call, Response<ProfileCustomer> response) {
                        dataCustomer = response.body().getData().get(0);

                        try {
                            inputNama.setText(dataCustomer.getName().toString());
                            inputEmail.setText(dataCustomer.getEmail().toString());
                            inputHp.setText(dataCustomer.getPhone().toString());
                            inputAlamat.setText(dataCustomer.getAddress().toString());
                            inputKecamatan.setText(dataCustomer.getDistrict().toString());
                            inputKota.setText(dataCustomer.getCity().toString());
                            inputProvinsi.setText(dataCustomer.getProvince().toString());
                        }catch (Exception ex){

                        }
                        progress1.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ProfileCustomer> call, Throwable t) {
                        progress1.dismiss();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
                        alertDialog.setMessage("Koneksi internet tidak ditemukan");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertDialog.show();
                    }
                });
            }
        }catch (Exception ex){

        }


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

                Intent openGalleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                openGalleryIntent.setType("image/*");
                File photoFile = null;

                try {

                    photoFile = FileUtils.createImageFile(getBaseContext());
                    mCurrentPhotoPath = photoFile.getAbsolutePath();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                startActivityForResult(openGalleryIntent, PICK_IMAGE_KTP);
            }
        });

        upload_colleteral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih gambar"), PICK_IMAGE_COLLETERAL);
            }
        });

        channel_id = "1";
        qty = "1";
        interfaceCreateRequest = ApiUtils.getCreateRequest();

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);

        inputNama.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputNama));
        inputEmail.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputEmail));
        inputHp.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputHp));
        inputAlamat.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputAlamat));
        inputProvinsi.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputProvinsi));
        inputKota.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputKota));
        inputKecamatan.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputKecamatan));


        ajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    Log.d(TAG, "uploadImage: mCurrentPhotoPath " + mCurrentPhotoPath);
//
//                    if (mCurrentPhotoPath != null && !mCurrentPhotoPath.equals("")) {
//
//                        String photnName = "upload_ktp";
//
//                        service.saveUserImage(getBaseContext(), photnName, new File(mCurrentPhotoPath), new Callback<UserProfile>() {
//                            @Override
//                            public void onResponse(Call<UserProfile> call, ObjekModel<UserProfile> response) {
//                                showMessage("Image successfully uploaded to server.!");
//                            }
//
//                            @Override
//                            public void onFailure(Call<UserProfile> call, Throwable t) {
//                                showMessage("Upload Failed.!");
//                                showMessage("error : " + t.getMessage());
//                            }
//                        });
//                    } else {
//                        showMessage("No Image found to be uploaded.");
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
                try {
                    axi_referral = getIntent().getStringExtra("axi_referral");
                    nama = inputNama.getText().toString();
                    email = inputEmail.getText().toString();
                    hp = inputHp.getText().toString();
                    alamat = inputAlamat.getText().toString();
                    provinsi = inputProvinsi.getText().toString();
                    kota = inputKota.getText().toString();
                    kecamatan = inputKecamatan.getText().toString();
                    program_id = getIntent().getStringExtra("program_id");
                    colleteral_id = getIntent().getStringExtra("colleteral_id");
                    status_id = "4";
                    manufacturer = getIntent().getStringExtra("manufacturer");
                    year = getIntent().getStringExtra("year");
                    tenor = getIntent().getStringExtra("tenor");
                    amount = getIntent().getStringExtra("ammount");
                    area_id = getIntent().getStringExtra("area_id");
                    branch_id = getIntent().getStringExtra("branch_id");
                    client_name = inputNama.getText().toString();
                    address = inputAlamat.getText().toString();
                    district = inputKecamatan.getText().toString();
                    city = inputKota.getText().toString();
                    province = inputProvinsi.getText().toString();
                    //ktp_image = "http://dicicilaja.com/public/assets/images/not-found.jpg";
                    //colleteral_image = "http://dicicilaja.com/public/assets/images/not-found.jpg";

                    if(validateForm(client_name, email, hp, alamat, provinsi, kota, kecamatan)) {
                        if(check.isChecked()) {
                            Log.d("ajukanpengajuan","axi_referral:" + axi_referral);
                            Log.d("ajukanpengajuan","program_id:" + program_id);
                            Log.d("ajukanpengajuan","colleteral_id:" + colleteral_id);
                            Log.d("ajukanpengajuan","status_id:" + status_id);
                            Log.d("ajukanpengajuan","manufacturer:" + manufacturer);
                            Log.d("ajukanpengajuan","year:" + year);
                            Log.d("ajukanpengajuan","tenor:" + tenor);
                            Log.d("ajukanpengajuan","amount:" + amount);
                            Log.d("ajukanpengajuan","qty:" + qty);
                            Log.d("ajukanpengajuan","area_id:" + area_id);
                            Log.d("ajukanpengajuan","branch_id:" + branch_id);
                            Log.d("ajukanpengajuan","channel_id:" + channel_id);
                            Log.d("ajukanpengajuan","client_name:" + client_name);
                            Log.d("ajukanpengajuan","hp:" + hp);
                            Log.d("ajukanpengajuan","address:" + address);
                            Log.d("ajukanpengajuan","district:" + district);
                            Log.d("ajukanpengajuan","city:" + city);
                            Log.d("ajukanpengajuan","province:" + province);
                            Log.d("ajukanpengajuan","email:" + email);
                            Log.d("ajukanpengajuan","ktp_image:" + ktp_image);
                            Log.d("ajukanpengajuan","colleteral_image:" + colleteral_image);
                            progress.show();
                            if(session.getRole() != null){
                                doRequest(apiKey, axi_referral, channel_id, program_id, colleteral_id, status_id, manufacturer, year, tenor, amount, qty, area_id, branch_id, client_name, hp, address, district, city, province, email, fileKtp, fileBpkb);
                            }else {
                                doRequest(null, axi_referral, channel_id, program_id, colleteral_id, status_id, manufacturer, year, tenor, amount, qty, area_id, branch_id, client_name, hp, address, district, city, province, email, fileKtp, fileBpkb);
                            }
                        }else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
                            alertDialog.setMessage("Anda belum menyetujui syarat dan ketentuan yang berlaku. Silakan centang pada kotak yang tersedia.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }
                    }
                }catch (Exception ex) {

                }

            }
        });

    }

    public void showMessage(String message) {
        try {
            android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AjukanPengajuanAxi2Activity.this).create();
            alertDialog.setTitle("Message");
            alertDialog.setMessage(message);
            alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void doRequest(final String apiKey, final String axi_referral, final String channel_id, final String program_id, final String colleteral_id, final String status_id, final String manufacturer, final String year, final String tenor, final String amount, final String qty, final String area_id, final String branch_id, final String client_name, final String hp, final String address, final String district, final String city, final String province, final String email,  final String file_ktp, final String file_colleteral) {
        InterfaceCreateRequest apiService =
                RetrofitClient.getClient().create(InterfaceCreateRequest.class);
        String product = "Dana Multiguna";
        Call<CreateRequest> call = apiService.assign(apiKey, axi_referral, channel_id, program_id, colleteral_id, status_id, manufacturer, year, tenor, amount, qty, area_id, branch_id, client_name, hp, address, district, city, province, email, file_ktp, file_colleteral, product);
        call.enqueue(new Callback<CreateRequest>() {
            @Override
            public void onResponse(Call<CreateRequest> call, Response<CreateRequest> response) {
                Log.d("PENGAJUAN:::", String.valueOf(response.body()));
                if(response.isSuccessful()){
                    progress.dismiss();
                    Toast.makeText(getBaseContext(),"Selamat! Pengajuan Anda berhasil dibuat",Toast.LENGTH_SHORT).show();
                    try{
                        if(session.getRole().equals("axi")) {
                            Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else if(session.getRole().equals("tc") || session.getRole().equals("crh") || session.getRole().equals("cro")) {
                            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else if(session.getRole().equals("basic")) {
                            Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }catch (Exception ex){
                        Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                }else if(response.code() == 406){
                    progress.dismiss();
                    Toast.makeText(getBaseContext(),"Something went wrong with our server.",Toast.LENGTH_SHORT).show();
                    Log.e("BUAT PENGAJUAN::::", response.message());
                }else{
                    progress.dismiss();
                    Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<CreateRequest> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
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
                    Log.d("REQUEST AXI:::", fileKtp);
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
        } else if(requestCode == PICK_IMAGE_COLLETERAL && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 750);

                    image_colleteral.setImageBitmap(resizedBitmap);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fileBpkb = Helper.ConvertBitmapToString(resizedBitmap);
                    Log.d("REQUEST AXI:::", fileBpkb);
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

        super.onActivityResult(requestCode, resultCode, data);
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
        } else if (requestCode == PICK_IMAGE_COLLETERAL) {
            if(uri != null){
                String filePath = getRealPathFromURIPath(uri,this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                file_colleteral = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                colleteral_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            }
        }

    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

    private boolean validateForm(String nama, String email, String hp, String alamat, String provinsi, String kota, String kecamatan) {
        if(nama == null || nama.trim().length() == 0 || nama.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
            alertDialog.setMessage("Masukan nama nasabah");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNama);
                }
            });
            alertDialog.show();
            return false;
        }

        if(email == null || email.trim().length() == 0 || email.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
            alertDialog.setMessage("Masukan email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        }

        if(hp == null || hp.trim().length() == 0 || hp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
            alertDialog.setMessage("Masukan no. handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputHp);
                }
            });
            alertDialog.show();
            return false;
        }

        if(alamat == null || alamat.trim().length() == 0 || alamat.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
            alertDialog.setMessage("Masukan alamat");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamat);
                }
            });
            alertDialog.show();
            return false;
        }

        if(provinsi == null || provinsi.trim().length() == 0 || provinsi.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
            alertDialog.setMessage("Masukan nama provinsi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputProvinsi);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kota == null || kota.trim().length() == 0 || kota.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
            alertDialog.setMessage("Masukan nama kota");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKota);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kecamatan == null || kecamatan.trim().length() == 0 || kecamatan.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
            alertDialog.setMessage("Masukan nama kecamatan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKecamatan);
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }

    private boolean validateNama() {
        if (inputNama.getText().toString().trim().isEmpty()) {
            inputLayoutNama.setError("Masukan nama nasabah");
            requestFocus(inputNama);
            return false;
        } else {
            inputLayoutNama.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        if (inputEmail.getText().toString().trim().isEmpty()) {
            inputLayoutEmail.setError("Masukan email");
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateHp() {
        if (inputHp.getText().toString().trim().isEmpty()) {
            inputLayoutHp.setError("Masukan no. handphone");
            requestFocus(inputHp);
            return false;
        } else {
            inputLayoutHp.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAlamat() {
        if (inputAlamat.getText().toString().trim().isEmpty()) {
            inputLayoutAlamat.setError("Masukan alamat");
            requestFocus(inputAlamat);
            return false;
        } else {
            inputLayoutAlamat.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateProvinsi() {
        if (inputProvinsi.getText().toString().trim().isEmpty()) {
            inputLayoutProvinsi.setError("Masukan nama provinsi");
            requestFocus(inputProvinsi);
            return false;
        } else {
            inputLayoutProvinsi.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateKota() {
        if (inputKota.getText().toString().trim().isEmpty()) {
            inputLayoutKota.setError("Masukan nama kota");
            requestFocus(inputKota);
            return false;
        } else {
            inputLayoutKota.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateKecamatan() {
        if (inputKecamatan.getText().toString().trim().isEmpty()) {
            inputLayoutKecamatan.setError("Masukan nama kecamatan");
            requestFocus(inputKecamatan);
            return false;
        } else {
            inputLayoutKecamatan.setErrorEnabled(false);
        }
        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.inputNama:
                    validateNama();
                    break;

                case R.id.inputEmail:
                    validateEmail();
                    break;

                case R.id.inputHp:
                    validateHp();
                    break;

                case R.id.inputAlamat:
                    validateAlamat();
                    break;

                case R.id.inputProvinsi:
                    validateProvinsi();
                    break;

                case R.id.inputKota:
                    validateKota();
                    break;

                case R.id.inputKecamatan:
                    validateKecamatan();
                    break;

            }
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