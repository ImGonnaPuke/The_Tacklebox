package com.example.thetacklebox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.lang.reflect.Field;

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
    private Spinner spin;
    public String title;

    public String name1;
    public String newType;
    public String newColor;
    public String newNumCol;
    public String newLen;
    public String newDep;
    public String newWeight;
    public String newMod;
    public String newDes;

    public int newPos;
    public int newID;
    public int[] resArray;
    public String[] drawName;
    public int spinPos;

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
        spin = view.findViewById(R.id.spinner);

        editN.setText(getName());
        editT.setText(getType());
        editC.setText(getColor());
        editNC.setText(getNCol());
        editW.setText(getWeight());
        editL.setText(getLen());
        editD.setText(getDep());
        editDesc.setText(getDes());
        editM.setText(getMod());


        Field[] ID_Fields = R.drawable.class.getFields();
        resArray = new int[resNum()];
        drawName = new String[resNum()];

        int k=0;
        for(int i = 0; i < ID_Fields.length; i++) {
            try {
                //System.out.println("nigger "+ID_Fields[i].getName());
                if(ID_Fields[i].getName().contains("image")) {
                    resArray[k] = ID_Fields[i].getInt(null);
                    drawName[k] = ID_Fields[i].getName();
                    System.out.println("shit "+ID_Fields[i].getName());
                    k++;
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(getContext(), R.array.test, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, drawName);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapt);

        String spinName = spin.getSelectedItem().toString();
        //spinPos = spin.getSelectedItemPosition();

        //Toast.makeText(getActivity(), "" + ass2, Toast. LENGTH_SHORT).show();

        //editN.setText(ass2);


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
                        spinPos = spin.getSelectedItemPosition();
                        int img = resArray[spinPos];

                        myEXD.applyTexts(name, type, color, length, numCol, weight, depth, model, desc, img);

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
        void applyTexts(String name, String type, String color, String length, String numCol, String weight, String depth,
                        String model, String desc, int img);

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
    public void setType(String pos){
        newType = pos;
    }
    public void setColor(String pos){
        newColor = pos;
    }
    public void setNumColor(String pos){
        newNumCol = pos;
    }
    public void setLength(String pos){
        newLen = pos;
    }
    public void setWeight(String pos){
        newWeight = pos;
    }
    public void setDepth(String pos){
        newDep = pos;
    }
    public void setModel(String pos){
        newMod = pos;
    }
    public void setDesc(String pos){
        newDes = pos;
    }

    public int getPos2(){return  newPos;}
    public int getID2(){return  newID;}
    public String getTitle(){
        return title;
    }

    public String getName(){
        return name1;
    }
    public String getType(){
        return newType;
    }
    public String getColor(){
        return newColor;
    }
    public String getNCol(){
        return newNumCol;
    }
    public String getLen(){
        return newLen;
    }
    public String getWeight(){ return newWeight; }
    public String getDep(){
        return newDep;
    }
    public String getMod(){ return newMod; }
    public String getDes(){
        return newDes;
    }



    public int resNum(){
        int k =0;
        Field[] ID_Fields = R.drawable.class.getFields();
        for(int i = 0; i < ID_Fields.length; i++) {
            try {

                if(ID_Fields[i].getName().contains("image")) {
                    //resArray[k] = ID_Fields[i].getInt(null);
                    //drawName[k] = ID_Fields[i].getName();
                    System.out.println("shit "+ID_Fields[i].getName());
                    k++;
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return k;
    }





}