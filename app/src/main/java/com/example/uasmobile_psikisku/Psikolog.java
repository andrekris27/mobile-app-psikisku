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

public class Psikolog extends AppCompatActivity {

    final String url_get_psikolog = "http://www.projectmobileappsm4.online/getPsikolog.php";
    ImageView btnback;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psikolog);

        lv = findViewById(R.id.listPsikiater);

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Psikolog.this, Tenagaahli.class);
                startActivity(intent);
                finish();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(Psikolog.this);
        StringRequest request = new StringRequest(Request.Method.GET, url_get_psikolog,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<HashMap<String, String>> listPsikolog = new ArrayList<HashMap<String, String>>();
                        try {
                            JSONObject jObj = new JSONObject(response);

                            JSONArray result = jObj.getJSONArray("result");

                            for (int i = 0; i < result.length(); i++) {
                                JSONObject psi = result.getJSONObject(i);
                                String nama_psikolog = psi.getString("nama_psikolog");
                                String noTelp_psikolog = psi.getString("noTelp_psikolog");
                                String email_psikolog = psi.getString("email_psikolog");

                                HashMap<String, String> map = new HashMap<>();
                                map.put("nama_psikolog", nama_psikolog);
                                map.put("noTelp_psikolog", noTelp_psikolog);
                                map.put("email_psikolog", email_psikolog);
                                listPsikolog.add(map);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(Psikolog.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        ListAdapter adapter = new SimpleAdapter(Psikolog.this, listPsikolog, R.layout.resourcepsikolog,
                                new String[]{"nama_psikolog", "noTelp_psikolog", "email_psikolog"},
                                new int[]{R.id.psiNama2, R.id.psiNoTelp2, R.id.dokterAppointment2});
                        lv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Psikolog.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.getCache().clear();
        queue.add(request);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String get_nama = ((TextView) view.findViewById(R.id.psiNama2)).getText().toString();

                Intent i = new Intent(Psikolog.this, Booking.class);
                i.putExtra("nama_psikolog", get_nama);
                startActivity(i);
            }
        });

    }
}