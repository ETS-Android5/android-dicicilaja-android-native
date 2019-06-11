package com.dicicilaja.app.NewSimulation.ui.newsimulation.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.NewSimulation.data.TipeObjek.Attributes;
import com.dicicilaja.app.NewSimulation.data.TipeObjek.DataItem;
import com.dicicilaja.app.NewSimulation.data.TipeObjek.Links;
import com.dicicilaja.app.NewSimulation.ui.bantuan.BantuanNewSimulationActivity;
import com.dicicilaja.app.NewSimulation.ui.newsimulation.adapter.TipeObjekAdapter;
import com.dicicilaja.app.R;

import java.util.ArrayList;
import java.util.List;

public class NewSimulationActivity extends AppCompatActivity {

    List<DataItem> dataItems;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_tipeobjek)
    RecyclerView recyclerTipeobjek;
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

        dataItems = new ArrayList<>();
        addData();

//        if (Build.VERSION.SDK_INT >= 21) {
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
//        }

        final RecyclerView recyclerView = findViewById(R.id.recycler_tipeobjek);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));


        recyclerView.setAdapter(new TipeObjekAdapter(dataItems, R.layout.card_tipe_objek, getBaseContext()));

    }

    public void addData() {
        dataItems.add(
                new DataItem(
                        new Attributes("car"),
                        new Links(null, null, null, null, null),
                        1,
                        "tipeobjek"));

        dataItems.add(
                new DataItem(
                        new Attributes("mcy"),
                        new Links(null, null, null, null, null),
                        2,
                        "tipeobjek"));

        Log.d("DATADATA", "jumlah : " + dataItems.size());
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
}
