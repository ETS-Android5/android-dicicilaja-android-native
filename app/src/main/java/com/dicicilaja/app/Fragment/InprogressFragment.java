package com.dicicilaja.app.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.dicicilaja.app.API.Client.RetrofitClient;
import com.dicicilaja.app.API.Interface.InterfaceRequest;
import com.dicicilaja.app.API.Interface.InterfaceTask;
import com.dicicilaja.app.API.Item.Request.Request;
import com.dicicilaja.app.API.Item.Task.Datum;
import com.dicicilaja.app.API.Item.Task.Task;
import com.dicicilaja.app.Adapter.RequestAdapter;
import com.dicicilaja.app.Adapter.TaskAdapter;
import com.dicicilaja.app.Component.SwipeRefreshLayoutWithEmpty;
import com.dicicilaja.app.Model.RequestMeta;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Remote.ApiUtils;
import com.dicicilaja.app.Remote.RequestProcess;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InprogressFragment extends Fragment implements RequestAdapter.RequestAdapterListener, TaskAdapter.TaskAdapterListener {

    private static final String TAG = InprogressFragment.class.getSimpleName();
    List<com.dicicilaja.app.API.Item.Request.Datum> requests;
    List<Datum> tasks;
    RequestProcess interfaceTCProcess;
    Integer positionCard;
    String apiKey;
    SwipeRefreshLayoutWithEmpty mSwipeRefreshLayout;
    RequestAdapter requestAdapter;
    TaskAdapter taskAdapter;

    RelativeLayout top_attribut;
    SearchView search;
    CardView search_toggle;

    FloatingActionButton fabScrollTop;
    RecyclerView recyclerView;
    EditText searchBox;
    ImageView searchClose;

    ProgressDialog progress;
    SessionManager session;

    TextView title_pengumuman;
    TextView jumlah_pengajuan;

    int totalData = 1;
    int totalPage = 1;
    int currentPage = 1;
    boolean isLoading = false;
    String searchVal = null;

    RelativeLayout layoutEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inprogress, container, false);

        session = new SessionManager(getContext());
        apiKey = "Bearer " + session.getToken();

        title_pengumuman = view.findViewById(R.id.title_pengumuman);
        jumlah_pengajuan = view.findViewById(R.id.jumlah_pengajuan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");


        title_pengumuman.setTypeface(opensans_bold);
        jumlah_pengajuan.setTypeface(opensans_bold);

        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        recyclerView =  view.findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        layoutEmpty = view.findViewById(R.id.layoutEmpty);

        interfaceTCProcess = ApiUtils.getRequestService();

        requests = new ArrayList<>();
        tasks = new ArrayList<>();
        requestAdapter = new RequestAdapter(getContext(), requests, this);
        taskAdapter = new TaskAdapter(getContext(), tasks, this);

        search = view.findViewById(R.id.search);
        search_toggle = view.findViewById(R.id.search_toggle);
        top_attribut = view.findViewById(R.id.top_attribut);

        fabScrollTop = view.findViewById(R.id.fabScrollTop);
        fabScrollTop.bringToFront();

        search.setVisibility(View.GONE);

        searchBox = search.findViewById (R.id.search_src_text);
        searchBox.setTextSize(16);
        searchBox.setTextColor(Color.parseColor("#000000"));
        searchBox.setCursorVisible(false);

        searchClose = search.findViewById (R.id.search_close_btn);
        searchClose.setColorFilter (Color.parseColor("#F89E4C"), PorterDuff.Mode.SRC_ATOP);
        searchClose.setImageResource(R.drawable.ic_close);

        search_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search.getVisibility() == View.GONE) {
                    top_attribut.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                    search.requestFocus();
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    InputMethodManager imm = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(search, InputMethodManager.SHOW_IMPLICIT);
                }else {
                    top_attribut.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);
                    search.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            }
        });

        progress = new ProgressDialog(getContext());
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                doLoadData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        // Load data
        doLoadData();

        // Init recyleview listener
        initListener();

        // Hide recyle
        hideEmpty();

        final SearchView search = view.findViewById(R.id.search);
        search.setActivated(true);
        search.setQueryHint("Ketik disini untuk mencari");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*if(session.getRole().equals("admin") || session.getRole().equals("tc")) {
                    requestAdapter.getFilter().filter(query);
                } else if(session.getRole().equals("crh") || session.getRole().equals("cro")) {
                    taskAdapter.getFilter().filter(query);
                }*/
                searchVal = query;
                doLoadData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                /*if(session.getRole().equals("admin") || session.getRole().equals("tc")) {
                    requestAdapter.getFilter().filter(query);
                } else if(session.getRole().equals("crh") || session.getRole().equals("cro")) {
                    taskAdapter.getFilter().filter(query);
                }*/
                if( query.equals("") ) {
                    searchVal = null;
                    doLoadData();
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onDataSelected(com.dicicilaja.app.API.Item.Request.Datum datum) {

    }

    @Override
    public void onDataSelected(Datum datum) {

    }

    private void doLoadData() {
        Log.d("page", String.valueOf(currentPage));
        showLoading(false);

        Log.d("FRS:::", session.getRole());
        if(session.getRole().equals("admin") || session.getRole().equals("tc")){
            InterfaceRequest apiService =
                    RetrofitClient.getClient().create(InterfaceRequest.class);

            Call<Request> call = apiService.getRequest(apiKey, currentPage);
            call.enqueue(new Callback<Request>() {
                @Override
                public void onResponse(Call<Request> call, Response<Request> response) {
                    if (response.isSuccessful()) {
                        List<com.dicicilaja.app.API.Item.Request.Datum> items = response.body().getData();
                        RequestMeta meta = response.body().getMeta();
                        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                        String total = formatter.format(meta.getTotal()).replace(",", ".");

                        totalPage = meta.getLastPage();
                        totalData = meta.getTotal();
                        currentPage = meta.getCurrentPage();

                        if( meta.getCurrentPage() == 1 ) {
                            requests.clear();
                            requests.addAll(items);

                            requestAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(requestAdapter);
                        } else {
                            requestAdapter.refreshAdapter(items);
                        }

                        jumlah_pengajuan.setText(total);
                    } else {
                        session.logoutUser();
                    }

                    hideLoading();
                }

                @Override
                public void onFailure(Call<Request> call, Throwable t) {
                    progress.dismiss();
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setMessage("Koneksi internet tidak ditemukan");

                    alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
        } else if(session.getRole().equals("crh") || session.getRole().equals("cro")){
            Log.d("FRS:::", session.getRole());
            InterfaceTask apiService =
                    RetrofitClient.getClient().create(InterfaceTask.class);

            Call<Task> call = apiService.getTask(apiKey, currentPage);
            call.enqueue(new Callback<Task>() {
                @Override
                public void onResponse(Call<Task> call, Response<Task> response) {
                    Log.d("FRS:::", String.valueOf(response.isSuccessful()));
                    if ( response.isSuccessful() ) {
                        List<Datum> items = response.body().getData();
                        RequestMeta meta = response.body().getMeta();
                        DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                        String total = formatter.format(meta.getTotal()).replace(",", ".");

                        jumlah_pengajuan.setText(total);

                        totalPage = meta.getLastPage();
                        totalData = meta.getTotal();
                        currentPage = meta.getCurrentPage();

                        Log.d("FRS::::", meta.getCurrentPage().toString());

                        if( meta.getCurrentPage() == 1 ) {
                            tasks.clear();
                            tasks.addAll(items);

                            taskAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(taskAdapter);
                        } else {
                            taskAdapter.refreshAdapter(tasks);
                        }

                        if( tasks.size() > 0 ) {
                            hideEmpty();
                        } else {
                            showEmpty();
                        }
/*
                        jumlah_pengajuan.setText(total);
                        tasks.clear();
                        tasks.addAll(items);

                        taskAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(taskAdapter);

                        progress.dismiss();
                        */
                    } else {
                        session.logoutUser();
                    }

                    hideLoading();
                }

                @Override
                public void onFailure(Call<Task> call, Throwable t) {
                    progress.dismiss();
                    t.printStackTrace();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setMessage("Koneksi internet tidak ditemukan");

                    alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
    }

    private void initListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int countItem = layoutManager.getItemCount();

                int visiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if( visiblePosition >= 3 ) {
                    fabScrollTop.setVisibility(View.VISIBLE);
                } else {
                    fabScrollTop.setVisibility(View.GONE);
                }

                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                boolean isLastPosition = countItem - 1 == lastVisiblePosition;

                if( !isLoading && isLastPosition && currentPage < totalPage ) {
                    showLoading(false);
                    currentPage = currentPage + 1;
                    doLoadData();
                }
            }
        });
        fabScrollTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(0);
                fabScrollTop.setVisibility(View.GONE);
            }
        });
    }

    private void showLoading(boolean isRefresh) {
        isLoading = true;
        progress.show();
    }

    private void hideLoading() {
        isLoading = false;
        progress.dismiss();
    }

    private void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    private void hideEmpty() {
        recyclerView.setVisibility(View.VISIBLE);
        layoutEmpty.setVisibility(View.GONE);
    }
}