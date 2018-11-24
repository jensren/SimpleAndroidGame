package gamecentre.battlegame;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
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

    private BattleQueue battleQueue;
    private Character player1;
    private Character player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loadFromFile();
        setContentView(R.layout.activity_battlegame_main);

        addRegularMoveButtonListener();
        addSpecialMoveButtonListener();
        setCharacters();

        ImageView catImage = findViewById(R.id.catimage);
        ImageView dogImage = findViewById(R.id.dogimage);

        if (player1.getType().equals("cat")) {
            switch (player1.getClass().getSimpleName()) {
                case "NinjaCat":
                    catImage.setImageResource(R.drawable.ninja_cat0);
                    break;
                case "SamuraiCat":
                    catImage.setImageResource(R.drawable.samurai_cat0);
                    break;
                case "ShamanCat":
                    catImage.setImageResource(R.drawable.shaman_cat0);
                    break;
            }
            switch (player2.getClass().getSimpleName()) {
                case "DetectiveShibe":
                    dogImage.setImageResource(R.drawable.detective_shibe0);
                    break;
                case "SirShibe":
                    dogImage.setImageResource(R.drawable.sir_shibe0);
                    break;
                case "DruidShibe":
                    dogImage.setImageResource(R.drawable.druid_shibe0);
                    break;
            }
        } else {
            switch (player2.getClass().getSimpleName()) {
                case "NinjaCat":
                    catImage.setImageResource(R.drawable.ninja_cat0);
                    break;
                case "SamuraiCat":
                    catImage.setImageResource(R.drawable.samurai_cat0);
                    break;
                case "ShamanCat":
                    catImage.setImageResource(R.drawable.shaman_cat0);
                    break;
            }
            switch (player1.getClass().getSimpleName()) {
                case "DetectiveShibe":
                    dogImage.setImageResource(R.drawable.detective_shibe0);
                    break;
                case "SirShibe":
                    dogImage.setImageResource(R.drawable.sir_shibe0);
                    break;
                case "DruidShibe":
                    dogImage.setImageResource(R.drawable.druid_shibe0);
                    break;
            }
        }
    }

//    public Character getPlayer1() {
//        return player1;
//    }
//
//    public Character getPlayer2() {
//        return player2;
//    }

    /**
     * Set player1 and player2 as their respective characters.
     */
    private void setCharacters() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String p1Class = extras.getString("player1");
            String p2Class = extras.getString("player2");
            if (p1Class != null && p2Class != null) {
                switch (p1Class) {
                    case "NinjaCat":
                        player1 = new NinjaCat();
                        break;
                    case "SamuraiCat":
                        player1 = new SamuraiCat();
                        break;
                    case "ShamanCat":
                        player1 = new ShamanCat();
                        break;
                    case "DetectiveShibe":
                        player1 = new DetectiveShibe();
                        break;
                    case "SirShibe":
                        player1 = new SirShibe();
                        break;
                    case "DruidShibe":
                        player1 = new DruidShibe();
                        break;
                }
                switch (p2Class) {
                    case "NinjaCat":
                        player2 = new NinjaCat();
                        break;
                    case "SamuraiCat":
                        player2 = new SamuraiCat();
                        break;
                    case "ShamanCat":
                        player2 = new ShamanCat();
                        break;
                    case "DetectiveShibe":
                        player2 = new DetectiveShibe();
                        break;
                    case "SirShibe":
                        player2 = new SirShibe();
                        break;
                    case "DruidShibe":
                        player2 = new DruidShibe();
                        break;
                }
            } else {
                throw new NullPointerException("Character is null");
            }
        }
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
