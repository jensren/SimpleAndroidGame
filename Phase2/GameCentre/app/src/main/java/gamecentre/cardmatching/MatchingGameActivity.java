package gamecentre.cardmatching;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import gamecentre.BoardUpdateListener;
import gamecentre.OnWinListener;
import gamecentre.Serializer;
import gamecentre.slidingtiles.CustomAdapter;
import gamecentre.slidingtiles.R;

/**
 * The game activity for the matching game.
 */
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
     * of positions, updates the display for user's number of moves, and then call the adapter to
     * set the view.
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
        boardManager = serializer.loadMatchingBoardManagerFromFile(
                MatchingStartingActivity.matchingTempSaveFileName, this);
        createTileButtons(this);
        setContentView(R.layout.activity_cardmatching_main);

        // Add View to activity
        gridView = findViewById(R.id.matching_grid);
        gridView.setNumColumns(MatchingBoard.numCols);
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().setBoardUpdateListener(new BoardUpdateListener() {
            @Override
            public void onBoardChanged() {   //Sets the board update listener. Will update display when board updates.
                display();
                serializer.saveMatchingBoardManagerToFile(
                        MatchingStartingActivity.matchingAutoSaveFileName, boardManager,
                        MatchingGameActivity.this);
            }
        });
        gridView.mController.setOnWinListener(new OnWinListener() {  //Sets the win listener. Will switch to scoreboard when the game is won.
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
                tmp.setBackgroundResource(board.matchingGetTile(row, col).getBackground());
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
            b.setBackgroundResource(board.matchingGetTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        serializer.saveMatchingBoardManagerToFile(MatchingStartingActivity.matchingTempSaveFileName,
                boardManager, this);
        serializer.saveMatchingBoardManagerToFile(MatchingStartingActivity.matchingAutoSaveFileName,
                boardManager, this);
    }

    /**
     * Switches to the scoreboard activity.
     */
    private void switchToScoreBoardActivity() {
        Intent tmp = new Intent(this, MatchingScoreboardActivity.class);
        startActivity(tmp);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
