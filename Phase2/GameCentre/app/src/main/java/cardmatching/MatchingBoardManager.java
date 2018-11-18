package cardmatching;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchingBoardManager implements Serializable {
    /**
     * The board being managed.
     */
    private MatchingBoard board;

    /**
     * Keeps track of number of cards flipped. Up to 2.
     */
    private int tilesCurrentlyFlipped = 0;

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

    boolean isValidTap(int position) {
        int row = position / MatchingBoard.numCols;
        int col = position % MatchingBoard.numCols;
        int blankId = board.numTiles() + 2;
        return board.getTile(row, col).getId() != blankId;
    }

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
                board.checkMatching(flippedTiles);
                tilesCurrentlyFlipped++;
            } else {
                board.flipBack(flippedTiles[0],flippedTiles[1]);
                board.flipBack(flippedTiles[2],flippedTiles[3]);
                flippedTiles = new int[4];
            }
        }
    }
}
