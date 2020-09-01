package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.dicicilaja.app.BuildConfig;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Boolean.TRUE;

public class RegisterAxiWebViewSHActivity extends AppCompatActivity {


    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    public static final int REQUEST_CAMERA_CAPTURE_FILE = 101;
    private final static int FILECHOOSER_RESULTCODE = 1;
    private File mFileFromCamera;
    SessionManager session;
    private Uri imageURI;
    private int PERMISSION_ALL = 2;
    private String[] PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi_webview_sh);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(RegisterAxiWebViewSHActivity.this);
        if(session.isLoggedIn() == TRUE){
            getSupportActionBar().setTitle("Daftarkan AXI");
        }


        final ProgressDialog pd = ProgressDialog.show(this, "", "Loading...",true);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient()
        {

            @Override
            public void onPageFinished(WebView view, String url) {
                if(pd!=null && pd.isShowing())
                {
                    pd.dismiss();
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient()
        {
            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }


            // For Lollipop 5.0+ Devices
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams)
            {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;
                if (!hasPermissions(RegisterAxiWebViewSHActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(RegisterAxiWebViewSHActivity.this, PERMISSIONS, PERMISSION_ALL);
                }

                try
                {
                    AlertDialog.Builder alertDialog7 = new AlertDialog.Builder(RegisterAxiWebViewSHActivity.this);
                    alertDialog7.setTitle("Perhatian");
                    alertDialog7.setMessage("Pilih foto dari Gallery atau foto dengan Kamera?");

                    alertDialog7.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = fileChooserParams.createIntent();
                            startActivityForResult(intent, REQUEST_SELECT_FILE);
                        }
                    });
                    alertDialog7.setNegativeButton("Kamera", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                processPickImage();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    alertDialog7.show();
                } catch (ActivityNotFoundException e)
                {
                    uploadMessage = null;
                    Toast.makeText(RegisterAxiWebViewSHActivity.this, "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
            {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
        });

        webView.loadUrl("https://dicicilaja.com/axi/register/mobile?isCrh=true");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Uri[] results = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }else if (requestCode == REQUEST_CAMERA_CAPTURE_FILE){
                if (uploadMessage == null)
                    return;
                results = new Uri[]{Uri.fromFile(mFileFromCamera)};
                uploadMessage.onReceiveValue(results);
                uploadMessage = null;
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else
            Toast.makeText(RegisterAxiWebViewSHActivity.this, "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_CAPTURE_FILE :

                try {
                    processPickImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String mFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File mFile = File.createTempFile(mFileName, ".jpg", storageDir);
        return mFile;
    }

    private void processPickImage() throws IOException {
        if (!hasPermissions(RegisterAxiWebViewSHActivity.this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(RegisterAxiWebViewSHActivity.this, PERMISSIONS, PERMISSION_ALL);
        }
        takeCameraPhoto();
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
//    private boolean hasCameraPermission() {
//        return ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
//    }
//    private boolean hasWritePermission() {
//        return ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    @TargetApi(Build.VERSION_CODES.M)
//    private void requestCameraPermission() {
//        requestPermissions(new String[]{Manifest.permission.CAMERA},
//                REQUEST_CAMERA_CAPTURE_FILE );
//    }
//    @TargetApi(Build.VERSION_CODES.M)
//    private void requestWritePermission() {
//        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                REQUEST_CAMERA_CAPTURE_FILE );
//    }

    public void takeCameraPhoto() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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
            mFileFromCamera = photoFile;

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_CAMERA_CAPTURE_FILE);
        }
    }

}
