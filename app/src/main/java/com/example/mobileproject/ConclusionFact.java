package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ConclusionFact extends AppCompatActivity {
    Connection connection;
    String ConnectionResult = "";
    ImageView Picture;
    View v;
    String img = null;
    TextView textFact;
    List<Mask> data;
    AdapterMask pAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion_fact);


        textFact = findViewById(R.id.textFact);
        Picture = findViewById(R.id.imageView);

        v = findViewById(com.google.android.material.R.id.ghost_view);

        GetTableSQL(v);
    }

    public void GetTableSQL(View view) {
        try {
            data = new ArrayList<Mask>();
            pAdapter = new AdapterMask(ConclusionFact.this, data);
           ConnectionHelpers connectionHelpers = new ConnectionHelpers();
           connection = connectionHelpers.connectionClass();
           if (connection != null)
           {
               String query = "Select * From Facts";
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery(query);
                   Mask tempMask = new Mask
                           (resultSet.getInt("Kod_fact"),
                                   resultSet.getString("Fact"),
                                   resultSet.getString("Images")
                   );
                   data.add(tempMask);
                   pAdapter.notifyDataSetInvalidated();;
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

    }


    public Bitmap toBip(String encodedImg) {
        byte[] bytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            bytes = Base64.getDecoder().decode(encodedImg);
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}