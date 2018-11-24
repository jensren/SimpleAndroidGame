package gamecentre.battlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gamecentre.GameChoiceActivity;
import gamecentre.slidingtiles.GameActivity;
import gamecentre.slidingtiles.R;

/**
 * Activity for choosing the cat to play.
 */
public class Player2CatChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlegame_p2_cat_character);

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
                switchToGameActivity("ShamanCat");
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
                switchToGameActivity("SamuraiCat");
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
                switchToGameActivity("NinjaCat");
            }
        });
    }

    /**
     * @param character The name of the character chosen
     */
    private void switchToGameActivity(String character) {
        Intent tmp = new Intent(this, BattleGameActivity.class);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            extras.putString("player2", character);
        } else
            throw new NullPointerException("Value of Bundle is null");
        tmp.putExtras(extras);
        startActivity(tmp);
    }
}

