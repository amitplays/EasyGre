package com.ams.vocabforall;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MastertedTest extends Activity {

    String a, ques;
    TextView ques1, op;
    DataBaseHelper mydb = new DataBaseHelper(MastertedTest.this);
    int Mflag = 1, i = 0, noOfMasteredWords;
    Cursor rword;
    ArrayList<Integer> integers;
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.masterted_test);
        ques1 = (TextView) findViewById(R.id.ques1);
        op = (TextView) findViewById(R.id.option1);
        Button Showmeaning = (Button) findViewById(R.id.showmeanng);
        Button nextword = (Button) findViewById(R.id.nextword);
        final ImageButton sear = (ImageButton) findViewById(R.id.sear);
        final RadioButton Dfav = (RadioButton) findViewById(R.id.mastered);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
        Showmeaning.setTypeface(font);
        nextword.setTypeface(font);
        integers = new ArrayList<>();
        rword = mydb.getMastered(1);

        progress();
        result();

        Cursor masterFlag = mydb.getfav(ques);
        if (masterFlag.moveToFirst()){
            if (masterFlag.getInt(0) == 1) {
                Mflag = 1;
            }

        }




        Dfav.setChecked(true);
        Dfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Mflag == 0) {
                    Toast.makeText(MastertedTest.this, "" + ques + " is added to your Mastered List ", Toast.LENGTH_SHORT).show();
                    Dfav.setChecked(true);
                    Mflag = 1;
                    mydb.UpdateMastered(ques, 1);


                } else {

                    Toast.makeText(MastertedTest.this, "" + ques + " is removed from your Mastered List ", Toast.LENGTH_SHORT).show();
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

                ++i;
                Dfav.setChecked(true);
                sear.setVisibility(View.INVISIBLE);
                Dfav.setVisibility(View.INVISIBLE);
                op.setVisibility(View.GONE);
                result();


            }
        });

    }


    public int progress() {
        noOfMasteredWords = mydb.getProfilesCount(1);
//        Toast.makeText(MastertedTest.this, "You have Mastered Word "+ mw +" words" , Toast.LENGTH_SHORT).show();

        if (noOfMasteredWords == 0){

            Toast toast= Toast.makeText(getApplicationContext(),
                    "You have not Mastered any word ", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

        }


        return noOfMasteredWords;
    }

    public void result() {

        if (i < noOfMasteredWords){

            int rand = random.nextInt(noOfMasteredWords);
            if (!integers.contains(rand)) {

                if (rword.moveToPosition(rand)) {

                    a = (rword.getString(1));
                    ques = (rword.getString(0));
                    ques1.setText(ques);
                    op.setText(a.trim());

//                End
                    integers.add(rand);
//                start

                }
            } else {
//                Toast.makeText(MastertedTest.this, " random_test else " , Toast.LENGTH_SHORT).show();
                result();


            }

        } else{

            final Dialog dialog = new Dialog(MastertedTest.this);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.reset);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialogright;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            Button reset = (Button) dialog.findViewById(R.id.reset);
            Button rd = (Button) dialog.findViewById(R.id.b3);
            Button wl = (Button) dialog.findViewById(R.id.b4);



            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                }
            });

            rd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent rt = new Intent(MastertedTest.this, RandomTest.class);
                    rt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(rt);


                }
            });

            wl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent rt = new Intent(MastertedTest.this, WordList.class);
                    rt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(rt);

                }
            });




            dialog.show();



        }


        }




    }

