package id.variable.dicicilaja.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import id.variable.dicicilaja.API.Interface.InterfaceLogout;
import id.variable.dicicilaja.Model.Logout;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileMaxiActivity extends AppCompatActivity {

    SessionManager session;
    InterfaceLogout interfaceLogout;
    String apiKey;
    TextView title_profile, title_status, title_pemilik, ubah, ganti;
    RelativeLayout changePassword;
    LinearLayout ubah_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_maxi);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        interfaceLogout = ApiUtils.getLogout();

        title_profile = findViewById(R.id.title_profile);
        title_status = findViewById(R.id.title_status);
        title_pemilik = findViewById(R.id.title_pemilik);
        changePassword = findViewById(R.id.changePassword);
        ganti = findViewById(R.id.ganti);

//        TextView title_status = findViewById(R.id.title_status);
//        TextView title_profile = findViewById(R.id.title_profile);
//        Button btnLogout = findViewById(R.id.btnLogout);
//        TextView name_user = findViewById(R.id.name_user);
//        TextView branch = findViewById(R.id.branch);
//        TextView area = findViewById(R.id.area);
//
//        TextView api_nik = findViewById(R.id.api_nik);
//        TextView api_jabatan = findViewById(R.id.api_jabatan);
//        TextView api_kode_cabang = findViewById(R.id.api_kode_cabang);
//        TextView api_laporan_area = findViewById(R.id.api_laporan_area);
//        TextView api_area = findViewById(R.id.api_area);
//        TextView api_cabang = findViewById(R.id.api_cabang);
//        TextView api_hp1 = findViewById(R.id.api_hp1);
//        TextView api_hp2 = findViewById(R.id.api_hp2);
//
//        api_nik.setText(session.getUserId().toString());
//        api_jabatan.setText(session.getRole().toString());
//        api_cabang.setText(session.getBranch().toString());
//        api_area.setText(session.getArea().toString());

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        title_profile.setTypeface(opensans_bold);
        title_status.setTypeface(opensans_bold);
        title_pemilik.setTypeface(opensans_bold);
        ganti.setTypeface(opensans_semibold);
//        title_status.setTypeface(opensans_bold);
//        title_profile.setTypeface(opensans_bold);
//        name_user.setTypeface(opensans_bold);
//        branch.setText(session.getBranch());
//        area.setText(session.getArea());
//        name_user.setText(session.getName());
//        String imageUrl = session.getPhoto().toString();
//
//        CircleImageView profilePictures =  findViewById(R.id.profile_picture_page);
//        Picasso.with(getApplicationContext()).load(imageUrl).into(profilePictures);

//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileMaxiActivity.this);
//
//                // Setting Dialog Title
//                alertDialog.setTitle("Konfirmasi");
//
//                // Setting Dialog Message
//                alertDialog.setMessage("Apakah Anda yakin ingin keluar?");
//
//                // Setting Positive "Yes" Button
//                alertDialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
////                        doLogout(apiKey);
//                        session.logoutUser();
//                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//
//                // Setting Negative "NO" Button
//                alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                // Showing Alert Message
//                alertDialog.show();
//
//            }
//        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), UbahPasswordMaxiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maxi_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ubah) {
            Intent intent = new Intent(getBaseContext(), UbahMaxiActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.home) {
            super.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doLogout(final String apiKey) {
        Call<Logout> call = interfaceLogout.logout(apiKey);
        call.enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(Call<Logout> call, Response<Logout> response) {
                Toast.makeText(getBaseContext(),"code :" + response.code(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Logout> call, Throwable t) {

            }
        });
    }
}
