package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    //задержка для анимаци кнопки
    private final int SPLASH_DISPLAY_LENGHT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    // анимированная кнопка + старт 
    public void btnStart(View view) {
        try {
            Button button = (Button) findViewById(R.id.buttonStart);
            final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
            // Используйте интерполятор отказов с амплитудой 0,2 и частотой 20
            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
            myAnim.setInterpolator(interpolator);
            button.startAnimation(myAnim);
            //задержка для анимаци кнопки  // переход
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(MainMenu.this, ConclusionFact.class);
                    MainMenu.this.startActivity(mainIntent);
                    MainMenu.this.finish();
                }
            }, SPLASH_DISPLAY_LENGHT);

        }
        catch (Exception ex)
        {
            Toast.makeText(MainMenu.this, "Что-то пошло не так", Toast.LENGTH_LONG).show();
        }



    }

}