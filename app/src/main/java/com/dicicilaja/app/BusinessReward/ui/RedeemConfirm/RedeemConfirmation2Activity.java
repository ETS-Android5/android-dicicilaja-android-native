package com.dicicilaja.app.BusinessReward.ui.RedeemConfirm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.BusinessReward.ui.Transaction.activity.ReviewActivity;
import com.dicicilaja.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RedeemConfirmation2Activity extends AppCompatActivity {
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

    String id, userId;
    int poin, thumbnail;

    String tgl, name, produk_id, alamat, no_transaksi, no_transaksi2, tgl_penukaran, status_pengiriman;
    @BindView(R.id.box1)
    ImageView box1;
    @BindView(R.id.box2)
    ImageView box2;
    @BindView(R.id.box3)
    ImageView box3;
    @BindView(R.id.box4)
    ImageView box4;
    @BindView(R.id.tv_cabang)
    TextView tvCabang;
    @BindView(R.id.tv_no_resi)
    TextView tvNoResi;
    @BindView(R.id.tv_no_resi2)
    TextView tvNoResi2;
    @BindView(R.id.copy_link)
    ImageView copyLink;
    @BindView(R.id.no_resi)
    LinearLayout noResi;
    @BindView(R.id.tv_no_jasa)
    TextView tvNoJasa;
    @BindView(R.id.tv_no_jasa2)
    TextView tvNoJasa2;
    @BindView(R.id.jasa_ekspedisi)
    LinearLayout jasaEkspedisi;
    @BindView(R.id.tv_title_penerima)
    TextView tvTitlePenerima;
    @BindView(R.id.tv_penerima)
    TextView tvPenerima;
    @BindView(R.id.penerimacabang)
    LinearLayout penerimacabang;
    @BindView(R.id.tv_title_hp)
    TextView tvTitleHp;
    @BindView(R.id.tv_hp_penerima)
    TextView tvHpPenerima;
    @BindView(R.id.hp_penerima)
    LinearLayout hpPenerima;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_confirmation2);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        tgl = intent.getStringExtra("DATE");
        alamat = intent.getStringExtra("ALAMAT");
        no_transaksi = intent.getStringExtra("NO_TRANSAKSI");
        no_transaksi2 = intent.getStringExtra("NO_TRANSAKSI2");
        tgl_penukaran = intent.getStringExtra("TGL_PENUKARAN");
        status_pengiriman = intent.getStringExtra("STATUS_PENGIRIMAN");
        produk_id = intent.getStringExtra("PRODUK_ID");

//        final SessionManager session = new SessionManager(getBaseContext());
//        userId = session.getUserId();

        tvAlamat.setText(alamat);
        tvTglTrans.setText(no_transaksi);
        tvTglPen2.setText(tgl_penukaran);

        switch (status_pengiriman) {
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
                break;
            case "10":
                box4.setBackgroundResource(R.drawable.border_active);
                statSelesai.setText("Batal");
                break;
            default:
                break;
        }


        ApiService apiService = ApiClient.getClient().create(ApiService.class);

//        Call<ClaimRewards> call = apiService.getClaim(Integer.parseInt(userId));
//        call.enqueue(new Callback<ClaimRewards>() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onResponse(Call<ClaimRewards> call, Response<ClaimRewards> response) {
//                final List<Datum> dataItems = response.body().getData();
//                Log.d("Cek1", "" + response.body().getData());
//
////                recyclerTransaksi.setAdapter(new ListClaimRewardAdapter(dataItems, getBaseContext()));
//            }
//
//            @Override
//            public void onFailure(Call<ClaimRewards> call, Throwable t) {
//
//            }
//        });

    }

    @OnClick(R.id.ulasan_produk)
    public void onViewClicked() {
        Intent intent = new Intent(getBaseContext(), ReviewActivity.class);
        startActivity(intent);
    }
}
