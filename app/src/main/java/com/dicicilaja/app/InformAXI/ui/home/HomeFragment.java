package com.dicicilaja.app.InformAXI.ui.home;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.dicicilaja.app.InformAXI.adapter.AxiHomeAdapter;
import com.dicicilaja.app.InformAXI.adapter.ImageSliderAdapter;
import com.dicicilaja.app.InformAXI.model.AxiHome;
import com.dicicilaja.app.InformAXI.model.Image;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
import com.dicicilaja.app.InformAXI.ui.search.SearchActivity;
import com.dicicilaja.app.InformAXI.utils.DatePickerRange;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.dicicilaja.app.InformAXI.utils.Constants.ACTIVE;
import static com.dicicilaja.app.InformAXI.utils.Constants.CHOOSE_DATE;
import static com.dicicilaja.app.InformAXI.utils.Constants.INACTIVE;
import static com.dicicilaja.app.InformAXI.utils.Constants.NEW_DATE;
import static com.dicicilaja.app.InformAXI.utils.Constants.OLD_DATE;
import static com.dicicilaja.app.InformAXI.utils.Constants.SUB_BUNDLE;
import static com.dicicilaja.app.InformAXI.utils.Constants.SUB_PICKER;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

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

    private List<Image> imageList;
    private List<AxiHome.DataBean> axiList;

    private ImageSliderAdapter imageAdapter;
    private AxiHomeAdapter listAdapter;

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

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
        initImageSlider();
        initRecyclerview();
        initListener();
        getHomeData(START_PAGE);
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

        imageList = new ArrayList<>();
        axiList = new ArrayList<>();

        imageAdapter = new ImageSliderAdapter(imageList, requireContext());
        listAdapter = new AxiHomeAdapter(axiList, requireContext());

        pref = requireContext().getSharedPreferences("Pref", Context.MODE_PRIVATE);
        if (!pref.getString("branch_id", "").isEmpty())
            branchId = Integer.valueOf(pref.getString("branch_id", ""));

        if (!pref.getString("branch_name", "").isEmpty())
            branchName = pref.getString("branch_name", "");

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance(requireContext());
        jsonApi = retrofit.create(NetworkInterface.class);
    }

    /* Init Image Slider */
    private void initImageSlider() {

        /* Init Data */
        for (int i = 0; i < 5; i++) {
            Image img = new Image();
            img.setImageDrawable(R.drawable.banner);
            imageList.add(img);
        }

        sliderImage.setAdapter(imageAdapter);
        sliderImage.setCurrentItem(0);
    }

    private void initRecyclerview() {
        LinearLayoutManager lm = new LinearLayoutManager(requireContext());

        rvAxi.setLayoutManager(lm);
        rvAxi.setHasFixedSize(true);
        rvAxi.setAdapter(listAdapter);
    }

    /* Init Listener */
    private void initListener() {
        searchContainer.setOnClickListener(view -> startActivity(new Intent(requireContext(), SearchActivity.class)));

        filterContainer.setOnClickListener(view -> showDialogFilter());

        nestedHome.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            noDataContainer.setVisibility(View.GONE);
            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                if (page < lastPage) {
                    pbHome.setVisibility(View.VISIBLE);
                    page += 1;
                    if (status != null && date != null)
                        getHomeDataWithFilter(page);
                    else
                        getHomeData(page);
                }
            }
        });

        swipeHome.setOnRefreshListener(() -> {
            isRefresh = true;
            isLastPage = false;
            page = START_PAGE;
            getHomeData(START_PAGE);
        });
    }

    private void getHomeData(int page) {
        mCompositeDisposable.add(
                jsonApi.getHome(page, branchId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessGetHomeData, this::onError)
        );
    }

    private void getHomeDataWithFilter(int page) {
        if (startDate != null && endDate != null)
            mCompositeDisposable.add(
                    jsonApi.getHomeWithFilter(status, date, startDate, endDate, page, branchId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::onSuccessGetHomeData, this::onError)
            );
        else
            mCompositeDisposable.add(
                    jsonApi.getHomeWithFilter(status, date, page, branchId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::onSuccessGetHomeData, this::onError)
            );
    }

    private void onError(Throwable t) {
        Tools.showLongToast(requireContext(), t.getMessage());
        pbHome.setVisibility(View.GONE);
    }

    private void onSuccessGetHomeData(AxiHome data) {
        /* getHomeData Subscriber */
        if (data != null && data.getData() != null && data.getData().size() > 0) {
            if (isRefresh) {
                axiList = new ArrayList<>();
                isRefresh = false;
            }

            axiPerBranch = data.getMeta().getTotal();

            axiList.addAll(data.getData());
            lastPage = data.getMeta().getLastPage();
            listAdapter.notifyDataSetChanged();

            String axiPerBranchStr = String.format(new Locale("in", "ID"),
                    "%d AXI di Cabang %s", axiPerBranch, branchName);
            searchResult.setText(axiPerBranchStr);
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

    private void showDialogFilter() {
        final Dialog dialog = new Dialog(requireContext(), android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(getLayoutInflater().inflate(R.layout.search_filter, null));

        ImageView icClose = dialog.findViewById(R.id.ic_close);
        RadioGroup rgDate = dialog.findViewById(R.id.rg_date);
        RadioGroup rgStatus = dialog.findViewById(R.id.rg_status);
        final RadioButton rbNew = dialog.findViewById(R.id.rb_new);
        RadioButton rbOld = dialog.findViewById(R.id.rb_old);
        final RadioButton rbChooseDate = dialog.findViewById(R.id.rb_choose_date);
        RadioButton rbActive = dialog.findViewById(R.id.rb_active);
        RadioButton rbInactive = dialog.findViewById(R.id.rb_inactive);
        Button btnActivate = dialog.findViewById(R.id.btn_activate);

        if (date == null)
            date = NEW_DATE;

        if (status == null)
            status = ACTIVE;

        /* Check Checked Radio Button */
        if (date == null) {
            rbNew.setChecked(true);
        } else {
            switch (date) {
                case NEW_DATE:
                    rbNew.setChecked(true);
                    break;
                case OLD_DATE:
                    rbOld.setChecked(true);
                    break;
                case CHOOSE_DATE:
                    // Show DatePicker if range is null
                    rbChooseDate.setChecked(true);
                    if (startDate == null || endDate == null)
                        showDatePicker(rbNew, rbChooseDate);
                    else
                        doSpanText(String.format(new Locale("in", "ID"), "%s - %s", startDate, endDate), rbChooseDate);
                    break;
            }
        }

        if (status == null) {
            rbActive.setChecked(true);
        } else {
            switch (status) {
                case ACTIVE:
                    rbActive.setChecked(true);
                    break;
                case INACTIVE:
                    rbInactive.setChecked(true);
                    break;
            }
        }

        icClose.setOnClickListener(view -> dialog.dismiss());

        rgDate.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rb_new:
                    date = NEW_DATE;
                    rbChooseDate.setText("Pilih Tanggal");
                    break;
                case R.id.rb_old:
                    date = OLD_DATE;
                    rbChooseDate.setText("Pilih Tanggal");
                    break;
                case R.id.rb_choose_date:
                    date = CHOOSE_DATE;
                    showDatePicker(rbNew, rbChooseDate);
                    break;
            }
        });

        rgStatus.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rb_active:
                    status = ACTIVE;
                    break;
                case R.id.rb_inactive:
                    status = INACTIVE;
                    break;
            }
        });

        btnActivate.setOnClickListener(v -> {
            axiList.clear();
            listAdapter.notifyDataSetChanged();
            dialog.dismiss();
            pbHome.setVisibility(View.VISIBLE);
            noDataContainer.setVisibility(View.GONE);
            page = START_PAGE;
            getHomeDataWithFilter(START_PAGE);
            //icCheck.setVisibility(View.VISIBLE);
        });

        dialog.show();
    }

    private void showDatePicker(final RadioButton rbNew, final RadioButton rbChooseDate) {
        Tools.showLongToast(requireContext(), "Long press on the start date and drag to the end date");
        DatePickerRange dp = new DatePickerRange();
        dp.setCallback(new DatePickerRange.Callback() {
            @Override
            public void onCancelled() {
                Tools.showToast(requireContext(), "Harap pilih rentang tanggal!");
                startDate = null;
                endDate = null;
                startDateStr = null;
                endDate = null;
                rbNew.setChecked(true);
            }

            @Override
            public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
                startDateStr = Tools.formatDate(selectedDate.getStartDate().getTime());
                endDateStr = Tools.formatDate(selectedDate.getEndDate().getTime());
                startDate = Tools.formatDateParams(selectedDate.getStartDate().getTime());
                endDate = Tools.formatDateParams(selectedDate.getEndDate().getTime());
                doSpanText(String.format(new Locale("in", "ID"), "%s - %s", startDateStr, endDateStr), rbChooseDate);
            }
        });

        SublimeOptions op = new SublimeOptions();
        op.setCanPickDateRange(true);
        op.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);

        Bundle bundle = new Bundle();
        bundle.putParcelable(SUB_BUNDLE, op);

        dp.setArguments(bundle);
        dp.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        dp.setCancelable(false);
        dp.show(getChildFragmentManager(), SUB_PICKER);
    }

    private void doSpanText(String message, RadioButton rbChooseDate) {
        SpannableStringBuilder b = new SpannableStringBuilder();
        String tempMessage = "Pilih Tanggal\n" + message;
        SpannableString s = new SpannableString(tempMessage);
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.colorPacificBlue)), 14, tempMessage.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        b.append(s);
        rbChooseDate.setText(b, RadioButton.BufferType.SPANNABLE);
    }

}
