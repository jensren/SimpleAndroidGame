package gamecentre.battlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gamecentre.Serializer;
import gamecentre.slidingtiles.R;

public class BattleScoreboardActivity extends AppCompatActivity {
    /**
     * The file where scoreboard will be saved
     */
    public static final String SCORE_FILENAME = "battle_scoreboard_file.ser";
    /**
     * The textview for scoreboard
     */
    TextView scoreBoardView;
    /**
     * The battle scoreboard
     */
    BattleScoreboard scoreboard;
    /**
     * The serializer for this activity
     */
    Serializer serializer = new Serializer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        scoreBoardView = findViewById(R.id.s_b);

        scoreboard = (BattleScoreboard) serializer.loadScoreboardFromFile(SCORE_FILENAME, this);
        if (scoreboard == null) {
            scoreboard = new BattleScoreboard();
        }
        scoreboard.update();
        serializer.saveScoreboardToFile(SCORE_FILENAME, scoreboard, this);
        scoreBoardView.setText(scoreboard.toString());
        displayScore();
        displayBestScore();
        displayWinner();
        addMainButtonListener();
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, BattleStartingActivity.class);
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
     * Display the winner of the game
     */
    private void displayWinner() {
        TextView winText = findViewById(R.id.winText);
        winText.setText(scoreboard.getWinner());
    }

    /**
     * Add the main button to go back to the main game screen.
     */
    private void addMainButtonListener() {
        Button BattleButton = findViewById(R.id.main);
        BattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToBattleActivity();
            }
        });
    }

    /**
     * Display the game's starting activity.
     */
    private void switchToBattleActivity() {
        Intent tmp = new Intent(this, BattleStartingActivity.class);
        startActivity(tmp);
    }
}
