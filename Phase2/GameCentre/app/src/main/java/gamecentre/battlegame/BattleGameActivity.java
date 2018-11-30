package gamecentre.battlegame;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import gamecentre.slidingtiles.R;

/*
 * The image for the background was taken from https://www.pinterest.ca/pin/374784000221729233/
 */

/**
 * The game activity.
 */
public class BattleGameActivity extends AppCompatActivity {

    /**
     * The battle queue
     */
    private BattleQueueManager battleQueueManager = new BattleQueueManager();
    /**
     * Player 1's MP
     */
    private TextView player1Mp;
    /**
     * Player 1's HP
     */
    private TextView player1Hp;
    /**
     * Player 2's MP
     */
    private TextView player2Mp;
    /**
     * Player 2's HP
     */
    private TextView player2Hp;
    /**
     * The ImageView for the cat's image
     */
    private ImageView catImage;
    /**
     * The ImageView for the dog's image
     */
    private ImageView dogImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_battlegame_main);

        catImage = findViewById(R.id.catimage);
        dogImage = findViewById(R.id.dogimage);

        addRegularMoveButtonListener();
        addSpecialMoveButtonListener();
        addUndoButtonListener();

        setCharacters();
        battleQueueManager.initializeBattleQueue();
        initializeHpMpView();
        updateCharacterPointsView();
        setSprites();
        displayTurn(battleQueueManager.getPlayer1());
    }

    /**
     * Initialize the HP and MP views of player1 and player2.
     */
    private void initializeHpMpView() {
        if (battleQueueManager.getPlayer1().getType().equals("cat")) {
            player1Mp = findViewById(R.id.catmp);
            player1Hp = findViewById(R.id.cathp);
            player2Mp = findViewById(R.id.dogmp);
            player2Hp = findViewById(R.id.doghp);
        } else {
            player1Mp = findViewById(R.id.dogmp);
            player1Hp = findViewById(R.id.doghp);
            player2Mp = findViewById(R.id.catmp);
            player2Hp = findViewById(R.id.cathp);
        }
    }

    /**
     * Return the id of the image.
     *
     * @param context   The current context
     * @param imageName The name of the image
     * @return the id of the image
     */
    // Adapted from: https://stackoverflow.com/questions/6783327/setimageresource-from-a-string
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null,
                context.getPackageName());
    }

    /**
     * Display the initial sprites.
     */
    private void setSprites() {
        String player1Sprite = battleQueueManager.getPlayer1().getSprite() + "0";
        String player2Sprite = battleQueueManager.getPlayer2().getSprite() + "0";

        if (battleQueueManager.getPlayer1().getType().equals("cat")) {
            catImage.setImageResource(getImageId(this, player1Sprite));
            dogImage.setImageResource(getImageId(this, player2Sprite));
        } else {
            dogImage.setImageResource(getImageId(this, player1Sprite));
            catImage.setImageResource(getImageId(this, player2Sprite));
        }
    }

    /**
     * Set player1 and player2 as their respective characters.
     */
    private void setCharacters() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String p1Class = extras.getString("player1");
            String p2Class = extras.getString("player2");
            if (p1Class != null && p2Class != null) {
                battleQueueManager.setCharacters(p1Class, p2Class);
            }
        } else {
            throw new NullPointerException("Character is null");
        }
    }

    /**
     * Add the special move button.
     */
    private void addSpecialMoveButtonListener() {
        Button specialMoveButton = findViewById(R.id.specialmove);
        specialMoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Character character = battleQueueManager.getBattleQueue().getNextCharacter();
                if (character.hasAttackMp()) {
                    character.specialMove();
                    final String sprite1 = character.getSprite() + "5";
                    final String sprite2 = character.getSprite() + "0";
                    displayAttackImage(character, sprite1, sprite2);
                } else {
                    Toast.makeText(getApplicationContext(), "No more MP", Toast.LENGTH_SHORT).show();
                }
                updateCharacterPointsView();
                if (battleQueueManager.getBattleQueue().getWinner() != null) {
                    switchToScoreBoardActivity();
                } else {
                    Character nextCharacter = battleQueueManager.getBattleQueue().getNextCharacter();
                    displayTurn(nextCharacter);
                }
            }
        });
    }

    /**
     * Display the attack sprite for the character.
     *
     * @param character The character who is attacking
     * @param sprite1   The attack image
     * @param sprite2   The original image
     */
    private void displayAttackImage(Character character, String sprite1, final String sprite2) {
        if (character.getType().equals("cat")) {
            catImage.setImageResource(getImageId(BattleGameActivity.this, sprite1));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    catImage.setImageResource(getImageId(BattleGameActivity.this, sprite2));
                }
            }, 350);
        } else {
            dogImage.setImageResource(getImageId(BattleGameActivity.this, sprite1));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dogImage.setImageResource(getImageId(BattleGameActivity.this, sprite2));
                }
            }, 350);
        }
    }

    /**
     * Add the regular move button.
     */
    private void addRegularMoveButtonListener() {
        Button regularMoveButton = findViewById(R.id.regularmove);
        regularMoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Character character = battleQueueManager.getBattleQueue().getNextCharacter();
                final String sprite1 = character.getSprite() + "2";
                final String sprite2 = character.getSprite() + "0";
                displayTurn(character);

                displayAttackImage(character, sprite1, sprite2);
                character.regularMove();
                updateCharacterPointsView();

                if (battleQueueManager.getBattleQueue().getWinner() != null) {
                    switchToScoreBoardActivity();
                } else {
                    Character nextCharacter = battleQueueManager.getBattleQueue().getNextCharacter();
                    displayTurn(nextCharacter);
                }
            }
        });
    }

    /**
     * Update the TextView to show that it is character's turn.
     *
     * @param character The character whose turn it is
     */
    private void displayTurn(Character character) {
        TextView turnView = findViewById(R.id.turn);
        String text = character.getType() + "'s turn";
        turnView.setText(text);
    }

    /**
     * Add the undo button
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (battleQueueManager.getBattleQueue().isInValidUndo()) {
                    Toast.makeText(getApplicationContext(), "Invalid Undo", Toast.LENGTH_SHORT).show();
                } else {
                    battleQueueManager.getBattleQueue().undo();
                    Character nextCharacter = battleQueueManager.getBattleQueue().getNextCharacter();
                    displayTurn(nextCharacter);
                    updateCharacterPointsView();
                }
            }
        });
    }

    /**
     * Update both character's MP and HP on the Main Activity after each attack is performed.
     */
    private void updateCharacterPointsView() {
        String p1Hp = "HP: " + battleQueueManager.getPlayer1().getHp();
        String p1Mp = "MP: " + battleQueueManager.getPlayer1().getMp();
        String p2Hp = "HP: " + battleQueueManager.getPlayer2().getHp();
        String p2Mp = "MP: " + battleQueueManager.getPlayer2().getMp();
        player1Hp.setText(p1Hp);
        player1Mp.setText(p1Mp);
        player2Mp.setText(p2Mp);
        player2Hp.setText(p2Hp);
    }

    /**
     * Switches to the scoreboard activity.
     */
    private void switchToScoreBoardActivity() {
        Intent tmp = new Intent(this, BattleScoreboardActivity.class);
        startActivity(tmp);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
