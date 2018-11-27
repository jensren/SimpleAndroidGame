package gamecentre.battlegame;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import gamecentre.slidingtiles.R;

/**
 * The game activity.
 */
public class BattleGameActivity extends AppCompatActivity {

    /**
     * The battle queue
     */
    private BattleQueue battleQueue = new BattleQueue();
    /**
     * Player 1's character
     */
    private Character player1;
    /**
     * Player 2's character
     */
    private Character player2;
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

    //TODO: save battle game to files
    //TODO: extract a class from BattleGameActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadFromFile(BattleStartingActivity.tempSaveFileName);
        setContentView(R.layout.activity_battlegame_main);

        catImage = findViewById(R.id.catimage);
        dogImage = findViewById(R.id.dogimage);

        addRegularMoveButtonListener();
        addSpecialMoveButtonListener();
        addUndoButtonListener();

        if (battleQueue.getPlayer1() == null || battleQueue.getPlayer2() == null) {
            setCharacters();
            setOpponent();
            initializeBattleQueue();
        } else {
            setCharacters();
            setOpponent();
            player1 = battleQueue.getPlayer1();
            player2 = battleQueue.getPlayer2();
            player1.setBattleQueue(battleQueue);
            player2.setBattleQueue(battleQueue);
        }

        initializeHpMp();
        updateCharacterPoints();
        setSprites();
        displayTurn(player1);
    }

    /**
     * Initialize the HP and MP of player1 and player2.
     */
    private void initializeHpMp() {
        if (player1.getType().equals("cat")) {
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
     * Add player1 and player2 to the battle queue, and set the battle queues of player1 and
     * player2.
     */
    private void initializeBattleQueue() {
        player1.setBattleQueue(battleQueue);
        player2.setBattleQueue(battleQueue);
        battleQueue.add(player1);
        battleQueue.add(player2);
    }

    /**
     * Set the opponent of player1 as player2, and the opponent of player2 as player1.
     */
    private void setOpponent() {
        player1.setOpponent(player2);
        player2.setOpponent(player1);
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
        String player1Sprite = player1.getSprite() + "0";
        String player2Sprite = player2.getSprite() + "0";

        if (player1.getType().equals("cat")) {
            catImage.setImageResource(getImageId(this, player1Sprite));
            dogImage.setImageResource(getImageId(this, player2Sprite));
        } else {
            dogImage.setImageResource(getImageId(this, player1Sprite));
            catImage.setImageResource(getImageId(this, player2Sprite));
        }
    }

//    public Character getPlayer1() {
//        return player1;
//    }
//
//    public Character getPlayer2() {
//        return player2;
//    }

    /**
     * Set player1 and player2 as their respective characters.
     */
    private void setCharacters() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String p1Class = extras.getString("player1");
            String p2Class = extras.getString("player2");
            if (p1Class != null && p2Class != null) {
                switch (p1Class) {
                    case "NinjaCat":
                        player1 = new NinjaCat();
                        break;
                    case "SamuraiCat":
                        player1 = new SamuraiCat();
                        break;
                    case "ShamanCat":
                        player1 = new ShamanCat();
                        break;
                    case "DetectiveShibe":
                        player1 = new DetectiveShibe();
                        break;
                    case "SirShibe":
                        player1 = new SirShibe();
                        break;
                    case "DruidShibe":
                        player1 = new DruidShibe();
                        break;
                }
                switch (p2Class) {
                    case "NinjaCat":
                        player2 = new NinjaCat();
                        break;
                    case "SamuraiCat":
                        player2 = new SamuraiCat();
                        break;
                    case "ShamanCat":
                        player2 = new ShamanCat();
                        break;
                    case "DetectiveShibe":
                        player2 = new DetectiveShibe();
                        break;
                    case "SirShibe":
                        player2 = new SirShibe();
                        break;
                    case "DruidShibe":
                        player2 = new DruidShibe();
                        break;
                }
            } else {
                throw new NullPointerException("Character is null");
            }
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
                Character character = battleQueue.getNextCharacter();

                if (character.hasAttackMp()) {
                    character.specialMove();
                    final String sprite1 = character.getSprite() + "5";
                    final String sprite2 = character.getSprite() + "0";
                    displayTurn(character);

                    displayAttackImage(character, sprite1, sprite2);
                    Toast.makeText(getApplicationContext(), "SPECIAL", Toast.LENGTH_SHORT).show();
                }
                updateCharacterPoints();

                if (battleQueue.getWinner() != null) {
                    Toast.makeText(getApplicationContext(), "Game over!", Toast.LENGTH_SHORT).show();
                    switchToScoreBoardActivity();
                } else {
                    Character nextCharacter = battleQueue.getNextCharacter();
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
                Character character = battleQueue.getNextCharacter();
                final String sprite1 = character.getSprite() + "2";
                final String sprite2 = character.getSprite() + "0";
                displayTurn(character);

                displayAttackImage(character, sprite1, sprite2);
                character.regularMove();
                Toast.makeText(getApplicationContext(), "Regular", Toast.LENGTH_SHORT).show();
                updateCharacterPoints();

                if (battleQueue.getWinner() != null) {
                    Toast.makeText(getApplicationContext(), "Game over!", Toast.LENGTH_SHORT).show();
                    switchToScoreBoardActivity();
                } else {
                    Character nextCharacter = battleQueue.getNextCharacter();
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
                if (battleQueue.isInValidUndo()) {
                    Toast.makeText(getApplicationContext(), "Invalid Undo", Toast.LENGTH_SHORT).show();
                } else {
                    battleQueue.undo();
                    Character nextCharacter = battleQueue.getNextCharacter();
                    displayTurn(nextCharacter);

                    Toast.makeText(getApplicationContext(), "Undo", Toast.LENGTH_SHORT).show();
                    updateCharacterPoints();
                }
            }
        });
    }

    /**
     * Update both character's MP and HP on the Main Activity after each attack is performed.
     */
    private void updateCharacterPoints() {
        String p1Hp = "HP: " + player1.getHp();
        String p1Mp = "MP: " + player1.getMp();
        String p2Hp = "HP: " + player2.getHp();
        String p2Mp = "MP: " + player2.getMp();
        player1Hp.setText(p1Hp);
        player1Mp.setText(p1Mp);
        player2Mp.setText(p2Mp);
        player2Hp.setText(p2Hp);
    }

    /**
     * Load the battle queue from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                battleQueue = (BattleQueue) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the battle queue to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(battleQueue);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(BattleStartingActivity.tempSaveFileName);
    }

    /**
     * Switches to the scoreboard activity.
     */
    private void switchToScoreBoardActivity() {
        Intent tmp = new Intent(this, BattleScoreboardActivity.class);
        startActivity(tmp);
    }
}
