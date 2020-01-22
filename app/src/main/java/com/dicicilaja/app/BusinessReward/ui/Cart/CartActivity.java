package com.dicicilaja.app.BusinessReward.ui.Cart;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.delCart.DelCart;
import com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp.FotoKtpNpwp;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.GetCart;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Item;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiClient3;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.network.ApiService3;
import com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity.DetailProduct2Activity;
import com.dicicilaja.app.BusinessReward.ui.KtpNpwp.activity.UploadKTPActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartCallback {


    public ProgressDialog progress;
    ApiService apiService;
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
    @BindView(R.id.cannot_claim)
    RelativeLayout cannotClaim;
    int point, totalPoint, sisaPoint;
    public static String ktpnpwp, no_ktp, no_npwp;
    private CartAdapter cartAdapter;

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

        claim.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.button_color)));

        //no_ktp = getIntent().getStringExtra("NOKTP");
        //ktpnpwp = getIntent().getStringExtra("KTP");
        //no_npwp = getIntent().getStringExtra("NONPWP");

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);

        apiService = ApiClient.getClient().create(ApiService.class);
        apiService3 = ApiClient3.getClient().create(ApiService3.class);

        ApiService service = ApiClient3.getClient().create(ApiService.class);

        Call<FotoKtpNpwp> callKtp = service.checkFotoNpwp(session.getUserId());
        callKtp.enqueue(new Callback<FotoKtpNpwp>() {
            @Override
            public void onResponse(Call<FotoKtpNpwp> call, Response<FotoKtpNpwp> response) {
                if (response.isSuccessful()) {
                    final List<com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp.Datum> dataItems = response.body().getData();
                    if (dataItems.size() == 0) {
                        ktpnpwp = "Tidak";
                    } else {
                        ktpnpwp = "Ada";
                        no_ktp = response.body().getData().get(0).getAttributes().getNoKtp();
                        no_npwp = response.body().getData().get(0).getAttributes().getNoNpwp();
                    }
                }

            }

            @Override
            public void onFailure(Call<FotoKtpNpwp> call, Throwable t) {
                Log.d("sizenyaaa", "data: " + t.getMessage());
            }
        });

        itemList = new ArrayList<>();
        includedList = new ArrayList<>();

        cartAdapter = new CartAdapter(itemList, includedList, R.layout.card_cart, getBaseContext(), CartActivity.this);

        recyclerCart.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerCart.setAdapter(cartAdapter);

        loadData();
    }

    private void toggleButton(boolean isCanClaim) {
        if (isCanClaim) {
            cannotClaim.setVisibility(View.GONE);
            claim.setEnabled(true);
            claim.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.button_color)));
        } else {
            cannotClaim.setVisibility(View.VISIBLE);
            claim.setEnabled(false);
            claim.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorIndicator)));
        }
    }

    private void loadData() {
        progress.show();
        Call<GetCart> call = apiService3.getCart(apiKey);
        call.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart> response) {
                if (response.isSuccessful()) {
                    itemList.clear();
                    includedList.clear();
                    itemList.addAll(response.body().getData().getAttributes().getItems());
                    includedList.addAll(response.body().getIncluded());
                    point = Integer.valueOf(getIntent().getStringExtra("point"));
                    totalPoint = response.body().getData().getAttributes().getTotalPoints();
                    sisaPoint = point - totalPoint;

                    toggleButton(sisaPoint >= 0);

                    tvTotalPoint.setText(point + " POINT");
                    tvPointBarang.setText(totalPoint + " POINT");
                    tvSisaPoint.setText(sisaPoint + " POINT");
                    if (itemList.size() == 0) {
                        recyclerCart.setVisibility(View.GONE);
                        order.setVisibility(View.VISIBLE);
                    } else {
                        recyclerCart.setVisibility(View.VISIBLE);
                        order.setVisibility(View.GONE);
                        cartAdapter.notifyDataSetChanged();
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
        //loadData();
        if (ktpnpwp.equals("Tidak"))
            new AlertDialog.Builder(this)
                    .setTitle("Perhatian!")
                    .setMessage(getString(R.string.notif_ktp))
                    .setPositiveButton("Upload", (dialog, which) -> {
                        startActivity(new Intent(CartActivity.this, UploadKTPActivity.class));
                    })
                    .show();
        else
            startActivityForResult(
                    new Intent(this, DetailProduct2Activity.class)
                            .putExtra("point", String.valueOf(point))
                            .putExtra("NOKTP", no_ktp)
                            .putExtra("NONPWP", no_npwp),
                    97
            );
    }

    @Override
    public void onUpdateCart() {
        itemList.clear();
        loadData();
        setResult(RESULT_OK);
    }

    @Override
    public void onDeleteCart(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Yakin dihapus?")
                .setMessage("Barang akan hilang dari keranjang, tapi Anda bisa menambahkannya lagi dari katalog.")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    ApiService3 apiService3 = ApiClient3.getClient().create(ApiService3.class);

                    Call<DelCart> call = apiService3.delCart(apiKey, itemList.get(position).getProductId());
                    call.enqueue(new Callback<DelCart>() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onResponse(Call<DelCart> call, Response<DelCart> response) {
                            //((Activity) context).finish();
                            Toast.makeText(CartActivity.this, "Produk berhasil di hapus", Toast.LENGTH_SHORT).show();

                            if (itemList.size() == 1) {
                                setResult(RESULT_OK);
                                finish();
                            } else {
                                itemList.clear();
                                loadData();
                                setResult(RESULT_OK);
                            }
                        }

                        @Override
                        public void onFailure(Call<DelCart> call, Throwable t) {
                        }
                    });
                })
                .setNegativeButton("Batal", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 97 && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
