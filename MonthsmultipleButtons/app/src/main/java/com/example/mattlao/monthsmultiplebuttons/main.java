package com.example.mattlao.monthsmultiplebuttons;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void doit(View v)
    {
        String[] months = getResources().getStringArray(R.array.months);
        String[] years = getResources().getStringArray(R.array.years);
        Button btn;
        Random ran= new Random();
        int r=ran.nextInt(255);
        int g=ran.nextInt(255);
        int b=ran.nextInt(255);


        int m = ran.nextInt(11);
        int n = ran.nextInt(10);

        String month=months[m];
        String year=years[n];


            if(v.getId()=="buttonMonth"){

            TextView txt = (TextView) findViewById(R.id.month);
            txt.setText(month);
            txt.setTextColor(Color.rgb(r, g, b));}


            if(v.getId()="buttonYear") {

            TextView txt = (TextView) findViewById(R.id.year);
                txt.setText(year);}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
