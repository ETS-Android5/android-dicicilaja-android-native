package id.variable.dicicilaja.Fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceRequestProgress;
import id.variable.dicicilaja.API.Item.RequestProgress.Datum;
import id.variable.dicicilaja.API.Item.RequestProgress.RequestProgress;
import id.variable.dicicilaja.Activity.DetailRequestActivity;
import id.variable.dicicilaja.Adapter.RequestAdapter;
import id.variable.dicicilaja.Adapter.RequestProgressAdapter;
import id.variable.dicicilaja.Adapter.TaskAdapter;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Session.SessionManager;
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
    ImageView search_toggle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_complete, container, false);

        TextView title_pengumuman = view.findViewById(R.id.title_pengumuman);
        final TextView jumlah_pengajuan = view.findViewById(R.id.jumlah_pengajuan);
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
        final RecyclerView recyclerView =  view.findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        requestProgress = new ArrayList<>();
        requestProgressAdapter = new RequestProgressAdapter(getContext(), requestProgress, this);

        search = view.findViewById(R.id.search);
        search_toggle = view.findViewById(R.id.search_toggle);
        top_attribut = view.findViewById(R.id.top_attribut);
        search.setVisibility(View.GONE);

        search_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search.getVisibility() == View.GONE) {
                    top_attribut.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                }else {
                    top_attribut.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                InterfaceRequestProgress apiService =
                        RetrofitClient.getClient().create(InterfaceRequestProgress.class);

                Call<RequestProgress> call = apiService.getRequestProgress(apiKey);
                call.enqueue(new Callback<RequestProgress>() {
                    @Override
                    public void onResponse(Call<RequestProgress> call, Response<RequestProgress> response) {
                        if ( response.isSuccessful() ) {
                            List<Datum> items = response.body().getData();
                            jumlah_pengajuan.setText(Integer.toString(items.size()));
                            requestProgress.clear();
                            requestProgress.addAll(items);

                            requestProgressAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(requestProgressAdapter);

//                            requestProgress = response.body().getData();
//                            jumlah_pengajuan.setText(Integer.toString(requestProgress.size()));
//                            recyclerView.setAdapter(new RequestProgressAdapter(requestProgress, R.layout.card_pengajuan, getContext()));
//
//
//                            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
//                                @Override
//                                public void onClick(View view, int position) {
//                                    Intent intent = new Intent(getContext(), DetailRequestActivity.class);
//                                    intent.putExtra("EXTRA_REQUEST_ID", requestProgress.get(position).getId().toString());
//                                    intent.putExtra("STATUS", true);
//                                    startActivity(intent);
//                                }
//
//                                @Override
//                                public void onLongClick(View view, int position) {
//                                }
//                            }));


                        }

                    }

                    @Override
                    public void onFailure(Call<RequestProgress> call, Throwable t) {

                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        InterfaceRequestProgress apiService =
                RetrofitClient.getClient().create(InterfaceRequestProgress.class);

        Call<RequestProgress> call = apiService.getRequestProgress(apiKey);
        call.enqueue(new Callback<RequestProgress>() {
            @Override
            public void onResponse(Call<RequestProgress> call, Response<RequestProgress> response) {
                if ( response.isSuccessful() ) {
                    List<Datum> items = response.body().getData();
                    jumlah_pengajuan.setText(Integer.toString(items.size()));
                    requestProgress.clear();
                    requestProgress.addAll(items);

                    requestProgressAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(requestProgressAdapter);


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

            }
        });

        SearchView search = view.findViewById(R.id.search);
        search.setActivated(true);
        search.setQueryHint("Ketik disini untuk mencari");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                requestProgressAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                requestProgressAdapter.getFilter().filter(query);
                return true;
            }
        });

        return view;
    }

    @Override
    public void onDataSelected(Datum datum) {

    }

}
