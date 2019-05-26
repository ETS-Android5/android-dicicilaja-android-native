package com.dicicilaja.app.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.API.Model.ProductCatalog.ProductCatalog;
import com.dicicilaja.app.API.Model.Transaction;
import com.dicicilaja.app.API.Model.Transaksi;
import com.dicicilaja.app.Adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.Adapter.TransaksiAdapter;
import com.dicicilaja.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_tujuan)
    TextView titleTujuan;
    @BindView(R.id.recycler_transaksi)
    RecyclerView recyclerTransaksi;

    private List<Transaksi> transaksiList;
    TransaksiAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        transaksiList = new ArrayList<>();
        adapter = new TransaksiAdapter(transaksiList, this);

        recyclerTransaksi.setLayoutManager(new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerTransaksi.setHasFixedSize(true);
        recyclerTransaksi.setAdapter(adapter);

        createDummyData();
    }

    private void createDummyData() {
        int[] covers = new int[]{
                R.drawable.pln,
                R.drawable.bpjs,
                R.drawable.pdam};

        //motor
        Transaksi a = new Transaksi("14 Mar", 1, 450, "Xiaomi A2 Lite", "Sedang diproses");
        transaksiList.add(a);

        a = new Transaksi("1 Mar", 1, 450, "Xiaomi A2 Lite", "Sudah diproses");
        transaksiList.add(a);

        adapter.notifyDataSetChanged();
    }
}
