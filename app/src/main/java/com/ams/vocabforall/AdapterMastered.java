package com.ams.vocabforall;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ams on 3/18/2017.
 */
public class AdapterMastered extends BaseAdapter{

    Context context;
    // Array to define lists of Words
    ArrayList<MasteredGetterSetter> words;
    DataBaseHelper myDb;


    public AdapterMastered (Context context, ArrayList<MasteredGetterSetter> wordlist){
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

        MasteredGetterSetter WL = words.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.mastered_list_layout, null);

        TextView word = (TextView) view.findViewById(R.id.word);
        word.setText(WL.getMeaning());

        TextView meaning = (TextView) view.findViewById(R.id.meaning);
        meaning.setText(WL.getWord());


        TextView serial = (TextView) view.findViewById(R.id.serial);
        serial.setText(String.valueOf(i+1));

        Typeface font = Typeface.createFromAsset(word.getContext().getAssets(), "fonts/thin.otf");
        word.setTypeface(font);
        meaning.setTypeface(font);


        return view;
    }

}
