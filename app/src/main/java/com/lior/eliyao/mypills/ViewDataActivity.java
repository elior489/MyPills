package com.lior.eliyao.mypills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {

    ListView lvPills;
    ArrayList<Pill> alPills;
    ArrayList<String> info;
    ArrayAdapter<String> adapter;
    HelperDB hlp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        lvPills=findViewById(R.id.lvPills);
    }

    public void viewAll(View view) {
        alPills=new ArrayList<>();
        hlp=new HelperDB(getApplicationContext());
        alPills=hlp.allData();
        Toast.makeText(this, ""+alPills.size(), Toast.LENGTH_SHORT).show();
        goView(alPills);
    }

    private void goView(ArrayList<Pill> alCars) {
        info=new ArrayList<>();
        for (int i = 0; i < alCars.size(); i++) {
            info.add(alCars.get(i).toString());
        }
        adapter=new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1, info);
        lvPills.setAdapter(adapter);
    }

    public void byCompany(View view) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("Write company by query");
        final EditText et=new EditText(getApplicationContext());
        et.setWidth(100);
        et.setEms(10);
        et.setGravity(Gravity.CENTER);
        et.setPadding(10,10,10,10);
        et.setBackgroundColor(Color.parseColor("#FFFFDD"));
        adb.setView(et);
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String st=et.getText().toString();
                alPills=new ArrayList<>();
                hlp=new HelperDB(getApplicationContext());
                alPills=hlp.byCompanyData(st);
                goView(alPills);
            }
        });
        adb.create().show();
    }

    public void byOldest(View view) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("Year for 'before'?");
        final EditText et=new EditText(getApplicationContext());
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        adb.setView(et);
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int before=Integer.parseInt(et.getText().toString());
                alPills=new ArrayList<>();
                hlp=new HelperDB(getApplicationContext());
                alPills=hlp.byOldest(before);
                goView(alPills);
            }
        });
        adb.create().show();
    }

    public void viewSum(View view) {
        hlp=new HelperDB(getApplicationContext());
        double sum=hlp.calcSum();
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("Sum of price="+sum);
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.create().show();
    }

    public void groupByCompany(View view) {
        info=new ArrayList<>();
        hlp=new HelperDB(getApplicationContext());
        info=hlp.byCompany();
        adapter=new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1, info);
        lvPills.setAdapter(adapter);
    }

    public void groupByBatch(View view) {
        info=new ArrayList<>();
        hlp=new HelperDB(getApplicationContext());
        info=hlp.byBatch();
        adapter=new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1, info);
        lvPills.setAdapter(adapter);
    }

    public void updateBatch(View view) {
        String oldModel="Uno";
        String newModel="DuoDuo";
        hlp=new HelperDB(getApplicationContext());
        hlp.updateModel(oldModel, newModel);
        alPills=hlp.allData();
        goView(alPills);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        menu.add(0,1,0,"View all");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.back)
            finish();
        if (id==R.id.exit)
            finishAffinity();
        if (id==1)
            viewAll(null);
        return true;
    }
}