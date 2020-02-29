package com.dicicilaja.app.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.Activity.RegisterMaxi2Activity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.WebView.AboutMaxiActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ganfra.materialspinner.MaterialSpinner;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterMaxiFragment extends Fragment {

    Button btnLanjut;
    TextView sudahPunyaAkun;
    SearchableSpinner spinnerJenisKelamin;
    EditText inputNamaPemilik, inputAlamatPemilik, inputNoHp, inputKtp;
    String apiKey, namapemilik, alamatpemilik, nohp, noktp, jk;
    TextInputLayout inputLayoutNamaPemilik, inputLayoutJenisKelamin, inputLayoutAlamatPemilik, inputLayoutNoHp, inputLayoutKtp;

    final List<String> JK_ITEMS = new ArrayList<>();
    final HashMap<Integer, String> JK_DATA = new HashMap<Integer, String>();

    public RegisterMaxiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_maxi, container, false);

        TextView titleSection   = view.findViewById(R.id.titleSection);
        TextView bodySection    = view.findViewById(R.id.bodySection);
        TextView detailSection  = view.findViewById(R.id.detailSection);
        inputNamaPemilik  = view.findViewById(R.id.inputNamaPemilik);
        sudahPunyaAkun = (TextView) view.findViewById(R.id.sudahPunyaAkun);
        inputAlamatPemilik  = view.findViewById(R.id.inputAlamatPemilik);
        inputNoHp  = view.findViewById(R.id.inputNoHp);
        inputKtp  = view.findViewById(R.id.inputKtp);
        spinnerJenisKelamin = view.findViewById(R.id.spinnerJenisKelamin);

        inputLayoutNamaPemilik = view.findViewById(R.id.inputLayoutNamaPemilik);
        inputLayoutAlamatPemilik = view.findViewById(R.id.inputLayoutAlamatPemilik);
        inputLayoutNoHp = view.findViewById(R.id.inputLayoutNoHp);
        inputLayoutKtp = view.findViewById(R.id.inputLayoutKtp);

        btnLanjut = view.findViewById(R.id.btnLanjut);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold      = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold  = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler   = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        apiKey = "null";
        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        detailSection.setTypeface(opensans_semibold);


        JK_ITEMS.clear();
        JK_DATA.clear();

        JK_DATA.put(0, "0");
        JK_DATA.put(1, "l");
        JK_DATA.put(2, "p");
        JK_ITEMS.add("Pilih Jenis Kelamin");
        JK_ITEMS.add("Laki-laki");
        JK_ITEMS.add("Perempuan");


        ArrayAdapter<String> jk_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, JK_ITEMS);
        jk_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(jk_adapter);
        spinnerJenisKelamin.setTitle("");
        spinnerJenisKelamin.setPositiveButton("OK");

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    namapemilik = inputNamaPemilik.getText().toString();
                    alamatpemilik = inputAlamatPemilik.getText().toString();
                    nohp = inputNoHp.getText().toString();
                    noktp = inputKtp.getText().toString();
                    jk = JK_DATA.get(spinnerJenisKelamin.getSelectedItemPosition());
                } catch (Exception ex) {

                }

                if(validateForm(namapemilik, jk, alamatpemilik, nohp, noktp)) {
                    Log.d("ajukanpengajuan","apiKey : " + apiKey);
                    Log.d("ajukanpengajuan","namapemilik : " + namapemilik);
                    Log.d("ajukanpengajuan","jk : " + jk);
                    Log.d("ajukanpengajuan","alamatpemilik : " + alamatpemilik);
                    Log.d("ajukanpengajuan","nohp : " + nohp);
                    Log.d("ajukanpengajuan","noktp : " + noktp);


                    Intent intent = new Intent(getContext(), RegisterMaxi2Activity.class);
                    intent.putExtra("apiKey",apiKey);
                    intent.putExtra("namapemilik",namapemilik);
                    intent.putExtra("alamatpemilik",alamatpemilik);
                    intent.putExtra("nohp",nohp);
                    intent.putExtra("noktp", noktp);
                    intent.putExtra("jk", jk);
                    startActivity(intent);
                }
            }
        });
        detailSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutMaxiActivity.class);
                startActivity(intent);
            }
        });

        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        inputNamaPemilik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputNamaPemilik.removeTextChangedListener(this);
                validateName();
                inputNamaPemilik.addTextChangedListener(this);
            }
        });

        inputAlamatPemilik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputAlamatPemilik.removeTextChangedListener(this);
                validateAlamat();
                inputAlamatPemilik.addTextChangedListener(this);
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

        inputKtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputKtp.removeTextChangedListener(this);
                validateKtp();
                inputKtp.addTextChangedListener(this);
            }
        });


        return view;
    }

    private boolean validateForm(String namapemilik, String jk, String alamatpemilik, String nohp, String noktp) {
        if (namapemilik == null || namapemilik.trim().length() == 0 || namapemilik.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle("Perhatian");
            alertDialog.setMessage("Masukan nama pemilik");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaPemilik);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isName(namapemilik)) {
            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(getContext());
            alertDialog5.setTitle("Perhatian");
            alertDialog5.setMessage("Masukan nama pemilik yang benar");

            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaPemilik);
                }
            });
            alertDialog5.show();
            return false;
        }

        if(jk == null || jk.trim().length() == 0 || jk.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setMessage("Pilih jenis kelamin");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJenisKelamin);
                    MotionEvent motionEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 0, 0, 0);
                    spinnerJenisKelamin.dispatchTouchEvent(motionEvent);
                    hideSoftKeyboard();
                }
            });
            alertDialog.show();
            return false;
        }

        if (alamatpemilik == null || alamatpemilik.trim().length() == 0 || alamatpemilik.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan alamat pemilik perusahaan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamatPemilik);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isAlamat(alamatpemilik)) {
            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(getContext());
            alertDialog5.setTitle("Perhatian");
            alertDialog5.setMessage("Masukan alamat pemilik yang benar");

            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamatPemilik);
                }
            });
            alertDialog5.show();
            return false;
        }

        if (nohp == null || nohp.trim().length() == 0 || nohp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan no. handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoHp);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isHp(nohp)) {
            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(getContext());
            alertDialog5.setTitle("Perhatian");
            alertDialog5.setMessage("Masukan no. handphone");

            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoHp);
                }
            });
            alertDialog5.show();
            return false;
        }

        if (noktp == null || noktp.trim().length() == 0 || noktp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan no. KTP");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKtp);
                }
            });
            alertDialog.show();
            return false;
        } else if (!isKtp(noktp)) {
            AlertDialog.Builder alertDialog5 = new AlertDialog.Builder(getContext());
            alertDialog5.setTitle("Perhatian");
            alertDialog5.setMessage("Masukan no. KTP");

            alertDialog5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKtp);
                }
            });
            alertDialog5.show();
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
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    public void hideSoftKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static boolean isName(String alamat) {
        String expression = "^[a-z.'/ A-Z]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateName() {
        if (inputNamaPemilik.getText().toString().trim().isEmpty()) {
            inputLayoutNamaPemilik.setErrorEnabled(false);
        } else {
            String emailId = inputNamaPemilik.getText().toString();
            String expression = "^[a-z.'/ A-Z]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutNamaPemilik.setError("Masukan nama pemilik dengan benar\ncontoh: Budi Susanto");
                requestFocus(inputNamaPemilik);
                return false;
            } else {
                inputLayoutNamaPemilik.setErrorEnabled(false);
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

    public static boolean isAlamat(String alamat) {
        String expression = "^[a-z.'/ A-Z0-9-]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateAlamat() {
        if (inputAlamatPemilik.getText().toString().trim().isEmpty()) {
            inputLayoutAlamatPemilik.setErrorEnabled(false);
        } else {
            String emailId = inputAlamatPemilik.getText().toString();
            String expression = "^[a-z.'/ A-Z0-9-]+$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutAlamatPemilik.setError("Masukan alamat pemilik dengan benar");
                requestFocus(inputAlamatPemilik);
                return false;
            } else {
                inputLayoutAlamatPemilik.setErrorEnabled(false);
            }
        }
        return true;
    }

    public static boolean isKtp(String alamat) {
        String expression = "^(?!0)([0-9]{12,12})(?=[0-9]{4,4})(?!(0{4})).{4,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(alamat);
        return matcher.matches();
    }

    private boolean validateKtp() {
        if (inputKtp.getText().toString().trim().isEmpty()) {
            inputLayoutKtp.setErrorEnabled(false);
        } else {
            String emailId = inputKtp.getText().toString();
            String expression = "^(?!0)([0-9]{12,12})(?=[0-9]{4,4})(?!(0{4})).{4,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailId);
            Boolean isValid = matcher.matches();
            if (!isValid) {
                inputLayoutKtp.setError("Nomor KTP yg diinput harus 16 digit angka\ncontoh : 3213030000000001");
                requestFocus(inputKtp);
                return false;
            } else {
                inputLayoutKtp.setErrorEnabled(false);
            }
        }
        return true;
    }

}