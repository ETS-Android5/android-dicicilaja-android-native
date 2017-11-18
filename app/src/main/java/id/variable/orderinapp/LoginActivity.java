package id.variable.orderinapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import id.variable.orderinapp.Register.RegisterAxiActivity;
import id.variable.orderinapp.Register.RegisterMitraActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView title_login,subtitle_login,forgot_password,powered,register_axi, register_mitra;
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        // set the view now
        setContentView(R.layout.activity_login);
        title_login = (TextView) findViewById(R.id.title_login);
        subtitle_login = (TextView) findViewById(R.id.subtitle_login);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);
        powered = (TextView) findViewById(R.id.powered);
        register_axi = (TextView) findViewById(R.id.register_axi);
        register_mitra = (TextView) findViewById(R.id.register_mitra);

        Typeface nunito_black = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-Black.ttf");
        Typeface nunito_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-Bold.ttf");
        Typeface nunito_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-SemiBold.ttf");
        Typeface nunito_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-Regular.ttf");
        title_login.setTypeface(nunito_black);
        subtitle_login.setTypeface(nunito_reguler);
        inputEmail.setTypeface(nunito_semibold);
        inputPassword.setTypeface(nunito_semibold);
        btnLogin.setTypeface(nunito_bold);
        forgot_password.setTypeface(nunito_reguler);
        powered.setTypeface(nunito_reguler);

        auth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();
        register_axi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterAxiActivity.class);
                startActivity(intent);
                finish();
            }
        });
        register_mitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterMitraActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukan email anda!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Masukan password anda!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Mencoba untuk masuk...");
                progressDialog.show();

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_success), Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                progressDialog.dismiss();
                            }
                        });

//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                startActivity(intent);
            }
        });

    }
}
