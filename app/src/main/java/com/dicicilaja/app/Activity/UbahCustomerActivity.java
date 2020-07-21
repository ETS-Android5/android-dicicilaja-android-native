package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.dicicilaja.app.API.Client.ApiBff;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahCustomer;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahCustomer.UbahCustomer;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahCustomerActivity extends AppCompatActivity {

    EditText inputNamaLengkap, inputHandphone, inputBill;
    SearchableSpinner spinnerJenisKelamin;
    ImageView date_range;
    ProgressDialog progress;
    Button save;
    String apiKey;
    TextInputLayout inputLayoutNamaLengkap, inputLayoutHandphone, inputLayoutJenisKelamin, inputLayoutBill;

    SessionManager session;
    String namaLengkap, handphone, alamat, kelurahan, kecamatan, kota, provinsi, bill, jk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_customer);

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
        inputBill           = findViewById(R.id.inputBill);
        save                = findViewById(R.id.save);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);

        inputLayoutNamaLengkap  = findViewById(R.id.inputLayoutNamaLengkap);
        inputLayoutHandphone = findViewById(R.id.inputLayoutHandphone);
        inputLayoutJenisKelamin = findViewById(R.id.inputLayoutJenisKelamin);
        inputLayoutBill = findViewById(R.id.inputLayoutBill);

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);

        final List<String> JK_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JK_DATA = new HashMap<Integer, String>();

        JK_ITEMS.clear();
        JK_DATA.clear();

        JK_DATA.put(0, "0");
        JK_DATA.put(1, "l");
        JK_DATA.put(2, "p");
        JK_ITEMS.add("Pilih Jenis Kelamin");
        JK_ITEMS.add("Laki-laki");
        JK_ITEMS.add("Perempuan");

        ArrayAdapter<String> jk_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JK_ITEMS);
        jk_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(jk_adapter);
        spinnerJenisKelamin.setTitle("");
        spinnerJenisKelamin.setPositiveButton("OK");

        try {
            inputNamaLengkap.setText(getIntent().getStringExtra("name_user"));
            inputHandphone.setText(getIntent().getStringExtra("api_handphone"));
            inputBill.setText(getIntent().getStringExtra("api_bill"));
            if (getIntent().getStringExtra("api_jk").equals("l")) {
                spinnerJenisKelamin.setSelection(1);
            } else if (getIntent().getStringExtra("api_jk").equals("p")) {
                spinnerJenisKelamin.setSelection(2);
            }
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
                    jk = JK_DATA.get(spinnerJenisKelamin.getSelectedItemPosition());
                    handphone = inputHandphone.getText().toString();
                    bill = inputBill.getText().toString();
                } catch (Exception ex) {

                }
                if(validateForm(namaLengkap, handphone, jk, bill)) {
                    progress.show();
                    ubahCustomer(apiKey, namaLengkap, handphone, jk, bill);

                }
            }
        });
    }

    private void ubahCustomer(final String apiKey, final String namaLengkap, final String handphone, final String jk, final String bill) {
        InterfaceUbahCustomer apiService =
                ApiBff.getClient().create(InterfaceUbahCustomer.class);

        Call<UbahCustomer> call = apiService.change(apiKey,namaLengkap, handphone, jk, bill);
        call.enqueue(new Callback<UbahCustomer>() {
            @Override
            public void onResponse(Call<UbahCustomer> call, Response<UbahCustomer> response) {
                if(response.isSuccessful()){
                    progress.dismiss();
                    session.editLoginSessionCustomer(namaLengkap, handphone);
                    Toast.makeText(getBaseContext(),"Data Anda berhasil diubah",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                    intent.putExtra("profile","profile");
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

    private boolean validateForm(String namaLengkap, String handphone, String jk, String bill) {
        if(namaLengkap == null || namaLengkap.trim().length() == 0 || namaLengkap.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
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
            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(UbahCustomerActivity.this);
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

        if(handphone == null || handphone.trim().length() == 0 || handphone.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(UbahCustomerActivity.this);
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

        if(jk == null || jk.trim().length() == 0 || jk.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Pilih jenis kelamin");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJenisKelamin);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerJenisKelamin.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if(bill == null || bill.trim().length() == 0 || bill.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan no. tagihan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputBill);
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
}
