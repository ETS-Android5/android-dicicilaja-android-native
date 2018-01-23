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
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import id.variable.dicicilaja.R;

public class ProsesPengajuanActivity extends AppCompatActivity {

    long delay = 1000;
    long last_text_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses_pengajuan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
        TextView konfirmasi = findViewById(R.id.konfirmasi);
        final TextView lihat_database = findViewById(R.id.lihat_database);
        TextView title_tugas = findViewById(R.id.title_tugas);
        TextView title_penugasan = findViewById(R.id.title_penugasan);

        final Handler handler = new Handler();


        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_tugas.setTypeface(opensans_bold);
        title_penugasan.setTypeface(opensans_bold);

        final MaterialEditText inputReferal = findViewById(R.id.inputReferal);
        final ProgressBar inputProgressBarReferal = findViewById(R.id.input_progess_bar_referal);

        inputReferal.setEnabled(false);
        lihat_database.setEnabled(false);
        inputProgressBarReferal.setVisibility(View.GONE);

        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProsesPengajuanActivity.this);
                // Setting Dialog Title
                alertDialog.setTitle("KONFIRMASI");

                // Setting Dialog Message
                alertDialog.setMessage("Apakah Anda sudah menghubungi pemohon dan mengkonfirmasi bahwa benar adanya pengajuan tersebut.");

                // Setting OK Button
                alertDialog.setPositiveButton("Ya, saya sudah konfirmasi data", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        inputReferal.setEnabled(true);
                        lihat_database.setEnabled(true);

                        inputReferal.requestFocus();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });

        lihat_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LihatDatabaseCRHActivity.class);
                startActivity(intent);
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
