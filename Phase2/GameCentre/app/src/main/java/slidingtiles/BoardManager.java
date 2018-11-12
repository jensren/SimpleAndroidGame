package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Stack to store moves made
     */
    private ArrayList<int[]> previousMoves = new ArrayList<>();

    /**
     * Number of moves made
     */
    private int numMoves = 0;

    /**
     * Manage a new shuffled board.
     */
    BoardManager() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.numRows * Board.numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }

        Collections.shuffle(tiles);
        this.board = new Board(tiles);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        int TileId = 0;
        for (Tile t : board) {
            TileId++;
            if (t.getId() != TileId) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the position of the surrounding blank tile as [row, column]. If there is no
     * surrounding blank tile, return [-1, -1].
     *
     * @param position the tile to check
     * @return the position of the surrounding blank tile
     */
    private int[] blankTilePosition(int position) {
        int row = position / Board.numCols;
        int col = position % Board.numCols;
        int blankId = board.numTiles();

        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.numRows - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.numCols - 1 ? null : board.getTile(row, col + 1);

        if (above != null && above.getId() == blankId) {
            return new int[]{row - 1, col, row, col};
        } else if (below != null && below.getId() == blankId) {
            return new int[]{row + 1, col, row, col};
        } else if (left != null && left.getId() == blankId) {
            return new int[]{row, col - 1, row, col};
        } else if (right != null && right.getId() == blankId) {
            return new int[]{row, col + 1, row, col};
        }
        return new int[]{-1, -1};
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {
        int[] blankPosition = blankTilePosition(position);
        int blankRow = blankPosition[0];
        int blankCol = blankPosition[1];

        return blankRow != -1 && blankCol != -1;
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {
        int row = position / Board.numCols;
        int col = position % Board.numCols;

        if (isValidTap(position)) {
            int[] toSwap = blankTilePosition(position);
            board.swapTiles(row, col, toSwap[0], toSwap[1]);
        }
    }

    /**
     * Add the move at position to the undo stack
     *
     * @param position the position of the tile swapped
     */
    void updateUndoStack(int position) {
        previousMoves.add(blankTilePosition(position).clone());
    }

    /**
     * Return if the tap of undo button is valid
     *
     * @return whether the undo is valid
     */
    boolean isInValidUndo() {
        return previousMoves.size() == 0;
    }

    /**
     * Undo the last move made, if there is a move to undo.
     */
    public void undo() {
        if (previousMoves.size() > 0) {
            int[] lastMove = previousMoves.remove(previousMoves.size() - 1);
            board.swapTiles(lastMove[0], lastMove[1], lastMove[2], lastMove[3]);
            updateMoves();
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

    /**
     * @return the number of rows of the current board
     */
    int getBoardSize() {
        return Board.numRows;
    }

}
