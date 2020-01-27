package com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.BusinessReward.dataAPI.claimReward.PostClaimReward;
import com.dicicilaja.app.BusinessReward.dataAPI.detailClaimReward.DetailClaimReward;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.GetCart;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Item;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiClient3;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.network.ApiService3;
import com.dicicilaja.app.BusinessReward.ui.Cart.CartActivity;
import com.dicicilaja.app.BusinessReward.ui.Cart.CartAdapter;
import com.dicicilaja.app.BusinessReward.ui.RedeemConfirm.RedeemConfirmationActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct2Activity extends AppCompatActivity {

    private static final String TAG = DetailProduct2Activity.class.getSimpleName();
    String id, name, alamat, tgl, no_transaksi, no_transaksi2, tgl_penukaran, status_pengiriman, produk_id, nama, nama_barang, gambar_barang, tot_point, alamatnya, area, area_id, cabang, cabang_id, no_ktp, no_npwp;
    int point, thumbnail, sisa, point_barang;
    ApiService3 apiService3;
    SessionManager session;
    String apiKey;
    List<Item> itemList;
    List<Included> includedList;
    @BindView(R.id.uploadid1)
    ImageView uploadid1;
    @BindView(R.id.tambah_alamat)
    RelativeLayout tambahAlamat;
    @BindView(R.id.ubahB)
    TextView ubahB;
    @BindView(R.id.ubah_form)
    RelativeLayout ubahForm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_alamat)
    TextView tvAlamat;
    @BindView(R.id.detail_user)
    LinearLayout detailUser;
    @BindView(R.id.tv_total_point)
    TextView tvTotalPoint;
    @BindView(R.id.tv_point_barang)
    TextView tvPointBarang;
    @BindView(R.id.tv_sisa_point)
    TextView tvSisaPoint;
    @BindView(R.id.klaim)
    Button klaim;
    @BindView(R.id.tv_cabang)
    TextView tvCabang;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;

    String profileId, date;
    int statusAlamat = 0;
    int totalPoint, sisaPoint;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product2);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();
        apiService3 = ApiClient3.getClient().create(ApiService3.class);
        nama = session.getName();
        profileId = session.getAxiId();
        nama = session.getName();
        no_ktp = getIntent().getStringExtra("NOKTP");
        no_npwp = getIntent().getStringExtra("NONPWP");

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        date = df.format(c);

        rvProduct.setLayoutManager(new LinearLayoutManager(this));

        loadData();
    }

    private void loadData() {
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
                    if (includedList.size() == 0) {
                        rvProduct.setVisibility(View.GONE);
                    } else {
                        rvProduct.setVisibility(View.VISIBLE);
                        rvProduct.setAdapter(new ProductSummaryAdapter(itemList, includedList, DetailProduct2Activity.this));
                    }
                }

            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.axi_dasboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notif) {
            Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.klaim)
    public void onViewClicked() {
        if (statusAlamat == 0) {
            Toast.makeText(this, "Mohon masukan alamat terlebih dahulu!", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailProduct2Activity.this);
            alertDialog.setTitle("Penukaran");
            alertDialog.setMessage("Apakah anda yakin ingin melakukan penukaran ?");
            alertDialog.setCancelable(false);

            alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    produk_id = "0";
                    ApiService apiService = ApiClient3.getClient().create(ApiService.class);
                    Call<PostClaimReward> call = apiService.postClaimReward2(profileId, nama, cabang_id, cabang, area_id, area, "0", null, produk_id, no_ktp + "/" + no_npwp, alamat, null, null, null, null, null, null, "5", null, "Bearer " + session.getToken());
                    call.enqueue(new Callback<PostClaimReward>() {
                        @Override
                        public void onResponse(Call<PostClaimReward> call, Response<PostClaimReward> response) {
                            if (response.isSuccessful()) {
                                setResult(RESULT_OK);
                                Intent intent = new Intent(getBaseContext(), RedeemConfirmationActivity.class);
                                intent.putExtra("DATE", date);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                            }
                        }

                        @Override
                        public void onFailure(Call<PostClaimReward> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t.getMessage());
                        }
                    });

                }
            });
            alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                tambahAlamat.setVisibility(View.GONE);
                ubahForm.setVisibility(View.VISIBLE);

                alamat = data.getStringExtra("ALAMATNYA");
                cabang = data.getStringExtra("CABANGNYA");
                area = data.getStringExtra("AREANYA");
                area_id = data.getStringExtra("AREAIDNYA");
                cabang_id = data.getStringExtra("CABANGIDNYA");

                tvAlamat.setText(data.getStringExtra("ALAMATNYA"));
                tvCabang.setText(data.getStringExtra("CABANGNYA"));
                tvNama.setText(nama);

                statusAlamat = 1;
            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    @OnClick({R.id.tambah_alamat, R.id.ubahB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tambah_alamat:
                Intent intent = new Intent(getBaseContext(), PilihCabangVendorActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.ubahB:
                Intent intent2 = new Intent(getBaseContext(), PilihCabangVendorActivity.class);
                startActivityForResult(intent2, 1);
                break;
        }
    }
}
