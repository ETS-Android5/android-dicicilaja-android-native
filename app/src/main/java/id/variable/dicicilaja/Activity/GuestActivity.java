package id.variable.dicicilaja.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.variable.dicicilaja.R;

public class GuestActivity extends AppCompatActivity {

    ImageView lewati;
    Button btn_register;
    TextView lewati_text, title_regis, title_regis_account, regis_account,title_login, desc_login, login_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);


        lewati = findViewById(R.id.lewati);
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

        title_regis.setTypeface(opensans_bold);
        title_regis_account.setTypeface(opensans_reguler);
        login_account.setTypeface(opensans_reguler);
        title_login.setTypeface(opensans_bold);
        desc_login.setTypeface(opensans_bold);
        lewati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
