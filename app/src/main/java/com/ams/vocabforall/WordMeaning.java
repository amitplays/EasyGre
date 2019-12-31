package com.ams.vocabforall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by Ams on 3/6/2017.
 */
public class WordMeaning extends Activity {

    String word , meaning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_meaning);

        Intent intent = getIntent();
        word = intent.getExtras().getString("word");
        meaning = intent.getExtras().getString("meaning");

        TextView Dword = (TextView)findViewById(R.id.textView2);
        TextView Dmeaning = (TextView)findViewById(R.id.textView3);


        Dword.setText(word);
        Dmeaning.setText(meaning);







    }
}
