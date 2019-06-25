package com.dicicilaja.app.BranchOffice.UI.DetailBranchOffice;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.R;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class DetailBranchOfficeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.branch_name)
    TextView branchName;
    @BindView(R.id.branch_address)
    TextView branchAddress;
    @BindView(R.id.branch_city)
    TextView branchCity;
    @BindView(R.id.branch_fax1)
    TextView branchFax1;
    @BindView(R.id.copy_fax1)
    ImageView copyFax1;
    @BindView(R.id.branch_fax2)
    TextView branchFax2;
    @BindView(R.id.copy_fax2)
    ImageView copyFax2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_branch_office);
        ButterKnife.bind(this);

        initToolbar();
        initAction();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("city"));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
    }

    private void initAction() {

    }

    @OnClick({R.id.copy_fax1, R.id.copy_fax2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.copy_fax1:
                break;
            case R.id.copy_fax2:
                break;
        }
    }
}
