package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class Instagram extends AppCompatActivity {
    WebView webViewInstagram;
    ImageView img_i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        img_i = findViewById ( R.id.back_i );
        img_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Instagram.this, Share.class);
                startActivity(intent);
                finish();
            }
        });

        webViewInstagram = (WebView) findViewById(R.id.webViewInstagram);
        webViewInstagram.setWebViewClient(new WebViewClient());
        webViewInstagram.getSettings().setJavaScriptEnabled(true);
        webViewInstagram.loadUrl("https://www.Instagram.com/");
    }
}