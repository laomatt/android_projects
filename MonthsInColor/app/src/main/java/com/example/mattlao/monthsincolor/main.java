/*
* Author: Matthew Lao
* Class: 211D
* title: Homework 2
*
* this program creates a button that displays the month of the year in a different color everytime it is pushed
*
* */

package com.example.mattlao.monthsincolor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class main extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }



     public void doit(View v)
     {
            String[] months = getResources().getStringArray(R.array.months);
            Button btn;
            Random ran= new Random();
            int r=ran.nextInt(255);
            int g=ran.nextInt(255);
            int b=ran.nextInt(255);


            int m = ran.nextInt(11);

            String month=months[m];

            TextView txt=(TextView)findViewById(R.id.textfield);
            txt.setText(month);
            txt.setTextColor(Color.rgb(r,g,b));

     }






    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

