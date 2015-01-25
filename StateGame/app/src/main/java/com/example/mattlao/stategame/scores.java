
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
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class scores extends Activity {
    String name;
    SQLiteDatabase db;
    EditText scoretable;
    String resulttable="";
    TextView[][] theresultstable = new TextView[6][4];
    Cursor c;
    @Override
    /*********************************************************************************/
    /***************************creates and sets up the score activity****************/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scores);



        db = openOrCreateDatabase("namesAndscoresAndtimes2", SQLiteDatabase.OPEN_READONLY, null);   //  db.execSQL("create table if not exists namesAndscoresAndtimes(name text primaryKey, score text, time text, percentile number);");
        c =db.query("namesAndscoresAndtimes", null, null, null, null, null, "percentile Desc", null);

        String[] cols={"name", "score", "time", "percentile"};

        String[] fromCols = {"name", "score", "time", "percentile"};
        int[] toViews = {R.id.na, R.id.sc, R.id.ti, R.id.po};
        SimpleCursorAdapter dude = new SimpleCursorAdapter(this, R.layout.scr, c, fromCols, toViews, 0);

        ListView lv = (ListView)findViewById(R.id.scoretables);
        lv.setAdapter(dude);

        name=getIntent().getStringExtra("name");
        int playerscore=getIntent().getIntExtra("scores", 0);
        String feedback = getIntent().getStringExtra("feedback");


        TextView scoreresult = (TextView)findViewById(R.id.scoresheader);
        firstpass=getIntent().getBooleanExtra("firstrnd", true);

        String fbs;
        if (firstpass)
            fbs="States";
        else
            fbs="Capitols";

        if(playerscore<3)
        {
            scoreresult.setText("\n\n ok " + name + ", we need to brush up on our " +
                    "US states facts" +
                    ", you got " + playerscore + " out of 5 possible right answers" +
                    "\n\n" + "These are the "+fbs+" you missed: \n\n" +
                    "" + feedback +
                    "");

        }

        else if (playerscore<5)
        {
            scoreresult.setText("\n\n OK, " + name + ", you got " + playerscore + " out of 5 " +
                    "possible right answers" +
                    "\n\n" + "These are the  "+fbs+"  you missed: \n\n" +
                    "" + feedback +
                    "");
        }
        else
        {
            scoreresult.setText("\n\nCongratulations "+name.toUpperCase()+"! You got a perfect " +
                    "score!!!!\n\n");
            scoreresult.setTextSize(17);
        }

    }
boolean firstpass=true;
    /*changes the boolean value 'firstpass' to let user decide how they would like
    * to play the game in the next screen*/
    public void onRadio(View view)
    {
        if (view.getId()==R.id.teststates)
        {
            firstpass=true;
        }
        else if (view.getId()==R.id.testcapitols)
        {
            firstpass=false;
        }
        else
        {
            firstpass=true;
        }

    }
    /*********************************************************************************/
    /*************************Method for buttons******************************/
    public void press(View view)
    {
        /*this button allows the player to play again under the same name and possibly
        * change his or her score (for better or worse)*/
        if(view.getId()==R.id.playagainbutton)
        {
            Intent intentagain = new Intent(getApplicationContext(), maingame.class);
            intentagain.putExtra("firstpass",firstpass);
            intentagain.putExtra("name",name);
            intentagain.putExtra("prev","scores");
            setResult(0, intentagain);
            finish();
            startActivity(intentagain);
        }
        /*this button allows the player to play under a completely different name
            * and thus make a new entry in the score table*/
        else if (view.getId()==R.id.newplayer)
        {
            Intent intentnew = new Intent(getApplicationContext(), Stategame.class);
            this.startActivity(intentnew);
        }
        /*this button clears the table*/
        else if (view.getId()==R.id.deleterecord)
        {
            db.delete("namesAndscoresAndtimes2",null,null);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scores, menu);
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
