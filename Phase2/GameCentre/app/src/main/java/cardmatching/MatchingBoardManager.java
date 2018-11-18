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
}
