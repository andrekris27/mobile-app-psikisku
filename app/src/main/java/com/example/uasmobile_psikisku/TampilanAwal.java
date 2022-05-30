package com.example.uasmobile_psikisku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class TampilanAwal extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView title, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ( ).setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_tampilan_awal );

        topAnim = AnimationUtils.loadAnimation ( this , R.anim.top_animation );
        bottomAnim = AnimationUtils.loadAnimation ( this , R.anim.bottom_animation );

        image = findViewById ( R.id.imageawal );
        title = findViewById ( R.id.txttitle );
        slogan = findViewById ( R.id.txtslogan );

        image.setAnimation ( topAnim );
        title.setAnimation ( topAnim );
        slogan.setAnimation ( bottomAnim );

        new Handler ( ).postDelayed ( new Runnable ( ) {
            @Override
            public void run() {
                Intent intent = new Intent ( TampilanAwal.this , LoginActivity.class );
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String> ( image , "image_transition" );
                pairs[1] = new Pair<View, String> ( title , "title_transition" );

                if ( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ) {
                    ActivityOptions option = ActivityOptions.makeSceneTransitionAnimation ( TampilanAwal.this , pairs );
                    startActivity ( intent , option.toBundle ( ) );
                }
            }
        } , SPLASH_SCREEN );

    }
}
