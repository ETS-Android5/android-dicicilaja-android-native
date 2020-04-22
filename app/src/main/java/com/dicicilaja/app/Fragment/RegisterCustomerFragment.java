package com.dicicilaja.app.Fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.app.API.Client.ComposserClient;
import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.Activity.RegisterCustomerDone;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateCustomer;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateCustomer.CreateCustomer;
import com.dicicilaja.app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterCustomerFragment extends Fragment {

    TextView sudahPunyaAkun, textCheck;
    EditText inputNamaLengkap, inputEmail, inputNoHp, inputKataSandi, inputKonfirmasi;
    String nama, email, nohp, katasandi, konfirmasi;
    Button btnDaftar;
    CheckBox check;
    String apiKey;
    ProgressDialog progress;
    TextInputLayout inputLayoutNamaLengkap, inputLayoutEmail, inputLayoutNoHp, inputLayoutKataSandi, inputLayoutKonfirmasi;
    public RegisterCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_customer, container, false);

        sudahPunyaAkun = view.findViewById(R.id.sudahPunyaAkun);
        inputNamaLengkap = view.findViewById(R.id.inputNamaLengkap);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputNoHp = view.findViewById(R.id.inputNoHp);
        inputKataSandi = view.findViewById(R.id.inputKataSandi);
        inputKonfirmasi = view.findViewById(R.id.inputKonfirmasi);
        btnDaftar = view.findViewById(R.id.btnDaftar);
        check = view.findViewById(R.id.check);
        textCheck = view.findViewById(R.id.textCheck);
        inputLayoutNamaLengkap = view.findViewById(R.id.inputLayoutNamaLengkap);
        inputLayoutEmail = view.findViewById(R.id.inputLayoutEmail);
        inputLayoutNoHp = view.findViewById(R.id.inputLayoutNoHp);
        inputLayoutKataSandi = view.findViewById(R.id.inputLayoutKataSandi);
        inputLayoutKonfirmasi = view.findViewById(R.id.inputLayoutKonfirmasi);

        progress = new ProgressDialog(getContext());
        progress.setMessage("Sedang mengirim data...");
        progress.setCanceledOnTouchOutside(false);

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

        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputEmail.removeTextChangedListener(this);
                validateEmail();
                inputEmail.addTextChangedListener(this);
            }
        });

        inputNoHp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputNoHp.removeTextChangedListener(this);
                validateHp();
                inputNoHp.addTextChangedListener(this);
            }
        });

        inputKonfirmasi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputKonfirmasi.removeTextChangedListener(this);
                validatePassword();
                inputKonfirmasi.addTextChangedListener(this);
            }
        });

        textCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {
                    check.setChecked(false);
                }
                else {
                    check.setChecked(true);
                }
            }
        });

        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    nama = inputNamaLengkap.getText().toString();
                    email = inputEmail.getText().toString();
                    nohp = inputNoHp.getText().toString();
                    katasandi = inputKataSandi.getText().toString();
                    konfirmasi = inputKonfirmasi.getText().toString();
                } catch (Exception ex) {

                }
                if(validateForm(nama, email, nohp, katasandi, konfirmasi)) {

                    if(!katasandi.equals(konfirmasi)) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setMessage("Kata sandi dan konfirmasi kata sandi berbeda");

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                requestFocus(inputKataSandi);
                            }
                        });
                        alertDialog.show();
                    }else{
                        if(check.isChecked()){
                            Log.d("ajukanpengajuan","nama : " + nama);
                            Log.d("ajukanpengajuan", "email : " + email);
                            Log.d("ajukanpengajuan", "nohp : " + nohp);
                            Log.d("ajukanpengajuan", "katasandi : " + katasandi);
                            Log.d("ajukanpengajuan", "konfirmasi : " + konfirmasi);
                            progress.show();
                            buatAkun(nama, email, nohp, katasandi, konfirmasi);

                        }else {
                            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
                            alertDialog.setMessage("Harap setujui syarat dan ketentuan jika ingin mendaftar");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }

                    }

                }

            }
        });

        return view;
    }

    private void buatAkun(final String nama, final String email, final String no_hp, final String password, final String password_confirmation) {
        InterfaceCreateCustomer apiService =
                ComposserClient.getClient().create(InterfaceCreateCustomer.class);

        Call<CreateCustomer> call = apiService.create(apiKey, nama, email, no_hp, password, password_confirmation);
        call.enqueue(new Callback<CreateCustomer>() {
            @Override
            public void onResponse(Call<CreateCustomer> call, Response<CreateCustomer> response) {
                if(response.isSuccessful()){
                    progress.dismiss();
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Intent intent = new Intent(getContext(), RegisterCustomerDone.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (response.code() == 422){
                    progress.dismiss();
                    Toast.makeText(getContext(),"Email Anda sudah terdaftar atau silahkan hubungi Tasya.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else{
                    progress.dismiss();
                    Toast.makeText(getContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CreateCustomer> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getContext(),"Terjadi kesalahan teknis, silahkan coba beberapa saat lagi.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private boolean validateForm(String nama, String email, String hp, String katasandi, String konfirmasi) {
        if (nama == null || nama.trim().length() == 0 || nama.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaLengkap);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isName(nama)) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nama lengkap yang benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaLengkap);
                }
            });
            alertDialog.show();
            return false;
        }

        if (email == null || email.trim().length() == 0 || email.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isEmailValid(email)) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan alamat email dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        }

        if (hp == null || hp.trim().length() == 0 || hp.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nomor handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoHp);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isHp(hp)) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nomor handphone dengan benar");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoHp);
                }
            });
            alertDialog.show();
            return false;
        }

        if (katasandi == null || katasandi.trim().length() == 0 || katasandi.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan kata sandi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKataSandi);
                }
            });
            alertDialog.show();
            return false;
        } else if (katasandi.trim().length() < 6){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan kata sandi minimal 6 karakter");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKataSandi);
                }
            });
            alertDialog.show();
            return false;
        }

        if (konfirmasi == null || konfirmasi.trim().length() == 0 || konfirmasi.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan konfirmasi kata sandi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKonfirmasi);
                }
            });
            alertDialog.show();
            return false;
        } else if (konfirmasi.trim().length() < 6){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan konfirmasi kata sandi minimal 6 karakter");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKonfirmasi);
                }
            });
            alertDialog.show();
            return false;
        } else if (!konfirmasi.equals(katasandi)){
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Kata sandi Anda tidak cocok");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKonfirmasi);
                }
            });
            alertDialog.show();
            return false;
        }
        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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
                inputLayoutNamaLengkap.setError("Masukan nama lengkap dengan benar\ncontoh: Budi Susanto");
                requestFocus(inputNamaLengkap);
                return false;
            } else {
                inputLayoutNamaLengkap.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateEmail() {
        if (inputEmail.getText().toString().trim().isEmpty()) {
            inputLayoutEmail.setErrorEnabled(false);
        } else {
            String emailId = inputEmail.getText().toString();
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutEmail.setError("Masukan alamat email dengan benar\ncontoh: budi.susanto@gmail.com");
                requestFocus(inputEmail);
                return false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
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
        if (inputNoHp.getText().toString().trim().isEmpty()) {
            inputLayoutNoHp.setErrorEnabled(false);
        } else {
            String emailId = inputNoHp.getText().toString();
            String expression = "^62[0-9]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutNoHp.setError("Format nomor HP salah\ncontoh: 6281234567890");
                requestFocus(inputNoHp);
                return false;
            } else {
                inputLayoutNoHp.setErrorEnabled(false);
            }
        }
        return true;
    }

    private boolean validatePassword() {
        if (inputKonfirmasi.getText().toString().trim().isEmpty()) {
            inputLayoutKonfirmasi.setErrorEnabled(false);
        } else {
            String password = inputKataSandi.getText().toString();
            String konfirmasi = inputKonfirmasi.getText().toString();
            if (!password.equals(konfirmasi)) {
                inputLayoutKonfirmasi.setError("Kata sandi Anda tidak cocok");
                requestFocus(inputKonfirmasi);
                return false;
            } else {
                inputLayoutKonfirmasi.setErrorEnabled(false);
            }
        }
        return true;
    }

}
