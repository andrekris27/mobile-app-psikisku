package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity  {
    TextView etID;
    EditText etUsername, etemail ;
    SessionManager sessionManager;
    ImageView btnback;
    Button btnupdate;
    String username, email, ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etUsername = findViewById(R.id.etMainUsername);
        etemail = findViewById(R.id.etMainEmail);
        etID = findViewById(R.id.etMainID);

        btnupdate = findViewById(R.id.btnUpdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(Profile.this, updateUser.class);
            intent.putExtra("id",etID.getText().toString());
            startActivity(intent);
            finish();
            }
        });

        btnback = findViewById(R.id.back_i );
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Mainmenu.class);
                startActivity(intent);
                finish();
            }
        });
        sessionManager = new SessionManager (Profile.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }



        ID = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        email = sessionManager.getUserDetail().get(SessionManager.Email);

        etID.setText(ID);
        etUsername.setText(username);
        etemail.setText(email);

    }


    private void moveToLogin() {
        Intent intent = new Intent(Profile.this, Mainmenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}