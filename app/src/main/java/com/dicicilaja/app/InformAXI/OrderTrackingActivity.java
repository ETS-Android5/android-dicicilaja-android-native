package com.dicicilaja.app.InformAXI;

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

import com.dicicilaja.app.InformAXI.utils.Tools;
import com.dicicilaja.app.R;

public class OrderTrackingActivity extends AppCompatActivity {

    /* UI Region */
    private Toolbar toolbar;
    private WebView wvTracking;
    private ProgressBar pbTracking;

    private String pdfUrl = "https://ad1gate.adira.co.id/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);

        initVariables();
        initToolbar();
        initWebView();
    }

    private void initVariables() {
        toolbar = findViewById(R.id.toolbar);
        wvTracking = findViewById(R.id.wv_tracking);
        pbTracking = findViewById(R.id.pb_tracking);
    }

    private void initToolbar() {
        toolbar.setTitle("Order Tracking");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initWebView() {
        wvTracking.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                pbTracking.setVisibility(View.GONE);
                wvTracking.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                Tools.showLongToast(OrderTrackingActivity.this, "Error load " + request.getUrl());
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);

                //Tools.showLongToast(OrderTrackingActivity.this, "Error : " + errorResponse.getReasonPhrase());
            }
        });
        wvTracking.getSettings().setSupportZoom(true);
        wvTracking.getSettings().setJavaScriptEnabled(true);
        if (pdfUrl != null)
            wvTracking.loadUrl(pdfUrl);
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
