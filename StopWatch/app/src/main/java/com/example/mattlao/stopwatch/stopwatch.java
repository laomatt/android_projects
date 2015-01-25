package com.example.mattlao.stopwatch;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;


public class stopwatch extends Activity {
    Cursor c;
    SQLiteDatabase db;
    boolean timeend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stopwatch);
        timeend=false;
        timer=(TextView)findViewById(R.id.timer);
        timer2=(TextView)findViewById(R.id.timerlap);


        settable();
    }


    public void settable()
    {

        db = openOrCreateDatabase("timesandname4", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        db.execSQL("create table if not exists times(_id integer primaryKey auto increment, number number, time text, numholder);");

        c=db.query("times",null, null, null, null, null, "number Desc", null);

        String[] fromCols={"number", "time"};
        int[] toViews={R.id.idnumber, R.id.timetaken};

        SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.timerlist, c, fromCols, toViews, 0);

        ListView lv = (ListView)findViewById(R.id.timerlastlist);
        lv.setAdapter(sca);
    }


int k=0;
int r=0;
int u=1;
Timer2 tt;
    public void press(View view){
        ImageView im = (ImageView)findViewById(R.id.startstopbutton);

        if (view.getId()==R.id.startstopbutton)
        {
            if (timeend)
            {
                im.setImageResource(R.drawable.startb);
                timeend = false;




            }
            else
            {
                db = openOrCreateDatabase("timesandname4", SQLiteDatabase.CREATE_IF_NECESSARY, null);

                db.delete("times",null,null);
                settable();

                Timer t = new Timer(k,r);
                tt = new Timer2(0,0);
                im.setImageResource(R.drawable.stopb);
                t.execute();
               // tt.execute();


            }


        }

        else if (view.getId()==R.id.resetbutton)
        {
            k=0;
            msecs=0;
            timer.setText("00:00:00");
            ms = (TextView)findViewById(R.id.milisec);
            ms.setText("00");


        }
        else if(view.getId()==R.id.savebutton)
        {
            Calendar c = Calendar.getInstance();
          //  db = openOrCreateDatabase("timesandname4", SQLiteDatabase.CREATE_IF_NECESSARY, null);
          //  db.execSQL("create if not exists timeRecords()");
          //  db.execSQL("insert into timeRecords(number, time, date) values('"+u+"','"+time+"::"+msecs+"');");

        }


        else if (view.getId()==R.id.lap)
        {


            db = openOrCreateDatabase("timesandname4", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL("insert into times(number, time) values('"+u+"','"+time+":"+msecs+"');");

       //     timer2.setText("00:00:00");
      //      tt.i=0;
     //       tt.f=0;
 //           tt.cancel(true);
//            tt.execute(0,0);

            u++;
            settable();

        }

        else if (view.getId()==R.id.cleattable)
        {

            db = openOrCreateDatabase("timesandname4", SQLiteDatabase.CREATE_IF_NECESSARY, null);

            db.delete("times",null,null);
            settable();


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stopwatch, menu);
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
    TextView timer;
    TextView timer2;
    String time;
    int totaltime;
    TextView ms;
    int msecs;

    /***********************************************************************************************/
    /***********************************************************************************************/
    /***********************************************************************************************/
    /*now here is my inner class for my timer thread*/
    public class Timer extends AsyncTask<Integer, Integer, Void> {
        int seconds;
        int minutes;
        int hours;
       // milisecs milli=new milisecs();
        int i=0;
        int f=0;
        public Timer(int g, int m)
        {

            i=g;
            f=m;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            timer.setText(time);
            timeend=true;
        }

        protected Void doInBackground(Integer... params) {
            while (timeend) {
                publishProgress(i);
                SystemClock.sleep(100);
                totaltime = i;
                i++;

            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... args) {
             msecs=args[0]%10;
             seconds = (args[0]/10) % 60;
             minutes = (args[0] / 600) % 60;
             hours = args[0] / 36000;
            time = "" + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);

            TextView ms = (TextView)findViewById(R.id.milisec);
            ms.setText(""+String.format("%02d",msecs));
            timer.setText(time);
            super.onProgressUpdate();

        }

        @Override
        protected void onPostExecute(Void result) {
            k=i;
            r=f;
            timer.setText(time);
            super.onPostExecute(result);


        }

    }

    /***********************************************************************************************/
    /***********************************************************************************************/

    public class Timer2 extends AsyncTask<Integer, Integer, Void> {
        int seconds;
        int minutes;
        int hours;
        // milisecs milli=new milisecs();
        int i=0;
        int f=0;
        boolean timeend2;
        String time2;
        public Timer2(int g, int m)
        {
            i=g;
            f=m;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            timer2.setText(time);
            timeend2=true;
        }

        protected Void doInBackground(Integer... params) {
            while (timeend2) {
                publishProgress(i);
                SystemClock.sleep(100);
                i++;

            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... args) {
            msecs=args[0]%10;
            seconds = (args[0]/10) % 60;
            minutes = (args[0] / 600) % 60;
            hours = args[0] / 36000;
            time2 = "" + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);


            TextView ms = (TextView)findViewById(R.id.milisec2);
            ms.setText(""+String.format("%02d",msecs));
            timer2.setText(time2);
            super.onProgressUpdate();

        }

        @Override
        protected void onPostExecute(Void result) {
            k=i;
            r=f;
            timer2.setText("00:00:00");
            ms.setText("00");
            super.onPostExecute(result);


        }

    }
    /***********************************************************************************************/

}
