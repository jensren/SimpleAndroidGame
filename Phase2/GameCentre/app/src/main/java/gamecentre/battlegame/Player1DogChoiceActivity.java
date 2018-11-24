package gamecentre.battlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gamecentre.slidingtiles.R;

/**
 * Activity for choosing the dog to play.
 */
public class Player1DogChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlegame_p1_dog_character);

        addDetectiveButtonListener();
        addDruidButtonListener();
        addSirShibeButtonListener();

    }

    /**
     * Add the Sir Shibe button.
     */
    private void addSirShibeButtonListener() {
        Button sirShibeButton = findViewById(R.id.sir);
        sirShibeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPlayer2CatChoiceActivity("SirShibe");
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
                switchToPlayer2CatChoiceActivity("DetectiveShibe");
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
                switchToPlayer2CatChoiceActivity("DruidShibe");
            }
        });
    }

    /**
     * Switch to the Player2CatChoiceActivity view.
     */
    private void switchToPlayer2CatChoiceActivity(String character) {
        Intent tmp = new Intent(this, Player2CatChoiceActivity.class);
        Bundle extras = new Bundle();
        extras.putString("player1", character);
        tmp.putExtras(extras);
        startActivity(tmp);
    }
}
