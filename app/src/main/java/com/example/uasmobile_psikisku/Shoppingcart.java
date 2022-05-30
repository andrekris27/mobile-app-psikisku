package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Shoppingcart extends AppCompatActivity {
    final String url_get_obat = "http://www.projectmobileappsm4.online/getObat.php";
    private ListView lv;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        lv = (ListView) findViewById(R.id.listObat);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shoppingcart.this, Mainmenu.class);
                startActivity(intent);
                finish();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(Shoppingcart.this);
        StringRequest request = new StringRequest(Request.Method.GET, url_get_obat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<HashMap<String, String>> listobat = new ArrayList<HashMap<String, String>>();
                        try {
                            JSONObject jObj = new JSONObject(response);
                            JSONArray result = jObj.getJSONArray("result");

                            for (int i = 0; i < result.length(); i++) {
                                JSONObject obat = result.getJSONObject(i);
                                String nama_obat = obat.getString("nama_obat");
                                String harga_obat = obat.getString("harga_obat");

                                HashMap<String, String> map = new HashMap<>();
                                map.put("nama_obat", nama_obat);
                                map.put("harga_obat", harga_obat);
                                listobat.add(map);

                            }
                        } catch (JSONException e) {
                            Toast.makeText(Shoppingcart.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        ListAdapter adapter = new SimpleAdapter(Shoppingcart.this, listobat, R.layout.listview_obat,
                                new String[]{"nama_obat", "harga_obat"},
                                new int[]{R.id.txtNamaObat, R.id.txtHargaObat});
                        lv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Shoppingcart.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.getCache().clear();
        queue.add(request);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String get_obat = ((TextView) view.findViewById(R.id.txtNamaObat)).getText().toString();
                String get_harga = ((TextView) view.findViewById(R.id.txtHargaObat)).getText().toString();

                Intent i = new Intent(Shoppingcart.this, ShoppingCart2.class);
                i.putExtra("nama_obat", get_obat);
                i.putExtra("harga_obat", get_harga);
                startActivity(i);
            }
        });
    }
}
