package com.google.vr.sdk.samples.hellovr;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    protected EditText originET, destET;
    protected TextView oriTV, destTV;
    protected Button startButton;
    protected StreetViewLoader sVLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startBtn);

        originET = (EditText) findViewById(R.id.originET);
        destET = (EditText) findViewById(R.id.destET);

        oriTV = (TextView) findViewById(R.id.originTV);
        destTV = (TextView) findViewById(R.id.destTV);

        oriTV.setText("Origin");
        destTV.setText("Destination");


        startButton.setText("Start Running");

        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == startButton.getId()){
            sVLoader = new StreetViewLoader(new Intent(this, HelloVrActivity.class), this);

            DirectionsLoader dirLoader = new DirectionsLoader(this);

            int svUrlLength = 4;

            int heading = 0;

            String urlArr[] = new String[svUrlLength];

            String svTempURL = "https://maps.googleapis.com/maps/api/streetview?size=600x300&location="+ originET.getText().toString() +"&key="+ getString(R.string.key)
                    + "&heading=";

            for(int i = 0 ; i < svUrlLength ; i++){
                urlArr[i] = svTempURL + heading;
                heading += 90;
            }

            String dirURL = "https://maps.googleapis.com/maps/api/directions/json?" +
                    "mode=walking&origin=" + originET.getText().toString() + "&destination=" +
                    destET.getText().toString() + "&key="+ getString(R.string.key);

            dirLoader.execute(dirURL);

            sVLoader.execute(urlArr);
        }
    }
}
