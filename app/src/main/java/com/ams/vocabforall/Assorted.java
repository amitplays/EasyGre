package com.ams.vocabforall;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ams on 3/23/2017.
 */
public class Assorted extends Activity {

    ListView MiscWordlist;
    ArrayList<AssortedUtil> WordMeaning;
    DataBaseHelper mydb;
    String word;
    TextView Dword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assorted);
        mydb = new DataBaseHelper(Assorted.this);
        Button how = (Button)findViewById(R.id.button6);

        MiscWordlist = (ListView) findViewById(R.id.assortedlistView);
        TextView index = (TextView) findViewById(R.id.index);
        Typeface font = Typeface.createFromAsset(index.getContext().getAssets(), "fonts/thin.otf");
        index.setTypeface(font);

        getdata();



        MiscWordlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String meaning = WordMeaning.get(i).getWord();
                word = WordMeaning.get(i).getMeaning();


                final Dialog dialog = new Dialog(Assorted.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.assorted_popup_layout);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                Dword = (TextView) dialog.findViewById(R.id.textView2);
                ImageButton share = (ImageButton) dialog.findViewById(R.id.share2);
                final TextView Dmeaning = (TextView) dialog.findViewById(R.id.textView3);


                ImageButton search = (ImageButton) dialog.findViewById(R.id.google);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");

                        String shareBody = "WORD : " + Dword.getText().toString() + " \n\nMEANING :" + Dmeaning.getText().toString();

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

                if (meaning == null) {
                    Dmeaning.setText(" ");
                    Dmeaning.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

                } else {
                    Dmeaning.setText(meaning.trim());

                }

                dialog.show();


            }
        });

        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(Assorted.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.how);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                    dialog.setTitle("Title...");

                // set the custom dialog components - text and button
                TextView text = (TextView) dialog.findViewById(R.id.txt);
                text.setText("These are the List of words that are not common to the GRE Exam but are still pre-eminent. \n\n This list contains :\n1. Popular Studies. \n2. Medical conditions. \n3. Human Characteristics. \n4. Name of Fears.  ");
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/test.ttf");
                text.setTypeface(font);
                dialog.show();



            }
        });




    }



    public void getdata() {
        WordMeaning = new ArrayList<>();
        Cursor wordscursor = mydb.getassorted();
        if (wordscursor.getCount() == 0) {

            return;
        }
        while (wordscursor.moveToNext()) {

            AssortedUtil per2 = new AssortedUtil();
            per2.setWord(wordscursor.getString(1)); // this is word
            per2.setMeaning(wordscursor.getString(0));        // this is meaning


            WordMeaning.add(per2);
            AssortedAdapter adapter = new AssortedAdapter(Assorted.this, WordMeaning);
            MiscWordlist.setAdapter(adapter);
        }

    }



}
