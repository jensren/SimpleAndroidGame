package gamecentre;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import slidingtiles.R;
import slidingtiles.SlidingtilesStartingActivity;

/**
 * The activity for choosing a game.
 */
public class GameChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamechoice);

        addSlidingTilesButtonListener();
    }

    /**
     * Add a SlidingTiles game button.
     */
    private void addSlidingTilesButtonListener() {
        Button slidingTilesButton = findViewById(R.id.slidingtiles);
        slidingTilesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSlidingTilesActivity();
            }
        });
    }

    /**
     * Display the sliding tiles game activity.
     */
    private void switchToSlidingTilesActivity() {
        Intent tmp = new Intent(this, SlidingtilesStartingActivity.class);
        startActivity(tmp);
    }
}
