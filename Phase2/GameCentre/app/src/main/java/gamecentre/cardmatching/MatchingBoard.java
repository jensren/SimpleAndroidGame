package gamecentre.cardmatching;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import gamecentre.Board;
import gamecentre.slidingtiles.R;

public class MatchingBoard extends Board implements Serializable {

    /**
     * Number of rows in matching board.
     */
    final static int numRows = 4;

    /**
     * Number of columns in matching board.
     */
    final static int numCols = 4;
    /**
     * The question mark cards.
     */
    private MatchingTile[][] unknownTiles = new MatchingTile[numRows][numCols];

    /**
     * The actual image cards.
     */
    public MatchingTile[][] tiles = new MatchingTile[numRows][numCols];

    /**
     * Creates two "boards" of tiles. One of all unknown tiles. One of the tile images.
     * @param tiles the tiles of actual card images for the board.
     */
    MatchingBoard(List<MatchingTile> tiles) {
        Iterator<MatchingTile> iter = tiles.iterator();

        for (int row = 0; row != MatchingBoard.numRows; row++) {
            for (int col = 0; col != MatchingBoard.numCols; col++) {
                this.tiles[row][col] = iter.next();
                this.unknownTiles[row][col] = new MatchingTile(16, R.drawable.card_unknown);
            }
        }
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    MatchingTile matchingGetTile(int row, int col) {
        return unknownTiles[row][col];
    }

    /**
     * Flips over an unknown tile to show what image it has.
     * @param row Row of tile to be flipped.
     * @param col Column of tile to be flipped.
     */
    public void flipTile(int row, int col){
        unknownTiles[row][col] = tiles[row][col];
        listenerUpdate();
    }

    /**
     * Flips back a flipped over tile.
     * @param row Row of tile to be flipped.
     * @param col Column of tile to be flipped.
     */
    public void flipBack(int row, int col){
        unknownTiles[row][col] = new MatchingTile(16,R.drawable.card_unknown);
        listenerUpdate();
    }

    /**
     * Changes two tiles to be blank, indicating that they have been removed from the board.
     * @param flippedTiles Array containing the row and column indices of the two tiles to be flipped to blank.
     */
    public void flipBlank(int[] flippedTiles){
        unknownTiles[flippedTiles[0]][flippedTiles[1]] = new MatchingTile(17,R.drawable.tile_25);
        unknownTiles[flippedTiles[2]][flippedTiles[3]] = new MatchingTile(17,R.drawable.tile_25);
        listenerUpdate();
    }

    void setTiles(int row, int col, MatchingTile tile){
        unknownTiles[row][col] = tile;
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(unknownTiles) +
                '}';
    }
}
