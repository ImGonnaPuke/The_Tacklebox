package com.example.thetacklebox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainLure extends AppCompatActivity {

    private ImageView imgV;
    private TextView name;
    private TextView weight;
    private TextView color;
    private TextView desc;
    private TextView numCol;
    private TextView length;
    private TextView depth;
    private TextView model;
    private TextView type;
    public int[] resArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lure);
        Bundle extras = getIntent().getExtras();

        Field[] ID_Fields = R.drawable.class.getFields();
        resArray = new int[ID_Fields.length];
        int k=0;
        for(int i = 0; i < ID_Fields.length; i++) {
            try {

                if(ID_Fields[i].getName().contains("image")) {
                    resArray[k] = ID_Fields[i].getInt(null);
                    System.out.println("hi "+ID_Fields[i].getName());
                    k++;
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Toast. makeText(getApplicationContext(), " " + resArray[0], Toast. LENGTH_SHORT).show();
        imgV = (ImageView) findViewById(R.id.res_lure);
        name = (TextView) findViewById(R.id.res_name);
        weight = (TextView) findViewById(R.id.res_weight);
        color = (TextView) findViewById(R.id.res_color);
        desc = (TextView) findViewById(R.id.res_desc);
        numCol = (TextView) findViewById(R.id.res_numColor);
        length = (TextView) findViewById(R.id.res_len);
        weight = (TextView) findViewById(R.id.res_weight);
        depth = (TextView) findViewById(R.id.res_depth);
        model = (TextView) findViewById(R.id.res_model);
        type = (TextView) findViewById(R.id.res_type);

        //Toast.makeText(getApplicationContext(), ""+ name.getText().toString(), Toast. LENGTH_SHORT).show();

        if (extras != null) {

            //getters
            String lureN = extras.getString("LURE_NAME");
            String lureT = extras.getString("LURE_TYPE");
            String lureC = extras.getString("LURE_COLOR");
            String lureL = extras.getString("LURE_LENGTH");
            String lureNC = extras.getString("LURE_NUM_COL");
            String lureD = extras.getString("LURE_DEPTH");
            String lureW = extras.getString("LURE_WEIGHT");
            String lureM = extras.getString("LURE_MODEL");
            String lureDesc = extras.getString("LURE_DESC");
            int lureI = extras.getInt("LURE_IMG");

            //setters

            name.setText(name.getText().toString() + lureN);
            type.setText(type.getText().toString()+lureT);
            color.setText(color.getText().toString()+lureC);
            length.setText(length.getText().toString()+lureL);
            numCol.setText(numCol.getText().toString()+lureNC);
            depth.setText(depth.getText().toString()+lureD);
            weight.setText(weight.getText().toString()+lureW);
            model.setText(model.getText().toString()+lureM);
            desc.setText(desc.getText().toString()+lureDesc);
            imgV.setImageResource(resArray[0]);
            Toast.makeText(getApplicationContext(), ""+ imgV, Toast. LENGTH_SHORT).show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Failure", Toast. LENGTH_SHORT);
            toast.show();
        }



    }


}
