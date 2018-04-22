package id.variable.dicicilaja.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import id.variable.dicicilaja.R;

public class AjukanPengajuan2Activity extends AppCompatActivity {

    Button ajukan;
    EditText inputNama, inputHp, inputAlamat, inputProvinsi, inputKota, inputKecamatan, inputEmail;
    Integer channel_id, qty;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajukan_pengajuan2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        inputNama = findViewById(R.id.inputNama);
        inputHp = findViewById(R.id.inputHp);
        inputAlamat = findViewById(R.id.inputAlamat);
        inputProvinsi = findViewById(R.id.inputProvinsi);
        inputKota = findViewById(R.id.inputKota);
        inputKecamatan = findViewById(R.id.inputKecamatan);
        inputEmail = findViewById(R.id.inputEmail);
        ajukan = findViewById(R.id.ajukan);

        channel_id = 1;
        qty = 1;


        ajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ajukanpengajuan","program_id:" + getIntent().getStringExtra("program_id"));
                Log.d("ajukanpengajuan","axi_referral:" + getIntent().getStringExtra("axi_referral"));
                Log.d("ajukanpengajuan","colleteral_id:" + getIntent().getStringExtra("colleteral_id"));
                Log.d("ajukanpengajuan","manufacturer:" + getIntent().getStringExtra("manufacturer"));
                Log.d("ajukanpengajuan","year:" + getIntent().getStringExtra("year"));
                Log.d("ajukanpengajuan","tenor:" + getIntent().getStringExtra("tenor"));
                Log.d("ajukanpengajuan","ammount:" + getIntent().getStringExtra("ammount"));
                Log.d("ajukanpengajuan","area_id:" + getIntent().getStringExtra("area_id"));
                Log.d("ajukanpengajuan","branch_id:" + getIntent().getStringExtra("branch_id"));
                Log.d("ajukanpengajuan","email:" + inputEmail.getText());
                Log.d("ajukanpengajuan","qty:" + qty);
                Log.d("ajukanpengajuan","channel_id:" + channel_id);
                Log.d("ajukanpengajuan","client_name:" + inputNama.getText());
                Log.d("ajukanpengajuan","hp:" + inputHp.getText());
                Log.d("ajukanpengajuan","address:" + inputAlamat.getText());
                Log.d("ajukanpengajuan","province:" + inputProvinsi.getText());
                Log.d("ajukanpengajuan","city:" + inputKota.getText());
                Log.d("ajukanpengajuan","district:" + inputKecamatan.getText());
//                Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
//                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
