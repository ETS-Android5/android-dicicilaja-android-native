package com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Data;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.DetailKategori;
import com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.kategori.KategoriProduk;
import com.dicicilaja.app.BusinessReward.dataAPI.produk.Datum;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListAllProductAdapter;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.adapter.ListProductCatalogAdapter;
import com.dicicilaja.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogResultActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //    private List<Produk> productCatalogList;
    ListProductCatalogAdapter adapter;
    List<Datum> requests;

    List<String> stringList;

    @BindView(R.id.recycler_catalog)
    RecyclerView recyclerCatalog;

    String id, size;
    @BindView(R.id.kat_icon)
    ImageView katIcon;
    @BindView(R.id.sort_icon)
    ImageView sortIcon;

    ApiService apiService;
    RadioButton rb;
    RadioGroup rg;
    Button btnOk;

    final HashMap<Integer, String> KATEGORI_ITEMS = new HashMap<Integer, String>();
    final HashMap<Integer, String> KATEGORI_DATA = new HashMap<Integer, String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_result);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiService = ApiClient.getClient().create(ApiService.class);

        stringList=new ArrayList<>();

        stringList.clear();
        KATEGORI_DATA.clear();
        KATEGORI_ITEMS.clear();

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        size = intent.getStringExtra("SIZE");

        Log.d("IDNYAINITEH", id);
        Log.d("IDNYAINITEHSIZE", size);

        recyclerCatalog.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        recyclerCatalog.setHasFixedSize(true);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<DetailKategori> call = apiService.getDetailKategori(Integer.parseInt(id));
        call.enqueue(new Callback<DetailKategori>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<DetailKategori> call, Response<DetailKategori> response) {
                final Data dataItems = response.body().getData();
                final List<Included> dataItems2 = response.body().getIncluded();
                getSupportActionBar().setTitle(dataItems.getAttributes().getNama());

                Log.d("Cek1", "" + response.code());

                recyclerCatalog.setAdapter(new ListAllProductAdapter(dataItems, dataItems2, getBaseContext(), id, size));
            }

            @Override
            public void onFailure(Call<DetailKategori> call, Throwable t) {
                Log.d("dadada", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filter) {
            Intent intent = new Intent(getBaseContext(), FIlterActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                id = data.getStringExtra("ID");
//                size = data.getStringExtra("SIZE");
//
//                Log.d("kadieu", id);
//                Log.d("kadieu", size);
//            }
////        }
//    }

    @OnClick({R.id.kat_icon, R.id.sort_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kat_icon:

//                Call<KategoriProduk> call = apiService.getKategori();
//                call.enqueue(new Callback<KategoriProduk>() {
//                    @SuppressLint("WrongConstant")
//                    @Override
//                    public void onResponse(Call<KategoriProduk> call, Response<KategoriProduk> response) {
//                        final List<com.dicicilaja.app.BusinessReward.dataAPI.kategori.Datum> dataItems = response.body().getData();
//                        final List<com.dicicilaja.app.BusinessReward.dataAPI.kategori.Included> dataItems2 = response.body().getIncluded();
//
//                        for(int i=0;i<response.body().getData().size();i++) {
////                            stringList.add(response.body().getData().get(i).getAttributes().getNama());
//                            KATEGORI_DATA.put(i + 1, String.valueOf(response.body().getData().get(i).getId()));
//                            KATEGORI_ITEMS.put(response.body().getData().size(), String.valueOf(response.body().getData().get(i).getId()));
//                            stringList.add(response.body().getData().get(i).getAttributes().getNama());
//                        }
//
//                        final Dialog dialog = new Dialog(CatalogResultActivity.this);
//                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog.setContentView(R.layout.radio_button_dialog);
//
//                        rg = (RadioGroup) dialog.findViewById(R.id.radio_group);
//                        btnOk = (Button) dialog.findViewById(R.id.pilih_kat);
//
//                        for(int i=0;i<stringList.size();i++){
//                            rb = new RadioButton(CatalogResultActivity.this); // dynamically creating RadioButton and adding to RadioGroup.
//                            rb.setText(stringList.get(i));
//                            rg.addView(rb);
//                        }
//
//                        dialog.show();
//
//                        btnOk.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                String selected = KATEGORI_DATA.get(rg.getCheckedRadioButtonId());
//                                String size = KATEGORI_ITEMS.get(rg.getCheckedRadioButtonId());
////                                Log.d("selectednya", selected);
//
//                                Intent intent = new Intent(getBaseContext(), CatalogResultActivity.class);
//                                intent.putExtra("ID", String.valueOf(selected));
//                                intent.putExtra("SIZE", String.valueOf(size));
////                                startActivityForResult(intent, 1);
//                                setResult(RESULT_OK, intent);
//                                finish();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(Call<KategoriProduk> call, Throwable t) {
//                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CatalogResultActivity.this);
//                        alertDialog.setTitle("Perhatian");
//                        alertDialog.setMessage("Gagal memuat data, silahkan coba beberapa saat lagi.");
//
//                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                                startActivity(getIntent());
//                            }
//                        });
//                        alertDialog.show();
//                    }
//                });
                break;
            case R.id.sort_icon:
                break;
        }
    }
}
