package gamecentre;

public abstract class BoardManager {

    protected abstract boolean isValidTap(int position);

    protected abstract boolean isWin();

    protected abstract void touchMove(int position);
}
