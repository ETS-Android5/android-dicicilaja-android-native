package com.dicicilaja.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import com.dicicilaja.app.API.Client.ApiClient2;
import com.dicicilaja.app.API.Client.ApiProd;
import com.dicicilaja.app.API.Client.RetrofitClient2;
import com.dicicilaja.app.API.Interface.InterfaceAreaBank;
import com.dicicilaja.app.API.Model.AreaBankRequest.Bank.Bank;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceKodeBank;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.KodeBank.KodeBank;
import com.dicicilaja.app.OrderIn.Network.ApiService2;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;

public class RegisterAxi3Activity extends AppCompatActivity {

    Button btnLanjut;
    TextView title;
    SessionManager session;
    EditText inputNamaBank, inputNoRekening, inputCabangBank, inputANRekening, inputKotaBank;
    TextInputLayout inputLayoutNamaBank, inputLayoutNoRekening, inputLayoutCabangBank, inputLayoutANRekening, inputLayoutKotaBank;
    String apiKey,axi_id, nama, email, hp, namaibu, area, cabang;
    String no_ktp_pasangan, nama_pasangan, no_ktp, tempat_lahir, tanggal, alamat, rt, rw, kode_bank, kelurahan, kecamatan, kota, provinsi, kodepos, jk, status;
    String nama_bank, no_rekening, cabang_bank, an_rekening, kota_bank;
    SearchableSpinner spinnerBank;

    MaterialProgressBar progressBar;

    InterfaceAreaBank apiServiceArea;
    InterfaceKodeBank apiServiceBank;

    final List<String> BANK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> BANK_DATA = new HashMap<Integer, String>();
    final HashMap<Integer, String> BANK_NAME = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(RegisterAxi3Activity.this);
        if(session.isLoggedIn() == TRUE){
            getSupportActionBar().setTitle("Tambah Rekan Bisnis");
        }

        title                   = findViewById(R.id.title);
        inputNoRekening         = findViewById(R.id.inputNoRekening);
        inputCabangBank         = findViewById(R.id.inputCabangBank);
        inputANRekening         = findViewById(R.id.inputANRekening);
        inputKotaBank           = findViewById(R.id.inputKotaBank);
        inputLayoutNamaBank     = findViewById(R.id.inputLayoutNamaBank);
        inputLayoutNoRekening   = findViewById(R.id.inputLayoutNoRekening);
        inputLayoutCabangBank   = findViewById(R.id.inputLayoutCabangBank);
        inputLayoutANRekening   = findViewById(R.id.inputLayoutANRekening);
        inputLayoutKotaBank     = findViewById(R.id.inputLayoutKotaBank);
        spinnerBank             = findViewById(R.id.spinnerBank);
        progressBar             = findViewById(R.id.progressBar);

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
        rt            = getIntent().getStringExtra("rt");
        rw            = getIntent().getStringExtra("rw");
        kelurahan       = getIntent().getStringExtra("kelurahan");
        kecamatan       = getIntent().getStringExtra("kecamatan");
        kota            = getIntent().getStringExtra("kota");
        provinsi        = getIntent().getStringExtra("provinsi");
        kodepos         = getIntent().getStringExtra("kodepos");
        jk              = getIntent().getStringExtra("jk");
        status          = getIntent().getStringExtra("status");
        nama_pasangan          = getIntent().getStringExtra("nama_pasangan");
        no_ktp_pasangan          = getIntent().getStringExtra("no_ktp_pasangan");


        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");



