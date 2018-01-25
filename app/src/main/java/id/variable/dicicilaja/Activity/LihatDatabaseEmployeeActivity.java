package id.variable.dicicilaja.Activity;

import android.content.Intent;
import android.graphics.Typeface;
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

import id.variable.dicicilaja.API.Client.ClientDatabaseEmployee;
import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceDatabaseCRO;
import id.variable.dicicilaja.API.Interface.InterfaceDatabaseEmployee;
import id.variable.dicicilaja.API.Item.DatabaseCRO.DatabaseCRO;
import id.variable.dicicilaja.API.Item.DatabaseCRO.Datum;
import id.variable.dicicilaja.API.Item.DatabaseEmployee;
import id.variable.dicicilaja.API.Item.DatabaseEmployeeResponse;
import id.variable.dicicilaja.Adapter.DatabaseCROAdapter;
import id.variable.dicicilaja.Adapter.DatabaseEmployeeAdapter;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LihatDatabaseEmployeeActivity extends AppCompatActivity {

    private static final String TAG = LihatDatabaseEmployeeActivity.class.getSimpleName();

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

        if(session.getRole().equals("tc")){
            InterfaceDatabaseEmployee apiService =
                    ClientDatabaseEmployee.getClientDatabaseEmployee().create(InterfaceDatabaseEmployee.class);

            Call<DatabaseEmployeeResponse> call = apiService.getDatabaseEmployee(apiKey);
            call.enqueue(new Callback<DatabaseEmployeeResponse>() {
                @Override
                public void onResponse(Call<DatabaseEmployeeResponse> call, Response<DatabaseEmployeeResponse> response) {
                    if ( response.isSuccessful() ) {
                        final List<DatabaseEmployee> databaseEmployees = response.body().getData();
                        recyclerView.setAdapter(new DatabaseEmployeeAdapter(databaseEmployees, R.layout.card_database_crh, getBaseContext()));

                        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
//                                Toast.makeText(getBaseContext(), "Urutan database crh : "+position + " nik : " + databaseEmployees.get(position).getUserId().toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.putExtra("ID_DATABASE", databaseEmployees.get(position).getUserId().toString());
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                            }
                        }));
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
        }else if(session.getRole().equals("crh")){
            InterfaceDatabaseCRO apiService =
                    RetrofitClient.getClient().create(InterfaceDatabaseCRO.class);

            Call<DatabaseCRO> call = apiService.getDatabaseCRO(apiKey);
            call.enqueue(new Callback<DatabaseCRO>() {
                @Override
                public void onResponse(Call<DatabaseCRO> call, Response<DatabaseCRO> response) {
                    if ( response.isSuccessful() ) {
                        final List<Datum> databaseEmployees = response.body().getData();
                        recyclerView.setAdapter(new DatabaseCROAdapter(databaseEmployees, R.layout.card_database_crh, getBaseContext()));

                        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
//                                Toast.makeText(getBaseContext(), "Urutan database crh : "+position + " nik : " + databaseEmployees.get(position).getUserId().toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.putExtra("ID_DATABASE", databaseEmployees.get(position).getUserId().toString());
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                            }
                        }));
                    } else {
                        //Toast.makeText(getContext(), session.getToken(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<DatabaseCRO> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }


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
