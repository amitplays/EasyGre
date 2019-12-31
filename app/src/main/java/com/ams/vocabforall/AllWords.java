package com.ams.vocabforall;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class AllWords extends Activity {

    ListView wordlist;

    ArrayList<WordListUtil> WordMeaning;
    DataBaseHelper mydb;
    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allwords);

        mydb = new DataBaseHelper(AllWords.this);
        wordlist = (ListView) findViewById(R.id.alllistview);
        TextView index = (TextView) findViewById(R.id.index);

        Typeface font = Typeface.createFromAsset(index.getContext().getAssets(), "fonts/test.ttf");
        index.setTypeface(font);
        getdata();

//      **** Animation******
        final Animation bottom2top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        wordlist.startAnimation(bottom2top);


    }



    public void getdata() {
        WordMeaning = new ArrayList<>();
        Cursor wordscursor = mydb.getwords();
        if (wordscursor.getCount() == 0) {

            return;
        }
        while (wordscursor.moveToNext()) {

            WordListUtil per2 = new WordListUtil();
            per2.setWord(wordscursor.getString(1)); // this is word
            per2.setMeaning(wordscursor.getString(0));        // this is meaning


            per2.setMastered(wordscursor.getInt(2));

            WordMeaning.add(per2);
            AdapterWord adapter = new AdapterWord(AllWords.this, WordMeaning);
            wordlist.setAdapter(adapter);
        }

    }




}
