package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
    }

    public void nextStart(View v)
    {
        Intent intent = new Intent(this, ConclusionFact.class);
        //intent.putExtra("one", 1);
        startActivity(intent);
    }

    public void nextStart2(View v)
    {
        Intent intent = new Intent(this, ConclusionFact.class);
        intent.putExtra("two", 2);
        startActivity(intent);
    }

    public void nextStart3(View v)
    {
        Intent intent = new Intent(this, ConclusionFact.class);
        intent.putExtra("three", 3);
        startActivity(intent);
    }
    public void nextStart4(View v)
    {
        Intent intent = new Intent(this, ConclusionFact.class);
        intent.putExtra("four", 4);
        startActivity(intent);
    }

}