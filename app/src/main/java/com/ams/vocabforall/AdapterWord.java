package com.ams.vocabforall;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ams on 2/28/2017.
 */
public class AdapterWord extends BaseAdapter {
    Context context;
    // Array to define lists of All words
    ArrayList<WordListUtil> words;
    DataBaseHelper myDb;

    public AdapterWord (Context context, ArrayList<WordListUtil> wordlist){
        this.context = context;
        words = wordlist;
        myDb = new DataBaseHelper(context);
    }



    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

       final WordListUtil WL = words.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.allwords_layout, null);
        RelativeLayout view2 = (RelativeLayout) view.findViewById(R.id.wordview);


        TextView word = (TextView) view.findViewById(R.id.word);
        word.setText(WL.getMeaning());
        TextView meaning = (TextView) view.findViewById(R.id.meaning);
        meaning.setText(WL.getWord());


        Typeface font = Typeface.createFromAsset(word.getContext().getAssets(), "fonts/thin.otf");
        word.setTypeface(font);
        meaning.setTypeface(font);

        ImageView fav = (ImageView)view.findViewById(R.id.fav);

        if (WL.getMastered()==1){

           fav.setImageResource(R.drawable.fin);

        }

        TextView serial = (TextView) view.findViewById(R.id.serial);

        serial.setText(String.valueOf(i+1));

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

                builder1.setMessage("Do you want to add " + WL.getMeaning().toUpperCase() + " to your Mastered Word-List ?");
                builder1.setCancelable(true);


                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                myDb.UpdateMastered(WL.getMeaning().trim(), 1);

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

        return view;
    }
}
