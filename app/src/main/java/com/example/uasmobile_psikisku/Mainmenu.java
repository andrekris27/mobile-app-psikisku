package com.example.uasmobile_psikisku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Mainmenu extends AppCompatActivity {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    CardView btnTenagaAhli, btnMedicine, btnContactUs;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);


        /*---------------------Hooks------------------------*/
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        textView=findViewById(R.id.textView);
        toolbar=findViewById(R.id.toolbar);
        btnTenagaAhli = findViewById(R.id.btnTenagaAhli);
        btnMedicine = findViewById(R.id.btnMedicine);
        btnContactUs = findViewById(R.id.btnContactUs);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_logout)
                {
                    Intent intent = new Intent(Mainmenu.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.nav_profile) {
                Intent intent = new Intent(Mainmenu.this, Profile.class);
                startActivity(intent);
                finish();
                }else if (id == R.id.nav_share){
                    Intent intent = new Intent(Mainmenu.this, Share.class);
                    startActivity(intent);
                    finish();
                }else if (id == R.id.nav_appPsikolog) {
                    Intent intent = new Intent(Mainmenu.this, listAppointment.class);
                    startActivity(intent);
                    finish();
                }else if (id == R.id.nav_appPsikiater) {
                    Intent intent = new Intent(Mainmenu.this, listAppointmentPsikiater.class);
                    startActivity(intent);
                    finish();
                }else if (id == R.id.checkout) {
                    Intent intent = new Intent(Mainmenu.this, Checkout.class);
                    startActivity(intent);
                    finish();
                }return false;
            }
        });
        btnTenagaAhli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainmenu.this, Tenagaahli.class);
                startActivity(intent);
                finish();
            }
        });
        btnMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainmenu.this, Shoppingcart.class);
                startActivity(intent);
                finish();
            }
        });
        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainmenu.this, ChatBot.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}