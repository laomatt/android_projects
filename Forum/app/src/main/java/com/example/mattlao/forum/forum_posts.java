package com.example.mattlao.forum;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class forum_posts extends Activity {
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_posts);
        db= openOrCreateDatabase("mainDB", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        db.execSQL("create if not exist table(_id integer primaryKey auto increment, name text, time text, message text);");

        settable();
    }

    public void settable()
    {

        Cursor c = db.query("table",null,null,null,null,null,"_id Desc",null);
        String[] fromCols={"name", "time", "message"};
        int[] toViews={R.id.name, R.id.time, R.id.body};

        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.listlayout, c, fromCols, toViews,0);


        ListView lv = (ListView)findViewById(R.id.listid);

        lv.setAdapter(sca);


    }

    public void submit(View view)
    {
        EditText name = (EditText) findViewById(R.id.nameplayer);
        EditText message = (EditText) findViewById(R.id.bodytext);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Calendar cal= Calendar.getInstance();
        db.execSQL("insert into table(name, time ,message) values('"+name.getText().toString()+","+sdf.format(cal.getTime())+","+message.getText().toString()+"')");

        settable();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forum_posts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
