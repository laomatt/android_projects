package com.example.mattlao.drawme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class startscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);


        GridView pics = (GridView) findViewById(R.id.pictures);

        Intent in = new Intent(Intent.ACTION_GET_CONTENT);

        in.addCategory(Intent.CATEGORY_OPENABLE);
        in.setType("image/*");

        startActivity(in);


        int i=0;
        while(in.getData().isAbsolute()){

            thumbs[i]=in.getData().getPort();


            i++;

        }

         pics.setAdapter(new ImageAdapter(this));
    }


    public void pressed(View view) {
/*
        if (view.getId() == R.id.populatepics) {
            Intent in = new Intent(Intent.ACTION_OPEN_DOCUMENT);

            in.addCategory(Intent.CATEGORY_OPENABLE);
            in.setType("image/*");

            startActivity(in);


        }

*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.startscreen, menu);
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


    private int[] thumbs;

/*inner class for the image adapter*/
    class ImageAdapter extends BaseAdapter {
        private Context mContext;


        public ImageAdapter(Context c) {
            mContext = c;
        }


        @Override
        public int getCount() {
            return thumbs.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);}

            else
            {
                imageView = (ImageView)convertView;

            }

            imageView.setImageResource(thumbs[position]);

                return imageView;
        }





    }
}