package com.example.mattlao.passwordbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;




public class passwords extends Activity
    {
    SQLiteDatabase db;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_passwords);

        db = openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("create table if not exists namesandpass4(_id integer primaryKey auto increment, website text, password text, time date);");

        c = db.query("namesandpass4", null, null, null, null, null, "time Desc", null);
        pws = (ListView) findViewById(R.id.pwtable);

        String[] fromCols = {"_id", "website", "time"};
        int[] toViews = {R.id.idnum, R.id.webname, R.id.date};

        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.encrptedlist, c, fromCols, toViews, 0);
        pws.setAdapter(sca);

        pws.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "this is a list item" + position, Toast.LENGTH_LONG).show();
            }
        });
    }
/*
    public void settable()
    {
        db=openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("create table if not exists namesandpass4(_id integer primaryKey auto increment, website text, password text, time date);");

        c=db.query("namesandpass4",null, null, null, null, null,"time Desc", null);
        pws = (ListView)findViewById(R.id.pwtable);

        String[] fromCols = {"_id","website", "password", "time"};
        int[] toViews = {R.id.idnum, R.id.webname, R.id.password, R.id.date};

            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.listlayout, c, fromCols, toViews, 0);
            pws.setAdapter(sca);

            pws.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "this is a list item"+position, Toast.LENGTH_LONG).show();
            }
        });

    }*/

        boolean encrypt=true;
        ListView pws;

        public void press(View view)
    {
        if(view.getId()==R.id.update)
        {
            EditText webname = (EditText) findViewById(R.id.updatename);
            EditText pwnew = (EditText) findViewById(R.id.updatepw);


            Calendar cal = Calendar.getInstance();

            db = openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

            db.execSQL("insert into namesandpass4(website, password, time) " +
                    "values('" + webname.getText().toString() + "','" + pwnew.getText().toString() + "','"+ sdf.format(cal.getTime())+"')");

            c=db.query("namesandpass4",null, null, null, null, null,"time Desc", null);
           // c=db.query("namesandpass4",null, null, null, null, null,"time Desc", null);


            String[] fromCols = {"website", "password", "time"};
            int[] toViews = {R.id.webname, R.id.password, R.id.date};

            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.listlayout, c, fromCols, toViews, 0);
            pwnew.setText("");
            webname.setText("");
            pws.setAdapter(sca);


        }

        else if (view.getId()== R.id.edittable)
        {
            Intent intentnew = new Intent(getApplicationContext(), Edittable.class);
            this.startActivity(intentnew);

        }



        else if (view.getId() == R.id.encrypt)
        {

            if (encrypt) {
                encrypt=false;
                db = openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                db.execSQL("create table if not exists namesandpass4(_id integer primaryKey auto increment, website text, password text, time date);");

                c = db.query("namesandpass4", null, null, null, null, null, "time Desc", null);
                pws = (ListView) findViewById(R.id.pwtable);

                String[] fromCols = {"_id", "website", "password", "time"};
                int[] toViews = {R.id.idnum, R.id.webname, R.id.password, R.id.date};

                SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.listlayout, c, fromCols, toViews, 0);
                pws.setAdapter(sca);

                pws.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), "this is a list item" + position, Toast.LENGTH_LONG).show();
                    }
                });

            }
            else
            {
                encrypt=true;
                db = openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                db.execSQL("create table if not exists namesandpass4(_id integer primaryKey auto increment, website text, password text, time date);");

                c = db.query("namesandpass4", null, null, null, null, null, "time Desc", null);
                pws = (ListView) findViewById(R.id.pwtable);

                String[] fromCols = {"_id", "website", "time"};
                int[] toViews = {R.id.idnum, R.id.webname, R.id.date};

                SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.encrptedlist, c, fromCols, toViews, 0);
                pws.setAdapter(sca);

                pws.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), "this is a list item" + position, Toast.LENGTH_LONG).show();
                    }
                });


            }
        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.passwords, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
