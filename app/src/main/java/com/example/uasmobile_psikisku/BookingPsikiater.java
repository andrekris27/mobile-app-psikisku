package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BookingPsikiater extends AppCompatActivity {

    Button btnPilihWaktu, btnPilihVia, btnBook;
    ImageView btnback;
    TextView txtWaktu, txtVia, txtDoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_booking_psikiater );

        btnPilihWaktu = findViewById(R.id.btnPilihWaktu);
        btnPilihVia = findViewById(R.id.btnPilihVia);
        txtVia = findViewById(R.id.txtVia);
        txtWaktu = findViewById(R.id.txtWaktu);
        txtDoctor = findViewById(R.id.txtDoctor);

        btnback = findViewById(R.id.btnback);
        btnBook = findViewById(R.id.btnBook);



        final int[] checkedItem = {-1};
        final int[] checkedItem2 ={-1};


        Intent i = getIntent();
        String txtPsikiater = i.getStringExtra("nama_psikiater");
        txtDoctor.setText(txtPsikiater);

        btnPilihWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BookingPsikiater.this);
                alertDialog.setTitle("Pilih Waktu");
                final String[] listItems = new String[]{ "12.00 Pm - 01.00 Pm", "01.00 Pm - 02.00 Pm", "02.00 Pm - 03.00 Pm", "03.00 Pm - 04.00Pm"};

                alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItem[0] = which;
                        txtWaktu.setText("Waktu :" + listItems[which] );
                        dialog.dismiss();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog customeAlertDialog = alertDialog.create();
                customeAlertDialog.show();
            }
        });
        btnPilihVia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(BookingPsikiater.this);
                alertDialog2.setTitle("Via Online");
                final String[] listItems2 = new String[]{  "Zoom", "Sype", "Discord", "WA", "LINE"};

                alertDialog2.setSingleChoiceItems(listItems2, checkedItem2[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which2) {
                        checkedItem2[0] = which2;
                        txtVia.setText("Via :" + listItems2[which2] );
                        dialog.dismiss();
                    }
                });
                alertDialog2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog customeAlertDialog2 = alertDialog2.create();
                customeAlertDialog2.show();
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(BookingPsikiater.this, AppointmentPsikiater.class);
                i.putExtra("waktu_psikiater", txtWaktu.getText().toString());
                i.putExtra("via_psikiater", txtVia.getText().toString());
                i.putExtra("nama_psikiater", txtDoctor.getText().toString());
                startActivity(i);
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(BookingPsikiater.this, Psikiater.class);
                startActivity(i);
            }
        });
    }
}