        btnLanjut = findViewById(R.id.btnLanjut);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    nama_bank   = BANK_NAME.get(spinnerBank.getSelectedItemPosition());
                    kode_bank   = BANK_DATA.get(spinnerBank.getSelectedItemPosition());
                    no_rekening = inputNoRekening.getText().toString();
                    cabang_bank = inputCabangBank.getText().toString();
                    an_rekening = inputANRekening.getText().toString();
                    kota_bank   = inputKotaBank.getText().toString();
                } catch (Exception ex) {

                }
                if(validateForm(nama_bank, no_rekening, cabang_bank, an_rekening, kota_bank)) {
                    Intent intent = new Intent(getBaseContext(), RegisterAxi4Activity.class);
                    intent.putExtra("apiKey",apiKey);
                    intent.putExtra("axi_id",axi_id);
                    intent.putExtra("nama",nama);
                    intent.putExtra("email",email);
                    intent.putExtra("hp", hp);
                    intent.putExtra("namaibu", namaibu);
                    intent.putExtra("area", area);
                    intent.putExtra("cabang", cabang);
                    intent.putExtra("no_ktp",no_ktp);
                    intent.putExtra("tempat_lahir",tempat_lahir);
                    intent.putExtra("tanggal",tanggal);
                    intent.putExtra("alamat",alamat);
                    intent.putExtra("rt",rt);
                    intent.putExtra("rw",rw);
                    intent.putExtra("kode_bank",kode_bank);
                    intent.putExtra("kelurahan",kelurahan);
                    intent.putExtra("kecamatan",kecamatan);
                    intent.putExtra("kota",kota);
                    intent.putExtra("provinsi",provinsi);
                    intent.putExtra("kodepos",kodepos);
                    intent.putExtra("jk",jk);
                    intent.putExtra("status",status);
                    intent.putExtra("nama_bank",nama_bank);
                    intent.putExtra("no_rekening",no_rekening);
                    intent.putExtra("cabang_bank",cabang_bank);
                    intent.putExtra("an_rekening",an_rekening);
                    intent.putExtra("kota_bank",kota_bank);
                    intent.putExtra("nama_pasangan",nama_pasangan);
                    intent.putExtra("no_ktp_pasangan",no_ktp_pasangan);
                    startActivity(intent);
                }
            }
        });

        clearBank();
        //Network
        apiServiceArea = RetrofitClient2.getClient().create(InterfaceAreaBank.class);
        apiServiceBank = ApiProd.getClient().create(InterfaceKodeBank.class);
        Call<KodeBank> call = apiServiceBank.getKodeBank();
        call.enqueue(new Callback<KodeBank>() {
            @Override
            public void onResponse(Call<KodeBank> call, Response<KodeBank> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getData().size() > 0) {
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                BANK_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
                                BANK_NAME.put(i + 1, String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                                BANK_ITEMS.add(String.valueOf(response.body().getData().get(i).getAttributes().getNama()));
                            }
                            progressBar.setVisibility(View.GONE);
                        } else {
                            clearBank();
                            progressBar.setVisibility(View.GONE);
                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi3Activity.this);
                            alertDialog.setTitle("Perhatian");
                            alertDialog.setMessage("Data bank gagal dipanggil, silahkan coba beberapa saat lagi.");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    startActivity(getIntent());
                                }
                            });
                            alertDialog.show();
                        }
                    } catch (Exception ex) {
                    }

                    ArrayAdapter<String> bank_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, BANK_ITEMS);
                    bank_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerBank.setAdapter(bank_adapter);
                    spinnerBank.setTitle("");
                    spinnerBank.setPositiveButton("OK");
                    spinnerBank.setEnabled(true);
                } else {
                    clearBank();
                    progressBar.setVisibility(View.GONE);
                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi3Activity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data bank gagal dipanggil, silahkan coba beberapa saat lagi.");

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
            public void onFailure(Call<KodeBank> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi3Activity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Data bank gagal dipanggil, silahkan coba beberapa saat lagi.");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                alertDialog.show();
            }
        });

        inputCabangBank.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputCabangBank.removeTextChangedListener(this);
                validateIsCabangBank();
                inputCabangBank.addTextChangedListener(this);
            }
        });

        inputANRekening.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputANRekening.removeTextChangedListener(this);
                validateIsANRekening();
                inputANRekening.addTextChangedListener(this);
            }
        });

        inputKotaBank.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputKotaBank.removeTextChangedListener(this);
                validateIsKotaBank();
                inputKotaBank.addTextChangedListener(this);
            }
        });

    }
    private boolean validateForm(String nama_bank, String no_rekening, String cabang_bank, String an_rekening, String kota_bank) {
        if (nama_bank == null || nama_bank.trim().length() == 0 || nama_bank.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nama bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerBank);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerBank.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (no_rekening == null || no_rekening.trim().length() == 0 || no_rekening.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan no.rekening");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoRekening);
                }
            });
            alertDialog.show();
            return false;
        }

        if (cabang_bank == null || cabang_bank.trim().length() == 0 || cabang_bank.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan cabang bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputCabangBank);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isCabangBank(cabang_bank)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan cabang bank dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputCabangBank);
                }
            });
            alertDialog.show();
            return false;
        }

        if (an_rekening == null || an_rekening.trim().length() == 0 || an_rekening.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan a.n rekening");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputANRekening);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isANRekening(an_rekening)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan a/n rekening dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputANRekening);
                }
            });
            alertDialog.show();
            return false;
        }

        if (kota_bank == null || kota_bank.trim().length() == 0 || kota_bank.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan kota bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKotaBank);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isKotaBank(kota_bank)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxi3Activity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan kota bank dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKotaBank);
                }
            });
            alertDialog.show();
            return false;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }


    private void clearBank() {
        BANK_DATA.clear();
        BANK_NAME.clear();
        BANK_ITEMS.clear();
        BANK_DATA.put(0, "0");
        BANK_NAME.put(0, "");
        BANK_ITEMS.add("Pilih Bank");
        ArrayAdapter<String> bank_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, BANK_ITEMS);
        bank_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBank.setAdapter(bank_adapter);
        spinnerBank.setTitle("");
        spinnerBank.setPositiveButton("OK");
        spinnerBank.setEnabled(false);
    }

    public static boolean isCabangBank(String alamat) {
        String expression = "^[a-z.'/ A-Z]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateIsCabangBank() {
        if (inputCabangBank.getText().toString().trim().isEmpty()) {
            inputLayoutCabangBank.setErrorEnabled(false);
        } else {
            String emailId = inputCabangBank.getText().toString();
            String expression = "^[a-z.'/ A-Z]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutCabangBank.setError("Format cabang bank salah");
                requestFocus(inputCabangBank);
                return false;
            } else {
                inputLayoutCabangBank.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isANRekening(String alamat) {
        String expression = "^[a-z.'/ A-Z]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateIsANRekening() {
        if (inputANRekening.getText().toString().trim().isEmpty()) {
            inputLayoutANRekening.setErrorEnabled(false);
        } else {
            String emailId = inputANRekening.getText().toString();
            String expression = "^[a-z.'/ A-Z]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutANRekening.setError("Format atas nama rekening salah");
                requestFocus(inputANRekening);
                return false;
            } else {
                inputLayoutANRekening.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isKotaBank(String alamat) {
        String expression = "^[a-z.'/ A-Z]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateIsKotaBank() {
        if (inputKotaBank.getText().toString().trim().isEmpty()) {
            inputLayoutKotaBank.setErrorEnabled(false);
        } else {
            String emailId = inputKotaBank.getText().toString();
            String expression = "^[a-z.'/ A-Z]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutKotaBank.setError("Format kota bank salah");
                requestFocus(inputKotaBank);
                return false;
            } else {
                inputLayoutKotaBank.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static String toTitleCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

}
