package com.dicicilaja.app.BusinessReward.ui.DetailProduct.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.BusinessReward.ui.RedeemConfirm.RedeemConfirmationActivity;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.AXIDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.Data;
import com.dicicilaja.app.BusinessReward.dataAPI.area.Area;
import com.dicicilaja.app.BusinessReward.dataAPI.branch.Branch;
import com.dicicilaja.app.BusinessReward.dataAPI.detailClaimReward.DetailClaimReward;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.network.InterfaceBranch;
import com.dicicilaja.app.BusinessReward.ui.BusinessReward.activity.BusinesRewardActivity;
import com.dicicilaja.app.BusinessReward.ui.KtpNpwp.activity.UploadKTPActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailProductActivity extends AppCompatActivity {

    public static String ktpnpwp, no_ktp, no_npwp;

    Data itemDetail;

    String name;
    int point, thumbnail, id;

    SessionManager session;
    String apiKey;

    int pointProduk;
    String noNpwp, noKtp, pointUser;

    String branchId, areaId, crhId, productCatalogId, statusId, noResi, noPo, ongkir, namaCabang, namaArea;
    String profileId, alamatC;
    String cabangText;
    String fotoKtp, fotoNpwp, nomorKtp, nomorNpwp;
    int product_id;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.progressBar)
//    MaterialProgressBar progressBar;
    @BindView(R.id.barang_picture)
    ImageView barangPicture;
    @BindView(R.id.barang_image)
    RelativeLayout barangImage;
    @BindView(R.id.title_barang)
    TextView titleBarang;
    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.barang_detail)
    RelativeLayout barangDetail;
    @BindView(R.id.spek_title)
    TextView spekTitle;
    @BindView(R.id.deskripsi)
    TextView deskripsi;
    @BindView(R.id.spek_barang_detail)
    RelativeLayout spekBarangDetail;
    @BindView(R.id.klaim)
    Button klaim;

    String gambar_barang;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);

//        progressBar.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        gambar_barang = getIntent().getStringExtra("IMAGE");

        productCatalogId = String.valueOf(getIntent().getIntExtra("ID", 0));
        titleBarang.setText(getIntent().getStringExtra("TITLE"));
        tvPoint.setText(getIntent().getIntExtra("POINT_PRODUCT", 0) + " POINT");
        Glide.with(getBaseContext()).load(getIntent().getStringExtra("IMAGE")).into(barangPicture);

        String textt = getIntent().getStringExtra("DETAIL").replace("\\n", "\n");
        deskripsi.setText(textt);
        pointUser = getIntent().getStringExtra("POINT_REWARD");
        pointProduk = getIntent().getIntExtra("POINT_PRODUCT", 0);
        no_ktp = getIntent().getStringExtra("NOKTP");
        ktpnpwp = getIntent().getStringExtra("KTP");
        no_npwp = getIntent().getStringExtra("NONPWP");
        id = intent.getIntExtra("ID", 0);

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();
        Log.d("apinya", session.getToken());


