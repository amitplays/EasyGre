package com.ams.vocabforall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ams on 4/21/2017.
 */
public class AlphabaticTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabatic_test);



        TextView head = (TextView)findViewById(R.id.textView);

        Typeface font = Typeface.createFromAsset(head.getContext().getAssets(), "fonts/thin.otf");
        head.setTypeface(font);

        final Button a = (Button)findViewById(R.id.a);        final Button b = (Button)findViewById(R.id.b);        final Button c = (Button)findViewById(R.id.c);        final Button d = (Button)findViewById(R.id.d);
        final Button e = (Button)findViewById(R.id.e);        final Button f = (Button)findViewById(R.id.f);        final Button g = (Button)findViewById(R.id.g);        final Button h = (Button)findViewById(R.id.h);
        final Button i = (Button)findViewById(R.id.i);      final   Button j = (Button)findViewById(R.id.j);        final Button k = (Button)findViewById(R.id.k);        final Button l = (Button)findViewById(R.id.l);
        final Button m = (Button)findViewById(R.id.m);        final Button n = (Button)findViewById(R.id.n);        final Button o = (Button)findViewById(R.id.o);        final Button p = (Button)findViewById(R.id.p);
        final Button q = (Button)findViewById(R.id.q);        final Button r = (Button)findViewById(R.id.r);        final Button s = (Button)findViewById(R.id.s);        final Button t = (Button)findViewById(R.id.t);
        final Button u = (Button)findViewById(R.id.u);        final Button v1 = (Button)findViewById(R.id.v);        final Button w = (Button)findViewById(R.id.w);        final Button x = (Button)findViewById(R.id.x);
        final Button y = (Button)findViewById(R.id.y);        final Button z = (Button)findViewById(R.id.z);




        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = a.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = b.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = c.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = d.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = e.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = f.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = g.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = h.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = i.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = j.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = k.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = l.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = m.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = n.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = o.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = p.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = q.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = r.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord", ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = s.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = t.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = u.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = v1.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = w.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = x.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = y.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });

        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ab = z.getText().toString();
                ab = ab.toLowerCase();
                Intent intent = new Intent(AlphabaticTest.this, AbTest.class);
                intent.putExtra("keyWord",ab);
                startActivity(intent);
                overridePendingTransition(R.anim.nochange, R.anim.slide_in);
            }
        });


    }
}
