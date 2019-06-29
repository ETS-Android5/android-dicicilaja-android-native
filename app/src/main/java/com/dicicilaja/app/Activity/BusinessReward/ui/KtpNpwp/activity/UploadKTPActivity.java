package com.dicicilaja.app.Activity.BusinessReward.ui.KtpNpwp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.Activity.BusinessReward.dataAPI.fotoKtpNpwp.FotoKtpNpwp;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiClient;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiService;
import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.Activity.RegisterAxi5Activity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.Utils.Helper;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadKTPActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.inputNoKTP)
    EditText inputNoKTP;
    @BindView(R.id.inputLayoutEmailID)
    TextInputLayout inputLayoutEmailID;
    @BindView(R.id.inputNoNPWP)
    EditText inputNoNPWP;
    @BindView(R.id.inputLayoutNPWP)
    TextInputLayout inputLayoutNPWP;
    @BindView(R.id.title_ktp)
    TextView titleKtp;
    @BindView(R.id.noKtp)
    EditText noKtp;
    @BindView(R.id.fileKtp)
    Button fileKtp;
    @BindView(R.id.photoKtp)
    EditText photoKtp;
    @BindView(R.id.title_npwp)
    TextView titleNpwp;
    @BindView(R.id.noNpwp)
    EditText noNpwp;
    @BindView(R.id.fileNpwp)
    Button fileNpwp;
    @BindView(R.id.photoNpwp)
    EditText photoNpwp;
    @BindView(R.id.title_ktp2)
    TextView titleKtp2;
    @BindView(R.id.upload)
    Button upload;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Bitmap bitmap;
    private int PICK_IMAGE_KTP = 100;
    private int PICK_IMAGE_NPWP = 200;
    private int PICK_IMAGE_COVER = 300;
    private static final String TAG = RegisterAxi5Activity.class.getSimpleName();
    private static final int READ_REQUEST_CODE = 400;
    private Uri uri;
    MultipartBody.Part file_ktp, file_npwp, file_cover;
    RequestBody ktp_desc, npwp_desc, cover_desc;

    String fKtp, fNpwp, axiId;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_ktp);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upload KTP & NPWP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getBaseContext());

        axiId = session.getAxiId();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomorKtp = noKtp.getText().toString();
                String nomorNpwp = noNpwp.getText().toString();
                Log.d("KTPNYA", nomorKtp);
                Log.d("KTPNYA", nomorNpwp);
                Log.d("KTPNYA", fKtp);
                Log.d("KTPNYA", fNpwp);

                ApiService apiService = ApiClient.getClient().create(ApiService.class);

                Call<FotoKtpNpwp> postKtp = apiService.postFoto(axiId, fKtp, fNpwp, nomorKtp, nomorNpwp);
                postKtp.enqueue(new Callback<FotoKtpNpwp>() {
                    @Override
                    public void onResponse(Call<FotoKtpNpwp> call, Response<FotoKtpNpwp> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getBaseContext(),"Anda telah mengisi KTP dan NPWP.",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                        }else{
                            Toast.makeText(getBaseContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                            Log.d("dada", String.valueOf(response.code()));
//                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<FotoKtpNpwp> call, Throwable t) {
                        Log.d("KTPNYA", t.getMessage());
                    }
                });
            }
        });
    }

    @OnClick({R.id.fileKtp, R.id.fileNpwp})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.fileKtp:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih gambar"), PICK_IMAGE_KTP);
                break;
            case R.id.fileNpwp:
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih gambar"), PICK_IMAGE_NPWP);
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

//                    image_ktp.setImageBitmap(resizedBitmap);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fKtp = Helper.ConvertBitmapToString(resizedBitmap);
                    photoKtp.setText(fKtp);
//                    Log.d("REGISTER AXI:::", fileKtp);
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

//                    image_npwp.setImageBitmap(getResizedBitmap(bitmap, 350));
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    fNpwp = Helper.ConvertBitmapToString(resizedBitmap);
                    photoNpwp.setText(fNpwp);
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
}
