package com.dicicilaja.app.BranchOffice.UI.DetailBranchOffice;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dicicilaja.app.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

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
    @BindView(R.id.guide)
    FrameLayout guide;
    @BindView(R.id.branch_fax3)
    TextView branchFax3;
    @BindView(R.id.copy_fax3)
    ImageView copyFax3;
    @BindView(R.id.fax1)
    RelativeLayout textFax1;
    @BindView(R.id.line_fax1)
    ImageView lineFax1;
    @BindView(R.id.fax2)
    RelativeLayout textFax2;
    @BindView(R.id.line_fax2)
    ImageView lineFax2;
    @BindView(R.id.fax3)
    RelativeLayout textFax3;
    @BindView(R.id.line_fax3)
    ImageView lineFax3;

    String fax1, fax2, fax3, nama, alamat, kota;
    @BindView(R.id.phoneNumber)
    FrameLayout phoneNumber;

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
        getSupportActionBar().setTitle(getIntent().getStringExtra("nama"));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }
    }

    private void initAction() {

        textFax1.setVisibility(View.GONE);
        lineFax1.setVisibility(View.GONE);
        textFax2.setVisibility(View.GONE);
        lineFax2.setVisibility(View.GONE);
        textFax3.setVisibility(View.GONE);
        lineFax3.setVisibility(View.GONE);

        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        kota = getIntent().getStringExtra("kota");
        fax1 = getIntent().getStringExtra("fax1");
        fax2 = getIntent().getStringExtra("fax2");
        fax3 = getIntent().getStringExtra("fax3");

        if (fax1 == null || fax1.equals("null") || fax1.equals("-") || fax1.equals("0")) {

        } else {
            textFax1.setVisibility(View.VISIBLE);
            lineFax1.setVisibility(View.VISIBLE);
            branchFax1.setText(fax1);
        }

        if (fax2 == null || fax2.equals("null") || fax2.equals("-") || fax2.equals("0")) {

        } else {
            textFax2.setVisibility(View.VISIBLE);
            lineFax2.setVisibility(View.VISIBLE);
            branchFax2.setText(fax2);
        }

        if (fax3 == null || fax3.equals("null") || fax3.equals("-") || fax3.equals("0")) {

        } else {
            textFax3.setVisibility(View.VISIBLE);
            lineFax3.setVisibility(View.VISIBLE);
            branchFax3.setText(fax3);
        }

        branchName.setText(nama);
        branchAddress.setText(alamat);
        branchCity.setText(kota);

    }

    @OnClick({R.id.copy_fax1, R.id.copy_fax2, R.id.copy_fax3, R.id.guide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.copy_fax1:
                Object clipboardService = getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager = (ClipboardManager) clipboardService;
                String srcText = branchFax1.getText().toString();
                ClipData clipData = ClipData.newPlainText("Fax", srcText);
                clipboardManager.setPrimaryClip(clipData);
                Snackbar snackbar = Snackbar.make(view, "Nomor Fax berhasil disalin", Snackbar.LENGTH_SHORT);
                snackbar.show();
                break;
            case R.id.copy_fax2:
                Object clipboardService2 = getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager2 = (ClipboardManager) clipboardService2;
                String srcText2 = branchFax2.getText().toString();
                ClipData clipData2 = ClipData.newPlainText("Fax", srcText2);
                clipboardManager2.setPrimaryClip(clipData2);
                Snackbar snackbar2 = Snackbar.make(view, "Nomor Fax berhasil disalin", Snackbar.LENGTH_SHORT);
                snackbar2.show();
                break;
            case R.id.copy_fax3:
                Object clipboardService3 = getSystemService(CLIPBOARD_SERVICE);
                final ClipboardManager clipboardManager3 = (ClipboardManager) clipboardService3;
                String srcText3 = branchFax3.getText().toString();
                ClipData clipData3 = ClipData.newPlainText("Fax", srcText3);
                clipboardManager3.setPrimaryClip(clipData3);
                Snackbar snackbar3 = Snackbar.make(view, "Nomor Fax berhasil disalin", Snackbar.LENGTH_SHORT);
                snackbar3.show();
                break;
            case R.id.phoneNumber:
                break;
            case R.id.guide:
                break;
        }
    }

    @OnClick(R.id.phoneNumber)
    public void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.fragment_phone_detail, null);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
    }
}
