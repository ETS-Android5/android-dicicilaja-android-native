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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.dicicilaja.API.Client.RetrofitClient;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceLogout;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProfileCustomer;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileCustomer.Data;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileCustomer.ProfileCustomer;
import com.dicicilaja.dicicilaja.Model.Logout;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Remote.ApiUtils;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileCustomerActivity extends AppCompatActivity {

    SessionManager session;
    InterfaceLogout interfaceLogout;
    String apiKey;
    RelativeLayout changePassword;
    Data dataCustomer;
    TextView name_user, api_email, api_telephone, api_tanggal_daftar, api_alamat, api_kelurahan, api_kecamatan, api_kota, api_provinsi, api_jk, api_bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_customer);

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

        TextView title_status = findViewById(R.id.title_status);
        TextView title_profile = findViewById(R.id.title_profile);
        Button btnLogout = findViewById(R.id.btnLogout);
        changePassword = findViewById(R.id.changePassword);

        name_user = findViewById(R.id.name_user);
        api_email = findViewById(R.id.api_email);
        api_telephone = findViewById(R.id.api_telephone);
        api_tanggal_daftar  = findViewById(R.id.api_tanggal_daftar);
        api_alamat = findViewById(R.id.api_alamat);
        api_kelurahan = findViewById(R.id.api_kelurahan);
        api_kecamatan = findViewById(R.id.api_kecamatan);
        api_kota = findViewById(R.id.api_kota);
        api_provinsi = findViewById(R.id.api_provinsi);
        api_jk = findViewById(R.id.api_jk);
        api_bill = findViewById(R.id.api_bill);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), UbahPasswordAxiActivity.class);
                startActivity(intent);
            }
        });


        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_status.setTypeface(opensans_bold);
        title_profile.setTypeface(opensans_bold);

        CircleImageView profilePictures =  findViewById(R.id.profile_picture_page);
        String imageUrl = session.getPhoto();
        Picasso.with(getApplicationContext())
                .load(imageUrl)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(profilePictures);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        InterfaceProfileCustomer apiService =
                RetrofitClient.getClient().create(InterfaceProfileCustomer.class);

        Call<ProfileCustomer> callProfile = apiService.getProfile(apiKey);
        callProfile.enqueue(new Callback<ProfileCustomer>() {
            @Override
            public void onResponse(Call<ProfileCustomer> call, Response<ProfileCustomer> response) {
                dataCustomer = response.body().getData();

                name_user.setText(dataCustomer.getName());
                api_email.setText(dataCustomer.getEmail());
                api_telephone.setText(dataCustomer.getPhone());
                api_tanggal_daftar.setText(dataCustomer.getBirthDate());
                api_alamat.setText(dataCustomer.getAddress());
                api_kelurahan.setText(dataCustomer.getSubdistrict());
                api_kecamatan.setText(dataCustomer.getDistrict());
                api_kota.setText(dataCustomer.getCity());
                api_provinsi.setText(dataCustomer.getProvince());
                api_jk.setText(dataCustomer.getGender());
                api_bill.setText(dataCustomer.getBillNumber());

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ProfileCustomer> call, Throwable t) {
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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileCustomerActivity.this);

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
//            Toast.makeText(getBaseContext(),api_cabang.getText()+" " +api_kodepos.getText(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), UbahCustomerActivity.class);

            intent.putExtra("name_user",name_user.getText().toString());
            intent.putExtra("api_handphone",api_telephone.getText().toString());
            intent.putExtra("api_tanggal_daftar",api_tanggal_daftar.getText().toString());
            intent.putExtra("api_alamat",api_alamat.getText().toString());
            intent.putExtra("api_kelurahan",api_kelurahan.getText().toString());
            intent.putExtra("api_kecamatan",api_kecamatan.getText().toString());
            intent.putExtra("api_kota",api_kota.getText().toString());
            intent.putExtra("api_provinsi",api_provinsi.getText().toString());
            intent.putExtra("api_bill",api_bill.getText().toString());

            String jk = api_jk.getText().toString();
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
