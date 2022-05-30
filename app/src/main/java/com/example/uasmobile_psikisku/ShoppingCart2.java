package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCart2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    private String[] pilihanQTY = {"- Pilih QTY -", "             1", "             2", "             3", "             4", "             5"};
    Button btnKonfirmasi;
    ImageView back;
    TextView obatNama, obatHarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart2);
        obatHarga = findViewById(R.id.obatHarga);
        obatNama = findViewById(R.id.obatNama);
        btnKonfirmasi = findViewById(R.id.btnKonfirmasi);
        back = findViewById(R.id.back);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, pilihanQTY);
        spinner.setAdapter(adapter);
        //Buat objek untuk Class MyDBHandler
        final MyDBHandler dbHandler = new MyDBHandler(this);
        //Membuka koneksi database
        try {
            dbHandler.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCart2.this, Shoppingcart.class);
                startActivity(intent);
                finish();
            }
        });
        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Obat obat = new Obat();
                String nama = obatNama.getText().toString();
                String QTY = spinner.getSelectedItem().toString();
                long harga = Long.parseLong(obatHarga.getText().toString());
                dbHandler.createBarang(nama,QTY,harga);
                Toast.makeText(ShoppingCart2.this, "Obat berhasil ditambahkan",Toast.LENGTH_LONG).show();
                obatNama.setText("");
                spinner.setSelection(0);
                obatHarga.setText("");
                obatNama.requestFocus();
                Intent i = new Intent(ShoppingCart2.this, Checkout.class);
                startActivity(i);
                ShoppingCart2.this.finish();
                dbHandler.close();
            }
        });
        Intent i = getIntent();

        String NAMA = i.getStringExtra("nama_obat");
        obatNama.setText(NAMA);

        String HARGA = i.getStringExtra("harga_obat");
        obatHarga.setText(HARGA);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}