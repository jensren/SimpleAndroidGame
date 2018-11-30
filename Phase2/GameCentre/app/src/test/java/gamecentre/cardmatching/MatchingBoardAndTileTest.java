package gamecentre.cardmatching;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import gamecentre.slidingtiles.R;

import static org.junit.Assert.*;

public class MatchingBoardAndTileTest {
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
     * Make a solved board.
     */
    private void setUpCorrect() {
        List<MatchingTile> tiles = makeTiles();
        MatchingBoard board = new MatchingBoard(tiles);
        boardManager = new MatchingBoardManager(board);
    }

    /**
     * Shuffle a few tiles.
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

    private void flipFirstTile() {
        boardManager.touchMove(0);
    }

    private void flipLastTile() {
        boardManager.touchMove(15);
    }

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

    private void FlipBlackLastTwo() {
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

    private void updateMove() {
        boardManager.updateMoves();
    }

    private void touchMoveFirstTile() {
        boardManager.touchMove(0);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect();
        assertEquals(false, boardManager.isWin());
        changeToBlank();
        assertEquals(true, boardManager.isWin());
    }

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

    @Test
    public void testMatchingFlipFirst() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().matchingGetTile(0, 0).getId());
        assertEquals(16, boardManager.getBoard().matchingGetTile(0, 1).getId());
        flipFirstTile();
        assertEquals(1, boardManager.getBoard().matchingGetTile(0, 0).getId());
    }

    @Test
    public void testMatchingSwapLast() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 3).getId());
        flipLastTile();
        assertEquals(8, boardManager.getBoard().matchingGetTile(3, 3).getId());
    }

    @Test
    public void testMatchingFlipBlackLastTwo() {
        setUpCorrect();
        FlipBlackLastTwo();
        assertEquals(7, boardManager.getBoard().matchingGetTile(3, 2).getId());
        boardManager.getBoard().flipBack(3,2);
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 2).getId());
    }

    @Test
    public void testTilesMatching() {
        setUpCorrect();
        FlipBlackLastTwo();
        boardManager.touchMove(15);
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 2).getId());
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 3).getId());
    }

    @Test
    public void testMatchingCheckMatching(){
        setUpCorrect();
        FlipBlackLastTwo();

    }

    @Test
    public void testMatchingFlipBlankLastTwo() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().matchingGetTile(1, 3).getId());
        assertEquals(16, boardManager.getBoard().matchingGetTile(3, 3).getId());
        FlipBlankLastTwo();
        assertEquals(17, boardManager.getBoard().matchingGetTile(1, 3).getId());
        assertEquals(17, boardManager.getBoard().matchingGetTile(3, 3).getId());
    }

    @Test
    public void testMatchingUpdateMove() {
        setUpCorrect();
        assertEquals(0, boardManager.getNumMoves());
        updateMove();
        assertEquals(1, boardManager.getNumMoves());
        updateMove();
        assertEquals(2, boardManager.getNumMoves());
    }

    @Test
    public void testMatchingTouchMove() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().matchingGetTile(0, 0).getId());
        touchMoveFirstTile();
        assertEquals(1, boardManager.getBoard().matchingGetTile(0, 0).getId());
    }

    @Test
    public void testCompareTiles(){
        setUpCorrect();
        assertEquals(1,boardManager.getBoard().tiles[0][0].compareTo(boardManager.getBoard().tiles[0][1]));
    }

    private List<MatchingTile> makeOtherTiles() {
        List<MatchingTile> tiles = new ArrayList<>();
        final int numTiles = MatchingBoard.numRows * MatchingBoard.numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                tiles.add(new MatchingTile(tileNum));
        }

        return tiles;
    }

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

    @Test
    public void testMatchingTilesGetBackgound(){
        setUpCorrect();
        assertEquals(0, boardManager.getBoard().tiles[0][0].getBackground());
        assertEquals(7, boardManager.getBoard().tiles[1][3].getBackground());
        assertEquals(0, boardManager.getBoard().tiles[2][0].getBackground());
        assertEquals(7, boardManager.getBoard().tiles[3][3].getBackground());
    }

    @Test
    public void testCreateBoardManager(){
        boardManager = new MatchingBoardManager();
    }

}

