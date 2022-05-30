package com.example.uasmobile_psikisku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Tenagaahli extends AppCompatActivity {

    ImageView btnback;
    Button btnPsikiater, btnPsikolog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenagaahli);

        btnPsikiater = findViewById(R.id.btnPsikiater);
        btnback = findViewById(R.id.back);
        btnPsikolog = findViewById(R.id.btnPsikolog);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tenagaahli.this, Mainmenu.class);
                startActivity(intent);
                finish();
            }
        });
        btnPsikiater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tenagaahli.this, Psikiater.class);
                startActivity(intent);
                finish();
            }
        });
        btnPsikolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tenagaahli.this, Psikolog.class);
                startActivity(intent);
                finish();
            }
        });
    }
}