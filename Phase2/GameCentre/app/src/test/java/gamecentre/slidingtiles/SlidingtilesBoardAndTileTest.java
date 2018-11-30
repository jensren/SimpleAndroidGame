package gamecentre.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SlidingtilesBoardAndTileTest {

    /** The board manager for testing. */
    private SlidingtilesBoardManager boardManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = SlidingtilesBoard.numRows * SlidingtilesBoard.numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }

        return tiles;
    }

    /**
     * Make a solved SlidingtilesBoard.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles();
        SlidingtilesBoard board = new SlidingtilesBoard(tiles);
        boardManager = new SlidingtilesBoardManager(board);
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect();
        assertEquals(true, boardManager.isWin());
        swapFirstTwoTiles();
        assertEquals(false, boardManager.isWin());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
        assertEquals(1, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager.getBoard().getTile(0, 1).getId());
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager.getBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();
        assertEquals(15, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.getBoard().swapTiles(3, 3, 3, 2);
        assertEquals(16, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManager.getBoard().getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setUpCorrect();
        assertEquals(true, boardManager.isValidTap(11));
        assertEquals(true, boardManager.isValidTap(14));
        assertEquals(false, boardManager.isValidTap(10));
    }

    /**
     * Test whether the correct tile is moved after a touch.
     */
    @Test
    public void testTouchMove() {
        setUpCorrect();
        assertEquals(15, boardManager.getBoard().getTile(3,2).getId());
        boardManager.touchMove(14);
        assertEquals(16, boardManager.getBoard().getTile(3,2).getId());
        assertEquals(15, boardManager.getBoard().getTile(3,3).getId());
    }

    /**
     * Test if number moves updates properly.
     */
    @Test
    public void testNumMoves(){
        setUpCorrect();
        boardManager.updateMoves();
        assertEquals(true, boardManager.getNumMoves() == 1);
        boardManager.updateMoves();
        assertEquals(false, boardManager.getNumMoves() == 1);
    }

    /**
     * Tests if solvable function works.
     */
    @Test
    public void testSolvable(){
        setUpCorrect();
        assertEquals(true, boardManager.solvable(makeTiles()));
    }

    /**
     *  Tests if getBoardSize returns the correct board size.
     */
    @Test
    public void testGetBoardSize(){
        setUpCorrect();
        assertEquals(true, boardManager.getBoardSize() == 4);
    }

    /**
     * Tests if boardmanager constructor works.
     */
    @Test
    public void testCreateBoardManager(){
        boardManager = new SlidingtilesBoardManager();
    }

    /**
     * Tests the creation of a 5x5 board.
     */
    @Test
    public void test5x5Board(){
        SlidingtilesBoard.numCols = 5;
        SlidingtilesBoard.numRows = 5;
        boardManager = new SlidingtilesBoardManager();
        SlidingtilesBoard.numRows = 4;
        SlidingtilesBoard.numCols = 4;
    }

    /**
     * Tests the creation of a 3x3 board.
     */
    @Test
    public void test3x3Board(){
        SlidingtilesBoard.numCols = 3;
        SlidingtilesBoard.numRows = 3;
        boardManager = new SlidingtilesBoardManager();
        SlidingtilesBoard.numRows = 4;
        SlidingtilesBoard.numCols = 4;
    }

    /**
     * Test comparing tiles.
     */
    @Test
    public void testCompareTiles(){
        setUpCorrect();
        assertEquals(1,boardManager.board.tiles[0][0].compareTo(boardManager.board.tiles[0][1]));
    }
}

