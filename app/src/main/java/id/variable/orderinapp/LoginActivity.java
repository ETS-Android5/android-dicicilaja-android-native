package id.variable.orderinapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import id.variable.orderinapp.Menu.HomeTCActivity;
import id.variable.orderinapp.Model.ResObj;
import id.variable.orderinapp.Model.Token;
import id.variable.orderinapp.Remote.ApiUtils;
import id.variable.orderinapp.Remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView title_login,subtitle_login,forgot_password,powered,register_axi, register_mitra;
    private EditText inputUsername, inputPassword;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnLogin;

    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeTCActivity.class));
            finish();
        }
        // set the view now
        setContentView(R.layout.activity_login);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);
        powered = (TextView) findViewById(R.id.powered);
        register_axi = (TextView) findViewById(R.id.register_axi);
        register_mitra = (TextView) findViewById(R.id.register_mitra);

        userService = ApiUtils.getUserService();

        Typeface nunito_black = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-Black.ttf");
        Typeface nunito_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-Bold.ttf");
        Typeface nunito_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-SemiBold.ttf");
        Typeface nunito_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-Regular.ttf");
        inputUsername.setTypeface(nunito_semibold);
        inputPassword.setTypeface(nunito_semibold);
        btnLogin.setTypeface(nunito_bold);
        forgot_password.setTypeface(nunito_reguler);
        powered.setTypeface(nunito_reguler);

        auth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();
                if(validateLogin(username, password)) {
                    doLogin(username, password);
                }
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        if(username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Masukan username anda", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Masukan password anda", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username, final String password) {
        Call<ResObj> call = userService.login(username, password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if(response.isSuccessful()) {
                    ResObj resObj = response.body();


                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("bearer", resObj.getToken().getAccessToken());
                    editor.apply();

                    Intent intent = new Intent(getBaseContext(), HomeTCActivity.class);
                    startActivity(intent);
                    finish();

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
}
