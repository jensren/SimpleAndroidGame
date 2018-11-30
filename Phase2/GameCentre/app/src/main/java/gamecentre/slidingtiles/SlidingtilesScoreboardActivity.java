package gamecentre.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gamecentre.Serializer;

/**
 * Manage the scoreboardView layout during the game and save the current scoreboardView.
 */

public class SlidingtilesScoreboardActivity extends AppCompatActivity {

    /**
     * The textview for the scoreboard.
     */
    TextView scoreboardView;
    /**
     * The file to save scoreboard to.
     */
    public static final String SCORE_FILENAME = "slidingtiles_scoreboard_file.ser";

    /**
     * The scoreboard.
     */
    SlidingtilesScoreboard scoreboard;

    /**
     * The serializer for this activity.
     */
    Serializer serializer = new Serializer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);
        scoreboardView = findViewById(R.id.s_b);
        scoreboard = (SlidingtilesScoreboard) serializer.loadScoreboardFromFile(SCORE_FILENAME, this);
        if (scoreboard == null) {
            scoreboard = new SlidingtilesScoreboard();
        }
        scoreboard.update();
        serializer.saveScoreboardToFile(SCORE_FILENAME, scoreboard, this);
        scoreboardView.setText(scoreboard.toString());
        displayScore();
        displayBestScore();
        addMainButtonListener();
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, SlidingtilesStartingActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        serializer.saveScoreboardToFile(SCORE_FILENAME, scoreboard, this);
    }

    /**
     * Display the user's score from the game that was just played.
     */
    private void displayScore() {
        TextView score = findViewById(R.id.score);
        score.setText(scoreboard.getUserCurrentScore());
    }

    /**
     * Display the user's high score for the game.
     */
    private void displayBestScore() {
        TextView highScore = findViewById(R.id.highScore);
        highScore.setText(scoreboard.getUserBestScore());
    }

    /**
     * Add the main button to go back to the main game screen.
     */
    private void addMainButtonListener() {
        Button slidingTilesButton = findViewById(R.id.main);
        slidingTilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSlidingTilesActivity();
            }
        });
    }

    /**
     * Display the game's starting activity.
     */
    private void switchToSlidingTilesActivity() {
        Intent tmp = new Intent(this, SlidingtilesStartingActivity.class);
        startActivity(tmp);
    }
}