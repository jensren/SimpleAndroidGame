package gamecentre.cardmatching;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gamecentre.BoardUpdateListener;
import gamecentre.OnWinListener;
import gamecentre.Serializer;
import gamecentre.slidingtiles.CustomAdapter;
import gamecentre.slidingtiles.R;

public class MatchingGameActivity extends AppCompatActivity{
    /**
     * The board manager.
     */
    private MatchingBoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;
    /**
     * The gridview for this game
     */
    private MatchingGestureDetectGridView gridView;
    /**
     * Calculated column height and width based on device size
     */
    private static int columnWidth, columnHeight;
    /**
     * The serializer for this activity.
     */
    Serializer serializer = new Serializer();

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
        TextView score = findViewById(R.id.moves);
        String a = "Your moves: " + boardManager.getNumMoves();
        score.setText(a);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        boardManager = serializer.loadMatchingBoardManagerFromFile(MatchingStartingActivity.matchingTempSaveFileName);
        createTileButtons(this);
        setContentView(R.layout.activity_cardmatching_main);

        // Add View to activity
        gridView = findViewById(R.id.matching_grid);
        gridView.setNumColumns(MatchingBoard.numCols);
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().setBoardUpdateListener(new BoardUpdateListener() {
            @Override
            public void onBoardChanged() {
                display();
                serializer.saveMatchingBoardManagerToFile(MatchingStartingActivity.matchingAutoSaveFileName, boardManager);
            }
        });
        gridView.mController.setOnWinListener(new OnWinListener() {
            @Override
            public void onWin() {
                switchToScoreBoardActivity();
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

                        columnWidth = displayWidth / MatchingBoard.numCols;
                        columnHeight = displayHeight / MatchingBoard.numRows;

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
        MatchingBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != MatchingBoard.numRows; row++) {
            for (int col = 0; col != MatchingBoard.numCols; col++) {
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
        MatchingBoard board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / MatchingBoard.numRows;
            int col = nextPos % MatchingBoard.numCols;
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
        serializer.saveMatchingBoardManagerToFile(MatchingStartingActivity.matchingTempSaveFileName, boardManager);
        serializer.saveMatchingBoardManagerToFile(MatchingStartingActivity.matchingAutoSaveFileName, boardManager);
    }

    /**
     * Switches to the scoreboard activity.
     */
    private void switchToScoreBoardActivity() {
        Intent tmp = new Intent(this, MatchingScoreboardActivity.class);
        startActivity(tmp);
    }
}
