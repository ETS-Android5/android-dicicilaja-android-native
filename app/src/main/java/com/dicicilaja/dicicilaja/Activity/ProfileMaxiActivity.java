package com.dicicilaja.dicicilaja.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.dicicilaja.dicicilaja.API.Client.NewRetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceLogout;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProfileMaxi;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileMaxi.Data;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileMaxi.ProfileMaxi;
import com.dicicilaja.dicicilaja.Model.Logout;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Remote.ApiUtils;
import com.dicicilaja.dicicilaja.Session.SessionManager;
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
    Data dataMaxi;
    TextView name_user, api_name, api_alamat, api_email, api_npwp, api_name_user, api_kelamin, api_email_user, api_telp, api_handphone, api_alamat_pemilik, api_kelurahan, api_kota, api_ktp, api_npwp_pemilik;
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

        Button btnLogout = findViewById(R.id.btnLogout);
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

        name_user = findViewById(R.id.name_user);
        api_npwp = findViewById(R.id.api_npwp);
        api_name = findViewById(R.id.api_name);
        api_alamat = findViewById(R.id.api_alamat);
        api_name_user = findViewById(R.id.api_name_user);
        api_kelamin = findViewById(R.id.api_kelamin);
        api_email_user = findViewById(R.id.api_email_user);
        api_telp = findViewById(R.id.api_telp);
        api_handphone = findViewById(R.id.api_handphone);
        api_alamat_pemilik = findViewById(R.id.api_alamat_pemilik);
        api_kelurahan = findViewById(R.id.api_kelurahan);
        api_kota = findViewById(R.id.api_kota);
        api_ktp = findViewById(R.id.api_ktp);
        api_npwp_pemilik = findViewById(R.id.api_npwp_pemilik);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        title_profile.setTypeface(opensans_bold);
        title_status.setTypeface(opensans_bold);
        title_pemilik.setTypeface(opensans_bold);
        ganti.setTypeface(opensans_semibold);

        String imageUrl = session.getPhoto().toString();

        CircleImageView profilePictures =  findViewById(R.id.profile_picture_page);
        try {
            Picasso.with(getApplicationContext()).load(imageUrl).into(profilePictures);
        } catch (Exception ex) {

        }

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

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        InterfaceProfileMaxi apiService =
                NewRetrofitClient.getClient().create(InterfaceProfileMaxi.class);

        Call<ProfileMaxi> callProfile = apiService.getProfile(apiKey);
        callProfile.enqueue(new Callback<ProfileMaxi>() {
            @Override
            public void onResponse(Call<ProfileMaxi> call, Response<ProfileMaxi> response) {
                dataMaxi = response.body().getData();

                name_user.setText(dataMaxi.getNamaPerusahaan());
                api_npwp.setText(dataMaxi.getNpwpPerusahaan());
                api_name.setText(dataMaxi.getNamaPerusahaan());
                api_alamat.setText(dataMaxi.getAlamatPerusahaan());
                api_name_user.setText(dataMaxi.getNamaPemilik());
                api_kelamin.setText(dataMaxi.getJenisKelamin());
                api_email_user.setText(dataMaxi.getEmail());
                api_telp.setText(dataMaxi.getTelp());
                api_handphone.setText(dataMaxi.getHandphone());
                api_alamat_pemilik.setText(dataMaxi.getAlamatPemilik());
                api_kelurahan.setText(dataMaxi.getKelurahan());
                api_kota.setText(dataMaxi.getKota());
                api_ktp.setText(dataMaxi.getKtp());
                api_npwp_pemilik.setText(dataMaxi.getNpwpPemilik());
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ProfileMaxi> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileMaxiActivity.this);

                // Setting Dialog Title
                alertDialog.setTitle("Konfirmasi");

                // Setting Dialog Message
                alertDialog.setMessage("Apakah Anda yakin ingin keluar?");

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        doLogout(apiKey);
                        session.logoutUser();
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();

            }
        });
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

            name_user.setText(dataMaxi.getNamaPerusahaan());
            api_npwp.setText(dataMaxi.getNpwpPerusahaan());
            api_name.setText(dataMaxi.getNamaPerusahaan());
            api_alamat.setText(dataMaxi.getAlamatPerusahaan());
            api_name_user.setText(dataMaxi.getNamaPemilik());
            api_kelamin.setText(dataMaxi.getJenisKelamin());
            api_email_user.setText(dataMaxi.getEmail());
            api_telp.setText(dataMaxi.getTelp());
            api_handphone.setText(dataMaxi.getHandphone());
            api_alamat_pemilik.setText(dataMaxi.getAlamatPemilik());
            api_kelurahan.setText(dataMaxi.getKelurahan());
            api_kota.setText(dataMaxi.getKota());
            api_ktp.setText(dataMaxi.getKtp());
            api_npwp_pemilik.setText(dataMaxi.getNpwpPemilik());

            intent.putExtra("api_npwp",api_npwp.getText().toString());
            intent.putExtra("api_name",api_name.getText().toString());
            intent.putExtra("api_alamat",api_alamat.getText().toString());
            intent.putExtra("api_name_user",api_name_user.getText().toString());
            intent.putExtra("api_email_user",api_email_user.getText().toString());
            intent.putExtra("api_telp",api_telp.getText().toString());
            intent.putExtra("api_handphone",api_handphone.getText().toString());
            intent.putExtra("api_alamat_pemilik",api_alamat_pemilik.getText().toString());
            intent.putExtra("api_kelurahan",api_kelurahan.getText().toString());
            intent.putExtra("api_kota",api_kota.getText().toString());
            intent.putExtra("api_ktp",api_ktp.getText().toString());
            intent.putExtra("api_npwp_pemilik",api_npwp_pemilik.getText().toString());

            String jk = api_kelamin.getText().toString();
            if(jk.toLowerCase().equals("l") || jk.toLowerCase().equals("laki-laki") || jk.toLowerCase().equals("laki - laki")) {
                intent.putExtra("api_jk","1");
            }else if(jk.toLowerCase().equals("p") || jk.toLowerCase().equals("perempuan")){
                intent.putExtra("api_jk","2");
            }else{
                intent.putExtra("api_jk","0");
            }
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
