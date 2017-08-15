package com.matthewkruk.jh5_hangman_mkruk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, HangmanUpdate {
    private MyDrawView1 myDrawView1;
    private TextView display;

    private final HangmanLogic hangmanLogic = new HangmanLogic(this);
    private boolean gameOver = false;

    static final String WORD_MANAGER_APP = "com.matthewkruk.jh5_wordmanager_mkruk.WordManager";
    static final int WORD_MANAGER = 1;

    private static String word = "";
    private static String[] words = {
            "animal",
            "dog",
            "cat",
            "insect",
            "bear",
            "beaver",
            "duck",
            "cow",
            "monkey",
            "donkey"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDrawView1 = (MyDrawView1) findViewById(R.id.myDrawHangmanView);
        display = (TextView) findViewById(R.id.display);

        findViewById(R.id.a).setOnClickListener(this);
        findViewById(R.id.b).setOnClickListener(this);
        findViewById(R.id.c).setOnClickListener(this);
        findViewById(R.id.d).setOnClickListener(this);
        findViewById(R.id.e).setOnClickListener(this);
        findViewById(R.id.f).setOnClickListener(this);
        findViewById(R.id.g).setOnClickListener(this);
        findViewById(R.id.h).setOnClickListener(this);
        findViewById(R.id.i).setOnClickListener(this);
        findViewById(R.id.j).setOnClickListener(this);
        findViewById(R.id.k).setOnClickListener(this);
        findViewById(R.id.l).setOnClickListener(this);
        findViewById(R.id.m).setOnClickListener(this);
        findViewById(R.id.n).setOnClickListener(this);
        findViewById(R.id.o).setOnClickListener(this);
        findViewById(R.id.p).setOnClickListener(this);
        findViewById(R.id.q).setOnClickListener(this);
        findViewById(R.id.r).setOnClickListener(this);
        findViewById(R.id.s).setOnClickListener(this);
        findViewById(R.id.t).setOnClickListener(this);
        findViewById(R.id.u).setOnClickListener(this);
        findViewById(R.id.v).setOnClickListener(this);
        findViewById(R.id.w).setOnClickListener(this);
        findViewById(R.id.x).setOnClickListener(this);
        findViewById(R.id.y).setOnClickListener(this);
        findViewById(R.id.z).setOnClickListener(this);

        hangmanLogic.newGame(10, words);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            extras.getStringArrayList("words");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game: {
                gameOver = false;
                myDrawView1.reset();
                hangmanLogic.newGame(10, words);
                return true;
            }
            case R.id.new_category: {
                Intent intent = new Intent(WORD_MANAGER_APP);
                startActivityForResult(intent, WORD_MANAGER /* Request */);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (gameOver) {
            return;
        }
        if (v instanceof Button) {
            char c = ((Button) v).getText().charAt(0);
            if (!(hangmanLogic.buttonClicked(c))) {
                myDrawView1.increment();
            }
        }
    }

    @Override
    public void updateMessage(String s) {
        Log.d("Mine", "updateMessage(String s): " + s);
        Log.d("Mine", "findViewById(R.id.word): " + s);

        display.setText(s);

        Log.d("Mine", "setText(s): " + s);
    }

    @Override
    public void gameIsDone(boolean winner) {
        gameOver = true;
    }
}