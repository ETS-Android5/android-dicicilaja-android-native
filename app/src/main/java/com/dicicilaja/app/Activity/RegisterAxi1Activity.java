package com.dicicilaja.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicicilaja.app.Activity.RemoteMarketplace.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAreaBranch;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area.Area;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch.Branch;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.dicicilaja.app.WebView.AboutAxiActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;

public class RegisterAxi1Activity extends AppCompatActivity {

    TextView titleSection, bodySection, detailSection;
    EditText inputAxiRefferal, inputNamaLengkap, inputEmail, inputNoHp, inputNamaIbu;
    Button btnLanjut, btnDaftar;
    MaterialSpinner spinnerArea, spinnerCabang;
    com.dicicilaja.app.Remote.AreaService AreaService;
    String axi_id, nama, email, hp, namaibu, area, cabang;
    TextInputLayout inputLayoutNamaLengkap, inputLayoutEmail, inputLayoutNoHp, inputLayoutNamaIbu;

    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_axi1);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(RegisterAxi1Activity.this);
        if(session.isLoggedIn() == TRUE){
            getSupportActionBar().setTitle("Tambah Rekan Bisnis");
        }

        inputNamaLengkap = findViewById(R.id.inputNamaLengkap);
        inputEmail = findViewById(R.id.inputEmail);
        inputNamaIbu = findViewById(R.id.inputNamaIbu);
        inputAxiRefferal = findViewById(R.id.inputAxiRefferal);
        inputNoHp = findViewById(R.id.inputNoHp);
        titleSection = findViewById(R.id.titleSection);
        bodySection = findViewById(R.id.bodySection);
        detailSection = findViewById(R.id.detailSection);
        btnLanjut = findViewById(R.id.btnLanjut);
        btnDaftar = findViewById(R.id.btnDaftar);
        spinnerArea = findViewById(R.id.spinnerArea);
        spinnerCabang = findViewById(R.id.spinnerCabang);

        inputLayoutNamaLengkap = findViewById(R.id.inputLayoutNamaLengkap);
        inputLayoutEmail = findViewById(R.id.inputLayoutEmail);
        inputLayoutNoHp = findViewById(R.id.inputLayoutNoHp);
        inputLayoutNamaIbu = findViewById(R.id.inputLayoutNamaIbu);

        try {
            inputAxiRefferal.setText(session.getAxiId());
        }catch (Exception ex){

        }

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

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

                ArrayAdapter<String> area_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, AREA_ITEMS);
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

                        ArrayAdapter<String> branch_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, CABANG_ITEMS);
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

                if(validateForm(nama, email, hp, namaibu, area, cabang)) {
                    Intent intent = new Intent(getBaseContext(), RegisterAxi2Activity.class);
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
        detailSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AboutAxiActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateForm(String nama, String email, String hp, String namaibu, String area, String cabang) {
        if(area == null || area.trim().length() == 0 || area.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi1Activity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi1Activity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi1Activity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi1Activity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi1Activity.this);
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
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterAxi1Activity.this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }

    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
