package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class ConclusionFact extends AppCompatActivity {
    Connection connection;
    String ConnectionResult = "";
    ImageView Picture;
    View v;
    String img = null;
    TextView textFact;
    List<Mask> data;
    AdapterMask pAdapter;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion_fact);


//        textFact = findViewById(R.id.textFact);
//        Picture = findViewById(R.id.imageView);

        //v = findViewById(com.google.android.material.R.id.ghost_view);

        GetTableSQL();
    }
    int min = 0;
     int max = 7;
    Random random = new Random();
     int  r= random.nextInt(7) ;

    public void GetTableSQL() {
        textFact = findViewById(R.id.textFact);
        Picture = findViewById(R.id.imageView);
        data = new ArrayList<Mask>();
        pAdapter = new AdapterMask(ConclusionFact.this, data);
        try {
           ConnectionHelpers connectionHelpers = new ConnectionHelpers();
           connection = connectionHelpers.connectionClass();
           if (connection != null)
           {
               String query = "Select * From Facts WHERE Kod_fact =" + r;
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery(query);

               while (resultSet.next()) {
                   textFact.setText(resultSet.getString("Fact"));
                   resultSet.getString("Images");
               }
               connection.close();

           }
           else
           {
               ConnectionResult="Нет подключения";
           }
        }
        catch (Exception ex) {
            Toast.makeText(ConclusionFact.this, "Что-то пошло не так с выводом факта", Toast.LENGTH_LONG).show();
        }
        //enterMobile();
    }
    public void enterMobile() {
        pAdapter.notifyDataSetInvalidated();
        //listView.setAdapter(pAdapter);
    }


    public Bitmap toBip(String encodedImg) {
        byte[] bytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            bytes = Base64.getDecoder().decode(encodedImg);
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}