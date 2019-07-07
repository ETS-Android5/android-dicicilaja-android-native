package com.dicicilaja.app.BusinessReward.ui.Transaction.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.detailClaimReward.DetailClaimReward;
import com.dicicilaja.app.BusinessReward.dataAPI.detailProduk.DetailProduk;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransactionActivity extends AppCompatActivity {
    SessionManager session;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_tujuan)
    TextView titleTujuan;
    @BindView(R.id.tv_alamat)
    TextView tvAlamat;
    @BindView(R.id.detail_user)
    LinearLayout detailUser;
    @BindView(R.id.pengirim)
    RelativeLayout pengirim;
    @BindView(R.id.title_penukaran)
    TextView titlePenukaran;
    @BindView(R.id.tv_no_trans)
    TextView tvNoTrans;
    @BindView(R.id.detail_penukaran)
    LinearLayout detailPenukaran;
    @BindView(R.id.tv_tgl_trans)
    TextView tvTglTrans;
    @BindView(R.id.detail_penukaran2)
    LinearLayout detailPenukaran2;
    @BindView(R.id.notrans)
    LinearLayout notrans;
    @BindView(R.id.tv_tgl_pen)
    TextView tvTglPen;
    @BindView(R.id.tv_tgl_pen2)
    TextView tvTglPen2;
    @BindView(R.id.tgltrans)
    LinearLayout tgltrans;
    @BindView(R.id.stat_kirim)
    TextView statKirim;
    @BindView(R.id.box1)
    ImageView box1;
    @BindView(R.id.box2)
    ImageView box2;
    @BindView(R.id.box3)
    ImageView box3;
    @BindView(R.id.box4)
    ImageView box4;
    @BindView(R.id.stat_kirim2)
    LinearLayout statKirim2;
    @BindView(R.id.status_pengiriman)
    LinearLayout statusPengiriman;
    @BindView(R.id.stat_selesai)
    TextView statSelesai;
    @BindView(R.id.selesai)
    LinearLayout selesai;
    @BindView(R.id.info_penukaran)
    RelativeLayout infoPenukaran;
    @BindView(R.id.spek_penukaran)
    TextView spekPenukaran;
    @BindView(R.id.title_barang)
    TextView titleBarang;
    @BindView(R.id.point)
    TextView point;
    @BindView(R.id.detail_produk)
    LinearLayout detailProduk;
    @BindView(R.id.barang_picture)
    ImageView barangPicture;
    @BindView(R.id.spek_barang_detail)
    RelativeLayout spekBarangDetail;
    @BindView(R.id.title_ulasan)
    TextView titleUlasan;
    @BindView(R.id.ulasan_produk)
    RelativeLayout ulasanProduk;

    String id, judulGambar, pointB, gambar;
    @BindView(R.id.title_penerima)
    TextView titlePenerima;
    @BindView(R.id.tv_penerima)
    TextView tvPenerima;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_confirmation2);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getBaseContext());

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
//        Log.d("IDNYA", id);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<DetailClaimReward> call = apiService.getDetailClaimReward(Integer.parseInt(id));
        call.enqueue(new Callback<DetailClaimReward>() {
            @Override
            public void onResponse(Call<DetailClaimReward> call, Response<DetailClaimReward> response) {
                try {
                    Log.d("Responnya", String.valueOf(response.code()));

                    String curString = response.body().getData().getAttributes().getCreatedAt();
                    String[] separated = curString.split("T");
                    String tgl = separated[0];

                    String[] separated2 = tgl.split("-");
                    String bulan = separated2[1];
                    String tanggal = separated2[2];

                    String finalBulan = null;

                    if (bulan.equals("01")) {
                        finalBulan = "Jan";
                    } else if (bulan.equals("02")) {
                        finalBulan = "Feb";
                    } else if (bulan.equals("03")) {
                        finalBulan = "Mar";
                    } else if (bulan.equals("04")) {
                        finalBulan = "Apr";
                    } else if (bulan.equals("05")) {
                        finalBulan = "Mei";
                    } else if (bulan.equals("06")) {
                        finalBulan = "Jun";
                    } else if (bulan.equals("07")) {
                        finalBulan = "Juli";
                    } else if (bulan.equals("08")) {
                        finalBulan = "Agus";
                    } else if (bulan.equals("09")) {
                        finalBulan = "Sep";
                    } else if (bulan.equals("10")) {
                        finalBulan = "Okt";
                    } else if (bulan.equals("11")) {
                        finalBulan = "Nov";
                    } else if (bulan.equals("12")) {
                        finalBulan = "Des";
                    }

                    String alamatnya;
                    if (response.body().getData().getAttributes().getAlamat() == null) {
                        alamatnya = "-";
                    } else {
                        alamatnya = String.valueOf(response.body().getData().getAttributes().getAlamat());
                    }

                    tvPenerima.setText(response.body().getData().getAttributes().getNamaAxi());

                    tvAlamat.setText(alamatnya);
                    tvTglTrans.setText(String.valueOf(response.body().getData().getAttributes().getTransaksiId()));
                    tvTglPen2.setText(tanggal + " " + finalBulan);

                    Call<DetailProduk> call2 = apiService.getDetailProduk(Integer.valueOf(response.body().getData().getAttributes().getProductCatalogId()));
                    call2.enqueue(new Callback<DetailProduk>() {
                        @Override
                        public void onResponse(Call<DetailProduk> call, Response<DetailProduk> response) {
                            judulGambar = response.body().getData().getAttributes().getNama();
                            pointB = String.valueOf(response.body().getData().getAttributes().getPoint());
                            gambar = response.body().getData().getAttributes().getFoto();

                            titleBarang.setText(response.body().getData().getAttributes().getNama());
                            point.setText(response.body().getData().getAttributes().getPoint() + " Point");
                            Glide.with(getBaseContext()).load(response.body().getData().getAttributes().getFoto()).into(barangPicture);
                        }

                        @Override
                        public void onFailure(Call<DetailProduk> call, Throwable t) {

                        }
                    });

                    switch (response.body().getData().getAttributes().getStatusId()) {
                        case "5":
                            box1.setBackgroundResource(R.drawable.border_active);
                            statSelesai.setText("Sedang diproses");
                            break;
                        case "6":
                            box1.setBackgroundResource(R.drawable.border_active);
                            statSelesai.setText("Sedang diproses");
                            break;
                        case "7":
                            box2.setBackgroundResource(R.drawable.border_active);
                            statSelesai.setText("Packing");
                            break;
                        case "8":
                            box3.setBackgroundResource(R.drawable.border_active);
                            statSelesai.setText("Dikirim");
                            break;
                        case "9":
                            box4.setBackgroundResource(R.drawable.border_active);
                            statSelesai.setText("Sudah sampai di cabang");
                            ulasanProduk.setVisibility(View.VISIBLE);
                            break;
                        case "10":
                            box4.setBackgroundResource(R.drawable.border_active);
                            statSelesai.setText("Batal");
                            break;
                        default:
                            break;
                    }

                    if (response.body().getData().getAttributes().getStatusId().equals("9")) {
                        if (response.body().getData().getRelationships().getTestimonis().getData().size() != 0) {
                            ulasanProduk.setVisibility(View.GONE);
                        } else {
                            ulasanProduk.setVisibility(View.VISIBLE);
                            ulasanProduk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getBaseContext(), ReviewActivity.class);
                                    intent.putExtra("ID", id);
                                    intent.putExtra("USER_ID", session.getAxiId());
                                    intent.putExtra("JUDUL", judulGambar);
                                    intent.putExtra("POINT", pointB);
                                    intent.putExtra("GAMBAR", gambar);
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                } catch (Exception ex) {

                }
            }

            @Override
            public void onFailure(Call<DetailClaimReward> call, Throwable t) {

            }
        });
    }
}
