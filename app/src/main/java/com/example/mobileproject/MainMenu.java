package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    // анимированная кнопка + старт 
    public void btnStart(View view) {
        Button button = (Button)findViewById(R.id.buttonStart);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Используйте интерполятор отказов с амплитудой 0,2 и частотой 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        button.startAnimation(myAnim);
    }

}