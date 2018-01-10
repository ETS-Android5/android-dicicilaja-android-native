package id.variable.dicicilaja.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.variable.dicicilaja.API.Interface.ApiDatabaseEmployee;
import id.variable.dicicilaja.API.Item.DatabaseEmployee;
import id.variable.dicicilaja.API.Item.DatabaseEmployeeResponse;
import id.variable.dicicilaja.Adapter.DatabaseEmployeeAdapter;
import id.variable.dicicilaja.Adapter.PengajuanAdapter;
import id.variable.dicicilaja.Fragment.InprogressFragment;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LihatDatabaseCRHActivity extends AppCompatActivity {

    private static final String TAG = LihatDatabaseCRHActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_database_crh);

        final SessionManager session = new SessionManager(getBaseContext());
        String apiKey = "Bearer " + session.getToken();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title_hasil_pencarian = findViewById(R.id.title_hasil_pencarian);

        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_hasil_pencarian.setTypeface(opensans_bold);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorAccentDark));
        }

        final RecyclerView recyclerView =  findViewById(R.id.recycler_database_crh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getBaseContext(), "Urutan database crh : "+position, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LihatDatabaseCRHActivity.this);
                // Setting Dialog Title
                alertDialog.setTitle("INFORMASI DETAIL");

                // Setting Dialog Message
                alertDialog.setMessage("Apakah Anda sudah menghubungi pemohon dan mengkonfirmasi bahwa benar adanya pengajuan tersebut.");
                alertDialog.setMessage("Apakah Anda sudah menghubungi pemohon dan mengkonfirmasi bahwa benar adanya pengajuan tersebut.");

                // Setting Icon to Dialog
//                alertDialog.setIcon(R.drawable.ic_circle);

                // Setting OK Button
                alertDialog.setPositiveButton("Ya, saya sudah konfirmasi data", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        ApiDatabaseEmployee apiService =
                id.variable.dicicilaja.API.Client.ApiDatabaseEmployee.getClientDatabaseEmployee().create(ApiDatabaseEmployee.class);

        Call<DatabaseEmployeeResponse> call = apiService.getDatabaseEmployee(apiKey);
        call.enqueue(new Callback<DatabaseEmployeeResponse>() {
            @Override
            public void onResponse(Call<DatabaseEmployeeResponse> call, Response<DatabaseEmployeeResponse> response) {
                if ( response.isSuccessful() ) {
                    List<DatabaseEmployee> databaseEmployees = response.body().getData();
                    recyclerView.setAdapter(new DatabaseEmployeeAdapter(databaseEmployees, R.layout.card_database_crh, getBaseContext()));
                } else {
                    //Toast.makeText(getContext(), session.getToken(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DatabaseEmployeeResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
                break;
            case R.id.search:
                break;
            case R.id.filter:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.database_crh_menu, menu);
        return true;
    }

}
