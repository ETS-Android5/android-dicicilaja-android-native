package com.dicicilaja.dicicilaja.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.dicicilaja.dicicilaja.API.Client.ClientDatabaseEmployee;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceDatabaseCRH;
import com.dicicilaja.dicicilaja.API.Item.DatabaseCRH.Datum;
import com.dicicilaja.dicicilaja.Adapter.DatabaseCRHAdapter;
import com.dicicilaja.dicicilaja.R;
import com.dicicilaja.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;

public class DatabaseCRH extends AppCompatActivity implements DatabaseCRHAdapter.CRHAdapterListener{


    private static final String TAG = DatabaseCRH.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Datum> dataList;
    private DatabaseCRHAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_crh);

        final SessionManager session = new SessionManager(getBaseContext());
        final String apiKey = "Bearer " + session.getToken();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_database_crh);


        TextView title_hasil_pencarian = findViewById(R.id.title_hasil_pencarian);


        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_hasil_pencarian.setTypeface(opensans_bold);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        dataList = new ArrayList<>();
        mAdapter = new DatabaseCRHAdapter(this, dataList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        InterfaceDatabaseCRH apiService =
                ClientDatabaseEmployee.getClientDatabaseEmployee().create(InterfaceDatabaseCRH.class);

        Call<com.dicicilaja.dicicilaja.API.Item.DatabaseCRH.DatabaseCRH> call = apiService.getDatabaseCRH(apiKey);
        call.enqueue(new Callback<com.dicicilaja.dicicilaja.API.Item.DatabaseCRH.DatabaseCRH>() {
            @Override
            public void onResponse(Call<com.dicicilaja.dicicilaja.API.Item.DatabaseCRH.DatabaseCRH> call, retrofit2.Response<com.dicicilaja.dicicilaja.API.Item.DatabaseCRH.DatabaseCRH> response) {
                List<Datum> items = response.body().getData();
                dataList.clear();
                dataList.addAll(items);

                mAdapter.notifyDataSetChanged();

//                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
//                    @Override
//                    public void onClick(View view, int position) {
//                        Intent intent = new Intent();
//                        intent.putExtra("ID_DATABASE", dataList.get(position).getUserId().toString());
//                        setResult(RESULT_OK, intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onLongClick(View view, int position) {
//                    }
//                }));


            }

            @Override
            public void onFailure(Call<com.dicicilaja.dicicilaja.API.Item.DatabaseCRH.DatabaseCRH> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.database_crh_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onDataSelected(Datum datum) {
//        Toast.makeText(getApplicationContext(), "Selected: " + datum.getBranch() + ", " + datum.getBranch(), Toast.LENGTH_LONG).show();
    }
}
