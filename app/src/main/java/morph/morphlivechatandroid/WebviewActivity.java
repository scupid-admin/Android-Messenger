package morph.morphlivechatandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by aapa on 14/02/17.
 */
public class WebviewActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.webview);
        WebView webView = (WebView) findViewById(R.id.webview);

//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.clearCache(true);
//        webView.getSettings().setDomStorageEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setJavaScriptEnabled(true);

        final Activity activity = this;
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 100);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("MyApplication", consoleMessage.message() + " -- From line "
                        + consoleMessage.lineNumber() + " of "
                        + consoleMessage.sourceId());
                return super.onConsoleMessage(consoleMessage);
            }

        });
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl("https://app.morph.ai/livechat/demo/?id=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImlkIjoiNTgzNDE3YTRmMzgzZjg1MzkzOTliNjkxIn0.eyJzY29wZSI6ImFwaSJ9.JorE-rBX2cTCf2riYycAKIwz-IG5_ZZ5uqIgHkr6yfE&mode=app");
    }

}
