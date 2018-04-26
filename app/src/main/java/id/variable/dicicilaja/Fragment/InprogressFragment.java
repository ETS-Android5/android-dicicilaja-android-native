package id.variable.dicicilaja.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.variable.dicicilaja.API.Client.RetrofitClient;
import id.variable.dicicilaja.API.Interface.InterfaceRequest;
import id.variable.dicicilaja.API.Interface.InterfaceTask;
import id.variable.dicicilaja.API.Item.Request.Request;
import id.variable.dicicilaja.API.Item.Task.Datum;
import id.variable.dicicilaja.API.Item.Task.Task;
import id.variable.dicicilaja.Activity.DetailRequestActivity;
import id.variable.dicicilaja.Adapter.RequestAdapter;
import id.variable.dicicilaja.Adapter.TaskAdapter;
import id.variable.dicicilaja.Listener.ClickListener;
import id.variable.dicicilaja.Listener.RecyclerTouchListener;
import id.variable.dicicilaja.Model.ResRequestProcess;
import id.variable.dicicilaja.R;
import id.variable.dicicilaja.Remote.ApiUtils;
import id.variable.dicicilaja.Remote.RequestProcess;
import id.variable.dicicilaja.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InprogressFragment extends Fragment implements RequestAdapter.RequestAdapterListener, TaskAdapter.TaskAdapterListener {

    private static final String TAG = InprogressFragment.class.getSimpleName();
    List<id.variable.dicicilaja.API.Item.Request.Datum> requests;
    List<Datum> tasks;
    RequestProcess interfaceTCProcess;
    Integer positionCard;
    String apiKey;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RequestAdapter requestAdapter;
    TaskAdapter taskAdapter;

    RelativeLayout top_attribut;
    SearchView search;
    CardView search_toggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inprogress, container, false);

        final SessionManager session = new SessionManager(getContext());
        apiKey = "Bearer " + session.getToken();

        TextView title_pengumuman = view.findViewById(R.id.title_pengumuman);
        final TextView jumlah_pengajuan = view.findViewById(R.id.jumlah_pengajuan);
        Typeface opensans_extrabold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        Typeface opensans_bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
        Typeface opensans_semibold = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
        Typeface opensans_reguler = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf");


        title_pengumuman.setTypeface(opensans_bold);
        jumlah_pengajuan.setTypeface(opensans_bold);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        final RecyclerView recyclerView =  view.findViewById(R.id.recycler_pengajuan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        interfaceTCProcess = ApiUtils.getRequestService();

        requests = new ArrayList<>();
        tasks = new ArrayList<>();
        requestAdapter = new RequestAdapter(getContext(), requests, this);
        taskAdapter = new TaskAdapter(getContext(), tasks, this);

        search = view.findViewById(R.id.search);
        search_toggle = view.findViewById(R.id.search_toggle);
        top_attribut = view.findViewById(R.id.top_attribut);
        search.setVisibility(View.GONE);

        EditText searchBox = search.findViewById (android.support.v7.appcompat.R.id.search_src_text);
        searchBox.setTextSize(16);
        searchBox.setTextColor(Color.parseColor("#000000"));
        searchBox.setCursorVisible(false);

        ImageView searchClose = search.findViewById (android.support.v7.appcompat.R.id.search_close_btn);
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

        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage("Sedang memuat data...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(session.getRole().equals("admin") || session.getRole().equals("tc")){
                    InterfaceRequest apiService =
                            RetrofitClient.getClient().create(InterfaceRequest.class);

                    Call<Request> call = apiService.getRequest(apiKey);
                    call.enqueue(new Callback<Request>() {
                        @Override
                        public void onResponse(Call<Request> call, Response<Request> response) {
                            if ( response.isSuccessful() ) {
                                List<id.variable.dicicilaja.API.Item.Request.Datum> items = response.body().getData();
                                jumlah_pengajuan.setText(Integer.toString(items.size()));
                                requests.clear();
                                requests.addAll(items);

                                requestAdapter.notifyDataSetChanged();
                                recyclerView.setAdapter(requestAdapter);

                                progress.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Request> call, Throwable t) {
                            progress.dismiss();
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                            alertDialog.setMessage("Koneksi internet tidak ditemukan");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }
                    });
                }else if(session.getRole().equals("crh") || session.getRole().equals("cro")){
                    InterfaceTask apiService =
                            RetrofitClient.getClient().create(InterfaceTask.class);

                    Call<Task> call = apiService.getTask(apiKey);
                    call.enqueue(new Callback<Task>() {
                        @Override
                        public void onResponse(Call<Task> call, Response<Task> response) {
                            if ( response.isSuccessful() ) {
                                List<Datum> items = response.body().getData();
                                jumlah_pengajuan.setText(Integer.toString(items.size()));
                                tasks.clear();
                                tasks.addAll(items);

                                taskAdapter.notifyDataSetChanged();
                                recyclerView.setAdapter(taskAdapter);

                                progress.dismiss();
                            }

                        }

                        @Override
                        public void onFailure(Call<Task> call, Throwable t) {
                            progress.dismiss();
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                            alertDialog.setMessage("Koneksi internet tidak ditemukan");

                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }
                    });
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        if(session.getRole().equals("admin") || session.getRole().equals("tc")){
            InterfaceRequest apiService =
                    RetrofitClient.getClient().create(InterfaceRequest.class);

            Call<Request> call = apiService.getRequest(apiKey);
            call.enqueue(new Callback<Request>() {
                @Override
                public void onResponse(Call<Request> call, Response<Request> response) {
                    if ( response.isSuccessful() ) {
                        List<id.variable.dicicilaja.API.Item.Request.Datum> items = response.body().getData();
                        jumlah_pengajuan.setText(Integer.toString(items.size()));
                        requests.clear();
                        requests.addAll(items);

                        requestAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(requestAdapter);

                        progress.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<Request> call, Throwable t) {
                    progress.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setMessage("Koneksi internet tidak ditemukan");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
            });
        }
        else if(session.getRole().equals("crh") || session.getRole().equals("cro")){
            InterfaceTask apiService =
                    RetrofitClient.getClient().create(InterfaceTask.class);

            Call<Task> call = apiService.getTask(apiKey);
            call.enqueue(new Callback<Task>() {
                @Override
                public void onResponse(Call<Task> call, Response<Task> response) {
                    if ( response.isSuccessful() ) {
                        List<Datum> items = response.body().getData();
                        jumlah_pengajuan.setText(Integer.toString(items.size()));
                        tasks.clear();
                        tasks.addAll(items);

                        taskAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(taskAdapter);

                        progress.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<Task> call, Throwable t) {
                    progress.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setMessage("Koneksi internet tidak ditemukan");

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
//
                        }
                    });
                    alertDialog.show();
                }
            });
        }

        SearchView search = view.findViewById(R.id.search);
        search.setActivated(true);
        search.setQueryHint("Ketik disini untuk mencari");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(session.getRole().equals("admin") || session.getRole().equals("tc")) {
                    requestAdapter.getFilter().filter(query);
                } else if(session.getRole().equals("crh") || session.getRole().equals("cro")) {
                    taskAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(session.getRole().equals("admin") || session.getRole().equals("tc")) {
                    requestAdapter.getFilter().filter(query);
                } else if(session.getRole().equals("crh") || session.getRole().equals("cro")) {
                    taskAdapter.getFilter().filter(query);
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onDataSelected(id.variable.dicicilaja.API.Item.Request.Datum datum) {

    }

    @Override
    public void onDataSelected(Datum datum) {

    }
}