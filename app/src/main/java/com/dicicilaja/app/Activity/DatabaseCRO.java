package com.dicicilaja.app.Activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.dicicilaja.app.API.Client.ClientDatabaseEmployee;
import com.dicicilaja.app.API.Interface.InterfaceDatabaseCRO;
import com.dicicilaja.app.Adapter.DatabaseCROAdapter;
import com.dicicilaja.app.Model.RequestMeta;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;

public class DatabaseCRO extends AppCompatActivity implements DatabaseCROAdapter.CROAdapterListener{


    private static final String TAG = DatabaseCRO.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<com.dicicilaja.app.API.Item.DatabaseCRO.Datum> dataList;
    private DatabaseCROAdapter mAdapter;
    private SearchView searchView;

    private ProgressDialog progress;

    private String apiKey;
    private int totalData = 1;
    private int totalPage = 1;
    private int currentPage = 1;
    private boolean isLoading = false;
    private String searchVal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_cro);

        final SessionManager session = new SessionManager(getBaseContext());
        apiKey = "Bearer " + session.getToken();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_database_cro);


        TextView title_hasil_pencarian = findViewById(R.id.title_hasil_pencarian);


        Typeface opensans_extrabold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        title_hasil_pencarian.setTypeface(opensans_bold);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        dataList = new ArrayList<>();
        mAdapter = new DatabaseCROAdapter(this, dataList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        progress = new ProgressDialog(this);
        progress.setMessage("Please wait...");

        // Load Data
        doLoadData();

        // Init Listener
        initListener();
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
                //mAdapter.getFilter().filter(query);
                searchVal = query;
                doLoadData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                //mAdapter.getFilter().filter(query);
                if( query.equals("") ) {
                    searchVal = null;
                    doLoadData();
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public void onDataSelected(com.dicicilaja.app.API.Item.DatabaseCRO.Datum datum) {
//        Toast.makeText(getApplicationContext(), "Selected: " + datum.getBranch() + ", " + datum.getBranch(), Toast.LENGTH_LONG).show();
    }

    private void doLoadData() {
        showLoading();

        InterfaceDatabaseCRO apiService =
                ClientDatabaseEmployee.getClientDatabaseEmployee().create(InterfaceDatabaseCRO.class);

        Call<com.dicicilaja.app.API.Item.DatabaseCRO.DatabaseCRO> call = apiService.getDatabaseCRO(apiKey, currentPage, searchVal);
        call.enqueue(new Callback<com.dicicilaja.app.API.Item.DatabaseCRO.DatabaseCRO>() {
            @Override
            public void onResponse(Call<com.dicicilaja.app.API.Item.DatabaseCRO.DatabaseCRO> call, retrofit2.Response<com.dicicilaja.app.API.Item.DatabaseCRO.DatabaseCRO> response) {
                List<com.dicicilaja.app.API.Item.DatabaseCRO.Datum> items = response.body().getData();
                RequestMeta meta = response.body().getMeta();

//                dataList.clear();
//                dataList.addAll(items);

//                mAdapter.notifyDataSetChanged();

                DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                String total = formatter.format(meta.getTotal()).replace(",", ".");

                totalPage = meta.getLastPage();
                totalData = meta.getTotal();
                currentPage = meta.getCurrentPage();

                Log.d("PAGE::", String.valueOf(currentPage));

                if( currentPage == 1 ) {
                    dataList.clear();
                    dataList.addAll(items);

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter.refreshAdapter(items);
                }

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

                hideLoading();
            }

            @Override
            public void onFailure(Call<com.dicicilaja.app.API.Item.DatabaseCRO.DatabaseCRO> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(DatabaseCRO.this);
                alertDialog.setMessage("Koneksi internet tidak ditemukan");

                alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        doLoadData();
                    }
                });

                hideLoading();
                alertDialog.show();
            }
        });
    }

    private void initListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int countItem = layoutManager.getItemCount();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                boolean isLastPosition = countItem - 1 == lastVisiblePosition;

                if( !isLoading && isLastPosition && currentPage < totalPage ) {
                    showLoading();
                    currentPage = currentPage + 1;
                    doLoadData();
                }
            }
        });
    }

    private void showLoading() {
        isLoading = true;
        progress.show();
    }

    private void hideLoading() {
        isLoading = false;
        progress.dismiss();
    }
}

