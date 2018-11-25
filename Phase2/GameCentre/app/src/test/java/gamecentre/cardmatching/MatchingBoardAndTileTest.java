package gamecentre.cardmatching;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import gamecentre.slidingtiles.R;

import static org.junit.Assert.*;

public class MatchingBoardAndTileTest {
    MatchingBoardManager boardManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<MatchingTile> makeTiles() {
        List<MatchingTile> tiles = new ArrayList<>();
        List<MatchingTile> unknownTiles = new ArrayList<>();
        final int numTiles = MatchingBoard.numRows * MatchingBoard.numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if(tileNum >= 8){
                tiles.add(new MatchingTile(tileNum - 8 + 1, tileNum - 8));
            }else{
                tiles.add(new MatchingTile(tileNum + 1, tileNum));
            }
        }

        return tiles;
    }

    /**
     * Make a solved Board.
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
        for (int row = 0; row != MatchingBoard.numRows; row++) {
            for (int col = 0; col != MatchingBoard.numCols; col++) {
                boardManager.getBoard().unknownTiles[row][col] = new MatchingTile(17,R.drawable.tile_25);
            }
        }
        boardManager.tilesMatched = 16;
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

    public void flipFirstTile(){
        boardManager.touchMove(0);
    }

    public void flipLastTile(){
        boardManager.touchMove(15);
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
        assertEquals(16, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(16, boardManager.getBoard().getTile(0, 1).getId());
        flipFirstTile();
        assertEquals(1, boardManager.getBoard().getTile(0, 0).getId());
    }
    @Test
    public void testMatchingSwapLast() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        flipLastTile();
        assertEquals(8, boardManager.getBoard().getTile(3, 3).getId());
    }

    public void FlipBlankLastTwo() {
        for (int row = 0; row != MatchingBoard.numRows; row++) {
            for (int col = 0; col != MatchingBoard.numCols - 1; col++) {
                boardManager.getBoard().unknownTiles[row][col] = new MatchingTile(17, R.drawable.tile_25);
            }
        }
        boardManager.getBoard().unknownTiles[0][3] = new MatchingTile(17, R.drawable.tile_25);
        boardManager.getBoard().unknownTiles[2][3] = new MatchingTile(17, R.drawable.tile_25);
        boardManager.tilesMatched = 14;
        boardManager.touchMove(7);
        boardManager.touchMove(15);
    }
    @Test
    public void testMatchingFlipBlankLastTwo() {
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().getTile(1, 3).getId());
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        FlipBlankLastTwo();
        assertEquals(17, boardManager.getBoard().getTile(1, 3).getId());
        assertEquals(17, boardManager.getBoard().getTile(3, 3).getId());
    }

    public void updateMove(){
        boardManager.updateMoves();
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
    public void touchMoveFirstTile(){
        boardManager.touchMove(0);
    }
    @Test
    public void testMatchingTouchMove(){
        setUpCorrect();
        assertEquals(16, boardManager.getBoard().getTile(0, 0).getId());
        touchMoveFirstTile();
        assertEquals(1, boardManager.getBoard().getTile(0, 0).getId());
    }


}

