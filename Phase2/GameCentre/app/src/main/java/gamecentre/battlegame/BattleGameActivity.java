package gamecentre.battlegame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import gamecentre.slidingtiles.R;

/**
 * The game activity.
 */
public class BattleGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loadFromFile();
        setContentView(R.layout.activity_battlegame_main);

        addRegularMoveButtonListener();
        addSpecialMoveButtonListener();

    }

    /**
     * Add the special move button.
     */
    private void addSpecialMoveButtonListener() {
        Button specialMoveButton = findViewById(R.id.specialmove);
        specialMoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * Add the regular move button.
     */
    private void addRegularMoveButtonListener() {
        Button regularMoveButton = findViewById(R.id.regularmove);
        regularMoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
