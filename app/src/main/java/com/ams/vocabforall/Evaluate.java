package com.ams.vocabforall;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ams on 3/31/2017.
 */
public class Evaluate extends Activity {


    DataBaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate);

        Button random = (Button)findViewById(R.id.randomtest);
        Button mst = (Button)findViewById(R.id.mst);
        Button alpha = (Button)findViewById(R.id.imageButton4);
        Button how = (Button)findViewById(R.id.button7);

        mydb = new DataBaseHelper(Evaluate.this);

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent random = new Intent(Evaluate.this, RandomTest.class);
                startActivity(random);
            }
        });

        mst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mydb.getProfilesCount(1) == 0){

                    Toast.makeText(Evaluate.this, " You haven't mastered any word yet " , Toast.LENGTH_SHORT).show();

                }else {
                    Intent random = new Intent(Evaluate.this, MastertedTest.class);
                    startActivity(random);
                }
                }

        });

        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent AT = new Intent(Evaluate.this, AlphabaticTest.class);
                startActivity(AT);


            }
        });

        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Evaluate.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.how);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                    dialog.setTitle("Title...");

                // set the custom dialog components - text and button
                TextView text = (TextView) dialog.findViewById(R.id.txt);
                text.setText("Random Test \nThis test will ask random words from the GRE WordList.\n\n Mastered Test\nThis test will ask words that you have mastered and are in your MasteredList.\n\n" +
                        "Alphabetic Test\nThis test will ask for the user to choose from a specific alphabet of which the user wants to take the test.");

                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
                text.setTypeface(font);

                dialog.show();


            }
        });




        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
        mst.setTypeface(font);
        random.setTypeface(font);
        alpha.setTypeface(font);

    }
}
