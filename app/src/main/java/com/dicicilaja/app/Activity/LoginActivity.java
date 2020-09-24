package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import com.dicicilaja.app.Inbox.UI.InboxActivity;
import com.dicicilaja.app.InformAXI.ui.InformAxiActivity;
import com.dicicilaja.app.BFF.API.Data.Login.Login;
import com.dicicilaja.app.BFF.API.Network.ApiClient;
import com.dicicilaja.app.BFF.API.Network.ApiService;
import com.dicicilaja.app.InformAXI.ui.InformSmActivity;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.app.Activity.Addon.CompletePhoneEmailActivity;
import com.dicicilaja.app.WebView.ForgotActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import com.dicicilaja.app.API.Interface.InterfaceNotifToken;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView lewati_text, subPassword,forgotPassword,powered,judulDaftarAkun,judulButuhBantuan,daftarAkun,butuhBantuan, tvForgot;
    private TextInputLayout inputLayoutEmailID, inputLayoutPassword;
    private EditText inputPassword, inputEmailID;
    private ProgressDialog progressDialog;
    private Button btnLogin;
    RelativeLayout lewati;

    String apiKey;

    InterfaceNotifToken interfaceNotifToken;

    SessionManager session;

    ApiService apiServiceLogin;
    String photo, zipcode, area;
    Boolean openInbox;

    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(LoginActivity.this);

        setContentView(R.layout.activity_login);
        if( session.isLoggedIn() ) {
            openInbox = getIntent().getBooleanExtra("openInbox", false);
            if(openInbox) {
                showWhichActivity(session.getRole(), openInbox);
            } else {
                showWhichActivity(session.getRole(), false);
            }
        } else {

            progress = new ProgressDialog(this);
            progress.setMessage("Mohon tunggu sebentar...");
            progress.setCanceledOnTouchOutside(false);

            /*if (session.isLoggedIn() == TRUE && session.getRole().equals("axi")) {
                Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("channel")) {
                Intent intent = new Intent(getBaseContext(), MaxiDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("crh")) {
                Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("cro")) {
                Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("tc")) {
                Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("admin")) {
                Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("spg")) {
                Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("mm")) {
                Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("bm")) {
                Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("ho")) {
                Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (session.isLoggedIn() == TRUE && session.getRole().equals("basic")) {
                Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {*/

            //            Instabug.setUserAttribute("USER_ID", null);
            //            Instabug.setUserAttribute("LOGIN", "False");

            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.colorWhite));
            }
            judulDaftarAkun = findViewById(R.id.judulDaftarAkun);
            daftarAkun = findViewById(R.id.daftarAkun);
            inputLayoutEmailID = findViewById(R.id.inputLayoutEmailID);
            inputLayoutPassword = findViewById(R.id.inputLayoutPassword);
            inputEmailID = findViewById(R.id.inputEmailID);
            inputPassword = findViewById(R.id.inputPassword);
            btnLogin = findViewById(R.id.btnLogin);
            progressDialog = new ProgressDialog(this);
            lewati = findViewById(R.id.lewati);
            lewati_text = findViewById(R.id.lewati_text);
            tvForgot = findViewById(R.id.tvForgot);

            inputEmailID.addTextChangedListener(new MyTextWatcher(inputEmailID));
            inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));


            apiServiceLogin = ApiClient.getClient().create(ApiService.class);

            final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            //Log.i("firebase_login", "token : "  + refreshedToken.toString());

            Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
            Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
            Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
            Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");
            inputEmailID.setTypeface(opensans_semibold);
            inputPassword.setTypeface(opensans_semibold);
            btnLogin.setTypeface(opensans_bold);
            judulDaftarAkun.setTypeface(opensans_reguler);
            daftarAkun.setTypeface(opensans_semibold);
            lewati_text.setTypeface(opensans_semibold);
            tvForgot.setTypeface(opensans_semibold);


            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSoftKeyboard();
                    String username = inputEmailID.getText().toString();
                    String password = inputPassword.getText().toString();
                    if(validateLogin(username, password)) {
                        progress.show();
                        doLogin(username, password);
                    }
                }
            });
            daftarAkun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            });
            tvForgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), ForgotActivity.class);
                    startActivity(intent);
                }
            });
            lewati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                    startActivity(intent);
                }
            });

        }

    }

    private boolean validateLogin(String username, String password) {
        if(username == null || username.trim().length() == 0) {
            progress.dismiss();
            hideSoftKeyboard();
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan username Anda");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
            return false;
        }
        if(password == null || password.trim().length() == 0) {
            progress.dismiss();
            hideSoftKeyboard();
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this);
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan password Anda");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username, final String password) {

        Call<Login> call = apiServiceLogin.login(username, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Log.d("log_login", "code: " + response.code() + " body: " + response.body());
                if(response.isSuccessful()) {
                    progress.dismiss();
                    Login resObj = response.body();
                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                    try {
                        photo = resObj.getPhoto();
                        zipcode = resObj.getZipcode();
                        area = resObj.getArea();
                    } catch (Exception ex) {
                        photo = "";
                        zipcode = "";
                        area = "";
                    }

                    session.createLoginSession(
                            resObj.getUserId(),
                            resObj.getNomorAxiId(),
                            resObj.getProfileId(),
                            String.valueOf(resObj.getUserIdOnesignal()),
                            resObj.getToken().getAccessToken(),
                            resObj.getRole(),
                            resObj.getName(),
                            photo,
                            resObj.getBranch(),
                            resObj.getBranchId(),
                            area,
                            String.valueOf(resObj.getAreaId()),
                            zipcode,
                            refreshedToken,
                            String.valueOf(resObj.getPhone()),
                            resObj.getEmail()
                    );

//                    Instabug.setUserAttribute("USER_ID", resObj.getUserId());
//                    Instabug.setUserAttribute("LOGIN", "True");

                    showWhichActivity(resObj.getRole(), false);

                    /*if (resObj.getRole().equals("axi")) {
                        Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("channel")) {
                        Intent intent = new Intent(getBaseContext(), MaxiDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("crh")) {
                        Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("cro")) {
                        Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("tc")) {
                        Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("admin")) {
                        Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("spg")) {
                        Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("bm")) {
                        Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("mm")) {
                        Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("ho")) {
                        Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if (resObj.getRole().equals("basic")) {
                        Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }*/
                }else {
                    progress.dismiss();
                    hideSoftKeyboard();
                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this);
                    String messageError = null;
                    try {
                        String error = response.errorBody().string();
                        messageError = error.trim();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage(messageError.substring(1, messageError.length() - 1));

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                progress.dismiss();
                hideSoftKeyboard();
                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Gagal terhubung ke server");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }


    private boolean validateEmailID() {
        if (inputEmailID.getText().toString().trim().isEmpty()) {
            inputLayoutEmailID.setError(getString(R.string.err_msg_username));
            requestFocus(inputEmailID);
            return false;
        } else {
            inputLayoutEmailID.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
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
                case R.id.inputEmailID:
                    validateEmailID();
                    break;

                case R.id.inputPassword:
                    validatePassword();
                    break;
            }
        }
    }

//    private void showNextActivity(String role) {
//        if( isLogin ) {
//            oneSignalSubscribe();
//
//            if( String.valueOf(session.getPhone()).isEmpty() || (session.getEmail() == null) ) {
//                Intent intent = new Intent( LoginActivity.this, CompletePhoneEmailActivity.class );
//
//                intent.putExtra("USER_ID", session.getUserId());
//                intent.putExtra("USER_PHONE", session.getPhone());
//                intent.putExtra("USER_EMAIL", session.getEmail());
//                intent.putExtra("USER_ROLE", session.getRole());
//
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                startActivity(intent);
//            } else {
//                showWhichActivity(role);
//            }
//        }
//    }

    private void showWhichActivity(String role, Boolean openInbox) {
        oneSignalSubscribe();
        role = role.toLowerCase();
        switch (role) {
            case "axi":
                Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                intent.putExtra("openInbox", openInbox);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
                break;

            case "sh":
                Intent intent2 = new Intent(getBaseContext(), InformAxiActivity.class);
                intent2.putExtra("openInbox", openInbox);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                break;

            case "sm":
                Intent intent3 = new Intent(getBaseContext(), InformSmActivity.class);
                intent3.putExtra("openInbox", openInbox);
                intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent3);
                break;

            case "nasabah":
                Intent intent4 = new Intent(getBaseContext(), MarketplaceActivity.class);
                intent4.putExtra("openInbox", openInbox);
                intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent4);
                break;

//            case "channel":
//                intent = new Intent(getBaseContext(), MaxiDashboardActivity.class);
//                break;

//            case "crh":
//                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
//                break;
//
//            case "cro":
//                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
//                break;
//
//            case "tc":
//                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
//                break;
//
//            case "admin":
//                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
//                break;
//
//            case "spg":
//                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
//                break;
//
//            case "bm":
//                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
//                break;
//
//            case "mm":
//                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
//                break;
//
//            case "ho":
//                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
//                break;

            default:
//                progress.dismiss();
                hideSoftKeyboard();
                androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(LoginActivity.this);
                alertDialog.setTitle("Perhatian");
                alertDialog.setMessage("Username atau password Anda salah");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();

                break;
        }
    }

    private void oneSignalSubscribe() {
        try {
            JSONObject tags = new JSONObject();
            tags.put("user_id", session.getUserIdOneSignal());
            tags.put("role", session.getRole());
            tags.put("profile_id", session.getProfileId());
            tags.put("axi_id", session.getNomorAxiId());
            OneSignal.sendTags(tags);
        } catch (Exception ex) {}

//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(false)
//                .init();
    }

}