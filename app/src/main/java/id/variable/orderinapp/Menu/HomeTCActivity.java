package id.variable.orderinapp.Menu;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import id.variable.orderinapp.Fragment.HomeFragment;
import id.variable.orderinapp.Fragment.ProfileFragment;
import id.variable.orderinapp.R;

public class HomeTCActivity extends AppCompatActivity {

    String token;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, homeFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    ProfileFragment historyFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, historyFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, homeFragment).addToBackStack(null).commit();

        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        // String s = getIntent().getStringExtra("token");
        String token = pref.getString("bearer", null);
        Log.d("lihat", "token = " + token);

        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        Typeface nunito_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Nunito-Bold.ttf");
        navigation.setTypeface(nunito_bold);

//        navigation.setIconsMarginTop(32);
//        navigation.setIconSize(26,26);
//        navigation.setItemHeight(144);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
