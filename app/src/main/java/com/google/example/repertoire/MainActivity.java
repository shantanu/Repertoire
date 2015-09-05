package com.google.example.repertoire;

import android.app.Activity;
import android.os.Bundle;
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

            // MAKING A NEW MUSIC PIECE IN THE ARRAY LIST
            mp.add(i, new MusicPiece(((EditText)view.findViewById(R.id.composer_edit)).getText().toString(),
                                    ((EditText)view.findViewById(R.id.opus_edit)).getText().toString()) + );
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


