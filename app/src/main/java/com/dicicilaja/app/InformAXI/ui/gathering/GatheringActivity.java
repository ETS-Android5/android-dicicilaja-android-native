package com.dicicilaja.app.InformAXI.ui.gathering;

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

import com.dicicilaja.app.InformAXI.model.GatheringInfo;
import com.dicicilaja.app.InformAXI.network.NetworkClient;
import com.dicicilaja.app.InformAXI.network.NetworkInterface;
import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.dicicilaja.app.InformAXI.utils.Constants.GOOGLE_DOCS_PDF;

public class GatheringActivity extends AppCompatActivity {

    SessionManager session;
    String apiKey;

    /* UI Region */
    private Toolbar toolbar;
    private WebView wvGathering;
    private ProgressBar pbGathering;

    private CompositeDisposable mCompositeDisposable;
    private NetworkInterface jsonApi;

    private String pdfUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gathering);

        session = new SessionManager(getApplicationContext());
        apiKey = "Bearer " + session.getToken();

        initVariables();
        initToolbar();
        getInfoGathering();
    }

    private void initVariables() {
        toolbar = findViewById(R.id.toolbar);
        wvGathering = findViewById(R.id.wv_gathering);
        pbGathering = findViewById(R.id.pb_gathering);

        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = new NetworkClient().getRetrofitInstance();
        jsonApi = retrofit.create(NetworkInterface.class);
    }

    private void initToolbar() {
        toolbar.setTitle("Informasi Gathering");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getInfoGathering() {
        /**
         * Hardcoded ID and Branch ID
         */
        mCompositeDisposable.add(
                jsonApi.getGatheringInfo(apiKey, 2, 2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessGetGatheringInfo, this::onError)
        );
    }

    private void initWebView() {
        wvGathering.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                pbGathering.setVisibility(View.GONE);
                wvGathering.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                Tools.showLongToast(GatheringActivity.this, "Error load " + request.getUrl());
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);

                Tools.showLongToast(GatheringActivity.this, "Error : " + errorResponse.getReasonPhrase());
            }
        });
        wvGathering.getSettings().setSupportZoom(true);
        wvGathering.getSettings().setJavaScriptEnabled(true);
        wvGathering.loadUrl(GOOGLE_DOCS_PDF + pdfUrl);
    }

    private void onSuccessGetGatheringInfo(GatheringInfo response) {
        if (response != null && response.getData() != null && response.getData().size() > 0) {
            List<GatheringInfo.Data> data = response.getData();

            if (data.get(0).getUrl() != null && !data.get(0).getUrl().isEmpty()) {
                pdfUrl = data.get(0).getUrl();
                initWebView();
            } else {
                Tools.showLongToast(this, "Url Undefined!");
            }
        }
    }

    private void onError(Throwable t) {
        Tools.showLongToast(this, t.getMessage());
        pbGathering.setVisibility(View.GONE);
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
