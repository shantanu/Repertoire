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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<MusicPiece> listItems = new ArrayList<MusicPiece>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW

    LinearLayout pieces;

    Repertoire rep;
    Schedule schedule;
    DataManager manager;

    ArrayList<MusicPiece> mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieces = (LinearLayout) findViewById(R.id.mylist);

        manager = new DataManager();
        manager.setContext(this);
        manager.loadData();

        rep = manager.getRepertoire();
        schedule = manager.getSchedule();
        mp = rep.getMusicPieces();
        if (mp.size() > 0)
        {
            populateLayout();
        }
    }

    private void deleteItem (int ID) {
        Log.d("", "In the delete method deleting " + ID);

        mp.remove(ID);

        Log.d("", ID + " Should have been removed");
        for (int i = ID; i < pieces.getChildCount()-1; i++) {
            pieces.getChildAt(i+1).findViewById(i+1).setId(i); //lowers the ID of each button by 1
        }

        Log.d("", "Size of ArrayList: " + mp.size());
        pieces.removeAllViews();

        populateLayout();
        Log.d("", "Number of Children: " + pieces.getChildCount());
        manager.saveData();
        printArray();
    }


    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        Log.d("","In the Add method!");
        RelativeLayout toAdd = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.edit_text_layout, pieces, false);
        ImageButton b = (ImageButton) toAdd.findViewById(R.id.cancel_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(new String(), "Cancel Button was clicked!");
                deleteItem(v.getId());
            }
        });
        toAdd.findViewById(R.id.cancel_button).setId(pieces.getChildCount());
        pieces.addView(toAdd);
        mp.clear();
        populateArrayList();
        Log.d("", "Added new view");
        Log.d("", "Size of arraylist " + mp.size());
        Log.d("", "Number of children: " + pieces.getChildCount());
        printArray();
        manager.saveData();


        /*ScrollView scroller = (ScrollView) findViewById(R.id.scroller);
        scroller.fullScroll(View.FOCUS_DOWN);*/
    }

    public void submitPieces(View v) {

        mp.clear();
        for (int i = 0; i < pieces.getChildCount(); i++)
        {
            ViewGroup view = (ViewGroup) pieces.getChildAt(i);

            String composer = ((EditText)view.findViewById(R.id.composer_edit)).getText().toString();

            int opus;
            if ((((EditText) view.findViewById(R.id.opus_edit)).getText().toString()).equals("")) {opus = 0;}
            else {opus = (Integer.parseInt(((EditText) view.findViewById(R.id.opus_edit)).getText().toString()));}

            int no;
            if ((((EditText)view.findViewById(R.id.no_edit)).getText().toString()).equals("")) {no = 0;}
            else {no = (Integer.parseInt(((EditText)view.findViewById(R.id.no_edit)).getText().toString()));}

            String name = ((EditText)view.findViewById(R.id.name_edit)).getText().toString();



            // MAKING A NEW MUSIC PIECE IN THE ARRAY LIST
            mp.add(i, new MusicPiece(composer, opus, no, name));
        }
        manager.saveData();
    }

    private void populateArrayList() {
        mp.clear();
        for (int i = 0; i < pieces.getChildCount(); i++)
        {
            ViewGroup view = (ViewGroup) pieces.getChildAt(i);

            String composer = ((EditText)view.findViewById(R.id.composer_edit)).getText().toString();

            int opus;
                if ((((EditText) view.findViewById(R.id.opus_edit)).getText().toString()).equals("")) {opus = 0;}
                else {opus = (Integer.parseInt(((EditText) view.findViewById(R.id.opus_edit)).getText().toString()));}

            int no;
                if ((((EditText)view.findViewById(R.id.no_edit)).getText().toString()).equals("")) {no = 0;}
                else {no = (Integer.parseInt(((EditText)view.findViewById(R.id.no_edit)).getText().toString()));}

            String name = ((EditText)view.findViewById(R.id.name_edit)).getText().toString();



            // MAKING A NEW MUSIC PIECE IN THE ARRAY LIST
            mp.add(i, new MusicPiece(composer, opus, no, name));
        }
        
    }

    private void populateLayout () {

        for (int i = 0; i < mp.size(); i++) {
            RelativeLayout toAdd = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.edit_text_layout, pieces, false);

            Log.d("", toAdd.toString());

            ImageButton b = (ImageButton) toAdd.findViewById(R.id.cancel_button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(new String(), "Cancel Button was clicked!");
                    deleteItem(v.getId());
                }
            });
            toAdd.findViewById(R.id.cancel_button).setId(pieces.getChildCount());
            if (mp.get(i).getOpus() == 0) {
                ((EditText) toAdd.findViewById(R.id.opus_edit)).setText("");
            }
            else {
                ((EditText) toAdd.findViewById(R.id.opus_edit)).setText(Integer.toString(mp.get(i).getOpus()));
            }
            if (mp.get(i).getNumber() == 0) {
                ((EditText) toAdd.findViewById(R.id.no_edit)).setText("");
            }
            else {
                ((EditText) toAdd.findViewById(R.id.no_edit)).setText(Integer.toString(mp.get(i).getNumber()));
            }
            ((EditText) toAdd.findViewById(R.id.name_edit)).setText(mp.get(i).getName());
            ((EditText) toAdd.findViewById(R.id.composer_edit)).setText(mp.get(i).getComposer());


            pieces.addView(toAdd);
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

    public void printArray() {
        ArrayList<MusicPiece> mp = rep.getMusicPieces();

        for (MusicPiece m: mp) {
            Log.d("",m.toString());
        }
    }


}


