package gamecentre.battlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gamecentre.slidingtiles.R;

/**
 * The instructions activity.
 */
public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_battlegame_instructions);

        addMainButtonListener();
        setText();
    }

    /**
     * Set the text in the instructions activity.
     */
    private void setText() {
        TextView tv1 = findViewById(R.id.t1);
        TextView tv2 = findViewById(R.id.t2);
        TextView tv3 = findViewById(R.id.t3);
        TextView tv4 = findViewById(R.id.t4);
        TextView tv5 = findViewById(R.id.t5);

        String text1 = "In this game, you play as either a cat or a dog, and battle. Whoever gets their opponent to 0 HP first, wins.";
        String text2 = "Each turn, you can play either a regular or a special move. Regular moves do not consume MP, whereas special moves consume an amount of MP dependant on your character. ";
        String text3 = "Ninja and Detective: Adds its opponent to the battle queue and then itself twice so that this character can attack twice in concession after its opponent attacks.";
        String text4 = "Samurai and Sir Shibe: Resets the battle queue so that there is only one copy of each character in the battle queue.";
        String text5 = "Shaman and Druid: Increases this characters Health Points by the amount of damage the opponent took.";

        tv1.setText(text1);
        tv2.setText(text2);
        tv3.setText(text3);
        tv4.setText(text4);
        tv5.setText(text5);

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
