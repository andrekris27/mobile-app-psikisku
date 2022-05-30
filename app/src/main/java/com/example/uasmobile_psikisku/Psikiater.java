package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

public class Psikiater extends AppCompatActivity {

    final String url_get_psikiater = "http://www.projectmobileappsm4.online/getPsikiater.php";
    ImageView btnback;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psikiater);

        lv = findViewById(R.id.listPsikiater);

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Psikiater.this, Tenagaahli.class);
                startActivity(intent);
                finish();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(Psikiater.this);
        StringRequest request = new StringRequest(Request.Method.GET, url_get_psikiater,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<HashMap<String, String>> listPsikiater = new ArrayList<HashMap<String, String>>();
                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONArray result = jObj.getJSONArray("result");

                            for (int i = 0; i < result.length(); i++) {
                                JSONObject psi = result.getJSONObject(i);
                                String nama_psikiater = psi.getString("nama_psikiater");
                                String noTelp_psikiater = psi.getString("noTelp_psikiater");
                                String email_psikiater = psi.getString("email_psikiater");

                                HashMap<String, String> map = new HashMap<>();
                                map.put("nama_psikiater", nama_psikiater);
                                map.put("noTelp_psikiater", noTelp_psikiater);
                                map.put("email_psikiater", email_psikiater);
                                listPsikiater.add(map);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(Psikiater.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        ListAdapter adapter = new SimpleAdapter(Psikiater.this, listPsikiater, R.layout.resourcepsikiater,
                                new String[]{"nama_psikiater", "noTelp_psikiater", "email_psikiater"},
                                new int[]{R.id.psiNama2, R.id.psiNoTelp2, R.id.dokterAppointment2});
                        lv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Psikiater.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.getCache().clear();
        queue.add(request);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String get_nama = ((TextView) view.findViewById(R.id.psiNama2)).getText().toString();

                Intent i = new Intent(Psikiater.this, BookingPsikiater.class);
                i.putExtra("nama_psikiater", get_nama);
                startActivity(i);
            }
        });

    }
}