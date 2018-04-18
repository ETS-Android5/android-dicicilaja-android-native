package id.variable.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import id.variable.dicicilaja.Adapter.ListPartnerAdapter;
import id.variable.dicicilaja.Adapter.RequestAdapter;
import id.variable.dicicilaja.Adapter.RuteAdapter;
import id.variable.dicicilaja.Content.PartnerModel;
import id.variable.dicicilaja.Content.PromoModel;
import id.variable.dicicilaja.Content.RekomendasiModel;
import id.variable.dicicilaja.Content.RuteModel;
import id.variable.dicicilaja.R;

import static android.widget.GridLayout.VERTICAL;

public class RutePerjalananActivity extends AppCompatActivity {

    private List<RuteModel> rute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rute_perjalanan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rute = new ArrayList<>();
        createDummyData();

        final RecyclerView recyclerView =  findViewById(R.id.recycler_rute);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RuteAdapter adapter = new RuteAdapter(rute, R.layout.card_rute, getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);



    }

    private void createDummyData() {
        for (int j = 1; j <= 2; j++) {
            rute.add(new RuteModel("1",
                    "DAY 2",
                    "Judul Rute",
                    "Deskripsi Rute"));
        }

        rute.add(new RuteModel("3","DAY 3","Judul Rute 3","Deskripsi Rute 3"));

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
