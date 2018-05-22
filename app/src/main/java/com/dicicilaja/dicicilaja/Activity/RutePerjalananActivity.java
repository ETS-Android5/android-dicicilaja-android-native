package com.dicicilaja.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import com.dicicilaja.dicicilaja.Adapter.RuteAdapter;
import com.dicicilaja.dicicilaja.Content.RuteModel;
import com.dicicilaja.dicicilaja.R;

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
//        for (int j = 1; j <= 2; j++) {
//            rute.add(new RuteModel("1",
//                    "DAY 2",
//                    "Judul Rute",
//                    "Deskripsi Rute"));
//        }
        rute.add(new RuteModel("1   ","DAY 1","JAKARTA - INCHEON","Para peserta berkumpul dibandara international Soekarno-Hatta untuk bersama-sama berangkat menuju ibukota Korea - Seoul. Bermalam dipesawat."));
        rute.add(new RuteModel("2   ","DAY 2","INCHEON - SEOUL - GEMPO","Pagi in setibanya di Incheon, Anda akan diajak ke Seoul untuk mengunjungi Geyoengbok Palace, National Falk Museum [Setiap hari Selasa tutup, Maka akan digantikan ke Changdeok Palace] & melewati The Blue House. "));
        rute.add(new RuteModel("3   ","DAY 3","JEJU ISLAND"," Pagi ini Anda akan diajak untuk mengunjungi Seongson Sunrise Peak - gunung berapi yang dari dasar laut. Dilanjutkan menuju desa tradisional - Jeju Falk Village. Lalu Anda akan diajak melihat keindahan air terjun yang ada di Pulau Jeju."));
        rute.add(new RuteModel("4   ","DAY 4","INCHEON - JAKARTA"," Hari ini Anda akan diajak mengunjungi Red Pine Tree Shop. Setelah itu menuju Grocery Market untuk berbelanja oleh-oleh snack Korea atau makan siang. Kemuadian anda akan diantar menuju Airpot untuk melakukan penerbangan kembali ke Tanah Air."));
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
