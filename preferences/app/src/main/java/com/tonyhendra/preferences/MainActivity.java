package com.tonyhendra.preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnSimplePref;
    Button btnFancyPref;
    TextView txtCaption1;
    Boolean fancyPrefChosen = false;
    final int mode = Activity.MODE_PRIVATE;
    final String MYPREFS = "MyPreferences_001";
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor myEditor;
    View vLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCaption1 = (TextView) findViewById(R.id.txtCaption1);
        btnFancyPref = (Button) findViewById(R.id.btnPrefFancy);
        btnSimplePref = (Button) findViewById(R.id.btnPrefSimple);
        vLayout = (View) findViewById(R.id.activity_main);

        txtCaption1.setText("This is a sample line \n"
                + "suggesting the way the UI looks \n"
                + "after you choose your preference");
        // create a reference & editor for the shared preferences object
        mySharedPreferences = getSharedPreferences(MYPREFS, 0);
        myEditor = mySharedPreferences.edit();
        // has a Preferences file been already created?
        if (mySharedPreferences != null && mySharedPreferences.contains("backColor")) {
            // object and key, show all saved value
            applySavedPreferences();
        } else {
            Toast.makeText(getApplicationContext(), "No Preferences found", Toast.LENGTH_SHORT).show();
        }

        btnSimplePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myEditor.clear();
                myEditor.putInt("backColor", Color.BLACK);
                myEditor.putInt("textSize", 12);
                myEditor.commit();
                applySavedPreferences();
            }
        });
        btnFancyPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myEditor.clear();
                myEditor.putInt("backColor", Color.BLUE); // fancy blue
                myEditor.putInt("textSize", 20); // fancy big
                myEditor.putString("textStyle", "bold"); // fancy bold
                myEditor.putInt("layoutColor", Color.GREEN);//fancy green
                myEditor.commit();
                applySavedPreferences();
            }
        });
    }

    @Override
    protected void onPause() {
        // warning: activity is on its last state of visibility!.
        // It s on the edge of been killed! Better save all current
        // state data into Preference object (be quick!)
        myEditor.putString("DateLastExecution", new Date().toLocaleString());
        myEditor.commit();
        super.onPause();
    }

    public void applySavedPreferences() {
        // extract the <key/value> pairs, use default param for missing data
        int backColor = mySharedPreferences.getInt("backColor",Color.BLACK);
        int textSize = mySharedPreferences.getInt("textSize", 12);
        String textStyle = mySharedPreferences.getString("textStyle", "normal");
        int layoutColor = mySharedPreferences.getInt("layoutColor",Color.DKGRAY);
        String msg = "color " + backColor + "\n"
                + "size "
                + textSize
                + "\n"
                + "style " + textStyle;
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        txtCaption1.setBackgroundColor(backColor);
        txtCaption1.setTextSize(textSize);
        if (textStyle.compareTo("normal")==0){
                txtCaption1.setTypeface(Typeface.SERIF, Typeface.NORMAL);
            }
            else {
                txtCaption1.setTypeface(Typeface.SERIF,Typeface.BOLD);
            }
            vLayout.setBackgroundColor(layoutColor);
        }// applySavedPreferences
}
