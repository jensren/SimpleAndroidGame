package cardmatching;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import slidingtiles.R;

public class MatchingBoard extends Observable implements Serializable {
    /**
     * The number of rows.
     */
    final static int numRows = 4;

    /**
     * The number of rows.
     */
    final static int numCols = 4;

    /**
     * The question mark cards.
     */
    public MatchingTile[][] unknownTiles = new MatchingTile[numRows][numCols];

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
                this.unknownTiles[row][col] = new MatchingTile(16,R.drawable.card_unknown);
            }
        }
    }
    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return numCols * numRows;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    MatchingTile getTile(int row, int col) {
        return unknownTiles[row][col];
    }

    public void flipTile(int row, int col){
        unknownTiles[row][col] = tiles[row][col];
    }

    public void flipBack(int row, int col){
        unknownTiles[row][col] = new MatchingTile(16);
    }

    public void flipBlank(int[] flippedTiles){
        unknownTiles[flippedTiles[0]][flippedTiles[1]] = new MatchingTile(17);
        unknownTiles[flippedTiles[2]][flippedTiles[3]] = new MatchingTile(17);
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }
}
