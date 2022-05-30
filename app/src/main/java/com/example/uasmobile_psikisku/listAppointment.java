package com.example.uasmobile_psikisku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class listAppointment extends AppCompatActivity {

    final String url_get_appointment = "http://www.projectmobileappsm4.online/getAppointment.php";
    final String url_delete_mahasiswa = "http://www.projectmobileappsm4.online/deleteAppointment.php";
    private ListView lv;
    Context context = this;
    public String ID;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_appointment);

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listAppointment.this, Mainmenu.class);
                startActivity(intent);
                finish();
            }
        });

        lv = findViewById(R.id.listAppointment2);

        RequestQueue queue = Volley.newRequestQueue(listAppointment.this);
        StringRequest request = new StringRequest(Request.Method.GET, url_get_appointment,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<HashMap<String, String>> listAppointment = new ArrayList<HashMap<String, String>>();
                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONArray result = jObj.getJSONArray("result");

                            for (int i = 0; i < result.length(); i++) {
                                JSONObject psi = result.getJSONObject(i);
                                ID = psi.getString("id_appointment");
                                String waktu = psi.getString("waktu");
                                String via = psi.getString("via");
                                String dokter = psi.getString("dokter");

                                HashMap<String, String> map = new HashMap<>();
                                map.put("id_appointment", ID);
                                map.put("waktu", waktu);
                                map.put("via", via);
                                map.put("dokter", dokter);
                                listAppointment.add(map);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(com.example.uasmobile_psikisku.listAppointment.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        ListAdapter adapter = new SimpleAdapter(com.example.uasmobile_psikisku.listAppointment.this, listAppointment, R.layout.getappointmentpsikolog,
                                new String[]{"id_appoinment","waktu", "via", "dokter"},
                                new int[]{R.id.idAppointment2, R.id.waktuAppointment2, R.id.viaAppointment2, R.id.dokterAppointment2});
                        lv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(listAppointment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.getCache().clear();
        queue.add(request);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                AlertDialog.Builder konfirm = new AlertDialog.Builder(context);
                konfirm.setTitle("Pembatalan Appointment");
                konfirm.setMessage("Anda yakin akan membatalkan appointment ini?");
                konfirm.setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteApp(ID);

                        finish();
                        startActivity(getIntent());

                        Toast.makeText(listAppointment.this,"Appointment Berhasil Dibatalkan",Toast.LENGTH_LONG).show();
                    }
                });
                konfirm.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                konfirm.show();
            }
        });
    }

    private void deleteApp(String ID) {
        RequestQueue queue = Volley.newRequestQueue(listAppointment.this);
        StringRequest request = new StringRequest(Request.Method.POST, url_delete_mahasiswa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //JALAN KALAU TIDAK ADA ERROR
                    JSONObject jObj = new JSONObject(response);
                    int success = jObj.getInt("success");
                    if (success == 1) {
                        Toast.makeText(listAppointment.this, "Appointment Dibatalkan", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(listAppointment.this, "Appointment tidak bisa dibatalkan", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {

                    Toast.makeText(listAppointment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(listAppointment.this, "Mohon cek koneksi anda ya !", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_appointment", ID);
                return params;
            }
        };
        queue.getCache().clear();
        queue.add(request);
    }
}