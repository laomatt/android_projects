/*
* Author: Matthew Lao
* class: 211D
* Date: 9-24-14
* Title: Slide show
*
* Description:  This program Cycles through my portfolio images in the
* drawable folder in a slide show and
* displays them on screen by pressing a next button and a previous button.
* */



package com.example.mattlao.slideshow;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import static com.example.mattlao.slideshow.R.*;
import static com.example.mattlao.slideshow.R.drawable.android_cover;


public class SlideShow extends Activity {
    /*first we set up our image array by first declaring one in class scope and then setting it
    * up in the onCreate method*/
    Drawable[] drarray = new Drawable[3];

/***************************************************************************************/
/*****************************This method sets up the app*****************************/
/**************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //here we omit the placement of the app title on the main screen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout.activity_slide_show);
        //setting up my image array
        drarray[0]=getResources().getDrawable(drawable.android_cover);
        drarray[1]=getResources().getDrawable(drawable.android_ridinghood);
        drarray[2]=getResources().getDrawable(drawable.android_teaching);

    }

    //we must declare the counter-variable in class-scope
    int i = 0;


/***********************************************************************************************/
/********************this method handles the operations of by buttons***************************/
/***********************************************************************************************/
    public void button(View v)
    {
        ImageView iv = (ImageView)findViewById(id.pictureWindow);
        /*using a switch operator we assign each button the task of either incrementing
        our counter-variable by 1, or decrementing it by 1*/
        switch (v.getId())
        {
            case id.NextButton:
                i += 1;
                                    break;
            case id.PrevButton:
                i -= 1;
                                    break;
        }
        //use a modulo operator to keep it cycling indices in the array
        int j=i%(drarray.length);

        //then set the imageview window to this new moded index
        iv.setImageDrawable(drarray[j]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slide_show, menu);
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
