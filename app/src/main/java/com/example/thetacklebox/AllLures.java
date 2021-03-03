package com.example.thetacklebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllLures extends AppCompatActivity {

    private RecyclerView rView;
    private LureAdaptor mAdapter;
    private ArrayList<Items> lureList;
    private RecyclerView.LayoutManager myLayout;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lures);
        genList();
        buildRecycle();

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


    }

    public void genList(){
        lureList = new ArrayList<>();

        lureList.add(new Items(R.drawable.ic_launcher_background, "Epic Lure", "Pog", "Purple", "350in", "5",
                "400mi", "420 tons", "21", "Fake and gay"));
        lureList.add(new Items(R.drawable.ic_launcher_background, "Epic Lure 2", "Pog", "Purple", "350in", "5",
                "400mi", "420 tons", "21", "Fake and gay"));


    }

    public void showItem (int position) {


        String lureN = lureList.get(position).getName();
        String lureT = lureList.get(position).getType();
        String lureC = lureList.get(position).getColor();
        String lureL = lureList.get(position).getLength();
        String lureNC = lureList.get(position).getNumColor();
        String lureD = lureList.get(position).getDepth();
        String lureW = lureList.get(position).getWeight();
        String lureM = lureList.get(position).getModel();
        String lureDesc = lureList.get(position).getDesc();
        int lureI = lureList.get(position).getImg();

        //Toast toast = Toast. makeText(getApplicationContext(), toToast + "", Toast. LENGTH_SHORT);
        //toast.show();

       // if(lureN.equals("Epic Lure")) {
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
        /*}
        else{
            Toast toast = Toast.makeText(getApplicationContext(), lureI + "", Toast. LENGTH_SHORT);
            toast.show();
        }

         */

    }

    public void buildRecycle(){
        rView = (RecyclerView) findViewById(R.id.recView);
        rView.setHasFixedSize(true);
        myLayout = new LinearLayoutManager(this);
        mAdapter = new LureAdaptor(lureList);
        rView.setLayoutManager(myLayout);
        rView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new LureAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showItem(position);
            }
            @Override
            public void onEditClick(int position){
                //openEdit(position);
            }

        });


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
