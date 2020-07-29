package com.dicicilaja.app.BusinessReward.ui.KtpNpwp.activity;

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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.Activity.RegisterAxi5Activity;
import com.dicicilaja.app.BusinessReward.dataAPI.foto.Foto;
import com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp.FotoKtpNpwp;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity.BusinesRewardActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.Utils.Helper;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class UploadKTPActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    String apiKey;
    SessionManager session;
    @BindView(R.id.upload)
    Button upload;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.noKtp)
    EditText noKtp;
    @BindView(R.id.noNpwp)
    EditText noNpwp;
    @BindView(R.id.image_ktp)
    ImageView imageKtp;
    @BindView(R.id.display_ktp)
    RelativeLayout displayKtp;
    @BindView(R.id.image_npwp)
    ImageView imageNpwp;
    @BindView(R.id.display_npwp)
    RelativeLayout displayNpwp;
    @BindView(R.id.fileKtp)
    RelativeLayout fileKtp;
    @BindView(R.id.fileNpwp)
    RelativeLayout fileNpwp;
    @BindView(R.id.change_ktp)
    TextView changeKtp;
    @BindView(R.id.change_npwp)
    TextView changeNpwp;
    @BindView(R.id.uploadid1)
    ImageView uploadid1;
    @BindView(R.id.uploadid2)
    ImageView uploadid2;
    @BindView(R.id.progressBar)
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


    String fKtp, fNpwp, nomorKtp, nomorNpwp, axiId;
    int textlength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_ktp);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.GONE);

        fKtp = "0";
        fNpwp = "0";
        nomorKtp = "0";
        nomorNpwp = "0";

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upload KTP & NPWP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();

        axiId = session.getNomorAxiId();

        noNpwp.addTextChangedListener(onTextChangedListener());

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nomorKtp = noKtp.getText().toString();
                nomorNpwp = noNpwp.getText().toString();
                if (nomorNpwp == null || nomorNpwp.trim().length() == 0 ) {
                    nomorNpwp = "0";
                }

                if (fNpwp == null || fNpwp.trim().length() == 0 ) {
                    fNpwp = "0";
                }

                Log.d("UPLOADKTP", "NO KTP: " + nomorKtp);
                Log.d("UPLOADKTP", "NO NPWP: " + nomorNpwp);
                Log.d("UPLOADKTP", "FOTO KTP: " + fKtp);
                Log.d("UPLOADKTP", "FOTO NPWP: " + fNpwp);

                if (validateForm(fKtp, nomorKtp, fNpwp, nomorNpwp)) {
                    progressBar.setVisibility(View.VISIBLE);

                    ApiService apiService = ApiClient.getClient().create(ApiService.class);

                    Call<Foto> postKtp = apiService.postFoto(apiKey, axiId, fKtp, fNpwp, nomorKtp, nomorNpwp);
                    postKtp.enqueue(new Callback<Foto>() {
                        @Override
                        public void onResponse(Call<Foto> call, Response<Foto> response) {
                            if (response.code() == 401) {
                                progressBar.setVisibility(View.GONE);
                                session.logoutUser();
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (response.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadKTPActivity.this);
                                alertDialog.setTitle("Sukses");
                                alertDialog.setMessage("Berhasil mengupload foto.");
                                alertDialog.setCancelable(false);

                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getBaseContext(), BusinesRewardActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                                alertDialog.show();

                            } else {
                                progressBar.setVisibility(View.GONE);
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadKTPActivity.this);
                                alertDialog.setTitle("Perhatian");
                                alertDialog.setMessage("Gagal mengupload foto, silahkan coba beberapa saat lagi.");

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
                        public void onFailure(Call<Foto> call, Throwable t) {
                            Log.d("UPLOADKTP", "KODE: " + t.getMessage());
                            progressBar.setVisibility(View.GONE);
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadKTPActivity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Gagal mengupload foto, silahkan coba beberapa saat lagi.");

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


            }
        });
    }


    @OnClick({R.id.fileKtp, R.id.fileNpwp, R.id.change_ktp, R.id.change_npwp})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.fileKtp:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_KTP);
                break;
            case R.id.fileNpwp:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_NPWP);
                break;
            case R.id.change_ktp:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_KTP);
                break;
            case R.id.change_npwp:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_NPWP);
                break;
        }
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

                    fileKtp.setVisibility(View.GONE);
                    displayKtp.setVisibility(View.VISIBLE);
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
        } else if (requestCode == PICK_IMAGE_NPWP && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 750);

                    imageNpwp.setImageBitmap(getResizedBitmap(bitmap, 350));
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fNpwp = Helper.ConvertBitmapToString(resizedBitmap);

                    fileNpwp.setVisibility(View.GONE);
                    displayNpwp.setVisibility(View.VISIBLE);
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
        } else if (requestCode == PICK_IMAGE_COVER && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 750);

//                    image_cover.setImageBitmap(getResizedBitmap(bitmap, 350));
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//                    fileCover = Helper.ConvertBitmapToString(resizedBitmap);
//                    Log.d("REGISTER AXI:::", fileCover);
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
        } else if (requestCode == PICK_IMAGE_NPWP) {
            if (uri != null) {
                String filePath = getRealPathFromURIPath(uri, this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                file_npwp = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                npwp_desc = RequestBody.create(MediaType.parse("text/plain"), file.getName());
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

    private boolean validateForm(String fKtp, String nomorKtp, String fNpwp, String nomorNpwp) {
        if (nomorKtp == null || nomorKtp.trim().length() == 0 || nomorKtp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadKTPActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan nomor KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(noKtp);
                }
            });
            alertDialog.show();
            return false;
        }

        if (fKtp == null || fKtp.trim().length() == 0 || fKtp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadKTPActivity.this);
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

        if (nomorNpwp == null || nomorNpwp.trim().length() == 0 || nomorNpwp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadKTPActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan masukan nomor NPWP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(noKtp);
                }
            });
            alertDialog.show();
            return false;
        }

        if (fNpwp == null || fNpwp.trim().length() == 0 || fNpwp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadKTPActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Silahkan upload foto NPWP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Pilih foto"), PICK_IMAGE_NPWP);
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = noNpwp.getText().toString();
                textlength = noNpwp.getText().length();

                if(text.endsWith(" "))
                    return;

                if(textlength == 3 || textlength == 7 || textlength == 11 || textlength == 17)
                {
                    noNpwp.setText(new StringBuilder(text).insert(text.length()-1, ".").toString());
                    noNpwp.setSelection(noNpwp.getText().length());
                }else if(textlength == 13){
                    noNpwp.setText(new StringBuilder(text).insert(text.length()-1, "-").toString());
                    noNpwp.setSelection(noNpwp.getText().length());
                }
            }
        };
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
