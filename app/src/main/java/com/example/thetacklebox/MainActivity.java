package com.example.thetacklebox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button lures1;
    private Button luresAll;
    private Button myTackle;
    private ImageButton iB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lures1 = (Button) findViewById(R.id.jerk);
        luresAll = (Button) findViewById(R.id.all);
        myTackle = (Button) findViewById(R.id.mybox);

        //luresAll.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.all_lures_ , 0, 0);
        //Drawable top = getResources().getDrawable(R.drawable.all_lures_);
        //luresAll.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);

        //final Toast toast = Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();

        lures1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                //Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Categorized.class));
            }
        });

        luresAll.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                //toast.show();
                startActivity(new Intent(MainActivity.this, AllLures.class));
            }
        });

        myTackle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                //Toast. makeText(getApplicationContext(), "This feature is not yet avaliable", Toast. LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MyBox.class));
            }
        });







    }
}
