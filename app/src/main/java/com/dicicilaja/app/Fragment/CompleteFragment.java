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
import com.dicicilaja.app.API.Interface.InterfaceRequestProgress;
import com.dicicilaja.app.API.Item.RequestProgress.Datum;
import com.dicicilaja.app.API.Item.RequestProgress.RequestProgress;
import com.dicicilaja.app.Adapter.RequestProgressAdapter;
import com.dicicilaja.app.Model.RequestMeta;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteFragment extends Fragment implements RequestProgressAdapter.RequestProgressAdapterListener {

    private static final String TAG = CompleteFragment.class.getSimpleName();
    List<Datum> requestProgress;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RequestProgressAdapter requestProgressAdapter;
    String apiKey;
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
        View view =  inflater.inflate(R.layout.fragment_complete, container, false);

        title_pengumuman = view.findViewById(R.id.title_pengumuman);
        jumlah_pengajuan = view.findViewById(R.id.jumlah_pengajuan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");

        SessionManager session = new SessionManager(getActivity().getBaseContext());
        apiKey = "Bearer " + session.getToken();

        title_pengumuman.setTypeface(opensans_bold);
        jumlah_pengajuan.setTypeface(opensans_bold);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        recyclerView =  view.findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fabScrollTop = view.findViewById(R.id.fabScrollTop);
        fabScrollTop.bringToFront();

        requestProgress = new ArrayList<>();
        requestProgressAdapter = new RequestProgressAdapter(getContext(), requestProgress, this);

        recyclerView.setAdapter(requestProgressAdapter);

        layoutEmpty = view.findViewById(R.id.layoutEmpty);

        progress = new ProgressDialog(getContext());
        progress.setMessage("Please wait...");

        search = view.findViewById(R.id.search);
        search_toggle = view.findViewById(R.id.search_toggle);
        top_attribut = view.findViewById(R.id.top_attribut);
        search.setVisibility(View.GONE);

        fabScrollTop = view.findViewById(R.id.fabScrollTop);
        fabScrollTop.bringToFront();

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

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doLoadData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        // Load Data
        doLoadData();

        // Init Listener
        initListener();

        // Hide first
        hideEmpty();

        search = view.findViewById(R.id.search);
        search.setActivated(true);
        search.setQueryHint("Ketik disini untuk mencari");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchVal = query;
                doLoadData();
                //requestProgressAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //searchVal = query;
                //doLoadData();
                //requestProgressAdapter.getFilter().filter(query);
                if( query.equals("") ) {
                    searchVal = null;
                    doLoadData();
                }
                return true;
            }
        });

        return view;
    }

    @Override
    public void onDataSelected(Datum datum) {

    }

    private void doLoadData() {
        //showLoading();

        InterfaceRequestProgress apiService =
                RetrofitClient.getClient().create(InterfaceRequestProgress.class);

        Call<RequestProgress> call = apiService.getRequestProgress(apiKey, currentPage, searchVal);
        call.enqueue(new Callback<RequestProgress>() {
            @Override
            public void onResponse(Call<RequestProgress> call, Response<RequestProgress> response) {
                if ( response.isSuccessful() ) {
                    List<Datum> items = response.body().getData();
                    RequestMeta meta = response.body().getMeta();
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");

                    String total = formatter.format(meta.getTotal()).replace(",", ".");

                    totalPage = meta.getLastPage();
                    totalData = meta.getTotal();
                    currentPage = meta.getCurrentPage();

                    if( meta.getCurrentPage() == 1 ) {
                        requestProgress.clear();
                        requestProgress.addAll(items);

                        requestProgressAdapter.notifyDataSetChanged();
                    } else {
                        requestProgressAdapter.refreshAdapter(items);
                    }

                    jumlah_pengajuan.setText(total);

                    if( requestProgress.size() > 0 ) {
                        hideEmpty();
                    } else {
                        showEmpty();
                    }

                    hideLoading();

//                    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
//                    jumlah_pengajuan.setText(formatter.format(items.size()).replace(",","."));
//                    requestProgress.clear();
//                    requestProgress.addAll(items);
//
//                    requestProgressAdapter.notifyDataSetChanged();
//                    recyclerView.setAdapter(requestProgressAdapter);


//                    requestProgress = response.body().getData();
//                    jumlah_pengajuan.setText(Integer.toString(requestProgress.size()));
//                    recyclerView.setAdapter(new RequestProgressAdapter(requestProgress, R.layout.card_pengajuan, getContext()));
//
//
//                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
//                        @Override
//                        public void onClick(View view, int position) {
//                            Intent intent = new Intent(getContext(), DetailRequestActivity.class);
//                            intent.putExtra("EXTRA_REQUEST_ID", requestProgress.get(position).getId().toString());
//                            intent.putExtra("STATUS", true);
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void onLongClick(View view, int position) {
//                        }
//                    }));


                }

            }

            @Override
            public void onFailure(Call<RequestProgress> call, Throwable t) {
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

    private void showLoading() {
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
