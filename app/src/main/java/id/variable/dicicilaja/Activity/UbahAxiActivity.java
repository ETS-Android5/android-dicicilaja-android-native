package id.variable.dicicilaja.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import id.variable.dicicilaja.API.Client.NewRetrofitClient;
import id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahAxi;
import id.variable.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceUbahPassword;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahAxi.UbahAxi;
import id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahPassword.UbahPassword;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahAxiActivity extends AppCompatActivity {

    MaterialEditText inputNamaLengkap, inputTempatLahir, inputTanggal, inputNoHp, inputEmail, inputAlamat, inputRtRw, inputKelurahan, inputKecamatan, inputProvinsi, inputKodepos, inputNPWP, inputNamaBank, inputCabang, inputRekening, inputAN, inputKotaBank;
    MaterialSpinner jenisKelamin;
    ImageView date_range;
    Button save;
    String apiKey;
    TextInputLayout layoutNamaLengkap;
    String namaLengkap,tempatLahir,tanggal,noHp,email,alamat,rtRw,kelurahan,kecamatan,provinsi,kodepos,jk,NPWP,namaBank,cabang,rekening,AN,kotaBank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_axi);

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

        jenisKelamin        = findViewById(R.id.spinnerJenisKelamin);
        inputNamaLengkap    = findViewById(R.id.inputNamaLengkap);
        inputTempatLahir    = findViewById(R.id.inputTempatLahir);
        inputTanggal        = findViewById(R.id.inputTanggal);
        inputNoHp           = findViewById(R.id.inputNoHp);
        inputEmail          = findViewById(R.id.inputEmail);
        inputAlamat         = findViewById(R.id.inputAlamat);
        inputRtRw           = findViewById(R.id.inputRtRw);
        inputKelurahan      = findViewById(R.id.inputKelurahan);
        inputKecamatan      = findViewById(R.id.inputKecamatan);
        inputProvinsi       = findViewById(R.id.inputProvinsi);
        inputKodepos        = findViewById(R.id.inputKodepos);
        inputNPWP           = findViewById(R.id.inputNPWP);
        inputNamaBank       = findViewById(R.id.inputNamaBank);
        inputCabang         = findViewById(R.id.inputCabang);
        inputRekening       = findViewById(R.id.inputRekening);
        inputAN             = findViewById(R.id.inputAN);
        inputKotaBank       = findViewById(R.id.inputKotaBank);
        save                = findViewById(R.id.save);

        inputNamaLengkap.setText(getIntent().getStringExtra("name_user"));
        inputTempatLahir.setText(getIntent().getStringExtra("api_tempat_lahir"));
        inputTanggal.setText(getIntent().getStringExtra("api_tanggal_lahir"));
        inputNoHp.setText(getIntent().getStringExtra("api_no_hp"));
        inputEmail.setText(getIntent().getStringExtra("api_email"));
        inputAlamat.setText(getIntent().getStringExtra("api_alamat"));
        inputRtRw.setText(getIntent().getStringExtra("api_rt_rw"));
        inputKelurahan.setText(getIntent().getStringExtra("api_kelurahan"));
        inputKecamatan.setText(getIntent().getStringExtra("api_kecamatan"));
        inputProvinsi.setText(getIntent().getStringExtra("api_provinsi"));
        inputKodepos.setText(getIntent().getStringExtra("api_kodepos"));
        inputNPWP.setText(getIntent().getStringExtra("api_no_npwp"));
        inputNamaBank.setText(getIntent().getStringExtra("api_nama_bank"));
        inputCabang.setText(getIntent().getStringExtra("api_cabang_bank"));
        inputRekening.setText(getIntent().getStringExtra("api_no_rekening"));
        inputAN.setText(getIntent().getStringExtra("api_an_rekening"));
        inputKotaBank.setText(getIntent().getStringExtra("api_kota_bank"));
        jenisKelamin.setSelection(Integer.parseInt(getIntent().getStringExtra("api_jk")));

        inputTanggal.setKeyListener(null);
        inputTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogfragment = new UbahAxiActivity.DatePickerDialogTheme4();

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
        jenisKelamin.setAdapter(tenor_adapter);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    jk = JK_DATA.get(jenisKelamin.getSelectedItemPosition());
                    namaLengkap = inputNamaLengkap.getText().toString();
                    tempatLahir = inputTempatLahir.getText().toString();
                    tanggal = inputTanggal.getText().toString();
                    noHp = inputNoHp.getText().toString();
                    email = inputEmail.getText().toString();
                    alamat = inputAlamat.getText().toString();
                    rtRw = inputRtRw.getText().toString();
                    kelurahan = inputKelurahan.getText().toString();
                    kecamatan = inputKecamatan.getText().toString();
                    provinsi = inputProvinsi.getText().toString();
                    kodepos = inputKodepos.getText().toString();
                    NPWP = inputNPWP.getText().toString();
                    namaBank = inputNamaBank.getText().toString();
                    cabang = inputCabang.getText().toString();
                    rekening = inputRekening.getText().toString();
                    AN = inputAN.getText().toString();
                    kotaBank = inputKotaBank.getText().toString();
                } catch (Exception ex) {

                }
                if(validateForm(namaLengkap,tempatLahir,tanggal,noHp,email,alamat,rtRw,kelurahan,kecamatan,provinsi,kodepos,jk,NPWP,namaBank,cabang,rekening,AN,kotaBank)) {
                    ubahAxi(apiKey,namaLengkap,tempatLahir,tanggal,noHp,email,alamat,rtRw,kelurahan,kecamatan,provinsi,kodepos,jk,NPWP,namaBank,cabang,rekening,AN,kotaBank);

                }
            }
        });
    }
    private void ubahAxi(final String apiKey, final String namaLengkap, final String tempatLahir, final String tanggal, final String noHp, final String email, final String alamat, final String rtRw, final String kelurahan, final String kecamatan, final String provinsi, final String kodepos, final String jk, final String NPWP, final String namaBank, final String cabang, final String rekening, final String AN, final String kotaBank) {
        InterfaceUbahAxi apiService =
                NewRetrofitClient.getClient().create(InterfaceUbahAxi.class);

        Call<UbahAxi> call = apiService.change(apiKey,namaLengkap,tempatLahir,tanggal,noHp,email,alamat,rtRw,kelurahan,kecamatan,provinsi,kodepos,jk,NPWP,namaBank,cabang,rekening,AN,kotaBank);
        call.enqueue(new Callback<UbahAxi>() {
            @Override
            public void onResponse(Call<UbahAxi> call, Response<UbahAxi> response) {
                try {
                    Toast.makeText(getBaseContext(),"Data Anda berhasil diubah",Toast.LENGTH_SHORT).show();
                    finish();
                } catch(Exception ex) {
                    Log.w("Process Exception :", ex.getMessage());
                    Toast.makeText(getBaseContext(), "Gagal mengubah data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UbahAxi> call, Throwable t) {

            }
        });
    }

    private boolean validateForm(String namaLengkap, String tempatLahir, String tanggal, String noHp, String email, String alamat, String rtRw, String kelurahan, String kecamatan, String provinsi, String kodepos, String jk, String NPWP, String namaBank, String cabang, String rekening, String AN, String kotaBank) {
        if(namaLengkap == null || namaLengkap.trim().length() == 0 || namaLengkap.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan nama lengkap");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaLengkap);
                }
            });
            alertDialog.show();
            return false;
        }

        if(tempatLahir == null || tempatLahir.trim().length() == 0 || tempatLahir.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan tempat lahir");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTempatLahir);
                }
            });
            alertDialog.show();
            return false;
        }

        if(tanggal == null || tanggal.trim().length() == 0 || tanggal.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan tanggal lahir");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputTanggal);
                }
            });
            alertDialog.show();
            return false;
        }

        if(noHp == null || noHp.trim().length() == 0 || noHp.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan no.handphone");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNoHp);
                }
            });
            alertDialog.show();
            return false;
        }

        if(email == null || email.trim().length() == 0 || email.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan email");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputEmail);
                }
            });
            alertDialog.show();
            return false;
        }

        if(alamat == null || alamat.trim().length() == 0 || alamat.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan alamat");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAlamat);
                }
            });
            alertDialog.show();
            return false;
        }

        if(rtRw == null || rtRw.trim().length() == 0 || rtRw.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan RT/RW");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputRtRw);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kelurahan == null || kelurahan.trim().length() == 0 || kelurahan.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
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
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan kecamatan");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKecamatan);
                }
            });
            alertDialog.show();
            return false;
        }

        if(provinsi == null || provinsi.trim().length() == 0 || provinsi.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan provinsi");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputProvinsi);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kodepos == null || kodepos.trim().length() == 0 || kodepos.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan kode pos");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKodepos);
                }
            });
            alertDialog.show();
            return false;
        }

        if(jk == null || jk.trim().length() == 0 || jk.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Pilih jenis kelamin");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(jenisKelamin);
                }
            });
            alertDialog.show();
            return false;
        }

        if(NPWP == null || NPWP.trim().length() == 0 || NPWP.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan no.npwp");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNPWP);
                }
            });
            alertDialog.show();
            return false;
        }

        if(namaBank == null || namaBank.trim().length() == 0 || namaBank.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan nama bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputNamaBank);
                }
            });
            alertDialog.show();
            return false;
        }

        if(cabang == null || cabang.trim().length() == 0 || cabang.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan cabang bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputCabang);
                }
            });
            alertDialog.show();
            return false;
        }

        if(rekening == null || rekening.trim().length() == 0 || rekening.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan no.rekening");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputRekening);
                }
            });
            alertDialog.show();
            return false;
        }

        if(AN == null || AN.trim().length() == 0 || AN.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan a/n rekening");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputAN);
                }
            });
            alertDialog.show();
            return false;
        }

        if(kotaBank == null || kotaBank.trim().length() == 0 || kotaBank.equals("0")) {
            android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(UbahAxiActivity.this);
            alertDialog.setMessage("Masukan kota bank");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    requestFocus(inputKotaBank);
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
