package id.variable.dicicilaja.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import id.variable.dicicilaja.API.Interface.InterfaceNotifToken;
import id.variable.dicicilaja.API.Interface.InterfaceSurveyFinish;
import id.variable.dicicilaja.Model.ResObj;
import id.variable.dicicilaja.Model.ResRequestProcess;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Remote.UserFirebase;
import id.variable.dicicilaja.Remote.UserService;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;

public class LoginActivity extends AppCompatActivity {

    private TextView lewati_text, subPassword,forgotPassword,powered,judulDaftarAkun,judulButuhBantuan,daftarAkun,butuhBantuan, tvForgot;
    private TextInputLayout inputLayoutEmailID, inputLayoutPassword;
    private EditText inputPassword, inputEmailID;
    private ProgressDialog progressDialog;
    private Button btnLogin;
    RelativeLayout lewati;

    InterfaceNotifToken interfaceNotifToken;

    SessionManager session;

    UserService userService;
    UserFirebase userFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(LoginActivity.this);

        if (session.isLoggedIn() == TRUE) {
            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
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
            Log.i("firebase_login", "token : "  + refreshedToken.toString());

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
                        doLogin(username, password, refreshedToken);
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
                    Intent intent = new Intent(getBaseContext(), ForgotPasswordActivity.class);
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

    private void doLogin(final String username, final String password, final String firebase_token) {
        Call<ResObj> call = userFirebase.login_token(username, password, firebase_token);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if(response.isSuccessful()) {
                    ResObj resObj = response.body();

                    try {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                        session.createLoginSession(resObj.getUserId(), resObj.getToken().getAccessToken(), resObj.getRole(), resObj.getName(), resObj.getPhoto(), resObj.getArea(), resObj.getBranch(), resObj.getZipcode(), refreshedToken);

                        Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                        startActivity(intent);
                        finish();
//                        if(resObj.getRole().equals("axi")) {
//                            Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }else {
//                            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }

                    } catch(Exception ex) {
                        Log.w("Login Exception:", ex.getMessage());
                        Toast.makeText(LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private Intent getRoleActivity(String role) {
//        switch( role ) {
//            case "admin":
//                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
//                break;
//            default:
//                intent = new Intent(getBaseContext(), HomeActivity.class);
//                break;
//        }
//
//        return intent;
//    }

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

}
