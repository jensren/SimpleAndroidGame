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
 * Activity for choosing the cat to play.
 */
public class CatChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlegame_cat_choice);

        changeChoiceText();
        addNinjaButtonListener();
        addSamuraiButtonListener();
        addShamanButtonListener();
        startAnimations();
    }

    /**
     * Start the animations in the activity.
     */
    private void startAnimations() {
        AnimationDrawable ninjaAnimation;
        ImageView ninjaImage = findViewById(R.id.animatedninja1);
        ninjaImage.setBackgroundResource(R.drawable.ninja_cat_animated);
        ninjaAnimation = (AnimationDrawable) ninjaImage.getBackground();
        ninjaAnimation.start();

        AnimationDrawable shamanAnimation;
        ImageView shamanImage = findViewById(R.id.animatedshaman1);
        shamanImage.setBackgroundResource(R.drawable.shaman_cat_animated);
        shamanAnimation = (AnimationDrawable) shamanImage.getBackground();
        shamanAnimation.start();

        AnimationDrawable samuraiAnimation;
        ImageView samuraiImage = findViewById(R.id.animatedsamurai1);
        samuraiImage.setBackgroundResource(R.drawable.samurai_cat_animated);
        samuraiAnimation = (AnimationDrawable) samuraiImage.getBackground();
        samuraiAnimation.start();
    }

    /**
     * Add the Shaman button.
     */
    private void addShamanButtonListener() {
        Button shamanButton = findViewById(R.id.shaman);
        shamanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNextActivity("ShamanCat");
            }
        });
    }

    /**
     * Add the Samurai button.
     */
    private void addSamuraiButtonListener() {
        Button samuraiButton = findViewById(R.id.samurai);
        samuraiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNextActivity("SamuraiCat");
            }
        });
    }

    /**
     * Add the Ninja button.
     */
    private void addNinjaButtonListener() {
        Button ninjaButton = findViewById(R.id.ninja);
        ninjaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNextActivity("NinjaCat");
            }
        });
    }

    /**
     * Switch to the DogChoiceActivity if second player needs to pick a dog, or switch to
     * BattleGameActivity if both players have chosen their characters
     */
    private void switchToNextActivity(String character) {
        Bundle extras = getIntent().getExtras();
        Intent tmp;
        if (extras == null) {
            extras = new Bundle();
            tmp = new Intent(this, DogChoiceActivity.class);
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
            TextView choiceText = findViewById(R.id.askchoose_cat);
            String text = "Player 2: Choose your fighter!";
            choiceText.setText(text);
        }
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
