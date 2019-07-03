package com.dicicilaja.app.BusinessReward.ui.RedeemConfirm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.Activity.NotificationActivity;
import com.dicicilaja.app.R;

public class RedeemConfirmationActivity extends AppCompatActivity {

    String tgl, name, produk_id, alamat, no_transaksi, no_transaksi2, tgl_penukaran, status_pengiriman;

    int point, thumbnail;
    @BindView(R.id.tgl)
    TextView tvTgl;
    @BindView(R.id.icon_sukses)
    ImageView iconSukses;
    @BindView(R.id.title_sukses)
    TextView titleSukses;
    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.detail2)
    LinearLayout detail2;
    @BindView(R.id.call_tasya)
    FrameLayout callTasya;
    @BindView(R.id.klaim)
    Button klaim;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_confirmation);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        tgl = intent.getStringExtra("DATE");
        alamat = intent.getStringExtra("ALAMAT");
        no_transaksi = intent.getStringExtra("NO_TRANSAKSI");
        no_transaksi2 = intent.getStringExtra("NO_TRANSAKSI2");
        tgl_penukaran = intent.getStringExtra("TGL_PENUKARAN");
        status_pengiriman = intent.getStringExtra("STATUS_PENGIRIMAN");
        produk_id = intent.getStringExtra("PRODUK_ID");

        tvTgl.setText(tgl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_center, menu);
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

    @OnClick({R.id.call_tasya, R.id.klaim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call_tasya:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+628111465005&text=Halo%20*Tasya*%20%f0%9f%98%8a%2c%0a%0aMau%20tanya%20tentang%20produk%20yang%20saya%20telah%20tukar%20"));
                startActivity(browserIntent);
                break;
            case R.id.klaim:
                Intent intent = new Intent(getBaseContext(), RedeemConfirmation2Activity.class);
                intent.putExtra("ALAMAT", alamat);
                intent.putExtra("NO_TRANSAKSI", no_transaksi);
                intent.putExtra("NO_TRANSAKSI2", no_transaksi2);
                intent.putExtra("TGL_PENUKARAN", tgl_penukaran);
                intent.putExtra("STATUS_PENGIRIMAN", status_pengiriman);
                intent.putExtra("PRODUK_ID", produk_id);
//        intent.putExtra("Name", name);
//        intent.putExtra("Point", point);
//        intent.putExtra("Thumbnail", thumbnail);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
    }
}
