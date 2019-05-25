package com.dicicilaja.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewSimulationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_car)
    CardView btnCar;
    @BindView(R.id.btn_motorcycle)
    CardView btnMotorcycle;
    @BindView(R.id.title_support)
    TextView titleSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_simulation);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Simulasi");


    }

    @OnClick({R.id.btn_car, R.id.btn_motorcycle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_car:
                Intent intent = new Intent(getBaseContext(), NewColleteralActivity.class);
                intent.putExtra("Type", "Car");
                startActivity(intent);
                break;
            case R.id.btn_motorcycle:
                Intent intent2 = new Intent(getBaseContext(), NewColleteralActivity.class);
                intent2.putExtra("Type", "Motorcycle");
                startActivity(intent2);
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
            case R.id.home:
                super.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
