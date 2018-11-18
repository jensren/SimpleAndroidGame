package cardmatching;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class MatchingBoard extends Observable implements Serializable {
    /**
     * The number of rows.
     */
    private final static int numRows = 4;

    /**
     * The number of rows.
     */
    private final static int numCols = 4;

    /**
     * The question mark cards.
     */
    private MatchingTile[][] unknownTiles = new MatchingTile[numRows][numCols];

    /**
     * The actual image cards.
     */
    private MatchingTile[][] tiles = new MatchingTile[numRows][numCols];

    /**
     * Creates two "boards" of tiles. One of all unknown tiles. One of the tile images.
     * @param tiles the tiles of actual card images for the board.
     */
    MatchingBoard(List<MatchingTile> tiles) {
        Iterator<MatchingTile> iter = tiles.iterator();

        for (int row = 0; row != MatchingBoard.numRows; row++) {
            for (int col = 0; col != MatchingBoard.numCols; col++) {
                this.tiles[row][col] = iter.next();
                this.unknownTiles[row][col] = new MatchingTile(16);
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
        return tiles[row][col];
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }
}
