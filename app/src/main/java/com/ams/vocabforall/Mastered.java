package com.ams.vocabforall;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ams on 3/18/2017.
 */
public class Mastered extends Activity {

    DataBaseHelper mydb;
    ListView masteredList;
    ImageButton reset;
    TextView head;
    String word;
    int mw;

    ArrayList<MasteredGetterSetter> WordMeaning;
    int key = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mastered);
        mydb = new DataBaseHelper(Mastered.this);
        head = (TextView)findViewById(R.id.index);
        Button how = (Button)findViewById(R.id.button4);
        masteredList = (ListView) findViewById(R.id.masteredlist);
        reset = (ImageButton)findViewById(R.id.reset);
        Typeface font = Typeface.createFromAsset(head.getContext().getAssets(), "fonts/thin.otf");
        head.setTypeface(font);
        getdata();
        progress();


        masteredList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                String meaning = WordMeaning.get(i).getWord();
                word = WordMeaning.get(i).getMeaning();



//                AlertDialog.Builder(Mastered.this, AlertDialog.THEME_HOLO_DARK)

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Mastered.this);

                builder1.setMessage("Do you want to remove " + word.toUpperCase() + " from your Mastered Word-List ?");
                builder1.setCancelable(true);


                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                mydb.UpdateMastered(word, 0);
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }


        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Mastered.this);
                builder1.setMessage("Do you want to Reset your Mastered Word-List");
                builder1.setCancelable(true);


                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                mydb.ResetMastered(0);
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Mastered.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.how);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                    dialog.setTitle("Title...");

                // set the custom dialog components - text and button
                TextView text = (TextView) dialog.findViewById(R.id.txt);
                text.setText("This list contains the word that you have already mastered. \n\nTo master any word tap go to the word in the Wordlist and click on the round shape icon.\n\nTo remove any word from this list, just tap on the word.\n\nTo REST the list tap on the Reset-Button on the top left corner. ");
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
                text.setTypeface(font);
                dialog.show();

            }
        });




    }

    public void getdata() {
        WordMeaning = new ArrayList<>();
        Cursor words = mydb.getMastered(key);
        if (words.getCount() == 0) {

            Toast.makeText(Mastered.this, "No Mastered Word " , Toast.LENGTH_SHORT).show();
                     return;
        }
        while (words.moveToNext()) {

            MasteredGetterSetter per2 = new MasteredGetterSetter();
            per2.setWord(words.getString(1));           // this is word
            per2.setMeaning(words.getString(0));        // this is meaning
            WordMeaning.add(per2);

            AdapterMastered adapter2 = new AdapterMastered(Mastered.this, WordMeaning);
            masteredList.setAdapter(adapter2);
        }

    }
    public int progress(){
        mw = mydb.getProfilesCount(key);
        Toast.makeText(Mastered.this, "You have Mastered Word " + mw + " words", Toast.LENGTH_SHORT).show();

        return mw;
    }

}
