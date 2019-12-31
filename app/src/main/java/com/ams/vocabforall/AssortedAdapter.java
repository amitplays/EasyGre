package com.ams.vocabforall;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ams on 3/26/2017.
 */
public class AssortedAdapter extends BaseAdapter {

    Context context;
    // Array to define lists of All words
    ArrayList<AssortedUtil> assortedwords;
    DataBaseHelper myDb;

    public AssortedAdapter(Context context,ArrayList<AssortedUtil> words ){

        this.context = context;
        assortedwords = words;
        myDb = new DataBaseHelper(context);


    }



    @Override
    public int getCount() {
        return assortedwords.size();
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

        AssortedUtil WL = assortedwords.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.assorted_layout, null);


        TextView word = (TextView) view.findViewById(R.id.word);
        word.setText(WL.getMeaning());
        TextView meaning = (TextView) view.findViewById(R.id.meaning);
        meaning.setText(WL.getWord());

        if (i == 24|| i==34 || i==62 || i==79 ) {
            word.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            word.setTypeface(null, Typeface.BOLD_ITALIC);  //-- for  bold & italic the text
            word.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            word.setPaintFlags(0);

            word.setBackgroundResource(R.color.colorAccent);
            meaning.setBackgroundResource(R.color.colorAccent);

            meaning.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);



        }




        Typeface font = Typeface.createFromAsset(word.getContext().getAssets(), "fonts/thin.otf");
        word.setTypeface(font);
        meaning.setTypeface(font);



        return view;
    }
}
