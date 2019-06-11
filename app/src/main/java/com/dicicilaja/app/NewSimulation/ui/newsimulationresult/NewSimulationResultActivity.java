package com.dicicilaja.app.NewSimulation.ui.newsimulationresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_simulation_result);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Hasil Simulasi");

    }

    @OnClick({R.id.call_tasya, R.id.next, R.id.simulation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call_tasya:
                break;
            case R.id.next:
                break;
            case R.id.simulation:
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
            case R.id.notif:
                Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
