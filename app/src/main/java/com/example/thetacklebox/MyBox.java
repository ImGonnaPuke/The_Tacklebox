package com.example.thetacklebox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

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
    public DialogPlayer dialog;
    public static LureAdaptor SearchHelper;
    public int count;


    //custom lure grabs
    public String nameNEW;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_box);

        SearchHelper = new LureAdaptor();
        SearchHelper.setSearch(4);


        TackleboxDB tBox = new TackleboxDB(this);
        myWDB = tBox.getWritableDatabase();
        myRDB = tBox.getReadableDatabase();
        myDBHelper = new TackleboxDB(this);

        dialog = new DialogPlayer();

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


        count = 1;
        addB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

               // count = count+1;
               // if(count>2){
               //     count = 1;
               // }


                Toast. makeText(getApplicationContext(), "" + count + "" +SearchHelper.getSearch(), Toast. LENGTH_SHORT).show();
                //openNew();
                //testDB();
                //delItem();
                //Toast. makeText(getApplicationContext(), "" + nameNEW, Toast. LENGTH_SHORT).show();
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                int oldPos = viewHolder.getAdapterPosition();
                int newPos = target.getAdapterPosition();

                Collections.swap(DBList, oldPos, newPos);


                rView.getAdapter().notifyItemMoved(oldPos,newPos);

                updateItem(oldPos, newPos);

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                delItem(viewHolder.getLayoutPosition());
                Toast.makeText(MyBox.this, "" + viewHolder.getOldPosition(), Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(rView);





    }


    public void delItem(int position){
        if(DBList.size()>1){
            myDBHelper.deleteFromDB(DBList.get(position).getID());
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
                //showItem(position);
                //delItem(position);
                Toast.makeText(getApplicationContext(), "short " + position, Toast. LENGTH_SHORT).show();
                //openEdit(position);
            }
            @Override
            public void onEditClick(int position){
                openEdit(position);
                Toast.makeText(getApplicationContext(), "cocke and blals " + DBList.get(position).getID(), Toast. LENGTH_SHORT).show();
            }

        });

        mAdapter.setOnItemLongClickListener(new LureAdaptor.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(getApplicationContext(), "long" + DBList.get(position).getID(), Toast. LENGTH_SHORT).show();
                //openEdit(position);
                //delItem(position);
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
        //DialogPlayer dialog = new DialogPlayer();
        dialog.newTitle("Add new entry");
        dialog.setName("Name");
        Toast. makeText(getApplicationContext(), ""+ dialog.getName(), Toast. LENGTH_SHORT).show();
        dialog.show( MyBox.this.getSupportFragmentManager(), "test");

    };
    public void openEdit(int position){
        //DialogPlayer dialog = new DialogPlayer();
        dialog.newTitle("Edit an entry");

        dialog.setName(DBList.get(position).getName());
        dialog.setType(DBList.get(position).getType());
        dialog.setColor(DBList.get(position).getColor());
        dialog.setNumColor(DBList.get(position).getNumColor());
        dialog.setLength(DBList.get(position).getLength());
        dialog.setDepth(DBList.get(position).getDepth());
        dialog.setWeight(DBList.get(position).getWeight());
        dialog.setModel(DBList.get(position).getModel());
        dialog.setDesc(DBList.get(position).getDesc());

        dialog.setPos(position);
        dialog.setID(DBList.get(position).getID());
        Toast. makeText(getApplicationContext(), ""+ dialog.getName(), Toast. LENGTH_SHORT).show();
        dialog.show( MyBox.this.getSupportFragmentManager(), "test");

        //updateItem(position);
    };

    public void updateItem(int position, int position2){

        //start here for swap??

        //int tempID = DBList.get(position).getID();
        //int tempID2 = DBList.get(position2).getID();

        myDBHelper.onUpdate(DBList.get(position2).getName(), DBList.get(position).getType(), DBList.get(position).getColor(), DBList.get(position).getLength(), DBList.get(position).getNumColor(),
                DBList.get(position).getWeight(), DBList.get(position).getDepth(), DBList.get(position).getModel(), DBList.get(position).getDesc(),
                DBList.get(position).getID(), DBList.get(position).getImg() );

        myDBHelper.onUpdate(DBList.get(position).getName(), DBList.get(position).getType(), DBList.get(position).getColor(), DBList.get(position).getLength(), DBList.get(position).getNumColor(),
                DBList.get(position).getWeight(), DBList.get(position).getDepth(), DBList.get(position).getModel(), DBList.get(position).getDesc(),
                DBList.get(position2).getID(), DBList.get(position).getImg() );
        //DBList.remove(position);


        //Toast.makeText(getApplicationContext(), "List is updated!!", Toast. LENGTH_SHORT).show();

        DBList = myDBHelper.getAll();
        //buildRecycle();
    }


    @Override
    public void applyTexts(String name, String type, String color, String length, String numCol, String weight,
                           String depth, String model, String desc, int img){

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
        //cv.put(LureTemplate.LureItems._ID, id);
        cv.put(LureTemplate.LureItems.COLUMN_IMG, img);

        //this is why onUpdate updates ID

        //DialogPlayer dialog = new DialogPlayer();
        String title = dialog.getTitle();
        int tempPos = dialog.getPos2();
        int tempID = dialog.getID2();

        if(title.equals("Add new entry")) {
            myWDB.insert(LureTemplate.LureItems.TABLE_NAME, null, cv);
        }
        else{
            updateItem2(name, type, color, length, numCol, weight, depth, model, desc, tempID, img);
            Toast. makeText(getApplicationContext(), "Editing " + tempID, Toast. LENGTH_SHORT).show();
        }

        DBList = myDBHelper.getAll();
        buildRecycle();


    }

    public void updateItem2(String name, String type, String color, String length, String numCol, String weight, String depth, String model, String desc, int id, int img){

        //IT WERKSSSSS
        myDBHelper.onUpdate(name, type, color, length, numCol, weight, depth, model, desc, id, img);
        //DBList.remove(position);


        //Toast.makeText(getApplicationContext(), "List is updated!!", Toast. LENGTH_SHORT).show();

        DBList = myDBHelper.getAll();
        buildRecycle();
    }



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


}
