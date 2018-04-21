package id.variable.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import id.variable.dicicilaja.R;

public class AjukanPengajuanActivity extends AppCompatActivity {

    MaterialSpinner spinnerJaminan,spinnerArea,spinnerTenor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajukan_pengajuan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        final List<String> JAMINAN_ITEMS = new ArrayList<>();
        final HashMap<Integer, String> JAMINAN_DATA = new HashMap<Integer, String>();

//        InterfaceSimulation apiServiceColleteral =
//                NewRetrofitClient.getClient().create(InterfaceSimulation.class);
//
//        Call<Colleteral> callcolleteral = apiServiceColleteral.getColleteral();
//        callcolleteral.enqueue(new Callback<Colleteral>() {
//            @Override
//            public void onResponse(Call<Colleteral> call, Response<Colleteral> response) {
//
//                JAMINAN_ITEMS.clear();
//                JAMINAN_DATA.clear();
//
//                for ( int i = 0; i < response.body().getData().size(); i++ ) {
//                    JAMINAN_DATA.put(response.body().getData().get(i).getId(), response.body().getData().get(i).getId().toString());
//                    JAMINAN_ITEMS.add(response.body().getData().get(i).getName());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Colleteral> call, Throwable t) {
//                JAMINAN_DATA.clear();
//                JAMINAN_ITEMS.clear();
//                Log.e("Error", t.getMessage());
//            }
//        });
//
//        ArrayAdapter<String> jaminan_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, JAMINAN_ITEMS);
//        jaminan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinnerJaminan = findViewById(R.id.spinnerJaminan);
//        spinnerJaminan.setAdapter(jaminan_adapter);

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
