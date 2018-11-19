package cardmatching;

import android.os.Handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import slidingtiles.R;

public class MatchingBoardManager implements Serializable {
    /**
     * The board being managed.
     */
    private MatchingBoard board;

    /**
     * Keeps track of number of cards flipped. Up to 2.
     */
    private int tilesCurrentlyFlipped = 0;
    /**
     * Keeps track of the row and col indices of the two flipped tiles.
     */
    private int[] flippedTiles = new int[4];

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    MatchingBoardManager(MatchingBoard board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    MatchingBoard getBoard() {
        return board;
    }

    /**
     * Number of moves made
     */
    private int numMoves = 0;

    /**
     * Manage a new shuffled board.
     */
    MatchingBoardManager() {
        List<MatchingTile> tiles = new ArrayList<>();
        final int numTiles = MatchingBoard.numRows * MatchingBoard.numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new MatchingTile(tileNum));
        }

        Collections.shuffle(tiles);
        this.board = new MatchingBoard(tiles);
    }
    //TODO: Bug: Clicking the top left tile first always results in invalid tap.
    boolean isValidTap(int position) {
        int row = position / MatchingBoard.numCols;
        int col = position % MatchingBoard.numCols;
        if (board.tiles[flippedTiles[0]][flippedTiles[1]] == board.tiles[row][col]){
            return false;
        }
        int blankId = 17;
        return board.getTile(row, col).getId() != blankId;
    }

    boolean isWin() {
        for (int row = 0; row <= 3; row++){
            for (int col = 0; col <= 3; col++){
                if (board.unknownTiles[row][col].compareTo(new MatchingTile(17, R.drawable.tile_25))!=0){
                    return false;
                }
            }
        }
        return true;
    }
    //TODO: Bug:If player clicks something during the 0.5s time window it breaks things.
    void touchMove(int position){
        int row = position / MatchingBoard.numCols;
        int col = position % MatchingBoard.numCols;

        if (isValidTap(position)) {
            if (tilesCurrentlyFlipped == 0){
                board.flipTile(row,col);
                flippedTiles[0] = row;
                flippedTiles[1] = col;
                tilesCurrentlyFlipped++;
            } else if(tilesCurrentlyFlipped == 1){
                board.flipTile(row,col);
                flippedTiles[2] = row;
                flippedTiles[3] = col;
                new Handler().postDelayed(new Runnable() {   //Waits 0.5 second and then checks matching and flips over accordingly
                    @Override
                    public void run() {
                        checkMatching();
                    }
                }, 500);
                //checkMatching();
                tilesCurrentlyFlipped++;
            } else {
                //checkMatching();
                flippedTiles = new int[4];
                tilesCurrentlyFlipped = 0;
                board.flipTile(row,col);
                flippedTiles[0] = row;
                flippedTiles[1] = col;
                tilesCurrentlyFlipped++;
                }
            }
        }
    private void checkMatching(){
        if(board.tiles[flippedTiles[0]][flippedTiles[1]].compareTo(board.tiles[flippedTiles[2]][flippedTiles[3]])==0){
            board.flipBlank(flippedTiles);
        } else {
            board.flipBack(flippedTiles[0],flippedTiles[1]);
            board.flipBack(flippedTiles[2],flippedTiles[3]);
        }
    }

    /**
     * count the number of total moves made
     */
    void updateMoves() {
        numMoves += 1;
    }

    /**
     * @return the highest score
     */

    int getNumMoves() {
        return numMoves;
    }

    int getBoardSize() {
        return MatchingBoard.numRows;
    }
}
