package com.orientexpress.setup;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.activity.OnBackPressedCallback;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // System Back button / gesture: go back one screen inside the randomizer.
        // On the first screen, send the app to the background (your setup is kept).
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                WebView webView = getBridge() != null ? getBridge().getWebView() : null;
                if (webView == null) {
                    moveTaskToBack(true);
                    return;
                }
                webView.evaluateJavascript(
                    "!!(window.__oesBack && window.__oesBack())",
                    result -> {
                        if (!"true".equals(result)) {
                            moveTaskToBack(true);
                        }
                    }
                );
            }
        });
    }
}
