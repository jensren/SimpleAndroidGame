package gamecentre.battlegame;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import gamecentre.slidingtiles.R;

/**
 * Starting activity for the battle game.
 */
public class BattleStartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BattleScoreboard.reset();

        setContentView(R.layout.activity_battlegame_starting);
        startAnimations();

        addStartButtonListener();
        addScoreboardButtonListener();
    }

    /**
     * Activate the scoreboard button.
     */
    private void addScoreboardButtonListener() {
        Button startButton = findViewById(R.id.ScoreboardButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Start the animations in the activity.
     */
    private void startAnimations() {
        AnimationDrawable shibeAnimation;
        ImageView imageView = findViewById(R.id.animatedshibe);
        imageView.setBackgroundResource(R.drawable.animated_shibe);
        shibeAnimation = (AnimationDrawable) imageView.getBackground();
        shibeAnimation.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.battlestart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToCatOrDogActivity();
            }
        });
    }

    /**
     * Switch to the CatOrDogActivity view.
     */
    private void switchToCatOrDogActivity() {
        Intent tmp = new Intent(this, CatOrDogActivity.class);
        startActivity(tmp);
    }


    /**
     * Switch to the score board activity.
     */
    private void switchToScoreboard() {
        Intent m = new Intent(this, BattleScoreboardActivity.class);
        startActivity(m);
    }

}