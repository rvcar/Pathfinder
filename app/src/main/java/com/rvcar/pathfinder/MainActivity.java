package com.rvcar.pathfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String GOOGLE_KEY = "AIzaSyDGuxjKi5ma3YN0yPeVEpXs3t-qnL3MNb8";
    private final static String DIRECTION_QUERY = "https://maps.googleapis.com/maps/api/directions/json?";

    EditText etOrigin;
    EditText etDestination;

    String stOrigin;
    String stDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etOrigin = (EditText) findViewById(R.id.editTextOrigin);
        etDestination = (EditText) findViewById(R.id.editTextDestination);

        Button buttonGo = (Button) findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        stOrigin = etOrigin.getText().toString();
        stDestination = etDestination.getText().toString();

        if (stOrigin.isEmpty()) {
            Toast.makeText(this, "Введите адрес начала пути", Toast.LENGTH_SHORT).show();
            return;
        }

        if (stDestination.isEmpty()) {
            Toast.makeText(this, "Введите адрес назначения", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MapsActivity.class);

        try {
            intent.putExtra("compltUrl", makeUrl());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        startActivity(intent);

    }

    private String makeUrl() throws UnsupportedEncodingException {
        String urlOrigin = URLEncoder.encode(stOrigin, "utf-8");
        String urlDestination = URLEncoder.encode(stDestination, "utf-8");

        return DIRECTION_QUERY + "origin=" + urlOrigin + "&destination=" + urlDestination + "&key=" + GOOGLE_KEY + "&departure_time=now";
    }

}
