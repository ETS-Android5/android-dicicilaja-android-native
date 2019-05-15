package com.dicicilaja.app.Activity;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceUbahPassword;
import com.dicicilaja.app.API.Item.UbahPassword.UbahPassword;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPasswordEmployeeActivity extends AppCompatActivity {

    Button save;
    String apiKey;
    EditText inputKataSandiLama, inputKataSandiBaru, inputKataSandiBaru2;
    String katasandi, katasandibaru, katasandibaru2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password_employee);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        save = findViewById(R.id.save);
        inputKataSandiLama = findViewById(R.id.inputKataSandiLama);
        inputKataSandiBaru = findViewById(R.id.inputKataSandiBaru);
        inputKataSandiBaru2 = findViewById(R.id.inputKataSandiBaru2);

            save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    katasandi = inputKataSandiLama.getText().toString();
                    katasandibaru = inputKataSandiBaru.getText().toString();
                    katasandibaru2 = inputKataSandiBaru2.getText().toString();
                } catch (Exception ex) {

                }

                if(validateForm(katasandi, katasandibaru, katasandibaru2)) {
                    if(!katasandibaru.equals(katasandibaru2)) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(UbahPasswordEmployeeActivity.this);
                        alertDialog.setMessage("Kata sandi baru yang Anda masukan berbeda");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestFocus(inputKataSandiBaru);
                            }
                        });
                        alertDialog.show();
                    }else{
                        changePassword(apiKey,katasandi,katasandibaru,katasandibaru2);
                    }
                }
            }
        });
    }

    private boolean validateForm(String katasandi, String katasandibaru, String katasandibaru2) {
        if(katasandi == null || katasandi.trim().length() == 0 || katasandi.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UbahPasswordEmployeeActivity.this);
            alertDialog.setMessage("Masukan kata sandi lama");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKataSandiLama);
                }
            });
            alertDialog.show();
            return false;
        }

        if(katasandibaru == null || katasandibaru.trim().length() == 0 || katasandibaru.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UbahPasswordEmployeeActivity.this);
            alertDialog.setMessage("Masukan kata sandi baru");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKataSandiBaru);
                }
            });
            alertDialog.show();
            return false;
        }

        if(katasandibaru2 == null || katasandibaru2.trim().length() == 0 || katasandibaru2.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UbahPasswordEmployeeActivity.this);
            alertDialog.setMessage("Ulangi kata sandi baru");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKataSandiBaru2);
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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

    private void changePassword(final String apiKey, final String oldPassword, final String newPassword, final String secondNewPassword) {
        InterfaceUbahPassword apiService =
                RetrofitClient.getClient().create(InterfaceUbahPassword.class);
        Call<UbahPassword> call = apiService.change(apiKey, oldPassword, newPassword, secondNewPassword);
        call.enqueue(new Callback<UbahPassword>() {
            @Override
            public void onResponse(Call<UbahPassword> call, Response<UbahPassword> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getBaseContext(),"Kata sandi Anda berhasil diubah",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"Gagal merubah kata sandi",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
            @Override
            public void onFailure(Call<UbahPassword> call, Throwable t) {

            }
        });
    }
}
