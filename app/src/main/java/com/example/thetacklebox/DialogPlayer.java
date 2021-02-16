package com.example.thetacklebox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogPlayer extends AppCompatDialogFragment {

    private EditText editN;
    private EditText editT;
    private EditText editC;
    private EditText editNC;
    private EditText editL;
    private EditText editD;
    private EditText editW;
    private EditText editM;
    private EditText editDesc;
    private ExampleDialogListener myEXD;
    public String title;

    public String name1;
    public int newPos;
    public int newID;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_layout,null);

        //title = "Add new entry";

        newTitle(title);

        editN = view.findViewById(R.id.name);
        editT = view.findViewById(R.id.type);
        editC = view.findViewById(R.id.color);
        editNC = view.findViewById(R.id.numCol);
        editW = view.findViewById(R.id.weight);
        editL = view.findViewById(R.id.length);
        editD = view.findViewById(R.id.depth);
        editDesc = view.findViewById(R.id.desc);
        editM = view.findViewById(R.id.model);




        editN.setText(name1);


        builder.setView(view).setTitle(title)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Toast. makeText(this, "" + name1, Toast. LENGTH_SHORT).show();

                        String name = editN.getText().toString();
                        String type = editT.getText().toString();
                        String color = editC.getText().toString();
                        String length = editL.getText().toString();
                        String numCol = editNC.getText().toString();
                        String weight = editW.getText().toString();
                        String depth = editD.getText().toString();
                        String model = editM.getText().toString();
                        String desc = editDesc.getText().toString();

                        myEXD.applyTexts(name, type, color, length, numCol, weight, depth, model, desc);

                    }
                });





        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            myEXD = (ExampleDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }

    }


    public interface ExampleDialogListener{
        void applyTexts(String name, String type, String color, String length, String numCol, String weight, String depth, String model, String desc);

    }

    public void newTitle(String newTitle){
        title = newTitle;
    }
    public void setName(String newName){
        //editN.setText(newName);
        name1 = newName;
    }
    public void setPos(int pos){
        //editN.setText(newName);
        newPos = pos;
    }
    public void setID(int pos){
        //editN.setText(newName);
        newID = pos;
    }
    public int getPos2(){return  newPos;}
    public int getID2(){return  newID;}
    public String getName(){
        return name1;
    }
    public String getTitle(){
        return title;
    }

}