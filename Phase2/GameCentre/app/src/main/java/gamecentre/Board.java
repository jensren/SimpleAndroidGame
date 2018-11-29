package gamecentre;

import java.io.Serializable;

public abstract class Board implements Serializable {
    /**
     * The number of rows.
     */
    public static int numRows = 4;

    /**
     * The number of rows.
     */
    public static int numCols = 4;
    /**
     * The listener that will notify when the board got updated.
     */
    transient private BoardUpdateListener boardUpdateListener = null;

    /**
     * Sets the board update listener.
     *
     * @param boardUpdateListener the board update listener being set.
     */
    public void setBoardUpdateListener(BoardUpdateListener boardUpdateListener){
        this.boardUpdateListener = boardUpdateListener;
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numTiles() {
        return numCols * numRows;
    }

    /**
     * Calls listener's on board changed as long as there is a listener.
     */
    public void listenerUpdate(){
        if (boardUpdateListener != null){
            boardUpdateListener.onBoardChanged();
        }
    }
}
