package gamecentre.battlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gamecentre.slidingtiles.R;

/**
 * The instructions activity.
 */
public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_battlegame_starting);

        addMainButtonListener();
    }

    /**
     * Add the main button to go back to the main game screen.
     */
    private void addMainButtonListener() {
        Button BattleButton = findViewById(R.id.instructions_main);
        BattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToStartingActivity();
            }
        });
    }

    /**
     * Switch to the starting activity for the battle game.
     */
    private void switchToStartingActivity() {
        Intent tmp = new Intent(this, BattleStartingActivity.class);
        startActivity(tmp);
    }
}
