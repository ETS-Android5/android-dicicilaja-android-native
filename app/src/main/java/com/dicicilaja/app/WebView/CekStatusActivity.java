package com.dicicilaja.app.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dicicilaja.app.R;

public class CekStatusActivity extends AppCompatActivity {

    public Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_status);
        mContext = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDisplayZoomControls(false);

        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient() {

            // For api level bellow 24
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                if(url.startsWith("tel:")){
                    handleTelLink(url);
                    return true;
                }

                return false;
            }


            // From api level 24
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                String url = request.getUrl().toString();
                if(url.startsWith("tel:")){
                    handleTelLink(url);
                    return true;
                }

                return false;
            }
        });
        webView.loadUrl("https://dicicilaja.com/status-pengajuan");
    }

    protected void handleTelLink(String url){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(url.replace("/", "")));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
