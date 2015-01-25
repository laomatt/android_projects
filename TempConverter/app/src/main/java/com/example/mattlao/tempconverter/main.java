
/*
* Author: Matthew Lao
* class: 211D
* Date: 9-14-14
*
* Description:  This program converts user input from
* Fahrenheit to Celsius, and back by use of
* radio buttons, and textview, and text
 * edits fields and a button.
*
* */

package com.example.mattlao.tempconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.NumberFormat;
/**********************************************************************************/
/*This method is for the startup and initialization operations performed by the app***/
/*********************************************************************************/

public class main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //here we omit the placement of the app title on the main screen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    double result;
    String operation="";

    /*****************************************************************************/
    /*This method is for the actions performed when radio buttons are selected****/
    /*****************************************************************************/


    public void onRadioButtonClick(View v)
    {
        boolean checked = ((RadioButton)v).isChecked();
        //this switch statement takes decides, based on the ID of the radio button
        //checked, what to assign to the 'operation' string value, which then in
        //turn decides how the input will be converted. (Fahrenheit to Celsius or
        // Celsius to Fahrenheit)

        switch(v.getId())
        {
            case R.id.CeltoFar:   if(checked){
                                              operation = "celfar";

                                             };break;
            case R.id.FarToCel:   if(checked){
                                              operation = "farcel";

                                             };break;
        }
    }


    /********************************************************************************/
    /**********This method is for the button that the converting button performs*****/
    /********************************************************************************/


    public void doit(View v)
    {
        //we first find the input textedit box.
        EditText in = (EditText) findViewById(R.id.input);

        //we then take the String value in that box, via the getText method.
        String inp = in.getText().toString();

        //our input must then be converted to a double value in order to be
        // used in arithmetic.
        double input = Double.parseDouble(inp);

        //our output strings will be changed depending on which function we run.
        String str1="";
        String str2="";
        String str3="";

        //out1-3 is just the textview boxes that will make up the output.
        TextView out1 = (TextView) findViewById(R.id.output1);
        TextView out2 = (TextView) findViewById(R.id.output2);
        TextView out3 = (TextView) findViewById(R.id.output3);
        //this is to format our output temperature
        NumberFormat formatter = new DecimalFormat("#0.00");

        //based on the operation string value, this if-if statement will decide
        //what action to carry out, and assign to the String value RES, which
        //is what is displayed in the output textviews.
        if(operation.equals("celfar"))
        {
            //here we apply the conversion formula from Celsius to Fahrenheit
            result = ((input+32)*9)/5;
            //then we translate the double value we get to a String
            // value to be read in the output
            str1 = inp+" degrees Celsius is ";
            str2 = formatter.format(result);
            str3=" degrees Fahrenheit";
        }

        if(operation.equals("farcel"))
        {
            //here we apply the conversion formula from Fahrenheit to Celsius
            result = ((input-32)*5)/9;
            //then we translate the double value we get to a
            // String value to be read in the output
            str1 = inp+" degrees Fahrenheit is ";
            str2 = formatter.format(result);
            str3 = " degrees Celsius";
        }




        //via the setText method we set the output
        // text to our output textviews value.
        out1.setText(str1);
        out2.setText(str2);
        out3.setText(str3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the
        // action bar if it is present.
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
