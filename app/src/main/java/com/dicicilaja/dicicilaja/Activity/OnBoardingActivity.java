package com.dicicilaja.dicicilaja.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;

import static java.lang.Boolean.TRUE;

public class OnBoardingActivity extends AppCompatActivity {

    RelativeLayout lewati;
    Button btn_register;
    TextView lewati_text, title_regis, title_regis_account, regis_account,title_login, desc_login, login_account;

    String apiKey;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        session = new SessionManager(OnBoardingActivity.this);



        if (session.isLoggedIn() == TRUE && session.getRole().equals("axi")) {
            Intent intent = new Intent(getBaseContext(), AxiDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("channel")) {
            Intent intent = new Intent(getBaseContext(), MaxiDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("crh")) {
            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("cro")) {
            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("tc")) {
            Intent intent = new Intent(getBaseContext(), EmployeeDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("spg")) {
            Intent intent = new Intent(getBaseContext(), SPGDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if (session.isLoggedIn() == TRUE && session.getRole().equals("basic")) {
            Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
            startActivity(intent);
            finish();
        } else {
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.colorWhite));
            }

            lewati = findViewById(R.id.lewati);
            lewati_text = findViewById(R.id.lewati_text);
            title_regis = findViewById(R.id.title_regis);
            title_regis_account = findViewById(R.id.title_regis_account);
            title_login = findViewById(R.id.title_login);
            desc_login = findViewById(R.id.desc_login);
            btn_register = findViewById(R.id.btn_regis);
            login_account = findViewById(R.id.login_account);

            Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
            Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
            Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
            Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

            lewati_text.setTypeface(opensans_bold);
            title_regis.setTypeface(opensans_bold);
            title_regis_account.setTypeface(opensans_bold);
            login_account.setTypeface(opensans_bold);
            title_login.setTypeface(opensans_bold);
            desc_login.setTypeface(opensans_bold);
            lewati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), MarketplaceActivity.class);
                    startActivity(intent);
                }
            });
            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            });
            login_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
