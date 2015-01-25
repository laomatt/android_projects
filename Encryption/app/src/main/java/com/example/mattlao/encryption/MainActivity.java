package com.example.mattlao.encryption;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.EmptyStackException;
import java.util.StringTokenizer;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText key = (EditText)findViewById(R.id.key);
        EditText in = (EditText)findViewById(R.id.input);

    }

    int k;
    public void press(View view) {
        {
            if(view.getId()==R.id.encypt)
            {
                EditText key = (EditText) findViewById(R.id.key);
                EditText in = (EditText) findViewById(R.id.input);
                String input = in.getText().toString();

                k = Integer.valueOf(key.getText().toString());

                String[] temp = new String[input.length()];
                int[] output = new int[input.length()];
                int i = 0;
                while (i < input.length()) {
                    temp[i] = ""+input.charAt(i);
                    i++;
                }

               // for (char g : temp)
                 i=0;
                while (i<input.length())
                {
                    String gat=temp[i];

                    switch (gat.charAt(0))
                    {
                        case 'a':
                            output[i] = 12 * k;
                            break;
                        case 'b':
                            output[i] = 23 * k;
                            break;
                        case 'c':
                            output[i] = 34 * k;
                            break;
                        case 'd':
                            output[i] = 45 * k;
                            break;
                        case 'e':
                            output[i] = 54 * k;
                            break;
                        case 'f':
                            output[i] = 32 * k;
                            break;
                        case 'g':
                            output[i] = 67 * k;
                            break;
                        case 'h':
                            output[i] = 92 * k;
                            break;
                        case 'i':
                            output[i] = 15 * k;
                            break;
                        case 'j':
                            output[i] = 24 * k;
                            break;
                        case 'k':
                            output[i] = 123 * k;
                            break;
                        case 'l':
                            output[i] = 567 * k;
                            break;
                        case 'm':
                            output[i] = 285 * k;
                            break;
                        case 'n':
                            output[i] = 198 * k;
                            break;
                        case 'o':
                            output[i] = 329 * k;
                            break;
                        case 'p':
                            output[i] = 517 * k;
                            break;
                        case 'q':
                            output[i] = 321 * k;
                            break;
                        case 'r':
                            output[i] = 275 * k;
                            break;
                        case 's':
                            output[i] = 9235 * k;
                            break;
                        case 't':
                            output[i] = 2974 * k;
                            break;
                        case 'u':
                            output[i] = 9898 * k;
                            break;
                        case 'v':
                            output[i] = 1253 * k;
                            break;
                        case 'w':
                            output[i] = 94382 * k;
                            break;
                        case 'x':
                            output[i] = 83458 * k;
                            break;
                        case 'y':
                            output[i] = 12432 * k;
                            break;
                        case 'z':
                            output[i] = 95483 * k;
                            break;


                    }
                    i++;

                }

                TextView tv = (TextView) findViewById(R.id.output);
                String tri="";

                for(int g:output)
                    tri +=g+" ";

               tv.setText(tri.trim());

            }


            else if(view.getId()==R.id.decypt)
            {
                String out="";
                TextView tv = (TextView)findViewById(R.id.output);
                EditText ed = (EditText)findViewById(R.id.input);
                String in = ed.getText().toString();

                StringTokenizer st = new StringTokenizer(in, " ");
                int y=0;
                Integer[] temp = new Integer[st.countTokens()];
                while(st.hasMoreTokens())
                {
                    temp[y]=Integer.parseInt(st.nextToken());
                    y++;

                }

                for(int f:temp)
                {


                    int tem=f/k;

                    switch(tem)
                    {
                        case 12:
                            out+="z";
                            break;
                        case 23:
                            out+="z";
                            break;
                        case 34:
                            out+="z";
                            break;
                        case 45:
                            out+="z";
                            break;
                        case 54:
                            out+="z";
                            break;
                        case 32:
                            out+="z";
                            break;
                        case 67:
                            out+="z";
                            break;
                        case 92:
                            out+="z";
                            break;
                        case 15:
                            out+="z";
                            break;
                        case 24:
                            out+="z";
                            break;
                        case 123:
                            out+="z";
                            break;
                        case 567:
                            out+="z";
                            break;
                        case 285:
                            out+="z";
                            break;
                        case 198:
                            out+="z";
                            break;
                        case 329:
                            out+="z";
                            break;
                        case 517:
                            out+="z";
                            break;
                        case 321:
                            out+="z";
                            break;
                        case 275:
                            out+="z";
                            break;
                        case 9235:
                            out+="z";
                            break;
                        case 2974:
                            out+="z";
                            break;
                        case 9898:
                            out+="z";
                            break;
                        case 1253:
                            out+="z";
                            break;
                        case 94382:
                            out+="z";
                            break;
                        case 83458:
                            out+="z";
                            break;
                        case 12432:
                            out+="z";
                            break;
                        case 95483:
                            out+="z";
                            break;
                        case 0:
                            out+=" ";
                            break;

                    }

                }

                tv.setText(out);

               //int[] temp=st.to;

               // for(int f:)


            }

            else if (view.getId()==R.id.switchbutton)
            {
                TextView tv = (TextView)findViewById(R.id.output);
                EditText ed = (EditText)findViewById(R.id.input);

                ed.setText(tv.getText().toString());
                ed.setText("");

            }
        }

    }
    private String decrpt(String input)
    {



        String output="";


        return output;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
