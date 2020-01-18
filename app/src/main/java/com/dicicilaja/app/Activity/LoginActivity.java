package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import com.dicicilaja.app.API.Model.Login.Login;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.dicicilaja.app.Remote.ApiUtils;
import com.dicicilaja.app.Remote.UserFirebase;
import com.dicicilaja.app.Remote.UserService;
import com.dicicilaja.app.Session.SessionManager;

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

    UserService userService;
    UserFirebase userFirebase;
    String photo, zipcode, area;

    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(LoginActivity.this);

        progress = new ProgressDialog(this);
        progress.setMessage("Mohon tunggu sebentar...");
        progress.setCanceledOnTouchOutside(false);

        if( session.isLoggedIn() ) {

            showNextActivity(true, session.getRole());

        } else {


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
            judulDaftarAkun = (TextView) findViewById(R.id.judulDaftarAkun);
            daftarAkun = (TextView) findViewById(R.id.daftarAkun);
            inputLayoutEmailID = (TextInputLayout) findViewById(R.id.inputLayoutEmailID);
            inputLayoutPassword = (TextInputLayout) findViewById(R.id.inputLayoutPassword);
            inputEmailID = (EditText) findViewById(R.id.inputEmailID);
            inputPassword = (EditText) findViewById(R.id.inputPassword);
            btnLogin = (Button) findViewById(R.id.btnLogin);
            progressDialog = new ProgressDialog(this);
            lewati = findViewById(R.id.lewati);
            lewati_text = findViewById(R.id.lewati_text);
            tvForgot = (TextView) findViewById(R.id.tvForgot);

            inputEmailID.addTextChangedListener(new MyTextWatcher(inputEmailID));
            inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

            userService = ApiUtils.getUserService();
            userFirebase = ApiUtils.getUserFirebase();

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
            Toast.makeText(this, "Masukan Email/ID Anda", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Masukan Password Anda", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username, final String password) {

        Call<Login> call = userFirebase.login_token(username, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
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
                            area,
                            zipcode,
                            refreshedToken,
                            resObj.getPhone(),
                            resObj.getEmail()
                    );

//                    Instabug.setUserAttribute("USER_ID", resObj.getUserId());
//                    Instabug.setUserAttribute("LOGIN", "True");

                    showNextActivity(true, resObj.getRole());

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
                    Toast.makeText(LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
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


    private void requestFocus(View view) {
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
                case R.id.inputEmailID:
                    validateEmailID();
                    break;

                case R.id.inputPassword:
                    validatePassword();
                    break;
            }
        }
    }

    private void showNextActivity(boolean isLogin, String role) {
        if( isLogin ) {

            if( String.valueOf(session.getPhone()).isEmpty() || (session.getEmail() == null) ) {
                Intent intent = new Intent( LoginActivity.this, CompletePhoneEmailActivity.class );

                intent.putExtra("USER_ID", session.getUserId());
                intent.putExtra("USER_PHONE", session.getPhone());
                intent.putExtra("USER_EMAIL", session.getEmail());
                intent.putExtra("USER_ROLE", session.getRole());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            } else {
                showWhichActivity(role);
            }
        }
    }

    private void showWhichActivity( String role ) {
        role = role.toLowerCase();
        Intent intent;
        switch (role) {
            case "axi":
                intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                break;

            case "channel":
                intent = new Intent(getBaseContext(), MaxiDashboardActivity.class);
                break;

            case "crh":
                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                break;

            case "cro":
                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                break;

            case "tc":
                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                break;

            case "admin":
                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                break;

            case "spg":
                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                break;

            case "bm":
                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                break;

            case "mm":
                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                break;

            case "ho":
                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                break;

            case "basic":
                intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                break;

            default:
                intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                break;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}