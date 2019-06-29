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
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.branch.Branch;
import com.dicicilaja.app.Activity.BusinessReward.dataAPI.claimReward.ClaimReward;
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

    String branchId, areaId, crhId, productCatalogId, statusId, noResi, noPo, ongkir;
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
//        name = intent.getStringExtra("Name");
//        point = (int) intent.getIntExtra("Point", 0);
//        thumbnail = intent.getIntExtra("Thumbnail", 0);

        final SessionManager session = new SessionManager(getBaseContext());
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
                                Log.d("SIZENYAAAA", String.valueOf(response.body().getData().size()));

                                for(int i = 0;i < sizenya;i++){
                                    Log.d("DATANYAAA", response.body().getData().get(i).getName() + cabangText);
                                    if(response.body().getData().get(i).getName().equals(cabangText)){
                                        branchId = String.valueOf(response.body().getData().get(i).getId());
                                        Log.d("Profile4", branchId);
                                        areaId = response.body().getData().get(i).getAreaId();
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

//        if(noNpwp == 0){
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.axi_dasboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notif) {
            Intent intent = new Intent(getBaseContext(), NotificationActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.klaim)
    public void onViewClicked() {
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
                        ApiService apiService = ApiClient.getClient().create(ApiService.class);
                        Call<ClaimReward> call = apiService.postClaimReward(profileId, branchId, areaId, crhId, productCatalogId, statusId, "0", "0", "0", "0");
                        call.enqueue(new Callback<ClaimReward>() {
                            @Override
                            public void onResponse(Call<ClaimReward> call, Response<ClaimReward> response) {
                                try {
                                    Log.d("Responnya", String.valueOf(response));

                                    Date c = Calendar.getInstance().getTime();

                                    SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                                    String date = df.format(c);

                                    Toast.makeText(getBaseContext(),"Berhasil Klaim",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getBaseContext(), RedeemConfirmationActivity.class);
                                    intent.putExtra("DATE", date);
                                    intent.putExtra("PRODUK_ID", product_id);
//                                    intent.putExtra("Name", name);
//                                    intent.putExtra("Point", point);
//                                    intent.putExtra("Thumbnail", thumbnail);
                                    startActivity(intent);
                                } catch(Exception ex) {

                                }
                            }

                            @Override
                            public void onFailure(Call<ClaimReward> call, Throwable t) {

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
}
