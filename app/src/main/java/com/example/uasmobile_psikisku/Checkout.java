package com.example.uasmobile_psikisku;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Checkout extends ListActivity implements AdapterView.OnItemLongClickListener {

    MyDBHandler dbHandler;
    ArrayList<Obat> values;
    ListView list;
    Context context = this;
    Button btnHapus, btnpesanLagi, btnpesan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        btnpesan = findViewById ( R.id.btnpesan );
        btnpesan.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder pesan = new AlertDialog.Builder(context);
                pesan.setTitle("Anda sudah yakin ingin membeli Obat ini?");
                pesan.setMessage("Pesanan bersifat Cash On Delivery yang akan langsung dikirim sesuai alamat yang di input");
                pesan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Checkout.this, Mainmenu.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Checkout.this,"Silahkan cek Email anda untuk konfirmasi, Terima Kasih..",Toast.LENGTH_LONG).show();
                    }
                });
                pesan.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                pesan.show();
            }
        } );

        btnpesanLagi = findViewById ( R.id.btnpesanLagi );
        btnpesanLagi.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Checkout.this, Shoppingcart.class);
                startActivity(intent);
                finish();
            }
        } );

        dbHandler = new  MyDBHandler(this);

        try{
            dbHandler.open();
        }catch (SQLException e){
            e.printStackTrace();
        }
        values = dbHandler.getAllBarang();

        ArrayAdapter<Obat> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        list = findViewById(android.R.id.list);
        list.setOnItemLongClickListener(this);


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.show();
        btnHapus = dialog.findViewById(R.id.btnHapus);

        Obat obat = (Obat) getListAdapter().getItem(i);
        long id = obat.getID();
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder konfirm = new AlertDialog.Builder(context);
                konfirm.setTitle("Hapus Obat");
                konfirm.setMessage("Anda yakin akan menghapus Obat ini?");
                konfirm.setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteBarang(id);

                        finish();
                        startActivity(getIntent());

                        Toast.makeText(Checkout.this,"Obat berhasil dihapus",Toast.LENGTH_LONG).show();
                    }
                });
                konfirm.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                konfirm.show();
                dialog.dismiss();
            }
        });
        return  true;
    }
}