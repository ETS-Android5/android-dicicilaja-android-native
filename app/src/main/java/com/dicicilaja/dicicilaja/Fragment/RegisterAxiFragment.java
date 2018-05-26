package com.dicicilaja.dicicilaja.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.dicicilaja.dicicilaja.Activity.AjukanPengajuanAxi2Activity;
import com.dicicilaja.dicicilaja.Activity.AjukanPengajuanAxiActivity;
import com.dicicilaja.dicicilaja.Activity.AxiDashboardActivity;
import com.dicicilaja.dicicilaja.Activity.LoginActivity;
import com.dicicilaja.dicicilaja.Activity.RegisterAxi2Activity;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAreaBranch;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch.Branch;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Remote.AreaService;
import com.dicicilaja.dicicilaja.WebView.AboutAxiActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
  */
public class RegisterAxiFragment extends Fragment {

    TextView titleSection, bodySection, detailSection, sudahPunyaAkun, judulSudahPunyaAkun;
    EditText inputAxiRefferal, inputNamaLengkap, inputEmail, inputNoHp, inputNamaIbu;
    Button btnLanjut, btnDaftar;
    MaterialSpinner spinnerArea, spinnerCabang;
    AreaService AreaService;
    String axi_id, nama, email, hp, namaibu, area, cabang;
    TextInputLayout inputLayoutNamaLengkap, inputLayoutEmail, inputLayoutNoHp, inputLayoutNamaIbu;
    public RegisterAxiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_axi, container, false);
        inputNamaLengkap = view.findViewById(R.id.inputNamaLengkap);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputNamaIbu = view.findViewById(R.id.inputNamaIbu);
        inputAxiRefferal = view.findViewById(R.id.inputAxiRefferal);
        inputNoHp = (EditText) view.findViewById(R.id.inputNoHp);
        sudahPunyaAkun = (TextView) view.findViewById(R.id.sudahPunyaAkun);
        judulSudahPunyaAkun = (TextView) view.findViewById(R.id.judulSudahPunyaAkun);
        titleSection = (TextView) view.findViewById(R.id.titleSection);
        bodySection = (TextView) view.findViewById(R.id.bodySection);
        detailSection = (TextView) view.findViewById(R.id.detailSection);
        btnLanjut = view.findViewById(R.id.btnLanjut);
        btnDaftar = view.findViewById(R.id.btnDaftar);
        spinnerArea = view.findViewById(R.id.spinnerArea);
        spinnerCabang = view.findViewById(R.id.spinnerCabang);

        inputLayoutNamaLengkap = view.findViewById(R.id.inputLayoutNamaLengkap);
        inputLayoutEmail = view.findViewById(R.id.inputLayoutEmail);
        inputLayoutNoHp = view.findViewById(R.id.inputLayoutNoHp);
        inputLayoutNamaIbu = view.findViewById(R.id.inputLayoutNamaIbu);


        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        judulSudahPunyaAkun.setTypeface(opensans_reguler);
        sudahPunyaAkun.setTypeface(opensans_semibold);
        titleSection.setTypeface(opensans_bold);
        bodySection.setTypeface(opensans_reguler);
        detailSection.setTypeface(opensans_semibold);
        btnLanjut.setTypeface(opensans_bold);

        final List<String> AREA_ITEMS = new ArrayList<>();;
        final HashMap<Integer, String> AREA_MAP = new HashMap<Integer, String>();

        final List<String> CABANG_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> CABANG_MAP = new HashMap<Integer, String>();


        InterfaceAreaBranch apiServiceArea = RetrofitClient.getClient().create(InterfaceAreaBranch.class);

        Call<Area> callArea = apiServiceArea.getArea();
        callArea.enqueue(new Callback<Area>() {
            @Override
            public void onResponse(Call<Area> call, Response<Area> response) {

                AREA_MAP.clear();
                AREA_ITEMS.clear();

                for ( int i = 0; i < response.body().getData().size(); i++ ) {
                    AREA_MAP.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getId().toString());
                    AREA_ITEMS.add(response.body().getData().get(i).getName());
                }

                ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
                area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerArea.setAdapter(area_adapter);
                spinnerCabang.setEnabled(false);
            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                AREA_MAP.clear();
                AREA_ITEMS.clear();
                Log.e("Area Error", t.getMessage());
            }
        });



        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                InterfaceAreaBranch apiServiceBranch = RetrofitClient.getClient().create(InterfaceAreaBranch.class);


                Call<Branch> callBranch = apiServiceBranch.getBranch(AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                callBranch.enqueue(new Callback<Branch>() {
                    @Override
                    public void onResponse(Call<Branch> call, Response<Branch> response) {
                        CABANG_MAP.clear();
                        CABANG_ITEMS.clear();

                        for ( int i = 0; i < response.body().getData().size(); i++ ) {
                            CABANG_MAP.put(i+1, response.body().getData().get(i).getId());
                            CABANG_ITEMS.add(response.body().getData().get(i).getName());
                        }

                        ArrayAdapter<String> branch_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, CABANG_ITEMS);
                        branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerCabang.setEnabled(true);
                        spinnerCabang.setAdapter(branch_adapter);
                    }

                    @Override
                    public void onFailure(Call<Branch> call, Throwable t) {
                        CABANG_MAP.clear();
                        CABANG_ITEMS.clear();
                        spinnerCabang.setEnabled(false);

                        Log.e("Error Cabang", t.getMessage());
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                AREA_ITEMS.clear();
                AREA_MAP.clear();
                CABANG_MAP.clear();
                CABANG_ITEMS.clear();
                spinnerCabang.setEnabled(false);
            }

        });

        sudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    axi_id = inputAxiRefferal.getText().toString();
                    nama = inputNamaLengkap.getText().toString();
                    email = inputEmail.getText().toString();
                    hp = inputNoHp.getText().toString();
                    namaibu = inputNamaIbu.getText().toString();
                    area = AREA_MAP.get(spinnerArea.getSelectedItemPosition());
                    cabang = CABANG_MAP.get(spinnerCabang.getSelectedItemPosition());
                } catch (Exception ex) {

                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("axi_id: " + axi_id + "\n" + "nama: " + nama + "\n" + "email: " + email + "\n" + "hp: " + hp + "\n" + "namaibu: " + namaibu + "\n" + "area: " + area + "\n" + "cabang: " + cabang);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(validateForm(nama, email, hp, namaibu, area, cabang)) {
                            Intent intent = new Intent(getContext(), RegisterAxi2Activity.class);
                            intent.putExtra("axi_id",axi_id);
                            intent.putExtra("nama",nama);
                            intent.putExtra("email",email);
                            intent.putExtra("hp", hp);
                            intent.putExtra("namaibu", namaibu);
                            intent.putExtra("area", AREA_MAP.get(spinnerArea.getSelectedItemPosition()));
                            intent.putExtra("cabang", CABANG_MAP.get(spinnerCabang.getSelectedItemPosition()));
                            startActivity(intent);
                        }
                    }
                });
                alertDialog.show();

            }
        });
        detailSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutAxiActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private boolean validateForm(String nama, String email, String hp, String namaibu, String area, String cabang) {
        if(area == null || area.trim().length() == 0 || area.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Pilih area pengajuan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerArea);
                    spinnerArea.performClick();
                }
            });
            alertDialog.show();
            return false;
        }

        if(cabang == null || cabang.trim().length() == 0 || cabang.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Pilih cabang pengajuan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerCabang);
                    spinnerCabang.performClick();
                }
            });
            alertDialog.show();
            return false;
        }
        if(nama == null || nama.trim().length() == 0 || nama.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaLengkap);
                }
            });
            alertDialog.show();
            return false;
        }

        if(email == null || email.trim().length() == 0 || email.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        }

        if(hp == null || hp.trim().length() == 0 || hp.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan no.handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoHp);
                }
            });
            alertDialog.show();
            return false;
        }

        if(namaibu == null || namaibu.trim().length() == 0 || namaibu.equals("0")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Masukan nama ibu");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaIbu);
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
