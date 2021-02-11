package com.example.thetacklebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MyBox extends AppCompatActivity implements DialogPlayer.ExampleDialogListener{

    private RecyclerView rView;
    private LureAdaptor mAdapter;
    private ArrayList<Items> lureList;
    private RecyclerView.LayoutManager myLayout;
    private Button addB;
    private SQLiteDatabase myWDB;
    private SQLiteDatabase myRDB;
    public TackleboxDB myDBHelper;
    public ArrayList<Items> DBList;

    //custom lure grabs
    public String nameNEW;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_box);

        TackleboxDB tBox = new TackleboxDB(this);
        myWDB = tBox.getWritableDatabase();
        myRDB = tBox.getReadableDatabase();
        myDBHelper = new TackleboxDB(this);

        //testDB();
        DBList = myDBHelper.getAll();
        //genList();
        buildRecycle();


        addB = (Button) findViewById(R.id.add);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


        addB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Toast. makeText(getApplicationContext(), "" + DBList.get(0).getName(), Toast. LENGTH_SHORT).show();
                openNew();
                //testDB();
                //delItem();
                //Toast. makeText(getApplicationContext(), "" + nameNEW, Toast. LENGTH_SHORT).show();
            }
        });


    }

    public void genList(){
        lureList = new ArrayList<>();

        lureList.add(new Items(R.drawable.ic_launcher_background, "Epic Lure", "Pog", "Purple", "350in", "5",
                "400mi", "420 tons", "21", "Fake and gay"));


    }

    public void delItem(int position){
        if(DBList.size()>1){
            myDBHelper.deleteFromDB(DBList.get(position).getName());
            DBList.remove(position);
        }
        else{
            Toast.makeText(getApplicationContext(), "List would be empty!", Toast. LENGTH_SHORT).show();
        }
        buildRecycle();
    }

    public void showItem (int position) {


        String lureN = DBList.get(position).getName();
        String lureT = DBList.get(position).getType();
        String lureC = DBList.get(position).getColor();
        String lureL = DBList.get(position).getLength();
        String lureNC = DBList.get(position).getNumColor();
        String lureD = DBList.get(position).getDepth();
        String lureW = DBList.get(position).getWeight();
        String lureM = DBList.get(position).getModel();
        String lureDesc = DBList.get(position).getDesc();
        int lureI = DBList.get(position).getImg();



        //Toast toast = Toast. makeText(getApplicationContext(), toToast + "", Toast. LENGTH_SHORT);
        //toast.show();

        if(lureN != null) {
            Intent intent = new Intent(getBaseContext(), MainLure.class);
            intent.putExtra("LURE_NAME", lureN);
            intent.putExtra("LURE_TYPE", lureT);
            intent.putExtra("LURE_COLOR", lureC);
            intent.putExtra("LURE_LENGTH", lureL);
            intent.putExtra("LURE_NUM_COL", lureNC);
            intent.putExtra("LURE_DEPTH", lureD);
            intent.putExtra("LURE_WEIGHT", lureW);
            intent.putExtra("LURE_MODEL", lureM);
            intent.putExtra("LURE_DESC", lureDesc);
            intent.putExtra("LURE_IMG", lureI);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), lureI + "", Toast. LENGTH_SHORT);
            toast.show();
        }


    }

    public void buildRecycle(){
        rView = (RecyclerView) findViewById(R.id.recView);
        rView.setHasFixedSize(true);
        myLayout = new LinearLayoutManager(this);
        //mAdapter = new LureAdaptor(lureList);

        mAdapter = new LureAdaptor(DBList);

        rView.setLayoutManager(myLayout);
        rView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new LureAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showItem(position);
            }

        });

        mAdapter.setOnItemLongClickListener(new LureAdaptor.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(getApplicationContext(), "cocke and blals " + DBList.get(position).getName(), Toast. LENGTH_SHORT).show();
                delItem(position);
            }
        });



    }

    public void testDB(){

        ContentValues cv = new ContentValues();
        cv.put(LureTemplate.LureItems.COLUMN_NAME, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_TYPE, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_COLOR, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_NUM_COLOR, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_DESC, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_DEPTH, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_LENGTH, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_WEIGHT, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_MODEL, "ASS");
        cv.put(LureTemplate.LureItems.COLUMN_IMG, R.drawable.ic_launcher_background);

        myWDB.insert(LureTemplate.LureItems.TABLE_NAME,null,cv);


    }


    public void openNew(){
        DialogPlayer dialog = new DialogPlayer();
        dialog.show( MyBox.this.getSupportFragmentManager(), "test");

    };
    @Override
    public void applyTexts(String name, String type, String color, String length, String numCol, String weight, String depth, String model, String desc){

        //nameNEW = name;

        ContentValues cv = new ContentValues();
        cv.put(LureTemplate.LureItems.COLUMN_NAME, name);
        cv.put(LureTemplate.LureItems.COLUMN_TYPE, type);
        cv.put(LureTemplate.LureItems.COLUMN_COLOR, color);
        cv.put(LureTemplate.LureItems.COLUMN_NUM_COLOR, numCol);
        cv.put(LureTemplate.LureItems.COLUMN_DESC, desc);
        cv.put(LureTemplate.LureItems.COLUMN_DEPTH, depth);
        cv.put(LureTemplate.LureItems.COLUMN_LENGTH, length);
        cv.put(LureTemplate.LureItems.COLUMN_WEIGHT, weight);
        cv.put(LureTemplate.LureItems.COLUMN_MODEL, model);
        cv.put(LureTemplate.LureItems.COLUMN_IMG, R.drawable.ic_launcher_background);

        myWDB.insert(LureTemplate.LureItems.TABLE_NAME,null,cv);

        DBList = myDBHelper.getAll();
        buildRecycle();


    }

/*
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater mInfalter = getMenuInflater();
        mInfalter.inflate(R.menu.menu, menu);

        MenuItem searchI = menu.findItem(R.id.action_search);
        SearchView searchV = (SearchView) searchI.getActionView();

        searchV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

 */
}
