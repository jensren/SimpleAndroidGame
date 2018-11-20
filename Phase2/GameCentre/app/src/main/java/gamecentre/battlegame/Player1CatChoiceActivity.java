package gamecentre.battlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gamecentre.slidingtiles.R;

/**
 * Activity for choosing the cat to play.
 */
public class Player1CatChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlegame_yourcatcharacter);

        addNinjaButtonListener();
        addSamuraiButtonListener();
        addShamanButtonListener();

    }

    /**
     * Add the Shaman button.
     */
    private void addShamanButtonListener() {
        Button shamanButton = findViewById(R.id.shaman);
        shamanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToPlayer2DogChoiceActivity();
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
                switchToPlayer2DogChoiceActivity();
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
                switchToPlayer2DogChoiceActivity();
            }
        });
    }

    /**
     * Switch to the Player2DogChoiceActivity view.
     */
    private void switchToPlayer2DogChoiceActivity() {
        Intent tmp = new Intent(this, Player2DogChoiceActivity.class);
        startActivity(tmp);
    }
}
