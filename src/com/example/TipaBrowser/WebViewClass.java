package com.example.TipaBrowser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by Practicant1.ORPO_KRG on 16.06.2014.
 */
public class WebViewClass extends Activity {

    WebView webView;
    ViewGroup.LayoutParams params;
    boolean isMinimized;
    Vibrator vibe;
    MyOnTouchListener touchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        webView = (WebView) findViewById(R.id.webView);

        vibe = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE) ;

        params = webView.getLayoutParams();
        isMinimized = false;

        webView.setWebViewClient(new MyWebViewClient());
        //loads the WebView completely zoomed out
        webView.getSettings().setLoadWithOverviewMode(true);

        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());

        touchListener = new MyOnTouchListener(this);

        webView.setOnTouchListener(touchListener);
    }

    private class MyOnTouchListener extends OnSwipeTouchListener {
        public MyOnTouchListener(Context ctx) {
            super(ctx);
        }

        public void onSwipeTop() {
            Toast.makeText(WebViewClass.this, "top", Toast.LENGTH_SHORT).show();
        }
        public void onSwipeRight() {
            Toast.makeText(WebViewClass.this, "right", Toast.LENGTH_SHORT).show();
        }
        public void onSwipeLeft() {
            Toast.makeText(WebViewClass.this, "left", Toast.LENGTH_SHORT).show();
        }
        public void onSwipeBottom() {
            Toast.makeText(WebViewClass.this, "bottom", Toast.LENGTH_SHORT).show();
        }
        public void doOnLongPress(){
            Toast.makeText(WebViewClass.this, "longPress", Toast.LENGTH_SHORT).show();
            if(!isMinimized) {
                minimize();
            }
        }
    }


    private void minimize(){
        vibe.vibrate(50);
        params.height = (int) (0.8 * webView.getHeight());
        params.width = (int) (0.8 * webView.getWidth());
        webView.setLayoutParams(params);
        webView.setEnabled(false);
        isMinimized = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient{
        @Override

        // show the web page in webview but not in web browser
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }
    }

    public void toMainMenu(View v){
        onPause();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.addFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT));

    }

}
