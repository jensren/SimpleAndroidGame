package gamecentre.battlegame;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import gamecentre.slidingtiles.R;

/**
 * Activity for choosing the dog to play.
 */
public class DogChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlegame_dog_choice);

        changeChoiceText();
        addDetectiveButtonListener();
        addDruidButtonListener();
        addSirShibeButtonListener();

        startAnimations();
    }

    /**
     * Start the animations in the activity.
     */
    private void startAnimations() {
        AnimationDrawable druidAnimation;
        ImageView ninjaImage = findViewById(R.id.animateddruid1);
        ninjaImage.setBackgroundResource(R.drawable.druid_shibe_animated);
        druidAnimation = (AnimationDrawable) ninjaImage.getBackground();
        druidAnimation.start();

        AnimationDrawable sirAnimation;
        ImageView shamanImage = findViewById(R.id.animatedsir1);
        shamanImage.setBackgroundResource(R.drawable.sir_shibe_animated);
        sirAnimation = (AnimationDrawable) shamanImage.getBackground();
        sirAnimation.start();

        AnimationDrawable detectiveAnimation;
        ImageView samuraiImage = findViewById(R.id.animateddetective1);
        samuraiImage.setBackgroundResource(R.drawable.detective_shibe_animated);
        detectiveAnimation = (AnimationDrawable) samuraiImage.getBackground();
        detectiveAnimation.start();
    }

    /**
     * Add the Sir Shibe button.
     */
    private void addSirShibeButtonListener() {
        Button sirShibeButton = findViewById(R.id.sir);
        sirShibeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNextActivity("SirShibe");
            }
        });
    }

    /**
     * Add the Detective button.
     */
    private void addDetectiveButtonListener() {
        Button detectiveButton = findViewById(R.id.detective);
        detectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNextActivity("DetectiveShibe");
            }
        });
    }

    /**
     * Add the Druid button.
     */
    private void addDruidButtonListener() {
        Button druidButton = findViewById(R.id.druid);
        druidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNextActivity("DruidShibe");
            }
        });
    }

    /**
     * Switch to the CatChoiceActivity if second player needs to pick a dog, or switch to
     * BattleGameActivity if both players have chosen their characters
     */
    private void switchToNextActivity(String character) {
        Bundle extras = getIntent().getExtras();
        Intent tmp;
        if (extras == null) {
            extras = new Bundle();
            tmp = new Intent(this, CatChoiceActivity.class);
            extras.putString("player1", character);
        } else {
            tmp = new Intent(this, BattleGameActivity.class);
            extras.putString("player2", character);
        }
        tmp.putExtras(extras);
        startActivity(tmp);
    }

    /**
     * Change the text based on which player is choosing their character.
     */
    private void changeChoiceText() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView choiceText = findViewById(R.id.askchoose_dog);
            String text = "Player 2: Choose your fighter!";
            choiceText.setText(text);
        }
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
