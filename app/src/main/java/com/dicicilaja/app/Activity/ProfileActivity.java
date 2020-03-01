package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.text.format.DateFormat;
import android.util.Log;
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

import com.dicicilaja.app.API.Client.ApiClient;
import com.dicicilaja.app.API.Client.ApiClient2;
import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProfile;
import com.dicicilaja.app.InformAXI.model.ShProfile;
import com.dicicilaja.app.InformAXI.ui.UbahShActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import com.dicicilaja.app.API.Interface.InterfaceLogout;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceProfileAxi;
import com.dicicilaja.app.Activity.RemoteMarketplace.ItemBFF.ProfileAxi.Data;
import com.dicicilaja.app.Activity.RemoteMarketplace.ItemBFF.ProfileAxi.ProfileAxi;
import com.dicicilaja.app.Model.Logout;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Remote.ApiUtils;
import com.dicicilaja.app.Session.SessionManager;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    SessionManager session;
    InterfaceLogout interfaceLogout;
    String apiKey;
    RelativeLayout changePassword;
    Data dataAxi;

    TextView name_user, api_axi_id, api_cabang, api_tanggal_daftar, api_no_ktp, api_mentor, api_no_hp, api_email, api_alamat, api_rt_rw, api_kelurahan, api_kecamatan, api_provinsi, api_kodepos, api_jk, api_no_npwp;
    TextView api_tempat_lahir, api_tanggal_lahir, api_nama_bank, api_no_rekening, api_an_rekening, api_cabang_bank, api_kota_bank;

    LinearLayout cvDetailRekening;
    LinearLayout titleDetailRekening, topCard2, shProfile;

    private String role = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        TextView title_info = findViewById(R.id.title_info);
        TextView title_profile = findViewById(R.id.title_profile);
        Button btnLogout = findViewById(R.id.btnLogout);
        changePassword = findViewById(R.id.changePassword);

        name_user = findViewById(R.id.name_user);
        api_axi_id = findViewById(R.id.api_axi_id);
        api_cabang = findViewById(R.id.api_cabang);
        api_tanggal_daftar = findViewById(R.id.api_tanggal_daftar);
        api_no_ktp = findViewById(R.id.api_no_ktp);
        api_mentor = findViewById(R.id.api_mentor);
        api_no_hp = findViewById(R.id.api_no_hp);
        api_email = findViewById(R.id.api_email);
        api_alamat = findViewById(R.id.api_alamat);
        api_no_npwp = findViewById(R.id.api_no_npwp);
        api_nama_bank = findViewById(R.id.api_nama_bank);
        api_no_rekening = findViewById(R.id.api_no_rekening);
        api_an_rekening = findViewById(R.id.api_an_rekening);
        api_cabang_bank = findViewById(R.id.api_cabang_bank);
        api_kota_bank = findViewById(R.id.api_kota_bank);

        topCard2 = findViewById(R.id.top_card_2);
        cvDetailRekening = findViewById(R.id.card_detail_rekening);
        titleDetailRekening = findViewById(R.id.title_info_rekening);
        shProfile = findViewById(R.id.sh_profile);

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
        title_info.setTypeface(opensans_bold);

        CircleImageView profilePictures = findViewById(R.id.profile_picture_page);
        String imageUrl = session.getPhoto();
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(profilePictures);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        role = session.getRole();

        // TODO: Change after role is fixed
        if (role.equalsIgnoreCase("sh")) {
            InterfaceProfile apiService = ApiClient.getClient().create(InterfaceProfile.class);
            Call<ShProfile> call = apiService.getShProfile(apiKey);
            call.enqueue(new Callback<ShProfile>() {
                @Override
                public void onResponse(Call<ShProfile> call, Response<ShProfile> response) {
                    if (response.isSuccessful()) {
                        shProfile.setVisibility(View.VISIBLE);
                        ShProfile.Data data = response.body().getData().get(0);

                        name_user.setText(data.getNama());
                        ((TextView) findViewById(R.id.api_email_sh)).setText(data.getEmail());
                        ((TextView) findViewById(R.id.api_phone_sh)).setText(data.getPhone());
                        ((TextView) findViewById(R.id.api_ktp_sh)).setText(data.getNik());

                        if (data.getPhoto() != null && !data.getPhoto().isEmpty())
                            Picasso.get()
                                    .load(data.getPhoto())
                                    .placeholder(R.drawable.avatar)
                                    .error(R.drawable.avatar)
                                    .fit()
                                    .centerCrop()
                                    .into(profilePictures);
                        else
                            Picasso.get()
                                    .load(R.drawable.avatar)
                                    .placeholder(R.drawable.avatar)
                                    .fit()
                                    .centerCrop()
                                    .into(profilePictures);

                        cvDetailRekening.setVisibility(View.GONE);
                        topCard2.setVisibility(View.GONE);
                        titleDetailRekening.setVisibility(View.GONE);

                        progress.dismiss();
                    } else {
                        progress.dismiss();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Data gagal dipanggil, silahkan coba beberapa saat lagi.");
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        alertDialog.show();
                    }
                }

                @Override
                public void onFailure(Call<ShProfile> call, Throwable t) {
                    progress.dismiss();
                    Log.d("asd", "onFailure: " + t.getMessage());
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data gagal dipanggil, silahkan coba beberapa saat lagi.");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alertDialog.show();
                }
            });
        } else {
            Call<ProfileAxi> callProfile;

            InterfaceProfile apiService = ApiClient.getClient().create(InterfaceProfile.class);
            callProfile = apiService.getProfile(apiKey);

            callProfile.enqueue(new Callback<ProfileAxi>() {
                @Override
                public void onResponse(Call<ProfileAxi> call, Response<ProfileAxi> response) {
//                Log.d("PROFILE::::", response.body().getData().toString());
//                Log.d("PROFILE::::", String.valueOf(response.body().equals(null)));
                    if (response.body() == null) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
                        alertDialog.setTitle("Perhatian");
                        alertDialog.setMessage("Data gagal dipanggil, silahkan coba beberapa saat lagi.");
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        alertDialog.show();
                    } else {

                        api_tanggal_daftar.setText(dataAxi.getTanggalDaftar());
                        api_no_ktp.setText(dataAxi.getNoKtp());
                        api_mentor.setText(dataAxi.getAxiIdReff());
                        api_no_hp.setText(dataAxi.getNoHp());
                        api_email.setText(dataAxi.getEmail());
                        api_alamat.setText(dataAxi.getAlamatKtp());
                        api_no_npwp.setText(dataAxi.getNpwpNo());

                        api_nama_bank.setText(dataAxi.getNamaBank());
                        api_no_rekening.setText(dataAxi.getNoRekening());
                        api_an_rekening.setText(dataAxi.getAnRekening());
                        api_cabang_bank.setText(dataAxi.getCabangBank());
                        api_kota_bank.setText(dataAxi.getKotaBank());
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<ProfileAxi> call, Throwable t) {
                    Log.e("PROFILE::::", t.toString());
                    progress.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
                    alertDialog.setTitle("Perhatian");
                    alertDialog.setMessage("Data gagal dipanggil, silahkan coba beberapa saat lagi.");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alertDialog.show();
                }
            });
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);

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
                                        progress.dismiss();
                                        session.logoutUser();
                                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } catch (Exception ex) {
                                    progress.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<Logout> call, Throwable t) {
                                progress.dismiss();
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


            if (role != null && role.equalsIgnoreCase("sh")) {
                Intent intent = new Intent(getBaseContext(), UbahShActivity.class);

                intent.putExtra("name_user", name_user.getText().toString());
                intent.putExtra("api_no_hp", ((TextView) findViewById(R.id.api_phone_sh)).getText().toString());
                intent.putExtra("api_email", ((TextView) findViewById(R.id.api_email_sh)).getText().toString());
                startActivity(intent);
            } else {
                Intent intent = new Intent(getBaseContext(), UbahAxiActivity.class);

                intent.putExtra("name_user", name_user.getText().toString());
                intent.putExtra("api_axi_id", api_axi_id.getText().toString());
                intent.putExtra("api_cabang", api_cabang.getText().toString());
                intent.putExtra("api_tanggal_daftar", api_tanggal_daftar.getText().toString());
                intent.putExtra("api_no_ktp", api_no_ktp.getText().toString());
                intent.putExtra("api_mentor", api_mentor.getText().toString());
                intent.putExtra("api_tanggal_lahir", api_tanggal_lahir.getText().toString());
                intent.putExtra("api_tempat_lahir", api_tempat_lahir.getText().toString());
                intent.putExtra("api_no_hp", api_no_hp.getText().toString());
                intent.putExtra("api_email", api_email.getText().toString());
                intent.putExtra("api_alamat", api_alamat.getText().toString());
                intent.putExtra("api_rt_rw", api_rt_rw.getText().toString());
                intent.putExtra("api_kelurahan", api_kelurahan.getText().toString());
                intent.putExtra("api_kecamatan", api_kecamatan.getText().toString());
                intent.putExtra("api_provinsi", api_provinsi.getText().toString());
                intent.putExtra("api_kodepos", api_kodepos.getText().toString());

                intent.putExtra("api_no_npwp", api_no_npwp.getText().toString());
                intent.putExtra("api_nama_bank", api_nama_bank.getText().toString());
                intent.putExtra("api_no_rekening", api_no_rekening.getText().toString());
                intent.putExtra("api_an_rekening", api_an_rekening.getText().toString());
                intent.putExtra("api_cabang_bank", api_cabang_bank.getText().toString());
                intent.putExtra("api_kota_bank", api_kota_bank.getText().toString());

                String jk = api_jk.getText().toString();
                if (jk.toLowerCase().equals("l") || jk.toLowerCase().equals("laki-laki") || jk.toLowerCase().equals("laki - laki")) {
                    intent.putExtra("api_jk", "1");
                } else if (jk.toLowerCase().equals("p") || jk.toLowerCase().equals("perempuan")) {
                    intent.putExtra("api_jk", "2");
                } else {
                    intent.putExtra("api_jk", "0");
                }
                startActivity(intent);
            }
            return true;
        } else if (id == R.id.home) {
            super.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}