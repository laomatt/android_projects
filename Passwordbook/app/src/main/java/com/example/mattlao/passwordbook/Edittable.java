package com.example.mattlao.passwordbook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Edittable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edittable);


        settable();
    }
    SQLiteDatabase db;
    Cursor c;
    ListView pws;

    public void settable()
    {
        db=openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL("create table if not exists namesandpass4(_id integer primaryKey auto increment, website text, password text, time text);");


        c=db.query("namesandpass4", null, null, null, null, null, null);
        pws = (ListView)findViewById(R.id.pwtable);

        String[] fromCols = {"website", "password", "time"};
        int[] toViews = {R.id.webname, R.id.password, R.id.date};

        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.listlayout, c, fromCols, toViews, 0);

        pws.setAdapter(sca);
        c.moveToNext();


        pws.setAdapter(sca);
        pws.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "this is a list item " + position, Toast.LENGTH_LONG).show();

                if(selected==false)
                {
                    view.setBackgroundColor(Color.LTGRAY);
                    selected=true;
                }
                else
                {
                    view.setBackgroundColor(Color.TRANSPARENT);
                    selected=false;
                }


              //  viewID = view

               TextView sampleidtv=(TextView)view.findViewById(R.id.webname);
               sampleid=sampleidtv.getText().toString();

                LinearLayout ll;
                View v;



            }
        });

    }

    boolean selected;

    String sampleid;
    String viewID;
    long logid;
    int currentitem;
    public void press(View view) {

        if (view.getId() == R.id.cleartable)
        {

            db = openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);
           // db.execSQL("insert into namesandpass4(website, password, time) values('" + webname.getText().toString() + "','" + pwnew.getText().toString() + "','samptime')");

            db.delete("namesandpass4",null,null);
            c = db.query("namesandpass4", null, null, null, null, null, null);


            String[] fromCols = {"website", "password", "time"};
            int[] toViews = {R.id.webname, R.id.password, R.id.date};
            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.listlayout, c, fromCols, toViews, 0);

            pws.setAdapter(sca);


        }

        else if(view.getId()==R.id.update)
        {
            EditText webname = (EditText) findViewById(R.id.updatename);
            EditText pwnew = (EditText) findViewById(R.id.updatepw);


            Calendar cal = Calendar.getInstance();

            db = openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

            db.execSQL("insert into namesandpass4(website, password, time) " +
                    "values('" + webname.getText().toString() + "','" + pwnew.getText().toString() + "','"+ sdf.format(cal.getTime())+"')");


            c=db.query("namesandpass4",null, null, null, null, null,"time Desc", null);



            String[] fromCols = {"website", "password", "time"};
            int[] toViews = {R.id.webname, R.id.password, R.id.date};
            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.listlayout, c, fromCols, toViews, 0);
            pws.setAdapter(sca);

            pwnew.setText("");
            webname.setText("");



        }
        else if(view.getId()==R.id.deleteentry)
        {

            db = openOrCreateDatabase("passwords4", SQLiteDatabase.CREATE_IF_NECESSARY, null);

            db.execSQL("delete from namesandpass4 where website='"+sampleid+"'");

            c=db.query("namesandpass4",null, null, null, null, null,"time Desc", null);


            String[] fromCols = {"website", "password", "time"};
            int[] toViews = {R.id.webname, R.id.password, R.id.date};

            SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.listlayout, c, fromCols, toViews, 0);

            pws.setAdapter(sca);

        }

        else if(view.getId()==R.id.backmenu)
        {
            Intent intent = new Intent(getApplicationContext(), passwords.class);
            this.startActivity(intent);

        }


    }

    private String encrpt(String input)
    {
        char[] temp=new char[input.length()];
    int i=0;
        while(i<input.length())
        {
            temp[i]=input.charAt(i);
            i++;
        }

        for(char g:temp)
        {
            

        }


        String output="";


        return output;
    }


    private String decrpt(String input)
    {



        String output="";


        return output;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edittable, menu);
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
