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
    TextView textLink;
    List<Mask> data;
    AdapterMask pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion_fact);

        GetTableSQL();
    }

    int min = 1;
    int max = 4;
    int i;
    //Random random = new Random();

    public void GetTableSQL() {
                final int r = new Random().nextInt((max - min) + 1) + min;


                textFact = findViewById(R.id.textFact);
                textLink = findViewById(R.id.textLink);
                Picture = findViewById(R.id.imageView);
                try {
                    ConnectionHelpers connectionHelpers = new ConnectionHelpers();
                    connection = connectionHelpers.connectionClass();
                    if (connection != null) {
                        String query = "Select * From Facts WHERE Kod_fact =" + r;
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);

                        while (resultSet.next()) {
                            textFact.setText(resultSet.getString("Fact"));
                            resultSet.getString("Images");
                            textLink.setText(resultSet.getString("Link"));
                        }
                        connection.close();

                    } else {
                        ConnectionResult = "Нет подключения";
                    }
                } catch (Exception ex) {
                    Toast.makeText(ConclusionFact.this, "Что-то пошло не так с выводом факта", Toast.LENGTH_LONG).show();
                }
    }


    public void btnYet(View view) {
        try {
            GetTableSQL();
        } catch (Exception ex) {
            Toast.makeText(ConclusionFact.this, "Что-то пошло не так", Toast.LENGTH_LONG).show();
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