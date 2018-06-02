package com.dicicilaja.dicicilaja.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import android.widget.Toast;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateRequest;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahAxi;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateRequest.CreateRequest;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Remote.ApiUtils;
import com.dicicilaja.dicicilaja.Session.SessionManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjukanPengajuanAxi2Activity extends AppCompatActivity {
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

    //Image request code
    private int PICK_IMAGE_KTP = 1;
    private int PICK_IMAGE_COLLETERAL = 2;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajukan_pengajuan_axi2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        requestStoragePermission();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        final SessionManager session = new SessionManager(getBaseContext());
        final String apiKey = "Bearer " + session.getToken();

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

        upload_ktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih gambar"), PICK_IMAGE_KTP);
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

        inputNama.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputNama));
        inputEmail.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputEmail));
        inputHp.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputHp));
        inputAlamat.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputAlamat));
        inputProvinsi.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputProvinsi));
        inputKota.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputKota));
        inputKecamatan.addTextChangedListener(new AjukanPengajuanAxi2Activity.MyTextWatcher(inputKecamatan));

//        inputNama.setHint(Html.fromHtml("Nama Nasabah <font color='#ff0000'>*</font>"));
//        inputEmail.setHint(Html.fromHtml("Email <font color='#ff0000'>*</font>"));
//        inputHp.setHint(Html.fromHtml("No. Handphone <font color='#ff0000'>*</font>"));
//        inputAlamat.setHint(Html.fromHtml("Alamat <font color='#ff0000'>*</font>"));
//        inputProvinsi.setHint(Html.fromHtml("Nama Provinsi <font color='#ff0000'>*</font>"));
//        inputKota.setHint(Html.fromHtml("Nama Kota <font color='#ff0000'>*</font>"));
//        inputKecamatan.setHint(Html.fromHtml("Nama Kecamatan <font color='#ff0000'>*</font>"));


        ajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    ktp_image = "http://dicicilaja.com/public/assets/images/not-found.jpg";
                    colleteral_image = "http://dicicilaja.com/public/assets/images/not-found.jpg";

                    if(validateForm(client_name, email, hp, alamat, provinsi, kota, kecamatan)) {
                        if(check.isChecked()) {
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

                            doRequest(apiKey, program_id, colleteral_id, status_id, manufacturer, year, tenor, amount, qty, area_id, branch_id, client_name, hp, address, district, city, province, email, ktp_image, colleteral_image);
                        }else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AjukanPengajuanAxi2Activity.this);
                            alertDialog.setMessage("Anda belum menyetujui syarat dan ketentuan yang berlaku. Silakan centang pada kotak yang tersedia.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    requestFocus(check);
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

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_KTP && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image_ktp.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(requestCode == PICK_IMAGE_COLLETERAL && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image_colleteral.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doRequest(final String apiKey, final String program_id, final String colleteral_id, final String status_id, final String manufacturer, final String year, final String tenor, final String amount, final String qty, final String area_id, final String branch_id, final String client_name, final String hp, final String address, final String district, final String city, final String province, final String email,  final String ktp_image, final String colleteral_image) {
        InterfaceCreateRequest apiService =
                NewRetrofitClient.getClient().create(InterfaceCreateRequest.class);

        Call<CreateRequest> call = apiService.assign(apiKey, program_id, colleteral_id, status_id, manufacturer, year, tenor, amount, qty, area_id, branch_id, client_name, hp, address, district, city, province, email, ktp_image, colleteral_image);
        call.enqueue(new Callback<CreateRequest>() {
            @Override
            public void onResponse(Call<CreateRequest> call, Response<CreateRequest> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getBaseContext(),"Selamat! Pengajuan Anda berhasil dibuat",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"code"+response.code(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateRequest> call, Throwable t) {
//                Toast.makeText(getBaseContext(),"code:"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
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