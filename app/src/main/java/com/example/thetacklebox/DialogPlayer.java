package com.example.thetacklebox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_layout,null);

        builder.setView(view).setTitle("Add new entry")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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

        editN = view.findViewById(R.id.name);
        editT = view.findViewById(R.id.type);
        editC = view.findViewById(R.id.color);
        editNC = view.findViewById(R.id.numCol);
        editW = view.findViewById(R.id.weight);
        editL = view.findViewById(R.id.length);
        editD = view.findViewById(R.id.depth);
        editDesc = view.findViewById(R.id.desc);
        editM = view.findViewById(R.id.model);



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

}