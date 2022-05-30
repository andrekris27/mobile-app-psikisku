package com.example.uasmobile_psikisku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Appoinment extends AppCompatActivity {
    String url_tambah_appoinment = "http://www.projectmobileappsm4.online/addAppointment.php";
    TextView textVia, textWaktu, textDoctor;
    Button btnConfirm;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_appoinment );

        textVia = findViewById(R.id.textVia);
        textWaktu = findViewById(R.id.textWaktu);
        textDoctor = findViewById(R.id.textDoctor);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnback = findViewById(R.id.btnback);

        Intent i = getIntent();

        String VIA = i.getStringExtra("via");
        textVia.setText(VIA);

        String WAKTU = i.getStringExtra("waktu");
        textWaktu.setText(WAKTU);

        String DOCTOR = i.getStringExtra("nama_psikolog");
        textDoctor.setText(DOCTOR);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hasilWaktu = textWaktu.getText().toString();
                String hasilVia = textVia.getText().toString();
                String hasilDokter = textDoctor.getText().toString();
                tambahAppoinment(hasilWaktu,hasilVia, hasilDokter);

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Appoinment.this, Booking.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void tambahAppoinment (String waktuApp, String viaApp, String doctorApp){
        RequestQueue queue = Volley.newRequestQueue(Appoinment.this);
        StringRequest request = new StringRequest(Request.Method.POST, url_tambah_appoinment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //JALAN KALAU TIDAK ADA ERROR
                    JSONObject jObj = new JSONObject(response);
                    int success = jObj.getInt("success");
                    if(success == 1){
                        Toast.makeText(Appoinment.this, "Silahkan Cek Email Anda", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Appoinment.this, listAppointment.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(Appoinment.this, "Appointment Gagal", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {

                    Toast.makeText(Appoinment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Appoinment.this, "Mohon cek koneksi anda ya !", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("waktu", waktuApp);
                params.put("via", viaApp);
                params.put("dokter", doctorApp);
                return params;
            }
        };
        queue.getCache().clear();
        queue.add(request);
    }
}
