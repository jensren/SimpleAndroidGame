package gamecentre.cardmatching;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import gamecentre.slidingtiles.R;

import static org.junit.Assert.*;

public class MatchingBoardAndTileTest {
    /**
     * The matching board manager for testing
     */
    private MatchingBoardManager boardManager;

    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    private List<MatchingTile> makeTiles() {
        List<MatchingTile> tiles = new ArrayList<>();
        final int numTiles = MatchingBoard.numRows * MatchingBoard.numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if (tileNum >= 8) {
                tiles.add(new MatchingTile(tileNum - 8 + 1, tileNum - 8));
            } else {
                tiles.add(new MatchingTile(tileNum + 1, tileNum));
            }
        }

        return tiles;
    }

    /**
     * Make a solved board. (All images flipped over)
     */
    private void setUpCorrect() {
        List<MatchingTile> tiles = makeTiles();
        MatchingBoard board = new MatchingBoard(tiles);
        boardManager = new MatchingBoardManager(board);
    }

    /**
     * Sets board to a won state: All tiles flipped to blank and tiles matched set to 16.
     */
    private void changeToBlank() {
        MatchingTile tile = new MatchingTile(17, R.drawable.tile_25);
        for (int row = 0; row != MatchingBoard.numRows; row++) {
            for (int col = 0; col != MatchingBoard.numCols; col++) {
                boardManager.getBoard().setTiles(row, col, tile);
            }
        }
        boardManager.tilesMatched = 16;
    }

    /**
     * Flips the first tile
     */
    private void flipFirstTile() {
        boardManager.touchMove(0);
    }

    /**
     * Flips the last tile
     */
    private void flipLastTile() {
        boardManager.touchMove(15);
    }

    /**
     * Sets board to have 14 tiles matched, and you flip over the last two remaining tiles on the board.
     */
    private void FlipBlankLastTwo() {
        MatchingTile tile = new MatchingTile(17, R.drawable.tile_25);
        for (int row = 0; row != MatchingBoard.numRows; row++) {
            for (int col = 0; col != MatchingBoard.numCols - 1; col++) {
                boardManager.getBoard().setTiles(row, col, tile);
            }
        }
        boardManager.getBoard().setTiles(0, 3, tile);
        boardManager.getBoard().setTiles(2,3, tile);
        boardManager.tilesMatched = 14;
        boardManager.touchMove(7);
        boardManager.touchMove(15);
    }

    /**
     * Set up board of all tile images. Flip all of the tiles to blank except last two tiles
     * and then touch the 14th tile to flip it back.
     */
    private void flipBack14th() {
        MatchingTile tile = new MatchingTile(17, R.drawable.tile_25);
        for (int row = 0; row != MatchingBoard.numRows - 1; row++) {
            for (int col = 0; col != MatchingBoard.numCols; col++) {
                boardManager.getBoard().setTiles(row, col, tile);
            }
        }
        boardManager.getBoard().setTiles(3, 0, tile);
        boardManager.getBoard().setTiles(3, 1, tile);
        boardManager.tilesMatched = 14;
        boardManager.touchMove(14);
    }

    /**
     * Update number of moves.
     */
    private void updateMove() {
        boardManager.updateMoves();
    }

    /**
     * Tap the first tile.
     */
    private void touchMoveFirstTile() {
        boardManager.touchMove(0);
    }

    /**
     * Make another set of tiles that are in order by using different way.
     *
     * @return a set of tiles that are in order
     */

    private List<MatchingTile> makeOtherTiles() {
        List<MatchingTile> tiles = new ArrayList<>();
        final int numTiles = MatchingBoard.numRows * MatchingBoard.numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new MatchingTile(tileNum));
        }

        return tiles;
    }

    /**
     * Test if board manager correctly detects when the game is solved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect();
        assertEquals(false, boardManager.isWin());
        changeToBlank();
        assertEquals(true, boardManager.isWin());
    }

    /**
     * Test whether method IsValidTap works.
     */
    @Test
    public void testIsValidTap() {
        setUpCorrect();
        assertEquals(true, boardManager.isValidTap(9));
        assertEquals(true, boardManager.isValidTap(10));
        assertEquals(true, boardManager.isValidTap(11));
        flipFirstTile();
        assertEquals(false, boardManager.isValidTap(0));

        changeToBlank();
        assertEquals(false, boardManager.isValidTap(9));
        assertEquals(false, boardManager.isValidTap(10));
        assertEquals(false, boardManager.isValidTap(11));
    }

    /**
     * Test whether method flipTile works by flipping first tile and then seeing if it returns the correct tile.
     */
    @Test
    public void testMatchingFlipFirst() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().matchingGetTile(0, 0).getId());
        assertEquals(16, boardManager.getBoard().matchingGetTile(0, 1).getId());
        flipFirstTile();
        assertEquals(1, boardManager.getBoard().matchingGetTile(0, 0).getId());
    }

    /**
     * Test whether method flipTile works by flipping last tile.
     */
    @Test
    public void testMatchingFlipLast() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 3).getId());
        flipLastTile();
        assertEquals(8, boardManager.getBoard().matchingGetTile(3, 3).getId());
    }

    /**
     * Test whether method flipTile works by flipping last tile.
     */
    @Test
    public void testMatchingFlipBack() {
        setUpCorrect();
        flipBack14th();
        assertEquals(7, boardManager.getBoard().matchingGetTile(3, 2).getId());
        boardManager.getBoard().flipBack(3,2);
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 2).getId());
    }

    /**
     * Test whether two tiles will flip back to question marks when these two tiles does not match.
     */
    @Test
    public void testTilesMatching() {
        setUpCorrect();
        flipBack14th();
        boardManager.touchMove(15);
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 2).getId());
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 3).getId());
    }

    /**
     * Test whether method flipBlank works.
     */
    @Test
    public void testMatchingFlipBlankLastTwo() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().matchingGetTile(1, 3).getId());
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 3).getId());
        FlipBlankLastTwo();
        assertEquals(17, boardManager.getBoard().matchingGetTile(1, 3).getId());
        assertEquals(17, boardManager.getBoard().matchingGetTile(3, 3).getId());
    }

    /**
     * Test whether method updateMove works.
     */
    @Test
    public void testMatchingUpdateMove() {
        setUpCorrect();
        assertEquals(0, boardManager.getNumMoves());
        updateMove();
        assertEquals(1, boardManager.getNumMoves());
        updateMove();
        assertEquals(2, boardManager.getNumMoves());
    }

    /**
     * Test whether method touchMove works by touch the first tile.
     */
    @Test
    public void testMatchingTouchMove() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().matchingGetTile(0, 0).getId());
        touchMoveFirstTile();
        assertEquals(1, boardManager.getBoard().matchingGetTile(0, 0).getId());
    }

    /**
     * Test comparing tiles
     */
    @Test
    public void testCompareTiles(){
        setUpCorrect();
        assertEquals(1,boardManager.getBoard().tiles[0][0].compareTo(boardManager.getBoard().tiles[0][1]));
    }

    /**
     * Test whether each tiles get the correct ID by using class MatchingTile.
     */
    @Test
    public void testMatchingTilesGetId() {
        List<MatchingTile> tiles = makeOtherTiles();
        MatchingBoard board = new MatchingBoard(tiles);
        boardManager = new MatchingBoardManager(board);
        assertEquals(1, boardManager.getBoard().tiles[0][0].getId());
        assertEquals(8, boardManager.getBoard().tiles[1][3].getId());
        assertEquals(1, boardManager.getBoard().tiles[2][0].getId());
        assertEquals(8, boardManager.getBoard().tiles[3][3].getId());
        boardManager.getBoard().tiles[0][0] = new MatchingTile(16);
        boardManager.getBoard().tiles[0][1] = new MatchingTile(17);
        assertEquals(17, boardManager.getBoard().tiles[0][0].getId());
        assertEquals(18, boardManager.getBoard().tiles[0][1].getId());
    }

    /**
     * Test whether method getBackground can return the correct background.
     */
    @Test
    public void testMatchingTilesGetBackground(){
        setUpCorrect();
        assertEquals(0, boardManager.getBoard().tiles[0][0].getBackground());
        assertEquals(7, boardManager.getBoard().tiles[1][3].getBackground());
        assertEquals(0, boardManager.getBoard().tiles[2][0].getBackground());
        assertEquals(7, boardManager.getBoard().tiles[3][3].getBackground());
    }

    /**
     * Test whether constructor of MatchingBoardManager works.
     */
    @Test
    public void testCreateBoardManager(){
        boardManager = new MatchingBoardManager();
    }

}

