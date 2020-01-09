package com.dicicilaja.app.BusinessReward.ui.Cart;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.GetCart;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Item;
import com.dicicilaja.app.BusinessReward.network.ApiClient3;
import com.dicicilaja.app.BusinessReward.network.ApiService3;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {


    public ProgressDialog progress;
    ApiService3 apiService3;
    SessionManager session;
    String apiKey;
    List<Item> itemList;
    List<Included> includedList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_cart)
    RecyclerView recyclerCart;
    @BindView(R.id.no_order)
    ImageView noOrder;
    @BindView(R.id.order)
    LinearLayout order;
    @BindView(R.id.claim)
    Button claim;
    @BindView(R.id.tv_total_point)
    TextView tvTotalPoint;
    @BindView(R.id.tv_point_barang)
    TextView tvPointBarang;
    @BindView(R.id.tv_sisa_point)
    TextView tvSisaPoint;
    int point, totalPoint, sisaPoint;
    public static String ktpnpwp, no_ktp, no_npwp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
        no_ktp = getIntent().getStringExtra("NOKTP");
        ktpnpwp = getIntent().getStringExtra("KTP");
        no_npwp = getIntent().getStringExtra("NONPWP");

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);

        apiService3 = ApiClient3.getClient().create(ApiService3.class);

        recyclerCart.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        loadData();
    }

    private void loadData() {
        progress.show();
        Call<GetCart> call = apiService3.getCart(apiKey);
        call.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart> response) {
                if (response.isSuccessful()) {
                    itemList = response.body().getData().getAttributes().getItems();
                    includedList = response.body().getIncluded();
                    point = Integer.valueOf(getIntent().getStringExtra("point"));
                    totalPoint = response.body().getData().getAttributes().getTotalPoints();
                    sisaPoint = point - totalPoint;

                    tvTotalPoint.setText(point + " POINT");
                    tvPointBarang.setText(totalPoint + " POINT");
                    tvSisaPoint.setText(sisaPoint + " POINT");
                    if (itemList.size() == 0) {
                        recyclerCart.setVisibility(View.GONE);
                        order.setVisibility(View.VISIBLE);
                    } else {
                        recyclerCart.setVisibility(View.VISIBLE);
                        order.setVisibility(View.GONE);
                        recyclerCart.setAdapter(new CartAdapter(itemList, includedList, R.layout.card_cart, getBaseContext()));
                    }
                    progress.dismiss();
                }
                progress.dismiss();

            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }

    @OnClick(R.id.claim)
    public void onViewClicked() {
        loadData();
    }
}
