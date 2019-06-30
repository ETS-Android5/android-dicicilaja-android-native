package com.dicicilaja.app.Activity.BusinessReward.ui.DetailProduct.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.area.Area;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.branch.Branch;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.claimReward.ClaimReward;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailClaimReward.DetailClaimReward;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailProduk.DetailProduk;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.fotoKtpNpwp.FotoKtpNpwp;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.point.Datum;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.point.Point;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiClient;
import com.dicicilaja.app.Activity.BusinessReward.network.ApiService;
import com.dicicilaja.app.Activity.BusinessReward.network.InterfaceBranch;
import com.dicicilaja.app.Activity.BusinessReward.ui.BusinessReward.activity.BusinesRewardActivity;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.Activity.RedeemConfirmationActivity;
import com.dicicilaja.app.Activity.RegisterMaxi3Activity;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.AXIDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemAxiDetail.Data;
import com.dicicilaja.app.Activity.BusinessReward.ui.KtpNpwp.activity.UploadKTPActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

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

public class DetailProductActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.spek_barang_detail)
    RelativeLayout spekBarangDetail;
    @BindView(R.id.klaim)
    Button klaim;

    Data itemDetail;

    String name;
    int point, thumbnail, id;
    @BindView(R.id.deskripsi)
    TextView deskripsi;

    SessionManager session;
    String apiKey;

    int pointR, pointS;
    String noNpwp, noKtp;
    @BindView(R.id.pbDetail)
    ProgressBar pbDetail;

    String branchId, areaId, crhId, productCatalogId, statusId, noResi, noPo, ongkir, namaCabang, namaArea, ktpnpwp;
    String profileId, alamatC;
    String cabangText;
    String fotoKtp, fotoNpwp, nomorKtp, nomorNpwp;
    int product_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
        pbDetail.setVisibility(View.VISIBLE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);

        session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();
        Log.d("apinya", session.getToken());


        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        ApiService apiService2 =
                ApiClient.getClient2().create(ApiService.class);

        InterfaceAxiDetail apiService5 =
                RetrofitClient.getClient().create(InterfaceAxiDetail.class);

        InterfaceBranch apiService6 =
                RetrofitClient.getClient().create(InterfaceBranch.class);


        Call<AXIDetail> callProfile = apiService5.getDetail(apiKey);
        callProfile.enqueue(new Callback<AXIDetail>() {
            @Override
            public void onResponse(Call<AXIDetail> call, Response<AXIDetail> response) {
                if (response.isSuccessful()) {
                    pbDetail.setVisibility(View.GONE);
                    itemDetail = response.body().getData();
                    noNpwp = itemDetail.getNpwpNo();
                    noKtp = itemDetail.getNoKtp();

                    profileId = itemDetail.getAxiId();
                    Log.d("Profile1", profileId);
                    alamatC = " ";
                    Log.d("Profile2", alamatC);
                    cabangText = itemDetail.getCabang();
//                    Log.d("Profile3", cabangText);

                    Log.d("popo", String.valueOf(noKtp));
                    Log.d("popo", String.valueOf(noNpwp));

                    Call<Branch> callBranch = apiService6.getCabang();
                    callBranch.enqueue(new Callback<Branch>() {
                        @Override
                        public void onResponse(Call<Branch> call, Response<Branch> response) {
                            if (response.isSuccessful()) {
                                pbDetail.setVisibility(View.GONE);
                                int sizenya = response.body().getData().size();
//                                Log.d("SIZENYAAAA", String.valueOf(response.body().getData().size()));

                                for(int i = 0;i < sizenya;i++){
                                    Log.d("DATANYAAA", response.body().getData().get(i).getName() + cabangText);
                                    if(response.body().getData().get(i).getName().equals(cabangText)){

                                        branchId = String.valueOf(response.body().getData().get(i).getId());
                                        namaCabang = String.valueOf(response.body().getData().get(i).getName());
//                                        Log.d("Profile4", branchId);
                                        areaId = response.body().getData().get(i).getAreaId();

                                        Call<Area> callArea = apiService6.getArea();
                                        callArea.enqueue(new Callback<Area>() {
                                            @Override
                                            public void onResponse(Call<Area> call2, Response<Area> response2) {
                                                if (response2.isSuccessful()) {

                                                    int sizenya2 = response2.body().getData().size();
                                                    Log.d("SIZENYAAAA", String.valueOf(response2.body().getData().size()));

                                                    for(int j = 0;j < sizenya2; j++){
                                                        Log.d("SIZENYAAAA", String.valueOf(response2.body().getData().get(j).getId()));
                                                        Log.d("SIZENYAAAA", areaId);
                                                        if(response2.body().getData().get(j).getId() == Integer.parseInt(areaId)){

                                                            namaArea = String.valueOf(response2.body().getData().get(j).getName());

                                                            Log.d("Profile5", areaId);
                                                        }
                                                    }

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Area> call, Throwable t) {
                                                t.printStackTrace();
                                            }
                                        });

                                        Log.d("Profile5", areaId);
                                    }
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<Branch> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<AXIDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Call<FotoKtpNpwp> callKtp = apiService.getFoto(Integer.parseInt(session.getUserId()));
        callKtp.enqueue(new Callback<FotoKtpNpwp>() {
            @Override
            public void onResponse(Call<FotoKtpNpwp> call, Response<FotoKtpNpwp> response) {
                final List<com.dicicilaja.app.Activity.BusinessReward.dataAPI.fotoKtpNpwp.Datum> dataItems = response.body().getData();

                if(dataItems.size() != 0){
                    fotoKtp = dataItems.get(0).getAttributes().getFotoKtp();
                    fotoNpwp = dataItems.get(0).getAttributes().getFotoNpwp();
                    nomorKtp = dataItems.get(0).getAttributes().getNoKtp();
                    nomorNpwp = dataItems.get(0).getAttributes().getNoNpwp();
                }

            }

            @Override
            public void onFailure(Call<FotoKtpNpwp> call, Throwable t) {

            }
        });

        Call<Point> call2 = apiService.getPoint(Integer.parseInt(session.getUserId()));
        call2.enqueue(new Callback<Point>() {
            @Override
            public void onResponse(Call<Point> call, Response<Point> response2) {
                final List<Datum> dataItems = response2.body().getData();
                pointR = response2.body().getData().get(0).getAttributes().getPointReward();
            }

            @Override
            public void onFailure(Call<Point> call, Throwable t) {

            }
        });

        Call<DetailProduk> call = apiService.getDetailProduk(Integer.valueOf(id));
        call.enqueue(new Callback<DetailProduk>() {
            @Override
            public void onResponse(Call<DetailProduk> call, Response<DetailProduk> response) {
                titleBarang.setText(response.body().getData().getAttributes().getNama());
                tvPoint.setText(response.body().getData().getAttributes().getPoint() + " Point");
                Glide.with(getBaseContext()).load(response.body().getData().getAttributes().getFoto()).into(barangPicture);
                deskripsi.setText(response.body().getData().getAttributes().getDeskripsi());

                pointS = response.body().getData().getAttributes().getPoint();
                product_id = response.body().getData().getId();
//                Log.d("popos", String.valueOf(pointS));
                productCatalogId = String.valueOf(response.body().getData().getId());
                Log.d("Profile6", productCatalogId);
                statusId = String.valueOf(5);
                Log.d("Profile7", statusId);
            }

            @Override
            public void onFailure(Call<DetailProduk> call, Throwable t) {

            }
        });

        klaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pointR >= pointS) {
                    if (((noKtp == null || noKtp.trim().length() == 0 || noKtp.equals("0")) && (noNpwp == null || noNpwp.trim().length() == 0 || noNpwp.equals("0"))) || ((nomorKtp == null || nomorKtp.trim().length() == 0 || nomorKtp.equals("0")) && (nomorNpwp == null || nomorNpwp.trim().length() == 0 || nomorNpwp.equals("0")) && (fotoKtp == null || fotoKtp.trim().length() == 0 || fotoKtp.equals("0")) && (fotoNpwp == null || fotoNpwp.trim().length() == 0 || fotoNpwp.equals("0")))) {
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
                                ktpnpwp = nomorKtp + "/" + nomorNpwp;
                                ApiService apiService = ApiClient.getClient().create(ApiService.class);

                                Log.d("datainput", profileId);
                                Log.d("datainput", session.getName());
                                Log.d("datainput", branchId);
                                Log.d("datainput", namaCabang);
                                Log.d("datainput", areaId);
//                                Log.d("datainput", namaArea);
                                Log.d("datainput", crhId);
                                Log.d("datainput", productCatalogId);
                                Log.d("datainput", ktpnpwp);
                                Log.d("datainput", statusId);

                                Call<DetailClaimReward> call = apiService.postClaimReward(profileId, session.getName(), branchId, namaCabang, areaId, namaArea, crhId, null, productCatalogId, ktpnpwp, null, null, null, null, null, null, null, statusId, null);
                                call.enqueue(new Callback<DetailClaimReward>() {
                                    @Override
                                    public void onResponse(Call<DetailClaimReward> call, Response<DetailClaimReward> response) {
                                        try {
                                            Log.d("Responnya", String.valueOf(response.code()));

                                            Date c = Calendar.getInstance().getTime();

                                            SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                                            String date = df.format(c);

                                            Toast.makeText(getBaseContext(),"Berhasil Klaim",Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(getBaseContext(), RedeemConfirmationActivity.class);
                                            intent.putExtra("DATE", date);
                                            intent.putExtra("ALAMAT", String.valueOf(response.body().getData().getAttributes().getAlamat()));
                                            intent.putExtra("NO_TRANSAKSI", response.body().getData().getId());
                                            intent.putExtra("NO_TRANSAKSI2", String.valueOf(response.body().getData().getAttributes().getNoResi()));
                                            intent.putExtra("TGL_PENUKARAN", response.body().getData().getAttributes().getCreatedAt());
                                            intent.putExtra("STATUS_PENGIRIMAN", response.body().getData().getAttributes().getStatusId());
                                            intent.putExtra("PRODUK_ID", response.body().getData().getAttributes().getProductCatalogId());
                                            startActivity(intent);

                                        } catch(Exception ex) {

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<DetailClaimReward> call, Throwable t) {

                                    }
                                });

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
