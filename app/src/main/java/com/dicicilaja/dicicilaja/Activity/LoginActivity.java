package com.dicicilaja.dicicilaja.Activity;

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

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceCreateRequest;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceLogin;
import com.dicicilaja.dicicilaja.API.Item.Login.Login;
import com.google.firebase.iid.FirebaseInstanceId;

import com.dicicilaja.dicicilaja.API.Interface.InterfaceNotifToken;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.LoginMarketplace;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemMarketplace.LoginObj;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Remote.ApiUtils;
import com.dicicilaja.dicicilaja.Remote.UserFirebase;
import com.dicicilaja.dicicilaja.Remote.UserService;
import com.dicicilaja.dicicilaja.Session.SessionManager;
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

    String apiKey;

    InterfaceNotifToken interfaceNotifToken;

    SessionManager session;

    UserService userService;
    UserFirebase userFirebase;
    String photo, zipcode, area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(LoginActivity.this);

        if (session.isLoggedIn() == TRUE && session.getRole().equals("axi")) {
            Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("channel")) {
            Intent intent = new Intent(getBaseContext(), MaxiDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("crh")) {
            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("cro")) {
            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("tc")) {
            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("spg")) {
            Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("basic")) {
            Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
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

            final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.i("firebase_login", "token : "  + refreshedToken);

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
        InterfaceLogin apiService =
                RetrofitClient.getClient().create(InterfaceLogin.class);

        Call<Login> call = apiService.login(username, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()) {

                    Login resObj = response.body();

                    try {
                        photo = resObj.getPhoto().toString();
                        zipcode = resObj.getZipcode().toString();
                        area = resObj.getArea().toString();
                    }catch (Exception ex) {
                        photo = "";
                        zipcode = "";
                        area = "";
                    }

                    session.createLoginSession(resObj.getUserId(), resObj.getToken().getAccessToken(), resObj.getRole(), resObj.getName(), photo, resObj.getBranch(), area, zipcode);

                    if (resObj.getRole().equals("axi")) {
                        Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (resObj.getRole().equals("channel")) {
                        Intent intent = new Intent(getBaseContext(), MaxiDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (resObj.getRole().equals("crh")) {
                        Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (resObj.getRole().equals("cro")) {
                        Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (resObj.getRole().equals("tc")) {
                        Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (resObj.getRole().equals("spg")) {
                        Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (resObj.getRole().equals("basic")) {
                        Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
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

}