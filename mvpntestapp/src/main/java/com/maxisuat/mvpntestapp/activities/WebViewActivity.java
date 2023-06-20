package com.maxisuat.mvpntestapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.citrix.mvpn.api.MicroVPNSDK;
import com.citrix.mvpn.exception.MvpnException;
import com.maxisuat.mvpntestapp.R;
import com.maxisuat.mvpntestapp.webview.CustomWebViewClient;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = "Mvpn-WebViewActivity";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_webview);

        webView = findViewById(R.id.webview);
        WebViewClient webViewClient = new CustomWebViewClient();

        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setSaveFormData(false);
        webView.setWebViewClient(webViewClient);

        try {
            webView = MicroVPNSDK.enableWebViewObjectForNetworkTunnel(this, webView, webViewClient);
        } catch (MvpnException e) {
            Log.e(TAG, e.getMessage());
        }

        webView.loadUrl(getIntent().getStringExtra(MainActivity.URL_KEY));
    }
}
