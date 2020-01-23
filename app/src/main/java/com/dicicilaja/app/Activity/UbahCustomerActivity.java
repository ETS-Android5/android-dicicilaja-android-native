package com.dicicilaja.app.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.dicicilaja.app.API.Client.ApiClient;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahCustomer;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahCustomer.UbahCustomer;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahCustomerActivity extends AppCompatActivity {

    MaterialEditText inputNamaLengkap, inputHandphone, inputTanggal, inputAlamat, inputKelurahan, inputKecamatan, inputKota, inputProvinsi, inputBill;
    MaterialSpinner spinnerJenisKelamin;
    ImageView date_range;
    Button save;
    String apiKey;
    TextInputLayout layoutNamaLengkap;

    SessionManager session;
    String namaLengkap, handphone, tanggal, alamat, kelurahan, kecamatan, kota, provinsi, bill, jk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_customer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        inputNamaLengkap    = findViewById(R.id.inputNamaLengkap);
        inputHandphone      = findViewById(R.id.inputHandphone);
        inputTanggal        = findViewById(R.id.inputTanggal);
        inputAlamat         = findViewById(R.id.inputAlamat);
        inputKelurahan      = findViewById(R.id.inputKelurahan);
        inputKecamatan      = findViewById(R.id.inputKecamatan);
        inputKota           = findViewById(R.id.inputKota);
        inputProvinsi       = findViewById(R.id.inputProvinsi);
        inputBill           = findViewById(R.id.inputBill);
        save                = findViewById(R.id.save);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);

        inputNamaLengkap.setText(getIntent().getStringExtra("name_user"));
        inputHandphone.setText(getIntent().getStringExtra("api_handphone"));
        inputTanggal.setText(getIntent().getStringExtra("api_tanggal_daftar"));
        inputAlamat.setText(getIntent().getStringExtra("api_alamat"));
        inputKelurahan.setText(getIntent().getStringExtra("api_kelurahan"));
        inputKecamatan.setText(getIntent().getStringExtra("api_kecamatan"));
        inputKota.setText(getIntent().getStringExtra("api_kota"));
        inputProvinsi.setText(getIntent().getStringExtra("api_provinsi"));
        inputBill.setText(getIntent().getStringExtra("api_bill"));
        spinnerJenisKelamin.setSelection(Integer.parseInt(getIntent().getStringExtra("api_jk")));

        inputTanggal.setKeyListener(null);
        inputTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogfragment = new UbahCustomerActivity.DatePickerDialogTheme4();

                dialogfragment.show(getSupportFragmentManager(), "Theme 4");
            }
        });
        final List<String> JK_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JK_DATA = new HashMap<Integer, String>();

        JK_ITEMS.clear();
        JK_DATA.clear();

        JK_DATA.put(1, "Laki-laki");
        JK_DATA.put(2, "Perempuan");
        JK_ITEMS.add("Laki-laki");
        JK_ITEMS.add("Perempuan");


        ArrayAdapter<String> tenor_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JK_ITEMS);
        tenor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJenisKelamin.setAdapter(tenor_adapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    namaLengkap = inputNamaLengkap.getText().toString();
                    jk = JK_DATA.get(spinnerJenisKelamin.getSelectedItemPosition());
                    handphone = inputHandphone.getText().toString();
                    tanggal = inputTanggal.getText().toString();
                    alamat = inputAlamat.getText().toString();
                    kelurahan = inputKelurahan.getText().toString();
                    kecamatan = inputKecamatan.getText().toString();
                    kota = inputKota.getText().toString();
                    provinsi = inputProvinsi.getText().toString();
                    bill = inputBill.getText().toString();
                } catch (Exception ex) {

                }
                if(validateForm(namaLengkap, handphone, tanggal, alamat, kelurahan, kecamatan, kota, provinsi, jk, bill)) {
                    ubahCustomer(apiKey,namaLengkap, handphone, tanggal, alamat, kelurahan, kecamatan, kota, provinsi, jk, bill);

                }
            }
        });
    }

    private void ubahCustomer(final String apiKey, final String namaLengkap, final String handphone, final String tanggal, final String alamat, final String kelurahan, final String kecamatan, final String kota, final String provinsi, final String jk, final String bill) {
        InterfaceUbahCustomer apiService =
                ApiClient.getClient().create(InterfaceUbahCustomer.class);

        Call<UbahCustomer> call = apiService.change(apiKey,namaLengkap, handphone, tanggal, alamat, kelurahan, kecamatan, kota, provinsi, jk, bill);
        call.enqueue(new Callback<UbahCustomer>() {
            @Override
            public void onResponse(Call<UbahCustomer> call, Response<UbahCustomer> response) {
                if(response.isSuccessful()){
                    session.editLoginSessionCustomer(namaLengkap);
                    Toast.makeText(getBaseContext(),"Data Anda berhasil diubah",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                    intent.putExtra("profile","profile");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(),"Gagal merubah data Anda",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UbahCustomer> call, Throwable t) {

            }
        });
    }

    private boolean validateForm(String namaLengkap, String handphone, String tanggal, String alamat, String kelurahan, String kecamatan, String kota, String provinsi, String bill, String jk) {
        if(namaLengkap == null || namaLengkap.trim().length() == 0 || namaLengkap.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaLengkap);
                }
            });
            alertDialog.show();
            return false;
        }

        if(handphone == null || handphone.trim().length() == 0 || handphone.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan no.handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputHandphone);
                }
            });
            alertDialog.show();
            return false;
        }

        if(tanggal == null || tanggal.trim().length() == 0 || tanggal.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan tanggal lahir");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTanggal);
                }
            });
            alertDialog.show();
            return false;
        }

        if(alamat == null || alamat.trim().length() == 0 || alamat.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan alamat");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamat);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kelurahan == null || kelurahan.trim().length() == 0 || kelurahan.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan kelurahan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKelurahan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kecamatan == null || kecamatan.trim().length() == 0 || kecamatan.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan kecamatan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKecamatan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kota == null || kota.trim().length() == 0 || kota.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan kota");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKota);
                }
            });
            alertDialog.show();
            return false;
        }

        if(provinsi == null || provinsi.trim().length() == 0 || provinsi.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan provinsi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputProvinsi);
                }
            });
            alertDialog.show();
            return false;
        }

        if(jk == null || jk.trim().length() == 0 || jk.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Pilih jenis kelamin");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(spinnerJenisKelamin);
                }
            });
            alertDialog.show();
            return false;
        }

        if(bill == null || bill.trim().length() == 0 || bill.equals("0")) {
            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(UbahCustomerActivity.this);
            alertDialog.setMessage("Masukan no. tagihan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputBill);
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

    public static class DatePickerDialogTheme4 extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

            return datepickerdialog;
        }
        public void onDateSet(DatePicker view, int year, int month, int day){

            EditText textview = (EditText)getActivity().findViewById(R.id.inputTanggal);

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateInString = day + "/" + (month + 1) + "/" + year;
                Date date = formatter.parse(dateInString);

                formatter = new SimpleDateFormat("dd/MM/yyyy");

                textview.setText(formatter.format(date).toString());

            } catch (Exception ex) {

            }

        }
    }
}
