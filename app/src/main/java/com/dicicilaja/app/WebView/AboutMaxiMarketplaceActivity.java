package com.dicicilaja.app.WebView;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.dicicilaja.app.Activity.RegisterActivity;
import com.dicicilaja.app.Activity.RegisterMaxi1Activity;
import com.dicicilaja.app.R;

public class AboutMaxiMarketplaceActivity extends AppCompatActivity {

    public Context mContext;
    Button daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_maxi_marketplace);
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
                if(url.startsWith("whatsapp://")){
                    handleTelLink(url);
                    return true;
                }

                return false;
            }


            // From api level 24
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                String url = request.getUrl().toString();
                if(url.startsWith("whatsapp://")){
                    handleTelLink(url);
                    return true;
                }

                return false;
            }
        });
        webView.loadUrl("https://dicicilaja.com/mitra-bisnis");



        daftar = findViewById(R.id.daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                intent.putExtra("register", "maxi");
                startActivity(intent);
            }
        });


    }

    protected void handleTelLink(String url){
        Uri uri=Uri.parse(url);
        String msg = uri.getQueryParameter("text");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
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