//        Call<DetailProduk> call = apiService.getDetailProduk(Integer.valueOf(id));
//        call.enqueue(new Callback<DetailProduk>() {
//            @Override
//            public void onResponse(Call<DetailProduk> call, Response<DetailProduk> response) {
//                titleBarang.setText(response.body().getData().getAttributes().getNama());
//                tvPoint.setText(response.body().getData().getAttributes().getPoint() + " Point");
//                Glide.with(getBaseContext()).load(response.body().getData().getAttributes().getFoto()).into(barangPicture);
//
//                String textt = response.body().getData().getAttributes().getDeskripsi().replace("\\n", "\n");
//                deskripsi.setText(textt);
//                pointS = response.body().getData().getAttributes().getPoint();
//                product_id = response.body().getData().getId();
////                Log.d("popos", String.valueOf(pointS));
//                productCatalogId = String.valueOf(response.body().getData().getId());
//                Log.d("Profile6", productCatalogId);
//                statusId = String.valueOf(5);
//                Log.d("Profile7", statusId);
//            }
//
//            @Override
//            public void onFailure(Call<DetailProduk> call, Throwable t) {
//
//            }
//        });

        klaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);

                if (Integer.valueOf(pointUser)>= pointProduk) {
                    if (ktpnpwp.equals("Tidak")) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailProductActivity.this);
                        alertDialog.setTitle("Perhatian!");
                        alertDialog.setMessage("Kamu belum mengupload NPWP dan KTP. Segera upload terlebih dahulu agar kamu mendapatkan pajak yang lebih rendah.");

                        alertDialog.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getBaseContext(), UploadKTPActivity.class);
                                startActivity(intent);
                            }
                        });
                        alertDialog.show();

                    } else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailProductActivity.this);
                        alertDialog.setTitle("Klaim Produk");
                        alertDialog.setMessage("Apakah kamu setuju ingin menukarkan point dengan produk ini?");
                        crhId = String.valueOf(0);
                        alertDialog.setPositiveButton("Setuju", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ApiService apiService = ApiClient.getClient().create(ApiService.class);

                                profileId = session.getAxiId();
                                name = session.getName();
                                crhId = String.valueOf(0);
                                no_ktp = getIntent().getStringExtra("NOKTP");
                                statusId = String.valueOf(5);

//                                Log.d("datainput", profileId);
//                                Log.d("datainput", name);
//                                Log.d("datainput", branchId);
//                                Log.d("datainput", namaCabang);
//                                Log.d("datainput", areaId);
//                                Log.d("datainput", namaArea);
//                                Log.d("datainput", crhId);
//                                Log.d("datainput", productCatalogId);
//                                Log.d("datainput", no_ktp);
//                                Log.d("datainput", statusId);

                                Date c = Calendar.getInstance().getTime();

                                SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                                String date = df.format(c);

                                Toast.makeText(getBaseContext(), "Berhasil Klaim", Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(getBaseContext(), DetailProduct2Activity.class);
                                Log.d("Tanggalnya", date);
                                intent.putExtra("DATE", date);
                                intent.putExtra("PROFILE_ID", String.valueOf(profileId));
                                intent.putExtra("NAMA", session.getName());
                                intent.putExtra("NOKTP", no_ktp);
                                intent.putExtra("NONPWP", no_npwp);
                                intent.putExtra("PRODUK_ID", productCatalogId);
//                                intent.putExtra("ALAMAT", String.valueOf(response.body().getData().getAttributes().getAlamat()));
//                                intent.putExtra("NO_TRANSAKSI", response.body().getData().getId());
//                                intent.putExtra("NO_TRANSAKSI2", String.valueOf(response.body().getData().getAttributes().getNoResi()));
//                                intent.putExtra("TGL_PENUKARAN", response.body().getData().getAttributes().getCreatedAt());
//                                intent.putExtra("STATUS_PENGIRIMAN", response.body().getData().getAttributes().getStatusId());
//
//                                intent.putExtra("PRODUK_ID", response.body().getData().getAttributes().getProductCatalogId());
                                intent.putExtra("TOTAL_POINT", pointUser);
                                intent.putExtra("POINT_BARANG", pointProduk);
//                                                                            Log.d("POINTAAA", String.valueOf(pointUser + pointProduk));
                                intent.putExtra("NAMA_BARANG", String.valueOf(titleBarang.getText()));
                                intent.putExtra("GAMBAR_BARANG", gambar_barang);
                                startActivity(intent);
                                finish();
//                                progressBar.setVisibility(View.GONE);


                            }
                        });
                        alertDialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        alertDialog.show();
                    }
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailProductActivity.this);
                    alertDialog.setTitle("Point Kurang!");
                    alertDialog.setMessage("Maaf point kamu kurang untuk penukaran barang ini!");

                    alertDialog.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                    startActivity(getIntent());
                            Intent intent = new Intent(getBaseContext(), BusinesRewardActivity.class);
                            startActivity(intent);
                        }
                    });
                    alertDialog.show();
                }

