package com.dicicilaja.app.OrderIn.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dicicilaja.app.Activity.AxiDashboardActivity;
import com.dicicilaja.app.Activity.EmployeeDashboardActivity;
import com.dicicilaja.app.Activity.MarketplaceActivity;
import com.dicicilaja.app.Activity.MaxiDashboardActivity;
import com.dicicilaja.app.Activity.SPGDashboardActivity;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PengajuanSuksesActivity extends AppCompatActivity {

    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.klaim)
    Button klaim;

    SessionManager sessionUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_sukses);
        ButterKnife.bind(this);

        sessionUser = new SessionManager(PengajuanSuksesActivity.this);

        initToolbar();
    }

    private void initToolbar() {

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @OnClick(R.id.klaim)
    public void onViewClicked() {
        Intent intent;
        switch (sessionUser.getRole()) {
            case "axi":
                intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
                break;

            case "channel":
                intent = new Intent(getBaseContext(), MaxiDashboardActivity.class);
                break;

            case "crh":
                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                break;

            case "cro":
                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                break;

            case "tc":
                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                break;

            case "admin":
                intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
                break;

            case "spg":
                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                break;

            case "bm":
                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                break;

            case "mm":
                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                break;

            case "ho":
                intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
                break;

            case "basic":
                intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                break;

            default:
                intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                break;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
