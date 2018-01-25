package id.variable.dicicilaja.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import id.variable.dicicilaja.Model.ResRequestProcess;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Remote.RequestProcess;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProsesPengajuan2Activity extends AppCompatActivity {

    long delay = 1000;
    long last_text_edit;
    String id_database;
    MaterialEditText inputReferal;
    RequestProcess interfaceTCProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses_pengajuan2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SessionManager session = new SessionManager(getBaseContext());
        final String apiKey = "Bearer " + session.getToken();

        final RelativeLayout proses = findViewById(R.id.proses);
        final TextView lihat_database = findViewById(R.id.lihat_database);
        TextView title_tugas = findViewById(R.id.title_tugas);
        TextView title_penugasan = findViewById(R.id.title_penugasan);

        final Handler handler = new Handler();
        interfaceTCProcess = ApiUtils.getRequestService();

//        Toast.makeText(getBaseContext(),"ID PENGAJUAN : " + getIntent().getStringExtra("TRANSACTION_ID"),Toast.LENGTH_SHORT).show();

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_tugas.setTypeface(opensans_bold);
        title_penugasan.setTypeface(opensans_bold);

        inputReferal = findViewById(R.id.inputReferal);
        final MaterialEditText inputCatatan = findViewById(R.id.inputCatatan);
        final ProgressBar inputProgressBarReferal = findViewById(R.id.input_progess_bar_referal);

        inputReferal.setTextColor(getResources().getColor(R.color.colorBackgroundDark));
        inputProgressBarReferal.setVisibility(View.GONE);


        lihat_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), LihatDatabaseEmployeeActivity.class);
                startActivityForResult(i, 1);
            }
        });

        final Runnable isInputFinish = new Runnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                    inputProgressBarReferal.setVisibility(View.GONE);
                }
            }
        };

        inputReferal.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if ( s.length() > 0 ) {
                    last_text_edit = System.currentTimeMillis();
                    handler.postDelayed(isInputFinish, delay);
                    inputProgressBarReferal.setVisibility(View.VISIBLE);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                handler.removeCallbacks(isInputFinish);
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String transaction_id = getIntent().getStringExtra("TRANSACTION_ID").toString();
                String assigned_id = inputReferal.getText().toString();
                String notes = inputCatatan.getText().toString();
//                Toast.makeText(getBaseContext(),"transcation_id : " + transaction_id + " assigned_id : " + assigned_id + " notes : " + notes,Toast.LENGTH_LONG).show();
                doProcess(apiKey, transaction_id, assigned_id, notes);
            }
        });
    }
    private void doProcess(final String apiKey, final String transaction_id, final String assigned_id, final String notes) {
        Call<ResRequestProcess> call = interfaceTCProcess.assign(apiKey,transaction_id, assigned_id, notes);
        call.enqueue(new Callback<ResRequestProcess>() {
            @Override
            public void onResponse(Call<ResRequestProcess> call, Response<ResRequestProcess> response) {
                try {
                    Intent intent = new Intent(getBaseContext(), TCDashboardActivity.class);
                    startActivity(intent);
                    finish();
                } catch(Exception ex) {
                    Log.w("Process Exception :", ex.getMessage());
                    Toast.makeText(ProsesPengajuan2Activity.this, "Tidak dapat memproses pengajuan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResRequestProcess> call, Throwable t) {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                id_database = data.getStringExtra("ID_DATABASE").toString();
//                Toast.makeText(getBaseContext(),"Halo : " + id_database,Toast.LENGTH_SHORT).show();
                inputReferal.setText(id_database);
            }
        }
    }
}
