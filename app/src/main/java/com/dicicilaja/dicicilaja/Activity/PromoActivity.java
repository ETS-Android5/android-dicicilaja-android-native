package com.dicicilaja.dicicilaja.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceSliderDetail;
import com.dicicilaja.dicicilaja.API.Item.SliderDetail.SliderDetail;
import com.dicicilaja.dicicilaja.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromoActivity extends AppCompatActivity {

    ImageView image;
    TextView date, description, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        image = findViewById(R.id.image);
        date = findViewById(R.id.date);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);

        InterfaceSliderDetail apiService = NewRetrofitClient.getClient().create(InterfaceSliderDetail.class);

        Call<SliderDetail> call = apiService.getSliderDetail(Integer.parseInt(getIntent().getStringExtra("ID")));
        call.enqueue(new Callback<SliderDetail>() {
            @Override
            public void onResponse(Call<SliderDetail> call, Response<SliderDetail> response) {
                Picasso.with(getBaseContext()).load(response.body().getData().get(0).getImageUrl()).into(image);
                date.setText(response.body().getData().get(0).getExpiredIn());
                name.setText(response.body().getData().get(0).getName());
                description.setText(response.body().getData().get(0).getDescription());
            }

            @Override
            public void onFailure(Call<SliderDetail> call, Throwable t) {

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
}
