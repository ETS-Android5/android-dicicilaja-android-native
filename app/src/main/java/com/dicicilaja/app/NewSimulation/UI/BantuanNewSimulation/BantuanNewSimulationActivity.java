package com.dicicilaja.app.NewSimulation.UI.BantuanNewSimulation;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.R;

public class BantuanNewSimulationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.yes)
    Button yes;
    @BindView(R.id.no)
    Button no;
    @BindView(R.id.hubungi_tasya)
    TextView hubungiTasya;
    @BindView(R.id.support_team)
    TextView supportTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan_new_simulation);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bantuan");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
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

    @OnClick({R.id.yes, R.id.no, R.id.hubungi_tasya, R.id.support_team})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yes:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BantuanNewSimulationActivity.this);
                alertDialog.setMessage("Terima kasih atas penilaian Anda");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alertDialog.show();
                break;
            case R.id.no:
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(BantuanNewSimulationActivity.this);
                alertDialog2.setMessage("Terima kasih atas penilaian Anda");

                alertDialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alertDialog2.show();
                break;

            case R.id.hubungi_tasya:
                String phone = "08111465005";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
                break;
            case R.id.support_team:
                String phone2 = "08111465005";
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone2, null));
                startActivity(intent2);
                break;
        }
    }
}
