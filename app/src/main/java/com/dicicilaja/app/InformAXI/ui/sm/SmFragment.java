package com.dicicilaja.app.InformAXI.ui.sm;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dicicilaja.app.InformAXI.adapter.AxiHomeAdapter;
import com.dicicilaja.app.InformAXI.adapter.ImageSliderAdapter;
import com.dicicilaja.app.InformAXI.adapter.SmHomeAdapter;
import com.dicicilaja.app.InformAXI.model.AxiHome;
import com.dicicilaja.app.InformAXI.model.Image;
import com.dicicilaja.app.InformAXI.model.axiterdaftar.AxiTerdaftar;
import com.dicicilaja.app.InformAXI.model.axiterdaftar.Datum;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
import com.dicicilaja.app.InformAXI.ui.search.SearchActivity;
import com.dicicilaja.app.InformAXI.utils.DatePickerRange;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmFragment extends Fragment implements ImageSliderAdapter.OnItemClickListener {


    SessionManager session;
    String apiKey;
    String area_id;
    /* UI Region */
    private SwipeRefreshLayout swipeHome;
    private NestedScrollView nestedHome;
    private ViewPager sliderImage;
    private RelativeLayout searchContainer;
    private TextView searchResult;
    private RecyclerView rvAxi;
    private ProgressBar pbHome;
    private RelativeLayout filterContainer;
    private ImageView icCheck;
    private RelativeLayout noDataContainer;
    private Dialog dialog;
    private DatePickerRange dp;

    private List<Image> imageList;
    private List<Datum> axiList;

    private ImageSliderAdapter imageAdapter;
    private SmHomeAdapter listAdapter;

    private String date = null;
    private String status = null;
    private String startDate = null;
    private String endDate = null;
    private String startDateStr = null;
    private String endDateStr = null;

    private SharedPreferences pref;

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    private final int START_PAGE = 1;
    private int page = START_PAGE, lastPage = 0, branchId = -1, axiPerBranch = -1;
    private boolean isRefresh = false, isLastPage = false;
    private String branchName = null;

    public SmFragment() {
        // Required empty public constructor
    }

    public static SmFragment newInstance(String param) {
        SmFragment fragment = new SmFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
        initImageSlider();
        initRecyclerview();
        initListener();
    }

    /* Init Variables */
    private void initVariables(View view) {
        swipeHome = view.findViewById(R.id.swipe_home);
        nestedHome = view.findViewById(R.id.nested_home);
        sliderImage = view.findViewById(R.id.image_slider);
        searchContainer = view.findViewById(R.id.search_container);
        searchResult = view.findViewById(R.id.search_result);
        rvAxi = view.findViewById(R.id.rv_axi);
        pbHome = view.findViewById(R.id.pb_home);
        filterContainer = view.findViewById(R.id.filter_container);
        icCheck = view.findViewById(R.id.ic_check);
        noDataContainer = view.findViewById(R.id.no_data_container);
        dp = new DatePickerRange();

        imageList = new ArrayList<>();
        axiList = new ArrayList<>();

        imageAdapter = new ImageSliderAdapter(imageList, requireContext(), this);
        listAdapter = new SmHomeAdapter(axiList, requireContext());

        session = new SessionManager(requireContext());
        apiKey = "Bearer " + session.getToken();
        branchId = Integer.valueOf(session.getBranchId());
        branchName = session.getBranch();

        //pref = requireContext().getSharedPreferences("DicicilajaPref", Context.MODE_PRIVATE);
        //if (!pref.getString("branch_id", "").isEmpty())
        //    branchId = Integer.valueOf(session.getBranchId());

        //if (!pref.getString("branch_name", "").isEmpty())
        //    branchName = pref.getString("branch_name", "");

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance();
        jsonApi = retrofit.create(NetworkInterface.class);

    }

    private void initImageSlider() {

        /* Init Data */
        for (int i = 0; i < 5; i++) {
            Image img = new Image();
            img.setImageDrawable(R.drawable.banner);
            imageList.add(img);
        }

        sliderImage.setAdapter(imageAdapter);

    }

        private void initRecyclerview() {
        LinearLayoutManager lm = new LinearLayoutManager(requireContext());

        rvAxi.setLayoutManager(lm);
        rvAxi.setHasFixedSize(true);
        rvAxi.setAdapter(listAdapter);
    }

    private void onError(Throwable t) {
        Tools.showLongToast(requireContext(), t.getMessage());
        pbHome.setVisibility(View.GONE);
    }

    private void initListener() {
        area_id = session.getAreaId();
        pbHome.setVisibility(View.VISIBLE);
        getHomeData(area_id);

        swipeHome.setOnRefreshListener(() -> {
            isRefresh = true;
            isLastPage = false;
            axiList.clear();
            listAdapter.notifyDataSetChanged();
            pbHome.setVisibility(View.VISIBLE);
            getHomeData(area_id);
        });
    }

    private void getHomeData(String area_id) {
        mCompositeDisposable.add(
                jsonApi.getAxiTerdaftar(apiKey, area_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessGetHomeData, this::onError)
        );
    }

    private void onSuccessGetHomeData(AxiTerdaftar data) {
        /* getHomeData Subscriber */
        if (data != null && data.getData() != null && data.getData().size() > 0) {
            if (isRefresh) {
                axiList.clear();
                listAdapter.notifyDataSetChanged();
                isRefresh = false;
            }

//            axiPerBranch = data.getMeta().getTotal();

            axiList.addAll(data.getData());
//            lastPage = data.getMeta().getLastPage();
            listAdapter.notifyDataSetChanged();

            String axiPerBranchStr = String.format(new Locale("in", "ID"),
                    "%d AXI di Cabang %s", axiPerBranch, branchName);
//            searchResult.setText(axiPerBranchStr);
        } else {
            if (page == START_PAGE) {
                noDataContainer.setVisibility(View.VISIBLE);
            } else {
                noDataContainer.setVisibility(View.GONE);
            }
        }
        pbHome.setVisibility(View.GONE);
        swipeHome.setRefreshing(false);
    }

    @Override
    public void onItemClick(View view, Image obj) {

    }

}
