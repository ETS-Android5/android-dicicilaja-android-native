package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import static java.lang.Boolean.TRUE;

public class AssignSurveyorWebViewActivity extends AppCompatActivity {

//    TextView titleSection, bodySection, detailSection, judulSudahPunyaAkun;
//    EditText inputAxiRefferal, inputNamaLengkap, inputEmail, inputNoHp, inputNamaIbu;
//    Button btnLanjut, btnDaftar;
//    SearchableSpinner spinnerArea, spinnerCabang;
//    InterfaceAreaBranch apiServiceArea;
//    com.dicicilaja.app.Remote.AreaService AreaService;
//    String apiKey, axi_id, nama, email, hp, namaibu, area, cabang;
//    TextInputLayout inputLayoutNamaLengkap, inputLayoutEmail, inputLayoutNoHp, inputLayoutNamaIbu;
//    String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
//    MaterialProgressBar progressBar;

//    final List<String> AREA_ITEMS = new ArrayList<>();
//    final HashMap<Integer, String> AREA_MAP = new HashMap<Integer, String>();
//    final List<String> CABANG_ITEMS = new ArrayList<>();
//    final HashMap<Integer, String> CABANG_MAP = new HashMap<Integer, String>();


    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi_webview);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(AssignSurveyorWebViewActivity.this);
        if(session.isLoggedIn() == TRUE){
            getSupportActionBar().setTitle("Assign Surveyor");
        }

//        progressBar = findViewById(R.id.progressBar);
//        inputNamaLengkap = findViewById(R.id.inputNamaLengkap);
//        inputEmail = findViewById(R.id.inputEmail);
//        inputNamaIbu = findViewById(R.id.inputNamaIbu);
//        inputAxiRefferal = findViewById(R.id.inputAxiRefferal);
//        inputNoHp = findViewById(R.id.inputNoHp);
//        titleSection = findViewById(R.id.titleSection);
//        bodySection = findViewById(R.id.bodySection);
//        detailSection = findViewById(R.id.detailSection);
//        btnLanjut = findViewById(R.id.btnLanjut);
//        btnDaftar = findViewById(R.id.btnDaftar);
//        spinnerArea = findViewById(R.id.spinnerArea);
//        spinnerCabang = findViewById(R.id.spinnerCabang);
//
//        inputLayoutNamaLengkap = findViewById(R.id.inputLayoutNamaLengkap);
//        inputLayoutEmail = findViewById(R.id.inputLayoutEmail);
//        inputLayoutNoHp = findViewById(R.id.inputLayoutNoHp);
//        inputLayoutNamaIbu = findViewById(R.id.inputLayoutNamaIbu);

//        try {
//            inputAxiRefferal.setText(session.getNomorAxiId());
//        }catch (Exception ex){
//
//        }

        final ProgressDialog pd = ProgressDialog.show(this, "", "Loading...",true);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);

