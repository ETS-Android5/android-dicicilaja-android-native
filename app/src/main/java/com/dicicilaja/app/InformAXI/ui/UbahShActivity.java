package com.dicicilaja.app.InformAXI.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.dicicilaja.app.API.Client.ApiClient;
import com.dicicilaja.app.Activity.RegisterAxi1Activity;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahSh;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahCustomer;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahCustomer.UbahCustomer;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahShActivity extends AppCompatActivity {

    EditText inputNamaLengkap, inputHandphone, inputEmail;
    ImageView date_range;
    ProgressDialog progress;
    Button save;
    String apiKey;
    TextInputLayout inputLayoutNamaLengkap, inputLayoutHandphone, inputLayoutEmail;

    SessionManager session;
    String namaLengkap, handphone, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_sh);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        inputNamaLengkap    = findViewById(R.id.inputNamaLengkap);
        inputHandphone      = findViewById(R.id.inputHandphone);
        inputEmail      = findViewById(R.id.inputEmail);
        save                = findViewById(R.id.save);

        inputLayoutNamaLengkap  = findViewById(R.id.inputLayoutNamaLengkap);
        inputLayoutEmail  = findViewById(R.id.inputLayoutEmail);
        inputLayoutHandphone = findViewById(R.id.inputLayoutHandphone);

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);

        try {
            inputNamaLengkap.setText(getIntent().getStringExtra("name_user"));
            inputHandphone.setText(getIntent().getStringExtra("api_no_hp"));
            inputEmail.setText(getIntent().getStringExtra("api_email"));
        } catch (Exception ex) {}

        inputNamaLengkap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputNamaLengkap.removeTextChangedListener(this);
                validateName();
                inputNamaLengkap.addTextChangedListener(this);
            }
        });

        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputEmail.removeTextChangedListener(this);
                validateEmail();
                inputEmail.addTextChangedListener(this);
            }
        });

        inputHandphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputHandphone.removeTextChangedListener(this);
                validateHp();
                inputHandphone.addTextChangedListener(this);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    namaLengkap = inputNamaLengkap.getText().toString();
                    handphone = inputHandphone.getText().toString();
                    email = inputEmail.getText().toString();
                } catch (Exception ex) {

                }
                if(validateForm(namaLengkap, email, handphone)) {
                    progress.show();
                    ubahSh(apiKey, namaLengkap, email, handphone);

                }
            }
        });
    }

    private void ubahSh(final String apiKey, final String namaLengkap, final String email, final String handphone) {
        InterfaceUbahSh apiService =
                ApiClient.getClient().create(InterfaceUbahSh.class);

        Call<UbahCustomer> call = apiService.change(apiKey,namaLengkap, email, handphone);
        call.enqueue(new Callback<UbahCustomer>() {
            @Override
            public void onResponse(Call<UbahCustomer> call, Response<UbahCustomer> response) {
                if(response.isSuccessful()){
                    progress.dismiss();
                    session.editLoginSessionSh(namaLengkap, handphone, email);
                    Toast.makeText(getBaseContext(),"Data Anda berhasil diubah",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), InformAxiActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    progress.dismiss();
                    Toast.makeText(getBaseContext(),"Gagal mengubah data Anda, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UbahCustomer> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getBaseContext(),"Gagal mengubah data Anda, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private boolean validateForm(String namaLengkap, String email, String handphone) {
        if(namaLengkap == null || namaLengkap.trim().length() == 0 || namaLengkap.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahShActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaLengkap);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isName(namaLengkap)) {
            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(UbahShActivity.this);
            alertDialog5.setTitle("Perhatian");
            alertDialog5.setMessage("Masukan nama lengkap yang benar");

            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaLengkap);
                }
            });
            alertDialog5.show();
            return false;
        }

        if (email == null || email.trim().length() == 0 || email.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UbahShActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isEmailValid(email)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UbahShActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan alamat email dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        }

        if(handphone == null || handphone.trim().length() == 0 || handphone.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahShActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan no. handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputHandphone);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isHp(handphone)) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(UbahShActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan no. handphone dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputHandphone);
                }
            });
            alertDialog.show();
            return false;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            showSoftKeyboard(view);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static boolean isName(String alamat) {
        String expression = "^[a-z.'/ A-Z]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateName() {
        if (inputNamaLengkap.getText().toString().trim().isEmpty()) {
            inputLayoutNamaLengkap.setErrorEnabled(false);
        } else {
            String emailId = inputNamaLengkap.getText().toString();
            String expression = "^[a-z.'/ A-Z]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutNamaLengkap.setError("Nama Lengkap yang di input harus sesuai \ncontoh: Budi Susanto");
                requestFocus(inputNamaLengkap);
                return false;
            } else {
                inputLayoutNamaLengkap.setErrorEnabled(false);
            }
        }
        return true;
    }


    public static boolean isHp(String hp) {
        String expression = "^62[0-9]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(hp);
        return matcher.matches();
    }

    private boolean validateHp() {
        if (inputHandphone.getText().toString().trim().isEmpty()) {
            inputLayoutHandphone.setErrorEnabled(false);
        } else {
            String emailId = inputHandphone.getText().toString();
            String expression = "^62[0-9]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutHandphone.setError("Format nomor HP salah\ncontoh: 6281234567890");
                requestFocus(inputHandphone);
                return false;
            } else {
                inputLayoutHandphone.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateEmail() {
        if (inputEmail.getText().toString().trim().isEmpty()) {
            inputLayoutEmail.setErrorEnabled(false);
        } else {
            String emailId = inputEmail.getText().toString();
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutEmail.setError("Masukan alamat email dengan benar\ncontoh: budi.susanto@gmail.com");
                requestFocus(inputEmail);
                return false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
            }
        }
        return true;
    }
}
