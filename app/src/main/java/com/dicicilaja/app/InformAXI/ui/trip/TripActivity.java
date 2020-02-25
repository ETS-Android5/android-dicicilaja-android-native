package com.dicicilaja.app.InformAXI.ui.trip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.dicicilaja.app.InformAXI.model.TripInfo;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.dicicilaja.app.InformAXI.utils.Constants.GOOGLE_DOCS_PDF;

public class TripActivity extends AppCompatActivity {

    /* UI Region */
    private Toolbar toolbar;
    private WebView wvTrip;
    private ProgressBar pbTrip;

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    private String pdfUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        initVariables();
        initToolbar();
        getInfoTrip();
    }

    private void initVariables() {
        toolbar = findViewById(R.id.toolbar);
        wvTrip = findViewById(R.id.wv_trip);
        pbTrip = findViewById(R.id.pb_trip);

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance();
        jsonApi = retrofit.create(NetworkInterface.class);
    }

    private void initToolbar() {
        toolbar.setTitle("Informasi Trip");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getInfoTrip() {
        /**
         * Hardcoded ID and Branch ID
         */
        mCompositeDisposable.add(
                jsonApi.getTripInfo(1, 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessGetTripInfo, this::onError)
        );
    }

    private void initWebView() {
        wvTrip.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                pbTrip.setVisibility(View.GONE);
                wvTrip.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                Tools.showLongToast(TripActivity.this, "Error load " + request.getUrl());
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);

                Tools.showLongToast(TripActivity.this, "Error : " + errorResponse.getReasonPhrase());
            }
        });
        wvTrip.getSettings().setSupportZoom(true);
        wvTrip.getSettings().setJavaScriptEnabled(true);
        if (pdfUrl != null)
            wvTrip.loadUrl(GOOGLE_DOCS_PDF + pdfUrl);
    }

    private void onSuccessGetTripInfo(TripInfo response) {
        if (response != null && response.getData() != null && response.getData().size() > 0) {
            List<TripInfo.Data> data = response.getData();

            if (data.get(0).getUrl() != null && !data.get(0).getUrl().isEmpty()) {
                pdfUrl = data.get(0).getUrl();
                initWebView();
            } else {
                Tools.showLongToast(this, "Url Undefined!");
            }
        }

        pbTrip.setVisibility(View.GONE);
    }

    private void onError(Throwable t) {
        Tools.showLongToast(this, t.getMessage());
        pbTrip.setVisibility(View.GONE);
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
