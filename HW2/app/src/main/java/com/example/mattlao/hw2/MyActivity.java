package com.example.mattlao.hw2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.Random;
import android.widget.TextView;


public class MyActivity extends Activity
{
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        btn = (Button)findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatetext();
            }
        });


      //  setContentView(R.layout.main);

    }

    public void updatetext()
    {
        Random ran= new Random();
        int r = ran.nextInt(255);
        int g = ran.nextInt(255);
        int b = ran.nextInt(255);
        Color.rgb(r, g, b);
        String atxt[] = getResources().getStringArray(R.array.months);
        String txt = atxt[ran.nextInt(11)];
        //findViewById(
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
       //  Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.my, menu);
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

    @Override
    public void onClick(View view) {

    }
}
