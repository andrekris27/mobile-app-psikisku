package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.uasmobile_psikisku.api.ApiClient;
import com.example.uasmobile_psikisku.api.ApiInterface;
import com.example.uasmobile_psikisku.model.update.Update;
import com.example.uasmobile_psikisku.model.update.UpdateData;
;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateUser extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etemail;
    TextView etID;
    Button btnSimpan;
    String Username,  email, id;
    ApiInterface apiInterface;
    SessionManager sessionManager;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        etID = findViewById(R.id.etMainID2);
        etUsername = findViewById(R.id.etMainUsername);
        etemail = findViewById(R.id.etMainEmail);

        btnSimpan = findViewById(R.id.btnSave);
        btnSimpan.setOnClickListener(this);

        Intent i = getIntent();
        String ID = i.getStringExtra("id");
        etID.setText(ID);

        btnback = findViewById(R.id.btnback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateUser.this, Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()){
            case R.id.btnSave:
                id = etID.getText().toString();
                Username = etUsername.getText().toString();
                email = etemail.getText().toString();
                update (id,Username, email);
                break;
        }
    }

    @SuppressLint("ResourceType")
    private void update (String id,String username, String password) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Update> updateCall = apiInterface.updateResponse(id,username,password);
        updateCall.enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    // Ini untuk menyimpan sesi
                    sessionManager = new SessionManager(updateUser.this);
                    UpdateData updateData = response.body().getUpdateData();
                    sessionManager.createUpdateSession(updateData);

                    //Ini untuk pindah
                    Toast.makeText(updateUser.this, response.body().getUpdateData().getEmail(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(updateUser.this, Profile.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(updateUser.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Toast.makeText(updateUser.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
