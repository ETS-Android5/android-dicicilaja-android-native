package com.dicicilaja.app.InformAXI.ui.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.dicicilaja.app.InformAXI.adapter.ProfileTabAdapter;
import com.dicicilaja.app.InformAXI.model.AxiProfile;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.InformAXI.utils.ViewPagerCustom;
import com.dicicilaja.app.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.dicicilaja.app.InformAXI.utils.Constants.BENEFIT;
import static com.dicicilaja.app.InformAXI.utils.Constants.OTHER;
import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE;
import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE_ID;
import static com.dicicilaja.app.InformAXI.utils.Constants.PROFILE_NAME;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabProfile;
    private ViewPagerCustom vpProfile;
    private TextView tvName;

    private ProfileTabAdapter adapter;

    public AxiProfile data = null;

    private String profileName = null;
    public int profileId = -1;

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informaxi_profile);

        initVariables();
        initToolbar();
        getProfileData();
    }

    private void initVariables() {
        toolbar = findViewById(R.id.toolbar);
        tabProfile = findViewById(R.id.tab_profile);
        vpProfile = findViewById(R.id.vp_profile);
        tvName = findViewById(R.id.tv_name);

        profileName = getIntent().getStringExtra(PROFILE_NAME);
        profileId = getIntent().getIntExtra(PROFILE_ID, -1);

        if (profileName != null)
            tvName.setText(profileName);
        else
            tvName.setText("Fajar Abdal");

        List<String> tabs = new ArrayList<>();
        tabs.add(PROFILE);
        tabs.add(BENEFIT);
        tabs.add(OTHER);

        adapter = new ProfileTabAdapter(getSupportFragmentManager(), tabs);

        tabProfile.setupWithViewPager(vpProfile);
        vpProfile.setAdapter(adapter);

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance(this);
        jsonApi = retrofit.create(NetworkInterface.class);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getProfileData() {
        mCompositeDisposable.add(
                jsonApi.getDetail(profileId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessGetProfile, this::onError)
        );
    }

    public void onSuccessGetProfile(AxiProfile data) {
        this.data = data;
        adapter.update(1);
    }

    public void onError(Throwable t) {
        Tools.showLongToast(this, t.getMessage());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
