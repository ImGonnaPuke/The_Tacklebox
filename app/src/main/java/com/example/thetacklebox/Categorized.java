package com.example.thetacklebox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Categorized extends AppCompatActivity {

    private Button jerkB;
    private Button crankB;
    private Button softB;
    private Button swimB;
    private Button buzzB;
    private Button jiggB;
    private Button spinB;
    private Button topB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized);

        jerkB = (Button) findViewById(R.id.jerk);
        crankB = (Button) findViewById(R.id.crank);
        softB = (Button) findViewById(R.id.sPlast);
        swimB = (Button) findViewById(R.id.swim);
        buzzB = (Button) findViewById(R.id.buzz);
        jiggB = (Button) findViewById(R.id.jig);
        spinB = (Button) findViewById(R.id.spin);
        topB = (Button) findViewById(R.id.tWater);

        jerkB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });
        crankB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                //startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });
        softB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                //startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });
        softB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                //startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });
        swimB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                //startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });
        buzzB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                //startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });
        jiggB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                //startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });
        spinB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                //startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });
        topB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                //startActivity(new Intent(Categorized.this, JerkBaits.class));
            }
        });


    }
}
