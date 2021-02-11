package com.example.thetacklebox;
import android.provider.BaseColumns;


public class LureTemplate {

    private LureTemplate(){}

    public static final class LureItems implements BaseColumns{
        public static final String TABLE_NAME = "myBox";

        public static final String COLUMN_IMG = "image";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_LENGTH = "length";
        public static final String COLUMN_NUM_COLOR = "numCol";
        public static final String COLUMN_DEPTH ="depth";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_MODEL = "model";
        public static final String COLUMN_DESC = "description";


    }

}
