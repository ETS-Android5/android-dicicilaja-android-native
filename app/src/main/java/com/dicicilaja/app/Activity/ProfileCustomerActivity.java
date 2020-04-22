package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dicicilaja.app.API.Client.ApiClient;
import com.dicicilaja.app.API.Interface.InterfaceLogout;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProfileCustomer;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileCustomer.Datum;
import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileCustomer.ProfileCustomer;
import com.dicicilaja.app.Model.Logout;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Remote.ApiUtils;
import com.dicicilaja.app.Session.SessionManager;
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
    ProgressDialog progress;
    Datum dataCustomer;
    Button btnLogout;
    TextView name_user, api_email, api_telephone, api_jk, api_bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_customer);

        session = new SessionManager(getBaseContext());
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
        btnLogout = findViewById(R.id.btnLogout);
        changePassword = findViewById(R.id.changePassword);

        name_user = findViewById(R.id.name_user);
        api_email = findViewById(R.id.api_email);
        api_telephone = findViewById(R.id.api_telephone);
//        api_tanggal_daftar  = findViewById(R.id.api_tanggal_daftar);
//        api_alamat = findViewById(R.id.api_alamat);
//        api_kelurahan = findViewById(R.id.api_kelurahan);
//        api_kecamatan = findViewById(R.id.api_kecamatan);
//        api_kota = findViewById(R.id.api_kota);
//        api_provinsi = findViewById(R.id.api_provinsi);
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
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(profilePictures);

        progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        InterfaceProfileCustomer apiService =
                ApiClient.getClient().create(InterfaceProfileCustomer.class);

        Log.d("APIKEY", "onCreate: " + apiKey);
        Call<ProfileCustomer> callProfile = apiService.getProfile(apiKey);
        callProfile.enqueue(new Callback<ProfileCustomer>() {
            @Override
            public void onResponse(Call<ProfileCustomer> call, Response<ProfileCustomer> response) {
                try {
                    Log.d("APIKEY", "onCreate: " + response);
                    dataCustomer = response.body().getData().get(0);

                    name_user.setText(dataCustomer.getName());
                    api_email.setText(dataCustomer.getEmail());
                    api_telephone.setText(dataCustomer.getPhone());
                    if (dataCustomer.getGender().equals("l")) {
                        api_jk.setText("Laki-laki");
                    } else if (dataCustomer.getGender().equals("p")) {
                        api_jk.setText("Perempuan");
                    }
                    api_bill.setText(dataCustomer.getBillNumber());

                    progress.dismiss();
                } catch (Exception ex) {
                    progress.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileCustomerActivity.this);
                    alertDialog.setMessage("Gagal memuat data, silahkan coba beberapa saat lagi");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }

            }

            @Override
            public void onFailure(Call<ProfileCustomer> call, Throwable t) {
                progress.dismiss();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileCustomerActivity.this);
                alertDialog.setMessage("Gagal memuat data, silahkan coba beberapa saat lagi");

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
                        progress.show();
                        InterfaceLogout apiService =
                                ApiClient.getClient().create(InterfaceLogout.class);

                        Call<Logout> call2 = apiService.logout(apiKey);
                        call2.enqueue(new Callback<Logout>() {
                            @Override
                            public void onResponse(Call<Logout> call, Response<Logout> response2) {
                                try {
                                    if (response2.isSuccessful()) {
                                        progress.hide();
                                        session.logoutUser();
                                    }
                                } catch (Exception ex) {
                                    progress.hide();
                                }
                            }

                            @Override
                            public void onFailure(Call<Logout> call, Throwable t) {
                                progress.hide();
                            }
                        });
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
            intent.putExtra("api_bill",api_bill.getText().toString());
            intent.putExtra("api_jk",dataCustomer.getGender());
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
