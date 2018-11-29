package gamecentre.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gamecentre.BoardUpdateListener;
import gamecentre.OnWinListener;
import gamecentre.SaveAndLoad;

/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity {

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * The saver and loader.
     */
    SaveAndLoad saveAndLoad = new SaveAndLoad();

    /**
     * The gridview for the game
     */
    private GestureDetectGridView gridView;
    /**
     * Calculated column height and width based on device size
     */
    private static int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardManager = saveAndLoad.loadBoardManagerFromFile(SlidingtilesStartingActivity.tempSaveFileName);
        createTileButtons(this);
        setContentView(R.layout.activity_slidingtiles_main);
        Button undoButton = findViewById(R.id.Undo);


        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(Board.numCols);
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().setBoardUpdateListener(new BoardUpdateListener() {
            @Override
            public void onBoardChanged() {
                display();
                saveAndLoad.saveBoardManagerToFile(SlidingtilesStartingActivity.autoSaveFileName, boardManager);
            }
        });
        gridView.mController.setOnWinListener(new OnWinListener() {
            @Override
            public void onWin() {
                switchToScoreBoardActivity();
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boardManager.isInValidUndo()) {
                    Toast.makeText(getApplicationContext(), "Invalid Undo", Toast.LENGTH_SHORT).show();
                }
                boardManager.undo();
            }
        });

        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / Board.numCols;
                        columnHeight = displayHeight / Board.numRows;

                        display();
                    }
                });
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != Board.numRows; row++) {
            for (int col = 0; col != Board.numCols; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / Board.numRows;
            int col = nextPos % Board.numCols;
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveAndLoad.saveBoardManagerToFile(SlidingtilesStartingActivity.tempSaveFileName, boardManager);
        saveAndLoad.saveBoardManagerToFile(SlidingtilesStartingActivity.autoSaveFileName, boardManager);
    }


    private void switchToScoreBoardActivity() {
        Intent tmp = new Intent(this, SlidingtilesScoreboardActivity.class);
        startActivity(tmp);
    }

}
