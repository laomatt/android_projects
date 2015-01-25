/*
* Author: Matthew Lao
* class: 211D
* Date: 9-31-14
* Title: HW#5: Circles
*
* Description:  This program draws circles onto a canvas in different positions
* and colors then clears them all.
* */

package com.example.mattlao.circles;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.graphics.*;
import android.content.Context;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.IOException;
import java.util.Random;

import static com.example.mattlao.circles.R.layout.activity_circles;

/*********************************************************/
/***************my setup method***************************/
public class circles extends Activity
{
    FrameLayout fla;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(activity_circles);
        fla=(FrameLayout)findViewById(R.id.mainframe);
    }


    @SuppressLint("WrongCall")
/***************my method to handle button actions**************/
    public void doit(View v) throws IOException
    {
        Random ran = new Random();
        int r = ran.nextInt(255);
        int g = ran.nextInt(255);
        int b = ran.nextInt(255);

        int radius = ran.nextInt(100);
        int x = ran.nextInt(1000);
        int y = ran.nextInt(700);



        Bitmap bmc = Bitmap.createBitmap(1000, 700, Bitmap.Config.ARGB_8888);
        Context cx = getApplicationContext();
        switch(v.getId())
        {

            case R.id.hitbutton:

                DrawCircle dc = new DrawCircle(cx, r, g, b, x, y, radius);
                fla.addView(dc);


                break;

            case R.id.clearbutton:

                DrawRect dr = new DrawRect(cx);
                fla.addView(dr);

                break;

        }
        BitmapDrawable bit = new BitmapDrawable(getResources(), bmc);

        ImageView iv = (ImageView) findViewById(R.id.mainwindow);
        iv.setImageDrawable(bit);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.circles, menu);
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
/***************my class to draw a white rectangle to clear the canvas**************/
class DrawRect extends View
{

    public DrawRect(Context con)
    {
        super(con);
    }
    /***************my method to draw a white rectangle to clear the canvas**************/
    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);

        c.drawRect(0,0,2000,2000,p);

    }



}

/******************************************/
/*********my class to draw a circle******/
class DrawCircle extends View
{

    int g;
    int b;
    int x;
    int y;
    int r;


    public DrawCircle(Context con, int red, int green, int blue, int ex, int ey, int radius)
    {
        super(con);
        r=red;
        g=green;
        b=blue;
        x=ex;
        y=ey;
        r=radius;



    }

/******************************************/
    /*********my method to draw a circle******/
    protected void onDraw(Canvas c)
    {
        super.onDraw(c);
        Paint p = new Paint();

        p.setColor(Color.rgb(r,g,b));
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(x,y,r,p);

    }


}


