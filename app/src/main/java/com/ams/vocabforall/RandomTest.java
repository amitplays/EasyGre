package com.ams.vocabforall;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Ams on 4/1/2017.
 */
public class RandomTest extends Activity {


    String a, ques;
    int Mflag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_test);
        final TextView op = (TextView) findViewById(R.id.option1);
//     TextView op2 = (TextView) findViewById(R.id.option2);
//     TextView op3 = (TextView) findViewById(R.id.option3);
//     TextView op4 = (TextView) findViewById(R.id.option4);


        final DataBaseHelper mydb = new DataBaseHelper(RandomTest.this);
        TextView ques1 = (TextView) findViewById(R.id.ques1);
        Button Showmeaning = (Button) findViewById(R.id.showmeanng);
        Button nextword = (Button) findViewById(R.id.nextword);
        final ImageButton sear = (ImageButton) findViewById(R.id.sear);
        final RadioButton Dfav = (RadioButton) findViewById(R.id.mastered);



        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
        Showmeaning.setTypeface(font);
        nextword.setTypeface(font);

        Cursor rword = mydb.getrandom();
        int i = 0;
        ArrayList<Integer> integers = new ArrayList<>();
        Random random = new Random();
        while (i < 4) {
            int rand = random.nextInt(4);
            if (!integers.contains(rand)) {

//                start
                if (rword.moveToPosition(rand)) {

                    if (rand == 0) {
                        a = (rword.getString(1));
                        ques = (rword.getString(0));
                        ques1.setText(ques);
                        op.setText(a.trim());
                    }

//                End
                    integers.add(rand);
                    i++;
                }
            }
        }


        Cursor masterFlag = mydb.getfav(ques);
        masterFlag.moveToFirst();

        if (masterFlag.getInt(0) == 1) {
            Mflag = 1;

        }

        Dfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Mflag == 0) {
                    Toast.makeText(RandomTest.this, "" + ques + " is added to your Mastered List ", Toast.LENGTH_SHORT).show();
                    Dfav.setChecked(true);
                    Mflag = 1;
                    mydb.UpdateMastered(ques, 1);


                } else {

                    Toast.makeText(RandomTest.this, "" + ques + " is removed from your Mastered List ", Toast.LENGTH_SHORT).show();
                    Mflag = 0;
                    mydb.UpdateMastered(ques, 0);
                    Dfav.setChecked(false);


                }


            }
        });

        sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://www.google.com/#q=define:" + ques + "");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });


        Showmeaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                op.setGravity(Gravity.CENTER);
                op.setVisibility(View.VISIBLE);
                sear.setVisibility(View.VISIBLE);
                Dfav.setVisibility(View.VISIBLE);

            }
        });

        nextword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });


    }

}
