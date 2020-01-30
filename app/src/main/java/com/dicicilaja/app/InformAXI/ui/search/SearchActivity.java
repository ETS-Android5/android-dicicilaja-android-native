package com.dicicilaja.app.InformAXI.ui.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.dicicilaja.app.InformAXI.adapter.AxiHomeAdapter;
import com.dicicilaja.app.InformAXI.model.AxiHome;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
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

public class SearchActivity extends AppCompatActivity {

    /* UI Region */
    private Toolbar toolbarSearch;
    private RecyclerView rvSearch;
    private ImageView icCheck;
    private ImageView icFilter;
    private RelativeLayout noDataContainer;
    private EditText etSearch;
    private NestedScrollView nestedSearch;
    private ProgressBar pbSearch;

    private List<AxiHome.DataBean> axiList;

    private AxiHomeAdapter searchAdapter;

    private boolean isFilterApplied = false;
    private String date = null;
    private String status = null;
    private String startDate = null;
    private String endDate = null;

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    private int START_PAGE = 1;
    private int page = START_PAGE;
    private int lastPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informaxi_search);

        initVariables();
        initToolbar();
        initListener();
        initRecyclerView();
        checkFilterApplied();
    }

    /* Init Variables */
    private void initVariables() {
        toolbarSearch = findViewById(R.id.toolbar);
        rvSearch = findViewById(R.id.rv_search);
        icCheck = findViewById(R.id.ic_check);
        icFilter = findViewById(R.id.ic_filter);
        noDataContainer = findViewById(R.id.no_data_container);
        etSearch = findViewById(R.id.et_search);
        nestedSearch = findViewById(R.id.nested_search);
        pbSearch = findViewById(R.id.pb_search);

        axiList = new ArrayList<>();

        searchAdapter = new AxiHomeAdapter(axiList, this);

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance(this);
        jsonApi = retrofit.create(NetworkInterface.class);
    }

    private void initToolbar() {
        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initListener() {
        icFilter.setOnClickListener(view -> {
            showDialogFilter();
            checkFilterApplied();
        });

        etSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                axiList.clear();
                rvSearch.setVisibility(View.GONE);
                search(etSearch.getText().toString(), START_PAGE);
                noDataContainer.setVisibility(View.GONE);
                pbSearch.setVisibility(View.VISIBLE);
            }
            return false;
        });

        nestedSearch.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                if (page < lastPage) {
                    pbSearch.setVisibility(View.VISIBLE);
                    page += 1;
                    search(etSearch.getText().toString(), page);
                }
            }
        });
    }

    private void search(String keyword, int page) {
        mCompositeDisposable.add(
                jsonApi.doSearch(page, keyword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessSearch, this::onError)
        );
    }

    private void searchWithFilter(String keyword, int page) {
        if (startDate != null && endDate != null)
            mCompositeDisposable.add(
                    jsonApi.searchWithFilter(status, date, startDate, endDate, page, keyword)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::onSuccessSearch, this::onError)
            );
        else
            mCompositeDisposable.add(
                    jsonApi.searchWithFilter(status, date, page, keyword)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::onSuccessSearch, this::onError)
            );
    }

    private void onSuccessSearch(AxiHome data) {
        if (data.getData() != null && data.getData().size() > 0) {
            axiList.addAll(data.getData());
            searchAdapter.notifyDataSetChanged();
        }
        lastPage = data.getMeta().getLastPage();
        pbSearch.setVisibility(View.GONE);
        checkData();
    }

    private void onError(Throwable t) {
        Tools.showLongToast(this, t.getMessage());
        pbSearch.setVisibility(View.GONE);
        checkData();
    }

    private void initRecyclerView() {
        //populateData();
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rvSearch.setLayoutManager(lm);
        rvSearch.setHasFixedSize(true);
        rvSearch.setAdapter(searchAdapter);

        checkData();
    }

    private void checkData() {
        if (axiList.size() == 0) {
            rvSearch.setVisibility(View.GONE);
            noDataContainer.setVisibility(View.VISIBLE);
        } else {
            rvSearch.setVisibility(View.VISIBLE);
            noDataContainer.setVisibility(View.GONE);
        }
    }

    /**
     * private void populateData() {
     * for (int i = 0; i < 10; i++) {
     * AxiHome obj = new AxiHome();
     * obj.setName("Risya " + i);
     * obj.setNumber("0000000" + i);
     * obj.setDate("3 Juli 2019");
     * <p>
     * axiList.add(obj);
     * }
     * }
     */

    private void showDialogFilter() {
        isFilterApplied = true;
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light);
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

        rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_active:
                        status = ACTIVE;
                        break;
                    case R.id.rb_inactive:
                        status = INACTIVE;
                        break;
                }
            }
        });

        btnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                axiList.clear();
                searchAdapter.notifyDataSetChanged();
                dialog.dismiss();
                pbSearch.setVisibility(View.VISIBLE);
                page = START_PAGE;
                searchWithFilter(etSearch.getText().toString(), page);
                icCheck.setVisibility(View.VISIBLE);
            }
        });

        dialog.show();
    }

    private void showDatePicker(final RadioButton rbNew, final RadioButton rbChooseDate) {
        Tools.showLongToast(this, "Long press on the start date and drag to the end date");
        DatePickerRange dp = new DatePickerRange();
        dp.setCallback(new DatePickerRange.Callback() {
            @Override
            public void onCancelled() {
                Tools.showToast(SearchActivity.this, "Harap pilih rentang tanggal!");
                startDate = null;
                endDate = null;
                rbNew.setChecked(true);
            }

            @Override
            public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
                startDate = Tools.formatDate(selectedDate.getStartDate().getTime());
                endDate = Tools.formatDate(selectedDate.getEndDate().getTime());
                doSpanText(String.format(new Locale("in", "ID"), "%s - %s", startDate, endDate), rbChooseDate);
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
        dp.show(getSupportFragmentManager(), SUB_PICKER);
    }

    private void doSpanText(String message, RadioButton rbChooseDate) {
        SpannableStringBuilder b = new SpannableStringBuilder();
        String tempMessage = "Pilih Tanggal\n" + message;
        SpannableString s = new SpannableString(tempMessage);
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPacificBlue)), 14, tempMessage.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        b.append(s);
        rbChooseDate.setText(b, RadioButton.BufferType.SPANNABLE);
    }

    private void checkFilterApplied() {
        if (isFilterApplied)
            icCheck.setVisibility(View.VISIBLE);
        else
            icCheck.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
