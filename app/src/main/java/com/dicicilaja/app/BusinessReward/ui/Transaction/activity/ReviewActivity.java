package com.dicicilaja.app.BusinessReward.ui.Transaction.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.testimoni.Testimoni;
import com.dicicilaja.app.BusinessReward.network.ApiClient;
import com.dicicilaja.app.BusinessReward.network.ApiService;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    String apiKey;
    SessionManager session;
    String id, user_id, isi_ulasan_text;
    float ratingValue;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.title_rating)
    TextView titleRating;
    @BindView(R.id.rt_bar)
    AppCompatRatingBar rtBar;
    @BindView(R.id.rating)
    RelativeLayout rating;
    @BindView(R.id.title_ulasan)
    TextView titleUlasan;
    @BindView(R.id.isi_ulasan_text)
    EditText isiUlasanText;
    @BindView(R.id.ulasan)
    RelativeLayout ulasan;
    @BindView(R.id.klaim)
    Button klaim;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        user_id = intent.getStringExtra("USER_ID");

        titleBarang.setText(intent.getStringExtra("JUDUL"));
        point.setText(intent.getStringExtra("POINT"));
        Glide.with(getBaseContext()).load(intent.getStringExtra("GAMBAR")).into(barangPicture);

        rtBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                Toast.makeText(ReviewActivity.this, " Rating " + rating, Toast.LENGTH_SHORT).show();
                ratingValue = rating;
            }
        });
    }

    @OnClick(R.id.klaim)
    public void onViewClicked() {
        isi_ulasan_text = String.valueOf(isiUlasanText.getText());

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReviewActivity.this);
        alertDialog.setTitle("Rating");
        alertDialog.setMessage("Apakah anda yakin ingin memberikan rating ?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ApiService apiService = ApiClient.getClient().create(ApiService.class);
//                TestimoniTemp testimoniTemp = new TestimoniTemp(Integer.valueOf(user_id), Integer.valueOf(id), 3, isi_ulasan, ratingValue);
//                Call<Testimoni> call = apiService.postTestimoni(testimoniTemp);
                Log.d("Testimo1", user_id);
                Log.d("Testimo2", id);
                Log.d("Testimo3", isi_ulasan_text);
                Log.d("Testimo10", String.valueOf(isiUlasanText.getText()));
                Log.d("Testimo4", String.valueOf(ratingValue));
                Call<Testimoni> call = apiService.postTestimoni(apiKey,user_id, id, "3", isi_ulasan_text, String.valueOf(ratingValue));
                call.enqueue(new Callback<Testimoni>() {
                    @Override
                    public void onResponse(Call<Testimoni> call, Response<Testimoni> response) {
                        try {
                            Log.d("Responnya2", String.valueOf(response.code()));
                            Log.d("Responnya2", String.valueOf(response.message()));
                            Intent intent = new Intent(getBaseContext(), TransactionActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } catch (Exception ex) {

                        }
                    }

                    @Override
                    public void onFailure(Call<Testimoni> call, Throwable t) {

                    }
                });
            }
        });
        alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startActivity(getIntent());
            }
        });
        alertDialog.show();
    }
}