//                ApiService apiService = ApiClient.getClient().create(ApiService.class);
//
//                ApiService apiService2 =
//                        ApiClient.getClient2().create(ApiService.class);
//
//                InterfaceAxiDetail apiService5 =
//                        RetrofitClient.getClient().create(InterfaceAxiDetail.class);
//
//                InterfaceBranch apiService6 =
//                        RetrofitClient.getClient().create(InterfaceBranch.class);


//                Call<AXIDetail> callProfile = apiService5.getDetail(apiKey);
//                callProfile.enqueue(new Callback<AXIDetail>() {
//                    @Override
//                    public void onResponse(Call<AXIDetail> call, Response<AXIDetail> response) {
//                        if (response.isSuccessful()) {
//                            itemDetail = response.body().getData();
//                            noNpwp = itemDetail.getNpwpNo();
//                            noKtp = itemDetail.getNoKtp();
//
//                            profileId = itemDetail.getAxiId();
//                            Log.d("Profile1", profileId);
//                            alamatC = " ";
//                            Log.d("Profile2", alamatC);
//                            cabangText = itemDetail.getCabang();
////                    Log.d("Profile3", cabangText);
//
//                            Log.d("popo", String.valueOf(noKtp));
//                            Log.d("popo", String.valueOf(noNpwp));
//
//                            Call<Branch> callBranch = apiService6.getCabang();
//                            callBranch.enqueue(new Callback<Branch>() {
//                                @Override
//                                public void onResponse(Call<Branch> call, Response<Branch> response) {
//                                    if (response.isSuccessful()) {
//                                        int sizenya = response.body().getData().size();
////                                Log.d("SIZENYAAAA", String.valueOf(response.body().getData().size()));
//
//                                        for (int i = 0; i < sizenya; i++) {
//                                            Log.d("DATANYAAA", response.body().getData().get(i).getName() + cabangText);
//                                            if (response.body().getData().get(i).getName().equals(cabangText)) {
//
//                                                branchId = String.valueOf(response.body().getData().get(i).getId());
//                                                namaCabang = String.valueOf(response.body().getData().get(i).getName());
////                                        Log.d("Profile4", branchId);
//                                                areaId = response.body().getData().get(i).getAreaId();
//
//                                                Call<Area> callArea = apiService6.getArea();
//                                                callArea.enqueue(new Callback<Area>() {
//                                                    @Override
//                                                    public void onResponse(Call<Area> call2, Response<Area> response2) {
//                                                        if (response2.isSuccessful()) {
//                                                            int sizenya2 = response2.body().getData().size();
//                                                            Log.d("SIZENYAAAA", String.valueOf(response2.body().getData().size()));
//
//                                                            for (int j = 0; j < sizenya2; j++) {
//                                                                Log.d("SIZENYAAAA", String.valueOf(response2.body().getData().get(j).getId()));
//                                                                Log.d("SIZENYAAAA", areaId);
//                                                                if (response2.body().getData().get(j).getId() == Integer.parseInt(areaId)) {
//
//                                                                    namaArea = String.valueOf(response2.body().getData().get(j).getName());
//
//                                                                    Log.d("Profile5", areaId);
//                                                                }
//                                                            }
//
//                                                        }
//                                                    }
//
//                                                    @Override
//                                                    public void onFailure(Call<Area> call, Throwable t) {
//                                                        t.printStackTrace();
//                                                    }
//                                                });
//
//                                                Log.d("Profile5", areaId);
//                                            }
//                                        }
//
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<Branch> call, Throwable t) {
//                                    t.printStackTrace();
//                                }
//                            });
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AXIDetail> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });

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

    }
}
