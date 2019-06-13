package com.dicicilaja.app.NewSimulation.ui.newsimulation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.NewSimulation.ui.bantuan.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.ui.motorcolleteral.MotorColleteralActivity;
import com.dicicilaja.app.NewSimulation.ui.newcolleteral.NewColleteralActivity;
import com.dicicilaja.app.NewSimulation.ui.newloan.NewLoanActivity;
import com.dicicilaja.app.NewSimulation.ui.newsimulationresult.NewSimulationResultActivity;
import com.dicicilaja.app.R;

public class NewSimulationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.card_view_car)
    CardView cardViewCar;
    @BindView(R.id.card_view_mcy)
    CardView cardViewMcy;
    @BindView(R.id.support)
    LinearLayout support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_simulation);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Simulasi");

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        if(getIntent().getStringExtra("finishingallact")!=null)
        {
            if(getIntent().getStringExtra("finishingallact").toLowerCase().equals("yes"))
            {
                NewColleteralActivity act1 = new NewColleteralActivity();
                NewLoanActivity act2 = new NewLoanActivity();
                MotorColleteralActivity act3 = new MotorColleteralActivity();
                BantuanNewSimulationActivity act4 = new BantuanNewSimulationActivity();
                NewSimulationResultActivity act5 = new NewSimulationResultActivity();

                act1.finish();
                act2.finish();
                act3.finish();
                act4.finish();
                act5.finish();
            }
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

    @OnClick(R.id.support)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), BantuanNewSimulationActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.card_view_car, R.id.card_view_mcy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_view_car:
                Intent car = new Intent(getBaseContext(), NewColleteralActivity.class);
                car.putExtra("tipe_objek_id","1");
                startActivity(car);
                break;
            case R.id.card_view_mcy:
                Intent mcy = new Intent(getBaseContext(), MotorColleteralActivity.class);
                mcy.putExtra("tipe_objek_id","2");
                startActivity(mcy);
                break;
        }
    }
}
