
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

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;


public class maingame extends Activity
{
    Random r = new Random();
    SQLiteDatabase db;
    ArrayList<String> states=new ArrayList();
    ArrayList<String> capitols=new ArrayList();
    ArrayList<Integer> drawn= new ArrayList();
    ArrayList<String> drawlist= new ArrayList();
    //this creates an array of hash tables
    int s1,s2,s3,s4,s5;
    HashMap<String, String> data;
    TextView timer;
    boolean timeend=true;

    @Override
    /*********************************************************************************/
    /******************************* creates everything*******************************/
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_maingame);
        timer = (TextView)findViewById(R.id.timer);
        TextView instruct = (TextView)findViewById(R.id.instructions);

        /* this is the boolean value to decide which game we are playing guessing states
         * or guessing capitols */
        firstpass = getIntent().getBooleanExtra("firstpass", true);
        data = new HashMap<String, String>();
        if (firstpass)
        instruct.setText("What are the capitols of each state?");
        else
        instruct.setText("To what states to each capitol belong to?");



        new Timer().execute();


/*populates the hash map for the questions and answers
* then enters it into my array of hash maps,  the keys are "state" and "capitol"*/
        InputStream is = getResources().openRawResource(R.raw.states);
        String line;
        Scanner sc = new Scanner(is);
        while (sc.hasNext())
        {
            //here we split up the line with an appropriate delimiter
            line=sc.nextLine();
            String[] tokens = line.split("        +");
            /*
            if we are in our first game then the hash key is the state, and the drawlist
            array list needs to be populated with the state name
             * if we are in out second game then the hash key is the capitol. and the
             drawlist needs to be populated with the capital names
             */
            if(firstpass)
            {
                data.put(tokens[0], tokens[1]);
                drawlist.add(tokens[0]);
            }
            else
            {
                data.put(tokens[1], tokens[0]);
                drawlist.add(tokens[1]);
            }

        }
        sc.close();
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] drawnumbers ={s1,s2,s3,s4,s5};
        /*this selects unique numbers to choose from the data arrays*/
        for(int h:drawnumbers)
        {
            boolean in=true;
            while (in)
            {
                h = 2 + r.nextInt(49);
                if (drawn.contains(h))
                {
                    in=true;

                }
                else
                {
                    in=false;
                    drawn.add(h);
                }
            }
        }

        stat1 = (TextView)findViewById(R.id.state1);
        stat2 = (TextView)findViewById(R.id.state2);
        stat3 = (TextView)findViewById(R.id.state3);
        stat4 = (TextView)findViewById(R.id.state4);
        stat5 = (TextView)findViewById(R.id.state5);

        stats = new TextView[5];
        stats[0]=stat1;
        stats[1]=stat2;
        stats[2]=stat3;
        stats[3]=stat4;
        stats[4]=stat5;

        int i=0;
        for (TextView tf:stats)
        {
            tf.setText(drawlist.get(drawn.get(i)));
            i+=1;
        }


        String player_name=getIntent().getStringExtra("name");
        name=player_name;

        TextView tv = (TextView)findViewById(R.id.test);
        tv.setText(name);
        tv.setTextSize(24);
        tv.setTextColor(Color.BLUE);

        TextView namemessage =(TextView)findViewById(R.id.submitmessage);
        namemessage.setText("Ok, "+player_name+". lets see how you did!  Hit the submit button! ");

//here is where we animate the little bear waiting for the user to input their answers
        ImageView waitview = (ImageView)findViewById(R.id.waitpicture);
        waitview.setBackgroundResource(R.drawable.waiting);
        AnimationDrawable bearwaiting = (AnimationDrawable)waitview.getBackground();
        bearwaiting.start();
    }
    TextView stat1,stat2,stat3,stat4,stat5;
    TextView[] stats;
    String name="";
    Intent intent=getIntent();
    boolean firstpass;
    String timestring;

/*********************************************************************************/
/**************this method handles my buttons for this activity****************/
    public void press(View view)
    {
        if (view.getId()==R.id.submit) {
            EditText ans1 = (EditText)findViewById(R.id.st1ans);
            EditText ans2 = (EditText)findViewById(R.id.st2ans);
            EditText ans3 = (EditText)findViewById(R.id.st3ans);
            EditText ans4 = (EditText)findViewById(R.id.st4ans);
            EditText ans5 = (EditText)findViewById(R.id.st5ans);
            EditText[] ans={ans1, ans2, ans3, ans4, ans5};
            int count=0;
            int number=0;
            String feed="";

            //this loop tallys up all of our correct answers and then builds
            // feedback for wrong answers to give to
            //the user, using our data hashmap
                for (EditText y : ans)
                {
                    String temp=y.getText().toString();
                if(temp.trim().equalsIgnoreCase(data.get(stats[number%stats.length].getText().toString())))
                    {
                        count += 1;
                    } else {
                        if (firstpass)
                        {
                            feed += "         The capitol of  " +
                                    drawlist.get(drawn.get(number)) + " is " +
                                    data.get(drawlist.get(drawn.get(number))) + "  " + "\n";
                        }
                        else
                        {
                            feed += "          "+drawlist.get(drawn.get(number))+
                                    " is the capitol of "+
                                    data.get(drawlist.get(drawn.get(number))) + "  " + "\n";
                        }
                    }

                    number += 1;

                }

            timeend=false;
            Intent submit = new Intent(getApplicationContext(), scores.class);
            submit.putExtra("scores",count);
            submit.putExtra("feedback",feed);
            submit.putExtra("name",name);
            submit.putExtra("firstrnd",firstpass);
            submit.putExtra("timeittook",totaltime); //int value
            //this is where we input the score into the sql table
            db = openOrCreateDatabase("namesAndscoresAndtimes2", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            ContentValues dv = new ContentValues();
            dv.put("score", count);
            dv.put("time", time);
            float perc=(100*count/(totaltime+1));
            dv.put("percentile", perc);
            //ContentValues dt = new ContentValues();

            db.update("namesAndscoresAndtimes", dv,"name=?",new String[] {name});


            this.startActivity(submit);

        }
        else if (view.getId()==R.id.backbutton)
        {
            String prev=getIntent().getStringExtra("prev");
            //default value for my back button intent
            Intent intentback=new Intent(getApplicationContext(), Stategame.class);;
            //but if  previous activity was the scores page
            // the intent changes to lead to the scores page.
            if (prev.equals("scores"))
                intentback = new Intent(getApplicationContext(), scores.class);

            this.startActivity(intentback);
        }
    }
    String time;
    int totaltime;
    /*now here is my inner class for my timer thread*/
    public class Timer extends AsyncTask<Integer, Integer, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            timer.setText("00:00:00");
            timeend=true;
        }

        protected Void doInBackground(Integer... params)
        {
            int i=0;
            while(timeend){
                publishProgress(i);
                SystemClock.sleep(1000);
                totaltime=i;
                i++;
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer...args)
        {
            int seconds=args[0]%60;
            int minutes=(args[0]/60)%60;
            int hours=args[0]/3600;
            time=""+String.format("%02d",hours)+":"+String.format("%02d",minutes)+":"+String.format("%02d",seconds);
            timer.setText(time);
            super.onProgressUpdate();

        }
        @Override
        protected void onPostExecute(Void result)
        {
            timer.setText("");
            super.onPostExecute(result);

        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maingame, menu);
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
