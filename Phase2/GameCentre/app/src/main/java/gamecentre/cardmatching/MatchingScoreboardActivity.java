package gamecentre.cardmatching;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gamecentre.Serializer;
import gamecentre.slidingtiles.R;
    /**
     * Manage the scoreboardView layout during the game and save the current scoreboardView.
     */
    public class MatchingScoreboardActivity extends AppCompatActivity {
        /**
         * The TextView for scoreboard
         */
        TextView scoreboardView;
        /**
         * The name of the file to serialize the scoreboard to
         */
        public static final String SCORE_FILENAME = "cardmatching_scoreboard_file.ser";
        /**
         * The scoreboard for the game
         */
        MatchingScoreboard scoreboard;
        /**
         * The serializer for this activity
         */
        Serializer serializer = new Serializer();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.scoreboard);
            scoreboardView = findViewById(R.id.s_b);
            scoreboard = (MatchingScoreboard) serializer.loadScoreboardFromFile(SCORE_FILENAME,
                    this);
            if (scoreboard == null) {
                scoreboard = new MatchingScoreboard();
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
            Intent in = new Intent(this, MatchingStartingActivity.class);
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
            Button matchingButton = findViewById(R.id.main);
            matchingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchToMatchingStartingActivity();
                }
            });
        }

        /**
         * Display the game's starting activity.
         */
        private void switchToMatchingStartingActivity() {
            Intent tmp = new Intent(this, MatchingStartingActivity.class);
            startActivity(tmp);
        }
    }
