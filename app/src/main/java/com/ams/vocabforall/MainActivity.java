package com.ams.vocabforall;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity {


    Button allwords, mastered, ambitious, randomtest;
    ImageButton info,links;
    static int a = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allwords = (Button) findViewById(R.id.randomtest);
        mastered = (Button) findViewById(R.id.mst);
        ambitious = (Button) findViewById(R.id.imageButton4);
        randomtest = (Button) findViewById(R.id.button);
        info = (ImageButton) findViewById(R.id.imageButton);
        links = (ImageButton) findViewById(R.id.imageButton2);



        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogbrand_layout);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                // set the custom dialog components - text and button
                Button dialogButton = dialog.findViewById(R.id.dialogButtonOK);

                TextView head = dialog.findViewById(R.id.textView5);
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");

                head.setTypeface(font);



                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.links);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                // set the custom dialog components - text and button
                Button email = (Button)dialog.findViewById(R.id.button8);
                Button rate = (Button)dialog.findViewById(R.id.button9);
                Button insta = (Button)dialog.findViewById(R.id.button10);

                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/wow.ttf");
                email.setTypeface(font);
                rate.setTypeface(font);
                insta.setTypeface(font);

                insta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("http://instagram.com/_u/easygre");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("com.instagram.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/easygre")));
                        }
                    }
                });

                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         /* Create the Intent */
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

/* Fill it with Data */
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"easy.gre.vocab@gmail.com"});
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Suggestions/Complaints");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "\nDevice name - " + Build.DEVICE + "\nDevice Manufacturer - " + Build.MANUFACTURER);

/* Send it off to the Activity-Chooser */
                        MainActivity.this.startActivity(Intent.createChooser(emailIntent, "Send mail VIA "));
                    }
                });

                rate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.ams.vocabforall&hl=en");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });



                dialog.show();
            }
        });



        allwords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allwords = new Intent(MainActivity.this, WordList.class);
                startActivity(allwords);


            }
        });

        mastered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mastered = new Intent(MainActivity.this, Mastered.class);
                startActivity(mastered);
            }
        });

        ambitious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mastered = new Intent(MainActivity.this, Assorted.class);
                startActivity(mastered);
            }
        });

        randomtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mastered = new Intent(MainActivity.this, Evaluate.class);
                startActivity(mastered);
            }
        });



//        font for buttons
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
        allwords.setTypeface(font);
        mastered.setTypeface(font);
        ambitious.setTypeface(font);
        randomtest.setTypeface(font);

        final Animation left2right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        final Animation right2left = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        final Animation top2bottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        final Animation bottom2top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);

        mastered.startAnimation(left2right);
        ambitious.startAnimation(right2left);
        allwords.startAnimation(top2bottom);
        randomtest.startAnimation(bottom2top);


    }



}
