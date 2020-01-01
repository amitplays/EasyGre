package com.ams.vocabforall;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ams on 3/3/2017.
 */
public class ListOfMeanings extends Activity {

    ListView wordlist;
    TextView head,Dword;
    ArrayList<CharWordListUtil> WordMeaning;
    DataBaseHelper mydb;
    RadioButton Dfav;
    String word,key;
    int Mflag, a= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_meanings);
        mydb = new DataBaseHelper(ListOfMeanings.this);
        wordlist = (ListView) findViewById(R.id.listView);
        head = (TextView) findViewById(R.id.index);
        Intent intent = getIntent();
        key = intent.getExtras().getString("keyWord");
        head.setText(key.toUpperCase());
        Button how = findViewById(R.id.button3);
        //      **** Animation******
//        final Animation bottom2top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
//        wordlist.startAnimation(bottom2top);
        getdata();



        wordlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String meaning = WordMeaning.get(i).getWord();
                word = WordMeaning.get(i).getMeaning();
//                Cursor masterFlag = mydb.getfav(word);
//                masterFlag.moveToFirst();
                Cursor masterFlag = mydb.getfav2(word);
                while (masterFlag.moveToNext()) {

                    a = masterFlag.getInt(2);
                }
//                Animation Code
                final Dialog dialog = new Dialog(ListOfMeanings.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.word_meaning);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                Dword = (TextView) dialog.findViewById(R.id.textView2);
                ImageButton share = (ImageButton) dialog.findViewById(R.id.share2);
                final TextView Dmeaning = (TextView) dialog.findViewById(R.id.textView3);
                Dfav = (RadioButton) dialog.findViewById(R.id.fav);

                if (a == 0) {

                    Mflag = 0;

                } else {
                    Mflag = 1;

                }

                if (Mflag == 1) {

                    Dfav.setChecked(true);
                } else {

                    Dfav.setChecked(false);

                }

                ImageButton search = (ImageButton) dialog.findViewById(R.id.google);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");

                        String shareBody = "WORD : " + Dword.getText().toString() + " \n\nMEANING : " + Dmeaning.getText().toString();

                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "GRE Word");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Send Word & Meaning Via"));
                    }
                });

                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("http://www.google.com/#q=define:" + word + "");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                Dword.setText(word.trim());
                Dmeaning.setText(meaning.trim());
                dialog.show();

                Dfav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Mflag == 0) {
                            Toast.makeText(ListOfMeanings.this, "" + word + " is added to your Mastered List ", Toast.LENGTH_SHORT).show();
                            Dfav.setChecked(true);
                            Mflag = 1;
                            mydb.UpdateMastered(word, 1);
                            getdata();
                        } else {
                            Toast.makeText(ListOfMeanings.this, "" + word + " is removed from your Mastered List ", Toast.LENGTH_SHORT).show();
                            Mflag = 0;
                            mydb.UpdateMastered(word, 0);
                            Dfav.setChecked(false);
                            getdata();
                        }
                    }
                });
            }
        });

        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(ListOfMeanings.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.how);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                    dialog.setTitle("Title...");

                // set the custom dialog components - text and button
                TextView text = (TextView) dialog.findViewById(R.id.txt);
                text.setText("Tap on the word to see the meaning of the word and to:\n1. Google search the word and know the synonyms, antonyms and detailed meaning.(internet connection required !)\n\n2. Share the word and meaning using different apps.\n\n3. Master the words that have learned or you already know.  ");
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
                text.setTypeface(font);
                dialog.show();
            }
        });
    }

    public void getdata() {
        WordMeaning = new ArrayList<>();
       Cursor words = mydb.getmeaning(key);
        if (words.getCount() == 0) {
            Toast.makeText(ListOfMeanings.this, "No Words with letter : " + key, Toast.LENGTH_SHORT).show();
            return;
        }
        while (words.moveToNext()) {
            CharWordListUtil per2 = new CharWordListUtil();
            per2.setWord(words.getString(1));           // this is word
            per2.setMeaning(words.getString(0));        // this is meaning
            per2.setMastered(words.getInt(2));
            WordMeaning.add(per2);
            AdapterCharWord adapter2 = new AdapterCharWord(ListOfMeanings.this, WordMeaning);
            wordlist.setAdapter(adapter2);
        }

    }


}


