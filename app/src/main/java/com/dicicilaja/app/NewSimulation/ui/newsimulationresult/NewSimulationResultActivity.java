package com.dicicilaja.app.NewSimulation.ui.newsimulationresult;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.Activity.AjukanPengajuanAxiActivity;
import com.dicicilaja.app.NewSimulation.data.hitungsimulasi.HitungSimulasi;
import com.dicicilaja.app.NewSimulation.network.ApiClient;
import com.dicicilaja.app.NewSimulation.network.ApiService;
import com.dicicilaja.app.NewSimulation.ui.bantuan.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.ui.newsimulation.NewSimulationActivity;
import com.dicicilaja.app.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewSimulationResultActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.call_tasya)
    TextView callTasya;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.tenor)
    TextView tenor;
    @BindView(R.id.angsuran)
    TextView angsuran;
    @BindView(R.id.tenor_angsuran)
    TextView tenorAngsuran;
    @BindView(R.id.colleteral)
    TextView colleteral;
    @BindView(R.id.merk)
    TextView merk;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.insurance)
    TextView insurance;
    @BindView(R.id.area)
    TextView area;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.simulation)
    TextView simulation;

    String tipe_objek_id, area_id, tahun_kendaraan, objek_model_id, tenor_simulasi, tipe_asuransi_id, tipe_angsuran_id;

    String spinner_jaminan, text_total, text_tenor, text_angsuran, text_tenor_angsuran, text_colleteral, text_merk, text_type, text_year, text_insurance, text_area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_simulation_result);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Hasil Simulasi");

//        Log.d("TESTTEST", "tipe_objek_id: " + getIntent().getStringExtra("tipe_objek_id"));
//        Log.d("TESTTEST", "objek_model_id: " + getIntent().getStringExtra("objek_model_id"));
//        Log.d("TESTTEST", "tahun_kendaraan: " + getIntent().getStringExtra("tahun_kendaraan"));
//        Log.d("TESTTEST", "area_id: " + getIntent().getStringExtra("area_id"));
//        Log.d("TESTTEST", "tenor: " + getIntent().getStringExtra("tenor"));
//        Log.d("TESTTEST", "tipe_asuransi_id: " + getIntent().getStringExtra("tipe_asuransi_id"));
//        Log.d("TESTTEST", "tipe_angsuran_id: " + getIntent().getStringExtra("tipe_angsuran_id"));

        text_total = getIntent().getStringExtra("text_total");
        text_tenor = getIntent().getStringExtra("text_tenor");
        text_angsuran = getIntent().getStringExtra("text_angsuran");
        text_tenor_angsuran = getIntent().getStringExtra("text_tenor_angsuran");
        text_colleteral = getIntent().getStringExtra("text_colleteral");
        text_merk = getIntent().getStringExtra("text_merk");
        text_type = getIntent().getStringExtra("text_type");
        text_year = getIntent().getStringExtra("text_year");
        text_insurance = getIntent().getStringExtra("text_insurance");
        text_area = getIntent().getStringExtra("text_area");

        total.setText(text_total);
        tenor.setText(text_tenor);
        angsuran.setText(text_angsuran);
        tenorAngsuran.setText(text_tenor_angsuran);
        colleteral.setText(text_colleteral);
        merk.setText(text_merk);
        type.setText(text_type);
        year.setText(text_year);
        insurance.setText(text_insurance);
        area.setText(text_area);

        if (text_colleteral.equals("Mobil")) {
            spinner_jaminan = "1";
        } else if (text_colleteral.equals("Motor")) {
            spinner_jaminan = "2";
        }

    }

    @OnClick({R.id.call_tasya, R.id.next, R.id.simulation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call_tasya:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6281293343334"));
                startActivity(browserIntent);
                break;
            case R.id.next:
                Intent intent2 = new Intent(getBaseContext(), AjukanPengajuanAxiActivity.class);
                intent2.putExtra("text_harga", String.valueOf(text_total));
                intent2.putExtra("text_merk", text_merk + " " + text_type);
                intent2.putExtra("spinner_jaminan", spinner_jaminan);
                startActivity(intent2);
                break;
            case R.id.simulation:
                Intent intent = new Intent(getBaseContext(), NewSimulationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simulasi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.help:
                Intent intent = new Intent(getBaseContext(), BantuanNewSimulationActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
