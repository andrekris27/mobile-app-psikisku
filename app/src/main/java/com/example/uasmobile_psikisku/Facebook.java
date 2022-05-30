package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class Facebook extends AppCompatActivity {
    WebView webViewFacebook;
    ImageView img_f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        img_f = findViewById ( R.id.back_i );
        img_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Facebook.this, Share.class);
                startActivity(intent);
                finish();
            }
        });

        webViewFacebook = (WebView) findViewById(R.id.webViewFacebook);
        webViewFacebook.setWebViewClient(new WebViewClient());
        webViewFacebook.getSettings().setJavaScriptEnabled(true);
        webViewFacebook.loadUrl("https://www.facebook.com/");
    }
}