//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDisplayZoomControls(false);

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

                Intent intent = fileChooserParams.createIntent();
                try
                {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e)
                {
                    uploadMessage = null;
                    Toast.makeText(AssignSurveyorWebViewActivity.this, "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
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

        if(session.getEmail() != null){
//            webView.loadUrl("https://dicicilaja.com/axi/register/mobile");
            webView.loadUrl("https://dicicilaja.com/crh/orderin?crhEmail=" + session.getEmail());
        }else{
//            webView.loadUrl("https://dicicilaja.com/axi/register/mobile");
        }


//        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
//        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
//        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
//        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");
//
//        titleSection.setTypeface(opensans_bold);
//        bodySection.setTypeface(opensans_reguler);
//        detailSection.setTypeface(opensans_semibold);
//        btnLanjut.setTypeface(opensans_bold);

//        initAction();
//        initLoadData();

//        inputNamaLengkap.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                inputNamaLengkap.removeTextChangedListener(this);
//                validateName();
//                inputNamaLengkap.addTextChangedListener(this);
//            }
//        });
//
//        inputEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                inputEmail.removeTextChangedListener(this);
//                validateEmail();
//                inputEmail.addTextChangedListener(this);
//            }
//        });
//
//        inputNoHp.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                inputNoHp.removeTextChangedListener(this);
//                validateHp();
//                inputNoHp.addTextChangedListener(this);
//            }
//        });
//
//        inputNamaIbu.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                inputNamaIbu.removeTextChangedListener(this);
//                validateMomName();
//                inputNamaIbu.addTextChangedListener(this);
//            }
//        });

    }

//    private boolean validateForm(String nama, String email, String hp, String namaibu, String area, String cabang) {
//        if (area == null || area.trim().length() == 0 || area.equals("0")) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Pilih area pengajuan");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(spinnerArea);
//                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
//                    spinnerArea.dispatchTouchEvent(motionEvent);
//                    hideSoftKeyboard();
//                }
//            });
//            alertDialog.show();
//            return false;
//        }
//
//        if (cabang == null || cabang.trim().length() == 0 || cabang.equals("0")) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Pilih cabang pengajuan");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(spinnerCabang);
//                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
//                    spinnerCabang.dispatchTouchEvent(motionEvent);
//                    hideSoftKeyboard();
//                }
//            });
//            alertDialog.show();
//            return false;
//        }
//        if (nama == null || nama.trim().length() == 0 || nama.equals("0")) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Masukan nama lengkap");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(inputNamaLengkap);
//                }
//            });
//            alertDialog.show();
//            return false;
//        } else if (!isName(nama)) {
//            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog5.setTitle("Perhatian");
//            alertDialog5.setMessage("Masukan nama lengkap yang benar");
//
//            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(inputNamaLengkap);
//                }
//            });
//            alertDialog5.show();
//            return false;
//        }
//
//        if (email == null || email.trim().length() == 0 || email.equals("0")) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Masukan email");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(inputEmail);
//                }
//            });
//            alertDialog.show();
//            return false;
//        } else if (!isEmailValid(email)) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Masukan alamat email dengan benar");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(inputEmail);
//                }
//            });
//            alertDialog.show();
//            return false;
//        }
//
//        if (hp == null || hp.trim().length() == 0 || hp.equals("0")) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Masukan nomor handphone");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(inputNoHp);
//                }
//            });
//            alertDialog.show();
//            return false;
//        } else if (!isHp(hp)) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Masukan nomor handphone dengan benar");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(inputNoHp);
//                }
//            });
//            alertDialog.show();
//            return false;
//        }
//
//        if (namaibu == null || namaibu.trim().length() == 0 || namaibu.equals("0")) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog.setTitle("Perhatian");
//            alertDialog.setMessage("Masukan nama ibu kandung");
//
//            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(inputNamaIbu);
//                }
//            });
//            alertDialog.show();
//            return false;
//        } else if (!isName(namaibu)) {
//            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//            alertDialog5.setTitle("Perhatian");
//            alertDialog5.setMessage("Masukan nama ibu kandung yang benar");
//
//            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    requestFocus(inputNamaIbu);
//                }
//            });
//            alertDialog5.show();
//            return false;
//        }
//        return true;
//    }
//
//    public void requestFocus(View view) {
//        if (view.requestFocus()) {
//            showSoftKeyboard(view);
//        }
//    }
//
//    public void showSoftKeyboard(View view) {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        view.requestFocus();
//        inputMethodManager.showSoftInput(view, 0);
//    }
//
//    public void hideSoftKeyboard() {
//        if (getCurrentFocus() != null) {
//            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        }
//    }
//
//    private void initAction() {
//        //Initialize
//        progressBar.setVisibility(View.GONE);
//        spinnerCabang.setEnabled(false);
//        clearArea();
//
//        //Network
//        apiServiceArea = ApiClient2.getClient().create(InterfaceAreaBranch.class);
//    }
//
//    private void clearArea() {
//        AREA_MAP.clear();
//        AREA_ITEMS.clear();
//        AREA_MAP.put(0, "0");
//        AREA_ITEMS.add("Pilih Area");
//        ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(RegisterAxiActivityWebView.this, android.R.layout.simple_spinner_item, AREA_ITEMS);
//        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerArea.setAdapter(area_adapter);
//        spinnerArea.setTitle("");
//        spinnerArea.setPositiveButton("OK");
//    }
//
//    private void clearCabang() {
//        CABANG_MAP.clear();
//        CABANG_ITEMS.clear();
//        CABANG_MAP.put(0, "0");
//        CABANG_ITEMS.add("Pilih Cabang");
//        ArrayAdapter<String> cabang_adapter = new ArrayAdapter<String>(RegisterAxiActivityWebView.this, android.R.layout.simple_spinner_item, CABANG_ITEMS);
//        cabang_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCabang.setAdapter(cabang_adapter);
//        spinnerCabang.setTitle("");
//        spinnerCabang.setPositiveButton("OK");
//        spinnerCabang.setEnabled(false);
//    }
//
//    private void initLoadData() {
//        progressBar.setVisibility(View.VISIBLE);
//        Call<Area> callArea = apiServiceArea.getArea(apiKey);
//        callArea.enqueue(new Callback<Area>() {
//            @Override
//            public void onResponse(Call<Area> call, Response<Area> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        if (response.body().getData().size() > 0) {
//                            for (int i = 0; i < response.body().getData().size(); i++) {
//                                AREA_MAP.put(Integer.valueOf(response.body().getData().get(i).getId()), response.body().getData().get(i).getId().toString());
//                                AREA_ITEMS.add(response.body().getData().get(i).getAttributes().getNama());
//                            }
//                            progressBar.setVisibility(View.GONE);
//                        } else {
//                            clearArea();
//                            progressBar.setVisibility(View.GONE);
//                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//                            alertDialog.setTitle("Perhatian");
//                            alertDialog.setMessage("Data area belum tersedia, silahkan coba beberapa saat lagi.");
//
//                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
//                                    startActivity(getIntent());
//                                }
//                            });
//                            alertDialog.show();
//
//                        }
//                    } catch (Exception ex) {
//                    }
//
//                    ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(RegisterAxiActivityWebView.this, android.R.layout.simple_spinner_item, AREA_ITEMS);
//                    area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinnerArea.setAdapter(area_adapter);
//                    spinnerArea.setTitle("");
//                    spinnerArea.setPositiveButton("OK");
//                    spinnerArea.setEnabled(true);
//
//                } else {
//                    clearArea();
//                    progressBar.setVisibility(View.GONE);
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//                    alertDialog.setTitle("Perhatian");
//                    alertDialog.setMessage("Data area belum tersedia, silahkan coba beberapa saat lagi.");
//
//                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                            startActivity(getIntent());
//                        }
//                    });
//                    alertDialog.show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Area> call, Throwable t) {
//                clearArea();
//                progressBar.setVisibility(View.GONE);
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//                alertDialog.setTitle("Perhatian");
//                alertDialog.setMessage("Data area belum tersedia, silahkan coba beberapa saat lagi.");
//
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                        startActivity(getIntent());
//                    }
//                });
//                alertDialog.show();
//            }
//        });
//
//        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                clearCabang();
//                if (Integer.parseInt(AREA_MAP.get(spinnerArea.getSelectedItemPosition())) > 0) {
//                    progressBar.setVisibility(View.VISIBLE);
//                    Call<Branch> callBranch = apiServiceArea.getBranch(apiKey, AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
//                    callBranch.enqueue(new Callback<Branch>() {
//                        @Override
//                        public void onResponse(Call<Branch> call, Response<Branch> response) {
//                            if (response.isSuccessful()) {
//                                try {
//                                    if (response.body().getData().size() > 0) {
//                                        for (int i = 0; i < response.body().getData().size(); i++) {
//                                            CABANG_MAP.put(i + 1, response.body().getData().get(i).getId());
//                                            CABANG_ITEMS.add(response.body().getData().get(i).getAttributes().getNama());
//                                        }
//                                        progressBar.setVisibility(View.GONE);
//                                    } else {
//                                        clearCabang();
//                                        progressBar.setVisibility(View.GONE);
//                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//                                        alertDialog.setTitle("Perhatian");
//                                        alertDialog.setMessage("Data cabang belum tersedia, silahkan coba beberapa saat lagi.");
//
//                                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                finish();
//                                                startActivity(getIntent());
//                                            }
//                                        });
//                                        alertDialog.show();
//                                    }
//                                } catch (Exception ex) {
//                                }
//
//                                ArrayAdapter<String> branch_adapter = new ArrayAdapter<String>(RegisterAxiActivityWebView.this, android.R.layout.simple_spinner_item, CABANG_ITEMS);
//                                branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                spinnerCabang.setEnabled(true);
//                                spinnerCabang.setAdapter(branch_adapter);
//                            } else {
//                                clearCabang();
//                                progressBar.setVisibility(View.GONE);
//                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//                                alertDialog.setTitle("Perhatian");
//                                alertDialog.setMessage("Data cabang belum tersedia, silahkan coba beberapa saat lagi.");
//
//                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
//                                        startActivity(getIntent());
//                                    }
//                                });
//                                alertDialog.show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Branch> call, Throwable t) {
//                            clearCabang();
//                            progressBar.setVisibility(View.GONE);
//                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterAxiActivityWebView.this);
//                            alertDialog.setTitle("Perhatian");
//                            alertDialog.setMessage("Data cabang belum tersedia, silahkan coba beberapa saat lagi.");
//
//                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
//                                    startActivity(getIntent());
//                                }
//                            });
//                            alertDialog.show();
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                AREA_ITEMS.clear();
//                AREA_MAP.clear();
//                CABANG_MAP.clear();
//                CABANG_ITEMS.clear();
//                spinnerCabang.setEnabled(false);
//            }
//
//        });
//
//        btnLanjut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    axi_id = inputAxiRefferal.getText().toString();
//                    nama = inputNamaLengkap.getText().toString();
//                    email = inputEmail.getText().toString();
//                    hp = inputNoHp.getText().toString();
//                    namaibu = inputNamaIbu.getText().toString();
//                    area = AREA_MAP.get(spinnerArea.getSelectedItemPosition());
//                    cabang = CABANG_MAP.get(spinnerCabang.getSelectedItemPosition());
//                } catch (Exception ex) {
//
//                }
//
//                if (validateForm(nama, email, hp, namaibu, area, cabang)) {
//                    Log.d("ajukanpengajuan", "nama : " + nama);
//
//
//                    Intent intent = new Intent(RegisterAxiActivityWebView.this, RegisterAxi2Activity.class);
//                    intent.putExtra("apiKey", apiKey);
//                    intent.putExtra("axi_id", axi_id);
//                    intent.putExtra("nama", nama);
//                    intent.putExtra("email", email);
//                    intent.putExtra("hp", hp);
//                    intent.putExtra("namaibu", namaibu);
//                    intent.putExtra("area", AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
//                    intent.putExtra("cabang", CABANG_MAP.get(spinnerCabang.getSelectedItemPosition()));
//                    startActivity(intent);
//                }
//
//            }
//        });
//        detailSection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RegisterAxiActivityWebView.this, AboutAxiActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    public static boolean isName(String alamat) {
//        String expression = "^[a-z.'/ A-Z]+$";
//        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(alamat);
//        return matcher.matches();
//    }
//
//    private boolean validateName() {
//        if (inputNamaLengkap.getText().toString().trim().isEmpty()) {
//            inputLayoutNamaLengkap.setErrorEnabled(false);
//        } else {
//            String emailId = inputNamaLengkap.getText().toString();
//            String expression = "^[a-z.'/ A-Z]+$";
//            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//            Matcher matcher = pattern.matcher(emailId);
//            Boolean isValid = matcher.matches();
//            if (!isValid) {
//                inputLayoutNamaLengkap.setError("Nama Lengkap yang di input harus sesuai \ncontoh: Budi Susanto");
//                requestFocus(inputNamaLengkap);
//                return false;
//            } else {
//                inputLayoutNamaLengkap.setErrorEnabled(false);
//            }
//        }
//        return true;
//    }
//
//    private boolean validateMomName() {
//        if (inputNamaIbu.getText().toString().trim().isEmpty()) {
//            inputLayoutNamaIbu.setErrorEnabled(false);
//        } else {
//            String emailId = inputNamaIbu.getText().toString();
//            String expression = "^[a-z.'/ A-Z]+$";
//            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//            Matcher matcher = pattern.matcher(emailId);
//            Boolean isValid = matcher.matches();
//            if (!isValid) {
//                inputLayoutNamaIbu.setError("Masukan nama ibu kandung dengan benar\ncontoh: Susi Susanti");
//                requestFocus(inputNamaIbu);
//                return false;
//            } else {
//                inputLayoutNamaIbu.setErrorEnabled(false);
//            }
//        }
//        return true;
//    }
//
//    public static boolean isEmailValid(String email) {
//        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
//        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    private boolean validateEmail() {
//        if (inputEmail.getText().toString().trim().isEmpty()) {
//            inputLayoutEmail.setErrorEnabled(false);
//        } else {
//            String emailId = inputEmail.getText().toString();
//            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
//            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//            Matcher matcher = pattern.matcher(emailId);
//            Boolean isValid = matcher.matches();
//            if (!isValid) {
//                inputLayoutEmail.setError("Masukan alamat email dengan benar\ncontoh: budi.susanto@gmail.com");
//                requestFocus(inputEmail);
//                return false;
//            } else {
//                inputLayoutEmail.setErrorEnabled(false);
//            }
//        }
//        return true;
//    }
//
//    public static boolean isHp(String hp) {
//        String expression = "^62[0-9]+$";
//        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(hp);
//        return matcher.matches();
//    }
//
//    private boolean validateHp() {
//        if (inputNoHp.getText().toString().trim().isEmpty()) {
//            inputLayoutNoHp.setErrorEnabled(false);
//        } else {
//            String emailId = inputNoHp.getText().toString();
//            String expression = "^62[0-9]+$";
//            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//            Matcher matcher = pattern.matcher(emailId);
//            Boolean isValid = matcher.matches();
//            if (!isValid) {
//                inputLayoutNoHp.setError("Format nomor HP salah\ncontoh: 6281234567890");
//                requestFocus(inputNoHp);
//                return false;
//            } else {
//                inputLayoutNoHp.setErrorEnabled(false);
//            }
//        }
//        return true;
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
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
            Toast.makeText(AssignSurveyorWebViewActivity.this, "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }
}
