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

/**
 * Created by Ams on 4/21/2017.
 */
public class AbTest extends Activity {

    String a, ques,key;

    TextView ques1, op;
    DataBaseHelper mydb = new DataBaseHelper(AbTest.this);
    int Mflag = 0, i = 0, noOfMasteredWords;
    Cursor rword;
    ArrayList<Integer> integers;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_test);


        ques1 = (TextView) findViewById(R.id.ques1);
        op = (TextView) findViewById(R.id.option1);
        Button Showmeaning = (Button) findViewById(R.id.showmeanng);
        Button nextword = (Button) findViewById(R.id.nextword);
        final ImageButton sear = (ImageButton) findViewById(R.id.sear);
        final RadioButton Dfav = (RadioButton) findViewById(R.id.mastered);

        Intent intent = getIntent();
        key = intent.getExtras().getString("keyWord");

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
        Showmeaning.setTypeface(font);
        nextword.setTypeface(font);
        integers = new ArrayList<>();
        rword = mydb.getmeaning(key);



        progress();
        result();


        Cursor masterFlag = mydb.getfav2(ques);
        if (masterFlag.moveToFirst()){
            if (masterFlag.getInt(2) == 1) {
                Mflag = 1;
            }

        }




//        Dfav.setChecked(true);
        Dfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Mflag == 0) {
                    Toast.makeText(AbTest.this, "" + ques + " is added to your Mastered List ", Toast.LENGTH_SHORT).show();
                    Dfav.setChecked(true);
                    Mflag = 1;
                    mydb.UpdateMastered(ques, 1);


                } else {

                    Toast.makeText(AbTest.this, "" + ques + " is removed from your Mastered List ", Toast.LENGTH_SHORT).show();
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

                if (Mflag == 1) {

                    Dfav.setChecked(true);



                } else {

                    Dfav.setChecked(false);


                }

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
                Cursor masterFlag = mydb.getfav2(ques);
                if (masterFlag.moveToFirst()){
                    if (masterFlag.getInt(2) == 1) {
                        Mflag = 1;
                    }else {

                        Mflag = 0;
                    }

                }


            }
        });

    }


    public int progress() {
        noOfMasteredWords = mydb.getCount(key);
//        Toast.makeText(MastertedTest.this, "You have Mastered Word "+ mw +" words" , Toast.LENGTH_SHORT).show();

        if (noOfMasteredWords == 0){

            Toast toast= Toast.makeText(getApplicationContext(),
                    "No Words from letter "+key, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            finish();

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

            final Dialog dialog = new Dialog(AbTest.this);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.reset);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialogright;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            Button reset = (Button) dialog.findViewById(R.id.reset);
            Button rd = (Button) dialog.findViewById(R.id.b3);
            TextView head = (TextView)dialog.findViewById(R.id.textView9);
            head.setText("All Done ! No more words from letter "+ key);
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
                    Intent rt = new Intent(AbTest.this, RandomTest.class);
                    rt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(rt);


                }
            });

            wl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent rt = new Intent(AbTest.this, WordList.class);
                    rt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(rt);

                }
            });






            dialog.show();



        }






    }



}
