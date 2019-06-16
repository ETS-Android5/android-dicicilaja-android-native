package com.dicicilaja.app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.API.Model.ProductCatalog.ProductCatalog;
import com.dicicilaja.app.Adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogResultActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<ProductCatalog> productCatalogList;
    ListProductCatalogAdapter adapter;

    @BindView(R.id.recycler_catalog)
    RecyclerView recyclerCatalog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_result);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productCatalogList = new ArrayList<>();
        adapter = new ListProductCatalogAdapter(productCatalogList, this);

        recyclerCatalog.setLayoutManager(new GridLayoutManager(getBaseContext(),
                2));
        recyclerCatalog.setHasFixedSize(true);
        recyclerCatalog.setAdapter(adapter);

        createDummyData();
    }

    private void createDummyData() {
        int[] covers = new int[]{
                R.drawable.motor1,
                R.drawable.motor2,
                R.drawable.motor3};

        //motor
        ProductCatalog a = new ProductCatalog("Yamaha Fino", 1, covers[0], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 2, covers[1], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 3, covers[2], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 4, covers[0], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 5, covers[1], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 6, covers[2], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 7, covers[0], 2000);
        productCatalogList.add(a);

        a = new ProductCatalog("Yamaha Fino", 8, covers[2], 2000);
        productCatalogList.add(a);

        adapter.notifyDataSetChanged();
    }
}
