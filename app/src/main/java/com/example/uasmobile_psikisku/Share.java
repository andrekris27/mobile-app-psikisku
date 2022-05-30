package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Share extends AppCompatActivity {

    CardView facebook, ig;
    ImageView back_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        facebook = findViewById(R.id.btnFacebook);
        ig = findViewById(R.id.btnIG);

        back_s = findViewById ( R.id.back_s );
        back_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Share.this, Mainmenu.class);
                startActivity(intent);
                finish();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Share.this, Facebook.class);
                startActivity(intent);
                finish();
            }
        });
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Share.this, Instagram.class);
                startActivity(intent);
                finish();
            }
        });

    }
}