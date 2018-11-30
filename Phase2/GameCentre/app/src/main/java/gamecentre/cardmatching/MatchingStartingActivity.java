package gamecentre.cardmatching;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gamecentre.Serializer;
import gamecentre.slidingtiles.R;

public class MatchingStartingActivity extends AppCompatActivity {
    /**
     * The main save file.
     */
    public static String matchingSaveFileName;
    /**
     * A temporary save file.
     */
    public static String matchingTempSaveFileName;
    /**
     * The auto saved file.
     */
    public static String matchingAutoSaveFileName;
    /**
     * The board manager.
     */
    private MatchingBoardManager matchingBoardManager;
    /**
     * The serializer for this activity.
     */
    Serializer serializer = new Serializer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matchingBoardManager = new MatchingBoardManager();
        serializer.saveMatchingBoardManagerToFile(matchingTempSaveFileName,matchingBoardManager,this);
        MatchingScoreboard.reset();

        setContentView(R.layout.activity_cardmatching_starting);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addScoreboardButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.MatchingGameStartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchingBoardManager = new MatchingBoardManager();
                switchToGame();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.MatchingGameLoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchingBoardManager = serializer.loadMatchingBoardManagerFromFile(matchingAutoSaveFileName, MatchingStartingActivity.this);
                serializer.saveMatchingBoardManagerToFile(matchingAutoSaveFileName, matchingBoardManager, MatchingStartingActivity.this);
                serializer.saveMatchingBoardManagerToFile(matchingTempSaveFileName,matchingBoardManager,MatchingStartingActivity.this);
                makeToastLoadedText();
                switchToGame();
            }
        });
    }

    /**
     * Activate the scoreboard button
     */
    private void addScoreboardButtonListener() {
        Button loadButton = findViewById(R.id.ScoreboardButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the score board activity.
     */
    private void switchToScoreboard() {
        Intent m = new Intent(this, MatchingScoreboardActivity.class);
        startActivity(m);
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.MatchingGameSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serializer.saveMatchingBoardManagerToFile(matchingSaveFileName, matchingBoardManager, MatchingStartingActivity.this);
                serializer.saveMatchingBoardManagerToFile(matchingTempSaveFileName,matchingBoardManager,MatchingStartingActivity.this);
                makeToastSavedText();
            }
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, MatchingGameActivity.class);
        serializer.saveMatchingBoardManagerToFile(MatchingStartingActivity.matchingTempSaveFileName, matchingBoardManager, this);
        startActivity(tmp);
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        matchingBoardManager = serializer.loadMatchingBoardManagerFromFile(matchingTempSaveFileName, this);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
