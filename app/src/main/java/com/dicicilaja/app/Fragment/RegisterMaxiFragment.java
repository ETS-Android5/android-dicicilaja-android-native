package com.dicicilaja.app.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.Activity.RegisterAxi2Activity;
import com.dicicilaja.app.Activity.RegisterMaxi2Activity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.WebView.AboutMaxiActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterMaxiFragment extends Fragment {

    Button btnLanjut;
    MaterialSpinner spinnerJenisKelamin;
    EditText inputNamaPemilik, inputAlamatPemilik, inputNoHp, inputKtp;
    String apiKey, namapemilik, alamatpemilik, nohp, noktp, jk;

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
        inputAlamatPemilik  = view.findViewById(R.id.inputAlamatPemilik);
        inputNoHp  = view.findViewById(R.id.inputNoHp);
        inputKtp  = view.findViewById(R.id.inputKtp);
        spinnerJenisKelamin = view.findViewById(R.id.spinnerJenisKelamin);

        btnLanjut = view.findViewById(R.id.btnLanjut);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold      = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold  = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler   = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        apiKey = "null";
        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        detailSection.setTypeface(opensans_semibold);

        final List<String> JK_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JK_DATA = new HashMap<Integer, String>();

        JK_ITEMS.clear();
        JK_DATA.clear();

        JK_DATA.put(1, "Laki-laki");
        JK_DATA.put(2, "Perempuan");
        JK_ITEMS.add("Laki-laki");
        JK_ITEMS.add("Perempuan");


        ArrayAdapter<String> jk_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, JK_ITEMS);
        jk_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(jk_adapter);

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
        return view;
    }

    private boolean validateForm(String namapemilik, String jk, String alamatpemilik, String nohp, String noktp) {
        if (namapemilik == null || namapemilik.trim().length() == 0 || namapemilik.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaPemilik);
                }
            });
            alertDialog.show();
            return false;
        }

        if(jk == null || jk.trim().length() == 0 || jk.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(getContext());
            alertDialog.setMessage("Pilih jenis kelamin");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJenisKelamin);
                    spinnerJenisKelamin.performClick();
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
        }

        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}