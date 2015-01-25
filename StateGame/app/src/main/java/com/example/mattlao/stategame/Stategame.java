
/*
* Author: Matthew Lao
* class: 211D  HW#6
* Date: 11-13-14
*
* Description:  This is a game that tests the users knowledge of US states and
* thier capitols, then it lists the top scorers for the game via a SQL table.
*
* */
package com.example.mattlao.stategame;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class Stategame extends Activity
{    @Override
     /*********************************************************************************/
    /******************sets up the animated splash screen ******************/
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stategame);
        FrameLayout fl = (FrameLayout)findViewById(R.id.doormat);
        fl.setBackgroundColor(Color.YELLOW);
        ImageView iv = (ImageView)findViewById(R.id.splashimage);
        iv.setBackgroundResource(R.drawable.animat);

        //bear animation
        AnimationDrawable bearrunning = (AnimationDrawable)iv.getBackground();
        bearrunning.start();

        }

SQLiteDatabase db;
public static String name;
   /*********************************************************************************/
  /********************** handles the button for this activity  ***********************/
    public void sendMessage(View view)
    {
        Context ct = getApplicationContext();
        Intent intent = new Intent(ct, maingame.class);

        EditText et = (EditText)findViewById(R.id.editfield);

       // TextView tv = (TextView)findViewById(R.id.textfield);
        name= et.getText().toString();


        //this creates the DATABASE
        db = openOrCreateDatabase("namesAndscoresAndtimes2", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        //db = SQLiteDatabase.openDatabase("namesAndscoresAndtimes")
        //this creates the TABLE is it doesn't already exist.
        db.execSQL("create table if not exists namesAndscoresAndtimes(_id integer primaryKey auto increment, name text, score text, time text, percentile number);");
        db.execSQL("insert into namesAndscoresAndtimes(name) values('" + name + "');");

            intent.putExtra("prev","splash");
            intent.putExtra("name",name);
            this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stategame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
