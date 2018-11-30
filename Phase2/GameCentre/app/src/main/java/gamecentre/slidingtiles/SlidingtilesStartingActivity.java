package gamecentre.slidingtiles;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import gamecentre.Serializer;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingtilesStartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static String saveFileName;
    /**
     * A temporary save file.
     */
    public static String tempSaveFileName;
    /**
     * The auto saved file.
     */
    public static String autoSaveFileName;
    /**
     * The board manager.
     */
    private SlidingtilesBoardManager boardManager;
    /**
     * The serializer for this activity
     */
    Serializer serializer = new Serializer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardManager = new SlidingtilesBoardManager();
        serializer.saveBoardManagerToFile(tempSaveFileName, boardManager, this);
        SlidingtilesScoreboard.reset();

        setContentView(R.layout.activity_slidingtiles_starting);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addBoardSizeListener();
        addScoreboardButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlidingtilesBoard.numCols = SlidingtilesBoard.size;
                SlidingtilesBoard.numRows = SlidingtilesBoard.size;
                boardManager = new SlidingtilesBoardManager();
                switchToGame();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = serializer.loadBoardManagerFromFile(autoSaveFileName, SlidingtilesStartingActivity.this);
                serializer.saveBoardManagerToFile(tempSaveFileName, boardManager, SlidingtilesStartingActivity.this);
                serializer.saveBoardManagerToFile(autoSaveFileName, boardManager, SlidingtilesStartingActivity.this);
                makeToastLoadedText();
                switchToGame();
            }
        });
    }

    /**
     * Activate the scoreboard button.
     */
    private void addScoreboardButtonListener() {
        Button loadButton = findViewById(R.id.ScoreboardButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serializer.saveBoardManagerToFile(saveFileName, boardManager, SlidingtilesStartingActivity.this);
                serializer.saveBoardManagerToFile(tempSaveFileName, boardManager, SlidingtilesStartingActivity.this);
                makeToastSavedText();
            }
        });
    }

    /**
     * Activate the board size radio group.
     */
    private void addBoardSizeListener() {
        final RadioGroup radio = (RadioGroup) findViewById(R.id.Radio_Group);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                switch (index) {
                    case 0: // 3x3 case
                        SlidingtilesBoard.size = 3;
                        break;
                    case 1: // 4x4 case
                        SlidingtilesBoard.size = 4;
                        break;
                    case 2: // 5x5 case
                        SlidingtilesBoard.size = 5;
                        break;
                }
            }
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the score board activity.
     */
    private void switchToScoreboard() {
        Intent m = new Intent(this, SlidingtilesScoreboardActivity.class);
        startActivity(m);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        serializer.saveBoardManagerToFile(SlidingtilesStartingActivity.tempSaveFileName, boardManager, this);
        startActivity(tmp);
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        serializer.loadBoardManagerFromFile(tempSaveFileName, this);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}

