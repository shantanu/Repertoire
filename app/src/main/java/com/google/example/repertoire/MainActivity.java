package com.google.example.repertoire;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<MusicPiece> listItems = new ArrayList<MusicPiece>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW

    LinearLayout pieces;

    Repertoire rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieces = (LinearLayout) findViewById(R.id.mylist);
        rep = new Repertoire();
        rep.setContext(this);
        rep.loadData();

        if (rep.getMusicPieces().size() > 0)
        {
            populateLayout(rep.getMusicPieces());
        }


    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        pieces.addView(LayoutInflater.from(this).inflate(R.layout.edit_text_layout, pieces, false));

        ScrollView scroller = (ScrollView) findViewById(R.id.scroller);
        scroller.fullScroll(View.FOCUS_DOWN);
    }

    public void submitPieces(View v) {
        ArrayList<MusicPiece> mp = rep.getMusicPieces();

        for (int i = 0; i < pieces.getChildCount(); i++)
        {
            ViewGroup view = (ViewGroup) pieces.getChildAt(i);

            String composer = ((EditText)view.findViewById(R.id.composer_edit)).getText().toString();
            int opus;
            if ((((EditText) view.findViewById(R.id.opus_edit)).getText().toString()).equals("")) {
                opus = 0;
            }
            else {
                opus = (Integer.parseInt(((EditText) view.findViewById(R.id.opus_edit)).getText().toString()));
            }

            int no;

            if ((((EditText)view.findViewById(R.id.no_edit)).getText().toString()).equals("")) {
                no = 0;
            }
            else {
                no =  (Integer.parseInt(((EditText)view.findViewById(R.id.no_edit)).getText().toString()));
            }

            String name = ((EditText)view.findViewById(R.id.name_edit)).getText().toString();



            // MAKING A NEW MUSIC PIECE IN THE ARRAY LIST
            mp.add(i, new MusicPiece(composer, opus, no, name));
        }

        for (MusicPiece m : mp) {
            Log.d(new String(), m.toString());
        }

        rep.saveData();
    }

    private void populateLayout (ArrayList<MusicPiece> mp) {

        for (int i = 0; i < mp.size(); i++) {
            pieces.addView(LayoutInflater.from(this).inflate(R.layout.edit_text_layout, pieces, false));

            ViewGroup vg = (ViewGroup)pieces.getChildAt(i);

            Log.d("", vg.toString());

            ((EditText) vg.findViewById(R.id.name_edit)).setText(mp.get(i).getName());
            ((EditText) vg.findViewById(R.id.composer_edit)).setText(mp.get(i).getComposer());
            ((EditText) vg.findViewById(R.id.opus_edit)).setText(Integer.toString(mp.get(i).getOpus()));
            ((EditText) vg.findViewById(R.id.no_edit)).setText(Integer.toString(mp.get(i).getNumber()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickListener(View view) {

    }


}


