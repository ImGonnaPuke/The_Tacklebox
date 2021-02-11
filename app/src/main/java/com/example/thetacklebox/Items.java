package com.example.thetacklebox;

public class Items {
    private int img;
    private String name;
    private String type;
    private String color;
    private String length;
    private String numColor;
    private String depth;
    private String weight;
    private String model;
    private String desc;


    public Items(int resImg, String Name, String Type, String Color, String Len, String nCol, String Depth, String Weight, String Model,
                 String Desc){
        img = resImg;
        name = Name;
        type = Type;
        color = Color;
        length = Len;
        numColor = nCol;
        depth = Depth;
        weight = Weight;
        model = Model;
        desc = Desc;
    }



    public int getImg(){
        return img;
    }
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public String getColor(){
        return color;
    }
    public String getNumColor(){
        return numColor;
    }
    public String getLength(){ return length; }
    public String getDepth(){
        return depth;
    }
    public String getWeight(){
        return weight;
    }
    public String getModel(){
        return model;
    }
    public String getDesc(){
        return desc;
    }

}
