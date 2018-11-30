package gamecentre;

public abstract class BoardManager {

    /**
     * Number of moves made
     */
    private int numMoves = 0;

    /**
     * count the number of total moves made
     */
    public void updateMoves() {
        numMoves += 1;
    }

    /**
     * @return the highest score
     */
    public int getNumMoves() {
        return numMoves;
    }

    protected abstract boolean isValidTap(int position);

    protected abstract boolean isWin();

    protected abstract void touchMove(int position);
}
